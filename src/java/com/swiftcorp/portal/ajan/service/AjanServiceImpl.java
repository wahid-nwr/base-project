/*
 * @ (#) AjanServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.ajan.service;
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
import com.swiftcorp.portal.ajan.AjanSuccessResult;
import com.swiftcorp.portal.ajan.dao.IAjanDAO;
import com.swiftcorp.portal.ajan.dto.AjanDTO;
import com.swiftcorp.portal.ajan.exception.AjanCreationException;
import com.swiftcorp.portal.ajan.exception.AjanNotFoundException;
import com.swiftcorp.portal.ajan.exception.AjanAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.ajan.dao.IAjanDAO.AjanSortBy;
import com.swiftcorp.portal.ajan.service.IAjanService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class AjanServiceImpl implements IAjanService 
{
	private static final Log logger = LogFactory.getLog(AjanServiceImpl.class);
	
	private IAjanDAO ajanDAO;	
	private ISearcher searcher;
	public void setAjanDAO(IAjanDAO ajanDAO) 
	{
		this.ajanDAO = ajanDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(AjanDTO) : Enter");
		AjanDTO ajanDTO = null;
		
		AjanSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof AjanDTO)
		{
			ajanDTO = (AjanDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(ajanDTO);
		logger.info("add(AjanDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new AjanAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(AjanDTO) : componentId = "+ ajanDTO.getComponentId());
		
		try
		{
			successResult = ajanDAO.add(ajanDTO);
		}
		catch(Exception e)
		{
			logger.info("add(AjanDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(AjanDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(AjanDTO) : Enter");
		AjanDTO ajanDTO = null;	
		AjanSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof AjanDTO)
		{
			ajanDTO = (AjanDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(AjanDTO) : componentId = "+ ajanDTO.getComponentId());
		
		try
		{
			successResult = ajanDAO.modify(ajanDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(AjanDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(AjanDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(AjanDTO) : Enter");
		AjanSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		AjanDTO ajanDTO = null;		
		if(genericDTO instanceof AjanDTO)
		{
			ajanDTO = (AjanDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF AjanDTO.");
		}
		
		logger.info("remove(AjanDTO) : code = "+ ajanDTO.getUniqueCode());
		logger.info("remove(AjanDTO) : componentId = "+ ajanDTO.getComponentId());
		
		try
		{
			successResult = ajanDAO.remove(ajanDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(AjanDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(AjanDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		AjanDTO ajanDTO = null;		
		try
		{
			ajanDTO = ajanDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return ajanDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		AjanDTO ajanDTO = null;		
		try
		{
			ajanDTO = ajanDAO.get(uniqueCode);
			if(ajanDTO == null)
			{
				throw new AjanNotFoundException("ajan.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return ajanDTO;
	}
	
	 public List<AjanDTO> getList(Long groupId, AjanSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<AjanDTO> result = null ;
			try
			{
				result = ajanDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<AjanDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<AjanDTO> result = null ;
			try
			{
				result = ajanDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(AjanDTO ajanDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			ajanDTO = ajanDAO.get(ajanDTO.getUniqueCode());
			if(ajanDTO != null)
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
