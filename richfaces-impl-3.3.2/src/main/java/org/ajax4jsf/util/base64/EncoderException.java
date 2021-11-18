/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
 

package org.ajax4jsf.util.base64;

/**
 * Thrown when there is a failure condition during the encoding process.  This
 * exception is thrown when an Encoder encounters a encoding specific exception
 * such as invalid data, inability to calculate a checksum, characters outside of the 
 * expected range.
 * 
 * @author Apache Software Foundation
 * @version $Id: EncoderException.java,v 1.1.2.1 2007/01/09 18:59:12 alexsmirnov Exp $
 */
public class EncoderException extends Exception {

    /**
     * Creates a new instance of this exception with an useful message.
     * 
     * @param pMessage a useful message relating to the encoder specific error.
     */
    public EncoderException(String pMessage) {
        super(pMessage);
    }
}  

