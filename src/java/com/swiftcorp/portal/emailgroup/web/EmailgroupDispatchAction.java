/*
 * @ (#) EmailgroupDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.emailgroup.web;
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
import com.swiftcorp.portal.emailgroup.dto.EmailgroupDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.emailgroup.EmailgroupSuccessResult;
import com.swiftcorp.portal.emailgroup.service.IEmailgroupService;
import com.swiftcorp.portal.emailgroup.web.EmailgroupSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailgroupDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(EmailgroupDispatchAction.class);
	@SuppressWarnings("unused")
	private IEmailgroupService emailgroupService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setEmailgroupService(IEmailgroupService emailgroupService) 
	{
		this.emailgroupService = emailgroupService;
	}
	
	public ActionForward promptEmailgroupSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailgroupSearchSystemLevel() : enter");	
		try 
		{
			EmailgroupSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmailgroupSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emailgroupService.search(searchSqlQuery);
			log.info("searchEmailgroupFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILGROUP_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailgroupSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailgroupSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the emailgroup search page
		return mapping.findForward(ForwardNames.EMAILGROUP_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtEmailgroupSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailgroupSearchSystemLevel() : enter");	
		try 
		{
			EmailgroupSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = EmailgroupSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = emailgroupService.search(searchSqlQuery);
			log.info("searchEmailgroupFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILGROUP_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailgroupSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailgroupSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the emailgroup search page
		return mapping.findForward(ForwardNames.EXT_EMAILGROUP_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptEmailgroupSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptEmailgroupSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the emailgroup
			String searchSqlQuery = EmailgroupSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = emailgroupService.search(searchSqlQuery);
			log.info("searchEmailgroupFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.EMAILGROUP_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			EmailgroupSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptEmailgroupSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the emailgroup search page
		return mapping.findForward(ForwardNames.EMAILGROUP_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddEmailgroup(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddEmailgroupHome() : enter");		
		DynaValidatorActionForm emailgroupForm = (DynaValidatorActionForm) form;
		emailgroupForm.set("emailgroup",new EmailgroupDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_EMAILGROUP);
	}
	public ActionForward addEmailgroup(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addEmailgroup() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm emailgroupForm = (DynaValidatorActionForm) form;
		EmailgroupDTO emailgroupDTO = (EmailgroupDTO)emailgroupForm.get("emailgroup");
		emailgroupDTO = new EmailgroupDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emailgroupDTO);
		String[][] messageArgValues = {{emailgroupDTO.getUniqueCode()}};
		EmailgroupSuccessResult result = (EmailgroupSuccessResult)emailgroupService.add(emailgroupDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addEmailgroup() : Exit");
		return promptSuccessAddEmailgroup( mapping,form, request, response);
	}
	
	public ActionForward searchEmailgroupFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmailgroupFromSystemLevel():: Enter");	
		String searchSqlQuery = EmailgroupSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emailgroupService.search(searchSqlQuery);
		log.info("searchEmailgroupFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.EMAILGROUP_SEARCH_RESULT, searchOperationResult);
		EmailgroupSearchUtils.prepareSearchPage(request);
		log.info("searchEmailgroupFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAILGROUP_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchEmailgroupFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchEmailgroupFromGroupLevel():: Enter");	
		String searchSqlQuery = EmailgroupSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = emailgroupService.search(searchSqlQuery);
		log.info("searchEmailgroupFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		EmailgroupSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.EMAILGROUP_SEARCH_RESULT, searchOperationResult);	
		log.info("searchEmailgroupFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.EMAILGROUP_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyEmailgroup(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyEmailgroup() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmailgroup() : componentId = "+ componentId);
		EmailgroupDTO emailgroupDTO = (EmailgroupDTO)emailgroupService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("emailgroup",emailgroupDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyEmailgroup() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_EMAILGROUP);
	}
	public ActionForward modifyEmailgroup(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyEmailgroup() : Enter");
		DynaValidatorActionForm emailgroupForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyEmailgroup() : componentId = "+ componentId);
		EmailgroupDTO emailgroupDTO = (EmailgroupDTO)emailgroupService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, emailgroupDTO);
		
		String[][] messageArgValues = {{emailgroupDTO.getUniqueCode()}};
		emailgroupService.modify(emailgroupDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyEmailgroup() : Exit");
		return promptSuccessAddEmailgroup( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddEmailgroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitEmailgroup() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelEmailgroupOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelEmailgroupOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptEmailgroupSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptEmailgroupSearchGroupLevel(mapping,form,request,response);
		}
		return promptEmailgroupSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchEmailgroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchEmailgroup() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
