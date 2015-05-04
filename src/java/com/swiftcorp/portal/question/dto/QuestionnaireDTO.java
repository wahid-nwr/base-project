package com.swiftcorp.portal.question.dto;

import java.util.ArrayList;
import java.util.List;

import com.swiftcorp.portal.common.dto.DTOConstants;

public class QuestionnaireDTO extends QDTO
{
	
	public QuestionnaireDTO ( )
	{
		super ( DTOConstants.QUESTIONNAIRE_TYPE );
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String questionnaireName;
	private String questionnaireVersion;
	private QuestionnaireStatusDTO questionnaireStatus = new QuestionnaireStatusDTO ();
	private String questionnaireId;
	private String questionnaireTimestamp;
	private String questionnaireDescription;
	private int numberOfQuestion;
	private int questionnaireTypeFlag;
	
	// flag to determine in which segment this questionnaire is used,,
	// say is it used in the algorithm or used in just independently or used in
	// both
	private int questionnaireUsageTypeFlag;
	
	// list of question
	List<QuestionDTO> questionList = new ArrayList<QuestionDTO> ();
	
	public String getQuestionnaireName ( )
	{
		return questionnaireName;
	}
	
	public void setQuestionnaireName ( String questionnaireName )
	{
		this.questionnaireName = questionnaireName;
	}
	
	public String getQuestionnaireVersion ( )
	{
		return questionnaireVersion;
	}
	
	public void setQuestionnaireVersion ( String questionnaireVersion )
	{
		this.questionnaireVersion = questionnaireVersion;
	}
	
	public String getQuestionnaireId ( )
	{
		return questionnaireId;
	}
	
	public void setQuestionnaireId ( String questionnaireId )
	{
		this.questionnaireId = questionnaireId;
	}
	
	public QuestionnaireStatusDTO getQuestionnaireStatus ( )
	{
		return questionnaireStatus;
	}
	
	public void setQuestionnaireStatus ( QuestionnaireStatusDTO questionnaireStatus )
	{
		this.questionnaireStatus = questionnaireStatus;
	}
	
	public String getQuestionnaireTimestamp ( )
	{
		return questionnaireTimestamp;
	}
	
	public void setQuestionnaireTimestamp ( String questionnaireTimestamp )
	{
		this.questionnaireTimestamp = questionnaireTimestamp;
	}
	
	public String getQuestionnaireDescription ( )
	{
		return questionnaireDescription;
	}
	
	public void setQuestionnaireDescription ( String questionnaireDescription )
	{
		this.questionnaireDescription = questionnaireDescription;
	}
	
	public int getNumberOfQuestion ( )
	{
		return numberOfQuestion;
	}
	
	public void setNumberOfQuestion ( int numberOfQuestion )
	{
		this.numberOfQuestion = numberOfQuestion;
	}
	
	public List<QuestionDTO> getQuestionList ( )
	{
		return questionList;
	}
	
	public void setQuestionList ( List<QuestionDTO> questionList )
	{
		this.questionList = questionList;
	}
	
	public int getQuestionnaireUsageTypeFlag ( )
	{
		return questionnaireUsageTypeFlag;
	}
	
	public void setQuestionnaireUsageTypeFlag ( int questionnaireUsageTypeFlag )
	{
		this.questionnaireUsageTypeFlag = questionnaireUsageTypeFlag;
	}
	
	public int getQuestionnaireTypeFlag ( )
	{
		return questionnaireTypeFlag;
	}
	
	public void setQuestionnaireTypeFlag ( int questionnaireTypeFlag )
	{
		this.questionnaireTypeFlag = questionnaireTypeFlag;
	}
	
}
