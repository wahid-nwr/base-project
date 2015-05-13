/*
 * @ (#) GenericFileProcessingException.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.exception;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class GenericException extends Exception
{
	public GenericException ( String message )
	{
		super ( message );
	}
	
	public GenericException ( )
	{
		
	}
	
	public GenericException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public GenericException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
	
}
