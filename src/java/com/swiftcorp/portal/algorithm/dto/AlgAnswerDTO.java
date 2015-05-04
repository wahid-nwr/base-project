/**
 * 
 */
package com.swiftcorp.portal.algorithm.dto;

import javax.xml.bind.annotation.XmlTransient;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
import com.swiftcorp.portal.question.dto.AnswerTypeDTO;
import com.swiftcorp.portal.question.dto.QuestionDTO;

/**
 * @author asraful.haque
 * 
 */
public class AlgAnswerDTO extends PersistentCapableDTO
{
	// algorithm for this answer
	@XmlTransient
	private AlgorithmDTO algorithmDTO;
	
	// question for this answer
	private QuestionDTO questionDTO;
	
	// answer type
	private AnswerTypeDTO answerTypeDTO;
	private AlgQuestionBranchDTO algQuestionBranchDTO;
	
	// answer 1 and answer 2 is for dual value/ range, if there is single value
	// only
	// first answer (ie answer1 ) is considerable
	private String answer1;
	
	// answer 2
	private String answer2;
	
	public AlgorithmDTO getAlgorithmDTO ( )
	{
		return algorithmDTO;
	}
	
	public void setAlgorithmDTO ( AlgorithmDTO algorithmDTO )
	{
		this.algorithmDTO = algorithmDTO;
	}
	
	public QuestionDTO getQuestionDTO ( )
	{
		return questionDTO;
	}
	
	public void setQuestionDTO ( QuestionDTO questionDTO )
	{
		this.questionDTO = questionDTO;
	}
	
	public AnswerTypeDTO getAnswerTypeDTO ( )
	{
		return answerTypeDTO;
	}
	
	public void setAnswerTypeDTO ( AnswerTypeDTO answerTypeDTO )
	{
		this.answerTypeDTO = answerTypeDTO;
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
	
	public AlgQuestionBranchDTO getAlgQuestionBranchDTO ( )
	{
		return algQuestionBranchDTO;
	}
	
	public void setAlgQuestionBranchDTO ( AlgQuestionBranchDTO algQuestionBranchDTO )
	{
		this.algQuestionBranchDTO = algQuestionBranchDTO;
	}
	
}
