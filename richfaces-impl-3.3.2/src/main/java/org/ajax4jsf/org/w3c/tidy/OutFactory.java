/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.ajax4jsf.Messages;


/**
 * Tidy Output factory.
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public final class OutFactory
{

    /**
     * Don't instantiate.
     */
    private OutFactory()
    {
        // unused
    }

    /**
     * Returns the appropriate Out implementation.
     * @param config configuration instance
     * @param stream output stream
     * @return out instance
     */
    public static Out getOut(Configuration config, OutputStream stream)
    {
        try
        {
            return new OutJavaImpl(config, config.getOutCharEncodingName(), stream);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(Messages.getMessage(Messages.UNSUPPORTED_ENCODING_ERROR, e.getMessage()));
        }
    }

	public static Out getOut(Configuration config, Writer out) {
            return new OutJavaImpl(config, config.getOutCharEncodingName(), out);
	}
}
