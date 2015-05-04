/**
 * 
 */
package com.swiftcorp.portal.dto.request;

import java.util.HashMap;
import java.util.Map;

import com.swiftcorp.portal.webservice.QuestionList;

/**
 * @author asraful.haque
 * 
 */
public class ChildAlgQuestionAnswerReqDTO extends RequestDTO
{
	// algorithm name
	private String algorithmName;
	
	private String userId;
	// algorithm name
	private String algorithmId;
	private String startTimeStamp;
	
	// algorithm end timestamp
	private String endTimeStamp;
	
	// question answer map-questionId vs answer
	private QuestionAnswerMap questionAnswerMap;
	
	private HashMap<String, QuestionAnswerMap> questionnaireIdQuestionAnswerMap;
	private HashMap<String, QuestionList> questionnaireIdQuestionListMap;
	
	// multipart question list
	private MultipartQuestionList multipartQuestionList;
	
	// these are the multipart question in the questionnaire
	private Map<String, MultipartQuestionList> multipartQuestionnaireIdQuestionListMap;
	
	// childAlgorithmId
	private String childAlgorithmId;
	
	public ChildAlgQuestionAnswerReqDTO ( )
	{
		this.dtoType = DTOType.ALG_QUESTION_ANS_REQ_DTO;
	}
	
	public String getAlgorithmName ( )
	{
		return algorithmName;
	}
	
	public void setAlgorithmName ( String algorithmName )
	{
		this.algorithmName = algorithmName;
	}
	
	public String getAlgorithmId ( )
	{
		return algorithmId;
	}
	
	public void setAlgorithmId ( String algorithmId )
	{
		this.algorithmId = algorithmId;
	}
	
	public QuestionAnswerMap getQuestionAnswerMap ( )
	{
		return questionAnswerMap;
	}
	
	public void setQuestionAnswerMap ( QuestionAnswerMap questionAnswerMap )
	{
		this.questionAnswerMap = questionAnswerMap;
	}
	
	public MultipartQuestionList getMultipartQuestionList ( )
	{
		return multipartQuestionList;
	}
	
	public void setMultipartQuestionList ( MultipartQuestionList multipartQuestionList )
	{
		this.multipartQuestionList = multipartQuestionList;
	}
	
	public HashMap<String, QuestionAnswerMap> getQuestionnaireIdQuestionAnswerMap ( )
	{
		return questionnaireIdQuestionAnswerMap;
	}
	
	public void setQuestionnaireIdQuestionAnswerMap ( HashMap<String, QuestionAnswerMap> questionnaireIdQuestionAnswerMap )
	{
		this.questionnaireIdQuestionAnswerMap = questionnaireIdQuestionAnswerMap;
	}
	
	public Map<String, MultipartQuestionList> getMultipartQuestionnaireIdQuestionListMap ( )
	{
		return multipartQuestionnaireIdQuestionListMap;
	}
	
	public void setMultipartQuestionnaireIdQuestionListMap ( Map<String, MultipartQuestionList> multipartQuestionnaireIdQuestionListMap )
	{
		this.multipartQuestionnaireIdQuestionListMap = multipartQuestionnaireIdQuestionListMap;
	}
	
	public HashMap<String, QuestionList> getQuestionnaireIdQuestionListMap ( )
	{
		return questionnaireIdQuestionListMap;
	}
	
	public void setQuestionnaireIdQuestionListMap ( HashMap<String, QuestionList> questionnaireIdQuestionListMap )
	{
		this.questionnaireIdQuestionListMap = questionnaireIdQuestionListMap;
	}
	
	public String getUserId ( )
	{
		return userId;
	}
	
	public void setUserId ( String userId )
	{
		this.userId = userId;
	}
	
	public String getStartTimeStamp ( )
	{
		return startTimeStamp;
	}
	
	public void setStartTimeStamp ( String startTimeStamp )
	{
		this.startTimeStamp = startTimeStamp;
	}
	
	public String getEndTimeStamp ( )
	{
		return endTimeStamp;
	}
	
	public void setEndTimeStamp ( String endTimeStamp )
	{
		this.endTimeStamp = endTimeStamp;
	}
	
	public String getChildAlgorithmId ( )
	{
		return childAlgorithmId;
	}
	
	public void setChildAlgorithmId ( String childAlgorithmId )
	{
		this.childAlgorithmId = childAlgorithmId;
	}
	
}
