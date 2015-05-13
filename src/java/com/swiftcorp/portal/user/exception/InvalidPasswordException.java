package com.swiftcorp.portal.user.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class InvalidPasswordException extends BusinessRuleViolationException
{
	private static final long serialVersionUID = 1L;
	
	public InvalidPasswordException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public InvalidPasswordException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public InvalidPasswordException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public InvalidPasswordException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public InvalidPasswordException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public InvalidPasswordException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
	
}
