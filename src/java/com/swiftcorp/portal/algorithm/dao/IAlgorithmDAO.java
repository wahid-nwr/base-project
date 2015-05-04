package com.swiftcorp.portal.algorithm.dao;

import java.util.ArrayList;

import com.swiftcorp.portal.algorithm.AlgQuestionSuccessResult;
import com.swiftcorp.portal.algorithm.AlgorithmSuccessResult;
import com.swiftcorp.portal.algorithm.dto.AlgQuestionDTO;
import com.swiftcorp.portal.algorithm.dto.AlgorithmDTO;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.question.dto.QDTO;

public interface IAlgorithmDAO
{
	public enum AlgorithmSortBy
	{
		algId, adminType, firstName, lastname
	};
	
	public enum AlgorithmWhereCondition
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public AlgorithmDTO get ( Long componentId ) throws SystemException;
	public AlgQuestionDTO getAlgQuestionByQQId ( QDTO qdto ) throws SystemException;
	
	public AlgorithmDTO get ( String unicodeCode ) throws SystemException;
	
	public AlgorithmSuccessResult add ( AlgorithmDTO algorithmDTO )
			throws SystemException;
	public AlgQuestionSuccessResult addAlgQuestion ( AlgQuestionDTO algQuestionDTO )
	throws SystemException;
	
	public AlgQuestionSuccessResult modifyAlgQuestion ( AlgQuestionDTO algQuestionDTO )
	throws SystemException;
	
	public AlgQuestionSuccessResult removeAlgQuestion ( AlgQuestionDTO algQuestionDTO )
	throws SystemException;
	
	public AlgorithmSuccessResult modify ( AlgorithmDTO algorithmDTO )
			throws SystemException;
	
	public AlgorithmSuccessResult remove ( AlgorithmDTO algorithmDTO )
			throws SystemException;
	
	public ArrayList<AlgorithmDTO> getList ( ) throws SystemException;
	
	public ArrayList<AlgorithmDTO> getList ( Long groupId, AlgorithmSortBy sortby )
			throws SystemException;
	
}
