/*
 * @ (#) GeneralprofileDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.generalprofile.web;
import java.util.Enumeration;

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
import com.swiftcorp.portal.generalprofile.dto.GeneralprofileDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.generalprofile.GeneralprofileSuccessResult;
import com.swiftcorp.portal.generalprofile.service.IGeneralprofileService;
import com.swiftcorp.portal.generalprofile.web.GeneralprofileSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class GeneralprofileDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(GeneralprofileDispatchAction.class);
	@SuppressWarnings("unused")
	private IGeneralprofileService generalprofileService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setGeneralprofileService(IGeneralprofileService generalprofileService) 
	{
		this.generalprofileService = generalprofileService;
	}
	
	public ActionForward promptGeneralprofileSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptGeneralprofileSearchSystemLevel() : enter");	
		try 
		{
			GeneralprofileSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = GeneralprofileSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = generalprofileService.search(searchSqlQuery);
			log.info("searchGeneralprofileFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.GENERALPROFILE_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			GeneralprofileSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptGeneralprofileSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the generalprofile search page
		return mapping.findForward(ForwardNames.GENERALPROFILE_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtGeneralprofileSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptGeneralprofileSearchSystemLevel() : enter");	
		try 
		{
			GeneralprofileSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = GeneralprofileSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = generalprofileService.search(searchSqlQuery);
			log.info("searchGeneralprofileFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.GENERALPROFILE_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			GeneralprofileSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptGeneralprofileSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the generalprofile search page
		return mapping.findForward(ForwardNames.EXT_GENERALPROFILE_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptGeneralprofileSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptGeneralprofileSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the generalprofile
			String searchSqlQuery = GeneralprofileSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = generalprofileService.search(searchSqlQuery);
			log.info("searchGeneralprofileFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.GENERALPROFILE_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			GeneralprofileSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptGeneralprofileSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the generalprofile search page
		return mapping.findForward(ForwardNames.GENERALPROFILE_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddGeneralprofile(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddGeneralprofileHome() : enter");		
		DynaValidatorActionForm generalprofileForm = (DynaValidatorActionForm) form;
		generalprofileForm.set("generalprofile",new GeneralprofileDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_GENERALPROFILE);
	}
	public ActionForward addGeneralprofile(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addGeneralprofile() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm generalprofileForm = (DynaValidatorActionForm) form;
		GeneralprofileDTO generalprofileDTO = (GeneralprofileDTO)generalprofileForm.get("generalprofile");
		generalprofileDTO = new GeneralprofileDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, generalprofileDTO);
		
		Enumeration<String> en = request.getParameterNames();		
		
		String key = "";
		String value = "";
		String supplyChainActor = "";
		while(en.hasMoreElements())
		{
			key = en.nextElement();
			if(key.startsWith("cb-"))
			{
				int index = -1;
				value = key.substring(3,key.length());
				supplyChainActor += value;
				log.debug ( "functionIndex are " + value );
				
			}
		}
		generalprofileDTO.setSupplyChainActor(supplyChainActor);
		String[][] messageArgValues = {{generalprofileDTO.getUniqueCode()}};
		GeneralprofileSuccessResult result = (GeneralprofileSuccessResult)generalprofileService.add(generalprofileDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addGeneralprofile() : Exit");
		return promptSuccessAddGeneralprofile( mapping,form, request, response);
	}
	
	public ActionForward searchGeneralprofileFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchGeneralprofileFromSystemLevel():: Enter");	
		String searchSqlQuery = GeneralprofileSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = generalprofileService.search(searchSqlQuery);
		log.info("searchGeneralprofileFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.GENERALPROFILE_SEARCH_RESULT, searchOperationResult);
		GeneralprofileSearchUtils.prepareSearchPage(request);
		log.info("searchGeneralprofileFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.GENERALPROFILE_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchGeneralprofileFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchGeneralprofileFromGroupLevel():: Enter");	
		String searchSqlQuery = GeneralprofileSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = generalprofileService.search(searchSqlQuery);
		log.info("searchGeneralprofileFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		GeneralprofileSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.GENERALPROFILE_SEARCH_RESULT, searchOperationResult);	
		log.info("searchGeneralprofileFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.GENERALPROFILE_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyGeneralprofile(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyGeneralprofile() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyGeneralprofile() : componentId = "+ componentId);
		GeneralprofileDTO generalprofileDTO = (GeneralprofileDTO)generalprofileService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("generalprofile",generalprofileDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyGeneralprofile() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_GENERALPROFILE);
	}
	public ActionForward modifyGeneralprofile(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyGeneralprofile() : Enter");
		DynaValidatorActionForm generalprofileForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyGeneralprofile() : componentId = "+ componentId);
		GeneralprofileDTO generalprofileDTO = (GeneralprofileDTO)generalprofileService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, generalprofileDTO);
		Enumeration<String> en = request.getParameterNames();		
		
		String key = "";
		String value = "";
		String supplyChainActor = "";
		while(en.hasMoreElements())
		{
			key = en.nextElement();
			if(key.startsWith("cb-"))
			{
				int index = -1;
				value = key.substring(3,key.length());
				if(supplyChainActor!=null && !supplyChainActor.equals("null") && supplyChainActor.length()>0)
				{
					supplyChainActor += ","+value;
				}
				else
				{
					supplyChainActor += value;
				}
				log.debug ( "functionIndex are " + value );
				
			}
		}
		generalprofileDTO.setSupplyChainActor(supplyChainActor);
		String[][] messageArgValues = {{generalprofileDTO.getUniqueCode()}};
		generalprofileService.modify(generalprofileDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyGeneralprofile() : Exit");
		return promptSuccessAddGeneralprofile( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddGeneralprofile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitGeneralprofile() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelGeneralprofileOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelGeneralprofileOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptGeneralprofileSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptGeneralprofileSearchGroupLevel(mapping,form,request,response);
		}
		return promptGeneralprofileSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchGeneralprofile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchGeneralprofile() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
