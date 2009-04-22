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
package org.jgentleframework.core.intercept;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;

import org.aopalliance.intercept.Interceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jgentleframework.configure.enums.MetadataKey;
import org.jgentleframework.context.injecting.Provider;
import org.jgentleframework.context.support.InstantiationSelector;
import org.jgentleframework.core.reflection.metadata.Definition;
import org.jgentleframework.core.reflection.metadata.MetadataController;
import org.jgentleframework.core.reflection.metadata.MetadataImpl;
import org.jgentleframework.utils.Assertor;

/**
 * The Class InstantiationInterceptorStackCallback.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Aug 20, 2008
 * @see InstantiationInterceptor
 * @see Interceptor
 */
public class InstantiationInterceptorStackCallback {
	/** The interceptors. */
	final InstantiationInterceptor[]	interceptors;

	/** The arg types. */
	final Class<?>[]					argTypes;

	/** The args. */
	final Object[]						args;

	/** The requestor. */
	final Object						requestor;

	/** The target class. */
	final Class<?>						targetClass;

	/** The target interface. */
	final Class<?>[]					targetInterface;

	/** The definition. */
	final Definition					definition;

	/** The previous result. */
	Object								previousResult	= null;

	/** The service handler. */
	final Provider						provider;

	/**
	 * Instantiates a new instantiation interceptor stack callback.
	 * 
	 * @param argTypes
	 *            the arg types
	 * @param args
	 *            the args
	 * @param requestor
	 *            the requestor
	 * @param targetClass
	 *            the target class
	 * @param targetInterface
	 *            the target interface
	 * @param definition
	 *            the definition
	 * @param provider
	 *            the {@link Provider}
	 * @param interceptors
	 *            the interceptors
	 */
	public InstantiationInterceptorStackCallback(Class<?>[] argTypes,
			Object[] args, Object requestor, Class<?> targetClass,
			Class<?>[] targetInterface, Definition definition,
			Provider provider, InstantiationInterceptor... interceptors) {

		Assertor.notNull(interceptors,
				"The list of interceptors must not be null!");
		Assertor.notNull(provider, "The service handler must not be null!");
		Assertor.notEmpty(interceptors,
				"The list of interceptors must not be empty !");
		this.argTypes = argTypes != null ? argTypes.clone() : null;
		this.args = args != null ? args.clone() : null;
		this.requestor = requestor;
		this.targetClass = targetClass;
		this.targetInterface = targetInterface != null ? targetInterface
				.clone() : null;
		this.interceptors = interceptors != null ? interceptors.clone()
				: interceptors;
		this.definition = definition;
		this.provider = provider;
	}

