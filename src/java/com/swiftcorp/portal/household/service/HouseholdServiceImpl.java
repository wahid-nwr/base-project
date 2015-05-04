/*
 * @ (#) HouseholdServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.household.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.household.FamilyPlanningInfoSuccessResult;
import com.swiftcorp.portal.household.HouseholdMemberSuccessResult;
import com.swiftcorp.portal.household.HouseholdMemberVisitSuccessResult;
import com.swiftcorp.portal.household.HouseholdSuccessResult;
import com.swiftcorp.portal.household.dao.IHouseholdDAO;
import com.swiftcorp.portal.household.dao.IHouseholdDAO.HouseholdSortBy;
import com.swiftcorp.portal.household.dto.FamilyPlanningInfoDTO;
import com.swiftcorp.portal.household.dto.HouseholdDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberVisitDTO;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class HouseholdServiceImpl implements IHouseholdService
{
	private static final Log logger = LogFactory.getLog ( HouseholdServiceImpl.class );
	
	private IHouseholdDAO householdDAO;
	private ISearcher searcher;
	
	public void setHouseholdDAO ( IHouseholdDAO householdDAO )
	{
		this.householdDAO = householdDAO;
	}
	
	public void setSearcher ( ISearcher searcher )
	{
		this.searcher = searcher;
	}
	
	public BusinessOperationResult add ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(HouseholdDTO) : Enter" );
		HouseholdDTO householdDTO = null;
		
		HouseholdSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof HouseholdDTO )
		{
			householdDTO = (HouseholdDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(householdDTO);
		 * logger.info("add(HouseholdDTO) : isExist = " + isExist); if(isExist)
		 * { throw new
		 * HouseholdAlreadyExistsException("already.exist.same.code"); }
		 */
		logger.info ( "add(HouseholdDTO) : componentId = " + householdDTO.getComponentId () );
		
		try
		{
			successResult = householdDAO.add ( householdDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(HouseholdDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(HouseholdDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modify ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modify(HouseholdDTO) : Enter" );
		HouseholdDTO householdDTO = null;
		HouseholdSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof HouseholdDTO )
		{
			householdDTO = (HouseholdDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modify(HouseholdDTO) : componentId = " + householdDTO.getComponentId () );
		
		try
		{
			successResult = householdDAO.modify ( householdDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modify(HouseholdDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modify(HouseholdDTO) : Exit" );
		return successResult;
	}
	
	// For Householdmember
	
	public BusinessOperationResult addHouseholdMember ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "addHousholdMember(HouseholdDTO) : Enter" );
		HouseholdMemberDTO householdMemberDTO = null;
		
		HouseholdMemberSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof HouseholdMemberDTO )
		{
			householdMemberDTO = (HouseholdMemberDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(householdDTO);
		 * logger.info("addHousholdMember(HouseholdDTO) : isExist = " +
		 * isExist); if(isExist) { throw new
		 * HouseholdAlreadyExistsException("already.exist.same.code"); }
		 */
		logger.info ( "addHousholdMember(HouseholdMemberDTO) : componentId = " + householdMemberDTO.getComponentId () );
		
		try
		{
			successResult = householdDAO.addHouseholdMember ( householdMemberDTO );
		}
		catch (Exception e)
		{
			logger.info ( "addHousholdMember(HouseholdMemberDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "addHousholdMember(HouseholdMemberDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modifyHouseholdMember ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modifyHousholdMember(HousholdMemberDTO) : Enter" );
		HouseholdMemberDTO householdMemberDTO = null;
		HouseholdMemberSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof HouseholdMemberDTO )
		{
			householdMemberDTO = (HouseholdMemberDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modifyHousholdMember(HouseholdMemberDTO) : componentId = " + householdMemberDTO.getComponentId () );
		
		try
		{
			successResult = householdDAO.modifyHouseholdMember ( householdMemberDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modifyHousholdMember(HouseholdMemberDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modifyHousholdMember(HouseholdMemberDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult addHouseholdMemberVisit ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "addHousholdMemberVisit(HouseholdMemberVisitDTO) : Enter" );
		HouseholdMemberVisitDTO householdMemberVisitDTO = null;
		
		HouseholdMemberVisitSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof HouseholdMemberVisitDTO )
		{
			householdMemberVisitDTO = (HouseholdMemberVisitDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(householdDTO);
		 * logger.info("addHousholdMemberVisit(HouseholdDTO) : isExist = " +
		 * isExist); if(isExist) { throw new
		 * HouseholdAlreadyExistsException("already.exist.same.code"); }
		 */
		logger.info ( "addHousholdMemberVisit(HouseholdMemberVisitDTO) : componentId = " + householdMemberVisitDTO.getComponentId () );
		
		try
		{
			successResult = householdDAO.addHouseholdMemberVisit ( householdMemberVisitDTO );
		}
		catch (Exception e)
		{
			logger.info ( "addHousholdMemberVisit(HouseholdMemberVisitDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "addHousholdMemberVisit(HouseholdMemberVisitDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modifyHouseholdMemberVisit ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modifyHousholdMemberVisit(HouseholdDTO) : Enter" );
		HouseholdMemberVisitDTO householdMemberVisitDTO = null;
		HouseholdMemberVisitSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof HouseholdMemberVisitDTO )
		{
			householdMemberVisitDTO = (HouseholdMemberVisitDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modifyHousholdMemberVisit(HouseholdMemberVisitDTO) : componentId = " + householdMemberVisitDTO.getComponentId () );
		
		try
		{
			successResult = householdDAO.modifyHouseholdMemberVisit ( householdMemberVisitDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modifyHousholdMemberVisit(HouseholdMemberVisitDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modifyHousholdMemberVisit(HouseholdMemberVisitDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult addFamilyPlanningInfo ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "addFamilyPlanningInfo(FamilyPlanningInfoDTO) : Enter" );
		FamilyPlanningInfoDTO familyPlanningInfoDTO = null;
		
		FamilyPlanningInfoSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof FamilyPlanningInfoDTO )
		{
			familyPlanningInfoDTO = (FamilyPlanningInfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(householdDTO);
		 * logger.info("addFamilyPlanningInfo(HouseholdDTO) : isExist = " +
		 * isExist); if(isExist) { throw new
		 * HouseholdAlreadyExistsException("already.exist.same.code"); }
		 */
		logger.info ( "addFamilyPlanningInfo(FamilyPlanningInfoDTO) : componentId = " + familyPlanningInfoDTO.getComponentId () );
		
		try
		{
			successResult = householdDAO.addFamilyPlanningInfo ( familyPlanningInfoDTO );
		}
		catch (Exception e)
		{
			logger.info ( "addFamilyPlanningInfo(FamilyPlanningInfoDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "addFamilyPlanningInfo(FamilyPlanningInfoDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modifyFamilyPlanningInfo ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modifyFamilyPlanningInfo(HouseholdDTO) : Enter" );
		FamilyPlanningInfoDTO familyPlanningInfoDTO = null;
		FamilyPlanningInfoSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof FamilyPlanningInfoDTO )
		{
			familyPlanningInfoDTO = (FamilyPlanningInfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modifyFamilyPlanningInfo(FamilyPlanningInfoDTO) : componentId = " + familyPlanningInfoDTO.getComponentId () );
		
		try
		{
			successResult = householdDAO.modifyFamilyPlanningInfo ( familyPlanningInfoDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modifyFamilyPlanningInfo(FamilyPlanningInfoDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modifyFamilyPlanningInfo(FamilyPlanningInfoDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult remove ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "remove(HouseholdDTO) : Enter" );
		HouseholdSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "DTO MUST NOT NULL." );
		}
		
		HouseholdDTO householdDTO = null;
		if ( genericDTO instanceof HouseholdDTO )
		{
			householdDTO = (HouseholdDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "INVALID DTO TYPE. MUST BE INSTANCE OF HouseholdDTO." );
		}
		
		logger.info ( "remove(HouseholdDTO) : code = " + householdDTO.getUniqueCode () );
		logger.info ( "remove(HouseholdDTO) : componentId = " + householdDTO.getComponentId () );
		
		try
		{
			successResult = householdDAO.remove ( householdDTO );
		}
		catch (Exception e)
		{
			logger.info ( "remove(HouseholdDTO) :", e );
			throw new SystemException ( e );
		}
		logger.info ( "remove(HouseholdDTO) : Exit" );
		return successResult;
	}
	
	public GenericDTO get ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		HouseholdDTO householdDTO = null;
		try
		{
			householdDTO = householdDAO.get ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return householdDTO;
	}
	
	public HouseholdMemberDTO getHouseholdMemberByMemberId ( String componentId )
			throws SystemException
	{
		logger.info ( "getHouseholdByMotherId(componentId) : Enter" );
		logger.info ( "getHouseholdByMotherId(componentId) : componentId = " + componentId );
		HouseholdMemberDTO householdMemberDTO = null;
		try
		{
			householdMemberDTO = (HouseholdMemberDTO) householdDAO.getHouseholdMemberByMemberId ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "getHouseholdByMotherId(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "getHouseholdByMotherId(componentId) : Exit" );
		return householdMemberDTO;
	}
	
	public GenericDTO get ( String householdId )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "get(code) : Enter" );
		HouseholdDTO householdDTO = null;
		try
		{
			householdDTO = householdDAO.get ( householdId );
			//if ( householdDTO == null )
			//{
				//throw new HouseholdNotFoundException ( "household.not.found" );
			//}
		}
		catch (Exception e)
		{
			logger.error ( "get(code)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(code) : Exit" );
		return householdDTO;
	}
	
	public List<HouseholdDTO> getList ( Long groupId, HouseholdSortBy sortby )
			throws SystemException
	{
		logger.info ( "getList(groupId,sortby) : Enter" );
		ArrayList<HouseholdDTO> result = null;
		try
		{
			result = householdDAO.getList ( groupId, sortby );
		}
		catch (Exception e)
		{
			logger.error ( "getList(groupId,sortby)", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList(groupId,sortby) : Exit" );
		return result;
	}
	
	public List<HouseholdDTO> getList ( ) throws SystemException
	{
		logger.info ( "getList() : Enter" );
		ArrayList<HouseholdDTO> result = null;
		try
		{
			result = householdDAO.getList ();
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
	
	public HouseholdDTO getHouseholeByHouseholdId ( String householdId )
			throws SystemException
	{
		logger.info ( "getHouseholdByHouseholdId() : Enter" );
		HouseholdDTO householdDTO = this.householdDAO.getHouseholdByHouseholdId ( householdId );
		
		logger.info ( "getHouseholdByHouseholdId() : Exit" );
		// return
		return householdDTO;
	}
	
	private boolean checkUniqueCodeDuplicacy ( HouseholdDTO householdDTO )
			throws SystemException
	{
		boolean isExist = false;
		try
		{
			householdDTO = householdDAO.get ( householdDTO.getUniqueCode () );
			if ( householdDTO != null )
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
