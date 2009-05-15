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
package org.jgentleframework.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.jgentleframework.configure.Configurable;
import org.jgentleframework.configure.REF;
import org.jgentleframework.configure.annotation.DefaultConstructor;
import org.jgentleframework.configure.annotation.Inject;
import org.jgentleframework.context.injecting.Provider;
import org.jgentleframework.core.GenericException;
import org.jgentleframework.core.InvalidOperationException;
import org.jgentleframework.core.factory.InOutDependencyException;
import org.jgentleframework.core.factory.InOutExecutor;
import org.jgentleframework.core.reflection.metadata.Definition;

/**
 * The Class Utils.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Aug 20, 2007
 */
public final class Utils {
	/**
	 * Creates the scope name.
	 * 
	 * @param referString
	 *            the refer string
	 * @param provider
	 *            the provider
	 * @return the string
	 * @throws GenericException
	 *             the generic exception
	 */
	public static String createScopeName(String referString, Provider provider)
			throws GenericException {

		if (referString.indexOf(":") != -1) {
			String[] values = referString.split(":");
			if (values[0].equals(Configurable.REF_CONSTANT)) {
				return referString;
			}
			else if (values[0].equals(Configurable.REF_MAPPING)) {
				Class<?> clazz = null;
				if (values[1].indexOf(" ") != -1) {
					String[] split = values[1].split(" ");
					try {
						clazz = Class.forName(split[1]);
						Class<?> targetClass = provider.getObjectBeanFactory()
								.getMappingList().get(clazz);
						targetClass = targetClass == null ? clazz : targetClass;
						return createScopeName(clazz, targetClass, provider
								.getDefinitionManager().getDefinition(
										targetClass), values[1]);
					}
					catch (ClassNotFoundException e) {
					}
				}
				Entry<Class<?>, Class<?>> entry = provider
						.getObjectBeanFactory().getAliasMap().get(values[1]);
				if (entry == null) {
					throw new GenericException("Does not found mapping name '"
							+ values[1] + "'");
				}
				Class<?> type = entry.getKey();
				Class<?> targetClass = entry.getValue();
				return createScopeName(type, targetClass, provider
						.getDefinitionManager().getDefinition(targetClass),
						values[1]);
			}
			else if (values[0].equals(Configurable.REF_ID)) {
				Definition definition = provider.getDefinitionManager()
						.getDefinition(values[1]);
				if (definition == null) {
					throw new GenericException(
							"Does not found the definition according to ID '"
									+ values[1] + "'");
				}
				Class<?> inclass = (Class<?>) definition.getKey();
				return createScopeName(inclass, inclass, definition, null);
			}
		}
		return REF.refConstant(referString);
	}

	/**
	 * Creates the scope name.
	 * 
	 * @param type
	 *            the type
	 * @param targetClass
	 *            the target class
	 * @param definition
	 *            the definition
	 * @param mappingName
	 *            the mapping name
	 * @return the string
	 */
	public static String createScopeName(Class<?> type, Class<?> targetClass,
			Definition definition, String mappingName) {

		Assertor.notNull(type);
		Assertor.notNull(targetClass);
		Assertor.notNull(definition);
		String nameScope = null;
		nameScope = type.toString() + ":" + targetClass.toString() + ":"
				+ definition.toString();
		if (mappingName != null && !mappingName.isEmpty()) {
			nameScope = type.toString() + ":" + nameScope + ":" + mappingName;
		}
		return nameScope;
	}

