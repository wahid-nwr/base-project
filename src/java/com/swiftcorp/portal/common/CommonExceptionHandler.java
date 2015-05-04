package com.swiftcorp.portal.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.util.WebUtils;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.MessageKeys;

public class CommonExceptionHandler extends ExceptionHandler
{
	
	private static final Log theLogger = LogFactory.getLog ( CommonExceptionHandler.class );
	
	@SuppressWarnings("null")
	public ActionForward execute ( Exception exception, ExceptionConfig exceptionConfig, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws ServletException
	{
		String messageArgValues = exception.getMessage ();
		theLogger.error ( "Exception occured: ", exception );
		theLogger.info ( "execute(): exception.name = " + exception.getClass ().getName () + ";messageArgValues = " + messageArgValues );
		
		// When specific message is found
		if ( messageArgValues != null || messageArgValues.compareTo ( "" ) != 0 )
		{
			WebUtils.setFailureMessages ( request, new String[]
			{
				messageArgValues
			} );
		}
		else
		{
			if ( exception instanceof BusinessRuleViolationException )
			{
				WebUtils.setFailureMessages ( request, MessageKeys.BUSINESS_EXCEPTION_ERROR_KEY );
			}
			else if ( exception instanceof SystemException )
			{
				WebUtils.setFailureMessages ( request, MessageKeys.SYSTEM_EXCEPTION_ERROR_KEY );
			}
			else
			{
				WebUtils.setFailureMessages ( request, MessageKeys.EXCEPTION_ERROR_KEY );
			}
		}
		
		return mapping.findForward ( ForwardNames.ERROR );
		
	}
	
}
