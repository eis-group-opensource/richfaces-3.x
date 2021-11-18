/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.ajax4jsf.xml.serializer;

import javax.xml.transform.Transformer;

import org.w3c.dom.Node;
/**
 * This interface is meant to be used by a base interface to
 * TransformState, but which as only the setters which have non Xalan
 * specific types in their signature, so that there are no dependancies
 * of the serializer on Xalan.
 * 
 * This interface is not a public API, it is only public because it is
 * used by Xalan.
 * 
 * @see org.apache.xalan.transformer.TransformState
 * @xsl.usage internal
 */
public interface TransformStateSetter
{


  /**
   * Set the current node.
   *
   * @param n The current node.
   */
  void setCurrentNode(Node n);

  /**
   * Reset the state on the given transformer object.
   *
   * @param transformer
   */
  void resetState(Transformer transformer);

}
