/**
 * 
 */
package com.swiftcorp.portal.algorithm.dto;

import java.util.ArrayList;
import java.util.List;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
import com.swiftcorp.portal.question.dto.QDTO;

/**
 * @author asraful.haque
 * 
 */
public class AlgQuestionDTO extends PersistentCapableDTO
{
	// question or questionnaire
	private QDTO qdto = new QDTO ();
	
	// list of Question branch
	private List<AlgQuestionBranchDTO> algQuestionBranchList = new ArrayList<AlgQuestionBranchDTO> ();
	
	// last question flag
	private int lastQuestionFlag;
	
	// name - optional here
	private String name;
	
	// branch alu
	private String branchAlu;
	
	public List<AlgQuestionBranchDTO> getAlgQuestionBranchList ( )
	{
		return algQuestionBranchList;
	}
	
	public void setAlgQuestionBranchList ( List<AlgQuestionBranchDTO> algQuestionBranchList )
	{
		this.algQuestionBranchList = algQuestionBranchList;
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
	
	public QDTO getQdto ( )
	{
		return qdto;
	}
	
	public void setQdto ( QDTO qdto )
	{
		this.qdto = qdto;
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
