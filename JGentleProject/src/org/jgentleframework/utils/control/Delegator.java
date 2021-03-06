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
package org.jgentleframework.utils.control;

import java.lang.reflect.*;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jgentleframework.utils.ReflectUtils;

/**
 * The Class Delegator.
 * 
 * @author Quoc Chung - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Dec 31, 2007
 */
@SuppressWarnings("unchecked")
public class Delegator {
	/**
	 * All problems become this type of exception As a runtime we do not impose
	 * burdens on the calling code.
	 */
	public static class DelegateInvokeException extends RuntimeException {
		/**
		 * 
		 */
		private static final long	serialVersionUID	= 2066334114276957295L;

		/**
		 * Instantiates a new delegate invoke exception.
		 * 
		 * @param cause
		 *            the cause
		 */
		public DelegateInvokeException(Throwable cause) {

			super(cause);
		}
	}

	/**
	 * The Class DelegateProxy.
	 * 
	 * @author Quoc Chung - mailto: <a
	 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
	 * @date Dec 31, 2008
	 */
	protected class DelegateProxy implements IDelegate, InvocationHandler {
		/** The m_ method. */
		private final Method		m_Method;

		/** The m_ target. */
		private final Object		m_Target;

		/** The m_ template. */
		protected final Delegator	m_Template;

		/**
		 * constructor supplying a Class passing in types not template.
		 * 
		 * @param target
		 *            non-null object implementing a suitable static method
		 * @param MethodName
		 *            nun-null name of a public static method in target
		 * @param template
		 *            non-null template with the required arguemts and return
		 * @param targetClass
		 *            the target class
		 */
		protected DelegateProxy(Object target, Class targetClass,
				String MethodName, Delegator template) {

			m_Template = template;
			m_Target = target;
			Method meth = findSuitableMethod(targetClass, MethodName, template);
			m_Method = meth;
		}

		/**
		 * accessor for the method.
		 * 
		 * @return the method
		 */
		public Method getMethod() {

			return m_Method;
		}

		/**
		 * accessor for the target.
		 * 
		 * @return the target
		 */
		public Object getTarget() {

			return m_Target;
		}

		/**
		 * convenience call to handle case of no arguments.
		 * 
		 * @return whatever is returned
		 * @throws IllegalArgumentException
		 *             the illegal argument exception
		 * @throws DelegateInvokeException
		 *             the delegate invoke exception
		 */
		public Object invoke() throws IllegalArgumentException,
				DelegateInvokeException {

			return (invoke(EMPTY_OBJECT_ARRAY));
		}

		/**
		 * convenience call to handle case of one argument.
		 * 
		 * @param arg
		 *            some argument
		 * @return whatever is returned
		 * @throws IllegalArgumentException
		 *             the illegal argument exception
		 * @throws DelegateInvokeException
		 *             the delegate invoke exception
		 */
		public Object invoke(Object arg) throws IllegalArgumentException,
				DelegateInvokeException {

			Object[] args = { arg };
			return (invoke(args));
		}

		/**
		 * method required by InvocationHandler so we can build dynamic Proxys.
		 * 
		 * @param proxy
		 *            object for which we are a proxy (ignored)
		 * @param method
		 *            method to call (ignored)
		 * @param args
		 *            arguments to pass
		 * @return whatever is returned primitive types are wrapped
		 */
		public Object invoke(Object proxy, Method method, Object[] args) {

			return (invoke(args));
		}

		/**
		 * convenience call to handle case of two argument.
		 * 
		 * @param arg1
		 *            some argument
		 * @param arg2
		 *            some argument
		 * @return whatever is returned
		 * @throws IllegalArgumentException
		 *             the illegal argument exception
		 * @throws DelegateInvokeException
		 *             the delegate invoke exception
		 */
		public Object invoke(Object arg1, Object arg2)
				throws IllegalArgumentException, DelegateInvokeException {

			Object[] args = { arg1, arg2 };
			return (invoke(args));
		}

		/**
		 * basic call to method.
		 * 
		 * @param args
		 *            method arguments
		 * @return whatever is returned
		 * @throws IllegalArgumentException
		 *             the illegal argument exception
		 * @throws DelegateInvokeException
		 *             the delegate invoke exception
		 */
		public Object invoke(Object[] args) throws IllegalArgumentException,
				DelegateInvokeException {

			// validateArgs(args);
			try {
				Object ret = getMethod().invoke(getTarget(), args);
				return (ret);
			}
			catch (IllegalAccessException ex1) {
				throw new IllegalStateException("Bad Delgate State"
						+ ex1.getMessage()); // should not happen
			}
			catch (InvocationTargetException ex1) {
				throw new Delegator.DelegateInvokeException(ex1.getCause());
			}
		}

