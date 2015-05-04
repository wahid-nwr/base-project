package com.swiftcorp.portal.role.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class RoleNotFoundException extends BusinessRuleViolationException
{
	
	private static final long serialVersionUID = 1L;
	
	public RoleNotFoundException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public RoleNotFoundException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public RoleNotFoundException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public RoleNotFoundException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public RoleNotFoundException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public RoleNotFoundException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
	
}
