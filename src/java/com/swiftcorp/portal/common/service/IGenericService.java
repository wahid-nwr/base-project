/*
 * @ (#) IGenericService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.service;

import java.sql.SQLException;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;

/**
 * @author swift
 * @since mar 3, 2011
 */
public interface IGenericService
{
	public BusinessOperationResult add ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException;
	
	public BusinessOperationResult modify ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException,
			SQLException, Exception;
	
	public BusinessOperationResult remove ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException;
	
	public GenericDTO get ( Long componentId )
			throws SystemException, BusinessRuleViolationException;
	
	public GenericDTO get ( String uniqueCode )
			throws SystemException, BusinessRuleViolationException;
		
}
