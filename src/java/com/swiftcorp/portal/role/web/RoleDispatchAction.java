/*
 * @ (#) RoleDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.role.web;

import java.util.List;

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
import com.swiftcorp.portal.common.ViewValueDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.SearchUtil;
import com.swiftcorp.portal.common.util.WebUtils;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.ForwardUtil;
import com.swiftcorp.portal.common.web.MessageKeys;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.role.dto.RoleDTO;
import com.swiftcorp.portal.role.service.IRoleService;
import com.swiftcorp.portal.user.dto.UserDTO;

/*
 * @author swift corporation
 * @since mar 3, 2011
 */

public class RoleDispatchAction extends DispatchAction
{
	
	protected static final Log log = LogFactory.getLog ( RoleDispatchAction.class );
	
	private IRoleService roleService;
	@SuppressWarnings("unused")
	private IGroupService groupService;
	@SuppressWarnings("unused")
	private ILoginService loginService;
	
	public void setLoginService ( ILoginService loginService )
	{
		this.loginService = loginService;
	}
	
	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public void setRoleService ( IRoleService roleService )
	{
		this.roleService = roleService;
	}
	
	public ActionForward promptRoleSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptRoleSearchSystemLevel() : enter" );
		try
		{
			RoleSearchUtils.prepareSearchPage ( request );
			// here we want to load the role
			String searchSqlQuery = RoleSearchUtils.prepareSqlQuery ( request );
			log.info ( "searchRoleFromSystemLevel():: searchSqlQuery = " + searchSqlQuery );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = roleService.search ( searchSqlQuery );
			log.info ( "searchRoleFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.ROLE_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			RoleSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptRoleSearchSystemLevel() :", e );
			throw e;
		}
		// show the role search page
		return mapping.findForward ( ForwardNames.ROLE_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptRoleSearchGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptRoleSearchGroupLevel() : enter" );
		try
		{
			// here we want to load the role
			String searchSqlQuery = RoleSearchUtils.prepareSqlQuery ( request );
			log.info ( "searchRoleFromSystemLevel():: searchSqlQuery = " + searchSqlQuery );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = roleService.search ( searchSqlQuery );
			log.info ( "searchRoleFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.ROLE_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			RoleSearchUtils.prepareSearchPage ( request );
			
			String groupId = request.getParameter ( "groupId" );
			log.info ( "promptRoleSearchGroupLevel() : groupId = " + groupId );
			request.setAttribute ( "groupId", groupId );
		}
		catch (Exception e)
		{
			log.info ( "promptRoleSearchGroupLevel() :", e );
			throw e;
		}
		// show the role search page
		return mapping.findForward ( ForwardNames.ROLE_SEARCH_GROUP_LEVEL );
	}
	
	public ActionForward promptRoleHomeSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptRoleHomeSystemLevel() : enter" );
		try
		{
			Long componentId = WebUtils.getComponentId ( request );
			log.info ( "promptRoleHomeSystemLevel() : componentId = " + componentId );
			request.setAttribute ( "componentId", componentId.toString () );
			RoleDTO roleDTO = (RoleDTO) roleService.get ( componentId );
			if ( roleDTO == null )
			{
				log.info ( "promptRoleHomeSystemLevel() : : Failed to load Role" );
			}
			
			request.getSession ().setAttribute ( SESSION_KEYS.ROLE, roleDTO );
		}
		catch (Exception e)
		{
			log.info ( "promptRoleHomeSystemLevel() : componentId = ", e );
			throw e;
		}
		log.info ( "promptRoleHomeSystemLevel() : Exit" );
		return mapping.findForward ( ForwardNames.ROLE_HOME_SYSTEM_LEVEL );
	}
	