	/**
	 * Instantiates a new instantiation interceptor stack callback.
	 * 
	 * @param requestor
	 *            the requestor
	 * @param provider
	 *            the {@link Provider}
	 * @param selector
	 *            the selector
	 */
	public InstantiationInterceptorStackCallback(Object requestor,
			Provider provider, InstantiationSelector selector) {

		Assertor.notNull(selector.getInstantiationInterceptors(),
				"The list of interceptors must not be null!");
		Assertor.notEmpty(selector.getInstantiationInterceptors(),
				"The list of interceptors must not be empty !");
		this.argTypes = selector.getArgTypes();
		this.args = selector.getArgs();
		this.requestor = requestor;
		this.targetClass = selector.getTargetClass();
		if (selector.getType().isInterface()) {
			this.targetInterface = new Class<?>[] { selector.getType() };
		}
		else
			this.targetInterface = null;
		this.interceptors = selector.getInstantiationInterceptors();
		this.definition = selector.getDefinition();
		this.provider = provider;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.jgentleframework.core.intercept.InstantiationInterceptor#instantiate
	 * (org.jgentleframework.core.intercept.ObjectInstantiation)
	 */
	public Object instantiate() throws Throwable {

		return new InterceptedObjectInstantiation(this.definition, null, null)
				.proceed();
	}

	/**
	 * The Class InterceptedObjectInstantiation.
	 */
	class InterceptedObjectInstantiation extends MetadataController implements
			ObjectInstantiation {
		/** The Constant serialVersionUID. */
		private static final long	serialVersionUID	= -3650610523177070593L;

		/**
		 * The Constructor.
		 * 
		 * @param key
		 *            the key
		 * @param value
		 *            the value
		 */
		public InterceptedObjectInstantiation(Object key, Object value) {

			super(key, value);
		}

		/**
		 * Instantiates a new intercepted object instantiation.
		 * 
		 * @param definition
		 *            the definition
		 * @param key
		 *            the key
		 * @param value
		 *            the value
		 */
		public InterceptedObjectInstantiation(Definition definition,
				Object key, Object value) {

			super(key, value);
			this.addMetadata(new MetadataImpl(MetadataKey.PREVIOUS_RESULT,
					previousResult));
			this.addMetadata(new MetadataImpl(MetadataKey.DEFINITION,
					definition));
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.jgentleframework.core.intercept.ObjectInstantiation#argTypes()
		 */
		@Override
		public Class<?>[] argTypes() {

			return argTypes;
		}

		/*
		 * (non-Javadoc)
		 * @see org.jgentleframework.core.intercept.ObjectInstantiation#args()
		 */
		@Override
		public Object[] args() {

			return args;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.jgentleframework.core.intercept.ObjectInstantiation#getRequestor()
		 */
		@Override
		public Object getRequestor() {

			return requestor;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.jgentleframework.core.intercept.ObjectInstantiation#getTargetClass
		 * ()
		 */
		@Override
		public Class<?> getTargetClass() {

			return targetClass;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.jgentleframework.core.intercept.ObjectInstantiation#getInterfaces
		 * ()
		 */
		@Override
		public Class<?>[] getInterfaces() {

			return targetInterface;
		}

		private final Log	log	= LogFactory.getLog(getClass());

		/*
		 * (non-Javadoc)
		 * @see org.aopalliance.intercept.Joinpoint#getStaticPart()
		 */
		@Override
		public AccessibleObject getStaticPart() {

			Constructor<?> constructor = null;
			if (argTypes != null && argTypes.length != 0) {
				try {
					constructor = getTargetClass().getConstructor(argTypes);
				}
				catch (SecurityException e) {
					if (log.isWarnEnabled()) {
						log.warn("Could not access to constructor !", e);
					}
					return null;
				}
				catch (NoSuchMethodException e) {
					if (log.isWarnEnabled()) {
						log.warn("Could not found specified constructor !", e);
					}
					return null;
				}
			}
			return constructor;
		}

		/*
		 * (non-Javadoc)
		 * @see org.aopalliance.intercept.Joinpoint#getThis()
		 */
		@Override
		public Object getThis() {

			return this;
		}

		/** The index. */
		int	index	= -1;

		/*
		 * (non-Javadoc)
		 * @see org.aopalliance.intercept.Joinpoint#proceed()
		 */
		@Override
		public Object proceed() throws Throwable {

			try {
				index++;
				if (index == interceptors.length) {
					return previousResult;
				}
				else {
					previousResult = interceptors[index].instantiate(this);
					return previousResult;
				}
			}
			finally {
				index--;
				previousResult = null;
			}
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.jgentleframework.core.intercept.ObjectInstantiation#getPreviousResult
		 * ()
		 */
		@Override
		public synchronized Object getPreviousResult() {

			return previousResult;
		}

		/*
		 * (non-Javadoc)
		 * @see
		 * org.jgentleframework.core.intercept.ObjectInstantiation#setPreviousResult
		 * (java.lang.Object)
		 */
		@Override
		public synchronized void setPreviousResult(Object pResult) {

			previousResult = pResult;
			this.addMetadata(new MetadataImpl(MetadataKey.PREVIOUS_RESULT,
					previousResult));
		}
	}
}