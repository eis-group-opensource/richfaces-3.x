/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.ajax4jsf.Messages;


/**
 * Tidy Input factory.
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public final class StreamInFactory
{

    /**
     * Don't instantiate.
     */
    private StreamInFactory()
    {
        // unused
    }

    /**
     * Returns the appropriate StreamIn implementation.
     * @param config configuration instance
     * @param stream input stream
     * @return StreamIn instance
     */
    public static StreamIn getStreamIn(Configuration config, Object in)
    {
    	if (in instanceof Reader) {
            return new StreamInJavaImpl((Reader) in, config.tabsize);			
		} else if (in instanceof String) {
            return new StreamInJavaImpl((String) in, config.tabsize);			
		} else if (in instanceof InputStream) {			
        try
        {
            return new StreamInJavaImpl((InputStream)in, config.getInCharEncodingName(), config.tabsize);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(Messages.getMessage(Messages.UNSUPPORTED_ENCODING_ERROR, e.getMessage()));
        }
		} else {
            throw new RuntimeException(Messages.getMessage(Messages.UNSUPPORTED_INPUT_SOURCE_ERROR, in.getClass().getName()));			
		}
    }
}
