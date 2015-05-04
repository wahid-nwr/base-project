/*
 * @ (#) PatientDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.patient.web;
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
import com.swiftcorp.portal.common.util.DTOObjectReflectionUtil;
import com.swiftcorp.portal.common.util.WebUtils;
import com.swiftcorp.portal.common.web.ForwardNames;
import com.swiftcorp.portal.common.web.ForwardUtil;
import com.swiftcorp.portal.common.web.MessageKeys;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.patient.PatientSuccessResult;
import com.swiftcorp.portal.patient.dto.PatientDTO;
import com.swiftcorp.portal.patient.service.IPatientService;
import com.swiftcorp.portal.patient.web.PatientSearchUtils;
import com.swiftcorp.portal.user.dto.UserDTO;
/**
 * @author soma
 * @since Sep 22, 2008
 */
public class PatientDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog(PatientDispatchAction.class);
	@SuppressWarnings("unused")
	private IPatientService patientService;
	@SuppressWarnings("unused")
	private IGroupService  groupService ;
	public void setGroupService(IGroupService groupService) 
	{
		this.groupService = groupService;
	}
	public void setPatientService(IPatientService patientService) 
	{
		this.patientService = patientService;
	}
	
	public ActionForward promptPatientSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptPatientSearchSystemLevel() : enter");	
		try 
		{
			PatientSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = PatientSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = patientService.search(searchSqlQuery);
			log.info("searchPatientFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.PATIENT_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			PatientSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptPatientSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the patient search page
		return mapping.findForward(ForwardNames.PATIENT_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward promptExtPatientSearchSystemLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptPatientSearchSystemLevel() : enter");	
		try 
		{
			PatientSearchUtils.prepareSearchPage(request);
			String searchSqlQuery = PatientSearchUtils.prepareSqlQuery(request);
			SearchUtil.prepareRequest(request);			
			SearchOperationResult searchOperationResult = patientService.search(searchSqlQuery);
			log.info("searchPatientFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			System.out.println("OPERATION RESULT SIZE::"+searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.PATIENT_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			PatientSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptPatientSearchSystemLevel() :",e);
		    throw e ;
		}
		// show the patient search page
		return mapping.findForward(ForwardNames.EXT_PATIENT_SEARCH_SYSTEM_LEVEL);
	}
	
	
	
	public ActionForward promptPatientSearchGroupLevel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptPatientSearchGroupLevel() : enter");	
		try 
		{
			// here we want to load the patient
			String searchSqlQuery = PatientSearchUtils.prepareSqlQuery(request);	
			SearchUtil.prepareRequest(request);
			
			SearchOperationResult searchOperationResult = patientService.search(searchSqlQuery);
			log.info("searchPatientFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
			request.setAttribute(SESSION_KEYS.PATIENT_SEARCH_RESULT, searchOperationResult);
			request.setAttribute(SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true);
			PatientSearchUtils.prepareSearchPage(request);	
		}
		catch (Exception e)
		{
			log.info("promptPatientSearchGroupLevel() :",e);
		    throw e ;
		}
		// show the patient search page
		return mapping.findForward(ForwardNames.PATIENT_SEARCH_GROUP_LEVEL);
	}
	
	public ActionForward promptAddPatient(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("promptAddPatientHome() : enter");		
		DynaValidatorActionForm patientForm = (DynaValidatorActionForm) form;
		patientForm.set("patient",new PatientDTO());
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.ADD_OPERATION);
		return mapping.findForward(ForwardNames.PROMPT_ADD_PATIENT);
	}
	public ActionForward addPatient(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info("addPatient() : Enter");
		HttpSession session = request.getSession();		
		DynaValidatorActionForm patientForm = (DynaValidatorActionForm) form;
		PatientDTO patientDTO = (PatientDTO)patientForm.get("patient");
		patientDTO = new PatientDTO();
		DTOObjectReflectionUtil.populateDTOFromRequest(request, patientDTO);
		String[][] messageArgValues = {{patientDTO.getUniqueCode()}};
		PatientSuccessResult result = (PatientSuccessResult)patientService.add(patientDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("addPatient() : Exit");
		return promptSuccessAddPatient( mapping,form, request, response);
	}
	
	public ActionForward searchPatientFromSystemLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchPatientFromSystemLevel():: Enter");	
		String searchSqlQuery = PatientSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = patientService.search(searchSqlQuery);
		log.info("searchPatientFromSystemLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		request.setAttribute(SESSION_KEYS.PATIENT_SEARCH_RESULT, searchOperationResult);
		PatientSearchUtils.prepareSearchPage(request);
		log.info("searchPatientFromSystemLevel()::Exit");	
		return mapping.findForward(ForwardNames.PATIENT_SEARCH_SYSTEM_LEVEL);
	}
	
	public ActionForward searchPatientFromGroupLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException
	{		
		log.info("searchPatientFromGroupLevel():: Enter");	
		String searchSqlQuery = PatientSearchUtils.prepareSqlQuery(request);
		SearchUtil.prepareRequest(request);
		
		SearchOperationResult searchOperationResult = patientService.search(searchSqlQuery);
		log.info("searchPatientFromGroupLevel():: searchOperationResult> size = "+ searchOperationResult.getTotalRowCount());
		PatientSearchUtils.prepareSearchPage(request);
		request.setAttribute(SESSION_KEYS.PATIENT_SEARCH_RESULT, searchOperationResult);	
		log.info("searchPatientFromGroupLevel()::Exit");	
		return mapping.findForward(ForwardNames.PATIENT_SEARCH_GROUP_LEVEL);
	}
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyPatient(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws RuntimeException, Exception
	{
		log.info("promptModifyPatient() : Enter");
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyPatient() : componentId = "+ componentId);
		PatientDTO patientDTO = (PatientDTO)patientService.get(componentId);
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm)form;
		dynaValidatorActionForm.set("patient",patientDTO);		
		request.getSession().setAttribute(SESSION_KEYS.OPERATION_TYPE,GlobalConstants.MODIFY_OPERATION);
		log.info("promptModifyPatient() : Exit");
		return mapping.findForward(ForwardNames.PROMPT_MODIFY_PATIENT);
	}
	public ActionForward modifyPatient(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SystemException, BusinessRuleViolationException ,Exception
	{
		log.info("modifyPatient() : Enter");
		DynaValidatorActionForm patientForm = (DynaValidatorActionForm) form;
		Long componentId = WebUtils.getComponentId(request);
		log.info("promptModifyPatient() : componentId = "+ componentId);
		PatientDTO patientDTO = (PatientDTO)patientService.get(componentId);
		DTOObjectReflectionUtil.populateDTOFromRequest(request, patientDTO);
		
		String[][] messageArgValues = {{patientDTO.getUniqueCode()}};
		patientService.modify(patientDTO);
		WebUtils.setSuccessMessages(request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		log.info("modifyPatient() : Exit");
		return promptSuccessAddPatient( mapping, form, request, response);
	}
	
	public ActionForward promptSuccessAddPatient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("successFormSubmitPatient() :");
		return mapping.findForward(ForwardNames.EXT_FORM_ADD_SUCCESS);
	}	
	
	public ActionForward cancelPatientOperation(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		log.info("cancelPatientOperation() :");		
		UserDTO usrDTO = (UserDTO)request.getSession().getAttribute(SESSION_KEYS.USER);		
		int accessLevel = usrDTO.getRole().getAccessLevel();
		
		if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
		{
			return promptPatientSearchSystemLevel(mapping,form,request,response);
		}
		else if(accessLevel == GlobalConstants.GROUP_LEVEL)
		{
			return promptPatientSearchGroupLevel(mapping,form,request,response);
		}
		return promptPatientSearchSystemLevel(mapping,form,request,response);
	}
		
	public ActionForward cancelSearchPatient(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SystemException, BusinessRuleViolationException, Exception
	{		
		log.info("cancelSearchPatient() :");
		return ForwardUtil.getInstance().promtHomePage(mapping,form,request,response);
	}
	
}
