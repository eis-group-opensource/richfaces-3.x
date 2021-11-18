/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * Linked list of style properties.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class StyleProp
{

    /**
     * Style name.
     */
    protected String name;

    /**
     * Style value.
     */
    protected String value;

    /**
     * Next linked style property.
     */
    protected StyleProp next;

    /**
     * Instantiates a new style property.
     * @param name Style name
     * @param value Style value
     * @param next Next linked style property. Can be null.
     */
    public StyleProp(String name, String value, StyleProp next)
    {
        this.name = name;
        this.value = value;
        this.next = next;
    }

}