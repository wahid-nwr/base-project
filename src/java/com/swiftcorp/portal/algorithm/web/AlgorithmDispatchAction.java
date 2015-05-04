/*
 * @ (#) AlgorithmDispatchAction.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.algorithm.web;

import java.util.ArrayList;
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

import com.swiftcorp.portal.algorithm.AlgorithmSuccessResult;
import com.swiftcorp.portal.algorithm.dto.AlgAnswerDTO;
import com.swiftcorp.portal.algorithm.dto.AlgQuestionBranchDTO;
import com.swiftcorp.portal.algorithm.dto.AlgQuestionDTO;
import com.swiftcorp.portal.algorithm.dto.AlgorithmDTO;
import com.swiftcorp.portal.algorithm.service.IAlgorithmService;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.dto.DTOConstants;
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
import com.swiftcorp.portal.question.dto.QDTO;
import com.swiftcorp.portal.question.dto.QuestionDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireDTO;
import com.swiftcorp.portal.question.service.IQuestionService;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author soma
 * @since Sep 22, 2008
 */
public class AlgorithmDispatchAction extends DispatchAction
{
	protected static final Log log = LogFactory.getLog ( AlgorithmDispatchAction.class );
	@SuppressWarnings("unused")
	private IAlgorithmService algorithmService;
	private IQuestionService questionService;
	
	public void setQuestionService(IQuestionService questionService) {
		this.questionService = questionService;
	}

	@SuppressWarnings("unused")
	private IGroupService groupService;
	
	private boolean isNextAlgAdd = false;
	
	public void setGroupService ( IGroupService groupService )
	{
		this.groupService = groupService;
	}
	
	public void setAlgorithmService ( IAlgorithmService algorithmService )
	{
		this.algorithmService = algorithmService;
	}
	
