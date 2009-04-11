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
package RMI;

import java.io.Serializable;

import org.jgentleframework.context.JGentle;
import org.jgentleframework.context.ServiceProvider;

/**
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Mar 12, 2008
 */
public class Client {
	public static void main(String[] args) {

		ServiceProvider context = JGentle.buildServiceProvider(Config.class);
		RemoteInterface RMIObj = (RemoteInterface) context
				.getBean(RemoteInterface.class);
		System.out.println(RMIObj.helloWorld());
		ReferObject refer = new ReferObject("Not NULL");
		System.out.println(refer.getName());
		System.out.println(RMIObj.process(refer).getName());
	}
}

class ReferObject implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5890441747373065164L;

	public ReferObject(String name) {

		this.name = name;
	}

	private String	name	= "";

	/**
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {

		this.name = name;
	}
}
