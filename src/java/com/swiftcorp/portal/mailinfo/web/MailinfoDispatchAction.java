/*
 * @ (#) MailinfoDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.mailinfo.web;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


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
import com.swiftcorp.portal.mailinfo.dto.MailinfoDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.mailinfo.MailinfoSuccessResult;
import com.swiftcorp.portal.mailinfo.service.IMailinfoService;
import com.swiftcorp.portal.mailinfo.web.MailinfoSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class MailinfoDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(MailinfoDispatchAction.class);
	@SuppressWarnings("unused")
	private IMailinfoService mailinfoService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setMailinfoService(IMailinfoService mailinfoService) 
	{
		this.mailinfoService = mailinfoService;
	}
	
	public ActionForward initialLoad(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		JSONArray arr2 = new JSONArray();
		JSONArray arr3 = new JSONArray();
		JSONArray arr4 = new JSONArray();
		JSONObject mailObj_group = new JSONObject();
		JSONObject mailObj = new JSONObject();
		JSONArray mail_dtl = new JSONArray();	
		JSONObject mails = new JSONObject();	
		mails.put("share_mail", mailObj);
		mails.put("personal_mail", mail_dtl);
		mails.put("company_unread", arr4);
		mails.put("company_all", arr3);
		mails.put("messages", arr3);
		log.info("promptMailinfoSearchSystemLevel() : enter");	
		try 
		{
			MailinfoSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = MailinfoSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = mailinfoService.search(searchSqlQuery);
			log.info("searchMailinfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.MAILINFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.MAILINFO_INITIAL_LOAD, mails);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			MailinfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptMailinfoSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the mailinfo search page
		return mapping.findForward(ForwardNames.MAILINFO_INITIAL_LOAD);
	}
	public ActionForward inbox(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_INBOX);
	}
	public ActionForward checkMail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_CHECK_MAIL);
	}
	
	public ActionForward countInbox(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_COUNT_INBOX);
	}
	
	public ActionForward companyInbox(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_COMPANY_INBOX);
	}
	
	public ActionForward mailDtl(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_MAIL_DTL);
	}
	
	public ActionForward markAsRead(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_MARK_AS_READ);
	}
	
	public ActionForward markAsUnRead(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_MARK_AS_UNREAD);
	}
	
	public ActionForward sessionCheck(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_SESSION_CHECK);
	}
	
	public ActionForward sendMail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_SEND_MAIL);
	}
	
	public ActionForward saveAsDraft(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_SAVE_AS_DRAFT);
	}
	
	public ActionForward attachments(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_ATTACHMENTS);
	}
	
	public ActionForward emailSettings(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_EMAIL_SETTINGS);
	}
	
	public ActionForward downloadAttachment(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_EMAIL_DOWNLOAD_ATTACHMENTS);
	}
	
	public ActionForward mailSearch(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_EMAIL_SEARCH);
	}
	
	public ActionForward shareMail(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward(ForwardNames.MAILINFO_EMAIL_SHARE);
	}
	
	public ActionForward promptMailinfoSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptMailinfoSearchSystemLevel() : enter");	
		try 
		{
			MailinfoSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = MailinfoSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = mailinfoService.search(searchSqlQuery);
			log.info("searchMailinfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.MAILINFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			MailinfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptMailinfoSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the mailinfo search page
		return mapping.findForward(ForwardNames.MAILINFO_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtMailUserSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptMailinfoSearchSystemLevel() : enter");	
		try 
		{
			MailinfoSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = MailinfoSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = mailinfoService.search(searchSqlQuery);
			log.info("searchMailinfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.MAILINFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			MailinfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptMailinfoSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the mailinfo search page
		return mapping.findForward(ForwardNames.EXT_MAILINFO_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtMailinfoSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptMailinfoSearchSystemLevel() : enter");	
		try 
		{
			MailinfoSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = MailinfoSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = mailinfoService.search(searchSqlQuery);
			log.info("searchMailinfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.MAILINFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			MailinfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptMailinfoSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the mailinfo search page
		return mapping.findForward(ForwardNames.EXT_MAILINFO_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptMailinfoSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptMailinfoSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the mailinfo
			String searchSqlQuery = MailinfoSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = mailinfoService.search(searchSqlQuery);
			log.info("searchMailinfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.MAILINFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			MailinfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptMailinfoSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the mailinfo search page
		return mapping.findForward(ForwardNames.MAILINFO_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddMailinfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddMailinfoHome() : enter");		
		DynaValidatorActionForm mailinfoForm = (DynaValidatorActionForm) form;
		mailinfoForm.set("mailinfo",new MailinfoDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_MAILINFO);
	}
	public ActionForward addMailinfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addMailinfo() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm mailinfoForm = (DynaValidatorActionForm) form;
		MailinfoDTO mailinfoDTO = (MailinfoDTO)mailinfoForm.get("mailinfo");
		mailinfoDTO = new MailinfoDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, mailinfoDTO);
		String[][] messageArgValues = {{mailinfoDTO.getUniqueCode()}};
		MailinfoSuccessResult result = (MailinfoSuccessResult)mailinfoService.add(mailinfoDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addMailinfo() : Exit");
		return promptSuccessAddMailinfo( mapping,form, request, response);
	}
	
	public ActionForward searchMailinfoFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchMailinfoFromSystemLevel():: Enter");	
		String searchSqlQuery = MailinfoSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = mailinfoService.search(searchSqlQuery);
		log.info("searchMailinfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.MAILINFO_SEARCH_RESULT, searchOperationResult);
		MailinfoSearchUtils.prepareSearchPage(request);
		log.info("searchMailinfoFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.MAILINFO_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchMailinfoFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchMailinfoFromGroupLevel():: Enter");	
		String searchSqlQuery = MailinfoSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = mailinfoService.search(searchSqlQuery);
		log.info("searchMailinfoFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		MailinfoSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.MAILINFO_SEARCH_RESULT, searchOperationResult);	
		log.info("searchMailinfoFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.MAILINFO_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyMailinfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyMailinfo() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyMailinfo() : componentId = "+ componentId);
		MailinfoDTO mailinfoDTO = (MailinfoDTO)mailinfoService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("mailinfo",mailinfoDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyMailinfo() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_MAILINFO);
	}
	public ActionForward modifyMailinfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyMailinfo() : Enter");
		DynaValidatorActionForm mailinfoForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyMailinfo() : componentId = "+ componentId);
		MailinfoDTO mailinfoDTO = (MailinfoDTO)mailinfoService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, mailinfoDTO);
		
		String[][] messageArgValues = {{mailinfoDTO.getUniqueCode()}};
		mailinfoService.modify(mailinfoDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyMailinfo() : Exit");
		return promptSuccessAddMailinfo( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddMailinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitMailinfo() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelMailinfoOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelMailinfoOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptMailinfoSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptMailinfoSearchGroupLevel(mapping,form,request,response);
		}
		return promptMailinfoSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchMailinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchMailinfo() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