	public ActionForward promptAlgorithmSearchSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAlgorithmSearchSystemLevel() : enter" );
		try
		{
			AlgorithmSearchUtils.prepareSearchPage ( request );
			String searchSqlQuery = AlgorithmSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			SearchOperationResult searchOperationResult = algorithmService.search ( searchSqlQuery );
			log.info ( "searchAlgorithmFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			List<CategoryTypeDTO> categoryTypeList = this.questionService.getCategoryTypeList ();
			List<QuestionDTO> questionList = this.questionService.getList();
			List<QuestionnaireDTO> questionnaireList = this.questionService.getQuestionnaireList();
			request.getSession ().setAttribute ( SESSION_KEYS.CATEGORY_LIST, categoryTypeList );
			request.getSession ().setAttribute ( SESSION_KEYS.QUESTION_LIST, questionList );
			request.getSession ().setAttribute ( SESSION_KEYS.QUESTIONNAIRE_LIST, questionnaireList );
			request.setAttribute ( SESSION_KEYS.ALGORITHM_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			AlgorithmSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptAlgorithmSearchSystemLevel() :", e );
			throw e;
		}
		// show the algorithm search page
		return mapping.findForward ( ForwardNames.ALGORITHM_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward promptAlgorithmSearchGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAlgorithmSearchGroupLevel() : enter" );
		try
		{
			// here we want to load the algorithm
			String searchSqlQuery = AlgorithmSearchUtils.prepareSqlQuery ( request );
			SearchUtil.prepareRequest ( request );
			
			SearchOperationResult searchOperationResult = algorithmService.search ( searchSqlQuery );
			log.info ( "searchAlgorithmFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
			request.setAttribute ( SESSION_KEYS.ALGORITHM_SEARCH_RESULT, searchOperationResult );
			request.setAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW, true );
			AlgorithmSearchUtils.prepareSearchPage ( request );
		}
		catch (Exception e)
		{
			log.info ( "promptAlgorithmSearchGroupLevel() :", e );
			throw e;
		}
		// show the algorithm search page
		return mapping.findForward ( ForwardNames.ALGORITHM_SEARCH_GROUP_LEVEL );
	}
	
	public ActionForward promptAddAlgorithm ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "promptAddAlgorithmHome() : enter" );
		DynaValidatorActionForm algorithmForm = (DynaValidatorActionForm) form;
		algorithmForm.set ( "algorithm", new AlgorithmDTO () );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.ADD_OPERATION );
		return mapping.findForward ( ForwardNames.PROMPT_ADD_ALGORITHM );
	}
	
	public ActionForward addAlgorithm ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addAlgorithm() : Enter" );
		HttpSession session = request.getSession ();
		DynaValidatorActionForm algorithmForm = (DynaValidatorActionForm) form;
		AlgorithmDTO algorithmDTO = (AlgorithmDTO) algorithmForm.get ( "algorithm" );
		request.getSession().setAttribute(SESSION_KEYS.ALGORITHM_DTO_TO_ADD, algorithmDTO);
		request.getSession().setAttribute("forwardPage", "/Algorithm/Modify/FirstAlgEntry.jsp");
		return mapping.findForward ( ForwardNames.PROMPT_ADD_ALGORITHM_FIRST_ALG );
		
		/*
		String[][] messageArgValues =
		
		{
			{
				algorithmDTO.getUniqueCode ()
			}
		};
		AlgorithmSuccessResult result = (AlgorithmSuccessResult) algorithmService.add ( algorithmDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "addAlgorithm() : Exit" );
		return promptAlgorithmSearchSystemLevel ( mapping, form, request, response );
		*/
	}
	
	public ActionForward addAlgorithmWithFirstAlg (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "addAlgorithm() : Enter" );
		HttpSession session = request.getSession ();
		DynaValidatorActionForm algorithmForm = (DynaValidatorActionForm) form;
		AlgorithmDTO algorithmDTO = (AlgorithmDTO)request.getSession().getAttribute(SESSION_KEYS.ALGORITHM_DTO_TO_ADD);
		String name = request.getParameter("algQuestionName");
		String qqType = request.getParameter("qqType");
		boolean isFirstAlgModify = false;
		String questionQuestionnaireId = "";
		String algQuestionBranchNumber = request.getParameter("algQuestionBranchNumber");
		int algBranchNumber = 0;
		if(algQuestionBranchNumber!=null && !algQuestionBranchNumber.equals("null") && algQuestionBranchNumber.length()>0)
		{
			algBranchNumber = Integer.parseInt(algQuestionBranchNumber);
		}
		int questionQuestionnaire = 0;
		QDTO qdto = null;
		if(qqType!=null && !qqType.equals("null") && qqType.length()>0)
		{
			questionQuestionnaire = Integer.parseInt(qqType);
		}
		
		AlgQuestionDTO nextAlgQuestion = new AlgQuestionDTO();
		AlgQuestionDTO firstAlgQuestion = null;
		
		
		
		
		String algNextQuestionQQType = "";
		String algNextQuestionId = "";
		String algAnswer = "";
		AlgQuestionBranchDTO algQuestionBranchDTO = null;
		AlgAnswerDTO algAnswerDTO = null;
		List<AlgQuestionBranchDTO> algQuestionBranchDTOList = new ArrayList<AlgQuestionBranchDTO>();
		List<AlgAnswerDTO> algAnswerDTOList = new ArrayList<AlgAnswerDTO>();
		boolean isSameQuestion = false;
		for(int i = 0; i<algBranchNumber; i++)
		{
			algQuestionBranchDTO = new AlgQuestionBranchDTO();
			algAnswerDTOList = new ArrayList<AlgAnswerDTO>();
			algNextQuestionQQType = request.getParameter("qqType_"+i);
			if(algNextQuestionQQType!=null && !algNextQuestionQQType.equals("null") && algNextQuestionQQType.length()>0)
			{
				questionQuestionnaire = Integer.parseInt(algNextQuestionQQType);
			}
			if(questionQuestionnaire == DTOConstants.QUESTION_TYPE)
			{
				questionQuestionnaireId = request.getParameter("nextQuestion_"+i);
				System.out.println("questionQuestionnaireId::"+questionQuestionnaireId);				
			}
			else if(questionQuestionnaire == DTOConstants.QUESTIONNAIRE_TYPE)
			{
				questionQuestionnaireId = request.getParameter("nextQuestionnaire_"+i);
				System.out.println("questionQuestionnaireId::"+questionQuestionnaireId);			
			}
			if(qdto!=null)
			{
				if(qdto.getQqType()==DTOConstants.QUESTION_TYPE)
				{
					if(questionQuestionnaireId.equals(((QuestionDTO)qdto).getQuestionId()))
					{
						isSameQuestion = true;
					}
				}
				else
				{
					if(questionQuestionnaireId.equals(((QuestionnaireDTO)qdto).getQuestionnaireId()))
					{
						isSameQuestion = true;
					}
				}
			}
			if(!isSameQuestion)
			{
				if(questionQuestionnaireId!=null && !questionQuestionnaireId.equals("null") && questionQuestionnaireId.length()>0)
				qdto = (QuestionDTO)questionService.get(questionQuestionnaireId);
				log.info ("algNextQuestionQQType::"+algNextQuestionQQType+" questionQuestionnaireId::"+questionQuestionnaireId);
				nextAlgQuestion = (AlgQuestionDTO) algorithmService.getAlgQuestionByQQId(qdto);				
				if(nextAlgQuestion == null)
				{
					nextAlgQuestion = new AlgQuestionDTO();
					nextAlgQuestion.setQdto(qdto);
					nextAlgQuestion.setName(qdto.getDescription());
					algorithmService.addAlgquestion(nextAlgQuestion);
					nextAlgQuestion = (AlgQuestionDTO) algorithmService.getAlgQuestionByQQId(qdto);
					log.info ("nextAlgQuestion::"+nextAlgQuestion.getComponentId()+" name::"+nextAlgQuestion.getName());				
				}
			}
			algQuestionBranchDTO.setNextQuestion(nextAlgQuestion);
			
			algAnswer = request.getParameter("algAnswer_"+i);
			log.info ("algAnswer::"+algAnswer);	
			if(algAnswer!=null && !algAnswer.equals("null") && algAnswer.length()>0)
			{
				algAnswerDTO = new AlgAnswerDTO();
				algAnswerDTO.setAnswer1(algAnswer);
				algAnswerDTO.setAlgQuestionBranchDTO(algQuestionBranchDTO);
				if(qdto.getQqType()== DTOConstants.QUESTION_TYPE)
				{
					algAnswerDTO.setAnswerTypeDTO(((QuestionDTO)qdto).getAnswerType());
					algAnswerDTO.setQuestionDTO((QuestionDTO)qdto);
				}
				algAnswerDTO.setAlgorithmDTO(algorithmDTO);
				algAnswerDTOList.add(algAnswerDTO);
				algQuestionBranchDTO.setAlgAnswerList(algAnswerDTOList);
			}
			algQuestionBranchDTOList.add(algQuestionBranchDTO);
		}
		
		if(questionQuestionnaire == DTOConstants.QUESTION_TYPE)
		{
			questionQuestionnaireId = request.getParameter("question");
			System.out.println("questionQuestionnaireId::"+questionQuestionnaireId);
			if(questionQuestionnaireId!=null && !questionQuestionnaireId.equals("null") && questionQuestionnaireId.length()>0)
			qdto = (QuestionDTO)questionService.get(questionQuestionnaireId);
		}
		else if(questionQuestionnaire == DTOConstants.QUESTIONNAIRE_TYPE)
		{
			questionQuestionnaireId = request.getParameter("questionnaire");
			System.out.println("questionQuestionnaireId::"+questionQuestionnaireId);
			if(questionQuestionnaireId!=null && !questionQuestionnaireId.equals("null") && questionQuestionnaireId.length()>0)
			qdto = (QuestionnaireDTO)questionService.getQuestionnaire(questionQuestionnaireId);
		}
		firstAlgQuestion = (AlgQuestionDTO) algorithmService.getAlgQuestionByQQId(qdto);
		if(firstAlgQuestion!=null)
		{
			algorithmService.modifyAlgquestion(firstAlgQuestion);
			isFirstAlgModify = true;
		}
		if(	firstAlgQuestion==null || firstAlgQuestion.getComponentId()==null )
		{
			firstAlgQuestion = new AlgQuestionDTO();
			firstAlgQuestion.setName(name);
			firstAlgQuestion.setQdto(qdto);
			if(algQuestionBranchDTOList!=null && algQuestionBranchDTOList.size()>0)
			{
				firstAlgQuestion.setAlgQuestionBranchList(algQuestionBranchDTOList);
			}
			
			//algorithmService.addAlgquestion(firstAlgQuestion);
			//firstAlgQuestion = (AlgQuestionDTO) algorithmService.getAlgQuestionByQQId(qdto);
		}
		algorithmDTO.setFirstAlgQuestion(firstAlgQuestion);
		
		String[][] messageArgValues =
		{
			{
				algorithmDTO.getUniqueCode ()
			}
		};
		AlgorithmSuccessResult result = (AlgorithmSuccessResult) algorithmService.add ( algorithmDTO );
		/*if(isFirstAlgModify)
		{
			algorithmDTO.setFirstAlgQuestion(firstAlgQuestion);
			algorithmService.modify(algorithmDTO);
		}*/
		WebUtils.setSuccessMessages ( request, MessageKeys.ADD_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "addAlgorithm() : Exit" );
		algorithmDTO = (AlgorithmDTO)algorithmService.get(algorithmDTO.getAlgId());
		request.getSession().setAttribute(SESSION_KEYS.ALGORITHM_DTO_TO_ADD, algorithmDTO);
		//request.getSession().setAttribute(SESSION_KEYS.PREV_ALG_QUESTION, firstAlgQuestion);
		request.getSession().setAttribute("forwardPage", "/Algorithm/Modify/NextAlgEntry.jsp");
		return mapping.findForward ( ForwardNames.PROMPT_ADD_ALGORITHM_FIRST_ALG );
		//return promptAlgorithmSearchSystemLevel ( mapping, form, request, response );
	}
	public ActionForward addNextAlgQuestion (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
	throws Exception
	{
		log.info ( "addNextAlgQuestion():: Enter" );
		AlgorithmDTO algorithmDTO = (AlgorithmDTO)request.getSession().getAttribute(SESSION_KEYS.ALGORITHM_DTO_TO_ADD);
		String name = request.getParameter("algQuestionName");
		String qqType = request.getParameter("qqType");
		String questionQuestionnaireId = "";
		String algQuestionBranchNumber = request.getParameter("algQuestionBranchNumber");
		int algBranchNumber = 0;
		if(algQuestionBranchNumber!=null && !algQuestionBranchNumber.equals("null") && algQuestionBranchNumber.length()>0)
		{
			algBranchNumber = Integer.parseInt(algQuestionBranchNumber);
		}
		int questionQuestionnaire = 0;
		QDTO qdto = null;
		if(qqType!=null && !qqType.equals("null") && qqType.length()>0)
		{
			questionQuestionnaire = Integer.parseInt(qqType);
		}
		
		AlgQuestionDTO nextAlgQuestion = new AlgQuestionDTO();
		AlgQuestionDTO prevAlgQuestion = null;		
		
		String algNextQuestionQQType = "";
		String algNextQuestionId = "";
		String algAnswer = "";
		AlgQuestionBranchDTO algQuestionBranchDTO = null;
		AlgAnswerDTO algAnswerDTO = null;
		List<AlgQuestionBranchDTO> algQuestionBranchDTOList = new ArrayList<AlgQuestionBranchDTO>();
		List<AlgAnswerDTO> algAnswerDTOList = new ArrayList<AlgAnswerDTO>();
		boolean isSameQuestion = false;
		for(int i = 0; i<algBranchNumber; i++)
		{
			algQuestionBranchDTO = new AlgQuestionBranchDTO();
			algAnswerDTOList = new ArrayList<AlgAnswerDTO>();
			algNextQuestionQQType = request.getParameter("qqType_"+i);
			if(algNextQuestionQQType!=null && !algNextQuestionQQType.equals("null") && algNextQuestionQQType.length()>0)
			{
				questionQuestionnaire = Integer.parseInt(algNextQuestionQQType);
			}
			if(questionQuestionnaire == DTOConstants.QUESTION_TYPE)
			{
				questionQuestionnaireId = request.getParameter("nextQuestion_"+i);
				System.out.println("questionQuestionnaireId::"+questionQuestionnaireId);				
			}
			else if(questionQuestionnaire == DTOConstants.QUESTIONNAIRE_TYPE)
			{
				questionQuestionnaireId = request.getParameter("nextQuestionnaire_"+i);
				System.out.println("questionQuestionnaireId::"+questionQuestionnaireId);			
			}
			if(qdto!=null)
			{
				if(qdto.getQqType()==DTOConstants.QUESTION_TYPE)
				{
					if(questionQuestionnaireId.equals(((QuestionDTO)qdto).getQuestionId()))
					{
						isSameQuestion = true;
					}
				}
				else
				{
					if(questionQuestionnaireId.equals(((QuestionnaireDTO)qdto).getQuestionnaireId()))
					{
						isSameQuestion = true;
					}
				}
			}
			if(!isSameQuestion)
			{
				if(questionQuestionnaireId!=null && !questionQuestionnaireId.equals("null") && questionQuestionnaireId.length()>0)
				qdto = (QuestionDTO)questionService.get(questionQuestionnaireId);
				log.info ("algNextQuestionQQType::"+algNextQuestionQQType+" questionQuestionnaireId::"+questionQuestionnaireId);
				nextAlgQuestion = (AlgQuestionDTO) algorithmService.getAlgQuestionByQQId(qdto);				
				if(nextAlgQuestion == null)
				{
					nextAlgQuestion = new AlgQuestionDTO();
					nextAlgQuestion.setQdto(qdto);
					nextAlgQuestion.setName(qdto.getDescription());
					algorithmService.addAlgquestion(nextAlgQuestion);
					nextAlgQuestion = (AlgQuestionDTO) algorithmService.getAlgQuestionByQQId(qdto);
					log.info ("nextAlgQuestion::"+nextAlgQuestion.getComponentId()+" name::"+nextAlgQuestion.getName());				
				}
			}
			algQuestionBranchDTO.setNextQuestion(nextAlgQuestion);
			
			algAnswer = request.getParameter("algAnswer_"+i);
			log.info ("algAnswer::"+algAnswer);	
			if(algAnswer!=null && !algAnswer.equals("null") && algAnswer.length()>0)
			{
				algAnswerDTO = new AlgAnswerDTO();
				algAnswerDTO.setAnswer1(algAnswer);
				algAnswerDTO.setAlgQuestionBranchDTO(algQuestionBranchDTO);
				if(qdto.getQqType()== DTOConstants.QUESTION_TYPE)
				{
					AnswerTypeDTO answerTypeDTO = ( (QuestionDTO)qdto ).getAnswerType();
					answerTypeDTO = (AnswerTypeDTO) questionService.getAnswerType( answerTypeDTO.getComponentId()  );
					
					algAnswerDTO.setAnswerTypeDTO( answerTypeDTO );
					algAnswerDTO.setQuestionDTO((QuestionDTO)qdto);
				}
				algAnswerDTO.setAlgorithmDTO(algorithmDTO);
				algAnswerDTOList.add(algAnswerDTO);
				algQuestionBranchDTO.setAlgAnswerList(algAnswerDTOList);
			}
			algQuestionBranchDTOList.add(algQuestionBranchDTO);
		}
		
		if(questionQuestionnaire == DTOConstants.QUESTION_TYPE)
		{
			questionQuestionnaireId = request.getParameter("question");
			System.out.println("questionQuestionnaireId::"+questionQuestionnaireId);
			if(questionQuestionnaireId!=null && !questionQuestionnaireId.equals("null") && questionQuestionnaireId.length()>0)
			qdto = (QuestionDTO)questionService.get(questionQuestionnaireId);
		}
		else if(questionQuestionnaire == DTOConstants.QUESTIONNAIRE_TYPE)
		{
			questionQuestionnaireId = request.getParameter("questionnaire");
			System.out.println("questionQuestionnaireId::"+questionQuestionnaireId);
			if(questionQuestionnaireId!=null && !questionQuestionnaireId.equals("null") && questionQuestionnaireId.length()>0)
			qdto = (QuestionnaireDTO)questionService.getQuestionnaire(questionQuestionnaireId);
		}
		prevAlgQuestion = (AlgQuestionDTO) algorithmService.getAlgQuestionByQQId(qdto);
		if(prevAlgQuestion==null)
		{
			prevAlgQuestion = new AlgQuestionDTO();
			prevAlgQuestion.setName(name);
			prevAlgQuestion.setQdto(qdto);
			
			prevAlgQuestion.setAlgQuestionBranchList( algQuestionBranchDTOList );
			algorithmService.addAlgquestion( prevAlgQuestion );
		}
		else if ( algQuestionBranchDTOList!=null && algQuestionBranchDTOList.size()>0 )
		{
			List<AlgQuestionBranchDTO> algBranchListFromDB = prevAlgQuestion.getAlgQuestionBranchList();
			algBranchListFromDB.addAll( algQuestionBranchDTOList );
			prevAlgQuestion.setAlgQuestionBranchList( algBranchListFromDB );
			
			algorithmService.modifyAlgquestion(prevAlgQuestion);
		}
		
		log.info("qdto is null::" + (qdto==null));	
			//firstAlgQuestion = (AlgQuestionDTO) algorithmService.getAlgQuestionByQQId(qdto);
		//algorithmService.removeAlgquestion(prevAlgQuestion);
		//prevAlgQuestion.setComponentId(null);
		
		request.getSession().setAttribute("forwardPage", "/Algorithm/Modify/NextAlgEntry.jsp");
		return mapping.findForward ( ForwardNames.PROMPT_ADD_ALGORITHM_FIRST_ALG );
	}
	
	public ActionForward searchAlgorithmFromSystemLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchAlgorithmFromSystemLevel():: Enter" );
		String searchSqlQuery = AlgorithmSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = algorithmService.search ( searchSqlQuery );
		log.info ( "searchAlgorithmFromSystemLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		request.setAttribute ( SESSION_KEYS.ALGORITHM_SEARCH_RESULT, searchOperationResult );
		AlgorithmSearchUtils.prepareSearchPage ( request );
		log.info ( "searchAlgorithmFromSystemLevel()::Exit" );
		return mapping.findForward ( ForwardNames.ALGORITHM_SEARCH_SYSTEM_LEVEL );
	}
	
	public ActionForward searchAlgorithmFromGroupLevel ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException
	{
		log.info ( "searchAlgorithmFromGroupLevel():: Enter" );
		String searchSqlQuery = AlgorithmSearchUtils.prepareSqlQuery ( request );
		SearchUtil.prepareRequest ( request );
		
		SearchOperationResult searchOperationResult = algorithmService.search ( searchSqlQuery );
		log.info ( "searchAlgorithmFromGroupLevel():: searchOperationResult> size = " + searchOperationResult.getTotalRowCount () );
		AlgorithmSearchUtils.prepareSearchPage ( request );
		request.setAttribute ( SESSION_KEYS.ALGORITHM_SEARCH_RESULT, searchOperationResult );
		log.info ( "searchAlgorithmFromGroupLevel()::Exit" );
		return mapping.findForward ( ForwardNames.ALGORITHM_SEARCH_GROUP_LEVEL );
	}
	
	/**
	 * Needs authenticated session to exist
	 */
	public ActionForward promptModifyAlgorithm ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws RuntimeException, Exception
	{
		log.info ( "promptModifyAlgorithm() : Enter" );
		Long componentId = WebUtils.getComponentId ( request );
		log.info ( "promptModifyAlgorithm() : componentId = " + componentId );
		AlgorithmDTO algorithmDTO = (AlgorithmDTO) algorithmService.get ( componentId );
		DynaValidatorActionForm dynaValidatorActionForm = (DynaValidatorActionForm) form;
		dynaValidatorActionForm.set ( "algorithm", algorithmDTO );
		request.getSession ().setAttribute ( SESSION_KEYS.OPERATION_TYPE, GlobalConstants.MODIFY_OPERATION );
		log.info ( "promptModifyAlgorithm() : Exit" );
		return mapping.findForward ( ForwardNames.PROMPT_MODIFY_ALGORITHM );
	}
	
	public ActionForward modifyAlgorithm ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "modifyAlgorithm() : Enter" );
		DynaValidatorActionForm algorithmForm = (DynaValidatorActionForm) form;
		AlgorithmDTO algorithmDTO = (AlgorithmDTO) algorithmForm.get ( "algorithm" );
		
		String[][] messageArgValues =
		{
			{
				algorithmDTO.getUniqueCode ()
			}
		};
		algorithmService.modify ( algorithmDTO );
		WebUtils.setSuccessMessages ( request, MessageKeys.MODIFY_SUCCESS_MESSAGE_KEYS, messageArgValues );
		log.info ( "modifyAlgorithm() : Exit" );
		return promptAlgorithmSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward cancelAlgorithmOperation ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws Exception
	{
		log.info ( "cancelAlgorithmOperation() :" );
		UserDTO usrDTO = (UserDTO) request.getSession ().getAttribute ( SESSION_KEYS.USER );
		int accessLevel = 0;// usrDTO.getRole().getAccessLevel();
		
		if ( accessLevel == GlobalConstants.SYSTEM_LEVEL )
		{
			return promptAlgorithmSearchSystemLevel ( mapping, form, request, response );
		}
		else if ( accessLevel == GlobalConstants.GROUP_LEVEL )
		{
			return promptAlgorithmSearchGroupLevel ( mapping, form, request, response );
		}
		return promptAlgorithmSearchSystemLevel ( mapping, form, request, response );
	}
	
	public ActionForward cancelSearchAlgorithm ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, BusinessRuleViolationException, Exception
	{
		log.info ( "cancelSearchAlgorithm() :" );
		return ForwardUtil.getInstance ().promtHomePage ( mapping, form, request, response );
	}
	
}
