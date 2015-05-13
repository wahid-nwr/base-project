/*
 * @ (#) GeoServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.geo.service;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.geo.GeoSuccessResult;
import com.swiftcorp.portal.geo.dao.IGeoDAO;
import com.swiftcorp.portal.geo.dao.IGeoDAO.GeoSortBy;
import com.swiftcorp.portal.geo.dto.GeoDTO;
import com.swiftcorp.portal.geo.exception.GeoNotFoundException;
import com.swiftcorp.portal.role.dto.RoleDTO;
import com.swiftcorp.portal.role.service.IRoleService;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.user.service.IUserService;
/**
 * @author swift
 * @since mar 3, 2011
 */
public class GeoServiceImpl implements IGeoService 
{
	private static final Log logger = LogFactory.getLog(GeoServiceImpl.class);
	
	private IGeoDAO geoDAO;	
	private IUserService userService;
	private IRoleService roleService;
	private ISearcher searcher;
	
	public void setGeoDAO(IGeoDAO geoDAO) 
	{
		this.geoDAO = geoDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(GeoDTO) : Enter");
		GeoDTO geoDTO = null;
		
		GeoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof GeoDTO)
		{
			geoDTO = (GeoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(geoDTO);
		logger.info("add(GeoDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new GeoAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(GeoDTO) : componentId = "+ geoDTO.getComponentId());
		
		try
		{
			successResult = geoDAO.add(geoDTO);
		}
		catch(Exception e)
		{
			logger.info("add(GeoDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(GeoDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(GeoDTO) : Enter");
		GeoDTO geoDTO = null;	
		GeoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof GeoDTO)
		{
			geoDTO = (GeoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(GeoDTO) : componentId = "+ geoDTO.getComponentId());
		
		try
		{
			successResult = geoDAO.modify(geoDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(GeoDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(GeoDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(GeoDTO) : Enter");
		GeoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		GeoDTO geoDTO = null;		
		if(genericDTO instanceof GeoDTO)
		{
			geoDTO = (GeoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF GeoDTO.");
		}
		
		logger.info("remove(GeoDTO) : code = "+ geoDTO.getUniqueCode());
		logger.info("remove(GeoDTO) : componentId = "+ geoDTO.getComponentId());
		
		try
		{
			successResult = geoDAO.remove(geoDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(GeoDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(GeoDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		GeoDTO geoDTO = null;		
		try
		{
			geoDTO = geoDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return geoDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		GeoDTO geoDTO = null;		
		try
		{
			geoDTO = geoDAO.get(uniqueCode);
			if(geoDTO == null)
			{
				throw new GeoNotFoundException("geo.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return geoDTO;
	}
	
	 public List<GeoDTO> getList(Long groupId, GeoSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<GeoDTO> result = null ;
			try
			{
				result = geoDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<GeoDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<GeoDTO> result = null ;
			try
			{
				result = geoDAO.getList() ;
			}
			catch (Exception e) 
			{
				logger.error("getList()",e);
				throw new SystemException(e);
			}	
			logger.info("getList() : Exit");
			return result; 
	 }
	 
	
		public SearchOperationResult search(String query) throws SystemException, BusinessRuleViolationException 
		{
			logger.info("search() : Enter");
			SearchOperationResult searchResult = null;
			try
			{
				searchResult = searcher.search(query);
			}
			catch (InvalidSQLSyntaxException e)
			{
				logger.info("search() :",e);
				throw e ;
			}
			catch (SystemException e)
			{
				logger.info("search() :",e);
				throw e ;
			}
			logger.info("search() : Exit");
			return searchResult;
		}
	
	private boolean checkUniqueCodeDuplicacy(GeoDTO geoDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			geoDTO = geoDAO.get(geoDTO.getUniqueCode());
			if(geoDTO != null)
			{
				isExist =  true ;	
			}
		}
		catch (SystemException e)
		{
			throw e ;
		}
		return isExist ;		
	}
	
	
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	@Override
	public List<GeoDTO> getCCList ( ) throws SystemException
	{
		// method to get the City Corporation List
		logger.info("getCCList() : Enter");
		ArrayList<GeoDTO> result = null ;
		try
		{
			result = geoDAO.getGeoListByGeoType ( DTOConstants.GEO_TYPE_CITY_CORPORATION );
		}
		catch (Exception e) 
		{
			logger.error("getCCList()",e);
			throw new SystemException(e);
		}	
		logger.info("getList() : Exit");
		return result; 
		
	}
	@Override
	public List<GeoDTO> getBranchList ( ) throws SystemException
	{
		// method to get the Branch List
		logger.info("getBranchList() : Enter");
		ArrayList<GeoDTO> result = null ;
		try
		{
			result = geoDAO.getGeoListByGeoType ( DTOConstants.GEO_TYPE_BRANCH );
		}
		catch (Exception e) 
		{
			logger.error("getBranchList()",e);
			throw new SystemException(e);
		}	
		logger.info("getList() : Exit");
		return result; 
	}
	@Override
	public List<GeoDTO> getRegionList ( ) throws SystemException
	{
		// method to get the Region List
		logger.info("getRegionList() : Enter");
		ArrayList<GeoDTO> result = null ;
		try
		{
			result = geoDAO.getGeoListByGeoType ( DTOConstants.GEO_TYPE_REGION );
		}
		catch (Exception e) 
		{
			logger.error("getRegionList()",e);
			throw new SystemException(e);
		}	
		logger.info("getList() : Exit");
		return result; 
	}
	
}
