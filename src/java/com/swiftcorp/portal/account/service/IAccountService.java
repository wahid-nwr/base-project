/*
 * @ (#) IAccountService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.account.service;
import java.util.ArrayList;
import java.util.List;
import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.account.dao.IAccountDAO.AccountSortBy;
import com.swiftcorp.portal.account.dto.AccountDTO;
import com.swiftcorp.portal.account.exception.AccountNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
/**
 * @author mosa
 * @since Sep 8, 2008
 */
public interface IAccountService extends IGenericService
{
	public SearchOperationResult search(String searchQuery) throws SystemException, BusinessRuleViolationException;
	public List<AccountDTO> getList(Long groupId, AccountSortBy sortby)throws SystemException; 
	public List<AccountDTO> getList()throws SystemException; 
}
