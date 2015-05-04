package com.swiftcorp.portal.user.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class UserNotFoundException extends BusinessRuleViolationException
{
	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public UserNotFoundException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public UserNotFoundException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public UserNotFoundException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public UserNotFoundException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public UserNotFoundException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
	
}
