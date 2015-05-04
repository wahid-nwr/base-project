package com.swiftcorp.portal.question.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.dto.DTOConstants;
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

public class QuestionHibernateDAOImpl extends HibernateDaoSupport implements IQuestionDAO
{
	
	protected static final Log log = LogFactory.getLog ( QuestionHibernateDAOImpl.class );
	
	public QuestionDTO get ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		QuestionDTO questionDTO = null;
		try
		{
			questionDTO = (QuestionDTO) getHibernateTemplate ().get ( QuestionDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return questionDTO;
	}
	
	// For get questionaire
	
	public CategoryTypeDTO getCategory ( Long componentId )
			throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		CategoryTypeDTO categoryTypeDTO = null;
		try
		{
			categoryTypeDTO = (CategoryTypeDTO) getHibernateTemplate ().get ( CategoryTypeDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return categoryTypeDTO;
	}
	
	public AnswerTypeDTO getAnswerType ( Long componentId )
			throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		AnswerTypeDTO answerTypeDTO = null;
		try
		{
			answerTypeDTO = (AnswerTypeDTO) getHibernateTemplate ().get ( AnswerTypeDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return answerTypeDTO;
	}
	
	public QuestionnaireStatusDTO getQuestionnaireStatus ( Long componentId )
			throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		QuestionnaireStatusDTO questionnaireStatusDTO = null;
		try
		{
			questionnaireStatusDTO = (QuestionnaireStatusDTO) getHibernateTemplate ().get ( QuestionnaireStatusDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return questionnaireStatusDTO;
	}
	
	public QuestionDTO get ( String questionId ) throws SystemException

	{
		
		log.info ( "get(code): Enter" );
		log.info ( "get(questionId): questionId = " + questionId );
		QuestionDTO questionDTO = null;
		try
		{
			
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from QuestionDTO questionDTO where questionDTO.questionId=?", questionId );
			if ( list.size () > 0 )
			{
				questionDTO = (QuestionDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return questionDTO;
	}
	
	// For AnswerType
	
	public ArrayList<QuestionnaireStatusDTO> getStatusList ( )
			throws SystemException
	{
		return getStatusList ( null, QuestionnaireStatusSortBy.statusName );
	}
	
	// For AnswerType
	
	public ArrayList<AnswerTypeDTO> getAnswerTypeList ( )
			throws SystemException
	{
		return getAnswerTypeList ( null, AnswerSortBy.answerTypeName );
	}
	
	public ArrayList<QuestionnaireStatusDTO> getStatusList ( Long groupId, QuestionnaireStatusSortBy sortby )
			throws SystemException
	{
		log.info ( "getStatusList: Enter" );
		log.info ( "getStatusList: sortby = " + sortby );
		System.out.println ( "sdfasdf" );
		ArrayList<QuestionnaireStatusDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT questionnaireStatusDTO FROM QuestionnaireStatusDTO questionnaireStatusDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE questionnaireStatusDTO.groupId=" + groupId );
		}
		queryStr.append ( " ORDER BY " );
		queryStr.append ( getqsStatusStr ( sortby ) );
		System.out.println ( "queryStr::::::::::::::::::::" + queryStr );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AnswerTypeDTO> getAnswerTypeList ( Long groupId, AnswerSortBy sortby )
			throws SystemException
	{
		log.info ( "getAnswerTypeList: Enter" );
		log.info ( "getAnswerTypeList: sortby = " + sortby );
		
		ArrayList<AnswerTypeDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT answerTypeDTO FROM AnswerTypeDTO answerTypeDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE answerTypeDTO.groupId=" + groupId );
		}
		queryStr.append ( " ORDER BY " );
		queryStr.append ( getAnswerSortByStr ( sortby ) );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return result;
	}
	
	// For Question Category
	
	public ArrayList<CategoryTypeDTO> getCategoryTypeList ( )
			throws SystemException
	{
		return getCategoryTypeList ( null, CategorySortBy.categoryName );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<CategoryTypeDTO> getCategoryTypeList ( Long groupId, CategorySortBy sortby )
			throws SystemException
	{
		log.info ( "getCategoryTypeList: Enter" );
		log.info ( "getCategoryTypeList: sortby = " + sortby );
		
		ArrayList<CategoryTypeDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT categoryTypeDTO FROM CategoryTypeDTO categoryTypeDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE categoryTypeDTO.groupId=" + groupId );
		}
		queryStr.append ( " ORDER BY " );
		queryStr.append ( getCaregorySortByStr ( sortby ) );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return result;
	}
	
	public ArrayList<MCQOptionDTO> getMCQList ( Long questionId )
			throws SystemException
	{
		log.info ( "getMCQList: Enter" );
		// log.info("getMCQList: sortby = " + sortby);
		
		ArrayList<MCQOptionDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT mcqOptionDTO FROM MCQOptionDTO mcqOptionDTO" );
		if ( questionId != null )
		{
			queryStr.append ( " WHERE mcqOptionDTO.QuestionDTO.componentId=" + questionId );
		}
		// queryStr.append(" ORDER BY ");
		// queryStr.append(getCaregorySortByStr(sortby));
		System.out.println ( "queryStr.toString():::::::::" + queryStr.toString () );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return result;
	}
	
	public List<QuestionnaireDTO> getSyncQuestionnaireList ( )
			throws SystemException
	{
		log.info ( "getSyncQuestionnaireList: Enter" );
		
		List<QuestionnaireDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT questionnaireDTO FROM QuestionnaireDTO questionnaireDTO" );
		
		queryStr.append ( " WHERE questionnaireDTO.questionnaireTypeFlag !=" + DTOConstants.QUESTIONNAIRE_IN_ALGORITHM_ONLY );
		
		System.out.println ( "queryStr.toString():::::::::" + queryStr.toString () );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return result;
	}
	
	public ArrayList<QuestionDTO> getList ( ) throws SystemException
	{
		return getList ( null, QuestionSortBy.questionName );
	}
	public ArrayList<QuestionnaireDTO> getQuestionnaireList ( ) throws SystemException
	{
		return getQuestionnaireList ( null, QuestionnaireSortBy.questionnaireId );
	}
	
	public ArrayList<QuestionnaireDTO> getQuestionnaireList ( Long groupId, QuestionnaireSortBy sortby )
	throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<QuestionnaireDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT questionnaireDTO FROM QuestionnaireDTO questionnaireDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE questionnaireDTO.groupId=" + groupId );
		}
		queryStr.append ( " ORDER BY " );
		queryStr.append ( getQuestionnaireSortByStr ( sortby ) );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<QuestionDTO> getList ( Long groupId, QuestionSortBy sortby )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<QuestionDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT questionDTO FROM QuestionDTO questionDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE questionDTO.groupId=" + groupId );
		}
		queryStr.append ( " ORDER BY " );
		queryStr.append ( getSortByStr ( sortby ) );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return result;
	}
	
	public QuestionSuccessResult add ( QuestionDTO questionDTO )
			throws UniqueCodeRequiredException, SystemException
	{
		log.info ( "add(): Enter" );
		
		QuestionSuccessResult successResult = new QuestionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( questionDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( questionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(QuestionDTO questionDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public QuestionnaireSuccessResult addQuestionnaire ( QuestionnaireDTO questionnaireDTO )
			throws UniqueCodeRequiredException, SystemException

	{
		log.info ( "add(): Enter" );
		
		QuestionnaireSuccessResult successResult = new QuestionnaireSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( questionnaireDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( questionnaireDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(QuestionnaireDTO questionnaireDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public QuestionnaireSuccessResult modifyQuestionnaire ( QuestionnaireDTO questionnaireDTO )
			throws SystemException

	{
		log.info ( "modifyQuestionnaire(): Enter" );
		
		QuestionnaireSuccessResult successResult = new QuestionnaireSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( questionnaireDTO );
			successResult.setMessage ( "modified Successfully." );
			successResult.setOperationResult ( questionnaireDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modifyQuestionnaire(QuestionnaireDTO questionnaireDTO): Failed to modify." + e );
			throw new SystemException ( e );
		}
		log.info ( "modifyQuestionnaire(): Exit" );
		return successResult;
	}
	
	public QuestionnaireSuccessResult deleteQuestionnaire ( QuestionnaireDTO questionnaireDTO )
			throws SystemException
	{
		log.info ( "deleteQuestionnaire(): Enter" );
		
		QuestionnaireSuccessResult successResult = new QuestionnaireSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( questionnaireDTO );
			successResult.setMessage ( "deleted Successfully." );
			successResult.setOperationResult ( questionnaireDTO );
		}
		catch (Exception e)
		{
			log.debug ( "deleteQuestionnaire(QuestionnaireDTO questionnaireDTO): Failed to modify." + e );
			throw new SystemException ( e );
		}
		log.info ( "deleteQuestionnaire(): Exit" );
		return successResult;
	}
	
	public QuestionSuccessResult addMcqOption ( MCQOptionDTO mcqOptionDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		QuestionSuccessResult successResult = new QuestionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( mcqOptionDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( null );
		}
		catch (Exception e)
		{
			log.debug ( "add(QuestionDTO questionDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public QuestionnaireDTO getQuestionnaire ( String questionnaireId )
			throws SystemException
	{
		log.info ( "getQuestionnaire(id): Enter" );
		log.info ( "getQuestionnaire(id): questionnaireId = " + questionnaireId );
		QuestionnaireDTO questionnaireDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from QuestionnaireDTO questionnaireDTO where questionnaireDTO.questionnaireId=?", questionnaireId );
			if ( list.size () > 0 )
			{
				questionnaireDTO = (QuestionnaireDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.info ( "getQuestionnaire(questionnaireId): ", e );
			throw new SystemException ( e );
		}
		log.info ( "getQuestionnaire(id): Exit" );
		return questionnaireDTO;
	}
	
	public QuestionnaireDTO getQuestionnaire ( Long componentId )
			throws SystemException
	{
		log.info ( "getQuestionnaire(id): Enter" );
		log.info ( "getQuestionnaire(id): componentId = " + componentId );
		QuestionnaireDTO questionnaireDTO = null;
		try
		{
			questionnaireDTO = (QuestionnaireDTO) getHibernateTemplate ().get ( QuestionnaireDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "getQuestionnaire(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "getQuestionnaire(id): Exit" );
		return questionnaireDTO;
	}
	
	public QuestionSuccessResult modify ( QuestionDTO questionDTO )
			throws SystemException
	{
		log.info ( "modify(): Enter" );
		QuestionSuccessResult successResult = new QuestionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.merge ( questionDTO ); // saveOrUpdate (
														// questionDTO
														// );
			successResult.setMessage ( "Modified Successfully." );
			successResult.setOperationResult ( questionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modify(QuestionDTO questionDTO): Failed to modify.", e );
			throw new SystemException ( e );
		}
		log.info ( "modify(): Exit" );
		return successResult;
	}
	
	public QuestionSuccessResult remove ( QuestionDTO questionDTO )
			throws SystemException
	{
		log.info ( "remove(): Enter" );
		QuestionSuccessResult successResult = new QuestionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( questionDTO );
			successResult.setMessage ( "removed Successfully." );
			successResult.setOperationResult ( questionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(QuestionDTO questionDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	public QuestionSuccessResult deleteMcqOption ( MCQOptionDTO mcqOptionDTO )
			throws SystemException
	{
		log.info ( "remove(): Enter" );
		QuestionSuccessResult successResult = new QuestionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( mcqOptionDTO );
			successResult.setMessage ( "removed Successfully." );
			// successResult.setOperationResult ( mcqOptionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(QuestionDTO questionDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	public QuestionSuccessResult deleteValidation ( ValidationDTO validationDTO )
	throws SystemException
	{
		log.info ( "remove(): Enter" );
		QuestionSuccessResult successResult = new QuestionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( validationDTO );
			successResult.setMessage ( "removed Successfully." );
			// successResult.setOperationResult ( mcqOptionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(ValidationDTO validationDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	private String getQuestionnaireSortByStr ( QuestionnaireSortBy sortBy )
	{
		// default value
		String resultStr = "questionnaireDTO.questionnaireName";
		if ( sortBy == QuestionnaireSortBy.questionnaireName )
		{
			resultStr = "questionnaireDTO.questionnaireName";
		}
		else if ( sortBy == QuestionnaireSortBy.questionnaireId )
		{
			resultStr = "questionnaireDTO.questionnaireId";
		}		
		return resultStr;
		
	}
	
	private String getSortByStr ( QuestionSortBy sortBy )
	{
		// default value
		String resultStr = "questionDTO.questionName";
		if ( sortBy == QuestionSortBy.questionName )
		{
			resultStr = "questionDTO.questionName";
		}
		else if ( sortBy == QuestionSortBy.questionId )
		{
			resultStr = "questionDTO.questionId";
		}
		else if ( sortBy == QuestionSortBy.categoryId )
		{
			resultStr = "questionDTO.categoryId";
		}
		else if ( sortBy == QuestionSortBy.answerTypeId )
		{
			resultStr = "questionDTO.answerTypeId";
		}
		return resultStr;
		
	}
	
	private String getAnswerSortByStr ( AnswerSortBy sortBy )
	{
		// default value
		String resultStr = "answerTypeDTO.answerTypeName";
		if ( sortBy == AnswerSortBy.answerTypeName )
		{
			resultStr = "answerTypeDTO.answerTypeName";
		}
		else if ( sortBy == AnswerSortBy.answerTypeId )
		{
			resultStr = "answerTypeDTO.answerTypeId";
		}
		
		return resultStr;
		
	}
	
	private String getqsStatusStr ( QuestionnaireStatusSortBy sortBy )
	{
		// default value
		String resultStr = "questionnaireStatusDTO.statusName";
		if ( sortBy == QuestionnaireStatusSortBy.statusName )
		{
			resultStr = "questionnaireStatusDTO.statusName";
		}
		else if ( sortBy == QuestionnaireStatusSortBy.statusId )
		{
			resultStr = "questionnaireStatusDTO.statusId";
		}
		
		return resultStr;
		
	}
	
	private String getCaregorySortByStr ( CategorySortBy sortBy )
	{
		// default value
		String resultStr = " categoryTypeDTO.categoryName";
		if ( sortBy == CategorySortBy.categoryName )
		{
			resultStr = " categoryTypeDTO.categoryName";
		}
		else if ( sortBy == CategorySortBy.categoryId )
		{
			resultStr = " categoryTypeDTO.categoryId";
		}
		
		return resultStr;
		
	}
	
}
