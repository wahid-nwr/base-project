package com.swiftcorp.portal.dto.sync;

import java.util.ArrayList;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class CASAlgorithmDTO extends PersistentCapableDTO
{
	// algorithm id
	private String algId = null;
	
	// name of the algoirthm
	private String name = null;
	
	// type/category of the algorithm
	private String category;
	
	// version of the algorithm
	private String version;
	
	// algorithm question id
	private String firstAlgQuestionId;
	
	// algorithm question List
	ArrayList<CASAlgQuestionDTO> casAlgQuestionDTOList = new ArrayList<CASAlgQuestionDTO> ();
	// algorithm branch list
	ArrayList<CASAlgQuestionBranchDTO> casAlgQuestionBranchDTOList = new ArrayList<CASAlgQuestionBranchDTO> ();
	// algorithm question list
	ArrayList<CASQuestionDTO> casQuestionDTOList = new ArrayList<CASQuestionDTO> ();
	// algoirthm questionnaire list
	ArrayList<CASQuestionnaireDTO> casQuestionnaireDTOList = new ArrayList<CASQuestionnaireDTO> ();
	
	// algorithm answer list
	ArrayList<CASAlgAnswerDTO> casAlgAnswerDTOList = new ArrayList<CASAlgAnswerDTO> ();
	ArrayList<CASSubAlgorithmDTO> casSubAlgorithmList = new ArrayList<CASSubAlgorithmDTO> ();
	
	// Getter and Setter
	public String getName ( )
	{
		return this.name;
	}
	
	public void setName ( String name )
	{
		this.name = name;
	}
	
	public String getCategory ( )
	{
		return category;
	}
	
	public void setCategory ( String category )
	{
		this.category = category;
	}
	
	public String getVersion ( )
	{
		return version;
	}
	
	public void setVersion ( String version )
	{
		this.version = version;
	}
	
	public String getAlgId ( )
	{
		return algId;
	}
	
	public void setAlgId ( String algId )
	{
		this.algId = algId;
	}
	
	public String getFirstAlgQuestionId ( )
	{
		return firstAlgQuestionId;
	}
	
	public void setFirstAlgQuestionId ( String firstAlgQuestionId )
	{
		this.firstAlgQuestionId = firstAlgQuestionId;
	}
	
	public ArrayList<CASAlgQuestionDTO> getCasAlgQuestionDTOList ( )
	{
		return casAlgQuestionDTOList;
	}
	
	public void setCasAlgQuestionDTOList ( ArrayList<CASAlgQuestionDTO> casAlgQuestionDTOList )
	{
		this.casAlgQuestionDTOList = casAlgQuestionDTOList;
	}
	
	public ArrayList<CASAlgQuestionBranchDTO> getCasAlgQuestionBranchDTOList ( )
	{
		return casAlgQuestionBranchDTOList;
	}
	
	public void setCasAlgQuestionBranchDTOList ( ArrayList<CASAlgQuestionBranchDTO> casAlgQuestionBranchDTOList )
	{
		this.casAlgQuestionBranchDTOList = casAlgQuestionBranchDTOList;
	}
	
	public ArrayList<CASQuestionDTO> getCasQuestionDTOList ( )
	{
		return casQuestionDTOList;
	}
	
	public void setCasQuestionDTOList ( ArrayList<CASQuestionDTO> casQuestionDTOList )
	{
		this.casQuestionDTOList = casQuestionDTOList;
	}
	
	public ArrayList<CASQuestionnaireDTO> getCasQuestionnaireDTOList ( )
	{
		return casQuestionnaireDTOList;
	}
	
	public void setCasQuestionnaireDTOList ( ArrayList<CASQuestionnaireDTO> casQuestionnaireDTOList )
	{
		this.casQuestionnaireDTOList = casQuestionnaireDTOList;
	}
	
	public ArrayList<CASAlgAnswerDTO> getCasAlgAnswerDTOList ( )
	{
		return casAlgAnswerDTOList;
	}
	
	public void setCasAlgAnswerDTOList ( ArrayList<CASAlgAnswerDTO> casAlgAnswerDTOList )
	{
		this.casAlgAnswerDTOList = casAlgAnswerDTOList;
	}

	public ArrayList<CASSubAlgorithmDTO> getCasSubAlgorithmList ( )
	{
		return casSubAlgorithmList;
	}

	public void setCasSubAlgorithmList ( ArrayList<CASSubAlgorithmDTO> casSubAlgorithmList )
	{
		this.casSubAlgorithmList = casSubAlgorithmList;
	}
	
}
