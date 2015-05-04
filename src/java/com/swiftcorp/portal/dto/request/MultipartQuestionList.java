package com.swiftcorp.portal.dto.request;

import java.util.ArrayList;

public class MultipartQuestionList
{
	private ArrayList<String> multipartQuestionList;
	
	public ArrayList<String> getMultipartQuestionList ( )
	{
		return multipartQuestionList;
	}
	
	public void setMultipartQuestionList ( ArrayList<String> multipartQuestionList )
	{
		this.multipartQuestionList = multipartQuestionList;
	}
}
