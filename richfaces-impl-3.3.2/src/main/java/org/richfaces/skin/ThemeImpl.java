/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
/**
 * 
 */
package org.richfaces.skin;

import java.util.Properties;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

/**
 * @author asmirnov
 *
 */
public class ThemeImpl implements Theme {
	
	private final Properties themeProperties;

	/**
	 * @param themeProperties
	 */
	public ThemeImpl(Properties themeProperties) {
		this.themeProperties = themeProperties;
	}

	/* (non-Javadoc)
	 * @see org.richfaces.skin.Theme#getProperty(java.lang.String)
	 */
	public Object getProperty(String name) {
		Object property = themeProperties.get(name);
		if (property instanceof ValueExpression) {
			ValueExpression ve = (ValueExpression) property;
			property = ve.getValue(FacesContext.getCurrentInstance().getELContext());
		}
		return property;
	}

	/* (non-Javadoc)
	 * @see org.richfaces.skin.Theme#getRendererType()
	 */
	public String getRendererType() {
		return (String) getProperty("rendererType");
	}

	/* (non-Javadoc)
	 * @see org.richfaces.skin.Theme#getScript()
	 */
	public String getScript() {
		return (String) getProperty("script");
	}

	/* (non-Javadoc)
	 * @see org.richfaces.skin.Theme#getStyle()
	 */
	public String getStyle() {
		return (String) getProperty("styleSheet");
	}

}
