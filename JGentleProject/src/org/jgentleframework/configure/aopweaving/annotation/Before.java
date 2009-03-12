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
package org.jgentleframework.configure.aopweaving.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Before advice
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Oct 27, 2007
 * @see Around
 * @see After
 * @see Throws
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Before {
	/**
	 * Specifies the advice instance
	 */
	String[] value();

	/**
	 * Specifies that the specified advice instance must not be
	 * <code>null</code>.
	 * <p>
	 * The default is <b>true</b>;
	 */
	boolean required() default true;

	/**
	 * if specifies <b>true</b>, the advice interceptor will be intercepted at
	 * <code>invocation time</code>, otherwise, if specficies <b>false</b>,
	 * the advice interceptor will be only intercepted at
	 * <code>instantiation time</code>.
	 * <p>
	 * The default value is <b>false</b>.
	 */
	boolean invocation() default false;

	/**
	 * If specifies <b>true</b>, all the specified advices will be invoked at
	 * the same time, otherwise, if specifies <b>false</b>, the specified
	 * advices will be invoked serially.
	 * <p>
	 * The default is <b>false</b>;
	 */
	boolean parallel() default false;
}
