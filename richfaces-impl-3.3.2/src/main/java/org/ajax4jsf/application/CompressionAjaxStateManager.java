/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.application;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.faces.FacesException;
import javax.faces.application.StateManager;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.render.ResponseStateManager;

import org.ajax4jsf.context.ContextInitParameters;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.myfaces.shared_impl.util.StateUtils;
import org.apache.myfaces.shared_impl.util.serial.SerialFactory;

/**
 * Overrides the Ajax state manager to enable the state compression.
 * 
 * should be in Richfaces 3.3.3
 * {@link https://jira.jboss.org/browse/RF-8145},
 *  {@link http://www.logikdev.com/2009/11/17/richfaces-is-too-greedy/}
 * 
 * @author Stephane Moreau
 * 
 */
public class CompressionAjaxStateManager extends AjaxStateManager {
    /**
     * Only applicable if state saving method is "server" (= default) and if
     * <code>org.apache.myfaces.SERIALIZE_STATE_IN_SESSION</code> is
     * <code>true</code> (= default).
     * If <code>true</code> (default) the serialized state will be compressed before
     * it is written to the session.
     * If <code>false</code> the state will not be compressed.
     */
    private static final String COMPRESS_SERVER_STATE_PARAM = "org.apache.myfaces.COMPRESS_STATE_IN_SESSION";

    /**
     * Default value for <code>org.apache.myfaces.COMPRESS_STATE_IN_SESSION</code>
     * context parameter.
     */
    private static final boolean DEFAULT_COMPRESS_SERVER_STATE_PARAM = true;

    private static final int UNCOMPRESSED_FLAG = 0;
    private static final int COMPRESSED_FLAG = 1;

    private static final Log log = LogFactory.getLog(CompressionAjaxStateManager.class);
    
    private final ComponentsLoader componentLoader;

    public CompressionAjaxStateManager(StateManager stateManager) {
        super(stateManager);
        componentLoader = new ComponentsLoaderImpl();
    }

    /**
     * Reads the value of the <code>org.apache.myfaces.COMPRESS_STATE_IN_SESSION</code>
     * context parameter.
     * @see COMPRESS_SERVER_STATE_PARAM
     * @param context <code>FacesContext</code> for the request we are processing.
     * @return boolean true, if the server state steam should be compressed
     */
    protected static boolean isCompressStateInSession(FacesContext context) {
        String value = context.getExternalContext().getInitParameter(
                COMPRESS_SERVER_STATE_PARAM);
        boolean compress = DEFAULT_COMPRESS_SERVER_STATE_PARAM;
        if (value != null) {
            compress = Boolean.valueOf(value);
        }
        return compress;
    }

    @Override
    protected Object[] buildViewState(FacesContext context) {
        Object[] viewStateArray = null;
        UIViewRoot viewRoot = context.getViewRoot();
        if (null != viewRoot && !viewRoot.isTransient()) {
            TreeStructureNode treeStructure = (TreeStructureNode) getTreeStructureToSave(context);
            Object state = getComponentStateToSave(context);
            if (isSavingStateInClient(context)) {
                viewStateArray = new Object[]{treeStructure, state};
            } else {
                viewStateArray = saveStateInSession(context, treeStructure,
                        handleSaveState(context, state));
            }

        }
        return viewStateArray;
    }

    @Override
    public UIViewRoot restoreView(FacesContext context, String viewId,
            String renderKitId) {
        UIViewRoot viewRoot = null;
        ResponseStateManager responseStateManager = getRenderKit(context,
                renderKitId).getResponseStateManager();
        TreeStructureNode treeStructure = null;
        Object[] state = null;
        Object[] serializedView = null;
        if (isSavingStateInClient(context)) {
            serializedView = (Object[]) responseStateManager.getState(context,
                    viewId);

            if (null != serializedView) {
                treeStructure = (TreeStructureNode) serializedView[0];
                state = (Object[]) serializedView[1];
            }
        } else {
            serializedView = restoreStateFromSession(context, viewId,
                    renderKitId);

            if (null != serializedView) {
                treeStructure = (TreeStructureNode) serializedView[0];
                state = (Object[]) handleRestoreState(context, serializedView[1]);
            }
        }

        if (null != treeStructure) {
            viewRoot = (UIViewRoot) treeStructure.restore(componentLoader);
            if (null != viewRoot && null != state) {
                viewRoot.processRestoreState(context, state[0]);
                restoreAdditionalState(context, state[1]);
            }
        }
        return viewRoot;

    }

