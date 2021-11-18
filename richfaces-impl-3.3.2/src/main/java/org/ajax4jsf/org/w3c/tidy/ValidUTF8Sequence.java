/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * @author Fabrizio Giustina (translation from c)
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class ValidUTF8Sequence
{

    /**
     * low char.
     */
    int lowChar;

    /**
     * high char.
     */
    int highChar;

    /**
     * number of bytes.
     */
    int numBytes;

    /**
     * array of valid bytes.
     */
    char[] validBytes = new char[8];

    /**
     * Instantiates a new ValidUTF8Sequence.
     * @param lowChar low utf8 char
     * @param highChar high utf8 char
     * @param numBytes number of bytes in the sequence
     * @param validBytes valid bytes array
     */
    public ValidUTF8Sequence(int lowChar, int highChar, int numBytes, char[] validBytes)
    {
        this.lowChar = lowChar;
        this.highChar = highChar;
        this.numBytes = numBytes;
        this.validBytes = validBytes;
    }

}
