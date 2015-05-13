/*
 * @ (#) UserDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.SearchUtil;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
import com.swiftcorp.portal.common.util.WebUtils;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.ForwardUtil;
import com.swiftcorp.portal.common.web.MessageKeys;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.geo.dto.GeoDTO;
import com.swiftcorp.portal.geo.service.IGeoService;
import com.swiftcorp.portal.group.dto.GroupDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.role.dto.RoleDTO;
import com.swiftcorp.portal.role.service.IRoleService;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.user.service.IUserService;

/*
 * @author swift corporation
 * @since mar 3, 2011
 */

public class UserDispatchAction extends DispatchAction
{
	
	protected static final Log log = LogFactory.getLog ( UserDispatchAction.class );
	
	//private IGeoService geoService;
	private IUserService userService;
	private IGroupService groupService;
	private ILoginService loginService;
	private IGeoService geoService;
	private long endUserRoleId;
	private IRoleService roleService;
	
	public void setRoleService ( IRoleService roleService )
	{
		this.roleService = roleService;
	}
	
	public void setLoginService ( ILoginService loginService )
	{
		this.loginService = loginService;
	}
	
	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public void setUserService ( IUserService userService )
	{
		this.userService = userService;
	}
	
	/*public void setGeoService ( IGeoService geoService )
	{
		this.geoService = geoService;
	}*/

	public void setEndUserRoleId ( long endUserRoleId )
	{
		this.endUserRoleId = endUserRoleId;
	}
	
