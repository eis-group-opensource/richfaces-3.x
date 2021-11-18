/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

import java.io.IOException;
import java.io.OutputStream;

import org.ajax4jsf.org.w3c.tidy.EncodingUtils.PutBytes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * Output implementation. This implementation is from the c version of tidy and it doesn't take advantage of java
 * writers.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class OutImpl implements Out
{
	
	private static final Log log = LogFactory.getLog(OutImpl.class);

    /**
     * output encoding.
     */
    private int encoding;

    /**
     * actual state for ISO 2022.
     */
    private int state;

    /**
     * output stream.
     */
    private OutputStream out;

    /**
     * putter callback.
     */
    private PutBytes putBytes;

    /**
     * newline bytes.
     */
    private byte[] newline;

    /**
     * Constructor.
     * @param configuration actual configuration instance (needed for newline configuration)
     * @param encoding encoding constant
     * @param out output stream
     */
    public OutImpl(Configuration configuration, int encoding, OutputStream out)
    {
        this.encoding = encoding;
        this.state = EncodingUtils.FSM_ASCII;
        this.out = out;

        // copy configured newline in bytes
        this.newline = new byte[configuration.newline.length];
        for (int j = 0; j < configuration.newline.length; j++)
        {
            this.newline[j] = (byte) configuration.newline[j];
        }

        this.putBytes = new PutBytes()
        {

            private OutImpl impl;

            PutBytes setOut(OutImpl out)
            {
                this.impl = out;
                return this;
            }

            public void doPut(byte[] buf, int[] count)
            {
                impl.outcUTF8Bytes(buf, count);
            }
        } // set the out instance direclty
            .setOut(this);
    }

    /**
     * output UTF-8 bytes to output stream.
     * @param buf array of bytes
     * @param count number of bytes in buf to write
     */
    void outcUTF8Bytes(byte[] buf, int[] count)
    {
        try
        {
            for (int i = 0; i < count[0]; i++)
            {
                out.write(buf[i]);
            }
        }
        catch (IOException e)
        {
            log.error("OutImpl.outcUTF8Bytes: " + e.toString());
        }
    }

    /**
     * .
     * @see org.ajax4jsf.org.w3c.tidy.Out#outc(byte)
     */
    public void outc(byte c)
    {
        outc(c & 0xFF); // Convert to unsigned.
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.Out#outc(int)
     */
    public void outc(int c)
    {
        int ch;

        try
        {

            if (this.encoding == Configuration.MACROMAN)
            {
                if (c < 128)
                {
                    out.write(c);
                }
                else
                {
                    int i;

                    for (i = 128; i < 256; i++)
                    {
                        if (EncodingUtils.decodeMacRoman(i - 128) == c)
                        {
                            out.write(i);
                            break;
                        }
                    }
                }
            }
            else

            if (this.encoding == Configuration.WIN1252)
            {
                if (c < 128 || (c > 159 && c < 256))
                {
                    out.write(c);
                }
                else
                {
                    int i;

                    for (i = 128; i < 160; i++)
                    {
                        if (EncodingUtils.decodeWin1252(i - 128) == c)
                        {
                            out.write(i);
                            break;
                        }
                    }
                }
            }
            else if (this.encoding == Configuration.UTF8)
            {
                int[] count = new int[]{0};

                EncodingUtils.encodeCharToUTF8Bytes(c, null, this.putBytes, count);
                if (count[0] <= 0)
                {
                    /* ReportEncodingError(in->lexer, INVALID_UTF8 | REPLACED_CHAR, c); */
                    /* replacement char 0xFFFD encoded as UTF-8 */
                    out.write(0xEF);
                    out.write(0xBF);
                    out.write(0xBF);
                }
            }
            else if (this.encoding == Configuration.ISO2022)
            {
                if (c == 0x1b) /* ESC */
                {
                    this.state = EncodingUtils.FSM_ESC;
                }
                else
                {
                    switch (this.state)
                    {
                        case EncodingUtils.FSM_ESC :
                            if (c == '$')
                            {
                                this.state = EncodingUtils.FSM_ESCD;
                            }
                            else if (c == '(')
                            {
                                this.state = EncodingUtils.FSM_ESCP;
                            }
                            else
                            {
                                this.state = EncodingUtils.FSM_ASCII;
                            }
                            break;

                        case EncodingUtils.FSM_ESCD :
                            if (c == '(')
                            {
                                this.state = EncodingUtils.FSM_ESCDP;
                            }
                            else
                            {
                                this.state = EncodingUtils.FSM_NONASCII;
                            }
                            break;

                        case EncodingUtils.FSM_ESCDP :
                            this.state = EncodingUtils.FSM_NONASCII;
                            break;

                        case EncodingUtils.FSM_ESCP :
                            this.state = EncodingUtils.FSM_ASCII;
                            break;

                        case EncodingUtils.FSM_NONASCII :
                            c &= 0x7F;
                            break;

                        default :
                            // should not reach here
                            break;
                    }
                }

                this.out.write(c);
            }
            else if (this.encoding == Configuration.UTF16LE
                || this.encoding == Configuration.UTF16BE
                || this.encoding == Configuration.UTF16)
            {
                int i = 1;
                int numChars = 1;
                int[] theChars = new int[2];

                if (c > EncodingUtils.MAX_UTF16_FROM_UCS4)
                {
                    // invalid UTF-16 value
                    /* ReportEncodingError(in.lexer, INVALID_UTF16 | DISCARDED_CHAR, c); */
                    c = 0;
                    numChars = 0;
                }
                else if (c >= EncodingUtils.UTF16_SURROGATES_BEGIN)
                {
                    // encode surrogate pairs

                    // check for invalid pairs
                    if (((c & 0x0000FFFE) == 0x0000FFFE) || ((c & 0x0000FFFF) == 0x0000FFFF))
                    {
                        /* ReportEncodingError(in.lexer, INVALID_UTF16 | DISCARDED_CHAR, c); */
                        c = 0;
                        numChars = 0;
                    }
                    else
                    {
                        theChars[0] = (c - EncodingUtils.UTF16_SURROGATES_BEGIN)
                            / 0x400
                            + EncodingUtils.UTF16_LOW_SURROGATE_BEGIN;
                        theChars[1] = (c - EncodingUtils.UTF16_SURROGATES_BEGIN)
                            % 0x400
                            + EncodingUtils.UTF16_HIGH_SURROGATE_BEGIN;

                        // output both
                        numChars = 2;
                    }
                }
                else
                {
                    // just put the char out
                    theChars[0] = c;
                }

                for (i = 0; i < numChars; i++)
                {
                    c = theChars[i];

                    if (this.encoding == Configuration.UTF16LE)
                    {
                        ch = c & 0xFF;
                        out.write(ch);
                        ch = (c >> 8) & 0xFF;
                        out.write(ch);
                    }

                    else if (this.encoding == Configuration.UTF16BE || this.encoding == Configuration.UTF16)
                    {
                        ch = (c >> 8) & 0xFF;
                        out.write(ch);
                        ch = c & 0xFF;
                        out.write(ch);
                    }
                }
            }
            // #431953 - start RJ
            else if (this.encoding == Configuration.BIG5 || this.encoding == Configuration.SHIFTJIS)
            {
                if (c < 128)
                {
                    this.out.write(c);
                }
                else
                {
                    ch = (c >> 8) & 0xFF;
                    this.out.write(ch);
                    ch = c & 0xFF;
                    this.out.write(ch);
                }
            }
            // #431953 - end RJ
            else
            {
                this.out.write(c);
            }
        }
        catch (IOException e)
        {
        	log.error("OutImpl.outc: " + e.toString());
        }
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.Out#newline()
     */
    public void newline()
    {
        try
        {
            this.out.write(this.newline);
            this.out.flush();
        }
        catch (IOException e)
        {
        	log.error("OutImpl.newline: " + e.toString());
        }
    }

    /**
     * Setter for <code>out</code>.
     * @param out The out to set.
     */
    public void setOut(OutputStream out)
    {
        this.out = out;
    }

    /**
     * Output a Byte Order Mark.
     */
    public void outBOM()
    {
        if (this.encoding == Configuration.UTF8
            || this.encoding == Configuration.UTF16LE
            || this.encoding == Configuration.UTF16BE
            || this.encoding == Configuration.UTF16)
        {
            outc(EncodingUtils.UNICODE_BOM); // this will take care of encoding the BOM correctly
        }
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.Out#close()
     */
    public void close()
    {
        try
        {
            this.out.flush();
            this.out.close();
        }
        catch (IOException e)
        {
        	log.error("OutImpl.close: " + e.toString());
        }
    }
}