	/**
	 * Creates the instance object from the given {@link Constructor}.
	 * 
	 * @param provider
	 *            the current {@link Provider}
	 * @param constructor
	 *            the given {@link Constructor}
	 * @return Object
	 */
	public static Object createInstanceFromInjectedConstructor(
			Provider provider, Constructor<?> constructor) {

		Object result = null;
		// Khởi tạo và inject dependency cho parameter
		Object[] args = new Object[constructor.getParameterTypes().length];
		for (int i = 0; i < constructor.getParameterAnnotations().length; i++) {
			HashMap<Class<? extends Annotation>, Annotation> annoList = new HashMap<Class<? extends Annotation>, Annotation>();
			List<Class<? extends Annotation>> clazzlist = new LinkedList<Class<? extends Annotation>>();
			for (Annotation anno : constructor.getParameterAnnotations()[i]) {
				annoList.put(anno.annotationType(), anno);
				clazzlist.add(anno.annotationType());
			}
			if (!clazzlist.contains(Inject.class)) {
				args[i] = null;
			}
			else {
				args[i] = InOutExecutor.getInjectedDependency((Inject) annoList
						.get(Inject.class), constructor.getParameterTypes()[i],
						provider);
			}
		}
		try {
			constructor.setAccessible(true);
			result = constructor.newInstance(args);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Gets the injected parameters of the given method.
	 * 
	 * @param method
	 *            the method
	 * @param defMethod
	 *            the definition according to given method.
	 * @param provider
	 *            the specified provider.
	 * @return returns an array containing the injected arguments if they exist,
	 *         if not, returns an empty array.
	 */
	public static Object[] getInjectedParametersOf(Method method,
			Definition defMethod, Provider provider) {

		Assertor.notNull(method, "The method must not be null.");
		Assertor.notNull(defMethod,
				"The definition of method must not be null.");
		Assertor.notNull(provider, "The provider must not be null");
		if (defMethod.getKey() != method) {
			Assertor
					.throwRunTimeException("The given definition is not corresponding to the given method!");
		}
		Class<?>[] argTypes = method.getParameterTypes();
		Object[] args = new Object[argTypes.length];
		if (argTypes.length == 0)
			throw new InOutDependencyException(
					"Invalid setter! The setter method has no any parameter!");
		if (defMethod.isAnnotationPresent(Inject.class)) {
			Inject anno = defMethod.getAnnotation(Inject.class);
			args = new Object[argTypes.length];
			for (int i = 0; i < argTypes.length; i++) {
				args[i] = InOutExecutor.getInjectedDependency(anno,
						argTypes[i], provider);
			}
		}
		if (defMethod.isAnnotationPresentAtAnyParameters(Inject.class)) {
			Definition[] defLst = defMethod.getParameterDefList();
			if (argTypes.length != defLst.length)
				throw new InOutDependencyException(
						"Invalid definition! Could not execute the injecting !");
			for (int i = 0; i < argTypes.length; i++) {
				if (defLst[i] != null
						&& defLst[i].isAnnotationPresent(Inject.class)) {
					args[i] = InOutExecutor
							.getInjectedDependency(defLst[i]
									.getAnnotation(Inject.class), argTypes[i],
									provider);
				}
			}
		}
		return args;
	}

	/**
	 * Returns the default {@link Constructor} of the given class. The default
	 * constructor is identified with annotated {@link DefaultConstructor}
	 * annotation.
	 * 
	 * @param clazz
	 *            the given class
	 * @return returns the default constructor if it exists, if not, return
	 *         <code>null</code>.
	 * @throws InOutDependencyException
	 *             throws this exception if there are more than one default
	 *             constructor.
	 * @see DefaultConstructor
	 */
	public static Constructor<?> getDefaultConstructor(Class<?> clazz) {

		Constructor<?> constructor = null;
		int i = 0;
		for (Constructor<?> objCons : clazz.getDeclaredConstructors()) {
			if (objCons.isAnnotationPresent(DefaultConstructor.class)) {
				constructor = objCons;
				i++;
			}
		}
		if (i > 1) {
			throw new RuntimeException(
					"There are more than one default constructor.");
		}
		return constructor;
	}

	/**
	 * Returns the specified default <code>getter</code> of the specified field.
	 * 
	 * @param declaringClass
	 *            the object class declared specified field.
	 * @param fieldName
	 *            name of field
	 * @return returns the <code>getter</code> method if it exists, if not,
	 *         return <code>null</code>.
	 */
	public static Method getDefaultGetter(Class<?> declaringClass,
			String fieldName) {

		Method getter = null;
		fieldName = fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		try {
			getter = ReflectUtils.getSupportedMethod(declaringClass, "get"
					+ fieldName, null);
		}
		catch (NoSuchMethodException e) {
			try {
				getter = ReflectUtils.getSupportedMethod(declaringClass, "is"
						+ fieldName, null);
			}
			catch (NoSuchMethodException e1) {
				return null;
			}
		}
		return getter;
	}

	/**
	 * Returns the specified default <code>setter</code> of the specified field.
	 * 
	 * @param declaringClass
	 *            the object class declared specified field.
	 * @param fieldName
	 *            name of field
	 * @return returns the <code>setter</code> method if it exists, if not,
	 *         return <code>null</code>.
	 */
	public static Method getDefaultSetter(Class<?> declaringClass,
			String fieldName) {

		Method setter = null;
		String methodName = "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		try {
			setter = ReflectUtils.getSupportedMethod(declaringClass,
					methodName, null);
		}
		catch (NoSuchMethodException e) {
			return null;
		}
		return setter;
	}

	/**
	 * Gets the field of default setter or getter method.
	 * 
	 * @param method
	 *            the given setter/getter method
	 * @param declaringClass
	 *            the declaring class
	 * @return the field of default setter/getter
	 * @throws InvalidOperationException
	 *             the invalid operation exception
	 * @throws NoSuchFieldException
	 *             the no such field exception
	 */
	public static Field getFieldOfDefaultSetGetter(Method method,
			Class<?> declaringClass) throws InvalidOperationException,
			NoSuchFieldException {

		Assertor.notNull(method, "The given method must not be null !");
		String name = method.getName();
		if (name.startsWith("set") || name.startsWith("get")) {
			String fieldName = name.substring(3);
			fieldName = fieldName.substring(0, 1).toLowerCase()
					+ fieldName.substring(1);
			return ReflectUtils.getSupportedField(declaringClass, fieldName);
		}
		else {
			throw new InvalidOperationException(
					"The given setter or getter method is not observed to default setter or getter !");
		}
	}

	/**
	 * Returns <code>true</code> if the given method is a setter method.
	 * 
	 * @param method
	 *            the given method.
	 * @return <code>true</code> if the given method is a setter method,
	 *         otherwise, returns <code>false</code>.
	 */
	public static boolean isSetter(Method method) {

		Assertor.notNull(method, "The given method must not be null !");
		String name = method.getName();
		if (name.startsWith("set"))
			return true;
		return false;
	}

	/**
	 * Returns <code>true</code> if the given method is a getter method.
	 * 
	 * @param method
	 *            the given method.
	 * @return <code>true</code> if the given method is a getter method,
	 *         otherwise, returns <code>false</code>.
	 */
	public static boolean isGetter(Method method) {

		Assertor.notNull(method, "The given method must not be null !");
		String name = method.getName();
		if (name.startsWith("get") || name.startsWith("is"))
			return true;
		return false;
	}
}