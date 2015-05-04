package com.swiftcorp.portal.dto.sync;

import java.util.List;

import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.question.dto.MCQOptionDTO;
import com.swiftcorp.portal.question.dto.QDTO;
import com.swiftcorp.portal.question.dto.ValidationDTO;

public class CASQuestionDTO extends QDTO
{
	
	private static final long serialVersionUID = 1L;
	private String questionName;
	private String answerType;
	private String categoryName;
	private String questionId;
	private long answerTypeCode;
	// list of mcq question if any
	private List<MCQOptionDTO> mcqOptionDTOList;
	private List<ValidationDTO> validationDTOList;
	
	
	public long getAnswerTypeCode() {
		return answerTypeCode;
	}

	public void setAnswerTypeCode(long answerTypeCode) {
		this.answerTypeCode = answerTypeCode;
	}

	public List<ValidationDTO> getValidationDTOList() {
		return validationDTOList;
	}

	public void setValidationDTOList(List<ValidationDTO> validationDTOList) {
		this.validationDTOList = validationDTOList;
	}

	public CASQuestionDTO ( )
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
	
	public String getAnswerType ( )
	{
		return answerType;
	}
	
	public void setAnswerType ( String answerType )
	{
		this.answerType = answerType;
	}
	
	public String getCategoryName ( )
	{
		return categoryName;
	}
	
	public void setCategoryName ( String categoryName )
	{
		this.categoryName = categoryName;
	}
	
	public String getQuestionId ( )
	{
		return questionId;
	}
	
	public void setQuestionId ( String questionId )
	{
		this.questionId = questionId;
	}
	
	public List<MCQOptionDTO> getMcqOptionDTOList ( )
	{
		return mcqOptionDTOList;
	}
	
	public void setMcqOptionDTOList ( List<MCQOptionDTO> mcqOptionDTOList )
	{
		this.mcqOptionDTOList = mcqOptionDTOList;
	}
	
}