		/**
		 * if uncommented in invoke this code will throw an IllegalArgument call
		 * if arguments are of the wrong type.
		 * 
		 * @param args
		 *            the args
		 * @throws IllegalArgumentException
		 *             the illegal argument exception
		 */
		protected void validateArgs(Object[] args)
				throws IllegalArgumentException {

			Class[] MyArgs = getArguments();
			if (args.length != MyArgs.length)
				throw new IllegalArgumentException("Delegate required "
						+ MyArgs.length + "arguments");
			for (int i = 0; i < args.length; i++) {
				if (!MyArgs[i].isInstance(args[i]))
					throw new IllegalArgumentException("Argument " + i
							+ " must be of class " + MyArgs[i].getName());
			}
		}
	}

	/** The Constant EMPTY_ARRAY. */
	public static final Delegator[]	EMPTY_ARRAY			= {};

	/** The Constant EMPTY_METHOD_ARRAY. */
	public static final Method[]	EMPTY_METHOD_ARRAY	= {};

	/** The Constant EMPTY_OBJECT_ARRAY. */
	public static final Object[]	EMPTY_OBJECT_ARRAY	= {};

	/** The log. */
	protected static final Log		log					= LogFactory
																.getLog(Delegator.class);

	/** The Constant RUNNABLE_DELEGATE */
	public static final Delegator	RUNNABLE_DELEGATE	= new Delegator(
																Runnable.class);

	/**
	 * Convenience method to make a runnable delegate.
	 * 
	 * @param item
	 *            non-null target class
	 * @param methodName
	 *            non-null name of a method of type void ()
	 * @return non-null Runnable proxy
	 */
	public static Runnable buildRunnable(Class item, String methodName) {

		return ((Runnable) RUNNABLE_DELEGATE.build(item, methodName));
	}

	/**
	 * Convenience method to make a runnable delegate.
	 * 
	 * @param item
	 *            non-null target object
	 * @param methodName
	 *            non-null name of a method of type void ()
	 * @return non-null Runnable proxy
	 */
	public static Runnable buildRunnable(Object item, String methodName) {

		return ((Runnable) RUNNABLE_DELEGATE.build(item, methodName));
	}

	/**
	 * utility code to find the one suitable method in the passed in interface.
	 * 
	 * @param TheInterface
	 *            the the interface
	 * @return the method
	 */
	protected static Method findMethod(Class TheInterface) {

		if (!TheInterface.isInterface())
			throw new IllegalArgumentException(
					"DelegateTemplate must be constructed with an interface");
		Method[] methods = TheInterface.getMethods();
		Method ret = null;
		for (int i = 0; i < methods.length; i++) {
			Method test = methods[i];
			if (Modifier.isAbstract(test.getModifiers())) {
				if (ret != null)
					throw new IllegalArgumentException(
							"DelegateTemplate must be constructed "
									+ " with an interface implementing only one method!");
				ret = test;
			}
		}
		if (ret == null)
			throw new IllegalArgumentException(
					"DelegateTemplate must be constructed "
							+ " with an interface implementing exactly method!");
		return (ret);
	}

	/**
	 * Utility method to locate a proper Method object.
	 * 
	 * @param targetClass
	 *            the target class
	 * @param MethodName
	 *            the method name
	 * @param templ
	 *            the templ
	 * @return the method
	 */
	protected static Method findSuitableMethod(Class targetClass,
			String MethodName, Delegator templ) {

		Class[] args = templ.getArguments();
		Class retClass = templ.getReturn();
		// perfect match
		Method ret;
		try {
			ret = targetClass.getMethod(MethodName, args);
			if (!isValidReturn(ret, retClass))
				throw new IllegalArgumentException(
						"Requested method returns wrong type");
			if (!Modifier.isPublic(ret.getModifiers()))
				throw new IllegalArgumentException(
						"Requested method is not public");
			return (ret);
		}
		catch (SecurityException e) {
			if (log.isInfoEnabled()) {
				log
						.info("Could not found the suitable method of target class ["
								+ targetClass + "]");
			}
		}
		catch (NoSuchMethodException e) {
			if (log.isInfoEnabled()) {
				log
						.info("Could not found the suitable method of target class ["
								+ targetClass + "]");
			}
		}
		Method[] possibilities = getCandidateMethods(targetClass, MethodName,
				args.length);
		for (int i = 0; i < possibilities.length; i++) {
			Method possibility = possibilities[i];
			if (isSuitableMethod(possibility, args, retClass))
				return (possibility);
		}
		throw new IllegalArgumentException("No suitable method found");
	}

