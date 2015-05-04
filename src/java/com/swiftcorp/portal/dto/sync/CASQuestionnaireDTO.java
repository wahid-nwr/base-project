package com.swiftcorp.portal.dto.sync;

import java.util.ArrayList;

import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.question.dto.QDTO;

public class CASQuestionnaireDTO extends QDTO
{
	
	public CASQuestionnaireDTO ( )
	{
		super ( DTOConstants.QUESTIONNAIRE_TYPE );
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String questionnaireName;
	private String questionnaireVersion;
	private String questionnaireStatus;
	private String questionnaireId;
	private String questionnaireTimestamp;
	private String questionnaireDescription;
	private int numberOfQuestion;
	
	// list of question
	private ArrayList<String> questionIdList = new ArrayList<String> ();
	
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
	
	public String getQuestionnaireStatus ( )
	{
		return questionnaireStatus;
	}
	
	public void setQuestionnaireStatus ( String questionnaireStatus )
	{
		this.questionnaireStatus = questionnaireStatus;
	}
	
	public String getQuestionnaireId ( )
	{
		return questionnaireId;
	}
	
	public void setQuestionnaireId ( String questionnaireId )
	{
		this.questionnaireId = questionnaireId;
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
	
	public ArrayList<String> getQuestionIdList ( )
	{
		return questionIdList;
	}
	
	public void setQuestionIdList ( ArrayList<String> questionIdList )
	{
		this.questionIdList = questionIdList;
	}
	
}
