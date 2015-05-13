/*
 * @ (#) BusinessRuleViolationException.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.exception;

/**
 * This is the superclass of all other exception which is generated from
 * violation of any business rule. Any exception that represents a logical/recoverable business logic 
 * violation must extend this class.
 * 
 * There is an errorcode into this class. All the exception thrown must define an errorcode for itself.
 * Clients who handles the exception will be able to categorize the exception from it's error code.
 * All business level errorcodes will start from 1000.
 * All SystemLevel errorcodes will start from 5000.
 *
 */

/**
 * @author swift
 * @since mar 3, 2011
 */
public class DuplicateDataException extends BusinessRuleViolationException
{
	private int errorCode;
	private String argument;
	private String argument2;
	
	public DuplicateDataException ( )
	{
		super ();
	}
	
	public DuplicateDataException ( String message )
	{
		super ( message );
	}
	
	public DuplicateDataException ( int errorCode )
	{
		this.errorCode = errorCode;
	}
	
	public DuplicateDataException ( int errorCode, String message )
	{
		super ( message );
		this.errorCode = errorCode;
	}
	
	public int getErrorCode ( )
	{
		return errorCode;
	}
	
	public void setErrorCode ( int errorCode )
	{
		this.errorCode = errorCode;
	}
	
	public DuplicateDataException ( String message, Throwable cause )
	{
		super ( message, cause );
		
	}
	
	public DuplicateDataException ( Throwable cause )
	{
		super ( cause );
		
	}
	
	public String getArgument ( )
	{
		return argument;
	}
	
	public void setArgument ( String argument )
	{
		this.argument = argument;
	}
	
	public String getArgument2 ( )
	{
		return argument2;
	}
	
	public void setArgument2 ( String argument2 )
	{
		this.argument2 = argument2;
	}
}
