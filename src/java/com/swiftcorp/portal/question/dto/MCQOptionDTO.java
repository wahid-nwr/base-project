/**
 * 
 */
package com.swiftcorp.portal.question.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

/**
 * @author click 1
 * 
 */
public class MCQOptionDTO extends PersistentCapableDTO
{
	private static final long serialVersionUID = 1L;
	private String name = null;
	private String value = "";
	private int questionOrder = 0;
	// question
	private QuestionDTO questionDTO;
	
	public String getName ( )
	{
		return name;
	}
	
	public void setName ( String name )
	{
		this.name = name;
	}
	
	public QuestionDTO getQuestionDTO ( )
	{
		return questionDTO;
	}
	
	public void setQuestionDTO ( QuestionDTO questionDTO )
	{
		this.questionDTO = questionDTO;
	}
	
	public int getQuestionOrder ( )
	{
		return questionOrder;
	}
	
	public void setQuestionOrder ( int questionOrder )
	{
		this.questionOrder = questionOrder;
	}
	
	public String getValue ( )
	{
		return value;
	}
	
	public void setValue ( String value )
	{
		this.value = value;
	}
	
}
