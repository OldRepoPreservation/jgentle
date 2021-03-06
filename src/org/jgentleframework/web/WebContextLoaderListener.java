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
package org.jgentleframework.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jgentleframework.configure.Configurable;
import org.jgentleframework.context.JGentle;
import org.jgentleframework.context.injecting.Provider;
import org.jgentleframework.core.JGentleRuntimeException;
import org.jgentleframework.utils.Assertor;

/**
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Dec 10, 2007
 */
public class WebContextLoaderListener implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		String configClassName = servletContext
				.getInitParameter(WebUtils.WEB_INIT_PARAM);
		Assertor.notNull(configClassName);
		Class<Configurable> configClasses = null;
		try {
			configClasses = (Class<Configurable>) Class
					.forName(configClassName);
			Provider provider = null;
			provider = JGentle.buildProvider(configClasses);
			servletContext.setAttribute(WebUtils.WEB_PROVIDER, provider);
		} catch (ClassNotFoundException e) {
			throw new JGentleRuntimeException(e);
		}

	}
}
