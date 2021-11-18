/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/

package org.ajax4jsf.org.w3c.tidy;

/**
 * DOMDocumentTypeImpl.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class DOMDocumentTypeImpl extends DOMNodeImpl implements org.w3c.dom.DocumentType
{

    /**
     * Instantiates a new DOM document type.
     * @param adaptee Tidy Node
     */
    protected DOMDocumentTypeImpl(Node adaptee)
    {
        super(adaptee);
    }

    /**
     * @see org.w3c.dom.Node#getNodeType
     */
    public short getNodeType()
    {
        return org.w3c.dom.Node.DOCUMENT_TYPE_NODE;
    }

    /**
     * @see org.w3c.dom.Node#getNodeName
     */
    public String getNodeName()
    {
        return getName();
    }

    /**
     * @see org.w3c.dom.DocumentType#getName
     */
    public String getName()
    {
        String value = null;
        if (adaptee.type == Node.DOCTYPE_TAG)
        {

            if (adaptee.textarray != null && adaptee.start < adaptee.end)
            {
                value = TidyUtils.getString(adaptee.textarray, adaptee.start, adaptee.end - adaptee.start);
            }
        }
        return value;
    }

    /**
     * @todo DOM level 2 getEntities() Not implemented. Returns null.
     * @see org.w3c.dom.DocumentType#getEntities()
     */
    public org.w3c.dom.NamedNodeMap getEntities()
    {
        return null;
    }

    /**
     * @todo DOM level 2 getNotations() Not implemented. Returns null.
     * @see org.w3c.dom.DocumentType#getNotations()
     */
    public org.w3c.dom.NamedNodeMap getNotations()
    {
        return null;
    }

    /**
     * @todo DOM level 2 getPublicId() Not implemented. Returns null.
     * @see org.w3c.dom.DocumentType#getPublicId()
     */
    public String getPublicId()
    {
        return null;
    }

    /**
     * @todo DOM level 2 getSystemId() Not implemented. Returns null.
     * @see org.w3c.dom.DocumentType#getSystemId()
     */
    public String getSystemId()
    {
        return null;
    }

    /**
     * @todo DOM level 2 getInternalSubset() Not implemented. Returns null.
     * @see org.w3c.dom.DocumentType#getInternalSubset()
     */
    public String getInternalSubset()
    {
        return null;
    }

}