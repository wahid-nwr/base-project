package com.swiftcorp.portal.user.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.SESSION_KEYS;

public class UserExceptionHandler
{
	private static final Log theLogger = LogFactory.getLog ( UserExceptionHandler.class );
	
	public static String getExceptionForwardPath ( Exception exception, ActionMapping mapping, HttpServletRequest request )
	{
		theLogger.info ( "getExceptionForwardPath()::" );
		
		String methodName = request.getParameter ( "method" );
		
		if ( "addGroupUser".equals ( methodName ) )
		{
			request.setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
			return ForwardNames.USER_SEARCH_SYSTEM_LEVEL;
		}
		else
		{
			return null;
		}
		
	}
	
}
