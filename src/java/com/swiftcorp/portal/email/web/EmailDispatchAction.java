/*
 * @ (#) EmailDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.email.web;
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
import com.swiftcorp.portal.email.dto.EmailDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.email.EmailSuccessResult;
import com.swiftcorp.portal.email.service.IEmailService;
import com.swiftcorp.portal.email.web.EmailSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(EmailDispatchAction.class);
	@SuppressWarnings("unused")
	private IEmailService emailService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setEmailService(IEmailService emailService) 
	{
		this.emailService = emailService;
	}
	
	public ActionForward promptEmailSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailSearchSystemLevel() : enter");	
		try 
		{
			EmailSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmailSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emailService.search(searchSqlQuery);
			log.info("searchEmailFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAIL_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the email search page
		return mapping.findForward(ForwardNames.EMAIL_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtEmailSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailSearchSystemLevel() : enter");	
		try 
		{
			EmailSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmailSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emailService.search(searchSqlQuery);
			log.info("searchEmailFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAIL_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the email search page
		return mapping.findForward(ForwardNames.EXT_EMAIL_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptEmailSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the email
			String searchSqlQuery = EmailSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = emailService.search(searchSqlQuery);
			log.info("searchEmailFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAIL_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the email search page
		return mapping.findForward(ForwardNames.EMAIL_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddEmail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddEmailHome() : enter");		
		DynaValidatorActionForm emailForm = (DynaValidatorActionForm) form;
		emailForm.set("email",new EmailDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_EMAIL);
	}
	public ActionForward addEmail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addEmail() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm emailForm = (DynaValidatorActionForm) form;
		EmailDTO emailDTO = (EmailDTO)emailForm.get("email");
		emailDTO = new EmailDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emailDTO);
		String[][] messageArgValues = {{emailDTO.getUniqueCode()}};
		EmailSuccessResult result = (EmailSuccessResult)emailService.add(emailDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addEmail() : Exit");
		return promptSuccessAddEmail( mapping,form, request, response);
	}
	
	public ActionForward searchEmailFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmailFromSystemLevel():: Enter");	
		String searchSqlQuery = EmailSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emailService.search(searchSqlQuery);
		log.info("searchEmailFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.EMAIL_SEARCH_RESULT, searchOperationResult);
		EmailSearchUtils.prepareSearchPage(request);
		log.info("searchEmailFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAIL_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchEmailFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmailFromGroupLevel():: Enter");	
		String searchSqlQuery = EmailSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emailService.search(searchSqlQuery);
		log.info("searchEmailFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		EmailSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.EMAIL_SEARCH_RESULT, searchOperationResult);	
		log.info("searchEmailFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAIL_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyEmail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyEmail() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmail() : componentId = "+ componentId);
		EmailDTO emailDTO = (EmailDTO)emailService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("email",emailDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyEmail() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_EMAIL);
	}
	public ActionForward modifyEmail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyEmail() : Enter");
		DynaValidatorActionForm emailForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmail() : componentId = "+ componentId);
		EmailDTO emailDTO = (EmailDTO)emailService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emailDTO);
		
		String[][] messageArgValues = {{emailDTO.getUniqueCode()}};
		emailService.modify(emailDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyEmail() : Exit");
		return promptSuccessAddEmail( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddEmail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitEmail() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelEmailOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelEmailOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptEmailSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptEmailSearchGroupLevel(mapping,form,request,response);
		}
		return promptEmailSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchEmail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchEmail() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
