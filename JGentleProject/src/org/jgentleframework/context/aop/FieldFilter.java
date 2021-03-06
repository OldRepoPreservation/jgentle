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
package org.jgentleframework.context.aop;

import org.jgentleframework.context.aop.support.FieldMatching;
import org.jgentleframework.core.intercept.support.Matcher;

/**
 * The Interface FieldFilter.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Aug 15, 2008
 */
public interface FieldFilter extends Matcher<FieldMatching>,
		RuntimeSupportFilter<FieldMatching> {
	/**
	 * Perform static or dynamic checking whether the given
	 * {@link FieldMatching} matches.If the {@link #isRuntime()} method returns
	 * <b>true</b>, a runtime check on this method call) will be made.
	 * 
	 * @param matching
	 *            the candidate matching
	 * @return whether or not this {@link FieldMatching} matches.
	 */
	boolean matches(FieldMatching matching);
}
