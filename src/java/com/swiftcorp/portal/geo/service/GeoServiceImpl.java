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
import com.swiftcorp.portal.geo.GeoImportSuccess;
import com.swiftcorp.portal.geo.GeoSuccessResult;
import com.swiftcorp.portal.geo.dao.IGeoDAO;
import com.swiftcorp.portal.geo.dao.IGeoDAO.GeoSortBy;
import com.swiftcorp.portal.geo.dto.GeoDTO;
import com.swiftcorp.portal.geo.dto.GeoImportHHRegDTO;
import com.swiftcorp.portal.geo.exception.GeoNotFoundException;
import com.swiftcorp.portal.role.dto.RoleDTO;
import com.swiftcorp.portal.role.service.IRoleService;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.user.service.IUserService;
/**
 * @author mosa
 * @since Sep 8, 2008
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
	@Override
	public GeoImportSuccess importData(List<GeoImportHHRegDTO> geoImportHHRegDTOList)
	throws SystemException 
	{
		Enumeration en = null;
		for(GeoImportHHRegDTO geoImportHHRegDTO:geoImportHHRegDTOList)
		{		
			String ssName = "";	
			String skName = "";
			GeoDTO branchDTO = this.saveGeoInfo ( geoImportHHRegDTO );
			UserDTO skUserDTO = null;
			UserDTO ssUserDTO;
			System.out.println("in geo import");
			
			/*List<SkDataDTO> skDataDTOList = geoImportHHRegDTO.getSkDataDTOList ();
			for(SkDataDTO skDataDTO : skDataDTOList)
			{				
				RoleDTO role = new RoleDTO();
				try {
					role = (RoleDTO)roleService.get("SK");
				} catch (BusinessRuleViolationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				skUserDTO = new UserDTO();
				skUserDTO.setFirstName(skDataDTO.getSkName ());
				skUserDTO.setLastName("");
				skUserDTO.setUniqueCode(skDataDTO.getSkId ());
				skUserDTO.setPassword(skDataDTO.getSkId ());
				skUserDTO.setConfirmPassword(skDataDTO.getSkId ());
				skUserDTO.setAreaType(DTOConstants.GEO_TYPE_BRANCH);
				skUserDTO.setUserArea(branchDTO);
				skUserDTO.setRole(role);
				System.out.println("uniquecode:::"+skUserDTO.getUniqueCode());
				
				try {
					userService.add(skUserDTO);
				} catch (BusinessRuleViolationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<SSDTO> ssIdList = skDataDTO.getSsDTOList ();
				for(int i=0;ssIdList!=null && i<ssIdList.size ();i++)
				{
					SSDTO ssDTO = ssIdList.get ( i );
					ssDTO.setBranch ( branchDTO );
					try {
						userService.addSS(ssDTO);
					} catch (BusinessRuleViolationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}				
			}*/
		}
		return null;
	}
	public GeoDTO saveGeoInfo(GeoImportHHRegDTO geoImportHHRegDTO)
	{
		GeoDTO cityCorpDTO = null;
		GeoDTO regionDTO = null;
		GeoDTO branchDTO = null;
		System.out.println (" city::"+geoImportHHRegDTO.getCityCorpId ()+" "+geoImportHHRegDTO.getCityCorpName ());
		System.out.println (" region::"+geoImportHHRegDTO.getRegionId ()+" "+geoImportHHRegDTO.getRegionName ());
		System.out.println (" branch::"+geoImportHHRegDTO.getBranchId ()+" "+geoImportHHRegDTO.getBranchName ());		
		try
		{
			cityCorpDTO = geoDAO.get ( geoImportHHRegDTO.getCityCorpId () );
			if(cityCorpDTO == null)
			{
				cityCorpDTO = new GeoDTO ();
				cityCorpDTO.setCode ( geoImportHHRegDTO.getCityCorpId () );
				cityCorpDTO.setName ( geoImportHHRegDTO.getCityCorpName () );
				cityCorpDTO.setGeoType ( DTOConstants.GEO_TYPE_CITY_CORPORATION );
				System.out.println ("in city add 1");
				geoDAO.add(cityCorpDTO);
				System.out.println ("in city add 2");
			}
			regionDTO = geoDAO.get ( geoImportHHRegDTO.getRegionId () );
			if(regionDTO == null)
			{
				regionDTO = new GeoDTO ();
				regionDTO.setCode ( geoImportHHRegDTO.getRegionId () );
				regionDTO.setName ( geoImportHHRegDTO.getRegionName () );
				regionDTO.setParentArea ( cityCorpDTO );
				regionDTO.setGeoType ( DTOConstants.GEO_TYPE_REGION );
				System.out.println ("in re add 1");
				geoDAO.add(regionDTO);
				System.out.println ("in re add 2");
			}
			branchDTO = geoDAO.get ( geoImportHHRegDTO.getBranchId () );
			if(branchDTO == null)
			{
				branchDTO = new GeoDTO ();
				branchDTO.setCode ( geoImportHHRegDTO.getBranchId () );
				branchDTO.setName ( geoImportHHRegDTO.getBranchName () );
				branchDTO.setParentArea ( regionDTO );
				branchDTO.setGeoType ( DTOConstants.GEO_TYPE_BRANCH );
				System.out.println ("in br add 1");
				geoDAO.add(branchDTO);
				System.out.println ("in br add 2");
			}			
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		return branchDTO;
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
