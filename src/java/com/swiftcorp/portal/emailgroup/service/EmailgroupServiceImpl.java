/*
 * @ (#) EmailgroupServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.emailgroup.service;
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
import com.swiftcorp.portal.emailgroup.EmailgroupSuccessResult;
import com.swiftcorp.portal.emailgroup.dao.IEmailgroupDAO;
import com.swiftcorp.portal.emailgroup.dto.EmailgroupDTO;
import com.swiftcorp.portal.emailgroup.exception.EmailgroupCreationException;
import com.swiftcorp.portal.emailgroup.exception.EmailgroupNotFoundException;
import com.swiftcorp.portal.emailgroup.exception.EmailgroupAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.emailgroup.dao.IEmailgroupDAO.EmailgroupSortBy;
import com.swiftcorp.portal.emailgroup.service.IEmailgroupService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailgroupServiceImpl implements IEmailgroupService 
{
	private static final Log logger = LogFactory.getLog(EmailgroupServiceImpl.class);
	
	private IEmailgroupDAO emailgroupDAO;	
	private ISearcher searcher;
	public void setEmailgroupDAO(IEmailgroupDAO emailgroupDAO) 
	{
		this.emailgroupDAO = emailgroupDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(EmailgroupDTO) : Enter");
		EmailgroupDTO emailgroupDTO = null;
		
		EmailgroupSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof EmailgroupDTO)
		{
			emailgroupDTO = (EmailgroupDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(emailgroupDTO);
		logger.info("add(EmailgroupDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new EmailgroupAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(EmailgroupDTO) : componentId = "+ emailgroupDTO.getComponentId());
		
		try
		{
			successResult = emailgroupDAO.add(emailgroupDTO);
		}
		catch(Exception e)
		{
			logger.info("add(EmailgroupDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(EmailgroupDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(EmailgroupDTO) : Enter");
		EmailgroupDTO emailgroupDTO = null;	
		EmailgroupSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof EmailgroupDTO)
		{
			emailgroupDTO = (EmailgroupDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(EmailgroupDTO) : componentId = "+ emailgroupDTO.getComponentId());
		
		try
		{
			successResult = emailgroupDAO.modify(emailgroupDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(EmailgroupDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(EmailgroupDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(EmailgroupDTO) : Enter");
		EmailgroupSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		EmailgroupDTO emailgroupDTO = null;		
		if(genericDTO instanceof EmailgroupDTO)
		{
			emailgroupDTO = (EmailgroupDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF EmailgroupDTO.");
		}
		
		logger.info("remove(EmailgroupDTO) : code = "+ emailgroupDTO.getUniqueCode());
		logger.info("remove(EmailgroupDTO) : componentId = "+ emailgroupDTO.getComponentId());
		
		try
		{
			successResult = emailgroupDAO.remove(emailgroupDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(EmailgroupDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(EmailgroupDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		EmailgroupDTO emailgroupDTO = null;		
		try
		{
			emailgroupDTO = emailgroupDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return emailgroupDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		EmailgroupDTO emailgroupDTO = null;		
		try
		{
			emailgroupDTO = emailgroupDAO.get(uniqueCode);
			if(emailgroupDTO == null)
			{
				throw new EmailgroupNotFoundException("emailgroup.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return emailgroupDTO;
	}
	
	 public List<EmailgroupDTO> getList(Long groupId, EmailgroupSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<EmailgroupDTO> result = null ;
			try
			{
				result = emailgroupDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<EmailgroupDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<EmailgroupDTO> result = null ;
			try
			{
				result = emailgroupDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(EmailgroupDTO emailgroupDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			emailgroupDTO = emailgroupDAO.get(emailgroupDTO.getUniqueCode());
			if(emailgroupDTO != null)
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
