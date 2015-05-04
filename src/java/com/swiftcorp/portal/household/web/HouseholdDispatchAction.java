/*
 * @ (#) HouseholdDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.household.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorActionForm;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.SearchUtil;
import com.swiftcorp.portal.common.util.WebUtils;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.ForwardUtil;
import com.swiftcorp.portal.common.web.MessageKeys;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.household.HouseholdSuccessResult;
import com.swiftcorp.portal.household.dto.HouseholdDTO;
import com.swiftcorp.portal.household.service.IHouseholdService;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author soma
 * @since Sep 22, 2008
 */
public class HouseholdDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog ( HouseholdDispatchAction.class );
	@SuppressWarnings("unused")
	private IHouseholdService householdService;
	@SuppressWarnings("unused")
	private IGroupService groupService;
	
	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public void setHouseholdService ( IHouseholdService householdService )
	{
		this.householdService = householdService;
	}
	
	public ActionForward promptHouseholdSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptHouseholdSearchSystemLevel() : enter" );
		try
		{
			HouseholdSearchUtils.prepareSearchPage ( request );
			String searchSqlQuery = HouseholdSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			SearchOperationResult searchOperationResult = householdService.search ( searchSqlQuery );
			log.info ( "searchHouseholdFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.HOUSEHOLD_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			HouseholdSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptHouseholdSearchSystemLevel() :", e );
			throw e;
		}
		// show the household search page
		return mapping.findForward ( ForwardNames.HOUSEHOLD_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptHouseholdSearchGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptHouseholdSearchGroupLevel() : enter" );
		try
		{
			// here we want to load the household
			String searchSqlQuery = HouseholdSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = householdService.search ( searchSqlQuery );
			log.info ( "searchHouseholdFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.HOUSEHOLD_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			HouseholdSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptHouseholdSearchGroupLevel() :", e );
			throw e;
		}
		// show the household search page
		return mapping.findForward ( ForwardNames.HOUSEHOLD_SEARCH_GROUP_LEVEL );
	}
	
	public ActionForward promptAddHousehold ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAddHouseholdHome() : enter" );
		DynaValidatorActionForm householdForm = (DynaValidatorActionForm) form;
		householdForm.set ( "household", new HouseholdDTO () );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		return mapping.findForward ( ForwardNames.PROMPT_ADD_HOUSEHOLD );
	}
	
	public ActionForward addHousehold ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addHousehold() : Enter" );
		HttpSession session = request.getSession ();
		DynaValidatorActionForm householdForm = (DynaValidatorActionForm) form;
		HouseholdDTO householdDTO = (HouseholdDTO) householdForm.get ( "household" );
		String[][] messageArgValues =
		{
			{
				householdDTO.getUniqueCode ()
			}
		};
		HouseholdSuccessResult result = (HouseholdSuccessResult) householdService.add ( householdDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "addHousehold() : Exit" );
		return promptHouseholdSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward searchHouseholdFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchHouseholdFromSystemLevel():: Enter" );
		String searchSqlQuery = HouseholdSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = householdService.search ( searchSqlQuery );
		log.info ( "searchHouseholdFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.HOUSEHOLD_SEARCH_RESULT, searchOperationResult );
		HouseholdSearchUtils.prepareSearchPage ( request );
		log.info ( "searchHouseholdFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.HOUSEHOLD_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchHouseholdFromGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchHouseholdFromGroupLevel():: Enter" );
		String searchSqlQuery = HouseholdSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = householdService.search ( searchSqlQuery );
		log.info ( "searchHouseholdFromGroupLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		HouseholdSearchUtils.prepareSearchPage ( request );
		request.setAttribute ( SESSION_KEYS.HOUSEHOLD_SEARCH_RESULT, searchOperationResult );
		log.info ( "searchHouseholdFromGroupLevel()::Exit" );
		return mapping.findForward ( ForwardNames.HOUSEHOLD_SEARCH_GROUP_LEVEL );
	}
	
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyHousehold ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws RuntimeException, Exception
	{
		log.info ( "promptModifyHousehold() : Enter" );
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptModifyHousehold() : componentId = " + componentId );
		HouseholdDTO householdDTO = (HouseholdDTO) householdService.get ( componentId );
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		dynaValidatorActionForm.set ( "household", householdDTO );
		request.getSession ().setAttribute ( SESSION_KEYS.HOUSEHOLD_TO_MODIFY, householdDTO );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.MODIFY_OPERATION );
		log.info ( "promptModifyHousehold() : Exit" );
		return mapping.findForward ( ForwardNames.PROMPT_MODIFY_HOUSEHOLD );
	}
	
	public ActionForward modifyHousehold ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifyHousehold() : Enter" );
		DynaValidatorActionForm householdForm = (DynaValidatorActionForm) form;
		HouseholdDTO householdDTO = (HouseholdDTO) householdForm.get ( "household" );
		
		String[][] messageArgValues =
		{
			{
				householdDTO.getUniqueCode ()
			}
		};
		householdService.modify ( householdDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "modifyHousehold() : Exit" );
		return promptHouseholdSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward cancelHouseholdOperation ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "cancelHouseholdOperation() :" );
		UserDTO usrDTO = (UserDTO) request.getSession ().getAttribute ( SESSION_KEYS.USER );
		
		return promptHouseholdSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward cancelSearchHousehold ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchHousehold() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
}
