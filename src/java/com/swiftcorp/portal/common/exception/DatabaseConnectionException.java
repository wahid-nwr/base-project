package com.swiftcorp.portal.common.exception;

public class DatabaseConnectionException extends SystemException
{
	
	public DatabaseConnectionException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public DatabaseConnectionException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public DatabaseConnectionException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public DatabaseConnectionException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public DatabaseConnectionException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public DatabaseConnectionException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
}
