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

import java.util.Enumeration;
import java.util.List;
import java.util.Set;

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

import com.swiftcorp.portal.common.dto.FunctionDTO;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.ViewValueDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.SearchUtil;
import com.swiftcorp.portal.common.service.IFunctionService;
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

public class RoleFunctionDispatchAction extends DispatchAction
{
	
	protected static final Log log = LogFactory.getLog ( RoleFunctionDispatchAction.class );
	
	// function service
	private IFunctionService functionService;
	
	private IRoleService roleService;
	@SuppressWarnings("unused")
	private IGroupService groupService;
	@SuppressWarnings("unused")
	private ILoginService loginService;
	
	private String[] selFunction =
	{};
	
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
	
	@Deprecated
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
	
	public ActionForward getFunctions ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "getFunctions() : enter" );
		
		@SuppressWarnings("unused")
		HttpSession session = request.getSession ();
		
		// Add this for role DTO
		List<RoleDTO> roleList = roleService.getList ();
		session.setAttribute ( SESSION_KEYS.ROLE_LIST, roleList );
		
		// get the function list
		List<FunctionDTO> functionDTOList = functionService.getList ();
		session.setAttribute ( SESSION_KEYS.FUNCTIONDTO_LIST, functionDTOList );
		session.setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		log.info ( "getFunctions(): Generate the functionlist and put it to the request" );
		return mapping.findForward ( ForwardNames.SHOW_FUNCTIONS );
		/*
		 * DynaValidatorActionForm roleForm = (DynaValidatorActionForm) form;
		 * RoleDTO roleDTO = (RoleDTO) roleForm.get("role");
		 * 
		 * String[][] messageArgValues = { { "Role" } };
		 * roleService.add(roleDTO); WebUtils.setSuccessMessages(request,
		 * MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues); String
		 * isStay = request.getParameter("isStay");
		 * log.info("addRole() : exit"); if ("true".equals(isStay)) { return
		 * mapping.findForward(ForwardNames.PROMPT_ADD_ROLE); } else { return
		 * null; //return promptRoleSearchSystemLevel(mapping, form, request,
		 * response); }
		 */
	}
	
	public ActionForward getExtFunctions ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
	throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "getFunctions() : enter" );
		
		@SuppressWarnings("unused")
		HttpSession session = request.getSession ();
		
		// Add this for role DTO
		List<RoleDTO> roleList = roleService.getList ();
		session.setAttribute ( SESSION_KEYS.ROLE_LIST, roleList );
		
		// get the function list
		List<FunctionDTO> functionDTOList = functionService.getList ();
		session.setAttribute ( SESSION_KEYS.FUNCTIONDTO_LIST, functionDTOList );
		session.setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		log.info ( "getFunctions(): Generate the functionlist and put it to the request" );
		return mapping.findForward ( ForwardNames.GET_ROLE_FUNCTION_LIST );		
	}
	
	public ActionForward getRoleFunctions ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
	throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addRoleFunctions() : enter" );
		
		@SuppressWarnings("unused")
		
		HttpSession session = request.getSession ();
		System.out.println("in role function");
		DynaValidatorActionForm roleFunctionForm = (DynaValidatorActionForm) form;
		long componentId = Long.parseLong ( (String) request.getParameter ( "role" ) );
		log.info ( "Role from Ui" + roleFunctionForm.get ( "role" ) );
		
		// now get the role dto from the database
		RoleDTO roleDTO = (RoleDTO) roleService.get ( componentId );// (RoleDTO)roleFunctionForm.get
		Set<FunctionDTO> functionSet = roleDTO.getFunctions();
		request.setAttribute("functionsSet", functionSet);
		return mapping.findForward ( ForwardNames.GET_ROLE_FUNCTIONS_LIST );
	}
	
	public ActionForward addRoleFunctions ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addRoleFunctions() : enter" );
		
		@SuppressWarnings("unused")
		
		HttpSession session = request.getSession ();
		System.out.println("in role function");
		DynaValidatorActionForm roleFunctionForm = (DynaValidatorActionForm) form;
		long componentId = Long.parseLong ( (String) roleFunctionForm.get ( "role" ) );
		log.info ( "Role from Ui" + roleFunctionForm.get ( "role" ) );
		
		// now get the role dto from the database
		RoleDTO roleDTO = (RoleDTO) roleService.get ( componentId );// (RoleDTO)roleFunctionForm.get
																	// ( "role"
																	// );
		
		String checkedIndex = (String) roleFunctionForm.get ( "checkedIndex" );
		String[] functionIndexArray = checkedIndex.split ( "," );
		List<FunctionDTO> functionDTOList = (List<FunctionDTO>) session.getAttribute ( SESSION_KEYS.FUNCTIONDTO_LIST );
		// get the function from the role dto
		Set<FunctionDTO> functionSet = null;
		if ( roleDTO != null )
		{
			functionSet = roleDTO.getFunctions ();
			// remove existing functions from the list
			functionSet.removeAll ( functionSet );
		}
		
		for ( int i = 0; i < functionIndexArray.length; i++ )
		{
			int index = -1;
			if ( functionIndexArray[i] != null && !(functionIndexArray[i]).equals ( "null" ) && (functionIndexArray[i]).length () > 0 )
			{
				index = Integer.parseInt ( functionIndexArray[i] );
				log.debug ( "functionIndex are " + functionIndexArray[i] );
				FunctionDTO functionDTOFromList = functionDTOList.get ( index );
				functionDTOFromList.getRoles ().add ( functionDTOFromList );
				functionSet.add ( functionDTOFromList );
			}
		}
		
		FunctionDTO functionDTO = new FunctionDTO ();
		String[][] messageArgValues =
		{
			{
				roleDTO.getDescription ()
			}
		};
		roleDTO.setFunctions ( functionSet );
		// now save the role dto
		roleService.modify ( roleDTO );
		// get modified role list
		List<RoleDTO> roleList = roleService.getList ();
		// put role list to session
		session.setAttribute ( SESSION_KEYS.ROLE_LIST, roleList );
		log.info ( "Save the role and Functions" );
		WebUtils.setSuccessMessages ( request, MessageKeys.ASSIGN_SUCCESS_MESSAGE_KEYS, messageArgValues );
		return mapping.findForward ( ForwardNames.SHOW_FUNCTIONS );
		
	}
	
	public ActionForward modifyRoleFunctions ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
	throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addRoleFunctions() : enter" );
		
		@SuppressWarnings("unused")
		
		HttpSession session = request.getSession ();
		System.out.println("in role function");
		
		long componentId = Long.parseLong ( (String) request.getParameter ( "role" ) );
		
		
		// now get the role dto from the database
		RoleDTO roleDTO = (RoleDTO) roleService.get ( componentId );// (RoleDTO)roleFunctionForm.get
																	// ( "role"
																	// );
		Enumeration<String> en = request.getParameterNames();
		
		List<FunctionDTO> functionDTOList = (List<FunctionDTO>) session.getAttribute ( SESSION_KEYS.FUNCTIONDTO_LIST );
		// get the function from the role dto
		Set<FunctionDTO> functionSet = null;
		if ( roleDTO != null )
		{
			functionSet = roleDTO.getFunctions ();
			// remove existing functions from the list
			functionSet.removeAll ( functionSet );
		}
		String key = "";
		String value = "";
		while(en.hasMoreElements())
		{
			key = en.nextElement();
			if(key.startsWith("cb-"))
			{
				int index = -1;
				value = key.substring(3,key.length());
				index = Integer.parseInt ( value );
				log.debug ( "functionIndex are " + value );
				FunctionDTO functionDTOFromList = null;
				for(FunctionDTO dto:functionDTOList)
				{
					if((""+dto.getComponentId()).equals(""+(value)))
					{
						functionDTOFromList = dto;
						break;
					}
				}
				if(functionDTOFromList!=null)
				{
					functionDTOFromList.getRoles ().add ( functionDTOFromList );
					functionSet.add ( functionDTOFromList );
				}
				
			}
		}
		
		FunctionDTO functionDTO = new FunctionDTO ();
		String[][] messageArgValues =
		{
			{
				roleDTO.getDescription ()
			}
		};
		roleDTO.setFunctions ( functionSet );
		// now save the role dto
		roleService.modify ( roleDTO );
		// get modified role list
		List<RoleDTO> roleList = roleService.getList ();
		// put role list to session
		session.setAttribute ( SESSION_KEYS.ROLE_LIST, roleList );
		log.info ( "Save the role and Functions" );
		WebUtils.setSuccessMessages ( request, MessageKeys.ASSIGN_SUCCESS_MESSAGE_KEYS, messageArgValues );
		return mapping.findForward ( ForwardNames.EXT_FORM_ADD_SUCCESS );
		
	}
	@Deprecated
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
	
	@Deprecated
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
	@Deprecated
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
	
	@Deprecated
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
		roleService.add ( roleDTO );
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
			// return promptRoleSearchSystemLevel(mapping, form, request,
			// response);
			return null;
		}
		
	}
	
	@Deprecated
	public ActionForward cancelRoleOperation ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "cancelGroupOperation() :" );
		UserDTO userDTO = (UserDTO) request.getSession ().getAttribute ( SESSION_KEYS.USER );
		
		return null;// promptRoleSearchSystemLevel(mapping, form, request,
					// response);
	}
	
	@Deprecated
	public ActionForward cancelSearchRole ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchRole() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
	public String[] getSelFunction ( )
	{
		return selFunction;
	}
	
	public void setSelFunction ( String[] selFunction )
	{
		this.selFunction = selFunction;
	}
	
	public IFunctionService getFunctionService ( )
	{
		return functionService;
	}
	
	public void setFunctionService ( IFunctionService functionService )
	{
		this.functionService = functionService;
	}
	
}
