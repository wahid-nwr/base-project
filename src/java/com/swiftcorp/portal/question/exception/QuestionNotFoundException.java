package com.swiftcorp.portal.question.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class QuestionNotFoundException extends BusinessRuleViolationException
{
	public QuestionNotFoundException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public QuestionNotFoundException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public QuestionNotFoundException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public QuestionNotFoundException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public QuestionNotFoundException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public QuestionNotFoundException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
}
