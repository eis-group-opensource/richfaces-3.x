/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * Tidy implementation of org.w3c.dom.Comment.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class DOMCommentImpl extends DOMCharacterDataImpl implements org.w3c.dom.Comment
{

    /**
     * Instantiates a new DOMCommentImpl which wraps the given Node.
     * @param adaptee wrapped node.
     */
    protected DOMCommentImpl(Node adaptee)
    {
        super(adaptee);
    }

    /**
     * @see org.w3c.dom.Node#getNodeName
     */
    public String getNodeName()
    {
        return "#comment";
    }

    /**
     * @see org.w3c.dom.Node#getNodeType
     */
    public short getNodeType()
    {
        return org.w3c.dom.Node.COMMENT_NODE;
    }

}