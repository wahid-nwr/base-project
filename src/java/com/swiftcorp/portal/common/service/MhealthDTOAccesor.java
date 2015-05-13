/**
 * 
 */
package com.swiftcorp.portal.common.service;

import java.util.Map;

import com.swiftcorp.portal.common.dto.GenericDTO;

/**
 * @author asraful.haque
 * 
 */
public class MhealthDTOAccesor
{
	// entity class
	private Class<? extends GenericDTO> entityClass;
	
	// property accessor map
	private QuestionPropertyAccessorMap questionPropertyAccessorMap;
	
	// questionnaire property accessor map- this is actually the map of
	// questionnaire id vs questionPropertyAccessorMap
	private Map<String, QuestionPropertyAccessorMap> questionnairePropertyAccessorMap;
	
	// post processor for the dto
	private DTOPostProcessor dtoPostProcessor;
	
	public Class<? extends GenericDTO> getEntityClass ( )
	{
		return entityClass;
	}
	
	public void setEntityClass ( Class<? extends GenericDTO> entityClass )
	{
		this.entityClass = entityClass;
	}
	
	public QuestionPropertyAccessorMap getQuestionPropertyAccessorMap ( )
	{
		return questionPropertyAccessorMap;
	}
	
	public void setQuestionPropertyAccessorMap ( QuestionPropertyAccessorMap questionPropertyAccessorMap )
	{
		this.questionPropertyAccessorMap = questionPropertyAccessorMap;
	}
	
	public Map<String, QuestionPropertyAccessorMap> getQuestionnairePropertyAccessorMap ( )
	{
		return questionnairePropertyAccessorMap;
	}
	
	public void setQuestionnairePropertyAccessorMap ( Map<String, QuestionPropertyAccessorMap> questionnairePropertyAccessorMap )
	{
		this.questionnairePropertyAccessorMap = questionnairePropertyAccessorMap;
	}
	
	public DTOPostProcessor getDtoPostProcessor ( )
	{
		return dtoPostProcessor;
	}
	
	public void setDtoPostProcessor ( DTOPostProcessor dtoPostProcessor )
	{
		this.dtoPostProcessor = dtoPostProcessor;
	}
	
}
