package com.swiftcorp.portal.common.mail.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class FailedToSendMailException extends BusinessRuleViolationException
{
	private static final long serialVersionUID = 1L;
	
	public FailedToSendMailException ( )
	{
		super ();
	}
	
	public FailedToSendMailException ( int errorCode, String message )
	{
		super ( errorCode, message );
	}
	
	public FailedToSendMailException ( int errorCode )
	{
		super ( errorCode );
	}
	
	public FailedToSendMailException ( String message, Throwable cause )
	{
		super ( message, cause );
	}
	
	public FailedToSendMailException ( String message )
	{
		super ( message );
	}
	
	public FailedToSendMailException ( Throwable cause )
	{
		super ( cause );
	}
}
