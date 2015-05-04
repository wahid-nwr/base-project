package com.swiftcorp.portal.schedule.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class ScheduleNotFoundException extends BusinessRuleViolationException
{
	public ScheduleNotFoundException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public ScheduleNotFoundException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public ScheduleNotFoundException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public ScheduleNotFoundException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public ScheduleNotFoundException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public ScheduleNotFoundException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
}
