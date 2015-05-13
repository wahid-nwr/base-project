/*
 * @ (#) ERROR_CODE.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common;

public interface ErrorConstants
{
	/**********************************************************************************
	 * BUSINESS ERRORS [1000-4999]
	 **********************************************************************************/
	public static final int CREATION_DATE_NOT_SUPPLIED = 1000;
	public static final int CREATION_DATE_NOT_TODAY = 1001;
	public static final int INVALID_PARENT_GROUP_ID = 1002;
	
	/**********************************************************************************
	 * SYSTEM ERRORS [5000-9999]
	 *********************************************************************************/
	public static final int INVALID_ARGUMENT = 5000;
	
	/**********************************************************************************
	 * XLS FILE DATA TO DTO CONVERSION ERRORS [10001-12000]
	 *********************************************************************************/
	
	public static final int INVALID_TC_EMIAMT = 10001;
	public static final int INVALID_TC_DTRPST = 10002;
	
	public static final String NOT_AUTHENTICATED = "NAUTH";
	
}
