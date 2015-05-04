package com.swiftcorp.portal.alert.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class AlertNotFoundException extends BusinessRuleViolationException
{
	public AlertNotFoundException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public AlertNotFoundException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public AlertNotFoundException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public AlertNotFoundException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public AlertNotFoundException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public AlertNotFoundException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
}
