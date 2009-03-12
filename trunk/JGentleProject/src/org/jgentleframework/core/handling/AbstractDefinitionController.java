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
package org.jgentleframework.core.handling;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.jgentleframework.core.factory.InOutDependencyException;
import org.jgentleframework.core.reflection.AbstractVisitorHandler;
import org.jgentleframework.core.reflection.DefinitionPostProcessor;
import org.jgentleframework.core.reflection.IAnnotationVisitor;
import org.jgentleframework.core.reflection.annohandler.AnnotationBeanProcessor;
import org.jgentleframework.core.reflection.annohandler.AnnotationHandler;
import org.jgentleframework.core.reflection.annohandler.AnnotationPostProcessor;
import org.jgentleframework.core.reflection.metadata.AnnoMeta;
import org.jgentleframework.utils.Assertor;
import org.jgentleframework.utils.ReflectUtils;

/**
 * Quản lý các phương thức điều khiển các <i>module</i> là các
 * <i>extension-point</i> trong khi thực thi xử lý annotation, bao gồm việc
 * diễn dịch <code>annotation</code> thành {@link AnnoMeta}, quản lý
 * {@link DefinitionPostProcessor}, {@link AnnotationBeanProcessor}, ...
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Sep 9, 2007 12:34:12 AM
 */
public abstract class AbstractDefinitionController extends
		AbstractDefinitionExceptionCatcher implements DefinitionManager {
	protected AnnotationRegister	annotationRegister	= null;
	/**
	 * Đối tượng visitorHandler
	 */
	protected IAnnotationVisitor	visitorHandler		= new AbstractVisitorHandler(
																this) {
															@Override
															protected AnnoMeta build(
																	Annotation element,
																	AnnoMeta containter,
																	DefinitionManager defManager)
																	throws Exception {

																return ReflectUtils
																		.buildAnnoMeta(
																				element,
																				containter,
																				defManager);
															}
														};

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.reflection.aohreflect.IAnnotationVisitor#addAnnotationBeanProcessor(org.exxlabs.jgentle.core.reflection.annohandler.AnnotationBeanProcessor)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T extends Annotation> AnnotationBeanProcessor<?> addAnnotationBeanProcessor(
			AnnotationBeanProcessor<T> handler) throws ClassNotFoundException {

		Assertor.notNull(handler);
		Class<?> clazz = handler.getClass();
		ArrayList<Type> typeList = ReflectUtils.getAllGenericInterfaces(
				clazz, true);
		for (Type type : typeList) {
			if (ReflectUtils.isCast(ParameterizedType.class, type)) {
				ParameterizedType pType = (ParameterizedType) type;
				if (pType.getRawType().equals(AnnotationPostProcessor.class)
						|| pType.getRawType().equals(AnnotationHandler.class)) {
					String annoStrClass = pType.getActualTypeArguments()[0]
							.toString().split(" ")[1];
					Class<T> annoClass = (Class<T>) Class.forName(annoStrClass);
					if (annoClass == null)
						throw new InOutDependencyException(
								"Does not found valid annotation class.");
					return this.visitorHandler.addAnnotationBeanProcessor(
							annoClass, handler);
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.reflection.aohreflect.IAnnotationVisitor#addAnnotationBeanProcessor(java.lang.Class,
	 *      org.exxlabs.jgentle.core.reflection.annohandler.AnnotationBeanProcessor)
	 */
	@Override
	public <T extends Annotation> AnnotationBeanProcessor<?> addAnnotationBeanProcessor(
			Class<T> key, AnnotationBeanProcessor<T> handler) {

		return this.visitorHandler.addAnnotationBeanProcessor(key, handler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.reflection.aohreflect.IAnnotationVisitor#addDefinitionPostProcessor(java.lang.Class)
	 */
	@Override
	public void addDefinitionPostProcessor(
			Class<? extends DefinitionPostProcessor> dpp) {

		this.visitorHandler.addDefinitionPostProcessor(dpp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.reflection.aohreflect.IAnnotationVisitor#addDefinitionPostProcessor(org.exxlabs.jgentle.core.reflection.aohreflect.DefinitionPostProcessor)
	 */
	@Override
	public void addDefinitionPostProcessor(DefinitionPostProcessor dpp) {

		this.visitorHandler.addDefinitionPostProcessor(dpp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.metadatahandling.aohhandling.defhandling.DefinitionManager#getAnnotationRegister()
	 */
	@Override
	public AnnotationRegister getAnnotationRegister() {

		return annotationRegister;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.reflection.aohreflect.IAnnotationVisitor#getDefinitionPostProcessor(int)
	 */
	@Override
	public DefinitionPostProcessor getDefinitionPostProcessor(int index) {

		return this.visitorHandler.getDefinitionPostProcessor(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.metadatahandling.aohhandling.defhandling.DefinitionManager#getVisitorHandler()
	 */
	@Override
	public IAnnotationVisitor getVisitorHandler() {

		return visitorHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.reflection.aohreflect.IAnnotationVisitor#removeAnnotationBeanProcessor(java.lang.Class)
	 */
	@Override
	public <T extends Annotation> AnnotationBeanProcessor<?> removeAnnotationBeanProcessor(
			Class<T> key) {

		return this.visitorHandler.removeAnnotationBeanProcessor(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.reflection.aohreflect.IAnnotationVisitor#removeDefinitionPostProcessor(org.exxlabs.jgentle.core.reflection.aohreflect.DefinitionPostProcessor)
	 */
	@Override
	public boolean removeDefinitionPostProcessor(DefinitionPostProcessor dpp) {

		return this.visitorHandler.removeDefinitionPostProcessor(dpp);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.reflection.aohreflect.IAnnotationVisitor#removeDefinitionPostProcessor(int)
	 */
	@Override
	public DefinitionPostProcessor removeDefinitionPostProcessor(int index) {

		return this.visitorHandler.removeDefinitionPostProcessor(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.reflection.aohreflect.IAnnotationVisitor#replaceAnnotationBeanProcessor(org.exxlabs.jgentle.core.reflection.annohandler.AnnotationBeanProcessor,
	 *      org.exxlabs.jgentle.core.reflection.annohandler.AnnotationBeanProcessor)
	 */
	@Override
	public <T extends Annotation> void replaceAnnotationBeanProcessor(
			AnnotationBeanProcessor<T> oldHandler,
			AnnotationBeanProcessor<T> newHandler) {

		this.visitorHandler.replaceAnnotationBeanProcessor(oldHandler,
				newHandler);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.metadatahandling.aohhandling.defhandling.IDefinitionManager#setVisitorHandler(org.exxlabs.jgentle.core.reflection.aohreflect.AbsVisitorHandler)
	 */
	@Override
	public void setVisitorHandler(AbstractVisitorHandler visitorHandler) {

		this.visitorHandler = visitorHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.exxlabs.jgentle.core.reflection.aohreflect.IAnnotationVisitor#visit(java.lang.annotation.Annotation[],
	 *      org.exxlabs.jgentle.core.reflection.metadata.AnnoMeta)
	 */
	@Override
	public void visit(Annotation[] annoArray, AnnoMeta rootAnnoMeta) {

		this.visitorHandler.visit(annoArray, rootAnnoMeta);
	}
}
