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
package org.jgentleframework.core.intercept.support;

import org.jgentleframework.reflection.metadata.Definition;

/**
 * The Class AbstractDefinitionMatcher.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Aug 16, 2008
 */
public abstract class AbstractDefinitionMatcher extends
		AbstractMatcher<Definition> {
	/*
	 * (non-Javadoc)
	 * @see
	 * org.jgentleframework.core.intercept.AbstractMatcher#and(org.jgentleframework
	 * .core.intercept.Matcher)
	 */
	@Override
	public Matcher<Definition> and(final Matcher<Definition> other) {

		return super.and(other);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.jgentleframework.core.intercept.AbstractMatcher#or(org.jgentleframework
	 * .core.intercept.Matcher)
	 */
	public Matcher<Definition> or(Matcher<Definition> other) {

		return super.or(other);
	}
}
