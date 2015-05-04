/*
 * @ (#) AccountDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.account.web;
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

import com.swiftcorp.portal.account.AccountSuccessResult;
import com.swiftcorp.portal.account.dto.AccountDTO;
import com.swiftcorp.portal.account.service.IAccountService;
import com.swiftcorp.portal.account.web.AccountSearchUtils;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.SearchUtil;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
import com.swiftcorp.portal.common.util.WebUtils;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.ForwardUtil;
import com.swiftcorp.portal.common.web.MessageKeys;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.user.dto.UserDTO;
/**
 * @author soma
 * @since Sep 22, 2008
 */
public class AccountDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(AccountDispatchAction.class);
	@SuppressWarnings("unused")
	private IAccountService accountService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setAccountService(IAccountService accountService) 
	{
		this.accountService = accountService;
	}
	
	public ActionForward promptAccountSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAccountSearchSystemLevel() : enter");	
		try 
		{
			AccountSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = AccountSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = accountService.search(searchSqlQuery);
			log.info("searchAccountFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.ACCOUNT_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			AccountSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptAccountSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the account search page
		return mapping.findForward(ForwardNames.ACCOUNT_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtAccountSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAccountSearchSystemLevel() : enter");	
		try 
		{
			AccountSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = AccountSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = accountService.search(searchSqlQuery);
			log.info("searchAccountFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.ACCOUNT_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			AccountSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptAccountSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the account search page
		return mapping.findForward(ForwardNames.EXT_ACCOUNT_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptAccountSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAccountSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the account
			String searchSqlQuery = AccountSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = accountService.search(searchSqlQuery);
			log.info("searchAccountFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.ACCOUNT_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			AccountSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptAccountSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the account search page
		return mapping.findForward(ForwardNames.ACCOUNT_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddAccount(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddAccountHome() : enter");		
		DynaValidatorActionForm accountForm = (DynaValidatorActionForm) form;
		accountForm.set("account",new AccountDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_ACCOUNT);
	}
	public ActionForward addAccount(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addAccount() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm accountForm = (DynaValidatorActionForm) form;
		AccountDTO accountDTO = (AccountDTO)accountForm.get("account");
		accountDTO = new AccountDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, accountDTO);
		String[][] messageArgValues = {{accountDTO.getUniqueCode()}};
		AccountSuccessResult result = (AccountSuccessResult)accountService.add(accountDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addAccount() : Exit");
		return promptSuccessAddAccount( mapping,form, request, response);
	}
	
	
	public ActionForward promptSuccessAddAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchAccount() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}
	
	
	public ActionForward searchAccountFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchAccountFromSystemLevel():: Enter");	
		String searchSqlQuery = AccountSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = accountService.search(searchSqlQuery);
		log.info("searchAccountFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.ACCOUNT_SEARCH_RESULT, searchOperationResult);
		AccountSearchUtils.prepareSearchPage(request);
		log.info("searchAccountFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.ACCOUNT_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchAccountFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchAccountFromGroupLevel():: Enter");	
		String searchSqlQuery = AccountSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = accountService.search(searchSqlQuery);
		log.info("searchAccountFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		AccountSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.ACCOUNT_SEARCH_RESULT, searchOperationResult);	
		log.info("searchAccountFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.ACCOUNT_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyAccount(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyAccount() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyAccount() : componentId = "+ componentId);
		AccountDTO accountDTO = (AccountDTO)accountService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("account",accountDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyAccount() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_ACCOUNT);
	}
	public ActionForward modifyAccount(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyAccount() : Enter");
		DynaValidatorActionForm accountForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyAccount() : componentId = "+ componentId);
		AccountDTO accountDTO = (AccountDTO)accountService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, accountDTO);
		String[][] messageArgValues = {{accountDTO.getUniqueCode()}};
		accountService.modify(accountDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyAccount() : Exit");
		return promptSuccessAddAccount( mapping, form, request, response);
	}
	
	
	public ActionForward cancelAccountOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelAccountOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptAccountSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptAccountSearchGroupLevel(mapping,form,request,response);
		}
		return promptAccountSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchAccount(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchAccount() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
