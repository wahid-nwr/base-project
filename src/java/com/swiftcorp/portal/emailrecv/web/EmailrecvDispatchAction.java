/*
 * @ (#) EmailrecvDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.emailrecv.web;
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
import com.swiftcorp.portal.emailrecv.dto.EmailrecvDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.emailrecv.EmailrecvSuccessResult;
import com.swiftcorp.portal.emailrecv.service.IEmailrecvService;
import com.swiftcorp.portal.emailrecv.web.EmailrecvSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailrecvDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(EmailrecvDispatchAction.class);
	@SuppressWarnings("unused")
	private IEmailrecvService emailrecvService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setEmailrecvService(IEmailrecvService emailrecvService) 
	{
		this.emailrecvService = emailrecvService;
	}
	
	public ActionForward promptEmailrecvSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailrecvSearchSystemLevel() : enter");	
		try 
		{
			EmailrecvSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmailrecvSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emailrecvService.search(searchSqlQuery);
			log.info("searchEmailrecvFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILRECV_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailrecvSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailrecvSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the emailrecv search page
		return mapping.findForward(ForwardNames.EMAILRECV_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtEmailrecvSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailrecvSearchSystemLevel() : enter");	
		try 
		{
			EmailrecvSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmailrecvSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emailrecvService.search(searchSqlQuery);
			log.info("searchEmailrecvFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILRECV_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailrecvSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailrecvSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the emailrecv search page
		return mapping.findForward(ForwardNames.EXT_EMAILRECV_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptEmailrecvSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailrecvSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the emailrecv
			String searchSqlQuery = EmailrecvSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = emailrecvService.search(searchSqlQuery);
			log.info("searchEmailrecvFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILRECV_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailrecvSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailrecvSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the emailrecv search page
		return mapping.findForward(ForwardNames.EMAILRECV_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddEmailrecv(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddEmailrecvHome() : enter");		
		DynaValidatorActionForm emailrecvForm = (DynaValidatorActionForm) form;
		emailrecvForm.set("emailrecv",new EmailrecvDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_EMAILRECV);
	}
	public ActionForward addEmailrecv(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addEmailrecv() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm emailrecvForm = (DynaValidatorActionForm) form;
		EmailrecvDTO emailrecvDTO = (EmailrecvDTO)emailrecvForm.get("emailrecv");
		emailrecvDTO = new EmailrecvDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emailrecvDTO);
		String[][] messageArgValues = {{emailrecvDTO.getUniqueCode()}};
		EmailrecvSuccessResult result = (EmailrecvSuccessResult)emailrecvService.add(emailrecvDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addEmailrecv() : Exit");
		return promptSuccessAddEmailrecv( mapping,form, request, response);
	}
	
	public ActionForward searchEmailrecvFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmailrecvFromSystemLevel():: Enter");	
		String searchSqlQuery = EmailrecvSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emailrecvService.search(searchSqlQuery);
		log.info("searchEmailrecvFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.EMAILRECV_SEARCH_RESULT, searchOperationResult);
		EmailrecvSearchUtils.prepareSearchPage(request);
		log.info("searchEmailrecvFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAILRECV_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchEmailrecvFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmailrecvFromGroupLevel():: Enter");	
		String searchSqlQuery = EmailrecvSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emailrecvService.search(searchSqlQuery);
		log.info("searchEmailrecvFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		EmailrecvSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.EMAILRECV_SEARCH_RESULT, searchOperationResult);	
		log.info("searchEmailrecvFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAILRECV_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyEmailrecv(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyEmailrecv() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmailrecv() : componentId = "+ componentId);
		EmailrecvDTO emailrecvDTO = (EmailrecvDTO)emailrecvService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("emailrecv",emailrecvDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyEmailrecv() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_EMAILRECV);
	}
	public ActionForward modifyEmailrecv(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyEmailrecv() : Enter");
		DynaValidatorActionForm emailrecvForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmailrecv() : componentId = "+ componentId);
		EmailrecvDTO emailrecvDTO = (EmailrecvDTO)emailrecvService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emailrecvDTO);
		
		String[][] messageArgValues = {{emailrecvDTO.getUniqueCode()}};
		emailrecvService.modify(emailrecvDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyEmailrecv() : Exit");
		return promptSuccessAddEmailrecv( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddEmailrecv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitEmailrecv() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelEmailrecvOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelEmailrecvOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptEmailrecvSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptEmailrecvSearchGroupLevel(mapping,form,request,response);
		}
		return promptEmailrecvSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchEmailrecv(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchEmailrecv() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