	/**
	 * utility method to get candidate methods to search.
	 * 
	 * @param targetClass
	 *            the target class
	 * @param MethodName
	 *            the method name
	 * @param nargs
	 *            the nargs
	 * @return the candidate methods
	 */
	protected static Method[] getCandidateMethods(Class targetClass,
			String MethodName, int nargs) {

		Method[] possibilities = targetClass.getMethods();
		List holder = new ArrayList();
		for (int i = 0; i < possibilities.length; i++) {
			Method possibility = possibilities[i];
			if (possibility.getName().equals(MethodName)
					&& possibility.getParameterTypes().length == nargs
					&& Modifier.isPublic(possibility.getModifiers()))
				holder.add(possibility);
		}
		return ((Method[]) holder.toArray(EMPTY_METHOD_ARRAY));
	}

	// ===================================================================
	// static utility methods in this section identify the
	// method in verious targets
	// ===================================================================
	/**
	 * utility method to test suitability.
	 * 
	 * @param testMethod
	 *            the test method
	 * @param args
	 *            the args
	 * @param retClass
	 *            the ret class
	 * @return true, if checks if is suitable method
	 */
	protected static boolean isSuitableMethod(Method testMethod, Class[] args,
			Class retClass) {

		Class[] methodArgs = testMethod.getParameterTypes();
		for (int i = 0; i < methodArgs.length; i++) {
			Class arg = methodArgs[i];
			if (!arg.isAssignableFrom(args[i]))
				return (false);
		}
		// This is the only
		isValidReturn(testMethod, retClass);
		return (true);
	}

	/**
	 * utility method to test return.
	 * 
	 * @param test
	 *            the test
	 * @param retClass
	 *            the ret class
	 * @return true, if checks if is valid return
	 */
	protected static boolean isValidReturn(Method test, Class retClass) {

		if (retClass == null)
			return (true); // we do not care
		if (test.getReturnType() == retClass)
			return (true);
		if (retClass.isAssignableFrom(test.getReturnType()))
			return (true);
		return (false);
	}

	/** The m_ arguments. */
	private final Class[]	m_Arguments;

	/** The m_ interface. */
	private final Class		m_Interface;	// may be null

	/** The m_ return. */
	private final Class		m_Return;

	/**
	 * The Constructor.
	 * 
	 * @param TheInterface
	 *            an non-null interface with EXACTLY one method
	 */
	public Delegator(Class TheInterface) {

		m_Interface = TheInterface;
		Method met = findMethod(TheInterface);
		m_Return = met.getReturnType();
		m_Arguments = met.getParameterTypes();
	}

	/**
	 * The Constructor.
	 * 
	 * @param params
	 *            non-null array of arguments
	 * @param retClass
	 *            possibly null return class null says do not care
	 */
	public Delegator(Class[] params, Class retClass) {

		m_Interface = null;
		m_Return = retClass;
		m_Arguments = params != null ? params.clone() : null;
	}

	/**
	 * Builds the.
	 * 
	 * @param target
	 *            non-null class with a bindable static method
	 * @param MethodName
	 *            name of the static method
	 * @return non-null IDelegate if getInterface() is non-null it will be a
	 *         dynamic prozy implementing that interface
	 */
	public IDelegate build(Class target, String MethodName) {

		Class myInterface = getInterface();
		DelegateProxy theDelegate = new DelegateProxy(null, target, MethodName,
				this);
		if (myInterface != null) {
			Class[] interfaces = { myInterface, IDelegate.class };
			IDelegate ret = (IDelegate) java.lang.reflect.Proxy
					.newProxyInstance(target.getClassLoader(), interfaces,
							theDelegate);
			return (ret);
		}
		return ((IDelegate) theDelegate);
	}

	/**
	 * Builds the.
	 * 
	 * @param target
	 *            non-null target with a bindable method
	 * @param MethodName
	 *            name of the method
	 * @return non-null IDelegate if getInterface() is non-null it will be a
	 *         dynamic prozy implementing that interface
	 */
	public IDelegate build(Object target, String MethodName) {

		Class myInterface = getInterface();
		DelegateProxy theDelegate = new DelegateProxy(target,
				target.getClass(), MethodName, this);
		if (myInterface != null) { // build a dynamic proxy
			Class[] interfaces = { myInterface, IDelegate.class };
			IDelegate ret = (IDelegate) java.lang.reflect.Proxy
					.newProxyInstance(target.getClass().getClassLoader(),
							interfaces, theDelegate);
			return (ret);
		}
		if (!ReflectUtils.isCast(IDelegate.class, theDelegate))
			throw new ClassCastException();
		return ((IDelegate) theDelegate);
	}

	/**
	 * accessor for argument classes.
	 * 
	 * @return the arguments
	 */
	public Class[] getArguments() {

		return m_Arguments != null ? m_Arguments.clone() : null;
	}

	/**
	 * Gets the interface.
	 * 
	 * @return the interface
	 */
	public Class getInterface() {

		return m_Interface;
	}

	/**
	 * accessor for return class.
	 * 
	 * @return the return
	 */
	public Class getReturn() {

		return m_Return;
	}
}
