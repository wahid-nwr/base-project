/*
 * @ (#) functionServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.FunctionSuccessResult;
import com.swiftcorp.portal.common.dao.IFunctionDAO;
import com.swiftcorp.portal.common.dao.IFunctionDAO.FunctionSortBy;
import com.swiftcorp.portal.common.dto.FunctionDTO;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class FunctionServiceImpl implements IFunctionService
{
	private static final Log logger = LogFactory.getLog ( FunctionServiceImpl.class );
	
	private IFunctionDAO functionDAO;
	private ISearcher searcher;
	
	public void setFunctionDAO ( IFunctionDAO functionDAO )
	{
		this.functionDAO = functionDAO;
	}
	
	public void setSearcher ( ISearcher searcher )
	{
		this.searcher = searcher;
	}
	
	public BusinessOperationResult add ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(FunctionDTO) : Enter" );
		FunctionDTO functionDTO = null;
		
		FunctionSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof FunctionDTO )
		{
			functionDTO = (FunctionDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(functionDTO);
		 * logger.info("add(FunctionDTO) : isExist = " + isExist); if(isExist) {
		 * throw new FunctionAlreadyExistsException("already.exist.same.code");
		 * }
		 */
		logger.info ( "add(FunctionDTO) : componentId = " + functionDTO.getComponentId () );
		
		try
		{
			successResult = functionDAO.add ( functionDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(FunctionDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(FunctionDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modify ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modify(FunctionDTO) : Enter" );
		FunctionDTO functionDTO = null;
		FunctionSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof FunctionDTO )
		{
			functionDTO = (FunctionDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modify(FunctionDTO) : componentId = " + functionDTO.getComponentId () );
		
		try
		{
			successResult = functionDAO.modify ( functionDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modify(FunctionDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modify(FunctionDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult remove ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "remove(FunctionDTO) : Enter" );
		FunctionSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "DTO MUST NOT NULL." );
		}
		
		FunctionDTO functionDTO = null;
		if ( genericDTO instanceof FunctionDTO )
		{
			functionDTO = (FunctionDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "INVALID DTO TYPE. MUST BE INSTANCE OF FunctionDTO." );
		}
		
		logger.info ( "remove(FunctionDTO) : code = " + functionDTO.getUniqueCode () );
		logger.info ( "remove(FunctionDTO) : componentId = " + functionDTO.getComponentId () );
		
		try
		{
			successResult = functionDAO.remove ( functionDTO );
		}
		catch (Exception e)
		{
			logger.info ( "remove(FunctionDTO) :", e );
			throw new SystemException ( e );
		}
		logger.info ( "remove(FunctionDTO) : Exit" );
		return successResult;
	}
	
	public GenericDTO get ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		FunctionDTO functionDTO = null;
		try
		{
			functionDTO = functionDAO.get ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return functionDTO;
	}
	
	public GenericDTO get ( String uniqueCode )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "get(code) : Enter" );
		FunctionDTO functionDTO = null;
		try
		{
			functionDTO = functionDAO.get ( uniqueCode );
			/*
			 * if(functionDTO == null) { throw SystemException(e); }
			 */
		}
		catch (Exception e)
		{
			logger.error ( "get(code)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(code) : Exit" );
		return functionDTO;
	}
	
	public List<FunctionDTO> getList ( Long groupId, FunctionSortBy sortby )
			throws SystemException
	{
		logger.info ( "getList(groupId,sortby) : Enter" );
		ArrayList<FunctionDTO> result = null;
		try
		{
			// result = functionDAO.getList(groupId,sortby);
			FunctionDTO functionDTO = new FunctionDTO ();
			
			functionDTO.setFunctionName ( "Function1" );
			result.add ( functionDTO );
			functionDTO = new FunctionDTO ();
			functionDTO.setFunctionName ( "Function2" );
			result.add ( functionDTO );
			functionDTO = new FunctionDTO ();
			functionDTO.setFunctionName ( "Function3" );
			result.add ( functionDTO );
			
			functionDTO = new FunctionDTO ();
			functionDTO.setFunctionName ( "Function4" );
			result.add ( functionDTO );
			
		}
		catch (Exception e)
		{
			logger.error ( "getList(groupId,sortby)", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList(groupId,sortby) : Exit" );
		return result;
	}
	
	@Deprecated
	public List<FunctionDTO> getFunctionListByRole ( int roleComponentId )
			throws SystemException
	{
		logger.info ( "getList(roleComponentId) : Enter" );
		ArrayList<FunctionDTO> result = null;
		try
		{
			result = new ArrayList<FunctionDTO> ();
			
			// result = functionDAO.getList(groupId,sortby);
			FunctionDTO functionDTO = new FunctionDTO ();
			
			functionDTO.setFunctionName ( "Function1" );
			result.add ( functionDTO );
			functionDTO = new FunctionDTO ();
			functionDTO.setFunctionName ( "Function2" );
			result.add ( functionDTO );
			functionDTO = new FunctionDTO ();
			functionDTO.setFunctionName ( "Function3" );
			result.add ( functionDTO );
			
			functionDTO = new FunctionDTO ();
			functionDTO.setFunctionName ( "Function4" );
			result.add ( functionDTO );
			
		}
		catch (Exception e)
		{
			logger.error ( "getList(groupId,sortby)", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList(roleComponentId) : Exit" );
		return result;
	}
	
	public List<FunctionDTO> getList ( ) throws SystemException
	{
		logger.info ( "getList() : Enter" );
		ArrayList<FunctionDTO> result = null;
		try
		{
			result = functionDAO.getList ();
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
	
	private boolean checkUniqueCodeDuplicacy ( FunctionDTO functionDTO )
			throws SystemException
	{
		boolean isExist = false;
		try
		{
			functionDTO = functionDAO.get ( functionDTO.getUniqueCode () );
			if ( functionDTO != null )
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
