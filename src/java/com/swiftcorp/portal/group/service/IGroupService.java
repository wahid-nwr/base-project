/*
 * @ (#) IGroupService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.group.service;

import java.util.ArrayList;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.group.dto.GroupDTO;

/**
 * @author swift
 * @since mar 3, 2011
 */
public interface IGroupService extends IGenericService
{
	public SearchOperationResult search ( String string )
			throws SystemException, BusinessRuleViolationException;
	
	public ArrayList<GroupDTO> getList ( String groupId )
			throws SystemException;
	
	public ArrayList<GroupDTO> getList ( ) throws SystemException;
}
