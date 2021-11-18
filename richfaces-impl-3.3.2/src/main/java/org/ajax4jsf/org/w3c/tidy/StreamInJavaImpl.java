/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;


/**
 * StreamIn Implementation using java writers.
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class StreamInJavaImpl implements StreamIn
{

    /**
     * number of characters kept in buffer.
     */
    private static final int CHARBUF_SIZE = 10;

    /**
     * character buffer.
     */
    private int[] charbuf = new int[CHARBUF_SIZE];

    /**
     * actual position in buffer.
     */
    private int bufpos;

    /**
     * Java input stream reader.
     */
    private Reader reader;

    /**
     * has end of stream been reached?
     */
    private boolean endOfStream;

    /**
     * Is char pushed?
     */
    private boolean pushed;

    /**
     * current column number.
     */
    private int curcol;

    /**
     * last column.
     */
    private int lastcol;

    /**
     * current line number.
     */
    private int curline;

    /**
     * tab size in chars.
     */
    private int tabsize;

    private int tabs;

    public StreamInJavaImpl(Reader in , int tabsize)
    {
        reader = in;
        this.pushed = false;
        this.tabsize = tabsize;
        this.curline = 1;
        this.curcol = 1;
        this.endOfStream = false;
    }
    /**
     * @param content
     * @param tabsize
     * @throws UnsupportedEncodingException
     */
    public StreamInJavaImpl(String content, int tabsize)
    {
        reader = new StringReader(content);
        this.pushed = false;
        this.tabsize = tabsize;
        this.curline = 1;
        this.curcol = 1;
        this.endOfStream = false;
    }
    /**
     * Instantiates a new StreamInJavaImpl.
     * @param stream
     * @param encoding
     * @param tabsize
     * @throws UnsupportedEncodingException
     */
    public StreamInJavaImpl(InputStream stream, String encoding, int tabsize) throws UnsupportedEncodingException
    {
        reader = new InputStreamReader(stream, encoding);
        this.pushed = false;
        this.tabsize = tabsize;
        this.curline = 1;
        this.curcol = 1;
        this.endOfStream = false;
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.StreamIn#readCharFromStream()
     */
    public int readCharFromStream()
    {
        int c;
        try
        {
///            c = reader.read();
            c = readCharFromStreamBuffer();
            if (c < 0)
            {
                endOfStream = true;
            }

        }
        catch (IOException e)
        {
            // @todo how to handle?
            endOfStream = true;
            return END_OF_STREAM;
        }

        return c;
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.StreamIn#readChar()
     */
    public int readChar()
    {
        int c;

        if (this.pushed)
        {
            c = this.charbuf[--(this.bufpos)];
            if ((this.bufpos) == 0)
            {
                this.pushed = false;
            }

            if (c == '\n')
            {
                this.curcol = 1;
                this.curline++;
                return c;
            }

            this.curcol++;
            return c;
        }

        this.lastcol = this.curcol;

        if (this.tabs > 0)
        {
            this.curcol++;
            this.tabs--;
            return ' ';
        }

        c = readCharFromStream();

        if (c < 0)
        {
            endOfStream = true;
            return END_OF_STREAM;
        }

        if (c == '\n')
        {
            this.curcol = 1;
            this.curline++;
            return c;
        }
        else if (c == '\r') // \r\n
        {
            c = readCharFromStream();
            if (c != '\n')
            {
                if (c != END_OF_STREAM)
                {
                    ungetChar(c);
                }
                c = '\n';
            }
            this.curcol = 1;
            this.curline++;
            return c;
        }

        if (c == '\t')
        {
            this.tabs = this.tabsize - ((this.curcol - 1) % this.tabsize) - 1;
            this.curcol++;
            c = ' ';
            return c;
        }

        this.curcol++;

        return c;
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.StreamIn#ungetChar(int)
     */
    public void ungetChar(int c)
    {
        this.pushed = true;
        if (this.bufpos >= CHARBUF_SIZE)
        {
            // pop last element
            System.arraycopy(this.charbuf, 0, this.charbuf, 1, CHARBUF_SIZE - 1);
            this.bufpos--;
        }
        this.charbuf[(this.bufpos)++] = c;

        if (c == '\n')
        {
            --this.curline;
        }

        this.curcol = this.lastcol;
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.StreamIn#isEndOfStream()
     */
    public boolean isEndOfStream()
    {
        return endOfStream;
    }

    /**
     * Getter for <code>curcol</code>.
     * @return Returns the curcol.
     */
    public int getCurcol()
    {
        return this.curcol;
    }

    /**
     * Getter for <code>curline</code>.
     * @return Returns the curline.
     */
    public int getCurline()
    {
        return this.curline;
    }

    /**
     * @see org.ajax4jsf.org.w3c.tidy.StreamIn#setLexer(org.ajax4jsf.org.w3c.tidy.Lexer)
     */
    public void setLexer(Lexer lexer)
    {
        // unused in the java implementation
    }    
    
    private char[] chars = new char[256];
    private int index = 0;
    private int length = 0;
    
    private int readCharFromStreamBuffer() throws IOException {
    	if(index >= length) {
    		if(length < 0) return -1;
    		length = reader.read(chars);
    		index = 0;
    	}
    	if(index < length) {
    		char c = chars[index];
    		index++;
    		return c;
    	}
    	return -1;
    }    

}