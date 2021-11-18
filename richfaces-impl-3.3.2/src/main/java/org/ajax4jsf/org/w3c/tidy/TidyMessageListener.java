/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * Listener interface for validation errors/warnings and info.
 * @see Tidy#setMessageListener(TidyMessageListener)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public interface TidyMessageListener
{

    /**
     * Called by tidy when a warning or error occurs.
     * @param message Tidy message
     */
    void messageReceived(TidyMessage message);

}
