/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
/**
 * License Agreement.
 *
 *  JBoss RichFaces - Ajax4jsf Component Library
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

package org.richfaces.component.util;

import javax.faces.context.FacesContext;

/**
 * @author Nick Belaevski - nbelaevski@exadel.com
 * created 19.02.2007
 * 
 */
public class ViewUtil {
    
	public static String getResourceURL(String url) {
		if (null == url) return null;		
		return ViewUtil.getResourceURL(url, FacesContext.getCurrentInstance());		
	}
	
	public static String getResourceURL(String url, FacesContext context) {
		if (null == url) return null;		
	    String value = url;
		value = context.getApplication().getViewHandler().getResourceURL(context, value);
		return (context.getExternalContext().encodeResourceURL(value));
	}
}
