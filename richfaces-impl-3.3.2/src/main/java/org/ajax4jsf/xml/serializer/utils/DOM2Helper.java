/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.xml.serializer.utils;

import org.w3c.dom.Node;

/**
 * This class provides a DOM level 2 "helper", which provides services currently 
 * not provided be the DOM standard.
 * 
 * This class is a copy of the one in org.apache.xml.utils. 
 * It exists to cut the serializers dependancy on that package.
 * 
 * The differences from the original class are:
 * it doesn't extend DOMHelper, not depricated, 
 * dropped method isNodeAfter(Node node1, Node node2)
 * dropped method parse(InputSource)
 * dropped method supportSAX()
 * dropped method setDocument(doc) 
 * dropped method checkNode(Node)
 * dropped method getDocument()
 * dropped method getElementByID(String id, Document doc)
 * dropped method getParentOfNode(Node node)
 * dropped field Document m_doc;
 * made class non-public
 *   
 * This class is not a public API, it is only public because it is 
 * used in org.ajax4jsf.xml.serializer.
 * 
 * @xsl.usage internal
 */
public final class DOM2Helper
{

  /**
   * Construct an instance.
   */
  public DOM2Helper(){}

  /**
   * Returns the local name of the given node, as defined by the
   * XML Namespaces specification. This is prepared to handle documents
   * built using DOM Level 1 methods by falling back upon explicitly
   * parsing the node name.
   *
   * @param n Node to be examined
   *
   * @return String containing the local name, or null if the node
   * was not assigned a Namespace.
   */
  public String getLocalNameOfNode(Node n)
  {

    String name = n.getLocalName();

    return (null == name) ? getLocalNameOfNodeFallback(n) : name;
  }
  
  /**
   * Returns the local name of the given node. If the node's name begins
   * with a namespace prefix, this is the part after the colon; otherwise
   * it's the full node name.
   * 
   * This method is copied from org.apache.xml.utils.DOMHelper
   *
   * @param n the node to be examined.
   *
   * @return String containing the Local Name
   */
  private String getLocalNameOfNodeFallback(Node n)
  {

    String qname = n.getNodeName();
    int index = qname.indexOf(':');

    return (index < 0) ? qname : qname.substring(index + 1);
  }

  /**
   * Returns the Namespace Name (Namespace URI) for the given node.
   * In a Level 2 DOM, you can ask the node itself. Note, however, that
   * doing so conflicts with our decision in getLocalNameOfNode not
   * to trust the that the DOM was indeed created using the Level 2
   * methods. If Level 1 methods were used, these two functions will
   * disagree with each other.
   * <p>
   * TODO: Reconcile with getLocalNameOfNode.
   *
   * @param n Node to be examined
   *
   * @return String containing the Namespace URI bound to this DOM node
   * at the time the Node was created.
   */
  public String getNamespaceOfNode(Node n)
  {
    return n.getNamespaceURI();
  }

  /** Field m_useDOM2getNamespaceURI is a compile-time flag which
   *  gates some of the parser options used to build a DOM -- but 
   * that code is commented out at this time and nobody else
   * references it, so I've commented this out as well. */
  //private boolean m_useDOM2getNamespaceURI = false;
}
