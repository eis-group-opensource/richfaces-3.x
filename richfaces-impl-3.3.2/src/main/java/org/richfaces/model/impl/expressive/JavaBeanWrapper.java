/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
/**
 * License Agreement.
 *
 *  JBoss RichFaces 3.0 - Ajax4jsf Component Library
 *
 * Copyright (C) 2007  Exadel, Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1 as published by the Free Software Foundation.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301  USA
 */

package org.richfaces.model.impl.expressive;

import java.util.Map;
/**
 * Object used in sorting - contains of base object, and its properties evaluated with EL
 * @author Maksim Kaszynski
 *
 */
public class JavaBeanWrapper {
	private Object wrappedObject;
	private Map<Object, Object> properties;
	
	public JavaBeanWrapper(Object o, Map<Object, Object> props) {
		wrappedObject = o;
		properties = props;
	}
	
	public Object getProperty(Object expression) {
		return properties.get(expression);
	}
	
	public Map<Object, Object> getProperties() {
		return properties;
	}

	public Object getWrappedObject() {
		return wrappedObject;
	}
}