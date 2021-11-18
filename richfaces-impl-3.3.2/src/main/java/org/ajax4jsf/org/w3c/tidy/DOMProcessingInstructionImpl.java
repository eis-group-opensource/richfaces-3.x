/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

import org.ajax4jsf.Messages;
import org.w3c.dom.DOMException;


/**
 * DOMProcessingInstructionImpl.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class DOMProcessingInstructionImpl extends DOMNodeImpl implements org.w3c.dom.ProcessingInstruction
{

    /**
     * Instantiates a new DOM processing instruction.
     * @param adaptee wrapped Tidy node
     */
    protected DOMProcessingInstructionImpl(Node adaptee)
    {
        super(adaptee);
    }

    /**
     * @see org.w3c.dom.Node#getNodeType
     */
    public short getNodeType()
    {
        return org.w3c.dom.Node.PROCESSING_INSTRUCTION_NODE;
    }

    /**
     * @todo DOM level 2 getTarget() Not implemented. Returns null.
     * @see org.w3c.dom.ProcessingInstruction#getTarget
     */
    public String getTarget()
    {
        return null;
    }

    /**
     * @see org.w3c.dom.ProcessingInstruction#getData
     */
    public String getData()
    {
        return getNodeValue();
    }

    /**
     * @see org.w3c.dom.ProcessingInstruction#setData(java.lang.String)
     */
    public void setData(String data) throws DOMException
    {
        throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR, Messages.getMessage(Messages.READ_ONLY_NODE_ERROR));
    }

}