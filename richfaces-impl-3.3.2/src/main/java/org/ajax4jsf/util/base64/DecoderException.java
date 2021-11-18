/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
 

package org.ajax4jsf.util.base64;

/**
 * Thrown when a Decoder has encountered a failure condition during a decode. 
 * 
 * @author Apache Software Foundation
 * @version $Id: DecoderException.java,v 1.1.2.1 2007/01/09 18:59:13 alexsmirnov Exp $
 */
public class DecoderException extends Exception {

    /**
     * Creates a DecoderException
     * 
     * @param pMessage A message with meaning to a human
     */
    public DecoderException(String pMessage) {
        super(pMessage);
    }

}  

