/**
 * 
 */
package com.swiftcorp.portal.dto.sync;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

/**
 * @author asraful.haque
 * 
 */
public class CASQDTO extends PersistentCapableDTO
{
	// q type means question type it may be question or questionnaire
	private int qqType;
	
	// question or questionnaire ID
	private String questionQuestionnaireId;
	
	public CASQDTO ( )
	{
		
	}
	
	public CASQDTO ( int qqType )
	{
		this.qqType = qqType;
	}
	
	public int getQqType ( )
	{
		return qqType;
	}
	
	public void setQqType ( int qqType )
	{
		this.qqType = qqType;
	}
	
	public String getQuestionQuestionnaireId ( )
	{
		return questionQuestionnaireId;
	}
	
	public void setQuestionQuestionnaireId ( String questionQuestionnaireId )
	{
		this.questionQuestionnaireId = questionQuestionnaireId;
	}
	
}
