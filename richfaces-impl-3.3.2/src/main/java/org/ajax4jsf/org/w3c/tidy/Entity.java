/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * HTML ISO entity.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class Entity
{

    /**
     * entity name.
     */
    private String name;

    /**
     * entity code.
     */
    private short code;

    /**
     * instantiates a new entity.
     * @param name entity name
     * @param code entity code (will be casted to short)
     */
    public Entity(String name, int code)
    {
        this.name = name;
        this.code = (short) code;
    }

    /**
     * Getter for <code>code</code>.
     * @return Returns the code.
     */
    public short getCode()
    {
        return this.code;
    }

    /**
     * Getter for <code>name</code>.
     * @return Returns the name.
     */
    public String getName()
    {
        return this.name;
    }
}