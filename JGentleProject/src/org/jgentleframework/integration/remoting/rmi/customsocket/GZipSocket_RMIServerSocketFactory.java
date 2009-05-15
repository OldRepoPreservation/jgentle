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
package org.jgentleframework.integration.remoting.rmi.customsocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.server.RMIServerSocketFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jgentleframework.utils.network.sockets.GZipCompressingServerSocket;

/**
 * The Class GZipSocket_RMIServerSocketFactory.
 * 
 * @author Quoc Chung - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Feb 13, 2009
 * @see RMIServerSocketFactory
 */
public class GZipSocket_RMIServerSocketFactory implements
		RMIServerSocketFactory {
	/**
	 * 
	 */
	private static final long	serialVersionUID				= -1877551642035468389L;

	/** The Constant GZIP_SOCKETS_PROPERTY. */
	private static final String	GZIP_SERVER_SOCKETS_PROPERTY	= GZipSocket_RMIServerSocketFactory.class
																		.getPackage()
																		.toString();

	/** The _hash code. */
	private int					hashCode						= GZIP_SERVER_SOCKETS_PROPERTY
																		.hashCode();

	/** The log. */
	private final Log			log								= LogFactory
																		.getLog(getClass());

	/*
	 * (non-Javadoc)
	 * @see java.rmi.server.RMIServerSocketFactory#createServerSocket(int)
	 */
	public ServerSocket createServerSocket(int port) {

		try {
			return new GZipCompressingServerSocket(port);
		}
		catch (IOException e) {
			if (log.isFatalEnabled()) {
				log.fatal("Could not create GZip Compressing Server Socket !",
						e);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object) {

		if (object instanceof GZipSocket_RMIServerSocketFactory) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {

		return hashCode;
	}
}
