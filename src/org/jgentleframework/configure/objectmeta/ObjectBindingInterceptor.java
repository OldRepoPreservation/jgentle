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
package org.jgentleframework.configure.objectmeta;

import org.jgentleframework.core.intercept.support.Matcher;
import org.jgentleframework.reflection.metadata.Definition;

/**
 * The Interface ObjectBindingInterceptor.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Aug 14, 2008
 */
public interface ObjectBindingInterceptor {
	/**
	 * Gets the matchers.
	 * 
	 * @return the matcher
	 */
	public Matcher<Definition>[] getMatchers();

	/**
	 * Sets the matcher.
	 * 
	 * @param matchers
	 *            the list of matchers to set
	 */
	public void setMatchers(Matcher<Definition>[] matchers);

	/**
	 * Gets the interceptor.
	 * 
	 * @return the interceptor
	 */
	public Object getInterceptor();

	/**
	 * Sets the interceptor.
	 * 
	 * @param interceptor
	 *            the interceptor to set
	 */
	public void setInterceptor(Object interceptor);
}