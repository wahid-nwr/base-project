/*
 * @ (#) AlertDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.alert.web;

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

import com.swiftcorp.portal.alert.AlertSuccessResult;
import com.swiftcorp.portal.alert.dto.AlertDTO;
import com.swiftcorp.portal.alert.service.IAlertService;
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

/**
 * @author soma
 * @since Sep 22, 2008
 */
public class AlertDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog ( AlertDispatchAction.class );
	@SuppressWarnings("unused")
	private IAlertService alertService;
	@SuppressWarnings("unused")
	private IGroupService groupService;
	
	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public void setAlertService ( IAlertService alertService )
	{
		this.alertService = alertService;
	}
	
	public ActionForward promptAlertSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAlertSearchSystemLevel() : enter" );
		try
		{
			AlertSearchUtils.prepareSearchPage ( request );
			String searchSqlQuery = AlertSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			SearchOperationResult searchOperationResult = alertService.search ( searchSqlQuery );
			log.info ( "searchAlertFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.ALERT_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			AlertSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptAlertSearchSystemLevel() :", e );
			throw e;
		}
		// show the alert search page
		return mapping.findForward ( ForwardNames.ALERT_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptAlertSearchByUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAlertSearchSystemLevel() : enter" );
		try
		{
			AlertSearchUtils.prepareSearchPage ( request );
			String searchSqlQuery = AlertSearchUtils.prepareSqlQueryByUser ( request );
			SearchUtil.prepareRequest ( request );
			SearchOperationResult searchOperationResult = alertService.search ( searchSqlQuery );
			log.info ( "searchAlertFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.ALERT_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			AlertSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptAlertSearchSystemLevel() :", e );
			throw e;
		}
		// show the alert search page
		return mapping.findForward ( ForwardNames.ALERT_SEARCH_BY_USER_POPUP );
	}
	
	public ActionForward promptAlertSearchGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAlertSearchGroupLevel() : enter" );
		try
		{
			// here we want to load the alert
			String searchSqlQuery = AlertSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = alertService.search ( searchSqlQuery );
			log.info ( "searchAlertFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.ALERT_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			AlertSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptAlertSearchGroupLevel() :", e );
			throw e;
		}
		// show the alert search page
		return mapping.findForward ( ForwardNames.ALERT_SEARCH_GROUP_LEVEL );
	}
	
	public ActionForward promptAddAlert ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAddAlertHome() : enter" );
		DynaValidatorActionForm alertForm = (DynaValidatorActionForm) form;
		alertForm.set ( "alert", new AlertDTO () );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		return mapping.findForward ( ForwardNames.PROMPT_ADD_ALERT );
	}
	
	public ActionForward addAlert ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addAlert() : Enter" );
		HttpSession session = request.getSession ();
		DynaValidatorActionForm alertForm = (DynaValidatorActionForm) form;
		AlertDTO alertDTO = (AlertDTO) alertForm.get ( "alert" );
		String[][] messageArgValues =
		{
			{
				alertDTO.getAlertId ()
			}
		};
		AlertSuccessResult result = (AlertSuccessResult) alertService.add ( alertDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "addAlert() : Exit" );
		return promptAlertSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward searchAlertFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchAlertFromSystemLevel():: Enter" );
		String searchSqlQuery = AlertSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = alertService.search ( searchSqlQuery );
		log.info ( "searchAlertFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.ALERT_SEARCH_RESULT, searchOperationResult );
		AlertSearchUtils.prepareSearchPage ( request );
		log.info ( "searchAlertFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.ALERT_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchAlertFromGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchAlertFromGroupLevel():: Enter" );
		String searchSqlQuery = AlertSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = alertService.search ( searchSqlQuery );
		log.info ( "searchAlertFromGroupLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		AlertSearchUtils.prepareSearchPage ( request );
		request.setAttribute ( SESSION_KEYS.ALERT_SEARCH_RESULT, searchOperationResult );
		log.info ( "searchAlertFromGroupLevel()::Exit" );
		return mapping.findForward ( ForwardNames.ALERT_SEARCH_GROUP_LEVEL );
	}
	
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyAlert ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws RuntimeException, Exception
	{
		log.info ( "promptModifyAlert() : Enter" );
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptModifyAlert() : componentId = " + componentId );
		AlertDTO alertDTO = (AlertDTO) alertService.get ( componentId );
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		dynaValidatorActionForm.set ( "alert", alertDTO );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.MODIFY_OPERATION );
		log.info ( "promptModifyAlert() : Exit" );
		return mapping.findForward ( ForwardNames.PROMPT_MODIFY_ALERT );
	}
	
	public ActionForward modifyAlert ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifyAlert() : Enter" );
		DynaValidatorActionForm alertForm = (DynaValidatorActionForm) form;
		AlertDTO alertDTO = (AlertDTO) alertForm.get ( "alert" );
		
		String[][] messageArgValues =
		{
			{
				alertDTO.getAlertId ()
			}
		};
		alertService.modify ( alertDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "modifyAlert() : Exit" );
		return promptAlertSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward removeAlert ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "removeAlert() : Enter" );
		DynaValidatorActionForm alertForm = (DynaValidatorActionForm) form;
		String[] alertIdList = request.getParameterValues ( "deleteCheck" );
		String alertsRemoved = "";
		for ( int i = 0; alertIdList != null && i < alertIdList.length; i++ )
		{
			log.info ( "alertId to delete::" + alertIdList[i] );
			String alertComponentId = alertIdList[i];
			if ( alertComponentId != null && !alertComponentId.equals ( "null" ) && alertComponentId.length () > 0 )
			{
				AlertDTO alertDTO = (AlertDTO) alertService.get ( Long.parseLong ( alertComponentId ) );
				alertService.remove ( alertDTO );
				if ( alertsRemoved != null && !alertsRemoved.equals ( "null" ) && alertsRemoved.length () > 0 )
				{
					alertsRemoved += "," + alertDTO.getAlertId ();
				}
				else
				{
					alertsRemoved += alertDTO.getAlertId ();
				}
			}
			System.out.println ( "alertId to delete::" + alertIdList[i] );
		}
		
		System.out.println ( "alertsRemoved::" + alertsRemoved );
		String[][] messageArgValues =
		{
			{
				"Alert " + alertsRemoved
			}
		};
		// SearchUtil.prepareRequest ( request );
		// AlertSearchUtils.prepareSearchPage ( request );
		WebUtils.setSuccessMessages ( request, MessageKeys.REMOVE_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "removeAlert() : Exit" );
		// return mapping.findForward ( ForwardNames.ALERT_SEARCH_SYSTEM_LEVEL
		// );
		// return mapping.findForward ( ForwardNames.ALERT_HOME );
		return promptAlertSearchSystemLevel ( mapping, form, request, response );
	}
	
	/*
	 * public ActionForward cancelAlertOperation(ActionMapping
	 * mapping,ActionForm form,HttpServletRequest request,HttpServletResponse
	 * response) throws Exception { log.info("cancelAlertOperation() :");
	 * UserDTO usrDTO =
	 * (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER); int
	 * accessLevel = usrDTO.getRole().getAccessLevel();
	 * 
	 * if(accessLevel == GlobalConstants.SYSTEM_LEVEL) { return
	 * promptAlertSearchSystemLevel(mapping,form,request,response); } else
	 * if(accessLevel == GlobalConstants.GROUP_LEVEL) { return
	 * promptAlertSearchGroupLevel(mapping,form,request,response); } return
	 * promptAlertSearchSystemLevel(mapping,form,request,response); }
	 */

	public ActionForward cancelSearchAlert ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchAlert() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
}
