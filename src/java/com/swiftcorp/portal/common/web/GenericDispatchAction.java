/*
 * @ (#) GenericDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class GenericDispatchAction extends DispatchAction
{
	
	@Override
	public ActionForward execute ( ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		return super.execute ( actionMapping, actionForm, request, response );
	}
	
}
