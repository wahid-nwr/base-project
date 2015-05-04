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
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.SearchUtil;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.ForwardUtil;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.household.service.IHouseholdService;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author soma
 * @since Sep 22, 2008
 */
public class BeneficiaryDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog ( BeneficiaryDispatchAction.class );
	@SuppressWarnings("unused")
	private IBeneficiaryService beneficiaryService;
	@SuppressWarnings("unused")
	private IGroupService groupService;
	
	private IHouseholdService householdService;
	
		
	public void setHouseholdService ( IHouseholdService householdService )
	{
		this.householdService = householdService;
	}
	
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
	
	public ActionForward showBeneficiary ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "showBeneficiary() : enter" );
		try
		{
			// List<MotherBeneficiaryDTO> beneficiaryList =
			// beneficiaryService.getList ();
			// request.getSession ().setAttribute (
			// SESSION_KEYS.BENEFICIARY_LIST, beneficiaryList );
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
		return mapping.findForward ( ForwardNames.BENEFICIARY_HOME );
	}
	
	
	
	public ActionForward promptBeneficiarySearchGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptBeneficiarySearchGroupLevel() : enter" );
		try
		{
			// here we want to load the beneficiary
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
			log.info ( "promptBeneficiarySearchGroupLevel() :", e );
			throw e;
		}
		// show the beneficiary search page
		return mapping.findForward ( ForwardNames.BENEFICIARY_SEARCH_GROUP_LEVEL );
	}
	
	
	
	public ActionForward searchBeneficiaryFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchBeneficiaryFromSystemLevel():: Enter" );
		String searchSqlQuery = BeneficiarySearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = beneficiaryService.search ( searchSqlQuery );
		log.info ( "searchBeneficiaryFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.BENEFICIARY_SEARCH_RESULT, searchOperationResult );
		BeneficiarySearchUtils.prepareSearchPage ( request );
		log.info ( "searchBeneficiaryFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.BENEFICIARY_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchChildBeneficiaryFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchBeneficiaryFromSystemLevel():: Enter" );
		String searchSqlQuery = BeneficiarySearchUtils.prepareChildSqlQuesry ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = beneficiaryService.search ( searchSqlQuery );
		log.info ( "searchBeneficiaryFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.BENEFICIARY_SEARCH_RESULT, searchOperationResult );
		BeneficiarySearchUtils.prepareSearchPageForChild ( request );
		log.info ( "searchBeneficiaryFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.BENEFICIARY_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchBeneficiaryFromGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchBeneficiaryFromGroupLevel():: Enter" );
		String searchSqlQuery = BeneficiarySearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = beneficiaryService.search ( searchSqlQuery );
		log.info ( "searchBeneficiaryFromGroupLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		BeneficiarySearchUtils.prepareSearchPage ( request );
		request.setAttribute ( SESSION_KEYS.BENEFICIARY_SEARCH_RESULT, searchOperationResult );
		log.info ( "searchBeneficiaryFromGroupLevel()::Exit" );
		return mapping.findForward ( ForwardNames.BENEFICIARY_SEARCH_GROUP_LEVEL );
	}
	
	
	
	public ActionForward cancelBeneficiaryOperation ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "cancelBeneficiaryOperation() :" );
		UserDTO usrDTO = (UserDTO) request.getSession ().getAttribute ( SESSION_KEYS.USER );
		// TODO: set 0 for now
		int accessLevel = 0;// usrDTO.getRole().getAccessLevel();
		
		if ( accessLevel == GlobalConstants.SYSTEM_LEVEL )
		{
			return promptBeneficiarySearchSystemLevel ( mapping, form, request, response );
		}
		else if ( accessLevel == GlobalConstants.GROUP_LEVEL )
		{
			return promptBeneficiarySearchGroupLevel ( mapping, form, request, response );
		}
		return promptBeneficiarySearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward cancelSearchBeneficiary ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchBeneficiary() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
}
