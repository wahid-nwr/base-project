/*
 * @ (#) IGroupDAO.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.group.dao;

import java.util.ArrayList;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.exception.UniqueCodeRequiredException;
import com.swiftcorp.portal.group.GroupSuccessResult;
import com.swiftcorp.portal.group.dto.GroupDTO;
import com.swiftcorp.portal.group.exception.GroupCreationException;
import com.swiftcorp.portal.group.exception.GroupForThisCodeNotFoundException;
import com.swiftcorp.portal.group.exception.GroupWithSameCodeAlreadyExistsException;
import com.swiftcorp.portal.group.exception.InvalidParentException;

/**
 * @author swift
 * @since mar 3, 2011
 */
public interface IGroupDAO
{
	/**
	 * 
	 * @param componentId
	 * @return
	 * @throws GroupForThisComponentIdNotFoundException
	 * @throws BusinessRuleViolationException
	 */
	public GroupDTO get ( Long componentId )
			throws BusinessRuleViolationException;
	
	/**
	 * 
	 * @param unicodeCode
	 * @return
	 * @throws GroupForThisUniqueCodeNotFoundException
	 * @throws BusinessRuleViolationException
	 */
	public GroupDTO get ( String unicodeCode )
			throws GroupForThisCodeNotFoundException;
	
	/**
	 * 
	 * @param groupDTO
	 * @return
	 * @throws GroupWithSameCodeAlreadyExistsException
	 * @throws GroupCreationException
	 * @throws SystemException
	 * @throws InvalidDateException
	 * @throws UniqueCodeRequiredException
	 * @throws InvalidParentException
	 * @throws BusinessRuleViolationException
	 */
	public GroupSuccessResult add ( GroupDTO groupDTO )
			throws GroupWithSameCodeAlreadyExistsException,
			GroupCreationException, SystemException, InvalidDateException,
			UniqueCodeRequiredException, InvalidParentException;
	
	/**
	 * 
	 * @param groupDTO
	 * @return
	 * @throws BusinessRuleViolationException
	 */
	public GroupSuccessResult modify ( GroupDTO groupDTO )
			throws BusinessRuleViolationException;
	
	/**
	 * 
	 * @param groupDTO
	 * @return
	 * @throws BusinessRuleViolationException
	 */
	public GroupSuccessResult remove ( GroupDTO groupDTO )
			throws BusinessRuleViolationException;
	
	public ArrayList<GroupDTO> getList ( String groupId )
			throws SystemException;
	
	public ArrayList<GroupDTO> getList ( ) throws SystemException;
	
}
