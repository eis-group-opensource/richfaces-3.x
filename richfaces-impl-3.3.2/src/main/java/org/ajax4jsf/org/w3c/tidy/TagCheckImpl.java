/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.org.w3c.tidy;

/**
 * Check HTML attributes implementation.
 * @author Dave Raggett <a href="mailto:dsr@w3.org">dsr@w3.org </a>
 * @author Andy Quick <a href="mailto:ac.quick@sympatico.ca">ac.quick@sympatico.ca </a> (translation to Java)
 * @author Fabrizio Giustina
 * @version $Revision: 1.1.2.1 $ ($Author: alexsmirnov $)
 */
public final class TagCheckImpl
{

    /**
     * CheckHTML instance.
     */
    public static final TagCheck HTML = new CheckHTML();

    /**
     * CheckSCRIPT instance.
     */
    public static final TagCheck SCRIPT = new CheckSCRIPT();

    /**
     * CheckTABLE instance.
     */
    public static final TagCheck TABLE = new CheckTABLE();

    /**
     * CheckCaption instance.
     */
    public static final TagCheck CAPTION = new CheckCaption();

    /**
     * CheckIMG instance.
     */
    public static final TagCheck IMG = new CheckIMG();

    /**
     * CheckAREA instance.
     */
    public static final TagCheck AREA = new CheckAREA();

    /**
     * CheckAnchor instance.
     */
    public static final TagCheck ANCHOR = new CheckAnchor();

    /**
     * CheckMap instance.
     */
    public static final TagCheck MAP = new CheckMap();

    /**
     * CheckSTYLE instance.
     */
    public static final TagCheck STYLE = new CheckSTYLE();

    /**
     * CheckTableCell instance.
     */
    public static final TagCheck TABLECELL = new CheckTableCell();

    /**
     * CheckLINK instance.
     */
    public static final TagCheck LINK = new CheckLINK();

    /**
     * CheckHR instance.
     */
    public static final TagCheck HR = new CheckHR();

    /**
     * CheckForm instance.
     */
    public static final TagCheck FORM = new CheckForm();

    /**
     * CheckMeta instance.
     */
    public static final TagCheck META = new CheckMeta();

    /**
     * don't instantiate.
     */
    private TagCheckImpl()
    {
        // unused
    }

    /**
     * Checker implementation for html tag.
     */
    public static class CheckHTML implements TagCheck
    {

        /**
         * xhtml namepace String.
         */
        private static final String XHTML_NAMESPACE = "http://www.w3.org/1999/xhtml";

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {

            AttVal attval;
            AttVal xmlns;

            xmlns = node.getAttrByName("xmlns");

            if (xmlns != null && XHTML_NAMESPACE.equals(xmlns.value))
            {
                lexer.isvoyager = true;
                if (!lexer.configuration.htmlOut) // Unless user has specified plain HTML output,
                {
                    lexer.configuration.xHTML = true; // output format will be XHTML.
                }
                // adjust other config options, just as in Configuration
                lexer.configuration.xmlOut = true;
                lexer.configuration.upperCaseTags = false;
                lexer.configuration.upperCaseAttrs = false;
            }

            for (attval = node.attributes; attval != null; attval = attval.next)
            {
                attval.checkAttribute(lexer, node);
            }
        }

    }

    /**
     * Checker implementation for script tags.
     */
    public static class CheckSCRIPT implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            AttVal lang, type;

            node.checkAttributes(lexer);

            lang = node.getAttrByName("language");
            type = node.getAttrByName("type");

