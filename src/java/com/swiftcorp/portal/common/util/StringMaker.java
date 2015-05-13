/*
 * @ (#) StringMaker.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.util;

/**
 * This is an utility class for string operations.
 * Works as a wrapper of underlying StringBuilder/StringBuffer. 
 * Use this class to do the string concatanations. We can switch to StringBuffer/StringBuilder 
 * any time we need depending on the java version. Users of this class will remain uneffected by the change.   
 */

/**
 * @author swift
 * @since mar 3, 2011
 */
public class StringMaker
{
	private StringBuilder buffer;
	
	private StringMaker ( )
	{
		buffer = new StringBuilder ();
	}
	
	/**
	 * Clears the buffer. All previous value will be lost.
	 * 
	 */
	public void clearBuffer ( )
	{
		buffer = new StringBuilder ();
	}
	
	/**
	 * Appends the String value (toString()) of the given param to the existing
	 * buffer.
	 * 
	 * @param o
	 * @return The reference to the StringMaker object itself so that it can be
	 *         used for call chaining.
	 * 
	 */
	public StringMaker addToBuffer ( Object o )
	{
		
		buffer.append ( o );
		return this;
	}
	
	public StringMaker addAllToBuffer ( Object... objects )
	{
		if ( objects == null )
			return this;
		
		for ( Object obj : objects )
		{
			buffer.append ( obj );
		}
		
		return this;
	}
	
	public String getBufferValue ( )
	{
		return buffer.toString ();
	}
	
	/**
	 * Utility method. Can be used to add the stringvalue of two object
	 * 
	 * @param str1
	 * @param str2
	 * @return str1.toString + str2.toString()
	 * 
	 */
	public static String makeString ( Object str1, Object str2 )
	{
		StringBuffer buffer = getNewBuffer ();
		
		buffer.append ( str1 );
		buffer.append ( str2 );
		
		return buffer.toString ();
	}
	
	/**
	 * Utility method. Can be used to add the stringvalue of three object
	 * 
	 * @param str1
	 * @param str2
	 * @param str3
	 * @return str1.toString()+str2.toString()+str3.toString()
	 */
	public static String makeString ( Object str1, Object str2, Object str3 )
	{
		StringBuffer buffer = getNewBuffer ();
		
		buffer.append ( str1 );
		buffer.append ( str2 );
		buffer.append ( str3 );
		
		return buffer.toString ();
	}
	
	/**
	 * Utility method. Can be used to add the stringvalue of four object
	 * 
	 * @param str1
	 * @param str2
	 * @param str3
	 * @param str4
	 * @return str1.toString() + str2.toString() + str3.toString() +
	 *         str4.toString()
	 */
	public static String makeString ( Object str1, Object str2, Object str3, Object str4 )
	{
		StringBuffer buffer = getNewBuffer ();
		
		buffer.append ( str1 );
		buffer.append ( str2 );
		buffer.append ( str3 );
		buffer.append ( str4 );
		
		return buffer.toString ();
	}
	
	private static StringBuffer getNewBuffer ( )
	{
		return new StringBuffer ();
	}
	
	/**
	 * Factory method. Use this to get a reference to the StringMaker.
	 * 
	 * @return instance of this class
	 */
	public static StringMaker getInstance ( )
	{
		return new StringMaker ();
	}
	
	@Override
	public String toString ( )
	{
		return this.getBufferValue ();
	}
}
