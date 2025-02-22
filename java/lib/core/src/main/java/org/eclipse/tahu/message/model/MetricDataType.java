/********************************************************************************
 * Copyright (c) 2014, 2018 Cirrus Link Solutions and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Cirrus Link Solutions - initial implementation
 ********************************************************************************/

package org.eclipse.tahu.message.model;

import java.math.BigInteger;
import java.util.Date;

import org.eclipse.tahu.SparkplugInvalidTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An enumeration of data types associated with the value of a {@link Metric}
 */
public enum MetricDataType {

	// Basic Types
	Int8(1, Byte.class),
	Int16(2, Short.class),
	Int32(3, Integer.class),
	Int64(4, Long.class),
	UInt8(5, Short.class),
	UInt16(6, Integer.class),
	UInt32(7, Long.class),
	UInt64(8, BigInteger.class),
	Float(9, Float.class),
	Double(10, Double.class),
	Boolean(11, Boolean.class),
	String(12, String.class),
	DateTime(13, Date.class),
	Text(14, String.class),

	// Custom Types for Metrics
	UUID(15, String.class),
	DataSet(16, DataSet.class),
	Bytes(17, byte[].class),
	File(18, File.class),
	Template(19, Template.class),

	// PropertyValue Types (20 and 21) are NOT metric datatypes

	// Array Types
	Int8Array(22, Byte[].class),
	Int16Array(23, Short[].class),
	Int32Array(24, Integer[].class),
	Int64Array(25, Long[].class),
	UInt8Array(26, Short[].class),
	UInt16Array(27, Integer[].class),
	UInt32Array(28, Long[].class),
	UInt64Array(29, BigInteger[].class),
	FloatArray(30, Float[].class),
	DoubleArray(31, Double[].class),
	BooleanArray(32, Boolean[].class),
	StringArray(33, String[].class),
	DateTimeArray(34, Date[].class),

	// Unknown
	Unknown(0, Object.class);

	private static final Logger logger = LoggerFactory.getLogger(MetricDataType.class.getName());

	private Class<?> clazz = null;
	private int intValue = 0;

	private MetricDataType(int intValue, Class<?> clazz) {
		this.intValue = intValue;
		this.clazz = clazz;
	}

	public void checkType(Object value) throws SparkplugInvalidTypeException {
		if (value != null && !clazz.isAssignableFrom(value.getClass())) {
			logger.warn(
					"Failed type check - " + clazz + " != " + ((value != null) ? value.getClass().toString() : "null"));
			throw new SparkplugInvalidTypeException(value.getClass());
		}
	}

	/**
	 * Returns an integer representation of the data type.
	 * 
	 * @return an integer representation of the data type.
	 */
	public int toIntValue() {
		return this.intValue;
	}

	/**
	 * Converts the integer representation of the data type into a {@link MetricDataType} instance.
	 * 
	 * @param i the integer representation of the data type.
	 * @return a {@link MetricDataType} instance.
	 */
	public static MetricDataType fromInteger(int i) {
		switch (i) {
			case 1:
				return Int8;
			case 2:
				return Int16;
			case 3:
				return Int32;
			case 4:
				return Int64;
			case 5:
				return UInt8;
			case 6:
				return UInt16;
			case 7:
				return UInt32;
			case 8:
				return UInt64;
			case 9:
				return Float;
			case 10:
				return Double;
			case 11:
				return Boolean;
			case 12:
				return String;
			case 13:
				return DateTime;
			case 14:
				return Text;
			case 15:
				return UUID;
			case 16:
				return DataSet;
			case 17:
				return Bytes;
			case 18:
				return File;
			case 19:
				return Template;
			case 22:
				return Int8Array;
			case 23:
				return Int16Array;
			case 24:
				return Int32Array;
			case 25:
				return Int64Array;
			case 26:
				return UInt8Array;
			case 27:
				return UInt16Array;
			case 28:
				return UInt32Array;
			case 29:
				return UInt64Array;
			case 30:
				return FloatArray;
			case 31:
				return DoubleArray;
			case 32:
				return BooleanArray;
			case 33:
				return StringArray;
			case 34:
				return DateTimeArray;
			default:
				return Unknown;
		}
	}

	/**
	 * Returns the class type for this DataType
	 * 
	 * @return the class type for this DataType
	 */
	public Class<?> getClazz() {
		return clazz;
	}
}
