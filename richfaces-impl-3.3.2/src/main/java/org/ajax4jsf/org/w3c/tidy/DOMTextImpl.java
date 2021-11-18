/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

import org.ajax4jsf.Messages;
import org.w3c.dom.DOMException;
import org.w3c.dom.Text;


/**
 * DOMTextImpl.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class DOMTextImpl extends DOMCharacterDataImpl implements org.w3c.dom.Text
{

    /**
     * Instantiates a new DOM text node.
     * @param adaptee wrapped Tidy node
     */
    protected DOMTextImpl(Node adaptee)
    {
        super(adaptee);
    }

    /**
     * @see org.w3c.dom.Node#getNodeName
     */
    public String getNodeName()
    {
        return "#text";
    }

    /**
     * @see org.w3c.dom.Node#getNodeType
     */
    public short getNodeType()
    {
        return org.w3c.dom.Node.TEXT_NODE;
    }

    /**
     * @todo DOM level 2 splitText() Not supported. Throws NO_MODIFICATION_ALLOWED_ERR.
     * @see org.w3c.dom.Text#splitText(int)
     */
    public org.w3c.dom.Text splitText(int offset) throws DOMException
    {
        throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, Messages.getMessage(Messages.DOM_METHOD_NOT_SUPPORTED));
    }

    /**
     * @todo DOM level 3 getWholeText() Not implemented. Returns null.
     * @see org.w3c.dom.Text#getWholeText()
     */
    public String getWholeText()
    {
        return null;
    }

    /**
     * @todo DOM level 3 isElementContentWhitespace() Not implemented. Returns false.
     * @see org.w3c.dom.Text#isElementContentWhitespace()
     */
    public boolean isElementContentWhitespace()
    {
        return false;
    }

    /**
     * @todo DOM level 3 replaceWholeText() Not implemented. Returns the same node.
     * @see org.w3c.dom.Text#isElementContentWhitespace()
     */
    public Text replaceWholeText(String content) throws DOMException
    {
        return this;
    }
}