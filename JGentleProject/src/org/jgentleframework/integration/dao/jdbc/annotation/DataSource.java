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
package org.jgentleframework.integration.dao.jdbc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Apr 15, 2008
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataSource {
	/**
	 * Object Class chỉ định class của đối tượng DataSource.
	 * 
	 * @return Class
	 */
	Class<?> dataSourceClass();

	/**
	 * Object Class chỉ định driver class.
	 * 
	 * @return Class
	 */
	Class<?> driverClassName();

	/**
	 * Địa chỉ URL
	 * 
	 * @return String
	 */
	String url();

	/**
	 * User name chỉ định
	 * 
	 * @return String
	 */
	String username();

	/**
	 * Password chỉ định
	 * 
	 * @return String
	 */
	String password();
}
