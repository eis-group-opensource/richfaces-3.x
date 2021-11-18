/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
 

package org.ajax4jsf.util.base64;

/**
 * <p>Provides the highest level of abstraction for Decoders.
 * This is the sister interface of {@link Encoder}.  All
 * Decoders implement this common generic interface.</p>
 * 
 * <p>Allows a user to pass a generic Object to any Decoder 
 * implementation in the codec package.</p>
 * 
 * <p>One of the two interfaces at the center of the codec package.</p>
 * 
 * @author Apache Software Foundation
 * @version $Id: Decoder.java,v 1.1.2.1 2007/01/09 18:59:14 alexsmirnov Exp $
 */
public interface Decoder {

    /**
     * Decodes an "encoded" Object and returns a "decoded"
     * Object.  Note that the implementation of this
     * interface will try to cast the Object parameter
     * to the specific type expected by a particular Decoder
     * implementation.  If a {@link java.lang.ClassCastException} occurs
     * this decode method will throw a DecoderException.
     * 
     * @param pObject an object to "decode"
     * 
     * @return a 'decoded" object
     * 
     * @throws DecoderException a decoder exception can
     * be thrown for any number of reasons.  Some good
     * candidates are that the parameter passed to this
     * method is null, a param cannot be cast to the
     * appropriate type for a specific encoder.
     */
    Object decode(Object pObject) throws DecoderException;
}  

