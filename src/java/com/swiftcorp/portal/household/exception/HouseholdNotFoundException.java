package com.swiftcorp.portal.household.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class HouseholdNotFoundException extends BusinessRuleViolationException
{
	public HouseholdNotFoundException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public HouseholdNotFoundException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public HouseholdNotFoundException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public HouseholdNotFoundException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public HouseholdNotFoundException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public HouseholdNotFoundException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
}
