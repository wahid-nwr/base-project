package com.swiftcorp.portal.question.dto;

import java.util.ArrayList;
import java.util.List;

import com.swiftcorp.portal.common.dto.DTOConstants;

public class QuestionDTO extends QDTO
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String questionName = null;
	private AnswerTypeDTO answerType = new AnswerTypeDTO ();
	private CategoryTypeDTO categoryType = new CategoryTypeDTO ();
	private String questionId = null;
	
	// list of mcq question if any
	List<MCQOptionDTO> mcqOptionList = new ArrayList<MCQOptionDTO> ();
	List<ValidationDTO> validationDTOList = new ArrayList<ValidationDTO> ();
	
	
	
	public List<ValidationDTO> getValidationDTOList() {
		return validationDTOList;
	}

	public void setValidationDTOList(List<ValidationDTO> validationDTOList) {
		this.validationDTOList = validationDTOList;
	}

	public QuestionDTO ( )
	{
		super ( DTOConstants.QUESTION_TYPE );
	}
	
	public String getQuestionName ( )
	{
		return questionName;
	}
	
	public void setQuestionName ( String questionName )
	{
		this.questionName = questionName;
	}
	
	public AnswerTypeDTO getAnswerType ( )
	{
		return answerType;
	}
	
	public void setAnswerType ( AnswerTypeDTO answerType )
	{
		this.answerType = answerType;
	}
	
	public CategoryTypeDTO getCategoryType ( )
	{
		return categoryType;
	}
	
	public void setCategoryType ( CategoryTypeDTO categoryType )
	{
		this.categoryType = categoryType;
	}
	
	public List<MCQOptionDTO> getMcqOptionList ( )
	{
		return mcqOptionList;
	}
	
	public void setMcqOptionList ( List<MCQOptionDTO> mcqOptionList )
	{
		this.mcqOptionList = mcqOptionList;
	}
	
	public String getQuestionId ( )
	{
		return questionId;
	}
	
	public void setQuestionId ( String questionId )
	{
		this.questionId = questionId;
	}
	
}
