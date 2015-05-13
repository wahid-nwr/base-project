/*
 * @ (#) EmailrecipientsDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.emailrecipients.web;
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
import com.swiftcorp.portal.emailrecipients.dto.EmailrecipientsDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.emailrecipients.EmailrecipientsSuccessResult;
import com.swiftcorp.portal.emailrecipients.service.IEmailrecipientsService;
import com.swiftcorp.portal.emailrecipients.web.EmailrecipientsSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailrecipientsDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(EmailrecipientsDispatchAction.class);
	@SuppressWarnings("unused")
	private IEmailrecipientsService emailrecipientsService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setEmailrecipientsService(IEmailrecipientsService emailrecipientsService) 
	{
		this.emailrecipientsService = emailrecipientsService;
	}
	
	public ActionForward promptEmailrecipientsSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailrecipientsSearchSystemLevel() : enter");	
		try 
		{
			EmailrecipientsSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmailrecipientsSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emailrecipientsService.search(searchSqlQuery);
			log.info("searchEmailrecipientsFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILRECIPIENTS_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailrecipientsSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailrecipientsSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the emailrecipients search page
		return mapping.findForward(ForwardNames.EMAILRECIPIENTS_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtEmailrecipientsSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailrecipientsSearchSystemLevel() : enter");	
		try 
		{
			EmailrecipientsSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmailrecipientsSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emailrecipientsService.search(searchSqlQuery);
			log.info("searchEmailrecipientsFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILRECIPIENTS_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailrecipientsSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailrecipientsSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the emailrecipients search page
		return mapping.findForward(ForwardNames.EXT_EMAILRECIPIENTS_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptEmailrecipientsSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailrecipientsSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the emailrecipients
			String searchSqlQuery = EmailrecipientsSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = emailrecipientsService.search(searchSqlQuery);
			log.info("searchEmailrecipientsFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILRECIPIENTS_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailrecipientsSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailrecipientsSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the emailrecipients search page
		return mapping.findForward(ForwardNames.EMAILRECIPIENTS_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddEmailrecipients(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddEmailrecipientsHome() : enter");		
		DynaValidatorActionForm emailrecipientsForm = (DynaValidatorActionForm) form;
		emailrecipientsForm.set("emailrecipients",new EmailrecipientsDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_EMAILRECIPIENTS);
	}
	public ActionForward addEmailrecipients(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addEmailrecipients() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm emailrecipientsForm = (DynaValidatorActionForm) form;
		EmailrecipientsDTO emailrecipientsDTO = (EmailrecipientsDTO)emailrecipientsForm.get("emailrecipients");
		emailrecipientsDTO = new EmailrecipientsDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emailrecipientsDTO);
		String[][] messageArgValues = {{emailrecipientsDTO.getUniqueCode()}};
		EmailrecipientsSuccessResult result = (EmailrecipientsSuccessResult)emailrecipientsService.add(emailrecipientsDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addEmailrecipients() : Exit");
		return promptSuccessAddEmailrecipients( mapping,form, request, response);
	}
	
	public ActionForward searchEmailrecipientsFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmailrecipientsFromSystemLevel():: Enter");	
		String searchSqlQuery = EmailrecipientsSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emailrecipientsService.search(searchSqlQuery);
		log.info("searchEmailrecipientsFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.EMAILRECIPIENTS_SEARCH_RESULT, searchOperationResult);
		EmailrecipientsSearchUtils.prepareSearchPage(request);
		log.info("searchEmailrecipientsFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAILRECIPIENTS_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchEmailrecipientsFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmailrecipientsFromGroupLevel():: Enter");	
		String searchSqlQuery = EmailrecipientsSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emailrecipientsService.search(searchSqlQuery);
		log.info("searchEmailrecipientsFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		EmailrecipientsSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.EMAILRECIPIENTS_SEARCH_RESULT, searchOperationResult);	
		log.info("searchEmailrecipientsFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAILRECIPIENTS_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyEmailrecipients(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyEmailrecipients() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmailrecipients() : componentId = "+ componentId);
		EmailrecipientsDTO emailrecipientsDTO = (EmailrecipientsDTO)emailrecipientsService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("emailrecipients",emailrecipientsDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyEmailrecipients() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_EMAILRECIPIENTS);
	}
	public ActionForward modifyEmailrecipients(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyEmailrecipients() : Enter");
		DynaValidatorActionForm emailrecipientsForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmailrecipients() : componentId = "+ componentId);
		EmailrecipientsDTO emailrecipientsDTO = (EmailrecipientsDTO)emailrecipientsService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emailrecipientsDTO);
		
		String[][] messageArgValues = {{emailrecipientsDTO.getUniqueCode()}};
		emailrecipientsService.modify(emailrecipientsDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyEmailrecipients() : Exit");
		return promptSuccessAddEmailrecipients( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddEmailrecipients(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitEmailrecipients() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelEmailrecipientsOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelEmailrecipientsOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptEmailrecipientsSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptEmailrecipientsSearchGroupLevel(mapping,form,request,response);
		}
		return promptEmailrecipientsSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchEmailrecipients(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchEmailrecipients() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
