/**
 * 
 */
package com.swiftcorp.portal.dto.sync;

import java.util.ArrayList;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

/**
 * @author asraful.haque
 * 
 */
public class CASAlgQuestionBranchDTO extends PersistentCapableDTO
{
	private ArrayList<String> algAnswerIdList = new ArrayList<String> ();
	
	// next question to go
	private String nextAlgQuestionId;
	
	public ArrayList<String> getAlgAnswerIdList ( )
	{
		return algAnswerIdList;
	}
	
	public void setAlgAnswerIdList ( ArrayList<String> algAnswerIdList )
	{
		this.algAnswerIdList = algAnswerIdList;
	}
	
	public String getNextAlgQuestionId ( )
	{
		return nextAlgQuestionId;
	}
	
	public void setNextAlgQuestionId ( String nextAlgQuestionId )
	{
		this.nextAlgQuestionId = nextAlgQuestionId;
	}
	
}
