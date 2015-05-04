/*
 * @ (#) QuestionDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.question.web;

import java.util.ArrayList;
import java.util.Hashtable;
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

import com.swiftcorp.portal.common.ApplicationConstants;
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
import com.swiftcorp.portal.group.service.IGroupService;
import com.swiftcorp.portal.question.dto.AnswerTypeDTO;
import com.swiftcorp.portal.question.dto.CategoryTypeDTO;
import com.swiftcorp.portal.question.dto.MCQOptionDTO;
import com.swiftcorp.portal.question.dto.QuestionDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireStatusDTO;
import com.swiftcorp.portal.question.dto.ValidationDTO;
import com.swiftcorp.portal.question.service.IQuestionService;
import com.swiftcorp.portal.question.validation.ValidationDTOConstants;
import com.swiftcorp.portal.role.service.IRoleService;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author soma
 * @since Sep 22, 2008
 */
public class QuestionDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog ( QuestionDispatchAction.class );
	@SuppressWarnings("unused")
	private IQuestionService questionService;
	@SuppressWarnings("unused")
	private IGroupService groupService;
	private IRoleService answerTypeService;
	
	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public void setQuestionService ( IQuestionService questionService )
	{
		this.questionService = questionService;
	}
	
	public ActionForward questionHome ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "questionHome() :" );
		return mapping.findForward ( ForwardNames.QUESTION_HOME );
	}
	
	public ActionForward promptQuestionSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptQuestionSearchSystemLevel() : enter" );
		try
		{
			QuestionSearchUtils.prepareSearchPage ( request );
			String searchSqlQuery = QuestionSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			SearchOperationResult searchOperationResult = questionService.search ( searchSqlQuery );
			log.info ( "searchQuestionFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.QUESTION_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			request.getSession ().removeAttribute ( SESSION_KEYS.QUESTION );
			QuestionSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptQuestionSearchSystemLevel() :", e );
			throw e;
		}
		// show the question search page
		return mapping.findForward ( ForwardNames.QUESTION_SEARCH_SYSTEM_LEVEL );
	}
	
	/*
	 * show questionnaire selected question to chooose order
	 */
	public ActionForward promptQuestionnaireSerialList ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptQuestionnaireSerialList() : enter" );
		try
		{
			QuestionSearchUtils.prepareQuestionnaireSearchPage ( request );
			String searchSqlQuery = QuestionSearchUtils.prepareSqlQueryForQuestionnaire ( request );
			SearchUtil.prepareRequest ( request );
			SearchOperationResult searchOperationResult = questionService.searchQuestionnaire ( searchSqlQuery );
			log.info ( "promptQuestionnaireSerialList():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.QUESTIONNAIRE_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			QuestionSearchUtils.prepareQuestionnaireSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptQuestionnaireSerialList() :", e );
			throw e;
		}
		// show the question search page
		return mapping.findForward ( ForwardNames.QUESTIONNAIRE_QUESTION_SERIAL );
	}
	
	public ActionForward promptQuestionnaireSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptQuestionnaireSearchSystemLevel() : enter" );
		try
		{
			QuestionSearchUtils.prepareQuestionnaireSearchPage ( request );
			String searchSqlQuery = QuestionSearchUtils.prepareSqlQueryForQuestionnaire ( request );
			
			SearchUtil.prepareRequest ( request );
			
			List<QuestionnaireStatusDTO> statusList = this.questionService.getStatusList ();
			request.getSession ().setAttribute ( SESSION_KEYS.QUESTIONNAIRE_STATUS_LIST, statusList );
			
			SearchOperationResult searchOperationResult = questionService.searchQuestionnaire ( searchSqlQuery );
			log.info ( "searchQuestionnaireFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.QUESTIONNAIRE_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			QuestionSearchUtils.prepareQuestionnaireSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptQuestionnaireSearchSystemLevel() :", e );
			throw e;
		}
		// show the question search page
		return mapping.findForward ( ForwardNames.QUESTIONNAIRE_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptQuestionSearchGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptQuestionSearchGroupLevel() : enter" );
		try
		{
			// here we want to load the question
			String searchSqlQuery = QuestionSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = questionService.search ( searchSqlQuery );
			log.info ( "searchQuestionFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.QUESTION_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			QuestionSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptQuestionSearchGroupLevel() :", e );
			throw e;
		}
		// show the question search page
		return mapping.findForward ( ForwardNames.QUESTION_SEARCH_GROUP_LEVEL );
	}
	
	public ActionForward promptAddQuestion ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAddQuestionHome() : enter" );
		DynaValidatorActionForm questionForm = (DynaValidatorActionForm) form;
		questionForm.set ( "question", new QuestionDTO () );
		
		List<AnswerTypeDTO> answerTypeList = this.questionService.getAnswerTypeList ();
		request.getSession ().setAttribute ( SESSION_KEYS.ANSWERTYPE_LIST, answerTypeList );
		
		List<CategoryTypeDTO> categoryTypeList = this.questionService.getCategoryTypeList ();
		request.getSession ().setAttribute ( SESSION_KEYS.CATEGORY_LIST, categoryTypeList );
		
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		return mapping.findForward ( ForwardNames.PROMPT_ADD_QUESTION );
	}
	
	// for add questionnaire
	
	public ActionForward promptAddQuestionnaire ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAddQuestionnaireHome() : enter" );
		DynaValidatorActionForm questionnaireForm = (DynaValidatorActionForm) form;
		questionnaireForm.set ( "questionnaire", new QuestionnaireDTO () );
		
		@SuppressWarnings("unused")
		HttpSession session = request.getSession ();
		
		List<QuestionDTO> questionList = questionService.getList ();
		session.setAttribute ( SESSION_KEYS.QUESTION_LIST, questionList );
		session.setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		return mapping.findForward ( ForwardNames.PROMPT_ADD_QUESTIONNAIRE );
	}
	
	public ActionForward addQuestion ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		request.setCharacterEncoding ( "UTF-8" );
		
		log.info ( "addQuestion() : Enter" );
		List mcqoptions = new ArrayList ();
		MCQOptionDTO mcqOptionDTO = null;
		String mcqOptionNumber = request.getParameter ( "mcqOptionNumber" );
		int mcqNo = 0;
		if ( mcqOptionNumber != null && !mcqOptionNumber.equals ( "null" ) && mcqOptionNumber.length () > 0 )
		{
			mcqNo = Integer.parseInt ( mcqOptionNumber );
		}
		HttpSession session = request.getSession ();
		DynaValidatorActionForm questionForm = (DynaValidatorActionForm) form;
		QuestionDTO questionDTO = (QuestionDTO) questionForm.get ( "question" );
		long categoryId = (Long) questionForm.get ( "category" );
		
		// now get the role dto from the database
		CategoryTypeDTO categoryTypeDTO = (CategoryTypeDTO) questionService.getCategory ( categoryId );
		
		if ( categoryTypeDTO == null )
		{
			throw new SystemException ( "Category is found null" );
		}
		questionDTO.setCategoryType ( categoryTypeDTO );
		
		long answerTypeId = (Long) questionForm.get ( "answerType" );
		
		AnswerTypeDTO answerTypeDTO = (AnswerTypeDTO) questionService.getAnswerType ( answerTypeId );		
		
		if ( answerTypeDTO == null )
		{
			throw new SystemException ( "Answer is found null" );
		}
		
		// encode the question name
		String questionName = questionDTO.getQuestionName ();
		String utfQuestionName = new String ( questionName.getBytes ( "UTF-8" ), "UTF-8" );
		System.out.println ( "Converted:" + questionName + " to:" + utfQuestionName );
		List<ValidationDTO> validationDTOList = this.populateValidationDTOFromReq(questionDTO,request,answerTypeDTO);
		questionDTO.setValidationDTOList(validationDTOList);
		questionDTO.setQuestionName ( utfQuestionName );
		// get questionId
		String questionId = request.getParameter ( "question.questionId" );
		questionDTO.setQuestionId ( "" + questionId );
		questionDTO.setAnswerType ( answerTypeDTO );
		for ( int i = 0; i < mcqNo; i++ )
		{
			mcqOptionDTO = new MCQOptionDTO ();
			mcqOptionDTO.setName ( request.getParameter ( "mcq" + i ) );
			mcqOptionDTO.setValue ( request.getParameter ( "value" + i ) );
			mcqOptionDTO.setQuestionDTO ( questionDTO );
			mcqoptions.add ( mcqOptionDTO );
		}
		if ( mcqoptions != null && mcqoptions.size () > 0 )
		{
			questionDTO.setMcqOptionList ( mcqoptions );
		}
		String[][] messageArgValues =
		{
			{
				questionDTO.getQuestionName ()
			}
		};
		
		try
		{
			questionService.add ( questionDTO );
		}
		catch (Exception e)
		{
			log.info ( "questionAdd() : ", e );
			throw e;
		}
		
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "addQuestion() : Exit" );
		return promptQuestionSearchSystemLevel ( mapping, form, request, response );
	}
	
	public List<ValidationDTO> populateValidationDTOFromReq(QuestionDTO questionDTO, HttpServletRequest request,AnswerTypeDTO answerTypeDTO )
	{
		int validationType = 0;
		String validationName = "";
		String validationValue = "";
		int secondValidationType = 0;
		String secondValidationName = "";
		String secondValidationValue = "";
		long answerId = answerTypeDTO.getComponentId();
		long answerTypeId = 0;
		String answerTypeName = "";
		String firstValidationTypeName = "";
		String secondValidationTypeName = "";
		
		if( answerId == ApplicationConstants.ANSSER_TYPE_DATE_INPUT)
		{
			
			answerTypeId = ValidationDTOConstants.DATE_ANSTYPE_CODE;
			answerTypeName = ValidationDTOConstants.DATE_ANSTYPE;
			firstValidationTypeName = request.getParameter("question.validationType");
			System.out.println("validationName::::::::::"+firstValidationTypeName);
			if(firstValidationTypeName!=null)
			{
				validationName = firstValidationTypeName.substring(0,firstValidationTypeName.indexOf("Validation"));
			}
			if(firstValidationTypeName!= null && firstValidationTypeName.contains("Range"))
			{
				validationValue = request.getParameter("startDate");
				validationValue += ";"+ request.getParameter("endDate");
				validationType = ValidationDTOConstants.DATE_RANGE_TYPE;
			}
			else
			{
				if(validationName.equals(ValidationDTOConstants.MAX_DATE))
				{
					validationValue = request.getParameter(ValidationDTOConstants.MAX_DATE);
					validationType = ValidationDTOConstants.MAX_DATE_TYPE;
				}
				else if(validationName.equals(ValidationDTOConstants.MIN_DATE))
				{
					validationValue = request.getParameter(ValidationDTOConstants.MIN_DATE);
					validationType = ValidationDTOConstants.MIN_DATE_TYPE;
				}
				else if(validationName.equals(ValidationDTOConstants.NOT_BEFORE))
				{
					validationValue = request.getParameter(ValidationDTOConstants.NOT_BEFORE);
					validationType = ValidationDTOConstants.NOT_BEFORE_TYPE;
				}
				else if(validationName.equals(ValidationDTOConstants.NOT_AFTER))
				{
					validationValue = request.getParameter(ValidationDTOConstants.NOT_AFTER);
					validationType = ValidationDTOConstants.NOT_AFTER_TYPE;
				}
				else if(validationName.equals(ValidationDTOConstants.PAST_DATE))
				{
					//validationValue = request.getParameter(ValidationDTOConstants.PAST_DATE);
					validationType = ValidationDTOConstants.PAST_DATE_TYPE;
				}
				else if(validationName.equals(ValidationDTOConstants.FUTURE_DATE))
				{
					//validationValue = request.getParameter(ValidationDTOConstants.FUTURE_DATE);
					validationType = ValidationDTOConstants.FUTURE_DATE_TYPE;
				}
			}			
		}
		else if (answerId == ApplicationConstants.ANSSER_TYPE_DOUBLE_INPUT )
		{
			answerTypeId = ValidationDTOConstants.DOUBLE_VALUE_ANSTYPE_CODE;
			answerTypeName = ValidationDTOConstants.DOUBLE_VALUE_ANSTYPE;
			firstValidationTypeName = request.getParameter("firstFieldValidationType");
			validationName = firstValidationTypeName;
			secondValidationName = request.getParameter("secondFieldValidationType");
			System.out.println("firstValidationTypeName::"+firstValidationTypeName+" secondValidationName::"+secondValidationName);
			if(firstValidationTypeName!=null && !firstValidationTypeName.equals("null") && firstValidationTypeName.length()>0)
			{
				if(validationName.equals(ValidationDTOConstants.FIXED_LENGTH))
				{
					validationType = ValidationDTOConstants.FIXED_LENGTH_TYPE;
				}
				else if(validationName.equals(ValidationDTOConstants.LENGTH_RANGE))
				{
					validationType = ValidationDTOConstants.LENGTH_RANGE_TYPE;
				}
				else if(validationName.equals(ValidationDTOConstants.VALUE_IN_RANGE))
				{
					validationType = ValidationDTOConstants.VALUE_IN_RANGE_TYPE;
				}
				else if(validationName.equals(ValidationDTOConstants.CONTAIN_CHARS))
				{
					validationType = ValidationDTOConstants.CONTAIN_CHARS_TYPE;
				}
				else if(validationName.equals(ValidationDTOConstants.STARTS_WITH_CHARS))
				{
					validationType = ValidationDTOConstants.STARTS_WITH_CHARS_TYPE;
				}
				if(firstValidationTypeName.contains("Range"))
				{
					validationValue = request.getParameter("first"+firstValidationTypeName+"LowerLimit");
					validationValue += ";"+ request.getParameter("first"+firstValidationTypeName+"UpperLimit");
				}
				else
				{
					validationValue = request.getParameter("first"+firstValidationTypeName+"Validation");
				}
			}
			if(secondValidationName!=null && !secondValidationName.equals("null") && secondValidationName.length()>0)
			{	
				if(secondValidationName.equals(ValidationDTOConstants.FIXED_LENGTH))
				{
					secondValidationType = ValidationDTOConstants.FIXED_LENGTH_TYPE;
				}
				else if(secondValidationName.equals(ValidationDTOConstants.LENGTH_RANGE))
				{
					secondValidationType = ValidationDTOConstants.LENGTH_RANGE_TYPE;
				}
				else if(secondValidationName.equals(ValidationDTOConstants.VALUE_IN_RANGE))
				{
					secondValidationType = ValidationDTOConstants.VALUE_IN_RANGE_TYPE;
				}
				else if(secondValidationName.equals(ValidationDTOConstants.CONTAIN_CHARS))
				{
					secondValidationType = ValidationDTOConstants.CONTAIN_CHARS_TYPE;
				}
				else if(secondValidationName.equals(ValidationDTOConstants.STARTS_WITH_CHARS))
				{
					secondValidationType = ValidationDTOConstants.STARTS_WITH_CHARS_TYPE;
				}
				
				if(secondValidationName.contains("Range"))
				{
					secondValidationValue = request.getParameter("second"+secondValidationName+"LowerLimit");//secondvalueRangeLowerLimit
					secondValidationValue += ";"+ request.getParameter("second"+secondValidationName+"UpperLimit");
				}
				else
				{
					secondValidationValue = request.getParameter("second"+secondValidationName+"Validation");
				}
			}
		}
		else
		{
			answerTypeId = ValidationDTOConstants.TEXT_ANSTYPE_CODE;
			answerTypeName = ValidationDTOConstants.TEXT_ANSTYPE;
			validationName = request.getParameter("question.validationType");
			System.out.println("validationName::::::::::"+validationName);
			if(validationName!= null && validationName.equals(ValidationDTOConstants.FIXED_LENGTH))
			{
				validationType = ValidationDTOConstants.FIXED_LENGTH_TYPE;
			}
			else if(validationName!= null && validationName.equals(ValidationDTOConstants.LENGTH_RANGE))
			{
				validationType = ValidationDTOConstants.LENGTH_RANGE_TYPE;
			}
			else if(validationName!= null && validationName.equals(ValidationDTOConstants.VALUE_IN_RANGE))
			{
				validationType = ValidationDTOConstants.VALUE_IN_RANGE_TYPE;
			}
			else if(validationName!= null && validationName.equals(ValidationDTOConstants.CONTAIN_CHARS))
			{
				validationType = ValidationDTOConstants.CONTAIN_CHARS_TYPE;
			}
			else if(validationName!= null && validationName.equals(ValidationDTOConstants.STARTS_WITH_CHARS))
			{
				validationType = ValidationDTOConstants.STARTS_WITH_CHARS_TYPE;
			}
			if(validationName!= null && validationName.contains("Range"))
			{
				validationValue = request.getParameter(validationName+"LowerLimit");
				validationValue += ";"+ request.getParameter(validationName+"UpperLimit");
			}
			else
			{
				if(validationName!= null)
				{
					validationValue = request.getParameter(validationName+"Validation");
				}
			}
		}
		ValidationDTO validationDTO = new ValidationDTO();
		
		validationDTO.setAnsTypeCode(answerTypeId);
		validationDTO.setAnsType(answerTypeName);
		System.out.println("validationType::"+validationType+" validationName:"+validationName+" secondValidationType::"+secondValidationType+" secondValidationName:"+secondValidationName);
		if(validationType>0)
		{
			validationDTO.setValidationName(validationName);
			validationDTO.setValidationValue(validationValue);
			validationDTO.setValidationType(validationType);
		}
		if(secondValidationType>0)
		{
			validationDTO.setSecondValidationName(secondValidationName);
			validationDTO.setSecondValidationType(secondValidationType);
			validationDTO.setSecondValidationValue(secondValidationValue);
		}
		validationDTO.setqId(questionDTO.getQuestionId());
		List<ValidationDTO> validationDTOList = new ArrayList<ValidationDTO>();
		validationDTOList.add(validationDTO);
		return validationDTOList;		
	}
	// For questionnaire
	
	public ActionForward addQuestionnaire ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addQuestionnaire() : Enter" );
		HttpSession session = request.getSession ();
		String[] checkbox = (String[]) request.getParameterValues ( "checkgroup" );
		
		return promptQuestionnaireSerialList ( mapping, form, request, response );
	}
	
	public ActionForward modifyQuestionnaireSerial ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifyQuestionnaire() : Enter" );
		Hashtable ht = new Hashtable ();
		String[] questionIds = null;
		String questionId = "";
		String questionSerial = "";
		String questionCompIds = "";
		String numberOfQuestion = "";
		String questionnaireStatus = "";
		long questionnaireStatusId = -1;
		java.util.Date today = new java.util.Date ();
		java.sql.Timestamp ts1 = new java.sql.Timestamp ( today.getTime () );
		
		int noOfQuestion = 0;
		int serial = -1;
		int prevSerial = 0;
		HttpSession session = request.getSession ();
		List questionListToAdd = new ArrayList ();
		List<QuestionDTO> questionList = (List<QuestionDTO>) session.getAttribute ( SESSION_KEYS.QUESTION_LIST );
		
		questionnaireStatus = request.getParameter ( "questionnaireStatus.componentId" );
		
		numberOfQuestion = request.getParameter ( "numberOfQuestion" );
		questionCompIds = request.getParameter ( "questionCompIds" );
		
		// get number of question in this questionnaire
		if ( numberOfQuestion != null && !numberOfQuestion.equals ( "null" ) && numberOfQuestion.length () > 0 )
		{
			numberOfQuestion = numberOfQuestion.replaceAll ( " ", "" );
			noOfQuestion = Integer.parseInt ( numberOfQuestion );
		}
		
		// get question ids
		if ( questionCompIds != null && !questionCompIds.equals ( "null" ) && questionCompIds.length () > 0 )
		{
			questionIds = questionCompIds.split ( "," );
		}
		for ( int i = 0; questionIds != null && i < questionIds.length; i++ )
		{
			questionSerial = request.getParameter ( "questionnaireSerial_" + questionIds[i] );
			
			if ( questionSerial != null && !questionSerial.equals ( "null" ) && questionSerial.length () > 0 )
			{
				ht.put ( questionSerial, "" + questionIds[i] );
			}
		}
		
		for ( int k = 0; ht != null && k < ht.size (); k++ )
		{
			questionId = (String) ht.get ( "" + k );
			for ( int i = 0; questionList != null && i < questionList.size (); i++ )
			{
				QuestionDTO qDTO = (QuestionDTO) questionList.get ( i );
				if ( questionId.equals ( "" + qDTO.getComponentId () ) )
				{
					
					for ( int j = 0; j < noOfQuestion; j++ )
					{
						if ( ("" + questionIds[j]).equals ( "" + qDTO.getComponentId () ) )
						{
							questionListToAdd.add ( questionList.get ( i ) );
						}
					}
				}
			}
		}
		
		DynaValidatorActionForm questionnaireForm = (DynaValidatorActionForm) form;
		QuestionnaireDTO questionnaireDTOUpdate = (QuestionnaireDTO) request.getSession ().getAttribute ( SESSION_KEYS.QUESTIONNAIRE_TO_MODIFY );
		QuestionnaireDTO questionnaireDTO = (QuestionnaireDTO) questionnaireForm.get ( "questionnaire" );
		// questionnaireDTO.setQuestionnaireStatus(questionnaireStatus);
		if ( questionnaireStatus != null && !questionnaireStatus.equals ( "null" ) && questionnaireStatus.length () > 0 )
		{
			questionnaireStatusId = Long.parseLong ( questionnaireStatus );
		}
		// get questionnaireStatus
		QuestionnaireStatusDTO questionnaireStatusDTO = (QuestionnaireStatusDTO) questionService.getQuestionnaireStatus ( questionnaireStatusId );
		questionnaireDTO.setQuestionnaireStatus ( questionnaireStatusDTO );
		questionnaireDTO.setQuestionList ( questionListToAdd );
		String questionnaireId = request.getParameter ( "questionnaire.questionnaireId" );
		
		questionnaireDTO.setQuestionnaireId ( "" + questionnaireId );
		questionnaireDTO.setQuestionnaireTimestamp ( "" + ts1 );
		
		QuestionDTO qdto = new QuestionDTO ();
		
		String[][] messageArgValues =
		{
			{
				questionnaireDTO.getQuestionnaireName ()
			}
		};
		
		try
		{
			questionService.modifyQuestionnaire ( questionnaireDTO );
		}
		catch (Exception e)
		{
			log.info ( "modifyQuestionnaire() : ", e );
			throw e;
		}
		
		WebUtils.setSuccessMessages ( request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues );
		
		// checged here to forward to question serial
		// return promptQuestionnaireSerialList (mapping, form, request,
		// response);
		return promptQuestionnaireSearchSystemLevel ( mapping, form, request, response );
		
	}
	
	public ActionForward addQuestionnaireSerial ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addQuestionnaire() : Enter" );
		Hashtable ht = new Hashtable ();
		String[] questionIds = null;
		String questionId = "";
		String questionSerial = "";
		String questionCompIds = "";
		String numberOfQuestion = "";
		String questionnaireStatus = "";
		long questionnaireStatusId = -1;
		java.util.Date today = new java.util.Date ();
		java.sql.Timestamp ts1 = new java.sql.Timestamp ( today.getTime () );
		
		int noOfQuestion = 0;
		int serial = -1;
		int prevSerial = 0;
		HttpSession session = request.getSession ();
		List questionListToAdd = new ArrayList ();
		List<QuestionDTO> questionList = (List<QuestionDTO>) session.getAttribute ( SESSION_KEYS.QUESTION_LIST );
		
		questionnaireStatus = request.getParameter ( "questionnaireStatus.componentId" );
		
		numberOfQuestion = request.getParameter ( "numberOfQuestion" );
		questionCompIds = request.getParameter ( "questionCompIds" );
		
		// get number of question
		if ( numberOfQuestion != null && !numberOfQuestion.equals ( "null" ) && numberOfQuestion.length () > 0 )
		{
			numberOfQuestion = numberOfQuestion.replaceAll ( " ", "" );
			noOfQuestion = Integer.parseInt ( numberOfQuestion );
		}
		
		// get question ids
		if ( questionCompIds != null && !questionCompIds.equals ( "null" ) && questionCompIds.length () > 0 )
		{
			questionIds = questionCompIds.split ( "," );
		}
		for ( int i = 0; questionIds != null && i < questionIds.length; i++ )
		{
			questionSerial = request.getParameter ( "questionnaireSerial_" + questionIds[i] );
			
			if ( questionSerial != null && !questionSerial.equals ( "null" ) && questionSerial.length () > 0 )
			{
				ht.put ( questionSerial, "" + questionIds[i] );
			}
		}
		
		for ( int k = 0; ht != null && k < ht.size (); k++ )
		{
			questionId = (String) ht.get ( "" + k );
			for ( int i = 0; questionList != null && i < questionList.size (); i++ )
			{
				QuestionDTO qDTO = (QuestionDTO) questionList.get ( i );
				if ( questionId.equals ( "" + qDTO.getComponentId () ) )
				{
					
					for ( int j = 0; j < noOfQuestion; j++ )
					{
						if ( ("" + questionIds[j]).equals ( "" + qDTO.getComponentId () ) )
						{
							questionListToAdd.add ( questionList.get ( i ) );
						}
					}
				}
			}
		}
		
		DynaValidatorActionForm questionnaireForm = (DynaValidatorActionForm) form;
		QuestionnaireDTO questionnaireDTO = (QuestionnaireDTO) questionnaireForm.get ( "questionnaire" );
		if ( questionnaireStatus != null && !questionnaireStatus.equals ( "null" ) && questionnaireStatus.length () > 0 )
		{
			questionnaireStatusId = Long.parseLong ( questionnaireStatus );
		}
		String questionnaireId = request.getParameter ( "questionnaire.questionnaireId" );
		questionnaireDTO.setQuestionnaireId ( "" + questionnaireId );
		// get questionnaire status
		QuestionnaireStatusDTO questionnaireStatusDTO = (QuestionnaireStatusDTO) questionService.getQuestionnaireStatus ( questionnaireStatusId );
		questionnaireDTO.setQuestionnaireStatus ( questionnaireStatusDTO );
		questionnaireDTO.setQuestionList ( questionListToAdd );
		questionnaireDTO.setQuestionnaireTimestamp ( "" + ts1 );
		
		QuestionDTO qdto = new QuestionDTO ();
		
		String[][] messageArgValues =
		{
			{
				questionnaireDTO.getQuestionnaireName ()
			}
		};
		
		try
		{
			questionService.addQuestionnaire ( questionnaireDTO );
		}
		catch (Exception e)
		{
			log.info ( "questionnaireAdd() : ", e );
			throw e;
		}
		
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "addQuestionnaire() : Exit" );
		
		// forward to search
		return promptQuestionnaireSearchSystemLevel ( mapping, form, request, response );
		
	}
	
	public ActionForward searchQuestionnaireFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchQuestionnaireFromSystemLevel():: Enter" );
		String searchSqlQuery = QuestionSearchUtils.prepareSqlQueryForQuestionnaire ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = questionService.searchQuestionnaire ( searchSqlQuery );
		log.info ( "searchQuestionnaireFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.QUESTIONNAIRE_SEARCH_RESULT, searchOperationResult );
		QuestionSearchUtils.prepareQuestionnaireSearchPage ( request );
		log.info ( "searchQuestionNAIREFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.QUESTIONNAIRE_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchQuestionFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchQuestionFromSystemLevel():: Enter" );
		String searchSqlQuery = QuestionSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = questionService.search ( searchSqlQuery );
		log.info ( "searchQuestionFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.QUESTION_SEARCH_RESULT, searchOperationResult );
		QuestionSearchUtils.prepareSearchPage ( request );
		log.info ( "searchQuestionFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.QUESTION_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchQuestionFromGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchQuestionFromGroupLevel():: Enter" );
		String searchSqlQuery = QuestionSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = questionService.search ( searchSqlQuery );
		log.info ( "searchQuestionFromGroupLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		QuestionSearchUtils.prepareSearchPage ( request );
		request.setAttribute ( SESSION_KEYS.QUESTION_SEARCH_RESULT, searchOperationResult );
		log.info ( "searchQuestionFromGroupLevel()::Exit" );
		return mapping.findForward ( ForwardNames.QUESTION_SEARCH_GROUP_LEVEL );
	}
	
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyQuestion ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws RuntimeException, Exception
	{
		log.info ( "promptModifyQuestion() : Enter" );
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptModifyQuestion() : componentId = " + componentId );
		QuestionDTO questionDTO = (QuestionDTO) questionService.get ( componentId );
		List<AnswerTypeDTO> answerTypeList = this.questionService.getAnswerTypeList ();
		request.getSession ().setAttribute ( SESSION_KEYS.ANSWERTYPE_LIST, answerTypeList );
		
		List<CategoryTypeDTO> categoryTypeList = this.questionService.getCategoryTypeList ();
		request.getSession ().setAttribute ( SESSION_KEYS.CATEGORY_LIST, categoryTypeList );
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		dynaValidatorActionForm.set ( "question", questionDTO );
		request.getSession ().setAttribute ( SESSION_KEYS.QUESTION, questionDTO );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.MODIFY_OPERATION );
		log.info ( "promptModifyQuestion() : Exit" );
		return mapping.findForward ( ForwardNames.PROMPT_MODIFY_QUESTION );
	}
	
	// Promptmodify method for questionnaire
	
	public ActionForward promptModifyQuestionnaire ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws RuntimeException, Exception
	{
		log.info ( "promptModifyQuestionnaire() : Enter" );
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptModifyQuestionnaire() : componentId = " + componentId );
		QuestionnaireDTO questionnaireDTO = (QuestionnaireDTO) questionService.getQuestionnaire ( componentId );
		
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		dynaValidatorActionForm.set ( "questionnaire", questionnaireDTO );
		List<QuestionDTO> questionList = questionService.getList ();
		request.getSession ().setAttribute ( SESSION_KEYS.QUESTION_LIST, questionList );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.MODIFY_OPERATION );
		request.getSession ().setAttribute ( SESSION_KEYS.QUESTIONNAIRE_TO_MODIFY, questionnaireDTO );
		log.info ( "promptModifyQuestionnaire() : Exit" );
		return mapping.findForward ( ForwardNames.PROMPT_MODIFY_QUESTIONNAIRE );
	}
	
	public ActionForward modifyQuestion ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifyQuestion() : Enter" );
		request.setCharacterEncoding ( "UTF-16" );
		
		String mcqOptionNumber = request.getParameter ( "mcqOptionNumber" );
		int mcqNo = 0;
		if ( mcqOptionNumber != null && !mcqOptionNumber.equals ( "null" ) && mcqOptionNumber.length () > 0 )
		{
			mcqNo = Integer.parseInt ( mcqOptionNumber );
		}
		DynaValidatorActionForm questionForm = (DynaValidatorActionForm) form;
		QuestionDTO questionsessDTO = (QuestionDTO) request.getSession ().getAttribute ( SESSION_KEYS.QUESTION_TO_MODIFY );
		QuestionDTO questionDTO = (QuestionDTO) questionForm.get ( "question" );
		questionDTO = (QuestionDTO) this.questionService.get ( questionDTO.getComponentId () );
		
		long categoryId = (Long) questionForm.get ( "category" );
		// set question id
		String questionId = request.getParameter ( "question.questionId" );
		String questionName = (String) request.getParameter ( "question.questionName" );
		questionDTO.setQuestionName ( questionName );
		questionDTO.setQuestionId ( "" + questionId );
		// now get the role dto from the database
		CategoryTypeDTO categoryTypeDTO = (CategoryTypeDTO) questionService.getCategory ( categoryId );
		
		if ( categoryTypeDTO == null )
		{
			throw new SystemException ( "Category is found null" );
		}
		questionDTO.setCategoryType ( categoryTypeDTO );
		
		long answerTypeId = (Long) questionForm.get ( "answerType" );
		
		AnswerTypeDTO answerTypeDTO = (AnswerTypeDTO) questionService.getAnswerType ( answerTypeId );
		
		if ( answerTypeDTO == null )
		{
			throw new SystemException ( "Answer is found null" );
		}
		questionDTO.setAnswerType ( answerTypeDTO );
		List<ValidationDTO> validationDTOList = questionDTO.getValidationDTOList();
		if ( validationDTOList != null && validationDTOList.size()>0 )
		{
			ValidationDTO validationDTO = validationDTOList.get(0);
			// mcqoptions.remove ( mcqOptionDTO );
			questionService.deleteValidation ( validationDTO );
			
		}
		validationDTOList = this.populateValidationDTOFromReq(questionDTO,request,answerTypeDTO);
		questionDTO.setValidationDTOList(validationDTOList);
		/*
		 * String searchSqlQuery
		 * ="select componentId,name,questionId,questionOrder from mcqOption";
		 * searchSqlQuery += " where questionId= "+questionDTO.getComponentId
		 * ()+" order by questionOrder asc"; System.out.println
		 * ("search query::"+searchSqlQuery ); SearchOperationResult
		 * searchOperationResult = questionService.search ( searchSqlQuery );
		 */
		// encode the question name
		questionName = questionDTO.getQuestionName ();
		String utfQuestionName = new String ( questionName.getBytes ( "UTF-16" ), "UTF-16" );
		System.out.println ( "Converted: !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + questionName + " to:" + utfQuestionName );
		
		questionDTO.setQuestionName ( utfQuestionName );
		
		List<MCQOptionDTO> mcqoptions = questionDTO.getMcqOptionList ();
		for ( MCQOptionDTO mcqOptionDTO : mcqoptions )
		{
			if ( mcqOptionDTO != null )
			{
				// mcqoptions.remove ( mcqOptionDTO );
				questionService.deleteMcqOption ( mcqOptionDTO );
				
			}
		}
		mcqoptions.removeAll ( mcqoptions );
		
		MCQOptionDTO mcqOptionDTO = null;
		
		for ( int i = 0; i < mcqNo; i++ )
		{
			// mcqoptions.add(questionService.getMCQDTO(Long.parseLong(request.getParameter("mcq"+i))));
			
			mcqOptionDTO = new MCQOptionDTO ();
			mcqOptionDTO.setName ( request.getParameter ( "mcq" + i ) );
			mcqOptionDTO.setValue ( request.getParameter ( "value" + i ) );
			// mcqOptionDTO.setValue ( i );
			mcqOptionDTO.setQuestionDTO ( questionDTO );
			mcqoptions.add ( mcqOptionDTO );
		}
		
		// mcqoptions = questionDTO.getMcqOptionList ();
		// System.out.println("mcq list size::::::"+questionDTO.getMcqOptionList().size());
		// mcqoptions.remove ( 0 );
		// questionDTO.setMcqOptionList ( mcqoptions );
		// System.out.println("mcq list size::::::"+questionsessDTO.getMcqOptionList().size());
		if ( answerTypeId == ApplicationConstants.MULTIPLE_CHOICE_QUESTION_TYPE && mcqoptions != null && mcqoptions.size () > 0 )
		{
			questionDTO.setMcqOptionList ( mcqoptions );
		}
		
		String[][] messageArgValues =
		{
			{
				questionDTO.getQuestionName ()
			}
		};
		questionService.modify ( questionDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "modifyQuestion() : Exit" );
		return promptQuestionSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward modifyQuestionnaire ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifyQuestionnaire() : Enter" );
		HttpSession session = request.getSession ();
		/*
		 * DynaValidatorActionForm questionnaireForm = (DynaValidatorActionForm)
		 * form; QuestionnaireDTO questionnaireDTO = (QuestionnaireDTO)
		 * questionnaireForm.get("questionnaire");
		 * 
		 * String[][] messageArgValues = { { questionnaireDTO.getUniqueCode() }
		 * }; questionService.modifyQuestionnaire(questionnaireDTO);
		 * WebUtils.setSuccessMessages(request,
		 * MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues);
		 */
		log.info ( "modifyQuestionnaire() : Exit" );
		return promptQuestionnaireSerialList ( mapping, form, request, response );
	}
	
	public ActionForward cancelQuestionOperation ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "cancelQuestionOperation() :" );
		UserDTO usrDTO = (UserDTO) request.getSession ().getAttribute ( SESSION_KEYS.USER );
		
		return promptQuestionSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward cancelQuestionnaireOperation ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "cancelQuestionnaireOperation() :" );
		UserDTO usrDTO = (UserDTO) request.getSession ().getAttribute ( SESSION_KEYS.USER );
		
		return promptQuestionnaireSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward cancelSearchQuestionnaire ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchQuestionnaire() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
	public ActionForward cancelSearchQuestion ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchQuestion() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
}
