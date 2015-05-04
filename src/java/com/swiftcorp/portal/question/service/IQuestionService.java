/*
 * @ (#) IQuestionService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.question.service;

import java.util.List;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.question.QuestionSuccessResult;
import com.swiftcorp.portal.question.dao.IQuestionDAO.QuestionSortBy;
import com.swiftcorp.portal.question.dto.AnswerTypeDTO;
import com.swiftcorp.portal.question.dto.CategoryTypeDTO;
import com.swiftcorp.portal.question.dto.MCQOptionDTO;
import com.swiftcorp.portal.question.dto.QuestionDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireStatusDTO;
import com.swiftcorp.portal.question.dto.ValidationDTO;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public interface IQuestionService extends IGenericService
{
	public SearchOperationResult search ( String searchQuery )
			throws SystemException, BusinessRuleViolationException;
	
	public SearchOperationResult searchQuestionnaire ( String searchQuery )
			throws SystemException, BusinessRuleViolationException;
	
	public List<QuestionDTO> getList ( Long groupId, QuestionSortBy sortby )
			throws SystemException;
	
	public List<QuestionDTO> getList ( ) throws SystemException;
	
	public List<QuestionnaireDTO> getQuestionnaireList ( ) throws SystemException;
	
	public List<AnswerTypeDTO> getAnswerTypeList ( ) throws SystemException;
	
	public List<QuestionnaireStatusDTO> getStatusList ( )
			throws SystemException;
	
	public List<CategoryTypeDTO> getCategoryTypeList ( ) throws SystemException;
	
	public List<CategoryTypeDTO> getMCQList ( Long questionId )
			throws SystemException;
	
	public GenericDTO getCategory ( Long componentId ) throws SystemException;
	
	public GenericDTO getAnswerType ( Long componentId ) throws SystemException;
	
	public GenericDTO getQuestionnaireStatus ( Long componentId )
			throws SystemException;
	
	public BusinessOperationResult addQuestionnaire ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException;
	
	public BusinessOperationResult modifyQuestionnaire ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException;
	
	public GenericDTO getQuestionnaire ( Long componentId )
			throws SystemException;
	public GenericDTO getQuestionnaire ( String questionnaireId )
	throws SystemException;
	
	public QuestionSuccessResult deleteMcqOption ( MCQOptionDTO mcqOptionDTO )
			throws SystemException;
	public QuestionSuccessResult deleteValidation ( ValidationDTO validationDTO )
	throws SystemException;
	
}
