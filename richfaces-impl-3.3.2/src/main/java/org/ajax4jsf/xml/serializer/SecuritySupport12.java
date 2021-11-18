/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/

package org.ajax4jsf.xml.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

import org.ajax4jsf.resource.util.URLToStreamHelper;

/**
 * This class is duplicated for each Xalan-Java subpackage so keep it in sync.
 * It is package private and therefore is not exposed as part of the Xalan-Java
 * API.
 *
 * Security related methods that only work on J2SE 1.2 and newer.
 */
class SecuritySupport12 extends SecuritySupport {

    ClassLoader getContextClassLoader() {
        return (ClassLoader)
                AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ClassLoader cl = null;
                try {
                    cl = Thread.currentThread().getContextClassLoader();
                } catch (SecurityException ex) { }
                return cl;
            }
        });
    }

    ClassLoader getSystemClassLoader() {
        return (ClassLoader)
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    ClassLoader cl = null;
                    try {
                        cl = ClassLoader.getSystemClassLoader();
                    } catch (SecurityException ex) {}
                    return cl;
                }
            });
    }

    ClassLoader getParentClassLoader(final ClassLoader cl) {
        return (ClassLoader)
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    ClassLoader parent = null;
                    try {
                        parent = cl.getParent();
                    } catch (SecurityException ex) {}

                    // eliminate loops in case of the boot
                    // ClassLoader returning itself as a parent
                    return (parent == cl) ? null : parent;
                }
            });
    }

    String getSystemProperty(final String propName) {
        return (String)
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    return System.getProperty(propName);
                }
            });
    }

    FileInputStream getFileInputStream(final File file)
        throws FileNotFoundException
    {
        try {
            return (FileInputStream)
                AccessController.doPrivileged(new PrivilegedExceptionAction() {
                    public Object run() throws FileNotFoundException {
                        return new FileInputStream(file);
                    }
                });
        } catch (PrivilegedActionException e) {
            throw (FileNotFoundException)e.getException();
        }
    }

    InputStream getResourceAsStream(final ClassLoader cl,
                                           final String name)
    {
        return (InputStream)
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    InputStream ris;
                    if (cl == null) {
                        ris = URLToStreamHelper.urlToStreamSafe(ClassLoader.getSystemResource(name));
                    } else {
                        ris = URLToStreamHelper.urlToStreamSafe(cl.getResource(name));
                    }
                    return ris;
                }
            });
    }
    
    boolean getFileExists(final File f) {
    return ((Boolean)
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    return new Boolean(f.exists());
                }
            })).booleanValue();
    }
    
    long getLastModified(final File f) {
    return ((Long)
            AccessController.doPrivileged(new PrivilegedAction() {
                public Object run() {
                    return new Long(f.lastModified());
                }
            })).longValue();
    }
        
}
