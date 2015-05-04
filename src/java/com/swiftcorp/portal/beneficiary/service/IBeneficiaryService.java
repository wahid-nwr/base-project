/*
 * @ (#) IBeneficiaryService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.beneficiary.service;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public interface IBeneficiaryService extends IGenericService
{
	public SearchOperationResult search ( String query ) throws SystemException, BusinessRuleViolationException;
	
}
