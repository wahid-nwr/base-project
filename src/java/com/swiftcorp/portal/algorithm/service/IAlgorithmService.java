/*
 * @ (#) IAlgorithmService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.algorithm.service;

import java.util.List;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.algorithm.dao.IAlgorithmDAO.AlgorithmSortBy;
import com.swiftcorp.portal.algorithm.dto.AlgorithmDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.question.dto.QDTO;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public interface IAlgorithmService extends IGenericService
{
	public SearchOperationResult search ( String searchQuery )
			throws SystemException, BusinessRuleViolationException;
	
	public List<AlgorithmDTO> getList ( Long groupId, AlgorithmSortBy sortby )
			throws SystemException;
	public GenericDTO getAlgQuestionByQQId ( QDTO qDTOId )
	throws SystemException;
	public List<AlgorithmDTO> getList ( ) throws SystemException;
	public BusinessOperationResult addAlgquestion(GenericDTO genericDTO ) throws SystemException;
	public BusinessOperationResult modifyAlgquestion(GenericDTO genericDTO ) throws SystemException;
	public BusinessOperationResult removeAlgquestion(GenericDTO genericDTO ) throws SystemException;
}
