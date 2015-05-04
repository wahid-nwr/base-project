package com.swiftcorp.portal.algorithm.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class SubAlgorithmDTO extends PersistentCapableDTO
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
	
	private long algorithmId;
	
	// first question to ask
	AlgQuestionDTO firstAlgQuestion;
	AlgQuestionDTO nextAlgQuestion;
	AlgQuestionDTO prevAlgQuestion;
	
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

	
	public AlgQuestionDTO getNextAlgQuestion ( )
	{
		return nextAlgQuestion;
	}

	public void setNextAlgQuestion ( AlgQuestionDTO nextAlgQuestion )
	{
		this.nextAlgQuestion = nextAlgQuestion;
	}

	public AlgQuestionDTO getPrevAlgQuestion ( )
	{
		return prevAlgQuestion;
	}

	public void setPrevAlgQuestion ( AlgQuestionDTO prevAlgQuestion )
	{
		this.prevAlgQuestion = prevAlgQuestion;
	}

	public long getAlgorithmId ( )
	{
		return algorithmId;
	}

	public void setAlgorithmId ( long algorithmId )
	{
		this.algorithmId = algorithmId;
	}
	
}
