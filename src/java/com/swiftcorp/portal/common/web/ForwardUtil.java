/*
 * Copyright (c) 2010 ClickDiagnostics Inc. All  Rights Reserved.
 * This software is the confidential and proprietary information
 * of ClickDiagnostics ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with ClickDiagnostics.
 */

package com.swiftcorp.portal.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author Zahangir Alam
 * @since Sep 10, 2009
 */
public class ForwardUtil
{
	protected static final Log log = LogFactory.getLog ( ForwardUtil.class );
	private static ForwardUtil forwardUtil;
	
	private ForwardUtil ( )
	{
		
	}
	
	public static ForwardUtil getInstance ( )
	{
		if ( forwardUtil == null )
		{
			forwardUtil = new ForwardUtil ();
		}
		
		return forwardUtil;
	}
	
	public ActionForward promtHomePage ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "promtHomePage() : enter" );
		HttpSession session = request.getSession ();
		
		UserDTO userDTO = (UserDTO) session.getAttribute ( SESSION_KEYS.USER );
		return mapping.findForward ( ForwardNames.USER_HOME );
		
	}
}
