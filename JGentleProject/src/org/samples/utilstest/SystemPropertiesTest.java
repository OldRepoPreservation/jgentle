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
 * Project: JGentleFrameworkExample
 */
package org.samples.utilstest;

import org.jgentleframework.utils.SystemPropertiesUtils;
import org.jgentleframework.utils.enums.SystemPropertyKeys;

/**
 * @author Quoc Chung - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date May 26, 2009
 */
public class SystemPropertiesTest {
	public static void main(String[] args) {

		for (SystemPropertyKeys key : SystemPropertyKeys.values()) {
			System.out.println(key + "::"
					+ SystemPropertiesUtils.getProperty(key));
		}
	}
}