	public ActionForward promptUserHomeUserLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptUserHomeUserLevel() : enter" );
		try
		{
			Long componentId = WebUtils.getComponentId ( request );
			log.info ( "promptUserHomeUserLevel() : componentId = " + componentId );
			request.setAttribute ( "componentId", componentId.toString () );
			UserDTO userDTO = (UserDTO) userService.get ( componentId );
			request.getSession ().setAttribute ( SESSION_KEYS.USER, userDTO );
		}
		catch (Exception e)
		{
			throw e;
		}
		log.info ( "promptUserHomeUserLevel() : Exit" );
		return mapping.findForward ( ForwardNames.USER_HOME );
	}
	
	public ActionForward promptUserSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptUserSearchSystemLevel() : enter" );
		try
		{
			UserSearchUtils.prepareSearchPage ( request );
			// here we want to load the user
			String searchSqlQuery = UserSearchUtils.prepareSqlQuery ( request );
			log.info ( "searchUserFromSystemLevel():: searchSqlQuery = " + searchSqlQuery );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = userService.search ( searchSqlQuery );
			log.info ( "searchUserFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.USER_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			UserSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptUserSearchSystemLevel() :", e );
			throw e;
		}
		// show the user search page
		return mapping.findForward ( ForwardNames.USER_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptExtUserSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
	throws Exception
	{
		log.info ( "promptUserSearchSystemLevel() : enter" );
		try
		{
			UserSearchUtils.prepareSearchPage ( request );
			// here we want to load the user
			String searchSqlQuery = UserSearchUtils.prepareSqlQuery ( request );
			log.info ( "searchUserFromSystemLevel():: searchSqlQuery = " + searchSqlQuery );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = userService.search ( searchSqlQuery );
			log.info ( "searchUserFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.USER_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			UserSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptUserSearchSystemLevel() :", e );
			throw e;
		}
		// show the user search page
		return mapping.findForward ( ForwardNames.EXT_USER_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptExtMailUserSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
	throws Exception
	{
		log.info ( "promptUserSearchSystemLevel() : enter" );
		try
		{
			UserSearchUtils.prepareSearchPage ( request );
			// here we want to load the user
			String searchSqlQuery = UserSearchUtils.prepareEmailSqlQuery(request);
			log.info ( "searchUserFromSystemLevel():: searchSqlQuery = " + searchSqlQuery );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = userService.search ( searchSqlQuery );
			log.info ( "searchUserFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.USER_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			UserSearchUtils.prepareMailSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptUserSearchSystemLevel() :", e );
			throw e;
		}
		// show the user search page
		return mapping.findForward ( ForwardNames.EXT_USER_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptUserSearchGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptUserSearchGroupLevel() : enter" );
		try
		{
			String searchSqlQuery = UserSearchUtils.prepareSqlQuery ( request );
			log.info ( "promptUserSearchGroupLevel():: searchSqlQuery = " + searchSqlQuery );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = userService.search ( searchSqlQuery );
			log.info ( "promptUserSearchGroupLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.USER_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			UserSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptUserSearchGroupLevel() :", e );
			throw e;
		}
		// show the user search page
		return mapping.findForward ( ForwardNames.USER_SEARCH_GROUP_LEVEL );
	}
	
	// search user from system level
	public ActionForward searchUserFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchUserFromSystemLevel():: Enter" );
		String searchSqlQuery = UserSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = userService.search ( searchSqlQuery );
		log.info ( "searchUserFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.USER_SEARCH_RESULT, searchOperationResult );
		UserSearchUtils.prepareSearchPage ( request );
		log.info ( "searchUserFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.USER_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchUserFromGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchUserFromGroupLevel():: Enter" );
		String groupId = request.getParameter ( "groupId" );
		
		log.info ( "promptUserSearchGroupLevel() : componentId = " + groupId );
		String searchSqlQuery = UserSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		SearchOperationResult searchOperationResult = userService.search ( searchSqlQuery );
		log.info ( "searchUserFromGroupLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.USER_SEARCH_RESULT, searchOperationResult );
		request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
		UserSearchUtils.prepareSearchPage ( request );
		log.info ( "searchUserFromGroupLevel()::Exit" );
		return mapping.findForward ( ForwardNames.USER_SEARCH_GROUP_LEVEL );
	}
	
	public ActionForward promptAddUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAddUserHome() : enter" );
		DynaValidatorActionForm userForm = (DynaValidatorActionForm) form;
		
		// this a assumption that 1 component Id will be a user role
		UserDTO user = new UserDTO ();
		// user.setRole(null);
		GroupDTO group = (GroupDTO) request.getSession ().getAttribute ( "group" );
		if ( group != null )
		{
			user.setGroupId ( group.getComponentId () );
		}
		userForm.set ( "user", user );
		// Add this for role DTO
		List<RoleDTO> availableList = roleService.getList ();
		request.getSession ().setAttribute ( SESSION_KEYS.ROLE_LIST, availableList );
		
		/*
		List<GeoDTO> geoCCList = geoService.getCCList ();
		List<GeoDTO> geoBranchList = geoService.getBranchList ();
		List<GeoDTO> geoRegionList = geoService.getRegionList ();
		
		
		request.getSession().setAttribute(SESSION_KEYS.GEO_LIST_CC,geoCCList);
		request.getSession ().setAttribute ( SESSION_KEYS.GEO_LIST_BRANCH, geoBranchList );
		request.getSession ().setAttribute ( SESSION_KEYS.GEO_LIST_REGION, geoRegionList );
		*/
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		return mapping.findForward ( ForwardNames.PROMPT_ADD_USER );
		
	}
	
	public ActionForward promptModifyUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws RuntimeException, Exception
	{
		log.info ( "promptModifyUser() : Enter" );
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptModifyUser() : componentId = " + componentId );
		UserDTO userDTO = (UserDTO) userService.get ( componentId );
		// userDTO.getRole()
		System.out.println ( "userDTO name:::::::::::::::::::::::::::::::::::::::::" + userDTO.getFirstName () );
		System.out.println ( "userDTO name:::::::::::::::::::::::::::::::::::::::::" + userDTO.getRole ().getComponentId () );
		request.getSession ().setAttribute ( SESSION_KEYS.USER_TO_MODIFY, userDTO );
		GroupDTO group = (GroupDTO) request.getSession ().getAttribute ( "group" );
		if ( group != null )
		{
			userDTO.setGroupId ( group.getComponentId () );
		}
		
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		
		dynaValidatorActionForm.set ( "user", userDTO );
		List<RoleDTO> availableList = roleService.getList ();
		request.getSession ().setAttribute ( SESSION_KEYS.ROLE_LIST, availableList );		
		/*
		List<GeoDTO> geoCCList = geoService.getCCList ();
		List<GeoDTO> geoBranchList = geoService.getBranchList ();
		List<GeoDTO> geoRegionList = geoService.getRegionList ();
		
		request.getSession().setAttribute(SESSION_KEYS.GEO_LIST_CC,geoCCList);
		request.getSession ().setAttribute ( SESSION_KEYS.GEO_LIST_BRANCH, geoBranchList );
		request.getSession ().setAttribute ( SESSION_KEYS.GEO_LIST_REGION, geoRegionList );
		*/
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.MODIFY_OPERATION );
		log.info ( "promptModifyUser() : Exit" );
		return mapping.findForward ( ForwardNames.PROMPT_MODIFY_USER );
		
	}
	
	public ActionForward addUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addUser() : enter" );
		DynaValidatorActionForm userForm = (DynaValidatorActionForm) form;
		UserDTO userDTO = new UserDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, userDTO);
		
		// get the role from the form
		String role = request.getParameter("rolename");
		long roleId = 0;
		if(role!=null && !role.equals("null") && role.length()>0)
		{
			roleId = Long.parseLong(role);
		}
		
		
		// now get the role dto from the database
		RoleDTO roleDTO = (RoleDTO) roleService.get ( roleId );
		
		if ( roleDTO == null )
		{
			throw new SystemException ( "Role is found null" );
		}
		System.out.println("userDTO.getUniqueCode ()::"+userDTO.getUniqueCode ());
		String uniqueCode = request.getParameter("uniqueCode");
		if(uniqueCode!=null && !uniqueCode.equals("null") && uniqueCode.length()>0)
		{
			userDTO.setUniqueCode(uniqueCode);
		}
		String[][] messageArgValues =
		{
			{
				userDTO.getUniqueCode ()
			}
		};
		
		GroupDTO group = (GroupDTO) request.getSession ().getAttribute ( SESSION_KEYS.GROUP );
		if ( group != null )
		{
			userDTO.setGroupId ( group.getComponentId () );
		}
		// RoleDTO roleDTO = (RoleDTO) roleService.get(endUserRoleId);
		userDTO.setRole ( roleDTO );
		log.info ( "getUserArea() : enter" );
		int userAreaType = userDTO.getAreaType ();
		long userAreaId = userDTO.getUserAreaId ();;
		
		
		System.out.println ("userAreaId:::::::::"+userAreaId);
		/*GeoDTO userArea = (GeoDTO) geoService.get ( userAreaId );
		userDTO.setUserArea ( userArea );*/
		//log.debug ( userArea.getName () );
		try
		{
			userService.add ( userDTO );
		}
		catch (Exception e)
		{
			log.info ( "userAdmin() : ", e );
			throw e;
		}
		
		// userService.add(userDTO);
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		return promptUserSearchGroupLevel ( mapping, form, request, response );
	}
	
	public ActionForward modifyUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifyUser() : Enter" );
		DynaValidatorActionForm userForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptModifyUser() : componentId = " + componentId );
		UserDTO userDTO = (UserDTO) userService.get ( componentId );
		String role = request.getParameter("role");
		long roleId = 0;
		if(role!=null && !role.equals("null") && role.length()>0)
		{
			roleId = Long.parseLong(role);
		}
		
		// now get the role dto from the database
		RoleDTO roleDTO = (RoleDTO) roleService.get ( roleId );
		
		if ( roleDTO == null )
		{
			throw new SystemException ( "Role is found null" );
		}
		
		String[][] messageArgValues =
		{
			{
				userDTO.getUniqueCode ()
			}
		};
		GroupDTO group = (GroupDTO) request.getSession ().getAttribute ( SESSION_KEYS.GROUP );
		if ( group != null )
		{
			userDTO.setGroupId ( group.getComponentId () );
		}
		
		// RoleDTO roleDTO = (RoleDTO) roleService.get(endUserRoleId);
		userDTO.setRole ( roleDTO );
		log.info ( "getUserArea() : enter modify" );
		int userAreaType = userDTO.getAreaType ();
		System.out.println ("userAreaType:::"+userAreaType);
		System.out.println ("userAreaid:::"+userDTO.getUserAreaId ());
		long userAreaId = userDTO.getUserAreaId ();
		
		
		System.out.println ("userAreaId:::::::::"+userAreaId);
		/*
		GeoDTO userArea = (GeoDTO) geoService.get ( userAreaId );
		userDTO.setUserArea ( userArea );
		*/
		try
		{
			userService.modify ( userDTO );
		}
		catch (Exception e)
		{
			log.info ( "userAdmin() : ", e );
			throw e;
		}
		
		WebUtils.setSuccessMessages ( request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues );
		return promptUserSearchGroupLevel ( mapping, form, request, response );
		
	}
	
	public ActionForward promptPasswordModifyUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws RuntimeException, Exception
	{
		log.info ( "promptPasswordModifyUser() : Enter" );
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptPasswordModifyUser() : componentId = " + componentId );
		UserDTO userDTO = (UserDTO) userService.get ( componentId );
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		dynaValidatorActionForm.set ( "user", userDTO );
		
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.PASSCHANGE_OPERATION );
		log.info ( "promptPasswordModifyUser() : Exit" );
		return mapping.findForward ( ForwardNames.PROMPT_PASS_MODIFY_USER );
	}
	
	/**
	 * Needs authenticated session to exist
	 */
	
	public ActionForward modifyUserPass ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifyUserPass() : Enter" );
		DynaValidatorActionForm userForm = (DynaValidatorActionForm) form;
		UserDTO userDTO = (UserDTO) userForm.get ( "user" );
		
		String[][] messageArgValues =
		{
			{
				"Passsword"
			}
		};
		userService.modify ( userDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
	public ActionForward removeUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "removeUser() : Enter" );
		DynaValidatorActionForm userForm = (DynaValidatorActionForm) form;
		// UserDTO userDTO = (UserDTO) userForm.get ( "user" );
		
		String[] userIdList = request.getParameterValues ( "deleteCheck" );
		String userRemoved = "";
		for ( int i = 0; i < userIdList.length; i++ )
		{
			log.info ( "userId to delete::" + userIdList[i] );
			String userComponentId = userIdList[i];
			if ( userComponentId != null && !userComponentId.equals ( "null" ) && userComponentId.length () > 0 )
			{
				UserDTO userDTO = (UserDTO) userService.get ( Long.parseLong ( userComponentId ) );
				userService.remove ( userDTO );
				if ( userRemoved != null && !userRemoved.equals ( "null" ) && userRemoved.length () > 0 )
				{
					userRemoved += "," + userDTO.getUniqueCode ();
				}
			}
			System.out.println ( "userId to delete::" + userIdList[i] );
		}
		System.out.println ( "userRemoved::" + userRemoved );
		String[][] messageArgValues =
		{
			{
				// userDTO.getUniqueCode ()
				"User" + userRemoved
			}
		};
		// userService.remove ( userDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.REMOVE_SUCCESS_MESSAGE_KEYS, messageArgValues );
		return promptUserSearchGroupLevel ( mapping, form, request, response );
	}
	
	public ActionForward cancelUserOperation ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "cancelUserOperation() :" );
		UserDTO userDTO = (UserDTO) request.getSession ().getAttribute ( SESSION_KEYS.USER );
		
		return promptUserSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward cancelSearchUser ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchUser() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}

}
