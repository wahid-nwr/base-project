/*
 * @ (#) AlertServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.alert.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.alert.AlertSuccessResult;
import com.swiftcorp.portal.alert.dao.IAlertDAO;
import com.swiftcorp.portal.alert.dao.IAlertDAO.AlertSortBy;
import com.swiftcorp.portal.alert.dto.AlertDTO;
import com.swiftcorp.portal.alert.exception.AlertNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.user.service.IUserService;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class AlertServiceImpl implements IAlertService
{
	private static final Log logger = LogFactory.getLog ( AlertServiceImpl.class );
	
	private IAlertDAO alertDAO;
	private ISearcher searcher;
	
	private IUserService userService;
	
	public void setUserService ( IUserService userService )
	{
		this.userService = userService;
	}
	
	
	
	public void setAlertDAO ( IAlertDAO alertDAO )
	{
		this.alertDAO = alertDAO;
	}
	
	public void setSearcher ( ISearcher searcher )
	{
		this.searcher = searcher;
	}
	
	public BusinessOperationResult add ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(AlertDTO) : Enter" );
		AlertDTO alertDTO = null;
		
		AlertSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof AlertDTO )
		{
			alertDTO = (AlertDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(alertDTO);
		 * logger.info("add(AlertDTO) : isExist = " + isExist); if(isExist) {
		 * throw new AlertAlreadyExistsException("already.exist.same.code"); }
		 */
		logger.info ( "add(AlertDTO) : componentId = " + alertDTO.getComponentId () );
		
		try
		{
			successResult = alertDAO.add ( alertDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(AlertDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(AlertDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modify ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modify(AlertDTO) : Enter" );
		AlertDTO alertDTO = null;
		AlertSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof AlertDTO )
		{
			alertDTO = (AlertDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modify(AlertDTO) : componentId = " + alertDTO.getComponentId () );
		
		try
		{
			successResult = alertDAO.modify ( alertDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modify(AlertDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modify(AlertDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult remove ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "remove(AlertDTO) : Enter" );
		AlertSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "DTO MUST NOT NULL." );
		}
		
		AlertDTO alertDTO = null;
		if ( genericDTO instanceof AlertDTO )
		{
			alertDTO = (AlertDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "INVALID DTO TYPE. MUST BE INSTANCE OF AlertDTO." );
		}
		
		logger.info ( "remove(AlertDTO) : code = " + alertDTO.getUniqueCode () );
		logger.info ( "remove(AlertDTO) : componentId = " + alertDTO.getComponentId () );
		
		try
		{
			successResult = alertDAO.remove ( alertDTO );
		}
		catch (Exception e)
		{
			logger.info ( "remove(AlertDTO) :", e );
			throw new SystemException ( e );
		}
		logger.info ( "remove(AlertDTO) : Exit" );
		return successResult;
	}
	
	public GenericDTO get ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		AlertDTO alertDTO = null;
		try
		{
			alertDTO = alertDAO.get ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return alertDTO;
	}
	
	public GenericDTO get ( String uniqueCode )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "get(code) : Enter" );
		AlertDTO alertDTO = null;
		try
		{
			alertDTO = alertDAO.get ( uniqueCode );
			if ( alertDTO == null )
			{
				throw new AlertNotFoundException ( "alert.not.found" );
			}
		}
		catch (Exception e)
		{
			logger.error ( "get(code)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(code) : Exit" );
		return alertDTO;
	}
	
	public List<AlertDTO> getList ( Long groupId, AlertSortBy sortby )
			throws SystemException
	{
		logger.info ( "getList(groupId,sortby) : Enter" );
		ArrayList<AlertDTO> result = null;
		try
		{
			result = alertDAO.getList ( groupId, sortby );
		}
		catch (Exception e)
		{
			logger.error ( "getList(groupId,sortby)", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList(groupId,sortby) : Exit" );
		return result;
	}
	
	public List<AlertDTO> getList ( ) throws SystemException
	{
		logger.info ( "getList() : Enter" );
		ArrayList<AlertDTO> result = null;
		try
		{
			result = alertDAO.getList ();
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
	
	private boolean checkUniqueCodeDuplicacy ( AlertDTO alertDTO )
			throws SystemException
	{
		boolean isExist = false;
		try
		{
			alertDTO = alertDAO.get ( alertDTO.getUniqueCode () );
			if ( alertDTO != null )
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
	
	/*
	 * returns alert list by skid
	 */
	@Override
	public ArrayList<AlertDTO> generateAlertBySkId ( String skId, Calendar date )
			throws SystemException
	{
		logger.info ( "getAlertBySkIdList() : Enter" );
		ArrayList<AlertDTO> alertList = null;
		try
		{
			UserDTO userDTO = (UserDTO) userService.get ( skId );
			// get search result
			alertList = alertDAO.getAlertBySkId ( userDTO, date );
		}
		catch (BusinessRuleViolationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		
		logger.info ( "getAlertBySkIdList() : Exit" );
		// return alert list
		return alertList;
		
	}
	
	public void saveAlertList ( List<AlertDTO> alertList )
	{
		logger.info ( "saveAlertList() : enter" );
		
		if ( alertList == null || alertList.size () == 0 )
		{
			logger.info ( "No alert in the list so return " );
			return;
		}
		
		for ( AlertDTO alertDTO : alertList )
		{
			try
			{
				this.alertDAO.add ( alertDTO );
			}
			catch (SystemException e)
			{
				logger.error ( "Error occured while saving the alert with id " + alertDTO.getAlertId () );
			}
		}
		
		logger.info ( "saveAlertList() : exit" );
	}
}
