/*
 * @ (#) InfoDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.info.web;
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
import com.swiftcorp.portal.info.dto.InfoDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.info.InfoSuccessResult;
import com.swiftcorp.portal.info.service.IInfoService;
import com.swiftcorp.portal.info.web.InfoSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class InfoDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(InfoDispatchAction.class);
	@SuppressWarnings("unused")
	private IInfoService infoService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setInfoService(IInfoService infoService) 
	{
		this.infoService = infoService;
	}
	
	public ActionForward promptInfoSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptInfoSearchSystemLevel() : enter");	
		try 
		{
			InfoSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = InfoSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = infoService.search(searchSqlQuery);
			log.info("searchInfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.INFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			InfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptInfoSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the info search page
		return mapping.findForward(ForwardNames.INFO_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtInfoSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptInfoSearchSystemLevel() : enter");	
		try 
		{
			InfoSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = InfoSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = infoService.search(searchSqlQuery);
			log.info("searchInfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.INFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			InfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptInfoSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the info search page
		return mapping.findForward(ForwardNames.EXT_INFO_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptInfoSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptInfoSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the info
			String searchSqlQuery = InfoSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = infoService.search(searchSqlQuery);
			log.info("searchInfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.INFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			InfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptInfoSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the info search page
		return mapping.findForward(ForwardNames.INFO_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddInfoHome() : enter");		
		DynaValidatorActionForm infoForm = (DynaValidatorActionForm) form;
		infoForm.set("info",new InfoDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_INFO);
	}
	public ActionForward addInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addInfo() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm infoForm = (DynaValidatorActionForm) form;
		InfoDTO infoDTO = (InfoDTO)infoForm.get("info");
		infoDTO = new InfoDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, infoDTO);
		String[][] messageArgValues = {{infoDTO.getUniqueCode()}};
		InfoSuccessResult result = (InfoSuccessResult)infoService.add(infoDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addInfo() : Exit");
		return promptSuccessAddInfo( mapping,form, request, response);
	}
	
	public ActionForward searchInfoFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchInfoFromSystemLevel():: Enter");	
		String searchSqlQuery = InfoSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = infoService.search(searchSqlQuery);
		log.info("searchInfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.INFO_SEARCH_RESULT, searchOperationResult);
		InfoSearchUtils.prepareSearchPage(request);
		log.info("searchInfoFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.INFO_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchInfoFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchInfoFromGroupLevel():: Enter");	
		String searchSqlQuery = InfoSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = infoService.search(searchSqlQuery);
		log.info("searchInfoFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		InfoSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.INFO_SEARCH_RESULT, searchOperationResult);	
		log.info("searchInfoFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.INFO_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyInfo() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyInfo() : componentId = "+ componentId);
		InfoDTO infoDTO = (InfoDTO)infoService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("info",infoDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyInfo() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_INFO);
	}
	public ActionForward modifyInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyInfo() : Enter");
		DynaValidatorActionForm infoForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyInfo() : componentId = "+ componentId);
		InfoDTO infoDTO = (InfoDTO)infoService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, infoDTO);
		
		String[][] messageArgValues = {{infoDTO.getUniqueCode()}};
		infoService.modify(infoDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyInfo() : Exit");
		return promptSuccessAddInfo( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitInfo() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelInfoOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelInfoOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptInfoSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptInfoSearchGroupLevel(mapping,form,request,response);
		}
		return promptInfoSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchInfo() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
