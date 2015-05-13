/*
 * @ (#) InfoServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.info.service;
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
import com.swiftcorp.portal.info.InfoSuccessResult;
import com.swiftcorp.portal.info.dao.IInfoDAO;
import com.swiftcorp.portal.info.dto.InfoDTO;
import com.swiftcorp.portal.info.exception.InfoCreationException;
import com.swiftcorp.portal.info.exception.InfoNotFoundException;
import com.swiftcorp.portal.info.exception.InfoAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.info.dao.IInfoDAO.InfoSortBy;
import com.swiftcorp.portal.info.service.IInfoService;
/**
 * @author swift
 * @since mar 3, 2011
 */
public class InfoServiceImpl implements IInfoService 
{
	private static final Log logger = LogFactory.getLog(InfoServiceImpl.class);
	
	private IInfoDAO infoDAO;	
	private ISearcher searcher;
	public void setInfoDAO(IInfoDAO infoDAO) 
	{
		this.infoDAO = infoDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(InfoDTO) : Enter");
		InfoDTO infoDTO = null;
		
		InfoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof InfoDTO)
		{
			infoDTO = (InfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(infoDTO);
		logger.info("add(InfoDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new InfoAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(InfoDTO) : componentId = "+ infoDTO.getComponentId());
		
		try
		{
			successResult = infoDAO.add(infoDTO);
		}
		catch(Exception e)
		{
			logger.info("add(InfoDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(InfoDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(InfoDTO) : Enter");
		InfoDTO infoDTO = null;	
		InfoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof InfoDTO)
		{
			infoDTO = (InfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(InfoDTO) : componentId = "+ infoDTO.getComponentId());
		
		try
		{
			successResult = infoDAO.modify(infoDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(InfoDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(InfoDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(InfoDTO) : Enter");
		InfoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		InfoDTO infoDTO = null;		
		if(genericDTO instanceof InfoDTO)
		{
			infoDTO = (InfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF InfoDTO.");
		}
		
		logger.info("remove(InfoDTO) : code = "+ infoDTO.getUniqueCode());
		logger.info("remove(InfoDTO) : componentId = "+ infoDTO.getComponentId());
		
		try
		{
			successResult = infoDAO.remove(infoDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(InfoDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(InfoDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		InfoDTO infoDTO = null;		
		try
		{
			infoDTO = infoDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return infoDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		InfoDTO infoDTO = null;		
		try
		{
			infoDTO = infoDAO.get(uniqueCode);
			if(infoDTO == null)
			{
				throw new InfoNotFoundException("info.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return infoDTO;
	}
	
	 public List<InfoDTO> getList(Long groupId, InfoSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<InfoDTO> result = null ;
			try
			{
				result = infoDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<InfoDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<InfoDTO> result = null ;
			try
			{
				result = infoDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(InfoDTO infoDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			infoDTO = infoDAO.get(infoDTO.getUniqueCode());
			if(infoDTO != null)
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
