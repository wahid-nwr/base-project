/*
 * @ (#) CoveringDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.covering.web;
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
import com.swiftcorp.portal.covering.dto.CoveringDTO;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.covering.CoveringSuccessResult;
import com.swiftcorp.portal.covering.service.ICoveringService;
import com.swiftcorp.portal.covering.web.CoveringSearchUtils;
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class CoveringDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(CoveringDispatchAction.class);
	@SuppressWarnings("unused")
	private ICoveringService coveringService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setCoveringService(ICoveringService coveringService) 
	{
		this.coveringService = coveringService;
	}
	
	public ActionForward promptCoveringSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptCoveringSearchSystemLevel() : enter");	
		try 
		{
			CoveringSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = CoveringSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = coveringService.search(searchSqlQuery);
			log.info("searchCoveringFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.COVERING_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			CoveringSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptCoveringSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the covering search page
		return mapping.findForward(ForwardNames.COVERING_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtCoveringSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptCoveringSearchSystemLevel() : enter");	
		try 
		{
			CoveringSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = CoveringSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = coveringService.search(searchSqlQuery);
			log.info("searchCoveringFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.COVERING_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			CoveringSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptCoveringSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the covering search page
		return mapping.findForward(ForwardNames.EXT_COVERING_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptCoveringSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptCoveringSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the covering
			String searchSqlQuery = CoveringSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = coveringService.search(searchSqlQuery);
			log.info("searchCoveringFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.COVERING_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			CoveringSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptCoveringSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the covering search page
		return mapping.findForward(ForwardNames.COVERING_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddCovering(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddCoveringHome() : enter");		
		DynaValidatorActionForm coveringForm = (DynaValidatorActionForm) form;
		coveringForm.set("covering",new CoveringDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_COVERING);
	}
	public ActionForward addCovering(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addCovering() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm coveringForm = (DynaValidatorActionForm) form;
		CoveringDTO coveringDTO = (CoveringDTO)coveringForm.get("covering");
		coveringDTO = new CoveringDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, coveringDTO);
		String[][] messageArgValues = {{coveringDTO.getUniqueCode()}};
		CoveringSuccessResult result = (CoveringSuccessResult)coveringService.add(coveringDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addCovering() : Exit");
		return promptSuccessAddCovering( mapping,form, request, response);
	}
	
	public ActionForward searchCoveringFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchCoveringFromSystemLevel():: Enter");	
		String searchSqlQuery = CoveringSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = coveringService.search(searchSqlQuery);
		log.info("searchCoveringFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.COVERING_SEARCH_RESULT, searchOperationResult);
		CoveringSearchUtils.prepareSearchPage(request);
		log.info("searchCoveringFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.COVERING_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchCoveringFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchCoveringFromGroupLevel():: Enter");	
		String searchSqlQuery = CoveringSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = coveringService.search(searchSqlQuery);
		log.info("searchCoveringFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		CoveringSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.COVERING_SEARCH_RESULT, searchOperationResult);	
		log.info("searchCoveringFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.COVERING_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyCovering(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyCovering() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyCovering() : componentId = "+ componentId);
		CoveringDTO coveringDTO = (CoveringDTO)coveringService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("covering",coveringDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyCovering() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_COVERING);
	}
	public ActionForward modifyCovering(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyCovering() : Enter");
		DynaValidatorActionForm coveringForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyCovering() : componentId = "+ componentId);
		CoveringDTO coveringDTO = (CoveringDTO)coveringService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, coveringDTO);
		
		String[][] messageArgValues = {{coveringDTO.getUniqueCode()}};
		coveringService.modify(coveringDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyCovering() : Exit");
		return promptSuccessAddCovering( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddCovering(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitCovering() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelCoveringOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelCoveringOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptCoveringSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptCoveringSearchGroupLevel(mapping,form,request,response);
		}
		return promptCoveringSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchCovering(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchCovering() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
