/*
 * @ (#) EmailreferanceDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.emailreferance.web;
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
import com.swiftcorp.portal.emailreferance.dto.EmailreferanceDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.emailreferance.EmailreferanceSuccessResult;
import com.swiftcorp.portal.emailreferance.service.IEmailreferanceService;
import com.swiftcorp.portal.emailreferance.web.EmailreferanceSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailreferanceDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(EmailreferanceDispatchAction.class);
	@SuppressWarnings("unused")
	private IEmailreferanceService emailreferanceService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setEmailreferanceService(IEmailreferanceService emailreferanceService) 
	{
		this.emailreferanceService = emailreferanceService;
	}
	
	public ActionForward promptEmailreferanceSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailreferanceSearchSystemLevel() : enter");	
		try 
		{
			EmailreferanceSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmailreferanceSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emailreferanceService.search(searchSqlQuery);
			log.info("searchEmailreferanceFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILREFERANCE_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailreferanceSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailreferanceSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the emailreferance search page
		return mapping.findForward(ForwardNames.EMAILREFERANCE_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtEmailreferanceSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailreferanceSearchSystemLevel() : enter");	
		try 
		{
			EmailreferanceSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmailreferanceSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emailreferanceService.search(searchSqlQuery);
			log.info("searchEmailreferanceFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILREFERANCE_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailreferanceSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailreferanceSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the emailreferance search page
		return mapping.findForward(ForwardNames.EXT_EMAILREFERANCE_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptEmailreferanceSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailreferanceSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the emailreferance
			String searchSqlQuery = EmailreferanceSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = emailreferanceService.search(searchSqlQuery);
			log.info("searchEmailreferanceFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILREFERANCE_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailreferanceSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailreferanceSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the emailreferance search page
		return mapping.findForward(ForwardNames.EMAILREFERANCE_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddEmailreferance(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddEmailreferanceHome() : enter");		
		DynaValidatorActionForm emailreferanceForm = (DynaValidatorActionForm) form;
		emailreferanceForm.set("emailreferance",new EmailreferanceDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_EMAILREFERANCE);
	}
	public ActionForward addEmailreferance(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addEmailreferance() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm emailreferanceForm = (DynaValidatorActionForm) form;
		EmailreferanceDTO emailreferanceDTO = (EmailreferanceDTO)emailreferanceForm.get("emailreferance");
		emailreferanceDTO = new EmailreferanceDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emailreferanceDTO);
		String[][] messageArgValues = {{emailreferanceDTO.getUniqueCode()}};
		EmailreferanceSuccessResult result = (EmailreferanceSuccessResult)emailreferanceService.add(emailreferanceDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addEmailreferance() : Exit");
		return promptSuccessAddEmailreferance( mapping,form, request, response);
	}
	
	public ActionForward searchEmailreferanceFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmailreferanceFromSystemLevel():: Enter");	
		String searchSqlQuery = EmailreferanceSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emailreferanceService.search(searchSqlQuery);
		log.info("searchEmailreferanceFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.EMAILREFERANCE_SEARCH_RESULT, searchOperationResult);
		EmailreferanceSearchUtils.prepareSearchPage(request);
		log.info("searchEmailreferanceFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAILREFERANCE_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchEmailreferanceFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmailreferanceFromGroupLevel():: Enter");	
		String searchSqlQuery = EmailreferanceSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emailreferanceService.search(searchSqlQuery);
		log.info("searchEmailreferanceFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		EmailreferanceSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.EMAILREFERANCE_SEARCH_RESULT, searchOperationResult);	
		log.info("searchEmailreferanceFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAILREFERANCE_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyEmailreferance(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyEmailreferance() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmailreferance() : componentId = "+ componentId);
		EmailreferanceDTO emailreferanceDTO = (EmailreferanceDTO)emailreferanceService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("emailreferance",emailreferanceDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyEmailreferance() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_EMAILREFERANCE);
	}
	public ActionForward modifyEmailreferance(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyEmailreferance() : Enter");
		DynaValidatorActionForm emailreferanceForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmailreferance() : componentId = "+ componentId);
		EmailreferanceDTO emailreferanceDTO = (EmailreferanceDTO)emailreferanceService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emailreferanceDTO);
		
		String[][] messageArgValues = {{emailreferanceDTO.getUniqueCode()}};
		emailreferanceService.modify(emailreferanceDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyEmailreferance() : Exit");
		return promptSuccessAddEmailreferance( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddEmailreferance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitEmailreferance() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelEmailreferanceOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelEmailreferanceOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptEmailreferanceSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptEmailreferanceSearchGroupLevel(mapping,form,request,response);
		}
		return promptEmailreferanceSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchEmailreferance(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchEmailreferance() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
