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
 * The Class AmbiguousException.
 * 
 * @author Quoc Chung - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Jul 30, 2007
 */
public class AmbiguousException extends JGentleRuntimeException {
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -6723998709026784041L;

	/**
	 * Instantiates a new ambiguous exception.
	 */
	public AmbiguousException() {

		super();
	}

	/**
	 * Instantiates a new ambiguous exception.
	 * 
	 * @param strEx
	 *            the str ex
	 */
	public AmbiguousException(String strEx) {

		super(strEx);
	}

	/**
	 * Instantiates a new ambiguous exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public AmbiguousException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * Instantiates a new ambiguous exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public AmbiguousException(Throwable cause) {

		super(cause);
	}
}
