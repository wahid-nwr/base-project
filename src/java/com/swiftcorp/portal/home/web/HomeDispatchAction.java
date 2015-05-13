/*
 * @ (#) HomeDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.home.web;
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
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.home.dto.HomeDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.home.HomeSuccessResult;
import com.swiftcorp.portal.home.service.IHomeService;
import com.swiftcorp.portal.home.web.HomeSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class HomeDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(HomeDispatchAction.class);
	@SuppressWarnings("unused")
	private IHomeService homeService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setHomeService(IHomeService homeService) 
	{
		this.homeService = homeService;
	}
	
	public ActionForward promptHomeSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptHomeSearchSystemLevel() : enter");	
		try 
		{
			HomeSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = HomeSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = homeService.search(searchSqlQuery);
			log.info("searchHomeFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.HOME_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			HomeSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptHomeSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the home search page
		return mapping.findForward(ForwardNames.HOME_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtHomeSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptHomeSearchSystemLevel() : enter");	
		try 
		{
			HomeSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = HomeSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = homeService.search(searchSqlQuery);
			log.info("searchHomeFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.HOME_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			HomeSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptHomeSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the home search page
		return mapping.findForward(ForwardNames.EXT_HOME_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptHomeSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptHomeSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the home
			String searchSqlQuery = HomeSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = homeService.search(searchSqlQuery);
			log.info("searchHomeFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.HOME_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			HomeSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptHomeSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the home search page
		return mapping.findForward(ForwardNames.HOME_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddHome(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddHomeHome() : enter");		
		DynaValidatorActionForm homeForm = (DynaValidatorActionForm) form;
		homeForm.set("home",new HomeDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_HOME);
	}
	public ActionForward addHome(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addHome() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm homeForm = (DynaValidatorActionForm) form;
		HomeDTO homeDTO = (HomeDTO)homeForm.get("home");
		homeDTO = new HomeDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, homeDTO);
		String[][] messageArgValues = {{homeDTO.getUniqueCode()}};
		HomeSuccessResult result = (HomeSuccessResult)homeService.add(homeDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addHome() : Exit");
		return promptSuccessAddHome( mapping,form, request, response);
	}
	
	public ActionForward searchHomeFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchHomeFromSystemLevel():: Enter");	
		String searchSqlQuery = HomeSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = homeService.search(searchSqlQuery);
		log.info("searchHomeFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.HOME_SEARCH_RESULT, searchOperationResult);
		HomeSearchUtils.prepareSearchPage(request);
		log.info("searchHomeFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.HOME_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchHomeFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchHomeFromGroupLevel():: Enter");	
		String searchSqlQuery = HomeSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = homeService.search(searchSqlQuery);
		log.info("searchHomeFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		HomeSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.HOME_SEARCH_RESULT, searchOperationResult);	
		log.info("searchHomeFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.HOME_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyHome(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyHome() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyHome() : componentId = "+ componentId);
		HomeDTO homeDTO = (HomeDTO)homeService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("home",homeDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyHome() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_HOME);
	}
	public ActionForward modifyHome(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyHome() : Enter");
		DynaValidatorActionForm homeForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyHome() : componentId = "+ componentId);
		HomeDTO homeDTO = (HomeDTO)homeService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, homeDTO);
		
		String[][] messageArgValues = {{homeDTO.getUniqueCode()}};
		homeService.modify(homeDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyHome() : Exit");
		return promptSuccessAddHome( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddHome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitHome() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelHomeOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelHomeOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptHomeSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptHomeSearchGroupLevel(mapping,form,request,response);
		}
		return promptHomeSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchHome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchHome() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
