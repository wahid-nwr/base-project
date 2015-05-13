/*
 * @ (#) IUserService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.user.service;

import java.util.List;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.user.UserSuccessResult;
import com.swiftcorp.portal.user.dao.IUserDAO.UserSortBy;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.user.exception.InvalidPasswordException;
import com.swiftcorp.portal.user.exception.UserAlreadyExistsException;
import com.swiftcorp.portal.user.exception.UserNotFoundException;

/**
 * @author swift
 * @since mar 3, 2011
 */
public interface IUserService extends IGenericService
{
	public SearchOperationResult search ( String searchQuery )
			throws SystemException, BusinessRuleViolationException;
	
	public List<UserDTO> getList ( Long groupId, UserSortBy sortby )
			throws SystemException;
	
	public List<UserDTO> getList ( ) throws SystemException;
	
	public GenericDTO get ( String userId )
			throws SystemException, BusinessRuleViolationException;
	public BusinessOperationResult authenticate ( LoginDTO LoginDTO )
			throws SystemException, UserNotFoundException,
			InvalidPasswordException;
	
}
