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
package org.jgentleframework.core.intercept;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

/**
 * The Class AbstractInterceptedAdviceInvocation.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Aug 26, 2008
 * @see MethodInvocation
 */
public abstract class AbstractInterceptedAdviceInvocation implements
		MethodInvocation {
	/** The invocation. */
	protected MethodInvocation	invocation;

	/**
	 * Instantiates a new abstract intercepted advice invocation.
	 * 
	 * @param invocation
	 *            the invocation
	 */
	public AbstractInterceptedAdviceInvocation(MethodInvocation invocation) {

		this.invocation = invocation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.aopalliance.intercept.MethodInvocation#getMethod()
	 */
	public Method getMethod() {

		return invocation.getMethod();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.aopalliance.intercept.Invocation#getArguments()
	 */
	@Override
	public Object[] getArguments() {

		return invocation.getArguments();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.aopalliance.intercept.Joinpoint#getStaticPart()
	 */
	@Override
	public AccessibleObject getStaticPart() {

		return invocation.getStaticPart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.aopalliance.intercept.Joinpoint#getThis()
	 */
	@Override
	public Object getThis() {

		return invocation.getThis();
	}
}
