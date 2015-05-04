package com.swiftcorp.portal.question.dao;

import java.util.ArrayList;
import java.util.List;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.exception.UniqueCodeRequiredException;
import com.swiftcorp.portal.question.QuestionSuccessResult;
import com.swiftcorp.portal.question.QuestionnaireSuccessResult;
import com.swiftcorp.portal.question.dto.AnswerTypeDTO;
import com.swiftcorp.portal.question.dto.CategoryTypeDTO;
import com.swiftcorp.portal.question.dto.MCQOptionDTO;
import com.swiftcorp.portal.question.dto.QuestionDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireStatusDTO;
import com.swiftcorp.portal.question.dto.ValidationDTO;

public interface IQuestionDAO
{
	public enum QuestionSortBy
	{
		questionName, questionId, categoryId, answerTypeId
	};
	
	public enum QuestionnaireSortBy
	{
		questionnaireName, questionnaireId
	};
	
	public enum QuestionWhereCondition
	{
		questionName, questionId, categoryId, answerTypeId
	};
	
	public enum AnswerSortBy
	{
		answerTypeName, answerTypeId
	};
	
	public enum QuestionnaireStatusSortBy
	{
		statusName, statusId
	};
	
	public enum AnswerWhereCondition
	{
		answerTypeName, answerTypeId
	};
	
	public enum CategorySortBy
	{
		categoryName, categoryId
	};
	
	public enum CategoryWhereCondition
	{
		categoryName, categoryId
	};
	
	public QuestionDTO get ( Long componentId ) throws SystemException;
	
	public QuestionDTO get ( String unicodeCode ) throws SystemException;
	
	public QuestionSuccessResult add ( QuestionDTO questionDTO )
			throws SystemException, UniqueCodeRequiredException;
	
	public QuestionSuccessResult modify ( QuestionDTO questionDTO )
			throws SystemException;
	
	public QuestionSuccessResult remove ( QuestionDTO questionDTO )
			throws SystemException;
	
	public ArrayList<QuestionDTO> getList ( ) throws SystemException;
	
	public ArrayList<QuestionnaireDTO> getQuestionnaireList ( ) throws SystemException;
	
	public ArrayList<QuestionDTO> getList ( Long groupId, QuestionSortBy sortby )
			throws SystemException;
	
	public ArrayList<AnswerTypeDTO> getAnswerTypeList ( )
			throws SystemException;
	
	public ArrayList<QuestionnaireStatusDTO> getStatusList ( )
			throws SystemException;
	
	public ArrayList<AnswerTypeDTO> getAnswerTypeList ( Long groupId, AnswerSortBy sortby )
			throws SystemException;
	
	public ArrayList<CategoryTypeDTO> getCategoryTypeList ( )
			throws SystemException;
	
	public ArrayList<CategoryTypeDTO> getCategoryTypeList ( Long groupId, CategorySortBy sortby )
			throws SystemException;
	
	public ArrayList<MCQOptionDTO> getMCQList ( Long groupId )
			throws SystemException;
	
	public CategoryTypeDTO getCategory ( Long componentId )
			throws SystemException;
	
	public QuestionnaireStatusDTO getQuestionnaireStatus ( Long componentId )
			throws SystemException;
	
	public AnswerTypeDTO getAnswerType ( Long componentId )
			throws SystemException;
	
	public QuestionnaireSuccessResult addQuestionnaire ( QuestionnaireDTO questionnaireDTO )
			throws SystemException, UniqueCodeRequiredException;
	
	public QuestionnaireSuccessResult modifyQuestionnaire ( QuestionnaireDTO questionnaireDTO )
			throws SystemException;
	
	public QuestionnaireDTO getQuestionnaire ( Long componentId )
			throws SystemException;
	
	public QuestionnaireDTO getQuestionnaire ( String questionnaireId )
			throws SystemException;
	
	public QuestionSuccessResult addMcqOption ( MCQOptionDTO mcqOptionDTO )
			throws SystemException;
	
	public List<QuestionnaireDTO> getSyncQuestionnaireList ( )
			throws SystemException;
	
	public QuestionSuccessResult deleteMcqOption ( MCQOptionDTO mcqOptionDTO )
			throws SystemException;
	public QuestionSuccessResult deleteValidation ( ValidationDTO validationDTO )
	throws SystemException;
	
}
