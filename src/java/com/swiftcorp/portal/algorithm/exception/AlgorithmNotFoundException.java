package com.swiftcorp.portal.algorithm.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class AlgorithmNotFoundException extends BusinessRuleViolationException
{
	public AlgorithmNotFoundException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public AlgorithmNotFoundException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public AlgorithmNotFoundException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public AlgorithmNotFoundException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public AlgorithmNotFoundException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public AlgorithmNotFoundException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
}
