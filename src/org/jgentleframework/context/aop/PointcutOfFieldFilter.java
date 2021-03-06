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

import org.jgentleframework.context.aop.support.Matching;

/**
 * Represents the {@link FieldFilter} pointcut.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Aug 17, 2008
 * @see Pointcut
 * @see PointcutOfClassFilter
 * @see PointcutOfConstructorFilter
 * @see PointcutOfMethodFilter
 * @see PointcutOfParameterFilter
 */
public interface PointcutOfFieldFilter<T extends Matching> extends Pointcut<T> {
	/**
	 * Returns the {@link FieldFilter} for this pointcut
	 * 
	 * @return the {@link FieldFilter} (never <code>null</code>)
	 */
	FieldFilter getFieldFilter();
}
