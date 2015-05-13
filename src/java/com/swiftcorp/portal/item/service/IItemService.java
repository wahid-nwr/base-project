/*
 * @ (#) IItemService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.item.service;
import java.util.ArrayList;
import java.util.List;
import com.swiftcorp.portal.item.exception.ItemNotFoundException;
import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.item.dao.IItemDAO.ItemSortBy;
import com.swiftcorp.portal.item.dto.ItemDTO;
/**
 * @author swift
 * @since mar 3, 2011
 */
public interface IItemService extends IGenericService
{
	public SearchOperationResult search(String searchQuery) throws SystemException, BusinessRuleViolationException;
	public List<ItemDTO> getList(Long groupId, ItemSortBy sortby)throws SystemException; 
	public List<ItemDTO> getList()throws SystemException; 
}
