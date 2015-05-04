/*
 * @ (#) QuestionServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.question.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.common.util.StringUtils;
import com.swiftcorp.portal.question.QuestionSuccessResult;
import com.swiftcorp.portal.question.QuestionnaireSuccessResult;
import com.swiftcorp.portal.question.dao.IQuestionDAO;
import com.swiftcorp.portal.question.dao.IQuestionDAO.QuestionSortBy;
import com.swiftcorp.portal.question.dto.AnswerTypeDTO;
import com.swiftcorp.portal.question.dto.CategoryTypeDTO;
import com.swiftcorp.portal.question.dto.MCQOptionDTO;
import com.swiftcorp.portal.question.dto.QuestionDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireStatusDTO;
import com.swiftcorp.portal.question.dto.ValidationDTO;
import com.swiftcorp.portal.question.exception.QuestionAlreadyExistsException;
import com.swiftcorp.portal.question.exception.QuestionNotFoundException;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class QuestionServiceImpl implements IQuestionService
{
	private static final Log logger = LogFactory.getLog ( QuestionServiceImpl.class );
	
	private IQuestionDAO questionDAO;
	private ISearcher searcher;
	
	public void setQuestionDAO ( IQuestionDAO questionDAO )
	{
		this.questionDAO = questionDAO;
	}
	
	public void setSearcher ( ISearcher searcher )
	{
		this.searcher = searcher;
	}
	
	public BusinessOperationResult add ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(QuestionDTO) : Enter" );
		QuestionDTO questionDTO = null;
		
		QuestionSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof QuestionDTO )
		{
			questionDTO = (QuestionDTO) genericDTO;
			
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		
		boolean isExist = checkUniqueCodeDuplicacy ( questionDTO );
		logger.info ( "add(QuestionDTO) : isExist = " + isExist );
		if ( isExist )
		{
			throw new QuestionAlreadyExistsException ( "exception.QuestionAlreadyExistException" );
		}
		
		logger.info ( "add(QuestionDTO) : componentId = " + questionDTO.getComponentId () );
		
		try
		{
			successResult = questionDAO.add ( questionDTO );
			logger.info ( "Execute add(QuestionDTO)" );
		}
		catch (Exception e)
		{
			logger.info ( "add(QuestionDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(QuestionDTO) : Exit" );
		return successResult;
	}
	
	// For questionnaire
	
	public BusinessOperationResult addQuestionnaire ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(QuestionnaireDTO) : Enter" );
		QuestionnaireDTO questionnaireDTO = null;
		
		QuestionnaireSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof QuestionnaireDTO )
		{
			questionnaireDTO = (QuestionnaireDTO) genericDTO;
			
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		
		boolean isExist = checkUniqueCodeDuplicacy ( questionnaireDTO );
		logger.info ( "add(QuestionnaireDTO) : isExist = " + isExist );
		if ( isExist )
		{
			throw new QuestionAlreadyExistsException ( "exception.QuestionnaireAlreadyExistException" );
		}
		
		logger.info ( "add(QuestionnaireDTO) : componentId = " + questionnaireDTO.getComponentId () );
		
		try
		{
			successResult = questionDAO.addQuestionnaire ( questionnaireDTO );
			logger.info ( "Execute add(QuestionnaireDTO)" );
		}
		catch (Exception e)
		{
			logger.info ( "add(QuestionnaireDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(QuestionnaireDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modifyQuestionnaire ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modify(QuestionnaireDTO) : Enter" );
		QuestionnaireDTO questionnaireDTO = null;
		QuestionnaireSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof QuestionnaireDTO )
		{
			questionnaireDTO = (QuestionnaireDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modify(QuestionnaireDTO) : componentId = " + questionnaireDTO.getComponentId () );
		
		boolean isExist = checkQuestionnaireUniqueCodeDuplicacyForUpdate ( questionnaireDTO );
		logger.info ( "modify(QuestionDTO) : isExist = " + isExist );
		if ( isExist )
		{
			throw new QuestionAlreadyExistsException ( "exception.QuestionAlreadyExistException" );
		}
		
		try
		{
			successResult = questionDAO.modifyQuestionnaire ( questionnaireDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modify(QuestionnaireDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modify(QuestionnaireDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modify ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modify(QuestionDTO) : Enter" );
		QuestionDTO questionDTO = null;
		QuestionSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof QuestionDTO )
		{
			questionDTO = (QuestionDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modify(QuestionDTO) : componentId = " + questionDTO.getComponentId () );
		
		boolean isExist = checkUniqueCodeDuplicacyForUpdate ( questionDTO );
		logger.info ( "modify(QuestionDTO) : isExist = " + isExist );
		if ( isExist )
		{
			throw new QuestionAlreadyExistsException ( "exception.QuestionAlreadyExistException" );
		}
		
		try
		{
			successResult = questionDAO.modify ( questionDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modify(QuestionDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modify(QuestionDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult remove ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "remove(QuestionDTO) : Enter" );
		QuestionSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "DTO MUST NOT NULL." );
		}
		
		QuestionDTO questionDTO = null;
		if ( genericDTO instanceof QuestionDTO )
		{
			questionDTO = (QuestionDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "INVALID DTO TYPE. MUST BE INSTANCE OF QuestionDTO." );
		}
		
		logger.info ( "remove(QuestionDTO) : code = " + questionDTO.getUniqueCode () );
		logger.info ( "remove(QuestionDTO) : componentId = " + questionDTO.getComponentId () );
		
		try
		{
			successResult = questionDAO.remove ( questionDTO );
		}
		catch (Exception e)
		{
			logger.info ( "remove(QuestionDTO) :", e );
			throw new SystemException ( e );
		}
		logger.info ( "remove(QuestionDTO) : Exit" );
		return successResult;
	}
	
	public QuestionSuccessResult deleteMcqOption ( MCQOptionDTO mcqOptionDTO )
			throws SystemException
	{
		logger.info ( "remove(QuestionDTO) : Enter" );
		QuestionSuccessResult successResult;
		
		logger.info ( "remove(QuestionDTO) : code = " + mcqOptionDTO.getUniqueCode () );
		logger.info ( "remove(QuestionDTO) : componentId = " + mcqOptionDTO.getComponentId () );
		
		try
		{
			successResult = questionDAO.deleteMcqOption ( mcqOptionDTO );
		}
		catch (Exception e)
		{
			logger.info ( "remove(mcqOptionDTO) :", e );
			throw new SystemException ( e );
		}
		logger.info ( "remove(mcqOptionDTO) : Exit" );
		return successResult;
	}
	
	public QuestionSuccessResult deleteValidation ( ValidationDTO validationDTO )
	throws SystemException
	{
		logger.info ( "remove(validationDTO) : Enter" );
		QuestionSuccessResult successResult;
		
		logger.info ( "remove(validationDTO) : code = " + validationDTO.getUniqueCode () );
		logger.info ( "remove(validationDTO) : componentId = " + validationDTO.getComponentId () );
		
		try
		{
			successResult = questionDAO.deleteValidation ( validationDTO );
		}
		catch (Exception e)
		{
			logger.info ( "remove(validationDTO) :", e );
			throw new SystemException ( e );
		}
		logger.info ( "remove(validationDTO) : Exit" );
		return successResult;
	}
	// For questionnaire
	
	public GenericDTO getQuestionnaire ( Long componentId )
			throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		QuestionnaireDTO questionnaireDTO = null;
		try
		{
			questionnaireDTO = questionDAO.getQuestionnaire ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return questionnaireDTO;
	}
	
	public GenericDTO getQuestionnaire (String questionnaireId)
	throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : questionnaireId = " + questionnaireId );
		QuestionnaireDTO questionnaireDTO = null;
		try
		{
			questionnaireDTO = questionDAO.getQuestionnaire ( questionnaireId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return questionnaireDTO;
	}
	
	public List getMCQList ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		List mcqList = null;
		try
		{
			mcqList = questionDAO.getMCQList ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return mcqList;
	}
	
	public GenericDTO get ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		QuestionDTO questionDTO = null;
		try
		{
			questionDTO = questionDAO.get ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return questionDTO;
	}
	
	// For Category id
	
	public GenericDTO getCategory ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		CategoryTypeDTO categoryTypeDTO = null;
		try
		{
			categoryTypeDTO = questionDAO.getCategory ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return categoryTypeDTO;
	}
	
	public GenericDTO getAnswerType ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		AnswerTypeDTO answerTypeDTO = null;
		try
		{
			answerTypeDTO = questionDAO.getAnswerType ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return answerTypeDTO;
	}
	
	public GenericDTO getQuestionnaireStatus ( Long componentId )
			throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		QuestionnaireStatusDTO questionnaireStatusDTO = null;
		try
		{
			questionnaireStatusDTO = questionDAO.getQuestionnaireStatus ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return questionnaireStatusDTO;
	}
	
	// For ques
	
	public GenericDTO get ( String uniqueCode )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "get(code) : Enter" );
		QuestionDTO questionDTO = null;
		try
		{
			questionDTO = questionDAO.get ( uniqueCode );
			if ( questionDTO == null )
			{
				throw new QuestionNotFoundException ( "question.not.found" );
			}
		}
		catch (Exception e)
		{
			logger.error ( "get(code)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(code) : Exit" );
		return questionDTO;
	}
	
	public List<QuestionDTO> getList ( Long groupId, QuestionSortBy sortby )
			throws SystemException
	{
		logger.info ( "getList(groupId,sortby) : Enter" );
		ArrayList<QuestionDTO> result = null;
		try
		{
			result = questionDAO.getList ( groupId, sortby );
		}
		catch (Exception e)
		{
			logger.error ( "getList(groupId,sortby)", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList(groupId,sortby) : Exit" );
		return result;
	}
	
	public List<QuestionDTO> getList ( ) throws SystemException
	{
		logger.info ( "getList() : Enter" );
		ArrayList<QuestionDTO> result = null;
		try
		{
			result = questionDAO.getList ();
		}
		catch (Exception e)
		{
			logger.error ( "getList()", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList() : Exit" );
		return result;
	}
	
	public List<QuestionnaireDTO> getQuestionnaireList ( ) throws SystemException
	{
		logger.info ( "getList() : Enter" );
		ArrayList<QuestionnaireDTO> result = null;
		try
		{
			result = questionDAO.getQuestionnaireList ();
		}
		catch (Exception e)
		{
			logger.error ( "getList()", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList() : Exit" );
		return result;
	}
	
	public SearchOperationResult search ( String query )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "search() : Enter" );
		SearchOperationResult searchResult = null;
		try
		{
			searchResult = searcher.search ( query );
		}
		catch (InvalidSQLSyntaxException e)
		{
			logger.info ( "search() :", e );
			throw e;
		}
		catch (SystemException e)
		{
			logger.info ( "search() :", e );
			throw e;
		}
		logger.info ( "search() : Exit" );
		return searchResult;
	}
	
	// Search for questionnaire
	
	public SearchOperationResult searchQuestionnaire ( String query )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "search() : Enter" );
		SearchOperationResult searchResult = null;
		try
		{
			searchResult = searcher.searchQuestionnaire ( query );
		}
		catch (InvalidSQLSyntaxException e)
		{
			logger.info ( "search() :", e );
			throw e;
		}
		catch (SystemException e)
		{
			logger.info ( "search() :", e );
			throw e;
		}
		logger.info ( "search() : Exit" );
		return searchResult;
	}
	
	// check duplicasy for questionnaire to add
	private boolean checkUniqueCodeDuplicacy ( QuestionnaireDTO questionnaireDTO )
			throws SystemException
	{
		boolean isExist = false;
		try
		{
			// get questionnaire with same questionnaire id
			questionnaireDTO = questionDAO.getQuestionnaire ( questionnaireDTO.getQuestionnaireId () );
			if ( questionnaireDTO != null )
			{
				isExist = true;
			}
		}
		catch (SystemException e)
		{
			throw e;
		}
		return isExist;
	}
	
	// check duplicasy for question to add
	private boolean checkUniqueCodeDuplicacy ( QuestionDTO questionDTO )
			throws SystemException
	{
		boolean isExist = false;
		QuestionDTO questionDTOIdCheck = null;
		try
		{
			// get question with same question id
			questionDTO = questionDAO.get ( questionDTO.getQuestionId () );
			if ( questionDTO != null )
			{
				isExist = true;
			}
		}
		catch (SystemException e)
		{
			throw e;
		}
		return isExist;
	}
	
	// check dulpicasy for question to update
	private boolean checkUniqueCodeDuplicacyForUpdate ( QuestionDTO questionDTO )
			throws SystemException
	{
		boolean isExist = false;
		// get question with question id
		QuestionDTO questionDTOIdCheck = questionDAO.get ( questionDTO.getQuestionId () );
		if ( questionDTOIdCheck != null )
		{
			// compare dtos with component id
			if ( ("" + questionDTO.getComponentId ()).equals ( "" + questionDTOIdCheck.getComponentId () ) )
			{
				isExist = false;
			}
			else
			{
				isExist = true;
			}
		}
		// return if exists
		return isExist;
	}
	
	// check duplicasy for questionnaire to update
	private boolean checkQuestionnaireUniqueCodeDuplicacyForUpdate ( QuestionnaireDTO questionnaireDTO )
			throws SystemException
	{
		boolean isExist = false;
		// get questionnnaire with questionnaire id
		QuestionnaireDTO questionnaireDTOIdCheck = questionDAO.getQuestionnaire ( questionnaireDTO.getQuestionnaireId () );
		if ( questionnaireDTOIdCheck != null )
		{
			// compare component id
			if ( ("" + questionnaireDTO.getComponentId ()).equals ( "" + questionnaireDTOIdCheck.getComponentId () ) )
			{
				isExist = false;
			}
			else
			{
				isExist = true;
			}
		}
		// return if exists
		return isExist;
	}
	
	public List<AnswerTypeDTO> getAnswerTypeList ( ) throws SystemException
	{
		
		logger.info ( "getAnswerTypeList() : Enter" );
		ArrayList<AnswerTypeDTO> result = null;
		try
		{
			result = questionDAO.getAnswerTypeList ();
		}
		catch (Exception e)
		{
			logger.error ( "getAnswerTypeList()", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList() : Exit" );
		return result;
	}
	
	public List<QuestionnaireStatusDTO> getStatusList ( )
			throws SystemException
	{
		
		logger.info ( "getStatusList() : Enter" );
		ArrayList<QuestionnaireStatusDTO> result = null;
		try
		{
			result = questionDAO.getStatusList ();
		}
		catch (Exception e)
		{
			logger.error ( "getStatusList()", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList() : Exit" );
		return result;
	}
	
	public List<CategoryTypeDTO> getCategoryTypeList ( ) throws SystemException
	{
		logger.info ( "getCategoryTypeList() : Enter" );
		ArrayList<CategoryTypeDTO> result = null;
		try
		{
			result = questionDAO.getCategoryTypeList ();
		}
		catch (Exception e)
		{
			logger.error ( "getCategoryTypeList()", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList() : Exit" );
		return result;
		
	}
	
	public List<QuestionnaireDTO> getSyncQuestionnaireList ( List<QuestionnaireDTO> mobileQuestionnaireList )
	{
		// sync question list to return
		List<QuestionnaireDTO> syncQuestionList = new ArrayList<QuestionnaireDTO> ();
		
		// get the sdp question list which is used in single
		List<QuestionnaireDTO> sdpQuestionnaireList = null;
		try
		{
			sdpQuestionnaireList = this.questionDAO.getSyncQuestionnaireList ();
		}
		catch (SystemException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		
		// questionnaire id question map for mobile
		Map<String, QuestionnaireDTO> questionnaireIdQuestionnaireDTOMap = new HashMap<String, QuestionnaireDTO> ();
		
		if ( mobileQuestionnaireList != null && mobileQuestionnaireList.size () != 0 )
		{
			// create the map for mobile
			for ( QuestionnaireDTO questionnaireDTO : mobileQuestionnaireList )
			{
				// get the questionnaire id
				String questionnaireId = questionnaireDTO.getQuestionnaireId ();
				
				// put it to the map
				questionnaireIdQuestionnaireDTOMap.put ( questionnaireId, questionnaireDTO );
			}
			
			if ( sdpQuestionnaireList != null && sdpQuestionnaireList.size () != 0 )
			{
				// iterate the questionnaire list
				for ( QuestionnaireDTO sdpQuestionnaire : sdpQuestionnaireList )
				{
					// get the questionnaire and version
					String sdpQuestionnaireId = sdpQuestionnaire.getQuestionnaireId ();
					String sdpQuestionnaireVersion = sdpQuestionnaire.getQuestionnaireVersion ();
					
					// now check if there is questionnaire in the mobile against
					// this
					QuestionnaireDTO mobileQuestionnaire = questionnaireIdQuestionnaireDTOMap.get ( sdpQuestionnaireId );
					// if it is not null check the version
					if ( mobileQuestionnaire != null )
					{
						// get the version
						String mobileQuestionnaireVersion = mobileQuestionnaire.getQuestionnaireVersion ();
						if ( StringUtils.isUpperVersion ( sdpQuestionnaireVersion, mobileQuestionnaireVersion ) )
						{
							syncQuestionList.add ( sdpQuestionnaire );
						}
					}
					// else it is null so this questionnaire is not in the map
					else
					{
						syncQuestionList.add ( sdpQuestionnaire );
					}
					
				}
			}
			
		}
		
		// return sync question list
		return syncQuestionList;
	}
	
}
