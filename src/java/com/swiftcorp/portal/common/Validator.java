/*
 * @ (#) Validator.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.util.CalendarUtils;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class Validator
{
	private static final Log theLogger = LogFactory.getLog ( Validator.class );
	
	protected static void validateCreationDate ( Calendar creationDate )
			throws InvalidDateException
	{
		theLogger.debug ( "validateCreationDate(Calendar creationDate)" );
		
		if ( creationDate == null )
		{
			InvalidDateException InvalidDateException = new InvalidDateException ( "CREATION DATE MUST BE SUPPLIED" );
			InvalidDateException.setErrorCode ( ErrorConstants.CREATION_DATE_NOT_SUPPLIED );
			throw InvalidDateException;
		}
		
		if ( !CalendarUtils.isToday ( creationDate ) )
		{
			InvalidDateException InvalidDateException = new InvalidDateException ( "CREATION DATE NOT TODAY" );
			InvalidDateException.setErrorCode ( ErrorConstants.CREATION_DATE_NOT_TODAY );
			throw InvalidDateException;
		}
	}
	
	protected static void checkIfNull ( GenericDTO theDTO )
			throws SystemException
	{
		if ( theDTO == null )
		{
			SystemException systemException = new SystemException ( "INVALID ARGUMENT.DTO IS NULL." );
			systemException.setErrorCode ( ErrorConstants.INVALID_ARGUMENT );
			throw systemException;
		}
		
	}
}
