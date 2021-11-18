/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
 

package org.ajax4jsf.util.base64;

/**
 * <p>Provides the highest level of abstraction for Encoders.
 * This is the sister interface of {@link Decoder}.  Every implementation of
 * Encoder provides this common generic interface whic allows a user to pass a 
 * generic Object to any Encoder implementation in the codec package.</p>
 *
 * @author Apache Software Foundation
 * @version $Id: Encoder.java,v 1.1.2.1 2007/01/09 18:59:12 alexsmirnov Exp $
 */
public interface Encoder {
    
    /**
     * Encodes an "Object" and returns the encoded content 
     * as an Object.  The Objects here may just be <code>byte[]</code>
     * or <code>String</code>s depending on the implementation used.
     *   
     * @param pObject An object ot encode
     * 
     * @return An "encoded" Object
     * 
     * @throws EncoderException an encoder exception is
     *  thrown if the encoder experiences a failure
     *  condition during the encoding process.
     */
    Object encode(Object pObject) throws EncoderException;
}  

