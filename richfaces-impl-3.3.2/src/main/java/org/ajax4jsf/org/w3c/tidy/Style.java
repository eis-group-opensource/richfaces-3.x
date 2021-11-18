/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * Linked list of class names and styles.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class Style
{

    /**
     * Tag name.
     */
    protected String tag;

    /**
     * Tag class.
     */
    protected String tagClass;

    /**
     * Style properties.
     */
    protected String properties;

    /**
     * Next linked style element.
     */
    protected Style next;

    /**
     * Instantiates a new style.
     * @param tag Tag name
     * @param tagClass Tag class
     * @param properties Style properties
     * @param next Next linked style element. Can be null.
     */
    public Style(String tag, String tagClass, String properties, Style next)
    {
        this.tag = tag;
        this.tagClass = tagClass;
        this.properties = properties;
        this.next = next;
    }

}