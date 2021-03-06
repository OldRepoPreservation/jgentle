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
package org.jgentleframework.utils.network.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Quoc Chung - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Feb 19, 2009
 */
public class XorServerSocket extends ServerSocket {
	/*
	 * The pattern used to "encrypt" and "decrypt" each byte sent or received by
	 * the socket.
	 */
	private final byte	pattern;

	/*
	 * Constructor for class XorServerSocket.
	 */
	public XorServerSocket(int port, byte pattern) throws IOException {

		super(port);
		this.pattern = pattern;
	}

	/*
	 * Creates a socket of type XorSocket and then calls implAccept to wait for
	 * a client connection.
	 */
	public Socket accept() throws IOException {

		Socket s = new XorSocket(pattern);
		implAccept(s);
		return s;
	}
}
