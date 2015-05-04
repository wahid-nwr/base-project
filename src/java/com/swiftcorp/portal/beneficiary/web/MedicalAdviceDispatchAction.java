/*
 * @ (#) BeneficiaryDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.beneficiary.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.swiftcorp.portal.beneficiary.service.IBeneficiaryService;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.SearchUtil;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.user.service.IUserService;

/**
 * @author soma
 * @since Sep 22, 2008
 */
public class MedicalAdviceDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog ( MedicalAdviceDispatchAction.class );
	@SuppressWarnings("unused")
	private IBeneficiaryService beneficiaryService;
	@SuppressWarnings("unused")
	private IGroupService groupService;
	
	//private IMedicalAdviceService medicalAdviceService;
	private IUserService userService;
	
	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public void setBeneficiaryService ( IBeneficiaryService beneficiaryService )
	{
		this.beneficiaryService = beneficiaryService;
	}
	
	public ActionForward promptBeneficiarySearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptBeneficiarySearchSystemLevel() : enter" );
		try
		{
			BeneficiarySearchUtils.prepareSearchPage ( request );
			String searchSqlQuery = BeneficiarySearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			SearchOperationResult searchOperationResult = beneficiaryService.search ( searchSqlQuery );
			log.info ( "searchBeneficiaryFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.BENEFICIARY_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			BeneficiarySearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptBeneficiarySearchSystemLevel() :", e );
			throw e;
		}
		// show the beneficiary search page
		return mapping.findForward ( ForwardNames.BENEFICIARY_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptMedicalAdvice ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		
		// show the beneficiary search page
		return mapping.findForward ( "medicalAdviceAdd" );
	}
	
	public ActionForward addMedicalAdvice ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		// request.setCharacterEncoding("UTF8");
		// response.setContentType("text/html;charset=UTF-8");
		// response.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF8");
		log.info ( "addMedicalAdvice enter :" );
		
		
		log.info ( "addMedicalAdvice Exit :" );
		// show the beneficiary search page
		return mapping.findForward ( "medicalAdviceAdd" );
	}
	
	public ActionForward addMedicalAdviceByDoctor ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		// request.setCharacterEncoding("UTF8");
		// response.setContentType("text/html;charset=UTF-8");
		// response.setCharacterEncoding("UTF-8");
		// response.setCharacterEncoding("UTF8");
		log.info ( "addMedicalAdvice enter :" );
		
		log.info ( "addMedicalAdvice Exit :" );
		// show the beneficiary search page
		return mapping.findForward ( "" );
	}
	
		
	public IUserService getUserService ( )
	{
		return userService;
	}
	
	public void setUserService ( IUserService userService )
	{
		this.userService = userService;
	}
	
}