            if (type == null)
            {
                AttVal missingType = new AttVal(null, null, '"', "type", "");
                lexer.report.attrError(lexer, node, missingType, Report.MISSING_ATTRIBUTE);

                // check for javascript
                if (lang != null)
                {
                    String str = lang.value;
                    if ("javascript".equalsIgnoreCase(str) || "jscript".equalsIgnoreCase(str))
                    {
                        node.addAttribute("type", "text/javascript");
                    }
                    else if ("vbscript".equalsIgnoreCase(str))
                    {
                        // per Randy Waki 8/6/01
                        node.addAttribute("type", "text/vbscript");
                    }
                }
                else
                {
                    node.addAttribute("type", "text/javascript");
                }
            }
        }

    }

    /**
     * Checker implementation for table.
     */
    public static class CheckTABLE implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            AttVal attval;
            Attribute attribute;
            boolean hasSummary = false;

            for (attval = node.attributes; attval != null; attval = attval.next)
            {
                attribute = attval.checkAttribute(lexer, node);

                if (attribute == AttributeTable.attrSummary)
                {
                    hasSummary = true;
                }
            }

            /* suppress warning for missing summary for HTML 2.0 and HTML 3.2 */
            if (!hasSummary && lexer.doctype != Dict.VERS_HTML20 && lexer.doctype != Dict.VERS_HTML32)
            {
                lexer.badAccess |= Report.MISSING_SUMMARY;

                // summary is not required, should be only an accessibility warning
                // AttVal missingSummary = new AttVal(null, null, '"', "summary", "");
                // lexer.report.attrError(lexer, node, missingSummary, Report.MISSING_ATTRIBUTE);
            }

            /* convert <table border> to <table border="1"> */
            if (lexer.configuration.xmlOut)
            {
                attval = node.getAttrByName("border");
                if (attval != null)
                {
                    if (attval.value == null)
                    {
                        attval.value = "1";
                    }
                }
            }

            /* <table height="..."> is proprietary */
            if ((attval = node.getAttrByName("height")) != null)
            {
                lexer.report.attrError(lexer, node, attval, Report.PROPRIETARY_ATTRIBUTE);
                lexer.versions &= Dict.VERS_PROPRIETARY;
            }

        }

    }

    /**
     * Checker implementation for table caption.
     */
    public static class CheckCaption implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            AttVal attval;
            String value = null;

            node.checkAttributes(lexer);

            for (attval = node.attributes; attval != null; attval = attval.next)
            {
                if ("align".equalsIgnoreCase(attval.attribute))
                {
                    value = attval.value;
                    break;
                }
            }

            if (value != null)
            {
                if ("left".equalsIgnoreCase(value) || "right".equalsIgnoreCase(value))
                {
                    lexer.constrainVersion(Dict.VERS_HTML40_LOOSE);
                }
                else if ("top".equalsIgnoreCase(value) || "bottom".equalsIgnoreCase(value))
                {
                    lexer.constrainVersion(~(Dict.VERS_HTML20 | Dict.VERS_HTML32));
                }
                else
                {
                    lexer.report.attrError(lexer, node, attval, Report.BAD_ATTRIBUTE_VALUE);
                }
            }
        }

    }

    /**
     * Checker implementation for hr.
     */
    public static class CheckHR implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            AttVal av = node.getAttrByName("src");

            node.checkAttributes(lexer);

            if (av != null)
            {
                lexer.report.attrError(lexer, node, av, Report.PROPRIETARY_ATTR_VALUE);
            }
        }
    }

    /**
     * Checker implementation for image tags.
     */
    public static class CheckIMG implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            AttVal attval;
            Attribute attribute;
            boolean hasAlt = false;
            boolean hasSrc = false;
            boolean hasUseMap = false;
            boolean hasIsMap = false;
            boolean hasDataFld = false;

            for (attval = node.attributes; attval != null; attval = attval.next)
            {
                attribute = attval.checkAttribute(lexer, node);

                if (attribute == AttributeTable.attrAlt)
                {
                    hasAlt = true;
                }
                else if (attribute == AttributeTable.attrSrc)
                {
                    hasSrc = true;
                }
                else if (attribute == AttributeTable.attrUsemap)
                {
                    hasUseMap = true;
                }
                else if (attribute == AttributeTable.attrIsmap)
                {
                    hasIsMap = true;
                }
                else if (attribute == AttributeTable.attrDatafld)
                {
                    hasDataFld = true;
                }
                else if (attribute == AttributeTable.attrWidth || attribute == AttributeTable.attrHeight)
                {
                    lexer.constrainVersion(~Dict.VERS_HTML20);
                }
            }

            if (!hasAlt)
            {
                lexer.badAccess |= Report.MISSING_IMAGE_ALT;
                AttVal missingAlt = new AttVal(null, null, '"', "alt", "");
                lexer.report.attrError(lexer, node, missingAlt, Report.MISSING_ATTRIBUTE);
                if (lexer.configuration.altText != null)
                {
                    node.addAttribute("alt", lexer.configuration.altText);
                }
            }

            if (!hasSrc && !hasDataFld)
            {
                AttVal missingSrc = new AttVal(null, null, '"', "src", "");
                lexer.report.attrError(lexer, node, missingSrc, Report.MISSING_ATTRIBUTE);
            }

            if (hasIsMap && !hasUseMap)
            {
                AttVal missingIsMap = new AttVal(null, null, '"', "ismap", "");
                lexer.report.attrError(lexer, node, missingIsMap, Report.MISSING_IMAGEMAP);
            }
        }

    }

    /**
     * Checker implementation for area.
     */
    public static class CheckAREA implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            AttVal attval;
            Attribute attribute;
            boolean hasAlt = false;
            boolean hasHref = false;

            for (attval = node.attributes; attval != null; attval = attval.next)
            {
                attribute = attval.checkAttribute(lexer, node);

                if (attribute == AttributeTable.attrAlt)
                {
                    hasAlt = true;
                }
                else if (attribute == AttributeTable.attrHref)
                {
                    hasHref = true;
                }
            }

            if (!hasAlt)
            {
                lexer.badAccess |= Report.MISSING_LINK_ALT;
                AttVal missingAlt = new AttVal(null, null, '"', "alt", "");
                lexer.report.attrError(lexer, node, missingAlt, Report.MISSING_ATTRIBUTE);
            }
            if (!hasHref)
            {
                AttVal missingHref = new AttVal(null, null, '"', "href", "");
                lexer.report.attrError(lexer, node, missingHref, Report.MISSING_ATTRIBUTE);
            }
        }

    }

    /**
     * Checker implementation for anchors.
     */
    public static class CheckAnchor implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            node.checkAttributes(lexer);

            lexer.fixId(node);
        }
    }

    /**
     * Checker implementation for image maps.
     */
    public static class CheckMap implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            node.checkAttributes(lexer);

            lexer.fixId(node);
        }
    }

    /**
     * Checker implementation for style tags.
     */
    public static class CheckSTYLE implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            AttVal type = node.getAttrByName("type");

            node.checkAttributes(lexer);

            if (type == null)
            {
                AttVal missingType = new AttVal(null, null, '"', "type", "");
                lexer.report.attrError(lexer, node, missingType, Report.MISSING_ATTRIBUTE);

                node.addAttribute("type", "text/css");
            }
        }
    }

    /**
     * Checker implementation for forms. Reports missing action attribute.
     */
    public static class CheckForm implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            AttVal action = node.getAttrByName("action");

            node.checkAttributes(lexer);

            if (action == null)
            {
                AttVal missingAttribute = new AttVal(null, null, '"', "action", "");
                lexer.report.attrError(lexer, node, missingAttribute, Report.MISSING_ATTRIBUTE);
            }
        }
    }

    /**
     * Checker implementation for meta tags. Reports missing content attribute.
     */
    public static class CheckMeta implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            AttVal content = node.getAttrByName("content");

            node.checkAttributes(lexer);

            if (content == null)
            {
                AttVal missingAttribute = new AttVal(null, null, '"', "content", "");
                lexer.report.attrError(lexer, node, missingAttribute, Report.MISSING_ATTRIBUTE);
            }

            // name or http-equiv attribute must also be set
        }
    }

    /**
     * Checker implementation for table cells.
     */
    public static class CheckTableCell implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            node.checkAttributes(lexer);

            // HTML4 strict doesn't allow mixed content for elements with %block; as their content model

            if (node.getAttrByName("width") != null || node.getAttrByName("height") != null)
            {
                lexer.constrainVersion(~Dict.VERS_HTML40_STRICT);
            }
        }
    }

    /**
     * add missing type attribute when appropriate.
     */
    public static class CheckLINK implements TagCheck
    {

        /**
         * @see org.ajax4jsf.org.w3c.tidy.TagCheck#check(org.ajax4jsf.org.w3c.tidy.Lexer, org.ajax4jsf.org.w3c.tidy.Node)
         */
        public void check(Lexer lexer, Node node)
        {
            AttVal rel = node.getAttrByName("rel");

            node.checkAttributes(lexer);

            if (rel != null && rel.value != null && rel.value.equals("stylesheet"))
            {
                AttVal type = node.getAttrByName("type");

                if (type == null)
                {
                    AttVal missingType = new AttVal(null, null, '"', "type", "");
                    lexer.report.attrError(lexer, node, missingType, Report.MISSING_ATTRIBUTE);

                    node.addAttribute("type", "text/css");
                }
            }
        }
    }

}