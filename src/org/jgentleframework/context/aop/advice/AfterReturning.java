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
package org.jgentleframework.context.aop.advice;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInvocation;

/**
 * After returning advice is invoked only on normal method return, not if an
 * exception is thrown. Such advice can see the return value, but cannot change
 * it.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Aug 26, 2008
 * @see Advice
 * @see BeforeAdvice
 * @see MethodBeforeAdvice
 */
public interface AfterReturning extends Advice {
	/**
	 * Callback after a given method successfully returned.
	 * 
	 * @param returnValue
	 *            the value returned by the method, if any
	 * @param invocation
	 *            the method invocation joinpoint
	 * @throws Throwable
	 *             if this object wishes to abort the call. Any exception thrown
	 *             will be returned to the caller if it's allowed by the method
	 *             signature. Otherwise the exception will be wrapped as a
	 *             runtime exception.
	 */
	void afterReturning(Object returnValue, MethodInvocation invocation)
			throws Throwable;
}
