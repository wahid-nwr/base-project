/*
 * @ (#) EmaildtlDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.emaildtl.web;
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
import com.swiftcorp.portal.emaildtl.dto.EmaildtlDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.emaildtl.EmaildtlSuccessResult;
import com.swiftcorp.portal.emaildtl.service.IEmaildtlService;
import com.swiftcorp.portal.emaildtl.web.EmaildtlSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmaildtlDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(EmaildtlDispatchAction.class);
	@SuppressWarnings("unused")
	private IEmaildtlService emaildtlService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setEmaildtlService(IEmaildtlService emaildtlService) 
	{
		this.emaildtlService = emaildtlService;
	}
	
	public ActionForward promptEmaildtlSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmaildtlSearchSystemLevel() : enter");	
		try 
		{
			EmaildtlSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmaildtlSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emaildtlService.search(searchSqlQuery);
			log.info("searchEmaildtlFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILDTL_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmaildtlSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmaildtlSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the emaildtl search page
		return mapping.findForward(ForwardNames.EMAILDTL_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtEmaildtlSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmaildtlSearchSystemLevel() : enter");	
		try 
		{
			EmaildtlSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmaildtlSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emaildtlService.search(searchSqlQuery);
			log.info("searchEmaildtlFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILDTL_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmaildtlSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmaildtlSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the emaildtl search page
		return mapping.findForward(ForwardNames.EXT_EMAILDTL_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptEmaildtlSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmaildtlSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the emaildtl
			String searchSqlQuery = EmaildtlSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = emaildtlService.search(searchSqlQuery);
			log.info("searchEmaildtlFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILDTL_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmaildtlSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmaildtlSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the emaildtl search page
		return mapping.findForward(ForwardNames.EMAILDTL_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddEmaildtl(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddEmaildtlHome() : enter");		
		DynaValidatorActionForm emaildtlForm = (DynaValidatorActionForm) form;
		emaildtlForm.set("emaildtl",new EmaildtlDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_EMAILDTL);
	}
	public ActionForward addEmaildtl(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addEmaildtl() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm emaildtlForm = (DynaValidatorActionForm) form;
		EmaildtlDTO emaildtlDTO = (EmaildtlDTO)emaildtlForm.get("emaildtl");
		emaildtlDTO = new EmaildtlDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emaildtlDTO);
		String[][] messageArgValues = {{emaildtlDTO.getUniqueCode()}};
		EmaildtlSuccessResult result = (EmaildtlSuccessResult)emaildtlService.add(emaildtlDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addEmaildtl() : Exit");
		return promptSuccessAddEmaildtl( mapping,form, request, response);
	}
	
	public ActionForward searchEmaildtlFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmaildtlFromSystemLevel():: Enter");	
		String searchSqlQuery = EmaildtlSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emaildtlService.search(searchSqlQuery);
		log.info("searchEmaildtlFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.EMAILDTL_SEARCH_RESULT, searchOperationResult);
		EmaildtlSearchUtils.prepareSearchPage(request);
		log.info("searchEmaildtlFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAILDTL_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchEmaildtlFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmaildtlFromGroupLevel():: Enter");	
		String searchSqlQuery = EmaildtlSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emaildtlService.search(searchSqlQuery);
		log.info("searchEmaildtlFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		EmaildtlSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.EMAILDTL_SEARCH_RESULT, searchOperationResult);	
		log.info("searchEmaildtlFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAILDTL_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyEmaildtl(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyEmaildtl() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmaildtl() : componentId = "+ componentId);
		EmaildtlDTO emaildtlDTO = (EmaildtlDTO)emaildtlService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("emaildtl",emaildtlDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyEmaildtl() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_EMAILDTL);
	}
	public ActionForward modifyEmaildtl(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyEmaildtl() : Enter");
		DynaValidatorActionForm emaildtlForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmaildtl() : componentId = "+ componentId);
		EmaildtlDTO emaildtlDTO = (EmaildtlDTO)emaildtlService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emaildtlDTO);
		
		String[][] messageArgValues = {{emaildtlDTO.getUniqueCode()}};
		emaildtlService.modify(emaildtlDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyEmaildtl() : Exit");
		return promptSuccessAddEmaildtl( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddEmaildtl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitEmaildtl() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelEmaildtlOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelEmaildtlOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptEmaildtlSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptEmaildtlSearchGroupLevel(mapping,form,request,response);
		}
		return promptEmaildtlSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchEmaildtl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchEmaildtl() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
