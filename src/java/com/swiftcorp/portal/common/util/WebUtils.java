/*
0 * @ (#) WebUtils.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.dto.LoginDetailInfoDTO;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.role.dto.RoleDTO;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author swift
 * @since mar 3, 2011
 */

public class WebUtils
{
	private static final Log theLogger = LogFactory.getLog ( WebUtils.class );
	
	private WebUtils ( )
	{
	}
	
	// public static int getAccessLevel(HttpServletRequest request)
	// {
	//
	// int loginLevel = GlobalConstants.SYSTEM_LEVEL;
	//
	// HttpSession session = request.getSession();
	// LoginDetailInfoDTO loginInfo =
	// (LoginDetailInfoDTO)session.getAttribute(SESSION_KEYS.LOGIN_INFO);
	// if(loginInfo != null)
	// {
	// loginLevel =loginInfo.getUser().getRole().getAccessLevel();
	// }
	// return loginLevel;
	// }
	
	public static int getUserWorkingLevel ( HttpServletRequest request )
	{
		
		int workingLevel = GlobalConstants.SYSTEM_LEVEL;
		
		HttpSession session = request.getSession ();
		Integer ulObj = (Integer) session.getAttribute ( SESSION_KEYS.WORKING_LEVEL );
		if ( ulObj != null )
		{
			workingLevel = ulObj.intValue ();
		}
		return workingLevel;
	}
	
	public static Long getComponentId ( HttpServletRequest request )
	{
		String componentId = request.getParameter ( "componentId" );
		theLogger.error ( "getComponentId(): componentId(str) from requestis = " + componentId );
		Long id = null;
		if ( componentId != null )
		{
			try
			{
				id = Long.parseLong ( componentId.trim () );
			}
			catch (NumberFormatException e)
			{
				theLogger.error ( "getComponentId(): NumberFormatException occurs", e );
			}
		}
		else
		{
			theLogger.error ( "getComponentId(): componentId is NULL" );
		}
		return id;
	}
	
	public static void setSuccessMessages ( HttpServletRequest request, String[] messageKeys, String[][] messageArgValues )
	{
		if ( messageKeys == null || messageKeys.length == 0 )
		{
			theLogger.error ( "messageKeys cant null or empty" );
			throw new RuntimeException ( "messageKeys cant null or empty" );
		}
		
		ActionMessages actionMessages = new ActionMessages ();
		
		int index = 0;
		for ( String messageKey : messageKeys )
		{
			String[] value = null;
			if ( messageArgValues != null && messageArgValues.length >= index )
			{
				value = messageArgValues[index];
			}
			ActionMessage actionMessage = new ActionMessage ( messageKey, value );
			actionMessages.add ( ActionMessages.GLOBAL_MESSAGE, actionMessage );
			index++;
		}
		
		request.setAttribute ( Globals.MESSAGE_KEY, actionMessages );
	}
	
	public static void setSuccessMessages ( HttpServletRequest request, String[] messageKeys, String messageArgValues )
	{
		if ( messageKeys == null || messageKeys.length == 0 )
		{
			theLogger.error ( "messageKeys cant null or empty" );
			throw new RuntimeException ( "messageKeys cant null or empty" );
		}
		
		ActionMessages actionMessages = new ActionMessages ();
		
		int index = 0;
		for ( String messageKey : messageKeys )
		{
			String value = null;
			
			theLogger.error ( "setSuccessMessages(): " + messageArgValues + "added" );
			value = messageArgValues;
			theLogger.error ( "setSuccessMessages(): " + value + "added" );
			
			ActionMessage actionMessage = new ActionMessage ( messageKey, value );
			actionMessages.add ( ActionMessages.GLOBAL_MESSAGE, actionMessage );
			index++;
		}
		
		request.setAttribute ( Globals.MESSAGE_KEY, actionMessages );
	}
	
