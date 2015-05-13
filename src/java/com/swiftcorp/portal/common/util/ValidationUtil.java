/*
 * @ (#) ValidationUtil.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.util;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class ValidationUtil
{
	/**
	 * SEE IF THE STRING IS NULL OR EMPTY
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty ( String str )
	{
		/**
		 * CHECK FOR NULL
		 */
		if ( str == null )
			return true;
		
		/**
		 * CHECK FOR EMPTY
		 */
		if ( "".equals ( str.trim () ) )
			return true;
		
		return false;
	}
	
}