    private static final Object handleSaveState(FacesContext context, Object state) {
        if (ContextInitParameters.isSerializeServerState(context)) {
            
            SerialFactory serialFactory = (SerialFactory) context.getExternalContext().getApplicationMap().get(StateUtils.SERIAL_FACTORY);
            
            if(serialFactory == null)
                throw new NullPointerException("serialFactory");
            
            long t = System.currentTimeMillis();
            
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            ObjectOutputStream oas = null;
            try {
                OutputStream os = baos;
                if(isCompressStateInSession(context))
                {
                    os.write(COMPRESSED_FLAG);
                    os = new GZIPOutputStream(os, 1024);
                }
                else
                {
                    os.write(UNCOMPRESSED_FLAG);
                }

//                oas = new ObjectOutputStream(os);
                oas = serialFactory.getObjectOutputStream(os);
                oas.writeObject(state);
                oas.flush();
            } catch (Exception e) {
                throw new FacesException(e);
            } finally {
                if (oas != null) {
                    try {
                        oas.close();
                    } catch (IOException ignored) { }
                }
            }
            
            t = System.currentTimeMillis() - t;
            if (log.isDebugEnabled())
                log.debug("Serialized t = " + t + " ms, size = " + baos.size());
            
            return baos.toByteArray();
        } else {
            return state;
        }
    }

    private static final Map<String,Class<?>> PRIMITIVE_CLASSES =
        new HashMap<String,Class<?>>(9, 1.0F);

    static {
        PRIMITIVE_CLASSES.put("boolean", boolean.class);
        PRIMITIVE_CLASSES.put("byte", byte.class);
        PRIMITIVE_CLASSES.put("char", char.class);
        PRIMITIVE_CLASSES.put("short", short.class);
        PRIMITIVE_CLASSES.put("int", int.class);
        PRIMITIVE_CLASSES.put("long", long.class);
        PRIMITIVE_CLASSES.put("float", float.class);
        PRIMITIVE_CLASSES.put("double", double.class);
        PRIMITIVE_CLASSES.put("void", void.class);
    }

    private static final Object handleRestoreState(FacesContext context, Object state) {
        if (ContextInitParameters.isSerializeServerState(context)) {
            
            SerialFactory serialFactory = (SerialFactory) context.getExternalContext().getApplicationMap().get(StateUtils.SERIAL_FACTORY);
            
            if(serialFactory == null)
                throw new NullPointerException("serialFactory");

            long t = System.currentTimeMillis();
            
            ObjectInputStream ois = null;
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream((byte[]) state);
                InputStream is = bais;
                if(is.read() == COMPRESSED_FLAG) {
                    is = new GZIPInputStream(is);
                }
                ois = serialFactory.getObjectInputStream(is);
//                ois = new ObjectInputStream(is) {
//                    @Override
//                    protected Class<?> resolveClass(ObjectStreamClass desc)
//                    throws IOException, ClassNotFoundException {
//                        String name = desc.getName();
//                        try {
//                            return Class.forName(name, true,
//                                    Thread.currentThread().getContextClassLoader());
//                        } catch (ClassNotFoundException cnfe) {
//                            Class<?> clazz = PRIMITIVE_CLASSES.get(name);
//                            if (clazz != null) {
//                                return clazz;
//                            } else {
//                                throw cnfe;
//                            }
//                        }
//                    }
//                };
                
                
                return ois.readObject();
            } catch (Exception e) {
                throw new FacesException(e);
            } finally {
                
                t = System.currentTimeMillis() - t;
                if (log.isDebugEnabled())
                    log.debug("Deserialized t = " + t + " ms");

                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ignored) { }
                }
            }
        } else {
            return state;
        }
    }

}
