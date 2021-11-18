/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * Interface for configuration property parser.
 * @author Fabrizio Giustina
 * @version $Revision $ ($Author $)
 */
public interface ParseProperty
{

    /**
     * Parse a configuration option.
     * @param value option value
     * @param option option name
     * @param configuration actual configuration instance
     * @return parsed configuration value
     */
    Object parse(String value, String option, Configuration configuration);

    /**
     * Returns the option type.
     * @return option type
     */
    String getType();

    /**
     * Returns the valid values.
     * @return valid values (text)
     */
    String getOptionValues();

    /**
     * Returns the "friendly name" for the passed value. Needed to print actual configuration setting.
     * @param option option name
     * @param value actual value
     * @param configuration actual configuration
     * @return "friendly" actual value
     */
    String getFriendlyName(String option, Object value, Configuration configuration);

}