/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.xml.serializer;

import java.util.Properties;

/**
 * This class contains some static methods that act as helpers when parsing a
 * Java Property object.
 * 
 * This class is not a public API. 
 * It is only public because it is used outside of this package.
 * 
 * @see java.util.Properties
 * @xsl.usage internal
 */
public final class OutputPropertyUtils
{
    /**
      * Searches for the boolean property with the specified key in the property list.
      * If the key is not found in this property list, the default property list,
      * and its defaults, recursively, are then checked. The method returns
      * <code>false</code> if the property is not found, or if the value is other
      * than "yes".
      *
      * @param   key   the property key.
      * @param   props   the list of properties that will be searched.
      * @return  the value in this property list as a boolean value, or false
      * if null or not "yes".
      */
    public static boolean getBooleanProperty(String key, Properties props)
    {

        String s = props.getProperty(key);

        if (null == s || !s.equals("yes"))
            return false;
        else
            return true;
    }

    /**
     * Searches for the int property with the specified key in the property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns
     * <code>false</code> if the property is not found, or if the value is other
     * than "yes".
     *
     * @param   key   the property key.
     * @param   props   the list of properties that will be searched.
     * @return  the value in this property list as a int value, or 0
     * if null or not a number.
     */
    public static int getIntProperty(String key, Properties props)
    {

        String s = props.getProperty(key);

        if (null == s)
            return 0;
        else
            return Integer.parseInt(s);
    }

}