	public ActionForward promptAddRole ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAddRoleHome() : enter" );
		DynaValidatorActionForm roleForm = (DynaValidatorActionForm) form;
		roleForm.set ( "role", new RoleDTO () );
		List<ViewValueDTO> availableList = RoleSearchUtils.getAccessLevelList ();
		request.getSession ().setAttribute ( SESSION_KEYS.ACCESS_LEVEL_LIST, availableList );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		return mapping.findForward ( ForwardNames.PROMPT_ADD_ROLE );
	}
	
	public ActionForward addRole ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addRole() : enter" );
		@SuppressWarnings("unused")
		HttpSession session = request.getSession ();
		DynaValidatorActionForm roleForm = (DynaValidatorActionForm) form;
		RoleDTO roleDTO = (RoleDTO) roleForm.get ( "role" );
		
		String[][] messageArgValues =
		{
			{
				"Role"
			}
		};
		roleService.add ( roleDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		String isStay = request.getParameter ( "isStay" );
		log.info ( "addRole() : exit" );
		if ( "true".equals ( isStay ) )
		{
			return mapping.findForward ( ForwardNames.PROMPT_ADD_ROLE );
		}
		else
		{
			return promptRoleSearchSystemLevel ( mapping, form, request, response );
		}
	}
	
	public ActionForward searchRoleFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchRoleFromSystemLevel():: Enter" );
		String searchSqlQuery = RoleSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = roleService.search ( searchSqlQuery );
		log.info ( "searchRoleFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.ROLE_SEARCH_RESULT, searchOperationResult );
		RoleSearchUtils.prepareSearchPage ( request );
		log.info ( "searchRoleFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.ROLE_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchRoleFromGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchRoleFromGroupLevel():: Enter" );
		String searchSqlQuery = RoleSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = roleService.search ( searchSqlQuery );
		log.info ( "searchRoleFromGroupLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		RoleSearchUtils.prepareSearchPage ( request );
		request.setAttribute ( SESSION_KEYS.ROLE_SEARCH_RESULT, searchOperationResult );
		log.info ( "searchRoleFromGroupLevel()::Exit" );
		return mapping.findForward ( ForwardNames.ROLE_SEARCH_GROUP_LEVEL );
	}
	
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyRole ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws RuntimeException, Exception
	{
		log.info ( "promptModifyRole() : Enter" );
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptModifyRole() : componentId = " + componentId );
		RoleDTO roleDTO = (RoleDTO) roleService.get ( componentId );
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		dynaValidatorActionForm.set ( "role", roleDTO );
		List<ViewValueDTO> availableList = RoleSearchUtils.getAccessLevelList ();
		request.getSession ().setAttribute ( SESSION_KEYS.ACCESS_LEVEL_LIST, availableList );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.MODIFY_OPERATION );
		log.info ( "promptModifyRole() : Exit" );
		return mapping.findForward ( ForwardNames.PROMPT_MODIFY_ROLE );
	}
	
	public ActionForward modifyRole ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifyRole() : Enter" );
		DynaValidatorActionForm roleForm = (DynaValidatorActionForm) form;
		RoleDTO roleDTO = (RoleDTO) roleForm.get ( "role" );
		String[][] messageArgValues =
		{
			{
				roleDTO.getUniqueCode ()
			}
		};
		roleService.modify ( roleDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues );
		String isStay = request.getParameter ( "isStay" );
		
		log.info ( "modifyRole() : exit" );
		
		if ( "true".equals ( isStay ) )
		{
			request.setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.MODIFY_OPERATION );
			return mapping.findForward ( ForwardNames.PROMPT_MODIFY_ROLE );
		}
		else
		{
			return promptRoleSearchSystemLevel ( mapping, form, request, response );
		}
		
	}
	
	public ActionForward getRoleList ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
	throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "getRoleList() : Enter" );
		DynaValidatorActionForm roleForm = (DynaValidatorActionForm) form;
		List<RoleDTO> roleDTOList = null;
		
		roleDTOList = roleService.getList();
		request.setAttribute("roleList", roleDTOList);
		log.info ( "getRoleList() : Exit" );
		return mapping.findForward ( ForwardNames.GET_ROLE_LIST );			
	}
	
	public ActionForward cancelRoleOperation ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "cancelGroupOperation() :" );
		UserDTO userDTO = (UserDTO) request.getSession ().getAttribute ( SESSION_KEYS.USER );
		
		return promptRoleSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward cancelSearchRole ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchRole() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
}