	public static void setFailureMessages ( HttpServletRequest request, String[] messageKeys )
	{
		if ( messageKeys == null || messageKeys.length == 0 )
		{
			theLogger.error ( "messageKeys cant null or empty" );
			throw new RuntimeException ( "messageKeys cant null or empty" );
		}
		
		ActionMessages actionMessages = new ActionMessages ();
		for ( String messageKey : messageKeys )
		{
			ActionMessage actionMessage = new ActionMessage ( messageKey );
			actionMessages.add ( ActionMessages.GLOBAL_MESSAGE, actionMessage );
		}
		request.setAttribute ( Globals.ERROR_KEY, actionMessages );
	}
	
	public static void setFailureMessages ( HttpServletRequest request, String messageKey, String val1, String val2 )
	{
		if ( messageKey == null || messageKey.length () == 0 )
		{
			theLogger.error ( "messageKeys cant null or empty" );
			throw new RuntimeException ( "messageKeys cant null or empty" );
		}
		
		ActionMessages actionMessages = new ActionMessages ();
		ActionMessage actionMessage = new ActionMessage ( messageKey, val1, val2 );
		actionMessages.add ( ActionMessages.GLOBAL_MESSAGE, actionMessage );
		request.setAttribute ( Globals.ERROR_KEY, actionMessages );
	}
	
	public static void setFailureMessages ( HttpServletRequest request, String messageKey, String val )
	{
		if ( messageKey == null || messageKey.length () == 0 )
		{
			theLogger.error ( "messageKeys cant null or empty" );
			throw new RuntimeException ( "messageKeys cant null or empty" );
		}
		
		ActionMessages actionMessages = new ActionMessages ();
		ActionMessage actionMessage = new ActionMessage ( messageKey, val );
		actionMessages.add ( ActionMessages.GLOBAL_MESSAGE, actionMessage );
		request.setAttribute ( Globals.ERROR_KEY, actionMessages );
	}
	
	public static void setFailureMessages ( HttpServletRequest request, String[] messageKeys, String messageArgValues )
	{
		if ( messageKeys == null || messageKeys.length == 0 )
		{
			theLogger.error ( "messageKeys cant null or empty" );
			throw new RuntimeException ( "messageKeys cant null or empty" );
		}
		
		ActionMessages actionMessages = new ActionMessages ();
		
		for ( String messageKey : messageKeys )
		{
			String value = null;
			value = messageArgValues;
			ActionMessage actionMessage = new ActionMessage ( messageKey, value );
			actionMessages.add ( ActionMessages.GLOBAL_MESSAGE, actionMessage );
		}
		request.setAttribute ( Globals.ERROR_KEY, actionMessages );
	}
	
	public static String getThemeName ( HttpServletRequest request )
	{
		return "Default";
	}
	
	public static String getUniqueCode ( HttpServletRequest request )
	{
		HttpSession session = request.getSession ();
		LoginDetailInfoDTO loginInfo = (LoginDetailInfoDTO) session.getAttribute ( SESSION_KEYS.LOGIN_INFO );
		return loginInfo.getUser ().getUniqueCode ();
	}
	
	public static RoleDTO getUserRole ( HttpServletRequest request )
	{
		HttpSession session = request.getSession ();
		UserDTO userDTO = (UserDTO) session.getAttribute ( SESSION_KEYS.USER );
		return userDTO.getRole ();
	}
	
	public static Long getLoggedInUserId ( HttpServletRequest request )
	{
		HttpSession session = request.getSession ();
		LoginDetailInfoDTO loginInfo = (LoginDetailInfoDTO) session.getAttribute ( SESSION_KEYS.LOGIN_INFO );
		Long userId = null;
		
		if ( loginInfo != null )
		{
			userId = loginInfo.getUser ().getComponentId ();
		}
		
		if ( userId == null )
		{
			// for initial phase assuming this is as default case
			userId = new Long ( 1 );
		}
		
		return userId;
	}
	
}
