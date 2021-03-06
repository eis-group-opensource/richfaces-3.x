/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
/**
 * License Agreement.
 *
 * Rich Faces - Natural Ajax for Java Server Faces (JSF)
 *
 * Copyright (C) 2007 Exadel, Inc.
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

package org.ajax4jsf.resource;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.ajax4jsf.resource.ResourceContext;


/**
 * @author asmirnov@exadel.com (latest modification by $Author: alexsmirnov $)
 * @version $Revision: 1.1.2.1 $ $Date: 2007/01/09 18:57:03 $
 *
 */
public class JpegRenderer extends ImageRenderer {

	/* (non-Javadoc)
	 * @see org.ajax4jsf.resource.ResourceRenderer#getContentType()
	 */
	public String getContentType() {
		// TODO Auto-generated method stub
		return "image/jpeg";
	}

	public void sendImage(ResourceContext context, RenderedImage image) throws IOException {
		OutputStream outputStream = context.getOutputStream();
		try {
			ImageIO.write(image, "jpeg", outputStream);
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			// IE can  unexpected close connection 
		}
	}

	public int getImageType() {
		// TODO Auto-generated method stub
		return BufferedImage.TYPE_INT_RGB;
	}

}
