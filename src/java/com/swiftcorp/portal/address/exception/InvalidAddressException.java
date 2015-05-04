/*
 * @ (#) InvalidAddressException.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.address.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

/**
 * @author mosa
 * @since Sep 4, 2008
 */
public class InvalidAddressException extends BusinessRuleViolationException
{
	
	public InvalidAddressException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public InvalidAddressException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public InvalidAddressException ( int errorCode )
	{
		super ( errorCode );
	}
	
	public InvalidAddressException ( int errorCode, String message )
	{
		super ( errorCode, message );
	}
}
