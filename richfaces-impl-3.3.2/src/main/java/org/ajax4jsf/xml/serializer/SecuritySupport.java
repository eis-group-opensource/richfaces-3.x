/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/

package org.ajax4jsf.xml.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.ajax4jsf.resource.util.URLToStreamHelper;

/**
 * This class is duplicated for each Xalan-Java subpackage so keep it in sync.
 * It is package private and therefore is not exposed as part of the Xalan-Java
 * API.
 *
 * Base class with security related methods that work on JDK 1.1.
 */
class SecuritySupport {

    /*
     * Make this of type Object so that the verifier won't try to
     * prove its type, thus possibly trying to load the SecuritySupport12
     * class.
     */
    private static final Object securitySupport;

    static {
	SecuritySupport ss = null;
	try {
	    Class c = Class.forName("java.security.AccessController");
	    // if that worked, we're on 1.2.
	    /*
	    // don't reference the class explicitly so it doesn't
	    // get dragged in accidentally.
	    c = Class.forName("javax.mail.SecuritySupport12");
	    Constructor cons = c.getConstructor(new Class[] { });
	    ss = (SecuritySupport)cons.newInstance(new Object[] { });
	    */
	    /*
	     * Unfortunately, we can't load the class using reflection
	     * because the class is package private.  And the class has
	     * to be package private so the APIs aren't exposed to other
	     * code that could use them to circumvent security.  Thus,
	     * we accept the risk that the direct reference might fail
	     * on some JDK 1.1 JVMs, even though we would never execute
	     * this code in such a case.  Sigh...
	     */
	    ss = new SecuritySupport12();
	} catch (Exception ex) {
	    // ignore it
	} finally {
	    if (ss == null)
		ss = new SecuritySupport();
	    securitySupport = ss;
	}
    }

    /**
     * Return an appropriate instance of this class, depending on whether
     * we're on a JDK 1.1 or J2SE 1.2 (or later) system.
     */
    static SecuritySupport getInstance() {
	return (SecuritySupport)securitySupport;
    }

    ClassLoader getContextClassLoader() {
	return null;
    }

    ClassLoader getSystemClassLoader() {
        return null;
    }

    ClassLoader getParentClassLoader(ClassLoader cl) {
        return null;
    }

    String getSystemProperty(String propName) {
        return System.getProperty(propName);
    }

    FileInputStream getFileInputStream(File file)
        throws FileNotFoundException
    {
        return new FileInputStream(file);
    }

    InputStream getResourceAsStream(ClassLoader cl, String name) {
        InputStream ris;
        if (cl == null) {
            ris = URLToStreamHelper.urlToStreamSafe(ClassLoader.getSystemResource(name));
        } else {
            ris = URLToStreamHelper.urlToStreamSafe(cl.getResource(name));
        }
        return ris;
    }
    
    boolean getFileExists(File f) {
        return f.exists();
    }
    
    long getLastModified(File f) {
        return f.lastModified();
    }    
}
