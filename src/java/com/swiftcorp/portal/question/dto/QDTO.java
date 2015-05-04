/**
 * 
 */
package com.swiftcorp.portal.question.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

/**
 * @author asraful.haque
 * 
 */
public class QDTO extends PersistentCapableDTO
{
	// q type means question type it may be question or questionnaire
	private int qqType;
	
	public QDTO ( )
	{
		
	}
	
	public QDTO ( int qqType )
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
	
}
