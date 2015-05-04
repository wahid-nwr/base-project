/*
 * @ (#) ScheduleDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.schedule.web;

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
import com.swiftcorp.portal.schedule.ScheduleSuccessResult;
import com.swiftcorp.portal.schedule.dto.ScheduleDTO;
import com.swiftcorp.portal.schedule.service.IScheduleService;

/**
 * @author soma
 * @since Sep 22, 2008
 */
public class ScheduleDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog ( ScheduleDispatchAction.class );
	@SuppressWarnings("unused")
	private IScheduleService scheduleService;
	@SuppressWarnings("unused")
	private IGroupService groupService;
	
	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public void setScheduleService ( IScheduleService scheduleService )
	{
		this.scheduleService = scheduleService;
	}
	
	public ActionForward promptScheduleSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptScheduleSearchSystemLevel() : enter" );
		try
		{
			ScheduleSearchUtils.prepareSearchPage ( request );
			String searchSqlQuery = ScheduleSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			SearchOperationResult searchOperationResult = scheduleService.search ( searchSqlQuery );
			log.info ( "searchScheduleFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.SCHEDULE_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			ScheduleSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptScheduleSearchSystemLevel() :", e );
			throw e;
		}
		// show the schedule search page
		return mapping.findForward ( ForwardNames.SCHEDULE_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptScheduleSearchGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptScheduleSearchGroupLevel() : enter" );
		try
		{
			// here we want to load the schedule
			String searchSqlQuery = ScheduleSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = scheduleService.search ( searchSqlQuery );
			log.info ( "searchScheduleFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.SCHEDULE_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			ScheduleSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptScheduleSearchGroupLevel() :", e );
			throw e;
		}
		// show the schedule search page
		return mapping.findForward ( ForwardNames.SCHEDULE_SEARCH_GROUP_LEVEL );
	}
	
	public ActionForward promptAddSchedule ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAddScheduleHome() : enter" );
		DynaValidatorActionForm scheduleForm = (DynaValidatorActionForm) form;
		scheduleForm.set ( "schedule", new ScheduleDTO () );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		return mapping.findForward ( ForwardNames.PROMPT_ADD_SCHEDULE );
	}
	
	public ActionForward addSchedule ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addSchedule() : Enter" );
		HttpSession session = request.getSession ();
		DynaValidatorActionForm scheduleForm = (DynaValidatorActionForm) form;
		ScheduleDTO scheduleDTO = (ScheduleDTO) scheduleForm.get ( "schedule" );
		String[][] messageArgValues =
		{
			{
				scheduleDTO.getUniqueCode ()
			}
		};
		ScheduleSuccessResult result = (ScheduleSuccessResult) scheduleService.add ( scheduleDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "addSchedule() : Exit" );
		return promptScheduleSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward searchScheduleFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchScheduleFromSystemLevel():: Enter" );
		String searchSqlQuery = ScheduleSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = scheduleService.search ( searchSqlQuery );
		log.info ( "searchScheduleFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.SCHEDULE_SEARCH_RESULT, searchOperationResult );
		ScheduleSearchUtils.prepareSearchPage ( request );
		log.info ( "searchScheduleFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.SCHEDULE_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchScheduleFromGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchScheduleFromGroupLevel():: Enter" );
		String searchSqlQuery = ScheduleSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = scheduleService.search ( searchSqlQuery );
		log.info ( "searchScheduleFromGroupLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		ScheduleSearchUtils.prepareSearchPage ( request );
		request.setAttribute ( SESSION_KEYS.SCHEDULE_SEARCH_RESULT, searchOperationResult );
		log.info ( "searchScheduleFromGroupLevel()::Exit" );
		return mapping.findForward ( ForwardNames.SCHEDULE_SEARCH_GROUP_LEVEL );
	}
	
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifySchedule ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws RuntimeException, Exception
	{
		log.info ( "promptModifySchedule() : Enter" );
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptModifySchedule() : componentId = " + componentId );
		ScheduleDTO scheduleDTO = (ScheduleDTO) scheduleService.get ( componentId );
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		dynaValidatorActionForm.set ( "schedule", scheduleDTO );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.MODIFY_OPERATION );
		log.info ( "promptModifySchedule() : Exit" );
		return mapping.findForward ( ForwardNames.PROMPT_MODIFY_SCHEDULE );
	}
	
	public ActionForward modifySchedule ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifySchedule() : Enter" );
		DynaValidatorActionForm scheduleForm = (DynaValidatorActionForm) form;
		ScheduleDTO scheduleDTO = (ScheduleDTO) scheduleForm.get ( "schedule" );
		
		String[][] messageArgValues =
		{
			{
				scheduleDTO.getUniqueCode ()
			}
		};
		scheduleService.modify ( scheduleDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "modifySchedule() : Exit" );
		return promptScheduleSearchSystemLevel ( mapping, form, request, response );
	}
	
	/*
	 * public ActionForward cancelScheduleOperation(ActionMapping
	 * mapping,ActionForm form,HttpServletRequest request,HttpServletResponse
	 * response) throws Exception { log.info("cancelScheduleOperation() :");
	 * UserDTO usrDTO =
	 * (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER); int
	 * accessLevel = usrDTO.getRole().getAccessLevel();
	 * 
	 * if(accessLevel == GlobalConstants.SYSTEM_LEVEL) { return
	 * promptScheduleSearchSystemLevel(mapping,form,request,response); } else
	 * if(accessLevel == GlobalConstants.GROUP_LEVEL) { return
	 * promptScheduleSearchGroupLevel(mapping,form,request,response); } return
	 * promptScheduleSearchSystemLevel(mapping,form,request,response); }
	 */

	public ActionForward cancelSearchSchedule ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchSchedule() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
}
