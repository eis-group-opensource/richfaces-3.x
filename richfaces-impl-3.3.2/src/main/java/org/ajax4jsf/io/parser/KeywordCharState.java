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

package org.ajax4jsf.io.parser;

import java.io.IOException;

/**
 * @author shura
 *
 */
public class KeywordCharState extends SingleCharState {

	public KeywordCharState(char c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

    boolean isAcceptChar(char c, ParsingContext context) {
		// TODO Auto-generated method stub
		return Character.toLowerCase(c) == _c;
	}
 
    /* (non-Javadoc)
	 * @see org.ajax4jsf.io.parser.ParserState#send(char, org.ajax4jsf.io.parser.ParsingContext)
	 */
	void send(char c, ParsingContext context) throws IOException {
		
		context.putChar(c);
	}


}
