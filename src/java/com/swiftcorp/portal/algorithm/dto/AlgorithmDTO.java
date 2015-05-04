package com.swiftcorp.portal.algorithm.dto;

import java.util.HashSet;
import java.util.Set;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class AlgorithmDTO extends PersistentCapableDTO
{
	private String algId = null;
	
	private String code = null;
	// name of the algoirthm
	private String name = null;
	
	// type/category of the algorithm
	private String category;
	
	// version of the algorithm
	private String version;
	
	// status of the algorithm
	private int algStatus;
	
	// first question to ask
	AlgQuestionDTO firstAlgQuestion;
	
	Set<SubAlgorithmDTO> subAlgorithmSet = new HashSet<SubAlgorithmDTO> ();
	
	public int getAlgStatus ( )
	{
		return algStatus;
	}
	
	public void setAlgStatus ( int algStatus )
	{
		this.algStatus = algStatus;
	}
	
	public String getCode ( )
	{
		return this.code;
	}
	
	public String getName ( )
	{
		return this.name;
	}
	
	public void setCode ( String code )
	{
		this.code = code;
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
	
	public AlgQuestionDTO getFirstAlgQuestion ( )
	{
		return firstAlgQuestion;
	}
	
	public void setFirstAlgQuestion ( AlgQuestionDTO firstAlgQuestion )
	{
		this.firstAlgQuestion = firstAlgQuestion;
	}
	
	public String getAlgId ( )
	{
		return algId;
	}
	
	public void setAlgId ( String algId )
	{
		this.algId = algId;
	}
	
	public Set<SubAlgorithmDTO> getSubAlgorithmSet ( )
	{
		return subAlgorithmSet;
	}
	
	public void setSubAlgorithmSet ( Set<SubAlgorithmDTO> subAlgorithmSet )
	{
		this.subAlgorithmSet = subAlgorithmSet;
	}
	
}
