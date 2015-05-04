/**
 * 
 */
package com.swiftcorp.portal.dto.sync;

/**
 * @author asraful.haque
 * 
 */
public class CASAlgAnswerDTO extends com.swiftcorp.portal.common.dto.PersistentCapableDTO
{
	private String algAnswerId;
	
	// question for this answer
	private String questionId;
	
	// answer type
	private String answerType;
	
	// answer 1 and answer 2 is for dual value/ range, if there is single value
	// only
	// first answer (ie answer1 ) is considerable
	private String answer1;
	
	// answer 2
	private String answer2;
	
	public String getQuestionId ( )
	{
		return questionId;
	}
	
	public void setQuestionId ( String questionId )
	{
		this.questionId = questionId;
	}
	
	public String getAnswerType ( )
	{
		return answerType;
	}
	
	public void setAnswerType ( String answerType )
	{
		this.answerType = answerType;
	}
	
	public String getAnswer1 ( )
	{
		return answer1;
	}
	
	public void setAnswer1 ( String answer1 )
	{
		this.answer1 = answer1;
	}
	
	public String getAnswer2 ( )
	{
		return answer2;
	}
	
	public void setAnswer2 ( String answer2 )
	{
		this.answer2 = answer2;
	}
	
	public String getAlgAnswerId ( )
	{
		return algAnswerId;
	}
	
	public void setAlgAnswerId ( String algAnswerId )
	{
		this.algAnswerId = algAnswerId;
	}
	
}
