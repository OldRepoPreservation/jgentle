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
 * The Class IllegalPropertyException.
 * 
 * @author LE QUOC CHUNG
 */
public class IllegalPropertyException extends JGentleRuntimeException {
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 8576136435821861551L;

	/**
	 * Instantiates a new illegal property exception.
	 */
	public IllegalPropertyException() {

		super();
	}

	/**
	 * Instantiates a new illegal property exception.
	 * 
	 * @param strEx
	 *            the str ex
	 */
	public IllegalPropertyException(String strEx) {

		super(strEx);
	}

	/**
	 * Instantiates a new illegal property exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public IllegalPropertyException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * Instantiates a new illegal property exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public IllegalPropertyException(Throwable cause) {

		super(cause);
	}
}
