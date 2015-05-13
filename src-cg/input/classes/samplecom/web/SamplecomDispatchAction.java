/*
 * @ (#) SamplecomDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.samplecom.web;

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

import com.swiftcorp.portal.account.dto.AccountDTO;
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
import com.swiftcorp.portal.samplecom.dto.SamplecomDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.samplecom.SamplecomSuccessResult;
import com.swiftcorp.portal.samplecom.service.ISamplecomService;
import com.swiftcorp.portal.samplecom.web.SamplecomSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;


/*
 * @author swift corporation
 * @since mar 3, 2011
 */

public class SamplecomDispatchAction extends DispatchAction
{

	protected static final Log log = LogFactory.getLog(SamplecomDispatchAction.class);

	@SuppressWarnings("unused")
	private ISamplecomService samplecomService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;

	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}

	public void setSamplecomService(ISamplecomService samplecomService) 
	{
		this.samplecomService = samplecomService;
	}
	
	public ActionForward promptSamplecomSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptSamplecomSearchSystemLevel() : enter");	
		try 
		{
			SamplecomSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = SamplecomSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = samplecomService.search(searchSqlQuery);
			log.info("searchSamplecomFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.SAMPLECOM_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			SamplecomSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptSamplecomSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the samplecom search page
		return mapping.findForward(ForwardNames.SAMPLECOM_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtSamplecomSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptSamplecomSearchSystemLevel() : enter");	
		try 
		{
			SamplecomSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = SamplecomSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = samplecomService.search(searchSqlQuery);
			log.info("searchSamplecomFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.SAMPLECOM_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			SamplecomSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptSamplecomSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the samplecom search page
		return mapping.findForward(ForwardNames.EXT_SAMPLECOM_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptSamplecomSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptSamplecomSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the samplecom
			String searchSqlQuery = SamplecomSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = samplecomService.search(searchSqlQuery);
			log.info("searchSamplecomFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.SAMPLECOM_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			SamplecomSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptSamplecomSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the samplecom search page
		return mapping.findForward(ForwardNames.SAMPLECOM_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddSamplecom(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddSamplecomHome() : enter");		
		DynaValidatorActionForm samplecomForm = (DynaValidatorActionForm) form;
		samplecomForm.set("samplecom",new SamplecomDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_SAMPLECOM);
	}


	public ActionForward addSamplecom(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addSamplecom() : Enter");
		HttpSession session = request.getSession();		

		DynaValidatorActionForm samplecomForm = (DynaValidatorActionForm) form;
		SamplecomDTO samplecomDTO = (SamplecomDTO)samplecomForm.get("samplecom");
		samplecomDTO = new SamplecomDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, samplecomDTO);
		String[][] messageArgValues = {{samplecomDTO.getUniqueCode()}};
		SamplecomSuccessResult result = (SamplecomSuccessResult)samplecomService.add(samplecomDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addSamplecom() : Exit");
		return promptSuccessAddSamplecom( mapping,form, request, response);
	}
	
	public ActionForward searchSamplecomFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchSamplecomFromSystemLevel():: Enter");	
		String searchSqlQuery = SamplecomSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = samplecomService.search(searchSqlQuery);
		log.info("searchSamplecomFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.SAMPLECOM_SEARCH_RESULT, searchOperationResult);
		SamplecomSearchUtils.prepareSearchPage(request);
		log.info("searchSamplecomFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.SAMPLECOM_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchSamplecomFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchSamplecomFromGroupLevel():: Enter");	
		String searchSqlQuery = SamplecomSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = samplecomService.search(searchSqlQuery);
		log.info("searchSamplecomFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		SamplecomSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.SAMPLECOM_SEARCH_RESULT, searchOperationResult);	
		log.info("searchSamplecomFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.SAMPLECOM_SEARCH_GROUP_LEVEL);
	}


	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifySamplecom(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifySamplecom() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifySamplecom() : componentId = "+ componentId);
		SamplecomDTO samplecomDTO = (SamplecomDTO)samplecomService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("samplecom",samplecomDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifySamplecom() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_SAMPLECOM);
	}

	public ActionForward modifySamplecom(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifySamplecom() : Enter");
		DynaValidatorActionForm samplecomForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifySamplecom() : componentId = "+ componentId);
		SamplecomDTO samplecomDTO = (SamplecomDTO)samplecomService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, samplecomDTO);
		
		String[][] messageArgValues = {{samplecomDTO.getUniqueCode()}};
		samplecomService.modify(samplecomDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifySamplecom() : Exit");
		return promptSuccessAddSamplecom( mapping, form, request, response);

	}
	
	public ActionForward promptSuccessAddSamplecom(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitSamplecom() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelSamplecomOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelSamplecomOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptSamplecomSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptSamplecomSearchGroupLevel(mapping,form,request,response);
		}
		return promptSamplecomSearchSystemLevel(mapping,form,request,response);
	}

		
	public ActionForward cancelSearchSamplecom(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchSamplecom() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}