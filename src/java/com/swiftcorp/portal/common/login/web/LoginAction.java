/*
 * @ (#) LoginAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.login.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.validator.DynaValidatorActionForm;

import com.swiftcorp.portal.common.ViewValueDTO;
import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.common.dto.FunctionDTO;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.LoginSuccessResult;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.login.dto.LoginDetailInfoDTO;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.common.service.IFunctionService;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.geo.dto.GeoDTO;
import com.swiftcorp.portal.geo.service.IGeoService;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.module.dto.ModuleDTO;
import com.swiftcorp.portal.module.service.IModuleService;
import com.swiftcorp.portal.report.util.ReportUtils;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.user.exception.InvalidPasswordException;
import com.swiftcorp.portal.user.exception.UserNotFoundException;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class LoginAction extends DispatchAction
{
	private static final Log theLogger = LogFactory.getLog ( LoginAction.class );
	private ILoginService loginService;
	private IGroupService groupService;
	private IFunctionService functionService;
	private IGeoService geoService;
	private IModuleService moduleService;
	
	private static String loginUserHomeRequestString = "loginAction.csmp?method=loginSuccess";
	@SuppressWarnings("unused")
	private static String promptLoginRequestString = "loginAction.csmp?method=promptLogin";
	
	public void setModuleService(IModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public void setLoginService ( ILoginService loginService )
	{
		this.loginService = loginService;
	}
	
	public IFunctionService getFunctionService ( )
	{
		return functionService;
	}
	
	public void setFunctionService ( IFunctionService functionService )
	{
		this.functionService = functionService;
	}
	
	public ActionForward login ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		theLogger.info ( "login():: Enter" );
		
		DynaValidatorActionForm userForm = (DynaValidatorActionForm) form;
		LoginDTO loginDTO = (LoginDTO) userForm.get ( "authDTO" );
		loginDTO = new LoginDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, loginDTO);
		ActionErrors errors = new ActionErrors ();
		
		try
		{
			LoginSuccessResult successResult = loginService.authenticate ( loginDTO );
			theLogger.info ( "login():: AUTHENTICATED..Login Sucessful" );
			
			HttpSession session = request.getSession ();
			if ( session == null )
			{
				session = request.getSession ( true );
			}
			
			GenericDTO genericDTO = successResult.getOperationResult ();
			LoginDetailInfoDTO loginInfo = new LoginDetailInfoDTO ();
			
			// cast the genericDTO to userDTO
			UserDTO userDTO = (UserDTO) genericDTO;
			loginInfo.setUser ( userDTO );
			
			// get the function dto list
			Set<FunctionDTO> functionDTOSet = userDTO.getRole ().getFunctions (); // this.functionService.getFunctionListByRole
																					// (
																					// userDTO.getRole
																					// ()
																					// );
			
			// theLogger.info ( "Function DTO List Size: " +
			// functionDTOList.size () );
			// put the list into session
			/*
			List<GeoDTO> ccList = geoService.getCCList ();
			List<GeoDTO> regionList = geoService.getRegionList ();
			List<GeoDTO> branchList = geoService.getBranchList ();
			List<GeoDTO> childAreaList = new ArrayList<GeoDTO> ();
			int userAreaType = userDTO.getAreaType ();
			
			if(userAreaType==DTOConstants.GEO_TYPE_CITY_CORPORATION)
			{
				
				for(GeoDTO geoDTO :regionList)
				{
					long parentCityId = geoDTO.getParentArea ().getComponentId ();
					long regionsParent = userDTO.getUserArea ().getComponentId ();
					
					if((""+parentCityId).equals ( ""+regionsParent ))
					{
						childAreaList.add ( geoDTO );
						for(GeoDTO childGeoDTO :branchList)
						{
							long parentRegionId = childGeoDTO.getParentArea ().getComponentId ();
							long branchsParent = geoDTO.getComponentId ();
							if((""+parentRegionId).equals ( ""+branchsParent ))
							{
								childAreaList.add ( childGeoDTO );
							}
						}
					}
				}
				
			}
			else if(userAreaType==DTOConstants.GEO_TYPE_REGION)
			{
				for(GeoDTO geoDTO :branchList)
				{
					long parentId = geoDTO.getParentArea ().getComponentId ();
					long childParent = userDTO.getUserArea ().getComponentId () ;
					System.out.println ("branch parent id::"+geoDTO.getParentArea ().getComponentId ());
					System.out.println ( "user area id::"+userDTO.getUserArea ().getComponentId () );
					System.out.println ("found child::"+(geoDTO.getParentArea ().getComponentId ()== userDTO.getUserArea ().getComponentId ()));
					if((""+parentId).equals ( ""+childParent ))
					{
						System.out.println ("adding::"+geoDTO.getComponentId ());
						childAreaList.add ( geoDTO );
					}
				}
			}
			else if(userAreaType==DTOConstants.GEO_TYPE_BRANCH)
			{
				childAreaList.add ( userDTO.getUserArea () );
			}
			else
			{
				childAreaList.addAll ( ccList );
				childAreaList.addAll ( regionList );
				childAreaList.addAll ( branchList );
			}
			System.out.println ("childAreaList size::::::::::::"+childAreaList.size ());
			session.setAttribute ( SESSION_KEYS.LOGIN_USER_AREA, userDTO.getUserArea () );
			session.setAttribute ( SESSION_KEYS.LOGIN_USER_CHILD_AREA, childAreaList );
			
			*/
			List<ModuleDTO> moduleList = (List<ModuleDTO>)moduleService.getList();
			for(int i=0;moduleList!=null && i<moduleList.size();i++)
			{
				System.out.println("module::"+moduleList.get(i).getModuleName());
			}
			session.setAttribute ( SESSION_KEYS.MODULEDTO_LIST, moduleList );
			session.setAttribute ( SESSION_KEYS.FUNCTIONDTO_SET, functionDTOSet );
			session.setAttribute ( SESSION_KEYS.LOGIN_INFO, loginInfo );
			return mapping.findForward(ForwardNames.LOGIN_SUCCESS);
			//response.sendRedirect ( loginUserHomeRequestString );
			//mapping.findForward("dcrinfo_add_success");
			//response.sendRedirect("dcrinfo_add_success");
			
		}
		catch (UserNotFoundException e)
		{
			// show login page with msg
			ActionMessage actionMessage = new ActionMessage ( "common.login.failure.msg" );
			errors.add ( ActionMessages.GLOBAL_MESSAGE, actionMessage );			
		}
		catch (InvalidPasswordException e)
		{
			// show login page with msg
			ActionMessage actionMessage = new ActionMessage ( "common.login.failure.msg" );
			errors.add ( ActionMessages.GLOBAL_MESSAGE, actionMessage );			
		}
		catch (SystemException e)
		{
			// show login page with msg
			ActionMessage actionMessage = new ActionMessage ( "common.login.systemfailure.msg" );
			errors.add ( ActionMessages.GLOBAL_MESSAGE, actionMessage );			
		}
		
		// ERROR OCCURS...SO FORWARD TO LOGIN PROMPT WITH ERROR MESSAGE
		if(!errors.isEmpty())
		{
			request.setAttribute ( Globals.ERROR_KEY, errors );
			return mapping.findForward(ForwardNames.EXT_FORM_SUBMIT_EXCEPTION);
		}
		return mapping.findForward ( ForwardNames.PROMT_LOGIN );
	}
	
	public ActionForward promptLogin ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		theLogger.info ( "promptLogin()::" );
		HttpSession session = request.getSession ();
		if ( session != null && session.getAttribute ( SESSION_KEYS.LOGIN_INFO ) != null )
		{
			response.sendRedirect ( loginUserHomeRequestString );
		}
		String isSessionExpire = request.getParameter ( SESSION_KEYS.IS_SESSION_EXPIRE );
		if ( "true".equals ( isSessionExpire ) )
		{
			ActionErrors errors = new ActionErrors ();
			ActionMessage actionMessage = new ActionMessage ( "common.login.session.expire.msg" );
			errors.add ( ActionMessages.GLOBAL_MESSAGE, actionMessage );
			request.setAttribute ( Globals.ERROR_KEY, errors );
		}
		return mapping.findForward ( ForwardNames.PROMT_LOGIN );
	}
	
	public ActionForward loginUserHome ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		theLogger.info ( "loginUserHome()::" );
		return loginSuccess ( mapping, form, request, response );
	}
	
	public ActionForward loginUserRoleFunctions ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
	throws Exception
	{
		theLogger.info ( "loginUserRoleFunctions()::" );
		return mapping.findForward ( ForwardNames.USER_FOLE_FUNCTIONS );
	}
	// Login success
	public ActionForward loginSuccess ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		theLogger.info ( "loginSuccess()::" );
		
		ActionForward actionForward = null;
		HttpSession session = request.getSession ();
		if(session == null)
		{
			response.sendRedirect(promptLoginRequestString);
		}
		LoginDetailInfoDTO loginInfo = (LoginDetailInfoDTO) session.getAttribute ( SESSION_KEYS.LOGIN_INFO );
		if ( loginInfo == null )
		{
			response.sendRedirect ( promptLoginRequestString );
		}
		try
		{
			loginInfo.getUser ();
		}
		catch(Exception e)
		{
			return promptLogin(mapping, form, request, response);
			//response.sendRedirect(promptLoginRequestString);
		}
		
		session.setAttribute ( SESSION_KEYS.USER, loginInfo.getUser () );
		// GroupDTO group = (GroupDTO)
		// groupService.get(loginInfo.getUser().getGroupId());
		// session.setAttribute(SESSION_KEYS.GROUP, group);
		actionForward = mapping.findForward ( ForwardNames.USER_HOME );
		
		// since this month list and year list is needed to all other components
		// that's why we need to add those into first long-in
		List<ViewValueDTO> monthList = ReportUtils.getMonths ();
		List<ViewValueDTO> yearList = ReportUtils.getYears ();
		request.getSession ().setAttribute ( SESSION_KEYS.MONTH_LIST, monthList );
		request.getSession ().setAttribute ( SESSION_KEYS.YEAR_LIST, yearList );
		
		if ( actionForward != null )
		{
			return actionForward;
		}
		
		return promptLogin ( mapping, form, request, response );
	}
	
	public ActionForward logout ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "logout()::" );
		System.out.println("logout()::");
		HttpSession session = request.getSession ();
		if ( session != null )
		{
			session.setAttribute ( SESSION_KEYS.LOGIN_INFO, null );
			session.invalidate ();
			log.debug ( "logout() : successfully ending session" );
		}
		return promptLogin ( mapping, form, request, response );
	}

	public void setGeoService ( IGeoService geoService )
	{
		this.geoService = geoService;
	}
	
	public ActionForward extTest ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
	throws Exception
	{
		theLogger.info ( "extTest()::" );
		return mapping.findForward ( ForwardNames.EXT_TEST );
	}
	
}
