/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * Input Stream.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */

public interface StreamIn
{

    /**
     * end of stream char.
     */
    int END_OF_STREAM = -1;

    /**
     * Getter for <code>curcol</code>.
     * @return Returns the curcol.
     */
    int getCurcol();

    /**
     * Getter for <code>curline</code>.
     * @return Returns the curline.
     */
    int getCurline();

    /**
     * reads a char from the stream.
     * @return char
     */
    int readCharFromStream();

    /**
     * Read a char.
     * @return char
     */
    int readChar();

    /**
     * Unget a char.
     * @param c char
     */
    void ungetChar(int c);

    /**
     * Has end of stream been reached?
     * @return <code>true</code> if end of stream has been reached
     */
    boolean isEndOfStream();

    /**
     * Setter for lexer instance (needed for error reporting).
     * @param lexer Lexer
     */
    void setLexer(Lexer lexer);

}