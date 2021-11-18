/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * Inline stack node.
 * <p>
 * Mosaic handles inlines via a separate stack from other elements We duplicate this to recover from inline markup
 * errors such as: &lt;i>italic text &lt;p> more italic text&lt;/b> normal text which for compatibility with Mosaic is
 * mapped to: &lt;i>italic text&lt;/i> &lt;p> &lt;i>more italic text&lt;/i> normal text Note that any inline end tag
 * pop's the effect of the current inline start tag, so that&lt;/b> pop's &lt;i>in the above example.
 * </p>
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public class IStack
{

    /**
     * Next element in the stack.
     */
    protected IStack next;

    /**
     * tag's dictionary definition.
     */
    protected Dict tag;

    /**
     * name (null for text nodes).
     */
    protected String element;

    /**
     * Attributes.
     */
    protected AttVal attributes;

}