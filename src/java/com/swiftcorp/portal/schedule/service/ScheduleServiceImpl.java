/*
 * @ (#) ScheduleServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.schedule.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.beneficiary.dto.VisitDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.schedule.ScheduleSuccessResult;
import com.swiftcorp.portal.schedule.dao.IScheduleDAO;
import com.swiftcorp.portal.schedule.dao.IScheduleDAO.ScheduleSortBy;
import com.swiftcorp.portal.schedule.dto.ScheduleDTO;
import com.swiftcorp.portal.schedule.dto.SchedulingConstants;
import com.swiftcorp.portal.schedule.exception.ScheduleNotFoundException;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class ScheduleServiceImpl implements IScheduleService
{
	private static final Log logger = LogFactory.getLog ( ScheduleServiceImpl.class );
	
	private IScheduleDAO scheduleDAO;
	private ISearcher searcher;
	
	public void setScheduleDAO ( IScheduleDAO scheduleDAO )
	{
		this.scheduleDAO = scheduleDAO;
	}
	
	public void setSearcher ( ISearcher searcher )
	{
		this.searcher = searcher;
	}
	
	public BusinessOperationResult add ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(ScheduleDTO) : Enter" );
		ScheduleDTO scheduleDTO = null;
		
		ScheduleSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof ScheduleDTO )
		{
			scheduleDTO = (ScheduleDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(scheduleDTO);
		 * logger.info("add(ScheduleDTO) : isExist = " + isExist); if(isExist) {
		 * throw new ScheduleAlreadyExistsException("already.exist.same.code");
		 * }
		 */
		logger.info ( "add(ScheduleDTO) : componentId = " + scheduleDTO.getComponentId () );
		
		try
		{
			successResult = scheduleDAO.add ( scheduleDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(ScheduleDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(ScheduleDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult addOrUpdate ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(ScheduleDTO) : Enter" );
		ScheduleDTO scheduleDTO = null;
		
		ScheduleSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof ScheduleDTO )
		{
			scheduleDTO = (ScheduleDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		logger.info ( "add(ScheduleDTO) : componentId = " + scheduleDTO.getComponentId () );
		
		try
		{
			successResult = scheduleDAO.addOrUpdate ( scheduleDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(ScheduleDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(ScheduleDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modify ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modify(ScheduleDTO) : Enter" );
		ScheduleDTO scheduleDTO = null;
		ScheduleSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof ScheduleDTO )
		{
			scheduleDTO = (ScheduleDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modify(ScheduleDTO) : componentId = " + scheduleDTO.getComponentId () );
		
		try
		{
			successResult = scheduleDAO.modify ( scheduleDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modify(ScheduleDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modify(ScheduleDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult remove ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "remove(ScheduleDTO) : Enter" );
		ScheduleSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "DTO MUST NOT NULL." );
		}
		
		ScheduleDTO scheduleDTO = null;
		if ( genericDTO instanceof ScheduleDTO )
		{
			scheduleDTO = (ScheduleDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "INVALID DTO TYPE. MUST BE INSTANCE OF ScheduleDTO." );
		}
		
		logger.info ( "remove(ScheduleDTO) : code = " + scheduleDTO.getUniqueCode () );
		logger.info ( "remove(ScheduleDTO) : componentId = " + scheduleDTO.getComponentId () );
		
		try
		{
			successResult = scheduleDAO.remove ( scheduleDTO );
		}
		catch (Exception e)
		{
			logger.info ( "remove(ScheduleDTO) :", e );
			throw new SystemException ( e );
		}
		logger.info ( "remove(ScheduleDTO) : Exit" );
		return successResult;
	}
	
	public GenericDTO get ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		ScheduleDTO scheduleDTO = null;
		try
		{
			scheduleDTO = scheduleDAO.get ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return scheduleDTO;
	}
	
	public GenericDTO get ( String uniqueCode )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "get(code) : Enter" );
		ScheduleDTO scheduleDTO = null;
		try
		{
			scheduleDTO = scheduleDAO.get ( uniqueCode );
			if ( scheduleDTO == null )
			{
				throw new ScheduleNotFoundException ( "schedule.not.found" );
			}
		}
		catch (Exception e)
		{
			logger.error ( "get(code)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(code) : Exit" );
		return scheduleDTO;
	}
	
	public List<ScheduleDTO> getList ( Long groupId, ScheduleSortBy sortby )
			throws SystemException
	{
		logger.info ( "getList(groupId,sortby) : Enter" );
		ArrayList<ScheduleDTO> result = null;
		try
		{
			result = scheduleDAO.getList ( groupId, sortby );
		}
		catch (Exception e)
		{
			logger.error ( "getList(groupId,sortby)", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList(groupId,sortby) : Exit" );
		return result;
	}
	
	public List<ScheduleDTO> getList ( ) throws SystemException
	{
		logger.info ( "getList() : Enter" );
		ArrayList<ScheduleDTO> result = null;
		try
		{
			result = scheduleDAO.getList ();
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
	
	private boolean checkUniqueCodeDuplicacy ( ScheduleDTO scheduleDTO )
			throws SystemException
	{
		boolean isExist = false;
		try
		{
			scheduleDTO = scheduleDAO.get ( scheduleDTO.getUniqueCode () );
			if ( scheduleDTO != null )
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
	
	public List<ScheduleDTO> getMissedScheduleListByDate ( Calendar date )
	{
		List<ScheduleDTO> scheduleList = null;
		
		try
		{
			scheduleList = this.scheduleDAO.getSheduleListByDateAndState ( date, SchedulingConstants.NOT_VISITED );
		}
		catch (SystemException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		
		return scheduleList;
	}
	
	public BusinessOperationResult updateScheduleByVisit ( Calendar nextVisitDate, String visitItemId, VisitDTO visitDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modify(ScheduleDTO) : Enter" );
		ScheduleDTO scheduleDTO = null;
		ScheduleSuccessResult successResult;
		List<ScheduleDTO> scheduleList = scheduleDAO.getSheduleDTOByDateAndVisitItemId ( nextVisitDate, visitItemId );
		if ( scheduleList != null && scheduleList.size () > 0 )
		{
			scheduleDTO = (ScheduleDTO) (scheduleList.get ( 0 ));
			logger.info ( "modify(ScheduleDTO) : componentId = " + scheduleDTO.getComponentId () );
			// set state as visited
			scheduleDTO.setState ( SchedulingConstants.VISITED );
			scheduleDTO.setVisitDTO ( visitDTO );
		}
		if ( scheduleDTO == null )
		{
			throw new ScheduleNotFoundException ( "schedule.not.found" );
		}
		try
		{
			successResult = scheduleDAO.modify ( scheduleDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modify(ScheduleDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modify(ScheduleDTO) : Exit" );
		return successResult;
	}
	
	public void saveScheduleList ( List<ScheduleDTO> scheduledList )
	{
		if ( scheduledList == null )
		{
			return;
		}
		// iterate through the list
		for ( ScheduleDTO scheduleDTO : scheduledList )
		{
			try
			{
				this.addOrUpdate ( scheduleDTO );
			}
			catch (Exception e)
			{
				// log error
				logger.error ( "Error occured while saving schedule ", e );
			}
			
		}
	}
	
	public List<ScheduleDTO> getScheduleListByUserAndDate ( UserDTO user, Calendar scheduleDate )
			throws SystemException
	{
		// get the list
		List<ScheduleDTO> scheduleList = this.scheduleDAO.getScheduleListByUserAndDate ( user, scheduleDate );
		
		// return the list
		return scheduleList;
	}
}
