/*
 * @ (#) GroupDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.group.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.DynaValidatorActionForm;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.SearchUtil;
import com.swiftcorp.portal.common.util.WebUtils;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.ForwardUtil;
import com.swiftcorp.portal.common.web.GenericDispatchAction;
import com.swiftcorp.portal.common.web.MessageKeys;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.group.dto.GroupDTO;
import com.swiftcorp.portal.group.service.IGroupService;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class GroupDispatchAction extends GenericDispatchAction
{
	@SuppressWarnings("unused")
	private static final Log theLogger = LogFactory.getLog ( GroupDispatchAction.class );
	
	IGroupService groupService;
	
	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public ActionForward promptGroupHomeGroupAdmin ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptGroupHomeGroupLevel() : enter" );
		try
		{
			Long componentId = WebUtils.getComponentId ( request );
			log.info ( "promptGroupHomeGroupLevel() : componentId = " + componentId );
			request.setAttribute ( "componentId", componentId.toString () );
			GroupDTO groupDTO = (GroupDTO) groupService.get ( componentId );
			request.getSession ().setAttribute ( SESSION_KEYS.GROUP, groupDTO );
			request.getSession ().setAttribute ( SESSION_KEYS.WORKING_LEVEL, GlobalConstants.GROUP_LEVEL );
		}
		catch (Exception e)
		{
			throw e;
		}
		log.info ( "promptGroupHomeGroupLevel() : Exit" );
		return mapping.findForward ( ForwardNames.GROUP_ADMIN_HOME );
	}
	
	public ActionForward promptGroupHomeSystemAdmin ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptGroupHomeSystemLevel() : enter" );
		try
		{
			Long componentId = WebUtils.getComponentId ( request );
			log.info ( "promptGroupHomeSystemLevel() : componentId = " + componentId );
			request.setAttribute ( "componentId", componentId.toString () );
			GroupDTO groupDTO = (GroupDTO) groupService.get ( componentId );
			
			request.getSession ().setAttribute ( SESSION_KEYS.GROUP, groupDTO );
			request.getSession ().setAttribute ( SESSION_KEYS.WORKING_LEVEL, GlobalConstants.GROUP_LEVEL );
		}
		catch (Exception e)
		{
			throw e;
		}
		log.info ( "promptGroupHomeSystemLevel() : Exit" );
		return mapping.findForward ( ForwardNames.GROUP_HOME_SYSTEM_ADMIN );
	}
	
	public ActionForward promptGroupSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptGroupSearchSystemLevel() : enter" );
		try
		{
			GroupSearchUtils.prepareSearchPage ( request );
			// here we want to load the group
			String searchSqlQuery = GroupSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = groupService.search ( searchSqlQuery );
			log.info ( "searchGroupFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.GROUP_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			GroupSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptGroupSearchSystemLevel() :", e );
			throw e;
		}
		// show the group search page
		return mapping.findForward ( ForwardNames.GROUP_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptGroupSearchGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptGroupSearchGroupLevel() : enter" );
		try
		{
			// here we want to load the group
			String searchSqlQuery = GroupSearchUtils.prepareSqlQuery ( request );
			log.info ( "searchGroupFromSystemLevel():: searchSqlQuery = " + searchSqlQuery );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = groupService.search ( searchSqlQuery );
			log.info ( "searchGroupFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.GROUP_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			GroupSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptGroupSearchGroupLevel() :", e );
			throw e;
		}
		// show the group search page
		return mapping.findForward ( ForwardNames.GROUP_SEARCH_GROUP_LEVEL );
	}
	
	public ActionForward promptAddGroup ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAddGroupHome() : enter" );
		DynaValidatorActionForm groupForm = (DynaValidatorActionForm) form;
		groupForm.set ( "group", new GroupDTO () );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		return mapping.findForward ( ForwardNames.PROMPT_ADD_GROUP );
	}
	
	public ActionForward addGroup ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addGroup() : enter" );
		DynaValidatorActionForm groupForm = (DynaValidatorActionForm) form;
		GroupDTO groupDTO = (GroupDTO) groupForm.get ( "group" );
		
		String[][] messageArgValues =
		{
			{
				groupDTO.getUniqueCode ()
			}
		};
		groupService.add ( groupDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "addGroup() : exit" );
		return promptGroupSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward searchGroupFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchGroupFromSystemLevel():: Enter" );
		String searchSqlQuery = GroupSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = groupService.search ( searchSqlQuery );
		log.info ( "searchGroupFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.GROUP_SEARCH_RESULT, searchOperationResult );
		GroupSearchUtils.prepareSearchPage ( request );
		log.info ( "searchGroupFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.GROUP_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchGroupFromGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchGroupFromGroupLevel():: Enter" );
		String searchSqlQuery = GroupSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = groupService.search ( searchSqlQuery );
		log.info ( "searchGroupFromGroupLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		GroupSearchUtils.prepareSearchPage ( request );
		request.setAttribute ( SESSION_KEYS.GROUP_SEARCH_RESULT, searchOperationResult );
		log.info ( "searchGroupFromGroupLevel()::Exit" );
		return mapping.findForward ( ForwardNames.GROUP_SEARCH_GROUP_LEVEL );
	}
	
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyGroup ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws RuntimeException, Exception
	{
		log.info ( "promptModifyGroup() : Enter" );
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptModifyGroup() : componentId = " + componentId );
		GroupDTO groupDTO = (GroupDTO) groupService.get ( componentId );
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		dynaValidatorActionForm.set ( "group", groupDTO );
		
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.MODIFY_OPERATION );
		log.info ( "promptModifyGroup() : Exit" );
		return mapping.findForward ( ForwardNames.PROMPT_MODIFY_GROUP );
	}
	
	public ActionForward modifyGroup ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifyGroup() : Enter" );
		DynaValidatorActionForm groupForm = (DynaValidatorActionForm) form;
		GroupDTO groupDTO = (GroupDTO) groupForm.get ( "group" );
		String[][] messageArgValues =
		{
			{
				groupDTO.getUniqueCode ()
			}
		};
		
		groupService.modify ( groupDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "modifyGroup() : exit" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
	public ActionForward cancelSearchGroup ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchGroup() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
}
