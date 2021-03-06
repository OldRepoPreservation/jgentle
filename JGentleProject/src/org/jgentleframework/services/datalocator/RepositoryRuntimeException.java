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
package org.jgentleframework.services.datalocator;

import org.jgentleframework.services.ServiceRunningException;

/**
 * The Class RepositoryRuntimeException.
 * 
 * @author LE QUOC CHUNG
 * @date Aug 31, 2007
 */
public class RepositoryRuntimeException extends ServiceRunningException {
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= 6466813120146858129L;

	/**
	 * Instantiates a new repository runtime exception.
	 */
	public RepositoryRuntimeException() {

		super();
	}

	/**
	 * Instantiates a new repository runtime exception.
	 * 
	 * @param strEx
	 *            the str ex
	 */
	public RepositoryRuntimeException(String strEx) {

		super(strEx);
	}

	/**
	 * Instantiates a new repository runtime exception.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public RepositoryRuntimeException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * Instantiates a new repository runtime exception.
	 * 
	 * @param cause
	 *            the cause
	 */
	public RepositoryRuntimeException(Throwable cause) {

		super(cause);
	}
}
