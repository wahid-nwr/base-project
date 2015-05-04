/**
 * 
 */
package com.swiftcorp.portal.algorithm.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

/**
 * @author asraful.haque
 * 
 */
public class AlgQuestionBranchDTO extends PersistentCapableDTO
{
	// algorithm answer list
	List<AlgAnswerDTO> algAnswerList = new ArrayList<AlgAnswerDTO> ();
	
	// next question to go
	@XmlTransient
	AlgQuestionDTO nextQuestion;
	
	public List<AlgAnswerDTO> getAlgAnswerList ( )
	{
		return algAnswerList;
	}
	
	public void setAlgAnswerList ( List<AlgAnswerDTO> algAnswerList )
	{
		this.algAnswerList = algAnswerList;
	}
	
	public AlgQuestionDTO getNextQuestion ( )
	{
		return nextQuestion;
	}
	
	public void setNextQuestion ( AlgQuestionDTO nextQuestion )
	{
		this.nextQuestion = nextQuestion;
	}
	
}
