/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * Anchor/node Linked list.
 * @author hoehrmann
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class Anchor
{

    /**
     * Anchor name.
     */
    protected String name;

    /**
     * Next anchor.
     */
    protected Anchor next;

    /**
     * linked node.
     */
    protected Node node;

}