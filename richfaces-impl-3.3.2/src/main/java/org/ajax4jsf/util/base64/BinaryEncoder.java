/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
 

package org.ajax4jsf.util.base64;

/**
 * Defines common encoding methods for byte array encoders.
 * 
 * @author Apache Software Foundation
 * @version $Id: BinaryEncoder.java,v 1.1.2.1 2007/01/09 18:59:13 alexsmirnov Exp $
 */
public interface BinaryEncoder extends Encoder {
    
    /**
     * Encodes a byte array and return the encoded data
     * as a byte array.
     * 
     * @param pArray Data to be encoded
     *
     * @return A byte array containing the encoded data
     * 
     * @throws EncoderException thrown if the Encoder
     *      encounters a failure condition during the
     *      encoding process.
     */
    byte[] encode(byte[] pArray) throws EncoderException;
}  

