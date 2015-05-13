/*
 * @ (#) RoleServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */

package com.swiftcorp.portal.role.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.role.RoleSuccessResult;
import com.swiftcorp.portal.role.dao.IRoleDAO;
import com.swiftcorp.portal.role.dao.IRoleDAO.RoleSortBy;
import com.swiftcorp.portal.role.dto.RoleDTO;
import com.swiftcorp.portal.role.exception.RoleAlreadyExistsException;
import com.swiftcorp.portal.role.exception.RoleNotFoundException;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class RoleServiceImpl implements IRoleService
{
	private static final Log logger = LogFactory.getLog ( RoleServiceImpl.class );
	
	private IRoleDAO roleDAO;
	private ISearcher searcher;
	private ILoginService loginService;
	
	public static Log getLogger ( )
	{
		return logger;
	}
	
	public ILoginService getLoginService ( )
	{
		return loginService;
	}
	
	public void setLoginService ( ILoginService loginService )
	{
		this.loginService = loginService;
	}
	
	public IRoleDAO getRoleDAO ( )
	{
		return roleDAO;
	}
	
	public void setRoleDAO ( IRoleDAO roleDAO )
	{
		this.roleDAO = roleDAO;
	}
	
	public ISearcher getSearcher ( )
	{
		return searcher;
	}
	
	public void setSearcher ( ISearcher searcher )
	{
		this.searcher = searcher;
	}
	
	public BusinessOperationResult add ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(RoleDTO) : Enter" );
		RoleDTO roleDTO = null;
		
		RoleSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof RoleDTO )
		{
			roleDTO = (RoleDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		boolean isExist = checkUniqueCodeDuplicacy ( roleDTO );
		logger.info ( "add(RoleDTO) : isExist = " + isExist );
		if ( isExist )
		{
			throw new RoleAlreadyExistsException ( "already.exist.same.code" );
		}
		
		logger.info ( "add(RoleDTO) : componentId = " + roleDTO.getComponentId () );
		
		// check the group ID.if NO group is selected, then add in orphan group
		// (system default group)
		/*
		 * Long groupId = roleDTO.getGroupId() ; if(groupId != null) {
		 * roleDTO.setGroupId(groupId); } else { roleDTO.setGroupId(new
		 * Long(GlobalConstants.GROUPID_OF_ORPHAN_ROLES)); }
		 */

		try
		{
			successResult = roleDAO.add ( roleDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(RoleDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(RoleDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modify ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modify(RoleDTO) : Enter" );
		RoleDTO roleDTO = null;
		
		RoleSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof RoleDTO )
		{
			roleDTO = (RoleDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modify(RoleDTO) : componentId = " + roleDTO.getComponentId () );
		
		try
		{
			successResult = roleDAO.modify ( roleDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modify(RoleDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modify(RoleDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult remove ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "remove(RoleDTO) : Enter" );
		RoleSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "DTO MUST NOT NULL." );
		}
		
		RoleDTO roleDTO = null;
		if ( genericDTO instanceof RoleDTO )
		{
			roleDTO = (RoleDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "INVALID DTO TYPE. MUST BE INSTANCE OF RoleDTO." );
		}
		
		logger.info ( "remove(RoleDTO) : code = " + roleDTO.getUniqueCode () );
		logger.info ( "remove(RoleDTO) : componentId = " + roleDTO.getComponentId () );
		
		try
		{
			successResult = roleDAO.remove ( roleDTO );
		}
		catch (Exception e)
		{
			logger.info ( "remove(RoleDTO) :", e );
			throw new SystemException ( e );
		}
		logger.info ( "remove(RoleDTO) : Exit" );
		return successResult;
	}
	
	public GenericDTO get ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		RoleDTO roleDTO = null;
		try
		{
			roleDTO = roleDAO.get ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return roleDTO;
	}
	
	public GenericDTO get ( String uniqueCode )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "get(code) : Enter" );
		RoleDTO roleDTO = null;
		try
		{
			roleDTO = roleDAO.get ( uniqueCode );
			if ( roleDTO == null )
			{
				throw new RoleNotFoundException ( "role.not.found" );
			}
		}
		catch (Exception e)
		{
			logger.error ( "get(code)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(code) : Exit" );
		return roleDTO;
	}
	
	public List<RoleDTO> getList ( Long groupId, RoleSortBy sortby )
			throws SystemException
	{
		logger.info ( "getList(groupId,sortby) : Enter" );
		ArrayList<RoleDTO> result = null;
		try
		{
			result = roleDAO.getList ( groupId, sortby );
		}
		catch (Exception e)
		{
			logger.error ( "getList(groupId,sortby)", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList(groupId,sortby) : Exit" );
		return result;
	}
	
	public List<RoleDTO> getList ( ) throws SystemException
	{
		logger.info ( "getList() : Enter" );
		ArrayList<RoleDTO> result = null;
		try
		{
			result = roleDAO.getList ();
		}
		catch (Exception e)
		{
			logger.error ( "getList()", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList() : Exit" );
		return result;
	}
	
	public SearchOperationResult search ( String query )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "search() : Enter" );
		SearchOperationResult searchResult = null;
		try
		{
			searchResult = searcher.search ( query );
		}
		catch (InvalidSQLSyntaxException e)
		{
			logger.info ( "search() :", e );
			throw e;
		}
		catch (SystemException e)
		{
			logger.info ( "search() :", e );
			throw e;
		}
		logger.info ( "search() : Exit" );
		return searchResult;
	}
	
	private boolean checkUniqueCodeDuplicacy ( RoleDTO roleDTO )
			throws SystemException
	{
		boolean isExist = false;
		try
		{
			roleDTO = roleDAO.get ( roleDTO.getUniqueCode () );
			if ( roleDTO != null )
			{
				isExist = true;
			}
		}
		catch (SystemException e)
		{
			throw e;
		}
		return isExist;
	}
	
}
