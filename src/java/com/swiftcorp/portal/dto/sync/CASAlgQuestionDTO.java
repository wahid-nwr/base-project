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
public class CASAlgQuestionDTO extends PersistentCapableDTO
{
	private String algQuestionId;
	// question or questionnaire
	private CASQDTO casqdto;
	
	// list of Question branch
	private ArrayList<String> algQuestionBranchIdList = new ArrayList<String> ();
	
	// last question flag
	private int lastQuestionFlag;
	
	// name - optional here
	private String name;
	
	private String branchAlu;
	
	
	public ArrayList<String> getAlgQuestionBranchIdList ( )
	{
		return algQuestionBranchIdList;
	}
	
	public void setAlgQuestionBranchIdList ( ArrayList<String> algQuestionBranchIdList )
	{
		this.algQuestionBranchIdList = algQuestionBranchIdList;
	}
	
	public int getLastQuestionFlag ( )
	{
		return lastQuestionFlag;
	}
	
	public void setLastQuestionFlag ( int lastQuestionFlag )
	{
		this.lastQuestionFlag = lastQuestionFlag;
	}
	
	public String getName ( )
	{
		return name;
	}
	
	public void setName ( String name )
	{
		this.name = name;
	}
	
	public CASQDTO getCasqdto ( )
	{
		return casqdto;
	}
	
	public void setCasqdto ( CASQDTO casqdto )
	{
		this.casqdto = casqdto;
	}
	
	public String getAlgQuestionId ( )
	{
		return algQuestionId;
	}
	
	public void setAlgQuestionId ( String algQuestionId )
	{
		this.algQuestionId = algQuestionId;
	}

	public String getBranchAlu ( )
	{
		return branchAlu;
	}

	public void setBranchAlu ( String branchAlu )
	{
		this.branchAlu = branchAlu;
	}
	
}
