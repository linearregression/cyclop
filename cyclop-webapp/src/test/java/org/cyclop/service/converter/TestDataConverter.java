/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cyclop.service.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.inject.Inject;

import org.cyclop.test.AbstractTestCase;
import org.junit.Test;

/** @author Maciej Miklas */
public class TestDataConverter extends AbstractTestCase {

	@Inject
	private DataConverter converter;

	@Test
	public void testConvert_Null() {
		assertNull(converter.convert(null));
	}

	@Test
	public void testConvert_String() {
		assertEquals("abc cde ", converter.convert("abc cde "));
	}

	@Test
	public void testConvert_InetAddress() {
		assertEquals("localhost/127.0.0.1", converter.convert(InetAddress.getLoopbackAddress()));
	}

	@Test
	public void testConvert_UUID() {
		final UUID id = UUID.randomUUID();
		assertEquals(id.toString(), converter.convert(id));
	}

	@Test
	public void testConvert_Date() {
		final String actual = converter.convert(LocalDateTime.parse("2014-03-03T08:33:46.821"));
		assertEquals("2014-03-03 08:33:46.821", actual);
	}

	@Test
	public void testConvert_BigInteger() {
		assertEquals("1393832026821", converter.convert(BigInteger.valueOf(1393832026821L)));
	}

	@Test
	public void testConvert_Double() {
		assertEquals("123.32444", converter.convert(Double.valueOf(123.32444)));
	}

	@Test
	public void testConvert_BigDecimal() {
		assertEquals("123.32444", converter.convert(BigDecimal.valueOf(123.32444)));
	}

	@Test
	public void testConvert_Boolean() {
		assertEquals("false", converter.convert(Boolean.FALSE));
	}

	@Test
	public void testConvert_Integer() {
		assertEquals("1212122", converter.convert(Integer.valueOf(1212122)));
	}

	@Test
	public void testConvert_Long() {
		assertEquals("1212122", converter.convert(Long.valueOf(1212122L)));
	}

	@Test
	public void testConvert_Float() {
		assertEquals("1.2123457E23", converter.convert(Float.valueOf(121234567898765412234232F)));
	}
}
