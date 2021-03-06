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
package org.jgentleframework.services.objectpooling;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.jgentleframework.context.beans.Initializing;
import org.jgentleframework.context.beans.ProviderAware;
import org.jgentleframework.services.objectpooling.annotation.SystemPooling;
import org.jgentleframework.utils.data.TimestampObjectBean;

/**
 * The Class CommonPool.
 * 
 * @author Quoc Chung - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Apr 8, 2008
 * @see Pool
 * @see BasePooling
 * @see ProviderAware
 * @see Initializing
 */
public class CommonPool extends AbstractBaseFactory {
	/*
	 * (non-Javadoc)
	 * @see
	 * org.jgentleframework.services.objectpooling.AbstractBaseFactory#initialize
	 * ()
	 */
	@Override
	public void initialize() {

		super.initialize();
		synchronized (this) {
			this.pool = new ArrayBlockingQueue<TimestampObjectBean<Object>>(
					this.maxPoolSize, true);
		}
		PoolStaticUtils.startEvictor(this.getEvictor(), this
				.getTimeBetweenEvictionRuns(), this, false);
		try {
			PoolStaticUtils.initIdleObject(this, this.minPoolSize);
		}
		catch (Exception e) {
			if (log.isFatalEnabled()) {
				log.fatal("Could not initialize min pool size !! ", e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.jgentleframework.services.objectpooling.Pool#addObject()
	 */
	@Override
	public synchronized void addObject() throws UnsupportedOperationException,
			Exception {

		assertDisable();
		Object obj = this.createsBean();
		addObjectToPool(obj, false);
	}

	/*
	 * (non-Javadoc)
	 * @see org.jgentleframework.services.objectpooling.Pool#close()
	 */
	@Override
	public void close() throws Exception {

		this.enable = false;
		synchronized (this) {
			clear();
			PoolStaticUtils.startEvictor(evictor, -1L, this, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.jgentleframework.services.objectpooling.Pool#obtainObject()
	 */
	@Override
	public Object obtainObject() throws NoSuchElementException, Exception {

		Object result = null;
		long starttime = System.currentTimeMillis();
		while (true) {
			TimestampObjectBean<Object> pair = null;
			synchronized (this) {
				assertDisable();
				pair = (TimestampObjectBean<Object>) (((Queue<TimestampObjectBean<Object>>) pool)
						.poll());
				if (pair == null) {
					if (this.maxPoolSize < 0
							|| this.getNumActive() <= this.maxPoolSize) {
						result = createsBean();
					}
					else {
						switch (this.exhaustedActionType) {
						case SystemPooling.EXHAUSTED_GROW:
							result = createsBean();
						case SystemPooling.EXHAUSTED_FAIL:
							throw new NoSuchElementException(
									"Pool exhausted !!");
						case SystemPooling.EXHAUSTED_BLOCK:
							try {
								if (this.creationTimeOut <= 0) {
									wait();
								}
								else {
									final long elapsed = (System
											.currentTimeMillis() - starttime);
									final long waitTime = this.creationTimeOut
											- elapsed;
									if (waitTime > 0) {
										wait(waitTime);
									}
								}
							}
							catch (InterruptedException e) {
								Thread.currentThread().interrupt();
								if (log.isErrorEnabled()) {
									log.error(e.getMessage(), e);
								}
							}
							if (this.creationTimeOut > 0
									&& ((System.currentTimeMillis() - starttime) >= this.creationTimeOut)) {
								throw new NoSuchElementException(
										"Timeout waiting for idle object");
							}
							else {
								continue;
							}
						default:
							throw new IllegalArgumentException(
									"ExhaustedActionType property "
											+ this.exhaustedActionType
											+ " not recognized.");
						}
					}
				}
				else {
					result = pair.getValue();
				}
				activatesObject(result);
				validatesObject(result);
				return result;
			}
		}
	}
}
