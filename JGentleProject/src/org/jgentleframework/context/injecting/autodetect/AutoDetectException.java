/*
 * Copyright 2007-2008 the original author or authors.
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
package org.jgentleframework.context.injecting.autodetect;

import org.jgentleframework.core.JGentleRuntimeException;

/**
 * The Class AutoDetectException.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Mar 7, 2008
 */
public class AutoDetectException extends JGentleRuntimeException {
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 6238845193254998635L;

	/**
	 * Instantiates a new auto detect exception.
	 */
	public AutoDetectException() {

		super();
	}

	/**
	 * Instantiates a new auto detect exception.
	 * 
	 * @param strEx
	 *            the str ex
	 */
	public AutoDetectException(String strEx) {

		super(strEx);
	}

	/**
	 * Instantiates a new auto detect exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public AutoDetectException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * Instantiates a new auto detect exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public AutoDetectException(Throwable cause) {

		super(cause);
	}
}