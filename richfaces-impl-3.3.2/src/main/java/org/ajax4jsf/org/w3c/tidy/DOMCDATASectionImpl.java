/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * Tidy implementation of org.w3c.dom.CDATASection.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class DOMCDATASectionImpl extends DOMTextImpl implements org.w3c.dom.CDATASection
{

    /**
     * Instantiates a new DOMCDATASectionImpl which wraps the given Node.
     * @param adaptee wrapped node.
     */
    protected DOMCDATASectionImpl(Node adaptee)
    {
        super(adaptee);
    }

    /**
     * @see org.w3c.dom.Node#getNodeName
     */
    public String getNodeName()
    {
        return "#cdata-section";
    }

    /**
     * @see org.w3c.dom.Node#getNodeType
     */
    public short getNodeType()
    {
        return org.w3c.dom.Node.CDATA_SECTION_NODE;
    }
}