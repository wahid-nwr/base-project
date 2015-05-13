/*
 * @ (#) GroupService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.group.service;

import java.util.ArrayList;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.group.GroupSuccessResult;
import com.swiftcorp.portal.group.dao.IGroupDAO;
import com.swiftcorp.portal.group.dto.GroupDTO;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class GroupServiceImpl implements IGroupService
{
	IGroupDAO groupDAO;
	ISearcher searcher;
	
	public void setSearcher ( ISearcher searcher )
	{
		this.searcher = searcher;
	}
	
	public void setGroupDAO ( IGroupDAO groupDAO )
	{
		this.groupDAO = groupDAO;
	}
	
	public BusinessOperationResult add ( GenericDTO groupDTO )
			throws SystemException, BusinessRuleViolationException
	{
		GroupSuccessResult successResult = groupDAO.add ( (GroupDTO) groupDTO );
		return successResult;
	}
	
	public BusinessOperationResult modify ( GenericDTO groupDTO )
			throws SystemException, BusinessRuleViolationException
	{
		GroupSuccessResult successResult = groupDAO.modify ( (GroupDTO) groupDTO );
		return successResult;
	}
	
	public BusinessOperationResult remove ( GenericDTO groupDTO )
			throws SystemException, BusinessRuleViolationException
	{
		GroupSuccessResult successResult = groupDAO.remove ( (GroupDTO) groupDTO );
		return successResult;
	}
	
	public GenericDTO get ( Long componentId )
			throws SystemException, BusinessRuleViolationException
	{
		GroupDTO groupDTO = groupDAO.get ( componentId );
		return groupDTO;
	}
	
	public GenericDTO get ( String uniqueCode )
			throws SystemException, BusinessRuleViolationException
	{
		GroupDTO groupDTO = groupDAO.get ( uniqueCode );
		return groupDTO;
	}
	
	public SearchOperationResult search ( String query )
			throws SystemException, BusinessRuleViolationException
	{
		SearchOperationResult searchResult = searcher.search ( query );
		return searchResult;
	}
	
	public ArrayList<GroupDTO> getList ( String groupId )
			throws SystemException
	{
		return groupDAO.getList ( groupId );
	}
	
	public ArrayList<GroupDTO> getList ( ) throws SystemException
	{
		return groupDAO.getList ();
	}
	
}
