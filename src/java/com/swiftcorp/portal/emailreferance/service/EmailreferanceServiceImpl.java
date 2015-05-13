/*
 * @ (#) EmailreferanceServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.emailreferance.service;
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
import com.swiftcorp.portal.emailreferance.EmailreferanceSuccessResult;
import com.swiftcorp.portal.emailreferance.dao.IEmailreferanceDAO;
import com.swiftcorp.portal.emailreferance.dto.EmailreferanceDTO;
import com.swiftcorp.portal.emailreferance.exception.EmailreferanceCreationException;
import com.swiftcorp.portal.emailreferance.exception.EmailreferanceNotFoundException;
import com.swiftcorp.portal.emailreferance.exception.EmailreferanceAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.emailreferance.dao.IEmailreferanceDAO.EmailreferanceSortBy;
import com.swiftcorp.portal.emailreferance.service.IEmailreferanceService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailreferanceServiceImpl implements IEmailreferanceService 
{
	private static final Log logger = LogFactory.getLog(EmailreferanceServiceImpl.class);
	
	private IEmailreferanceDAO emailreferanceDAO;	
	private ISearcher searcher;
	public void setEmailreferanceDAO(IEmailreferanceDAO emailreferanceDAO) 
	{
		this.emailreferanceDAO = emailreferanceDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(EmailreferanceDTO) : Enter");
		EmailreferanceDTO emailreferanceDTO = null;
		
		EmailreferanceSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof EmailreferanceDTO)
		{
			emailreferanceDTO = (EmailreferanceDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(emailreferanceDTO);
		logger.info("add(EmailreferanceDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new EmailreferanceAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(EmailreferanceDTO) : componentId = "+ emailreferanceDTO.getComponentId());
		
		try
		{
			successResult = emailreferanceDAO.add(emailreferanceDTO);
		}
		catch(Exception e)
		{
			logger.info("add(EmailreferanceDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(EmailreferanceDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(EmailreferanceDTO) : Enter");
		EmailreferanceDTO emailreferanceDTO = null;	
		EmailreferanceSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof EmailreferanceDTO)
		{
			emailreferanceDTO = (EmailreferanceDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(EmailreferanceDTO) : componentId = "+ emailreferanceDTO.getComponentId());
		
		try
		{
			successResult = emailreferanceDAO.modify(emailreferanceDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(EmailreferanceDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(EmailreferanceDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(EmailreferanceDTO) : Enter");
		EmailreferanceSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		EmailreferanceDTO emailreferanceDTO = null;		
		if(genericDTO instanceof EmailreferanceDTO)
		{
			emailreferanceDTO = (EmailreferanceDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF EmailreferanceDTO.");
		}
		
		logger.info("remove(EmailreferanceDTO) : code = "+ emailreferanceDTO.getUniqueCode());
		logger.info("remove(EmailreferanceDTO) : componentId = "+ emailreferanceDTO.getComponentId());
		
		try
		{
			successResult = emailreferanceDAO.remove(emailreferanceDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(EmailreferanceDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(EmailreferanceDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		EmailreferanceDTO emailreferanceDTO = null;		
		try
		{
			emailreferanceDTO = emailreferanceDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return emailreferanceDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		EmailreferanceDTO emailreferanceDTO = null;		
		try
		{
			emailreferanceDTO = emailreferanceDAO.get(uniqueCode);
			if(emailreferanceDTO == null)
			{
				throw new EmailreferanceNotFoundException("emailreferance.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return emailreferanceDTO;
	}
	
	 public List<EmailreferanceDTO> getList(Long groupId, EmailreferanceSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<EmailreferanceDTO> result = null ;
			try
			{
				result = emailreferanceDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<EmailreferanceDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<EmailreferanceDTO> result = null ;
			try
			{
				result = emailreferanceDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(EmailreferanceDTO emailreferanceDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			emailreferanceDTO = emailreferanceDAO.get(emailreferanceDTO.getUniqueCode());
			if(emailreferanceDTO != null)
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
