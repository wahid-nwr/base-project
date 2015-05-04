package com.swiftcorp.portal.question.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class AnswerTypeDTO extends PersistentCapableDTO
{
	private static final long serialVersionUID = 1L;
	
	private String answerTypeName;
	private int answerTypeId;
	
	public String getAnswerTypeName ( )
	{
		return answerTypeName;
	}
	
	public void setAnswerTypeName ( String answerTypeName )
	{
		this.answerTypeName = answerTypeName;
	}
	
	public int getAnswerTypeId ( )
	{
		return answerTypeId;
	}
	
	public void setAnswerTypeId ( int answerTypeId )
	{
		this.answerTypeId = answerTypeId;
	}
}
