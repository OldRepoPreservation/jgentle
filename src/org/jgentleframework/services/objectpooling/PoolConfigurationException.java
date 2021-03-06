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
package org.jgentleframework.services.objectpooling;

import org.jgentleframework.services.ServiceRunningException;

/**
 * The Class PoolConfigurationException.
 * 
 * @author Quoc Chung - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Aug 13, 2009
 */
public class PoolConfigurationException extends ServiceRunningException {
	/** The Constant serialVersionUID. */
	private static final long	serialVersionUID	= -459495380061787592L;

	/**
	 * Instantiates a new pool configuration exception.
	 */
	public PoolConfigurationException() {

		super();
	}

	/**
	 * The Constructor.
	 * 
	 * @param strEx
	 *            the str ex
	 */
	public PoolConfigurationException(String strEx) {

		super(strEx);
	}

	/**
	 * The Constructor.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public PoolConfigurationException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * The Constructor.
	 * 
	 * @param cause
	 *            the cause
	 */
	public PoolConfigurationException(Throwable cause) {

		super(cause);
	}
}
