/*
 * @ (#) ModuleDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.module.web;
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
import com.swiftcorp.portal.module.dto.ModuleDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.module.ModuleSuccessResult;
import com.swiftcorp.portal.module.service.IModuleService;
import com.swiftcorp.portal.module.web.ModuleSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class ModuleDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(ModuleDispatchAction.class);
	@SuppressWarnings("unused")
	private IModuleService moduleService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setModuleService(IModuleService moduleService) 
	{
		this.moduleService = moduleService;
	}
	
	public ActionForward promptModuleSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptModuleSearchSystemLevel() : enter");	
		try 
		{
			ModuleSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = ModuleSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = moduleService.search(searchSqlQuery);
			log.info("searchModuleFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.MODULE_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			ModuleSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptModuleSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the module search page
		return mapping.findForward(ForwardNames.MODULE_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtModuleSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptModuleSearchSystemLevel() : enter");	
		try 
		{
			ModuleSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = ModuleSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = moduleService.search(searchSqlQuery);
			log.info("searchModuleFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.MODULE_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			ModuleSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptModuleSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the module search page
		return mapping.findForward(ForwardNames.EXT_MODULE_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptModuleSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptModuleSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the module
			String searchSqlQuery = ModuleSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = moduleService.search(searchSqlQuery);
			log.info("searchModuleFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.MODULE_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			ModuleSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptModuleSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the module search page
		return mapping.findForward(ForwardNames.MODULE_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddModule(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddModuleHome() : enter");		
		DynaValidatorActionForm moduleForm = (DynaValidatorActionForm) form;
		moduleForm.set("module",new ModuleDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_MODULE);
	}
	public ActionForward addModule(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addModule() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm moduleForm = (DynaValidatorActionForm) form;
		ModuleDTO moduleDTO = (ModuleDTO)moduleForm.get("module");
		moduleDTO = new ModuleDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, moduleDTO);
		String[][] messageArgValues = {{moduleDTO.getUniqueCode()}};
		ModuleSuccessResult result = (ModuleSuccessResult)moduleService.add(moduleDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addModule() : Exit");
		return promptSuccessAddModule( mapping,form, request, response);
	}
	
	public ActionForward searchModuleFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchModuleFromSystemLevel():: Enter");	
		String searchSqlQuery = ModuleSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = moduleService.search(searchSqlQuery);
		log.info("searchModuleFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.MODULE_SEARCH_RESULT, searchOperationResult);
		ModuleSearchUtils.prepareSearchPage(request);
		log.info("searchModuleFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.MODULE_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchModuleFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchModuleFromGroupLevel():: Enter");	
		String searchSqlQuery = ModuleSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = moduleService.search(searchSqlQuery);
		log.info("searchModuleFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		ModuleSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.MODULE_SEARCH_RESULT, searchOperationResult);	
		log.info("searchModuleFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.MODULE_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyModule(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyModule() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyModule() : componentId = "+ componentId);
		ModuleDTO moduleDTO = (ModuleDTO)moduleService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("module",moduleDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyModule() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_MODULE);
	}
	public ActionForward modifyModule(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyModule() : Enter");
		DynaValidatorActionForm moduleForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyModule() : componentId = "+ componentId);
		ModuleDTO moduleDTO = (ModuleDTO)moduleService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, moduleDTO);
		
		String[][] messageArgValues = {{moduleDTO.getUniqueCode()}};
		moduleService.modify(moduleDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyModule() : Exit");
		return promptSuccessAddModule( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddModule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitModule() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelModuleOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelModuleOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptModuleSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptModuleSearchGroupLevel(mapping,form,request,response);
		}
		return promptModuleSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchModule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchModule() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
