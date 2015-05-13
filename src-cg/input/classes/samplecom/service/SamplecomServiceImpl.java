/*
 * @ (#) SamplecomServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */

package com.swiftcorp.portal.samplecom.service;

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
import com.swiftcorp.portal.samplecom.SamplecomSuccessResult;
import com.swiftcorp.portal.samplecom.dao.ISamplecomDAO;
import com.swiftcorp.portal.samplecom.dto.SamplecomDTO;
import com.swiftcorp.portal.samplecom.exception.SamplecomCreationException;
import com.swiftcorp.portal.samplecom.exception.SamplecomNotFoundException;
import com.swiftcorp.portal.samplecom.exception.SamplecomAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;

import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.samplecom.dao.ISamplecomDAO.SamplecomSortBy;
import com.swiftcorp.portal.samplecom.service.ISamplecomService;


/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class SamplecomServiceImpl implements ISamplecomService 
{
	private static final Log logger = LogFactory.getLog(SamplecomServiceImpl.class);
	
	private ISamplecomDAO samplecomDAO;	
	private ISearcher searcher;

	public void setSamplecomDAO(ISamplecomDAO samplecomDAO) 
	{
		this.samplecomDAO = samplecomDAO;
	}

	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}


	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(SamplecomDTO) : Enter");
		SamplecomDTO samplecomDTO = null;
		
		SamplecomSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof SamplecomDTO)
		{
			samplecomDTO = (SamplecomDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(samplecomDTO);
		logger.info("add(SamplecomDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new SamplecomAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(SamplecomDTO) : componentId = "+ samplecomDTO.getComponentId());

		
		try
		{
			successResult = samplecomDAO.add(samplecomDTO);
		}
		catch(Exception e)
		{
			logger.info("add(SamplecomDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(SamplecomDTO) : Exit");
		return successResult;
	}
	
	

	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(SamplecomDTO) : Enter");
		SamplecomDTO samplecomDTO = null;	

		SamplecomSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof SamplecomDTO)
		{
			samplecomDTO = (SamplecomDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(SamplecomDTO) : componentId = "+ samplecomDTO.getComponentId());
		
		try
		{
			successResult = samplecomDAO.modify(samplecomDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(SamplecomDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(SamplecomDTO) : Exit");
		return successResult;
	}
	
	

	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(SamplecomDTO) : Enter");
		SamplecomSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		SamplecomDTO samplecomDTO = null;		
		if(genericDTO instanceof SamplecomDTO)
		{
			samplecomDTO = (SamplecomDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF SamplecomDTO.");
		}
		
		logger.info("remove(SamplecomDTO) : code = "+ samplecomDTO.getUniqueCode());
		logger.info("remove(SamplecomDTO) : componentId = "+ samplecomDTO.getComponentId());
		
		try
		{
			successResult = samplecomDAO.remove(samplecomDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(SamplecomDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(SamplecomDTO) : Exit");
		return successResult;
	}
	
	
	

	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		SamplecomDTO samplecomDTO = null;		
		try
		{
			samplecomDTO = samplecomDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return samplecomDTO;
	}
	
	

	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		SamplecomDTO samplecomDTO = null;		
		try
		{
			samplecomDTO = samplecomDAO.get(uniqueCode);
			if(samplecomDTO == null)
			{
				throw new SamplecomNotFoundException("samplecom.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return samplecomDTO;
	}

	
	 public List<SamplecomDTO> getList(Long groupId, SamplecomSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<SamplecomDTO> result = null ;
			try
			{
				result = samplecomDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<SamplecomDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<SamplecomDTO> result = null ;
			try
			{
				result = samplecomDAO.getList() ;
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

	
	private boolean checkUniqueCodeDuplicacy(SamplecomDTO samplecomDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			samplecomDTO = samplecomDAO.get(samplecomDTO.getUniqueCode());
			if(samplecomDTO != null)
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
