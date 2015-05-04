/*
 * @ (#) DcrreportDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.dcrreport.web;
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
import com.swiftcorp.portal.dcrreport.DcrReportSuccessResult;
import com.swiftcorp.portal.dcrreport.dto.DcrReportDTO;
import com.swiftcorp.portal.dcrreport.service.IDcrReportService;
import com.swiftcorp.portal.dcrreport.web.DcrreportSearchUtils;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.user.dto.UserDTO;
/**
 * @author soma
 * @since Sep 22, 2008
 */
public class DcrreportDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(DcrreportDispatchAction.class);
	@SuppressWarnings("unused")
	private IDcrReportService dcrreportService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setDcrreportService(IDcrReportService dcrreportService) 
	{
		this.dcrreportService = dcrreportService;
	}
	
	public ActionForward promptDcrreportSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptDcrreportSearchSystemLevel() : enter");	
		try 
		{
			DcrreportSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = DcrreportSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = dcrreportService.search(searchSqlQuery);
			log.info("searchDcrreportFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.DCRREPORT_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			DcrreportSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptDcrreportSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the dcrreport search page
		return mapping.findForward(ForwardNames.DCRREPORT_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtDcrReportSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptDcrreportSearchSystemLevel() : enter");	
		try 
		{
			DcrreportSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = DcrreportSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = dcrreportService.search(searchSqlQuery);
			log.info("searchDcrreportFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.DCRREPORT_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			DcrreportSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptDcrreportSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the dcrreport search page
		return mapping.findForward(ForwardNames.DCRREPORT_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptDcrreportSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptDcrreportSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the dcrreport
			String searchSqlQuery = DcrreportSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = dcrreportService.search(searchSqlQuery);
			log.info("searchDcrreportFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.DCRREPORT_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			DcrreportSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptDcrreportSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the dcrreport search page
		return mapping.findForward(ForwardNames.DCRREPORT_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddDcrreport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddDcrreportHome() : enter");		
		DynaValidatorActionForm dcrreportForm = (DynaValidatorActionForm) form;
		dcrreportForm.set("dcrreport",new DcrReportDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_DCRREPORT);
	}
	public ActionForward addDcrreport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addDcrreport() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm dcrreportForm = (DynaValidatorActionForm) form;
		DcrReportDTO dcrreportDTO = (DcrReportDTO)dcrreportForm.get("dcrreport");
		String[][] messageArgValues = {{dcrreportDTO.getUniqueCode()}};
		DcrReportSuccessResult result = (DcrReportSuccessResult)dcrreportService.add(dcrreportDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addDcrreport() : Exit");
		return promptDcrreportSearchSystemLevel( mapping,form, request, response);
	}
	
	
	public ActionForward searchDcrreportFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchDcrreportFromSystemLevel():: Enter");	
		String searchSqlQuery = DcrreportSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = dcrreportService.search(searchSqlQuery);
		log.info("searchDcrreportFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.DCRREPORT_SEARCH_RESULT, searchOperationResult);
		DcrreportSearchUtils.prepareSearchPage(request);
		log.info("searchDcrreportFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.DCRREPORT_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchDcrreportFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchDcrreportFromGroupLevel():: Enter");	
		String searchSqlQuery = DcrreportSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = dcrreportService.search(searchSqlQuery);
		log.info("searchDcrreportFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		DcrreportSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.DCRREPORT_SEARCH_RESULT, searchOperationResult);	
		log.info("searchDcrreportFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.DCRREPORT_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyDcrreport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyDcrreport() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyDcrreport() : componentId = "+ componentId);
		DcrReportDTO dcrreportDTO = (DcrReportDTO)dcrreportService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("dcrreport",dcrreportDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyDcrreport() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_DCRREPORT);
	}
	public ActionForward modifyDcrreport(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyDcrreport() : Enter");
		DynaValidatorActionForm dcrreportForm = (DynaValidatorActionForm) form;
		DcrReportDTO dcrreportDTO = (DcrReportDTO)dcrreportForm.get("dcrreport");
		
		String[][] messageArgValues = {{dcrreportDTO.getUniqueCode()}};
		dcrreportService.modify(dcrreportDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyDcrreport() : Exit");
		return promptDcrreportSearchSystemLevel( mapping, form, request, response);
	}
	
	
	public ActionForward cancelDcrreportOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelDcrreportOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = 0;//usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptDcrreportSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptDcrreportSearchGroupLevel(mapping,form,request,response);
		}
		return promptDcrreportSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchDcrreport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchDcrreport() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
