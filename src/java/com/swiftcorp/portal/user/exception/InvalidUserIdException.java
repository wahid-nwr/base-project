package com.swiftcorp.portal.user.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class InvalidUserIdException extends BusinessRuleViolationException
{
	private static final long serialVersionUID = 1L;
	
	public InvalidUserIdException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public InvalidUserIdException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public InvalidUserIdException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public InvalidUserIdException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
	
	public InvalidUserIdException ( int errorCode, String message )
	{
		super ( errorCode, message );
	}
	
	public InvalidUserIdException ( String message )
	{
		super ( message );
	}
	
}
