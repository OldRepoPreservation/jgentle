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
package org.jgentleframework.utils.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

/**
 * The Class StreamController.
 * 
 * @author LE QUOC CHUNG - mailto: <a
 *         href="mailto:skydunkpro@yahoo.com">skydunkpro@yahoo.com</a>
 * @date Mar 3, 2008
 */
public abstract class StreamController {
	/**
	 * Copy.
	 * 
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @return int
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static int copy(InputStream source, OutputStream destination)
			throws IOException {

		int nextByte;
		int numberOfBytesCopied = 0;
		while (-1 != (nextByte = source.read())) {
			destination.write(nextByte);
			numberOfBytesCopied++;
		}
		destination.flush();
		return numberOfBytesCopied;
	}

	/**
	 * Compress gzip.
	 * 
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void compressGZIP(InputStream source, OutputStream destination)
			throws IOException {

		if ((null != source) && (null != destination)) {
			BufferedInputStream bufferedSource = new BufferedInputStream(source);
			BufferedOutputStream bufferedDestination = new BufferedOutputStream(
					destination);
			GZIPOutputStream zippedDestination = new GZIPOutputStream(
					bufferedDestination);
			StreamController.copy(bufferedSource, zippedDestination);
			bufferedSource.close();
			zippedDestination.close();
		}
	}

	/**
	 * Compress zip.
	 * 
	 * @param source
	 *            the source
	 * @param destination
	 *            the destination
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void compressZIP(InputStream source, OutputStream destination)
			throws IOException {

		if ((null != source) && (null != destination)) {
			BufferedInputStream bufferedSource = new BufferedInputStream(source);
			BufferedOutputStream bufferedDestination = new BufferedOutputStream(
					destination);
			ZipOutputStream zippedDestination = new ZipOutputStream(
					bufferedDestination);
			StreamController.copy(bufferedSource, zippedDestination);
			bufferedSource.close();
			zippedDestination.close();
		}
	}
}
