/*
 * @ (#) DcrinfoDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.dcrinfo.web;
import java.util.List;

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
import com.swiftcorp.portal.dcrinfo.DcrinfoSuccessResult;
import com.swiftcorp.portal.dcrinfo.dto.DcrProductInfoDTO;
import com.swiftcorp.portal.dcrinfo.dto.DcrinfoDTO;
import com.swiftcorp.portal.dcrinfo.service.IDcrinfoService;
import com.swiftcorp.portal.dcrinfo.web.DcrinfoSearchUtils;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.user.dto.UserDTO;
/**
 * @author soma
 * @since Sep 22, 2008
 */
public class DcrinfoDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(DcrinfoDispatchAction.class);
	@SuppressWarnings("unused")
	private IDcrinfoService dcrinfoService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setDcrinfoService(IDcrinfoService dcrinfoService) 
	{
		this.dcrinfoService = dcrinfoService;
	}
	
	public ActionForward promptDcrinfoSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptDcrinfoSearchSystemLevel() : enter");	
		try 
		{
			DcrinfoSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = DcrinfoSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = dcrinfoService.search(searchSqlQuery);
			log.info("searchDcrinfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.DCRINFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			DcrinfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptDcrinfoSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the dcrinfo search page
		return mapping.findForward(ForwardNames.DCRINFO_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtDcrinfoSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptDcrinfoSearchSystemLevel() : enter");	
		try 
		{
			DcrinfoSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = DcrinfoSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = dcrinfoService.search(searchSqlQuery);
			log.info("searchDcrinfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.DCRINFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			DcrinfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptDcrinfoSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the dcrinfo search page
		return mapping.findForward(ForwardNames.EXT_DCRINFO_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptDcrinfoSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptDcrinfoSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the dcrinfo
			String searchSqlQuery = DcrinfoSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = dcrinfoService.search(searchSqlQuery);
			log.info("searchDcrinfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.DCRINFO_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			DcrinfoSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptDcrinfoSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the dcrinfo search page
		return mapping.findForward(ForwardNames.DCRINFO_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddDcrinfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddDcrinfoHome() : enter");		
		DynaValidatorActionForm dcrinfoForm = (DynaValidatorActionForm) form;
		dcrinfoForm.set("dcrinfo",new DcrinfoDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_DCRINFO);
	}
	public ActionForward addDcrinfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addDcrinfo() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm dcrinfoForm = (DynaValidatorActionForm) form;
		DcrinfoDTO dcrinfoDTO = (DcrinfoDTO)dcrinfoForm.get("dcrinfo");
		String[][] messageArgValues = {{dcrinfoDTO.getUniqueCode()}};
		DcrinfoSuccessResult result = (DcrinfoSuccessResult)dcrinfoService.add(dcrinfoDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addDcrinfo() : Exit");
		return promptSuccessAddDcrinfo( mapping,form, request, response);
	}
	
	public ActionForward promptSuccessAddDcrinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchDcrinfo() :");
		return mapping.findForward(ForwardNames.DCRINFO_ADD_SUCCESS);
	}
	public ActionForward searchDcrinfoFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchDcrinfoFromSystemLevel():: Enter");	
		String searchSqlQuery = DcrinfoSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = dcrinfoService.search(searchSqlQuery);
		log.info("searchDcrinfoFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.DCRINFO_SEARCH_RESULT, searchOperationResult);
		DcrinfoSearchUtils.prepareSearchPage(request);
		log.info("searchDcrinfoFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.DCRINFO_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchDcrinfoFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchDcrinfoFromGroupLevel():: Enter");	
		String searchSqlQuery = DcrinfoSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = dcrinfoService.search(searchSqlQuery);
		log.info("searchDcrinfoFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		DcrinfoSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.DCRINFO_SEARCH_RESULT, searchOperationResult);	
		log.info("searchDcrinfoFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.DCRINFO_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyDcrinfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyDcrinfo() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyDcrinfo() : componentId = "+ componentId);
		DcrinfoDTO dcrinfoDTO = (DcrinfoDTO)dcrinfoService.get(componentId);
		List<DcrProductInfoDTO> productInfoList = dcrinfoDTO.getProductInfoList();
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("dcrinfo",dcrinfoDTO);
		dynaValidatorActionForm.set("productInfoList",dcrinfoDTO.getProductInfoList());		
		request.getSession().setAttribute("productInfoList",dcrinfoDTO.getProductInfoList());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyDcrinfo() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_DCRINFO);
	}
	public ActionForward modifyDcrinfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyDcrinfo() : Enter");
		System.out.println("modifyDcrinfo() : Enter");
		DynaValidatorActionForm dcrinfoForm = (DynaValidatorActionForm) form;
		DcrinfoDTO dcrinfoDTO = (DcrinfoDTO)dcrinfoForm.get("dcrinfo");
		
		String[][] messageArgValues = {{dcrinfoDTO.getUniqueCode()}};
		dcrinfoService.modify(dcrinfoDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyDcrinfo() : Exit");
		System.out.println("modifyDcrinfo() : Exit");
		return promptSuccessAddDcrinfo( mapping, form, request, response);
	}
	
	
	public ActionForward cancelDcrinfoOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelDcrinfoOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = 0;//usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptDcrinfoSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptDcrinfoSearchGroupLevel(mapping,form,request,response);
		}
		return promptDcrinfoSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchDcrinfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchDcrinfo() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
	public ActionForward promptImport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws SystemException,
			BusinessRuleViolationException, Exception {
		log.info("cancelSearchGeo() :");
		return mapping.findForward(ForwardNames.PROMPT_IMPORT);
	}
	
}
