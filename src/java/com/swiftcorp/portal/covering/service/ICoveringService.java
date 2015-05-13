/*
 * @ (#) ICoveringService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.covering.service;
import java.util.ArrayList;
import java.util.List;
import com.swiftcorp.portal.covering.exception.CoveringNotFoundException;
import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.covering.dao.ICoveringDAO.CoveringSortBy;
import com.swiftcorp.portal.covering.dto.CoveringDTO;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface ICoveringService extends IGenericService
{
	public SearchOperationResult search(String searchQuery) throws SystemException, BusinessRuleViolationException;
	public List<CoveringDTO> getList(Long groupId, CoveringSortBy sortby)throws SystemException; 
	public List<CoveringDTO> getList()throws SystemException; 
}
