/*
 * Copyright 2007-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 * 
 * Project: JGentleFramework
 */
package org.jgentleframework.core;

/**
 * The Class GenericException.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Aug 30, 2008
 * @See {@link JGentleException}
 */
public class GenericException extends JGentleException {
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -7741038253141262844L;

	/**
	 * Instantiates a new generic exception.
	 */
	public GenericException() {

		super();
	}

	/**
	 * Instantiates a new generic exception.
	 * 
	 * @param strEx
	 *            the str ex
	 */
	public GenericException(String strEx) {

		super(strEx);
	}

	/**
	 * Instantiates a new generic exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public GenericException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * Instantiates a new generic exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public GenericException(Throwable cause) {

		super(cause);
	}
}
