/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Output implementation using java writers.
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class OutJavaImpl implements Out
{

	private static final Log log = LogFactory.getLog(OutJavaImpl.class);
	
    /**
     * Java input stream writer.
     */
    private Writer writer;

    /**
     * Newline string.
     */
    private char[] newline;

    /**
     * Constructor.
     * @param configuration actual configuration instance (needed for newline configuration)
     * @param encoding encoding name
     * @param out output stream
     * @throws UnsupportedEncodingException if the undelining OutputStreamWriter doesn't support the rquested encoding.
     */
    public OutJavaImpl(Configuration configuration, String encoding, OutputStream out)
        throws UnsupportedEncodingException
    {
        this.writer = new OutputStreamWriter(out, encoding);
        this.newline = configuration.newline;
    }

    public OutJavaImpl(Configuration config, String outCharEncodingName, Writer out) {
        this.writer = out;
        this.newline = config.newline;
	}

	/**
     * @see org.ajax4jsf.org.w3c.tidy.Out#outc(int)
     */
    public void outc(int c)  throws IOException
    {
        try
        {
            writer.write(c);
        }
        catch (IOException e)
        {
            // @todo throws exception
            if (log.isErrorEnabled()) {
            	log.error("OutJavaImpl.outc: " + e.getMessage());
            }
            throw e;
        }
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.Out#outc(byte)
     */
    public void outc(byte c)  throws IOException
    {
        try
        {
            writer.write(c);
        }
        catch (IOException e)
        {
            // @todo throws exception
            if (log.isErrorEnabled()) {
            	log.error("OutJavaImpl.outc: " + e.getMessage());
            }
            throw e;
        }
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.Out#newline()
     */
    public void newline()  throws IOException
    {
        try
        {
            writer.write(this.newline);
        }
        catch (IOException e)
        {
            // @todo throws exception
            if (log.isErrorEnabled()) {
            	log.error("OutJavaImpl.newline: " + e.getMessage());
            }
            throw e;
        }
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.Out#close()
     */
    public void close() throws IOException
    {
        try
        {
            writer.close();
        }
        catch (IOException e)
        {
            if (log.isErrorEnabled()) {
            	log.error("OutJavaImpl.close: " + e.getMessage());
            }
            throw e;
        }
    }

}
