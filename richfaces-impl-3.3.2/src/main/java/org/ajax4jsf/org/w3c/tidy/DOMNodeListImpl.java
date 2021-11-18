/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * DOMNodeListImpl. The items in the <code>NodeList</code> are accessible via an integral index, starting from 0.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class DOMNodeListImpl implements org.w3c.dom.NodeList
{

    /**
     * Parent Node.
     */
    private Node parent;

    /**
     * Instantiates a new DOM node list.
     * @param parent parent Node
     */
    protected DOMNodeListImpl(Node parent)
    {
        this.parent = parent;
    }

    /**
     * @see org.w3c.dom.NodeList#item(int)
     */
    public org.w3c.dom.Node item(int index)
    {
        if (parent == null)
        {
            return null;
        }

        int i = 0;
        Node node = this.parent.content;
        while (node != null)
        {
            if (i >= index)
            {
                break;
            }
            i++;
            node = node.next;
        }
        if (node != null)
        {
            return node.getAdapter();
        }

        return null;
    }

    /**
     * @see org.w3c.dom.NodeList#getLength
     */
    public int getLength()
    {
        if (parent == null)
        {
            return 0;
        }

        int len = 0;
        Node node = this.parent.content;
        while (node != null)
        {
            len++;
            node = node.next;
        }
        return len;
    }

}