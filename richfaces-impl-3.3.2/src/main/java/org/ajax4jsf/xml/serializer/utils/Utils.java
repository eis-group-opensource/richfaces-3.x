/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.xml.serializer.utils;


/**
 * This class contains utilities used by the serializer.
 * 
 * This class is not a public API, it is only public because it is
 * used by org.ajax4jsf.xml.serializer.
 * 
 * @xsl.usage internal
 */
public final class Utils
{
    /**
     * A singleton Messages object is used to load the 
     * given resource bundle just once, it is
     * used by multiple transformations as long as the JVM stays up.
     */
    public static final org.ajax4jsf.xml.serializer.utils.Messages messages= 
        new org.ajax4jsf.xml.serializer.utils.Messages(
            "org.ajax4jsf.xml.serializer.utils.SerializerMessages");
}
