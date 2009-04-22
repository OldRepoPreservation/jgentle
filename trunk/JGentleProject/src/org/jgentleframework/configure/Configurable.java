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
package org.jgentleframework.configure;

/**
 * All classes implement this interface must be abstract classes, calling
 * <code>configurable classes</code>. The <code>configurable class</code> must
 * overrides {@link #configure()} method in order to specify configured
 * information and also must not implement other abstract methods. All abstract
 * methods except {@link #configure()} will be generated by JGentle system when
 * corresponding container is created.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Jan 16, 2008
 * @see AbstractConfig
 * @see SystemConfig
 * @see BindingConfig
 */
public interface Configurable extends SystemConfig, BindingConfig {
	/**
	 * Configuration method.
	 */
	public void configure();

	/** The Constant REF_MAPPING. */
	public final static String	REF_MAPPING		= "REF_MAPPING";

	/** The Constant REF_ID. */
	public final static String	REF_ID			= "REF_ID";

	/** The Constant REF_CONSTANT. */
	public final static String	REF_CONSTANT	= "REF_CONSTANT";
}