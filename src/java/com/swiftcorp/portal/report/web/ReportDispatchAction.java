package com.swiftcorp.portal.report.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.web.ForwardUtil;
import com.swiftcorp.portal.report.service.IReportService;

public class ReportDispatchAction extends DispatchAction
{
	private static final Log theLogger = LogFactory.getLog ( ReportDispatchAction.class );
	private IReportService reportService;
	
	public void setReportService ( IReportService reportService )
	{
		this.reportService = reportService;
	}
	
	public ActionForward promptInputReport ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws BusinessRuleViolationException, SystemException
	{
		theLogger.info ( "promptInputReport()::" );
		return mapping.findForward ( "reporthome" );
	}
	
	public ActionForward cancelOperation ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelOperation() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
	public ActionForward promptMPRInputReport ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
	throws BusinessRuleViolationException, SystemException
	{
		theLogger.info ( "promptInputReport()::" );
		return mapping.findForward ( "reporthome" );
	}
}
