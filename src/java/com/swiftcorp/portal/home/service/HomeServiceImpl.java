/*
 * @ (#) HomeServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.home.service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.common.util.ValidationUtil;
import com.swiftcorp.portal.home.HomeSuccessResult;
import com.swiftcorp.portal.home.dao.IHomeDAO;
import com.swiftcorp.portal.home.dto.HomeDTO;
import com.swiftcorp.portal.home.exception.HomeCreationException;
import com.swiftcorp.portal.home.exception.HomeNotFoundException;
import com.swiftcorp.portal.home.exception.HomeAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.home.dao.IHomeDAO.HomeSortBy;
import com.swiftcorp.portal.home.service.IHomeService;
/**
 * @author swift
 * @since mar 3, 2011
 */
public class HomeServiceImpl implements IHomeService 
{
	private static final Log logger = LogFactory.getLog(HomeServiceImpl.class);
	
	private IHomeDAO homeDAO;	
	private ISearcher searcher;
	public void setHomeDAO(IHomeDAO homeDAO) 
	{
		this.homeDAO = homeDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(HomeDTO) : Enter");
		HomeDTO homeDTO = null;
		
		HomeSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof HomeDTO)
		{
			homeDTO = (HomeDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(homeDTO);
		logger.info("add(HomeDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new HomeAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(HomeDTO) : componentId = "+ homeDTO.getComponentId());
		
		try
		{
			successResult = homeDAO.add(homeDTO);
		}
		catch(Exception e)
		{
			logger.info("add(HomeDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(HomeDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(HomeDTO) : Enter");
		HomeDTO homeDTO = null;	
		HomeSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof HomeDTO)
		{
			homeDTO = (HomeDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(HomeDTO) : componentId = "+ homeDTO.getComponentId());
		
		try
		{
			successResult = homeDAO.modify(homeDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(HomeDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(HomeDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(HomeDTO) : Enter");
		HomeSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		HomeDTO homeDTO = null;		
		if(genericDTO instanceof HomeDTO)
		{
			homeDTO = (HomeDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF HomeDTO.");
		}
		
		logger.info("remove(HomeDTO) : code = "+ homeDTO.getUniqueCode());
		logger.info("remove(HomeDTO) : componentId = "+ homeDTO.getComponentId());
		
		try
		{
			successResult = homeDAO.remove(homeDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(HomeDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(HomeDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		HomeDTO homeDTO = null;		
		try
		{
			homeDTO = homeDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return homeDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		HomeDTO homeDTO = null;		
		try
		{
			homeDTO = homeDAO.get(uniqueCode);
			if(homeDTO == null)
			{
				throw new HomeNotFoundException("home.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return homeDTO;
	}
	
	 public List<HomeDTO> getList(Long groupId, HomeSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<HomeDTO> result = null ;
			try
			{
				result = homeDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<HomeDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<HomeDTO> result = null ;
			try
			{
				result = homeDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(HomeDTO homeDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			homeDTO = homeDAO.get(homeDTO.getUniqueCode());
			if(homeDTO != null)
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
	
}
