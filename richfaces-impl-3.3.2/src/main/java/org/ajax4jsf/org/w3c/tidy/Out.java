/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

import java.io.IOException;

/**
 * Tidy Output interface.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public interface Out
{

    /**
     * writes an char.
     * @param c char to write
     */
    void outc(int c) throws IOException;

    /**
     * writes a byte.
     * @param c byte to write
     */
    void outc(byte c) throws IOException;

    /**
     * writes a newline.
     */
    void newline() throws IOException;

    /**
     * Flush and close the stream.
     */
    void close() throws IOException;

}