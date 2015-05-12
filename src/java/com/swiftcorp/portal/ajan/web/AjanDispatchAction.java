/*
 * @ (#) AjanDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.ajan.web;
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
import com.swiftcorp.portal.ajan.dto.AjanDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.ajan.AjanSuccessResult;
import com.swiftcorp.portal.ajan.service.IAjanService;
import com.swiftcorp.portal.ajan.web.AjanSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class AjanDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(AjanDispatchAction.class);
	@SuppressWarnings("unused")
	private IAjanService ajanService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setAjanService(IAjanService ajanService) 
	{
		this.ajanService = ajanService;
	}
	
	public ActionForward promptAjanSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAjanSearchSystemLevel() : enter");	
		try 
		{
			AjanSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = AjanSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = ajanService.search(searchSqlQuery);
			log.info("searchAjanFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.AJAN_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			AjanSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptAjanSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the ajan search page
		return mapping.findForward(ForwardNames.AJAN_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtAjanSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAjanSearchSystemLevel() : enter");	
		try 
		{
			AjanSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = AjanSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = ajanService.search(searchSqlQuery);
			log.info("searchAjanFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.AJAN_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			AjanSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptAjanSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the ajan search page
		return mapping.findForward(ForwardNames.EXT_AJAN_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptAjanSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAjanSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the ajan
			String searchSqlQuery = AjanSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = ajanService.search(searchSqlQuery);
			log.info("searchAjanFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.AJAN_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			AjanSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptAjanSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the ajan search page
		return mapping.findForward(ForwardNames.AJAN_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddAjan(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddAjanHome() : enter");		
		DynaValidatorActionForm ajanForm = (DynaValidatorActionForm) form;
		ajanForm.set("ajan",new AjanDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_AJAN);
	}
	public ActionForward addAjan(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addAjan() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm ajanForm = (DynaValidatorActionForm) form;
		AjanDTO ajanDTO = (AjanDTO)ajanForm.get("ajan");
		ajanDTO = new AjanDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, ajanDTO);
		String[][] messageArgValues = {{ajanDTO.getUniqueCode()}};
		AjanSuccessResult result = (AjanSuccessResult)ajanService.add(ajanDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addAjan() : Exit");
		return promptSuccessAddAjan( mapping,form, request, response);
	}
	
	public ActionForward searchAjanFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchAjanFromSystemLevel():: Enter");	
		String searchSqlQuery = AjanSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = ajanService.search(searchSqlQuery);
		log.info("searchAjanFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.AJAN_SEARCH_RESULT, searchOperationResult);
		AjanSearchUtils.prepareSearchPage(request);
		log.info("searchAjanFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.AJAN_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchAjanFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchAjanFromGroupLevel():: Enter");	
		String searchSqlQuery = AjanSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = ajanService.search(searchSqlQuery);
		log.info("searchAjanFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		AjanSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.AJAN_SEARCH_RESULT, searchOperationResult);	
		log.info("searchAjanFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.AJAN_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyAjan(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyAjan() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyAjan() : componentId = "+ componentId);
		AjanDTO ajanDTO = (AjanDTO)ajanService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("ajan",ajanDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyAjan() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_AJAN);
	}
	public ActionForward modifyAjan(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyAjan() : Enter");
		DynaValidatorActionForm ajanForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyAjan() : componentId = "+ componentId);
		AjanDTO ajanDTO = (AjanDTO)ajanService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, ajanDTO);
		
		String[][] messageArgValues = {{ajanDTO.getUniqueCode()}};
		ajanService.modify(ajanDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyAjan() : Exit");
		return promptSuccessAddAjan( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddAjan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitAjan() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelAjanOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelAjanOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptAjanSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptAjanSearchGroupLevel(mapping,form,request,response);
		}
		return promptAjanSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchAjan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchAjan() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
