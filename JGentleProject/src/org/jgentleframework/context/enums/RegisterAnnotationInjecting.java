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
package org.jgentleframework.context.enums;

import java.lang.annotation.Annotation;

import org.jgentleframework.configure.annotation.AnnotationClass;
import org.jgentleframework.configure.annotation.AnnotationValidators;
import org.jgentleframework.configure.annotation.Bean;
import org.jgentleframework.configure.annotation.Builder;
import org.jgentleframework.configure.annotation.DefaultConstructor;
import org.jgentleframework.configure.annotation.Filter;
import org.jgentleframework.configure.annotation.Inject;
import org.jgentleframework.configure.annotation.Outject;

/**
 * The Enum RegisterAnnotationInjecting.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Oct 19, 2007
 */
public enum RegisterAnnotationInjecting {
	/** The Inject. */
	Inject (Inject.class),
	/** The Outject. */
	Outject (Outject.class),
	/** The Filter. */
	Filter (Filter.class),
	/** The Default constructor. */
	DefaultConstructor (DefaultConstructor.class),
	/** The Builder. */
	Builder (Builder.class),
	/** The Bean. */
	Bean (Bean.class),
	/** The Annotation class. */
	AnnotationClass (AnnotationClass.class),
	/** The Annotation validator. */
	AnnotationValidator (AnnotationValidators.class);
	/** The annotation class. */
	Class<? extends Annotation>	annotationClass;

	/**
	 * The Constructor.
	 * 
	 * @param clazz
	 *            the clazz
	 */
	RegisterAnnotationInjecting(Class<? extends Annotation> clazz) {

		this.annotationClass = clazz;
	}

	/**
	 * Gets the annotation class.
	 * 
	 * @return the annotation class
	 */
	@AnnotationClass
	public Class<? extends Annotation> getAnnotationClass() {

		return annotationClass;
	}
}
