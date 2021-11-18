/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
 

package org.ajax4jsf.util.base64;

/**
 * Defines common decoding methods for byte array decoders.
 *
 * @author Apache Software Foundation
 * @version $Id: BinaryDecoder.java,v 1.1.2.1 2007/01/09 18:59:12 alexsmirnov Exp $
 */
public interface BinaryDecoder extends Decoder {

    /**
     * Decodes a byte array and returns the results as a byte array. 
     *
     * @param pArray A byte array which has been encoded with the
     *      appropriate encoder
     * 
     * @return a byte array that contains decoded content
     * 
     * @throws DecoderException A decoder exception is thrown
     *          if a Decoder encounters a failure condition during
     *          the decode process.
     */
    byte[] decode(byte[] pArray) throws DecoderException;
}  

