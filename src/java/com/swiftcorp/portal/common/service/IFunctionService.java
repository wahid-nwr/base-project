/*
 * @ (#) IQuestionService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.service;

import java.util.List;

import com.swiftcorp.portal.common.dao.IFunctionDAO.FunctionSortBy;
import com.swiftcorp.portal.common.dto.FunctionDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;

/**
 * @author swift
 * @since mar 3, 2011
 */
public interface IFunctionService extends IGenericService
{
	public SearchOperationResult search ( String searchQuery )
			throws SystemException, BusinessRuleViolationException;
	
	public List<FunctionDTO> getList ( Long groupId, FunctionSortBy sortby )
			throws SystemException;
	
	public List<FunctionDTO> getList ( ) throws SystemException;
	
	public List<FunctionDTO> getFunctionListByRole ( int roleComponentId )
			throws SystemException;
}
