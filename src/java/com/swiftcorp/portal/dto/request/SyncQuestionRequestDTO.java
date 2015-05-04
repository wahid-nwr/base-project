package com.swiftcorp.portal.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.swiftcorp.portal.algorithm.dto.AlgorithmDTO;
import com.swiftcorp.portal.question.dto.QuestionnaireDTO;

public class SyncQuestionRequestDTO extends RequestDTO
{
	List<AlgorithmDTO> algorithmList = new ArrayList<AlgorithmDTO> ();
	List<QuestionnaireDTO> questionnaireList = new ArrayList<QuestionnaireDTO> ();
	
	public List<AlgorithmDTO> getAlgorithmList ( )
	{
		return algorithmList;
	}
	
	public void setAlgorithmList ( List<AlgorithmDTO> algorithmList )
	{
		this.algorithmList = algorithmList;
	}
	
	public List<QuestionnaireDTO> getQuestionnaireList ( )
	{
		return questionnaireList;
	}
	
	public void setQuestionnaireList ( List<QuestionnaireDTO> questionnaireList )
	{
		this.questionnaireList = questionnaireList;
	}
	
}
