/*
 * @ (#) IRoleService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.role.service;

import java.util.List;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.role.dao.IRoleDAO.RoleSortBy;
import com.swiftcorp.portal.role.dto.RoleDTO;

/**
 * @author swift
 * @since mar 3, 2011
 */
public interface IRoleService extends IGenericService
{
	public SearchOperationResult search ( String searchQuery )
			throws SystemException, BusinessRuleViolationException;
	
	public List<RoleDTO> getList ( Long groupId, RoleSortBy sortby )
			throws SystemException;
	
	public List<RoleDTO> getList ( ) throws SystemException;
	
}
