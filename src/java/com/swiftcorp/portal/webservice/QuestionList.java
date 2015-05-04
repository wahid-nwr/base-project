package com.swiftcorp.portal.webservice;

import java.util.ArrayList;

public class QuestionList
{
	ArrayList<String> questionList = new ArrayList<String> ();
	
	public ArrayList<String> getQuestionList ( )
	{
		return questionList;
	}
	
	public void setQuestionList ( ArrayList<String> questionList )
	{
		this.questionList = questionList;
	}
	
}
