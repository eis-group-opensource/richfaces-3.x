/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

import org.ajax4jsf.Messages;
import org.w3c.dom.DOMException;


/**
 * Tidy implementation of org.w3c.dom.NamedNodeMap.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class DOMAttrMapImpl implements org.w3c.dom.NamedNodeMap
{

    /**
     * wrapped org.w3c.tidy.AttVal.
     */
    private AttVal first;

    /**
     * instantiates a new DOMAttrMapImpl for the given AttVal.
     * @param firstAttVal wrapped AttVal
     */
    protected DOMAttrMapImpl(AttVal firstAttVal)
    {
        this.first = firstAttVal;
    }

    /**
     * @see org.w3c.dom.NamedNodeMap#getNamedItem(java.lang.String)
     */
    public org.w3c.dom.Node getNamedItem(String name)
    {
        AttVal att = this.first;
        while (att != null)
        {
            if (att.attribute.equals(name))
            {
                break;
            }
            att = att.next;
        }
        if (att != null)
        {
            return att.getAdapter();
        }

        return null;
    }

    /**
     * @see org.w3c.dom.NamedNodeMap#item
     */
    public org.w3c.dom.Node item(int index)
    {
        int i = 0;
        AttVal att = this.first;
        while (att != null)
        {
            if (i >= index)
            {
                break;
            }
            i++;
            att = att.next;
        }
        if (att != null)
        {
            return att.getAdapter();
        }

        return null;
    }

    /**
     * @see org.w3c.dom.NamedNodeMap#getLength
     */
    public int getLength()
    {
        int len = 0;
        AttVal att = this.first;
        while (att != null)
        {
            len++;
            att = att.next;
        }
        return len;
    }

    /**
     * @todo DOM level 2 setNamedItem() Not implemented. Throws NOT_SUPPORTED_ERR.
     * @see org.w3c.dom.NamedNodeMap#setNamedItem
     */
    public org.w3c.dom.Node setNamedItem(org.w3c.dom.Node arg) throws DOMException
    {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, Messages.getMessage(Messages.DOM_METHOD_NOT_SUPPORTED));
    }

    /**
     * @see org.w3c.dom.NamedNodeMap#removeNamedItem
     */
    public org.w3c.dom.Node removeNamedItem(String name) throws DOMException
    {
        AttVal att = this.first;
        AttVal previous = null;

        while (att != null)
        {
            if (att.attribute.equals(name))
            {
                if (previous == null)
                {
                    this.first = att.getNext();
                }
                else
                {
                    previous.setNext(att.getNext());
                }

                break;
            }
            previous = att;
            att = att.next;
        }

        if (att != null)
        {
            return att.getAdapter();
        }

        throw new DOMException(DOMException.NOT_FOUND_ERR, Messages.getMessage(Messages.NAMED_ITEM_NOT_FOUND_ERROR, name));
    }

    /**
     * Not supported, returns <code>DOMException.NOT_SUPPORTED_ERR</code>.
     * @see org.w3c.dom.NamedNodeMap#getNamedItemNS(java.lang.String, java.lang.String)
     */
    public org.w3c.dom.Node getNamedItemNS(String namespaceURI, String localName)
    {
        // NOT_SUPPORTED_ERR: May be raised if the implementation does not support the feature "XML" and the language
        // exposed through the Document does not support XML Namespaces (such as HTML 4.01).
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, Messages.getMessage(Messages.DOM_METHOD_NOT_SUPPORTED));
    }

    /**
     * Not supported, returns <code>DOMException.NOT_SUPPORTED_ERR</code>.
     * @see org.w3c.dom.NamedNodeMap#setNamedItemNS(org.w3c.dom.Node)
     */
    public org.w3c.dom.Node setNamedItemNS(org.w3c.dom.Node arg) throws org.w3c.dom.DOMException
    {
        // NOT_SUPPORTED_ERR: May be raised if the implementation does not support the feature "XML" and the language
        // exposed through the Document does not support XML Namespaces (such as HTML 4.01).
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, Messages.getMessage(Messages.DOM_METHOD_NOT_SUPPORTED));
    }

    /**
     * Not supported, returns <code>DOMException.NOT_SUPPORTED_ERR</code>.
     * @see org.w3c.dom.NamedNodeMap#removeNamedItemNS(java.lang.String, java.lang.String)
     */
    public org.w3c.dom.Node removeNamedItemNS(String namespaceURI, String localName) throws org.w3c.dom.DOMException
    {
        // NOT_SUPPORTED_ERR: May be raised if the implementation does not support the feature "XML" and the language
        // exposed through the Document does not support XML Namespaces (such as HTML 4.01).
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR, Messages.getMessage(Messages.DOM_METHOD_NOT_SUPPORTED));
    }

}