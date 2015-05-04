/*
 * @ (#) CMSReportCreationException.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.report.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

/**
 * @author mosa
 * @since Sep 4, 2008
 */
public class ReportCreationException extends BusinessRuleViolationException
{
	
	public ReportCreationException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public ReportCreationException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public ReportCreationException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public ReportCreationException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public ReportCreationException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public ReportCreationException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
	
}
