/*
 * @ (#) EmaildtlServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.emaildtl.service;
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
import com.swiftcorp.portal.emaildtl.EmaildtlSuccessResult;
import com.swiftcorp.portal.emaildtl.dao.IEmaildtlDAO;
import com.swiftcorp.portal.emaildtl.dto.EmaildtlDTO;
import com.swiftcorp.portal.emaildtl.exception.EmaildtlCreationException;
import com.swiftcorp.portal.emaildtl.exception.EmaildtlNotFoundException;
import com.swiftcorp.portal.emaildtl.exception.EmaildtlAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.emaildtl.dao.IEmaildtlDAO.EmaildtlSortBy;
import com.swiftcorp.portal.emaildtl.service.IEmaildtlService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmaildtlServiceImpl implements IEmaildtlService 
{
	private static final Log logger = LogFactory.getLog(EmaildtlServiceImpl.class);
	
	private IEmaildtlDAO emaildtlDAO;	
	private ISearcher searcher;
	public void setEmaildtlDAO(IEmaildtlDAO emaildtlDAO) 
	{
		this.emaildtlDAO = emaildtlDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(EmaildtlDTO) : Enter");
		EmaildtlDTO emaildtlDTO = null;
		
		EmaildtlSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof EmaildtlDTO)
		{
			emaildtlDTO = (EmaildtlDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(emaildtlDTO);
		logger.info("add(EmaildtlDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new EmaildtlAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(EmaildtlDTO) : componentId = "+ emaildtlDTO.getComponentId());
		
		try
		{
			successResult = emaildtlDAO.add(emaildtlDTO);
		}
		catch(Exception e)
		{
			logger.info("add(EmaildtlDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(EmaildtlDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(EmaildtlDTO) : Enter");
		EmaildtlDTO emaildtlDTO = null;	
		EmaildtlSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof EmaildtlDTO)
		{
			emaildtlDTO = (EmaildtlDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(EmaildtlDTO) : componentId = "+ emaildtlDTO.getComponentId());
		
		try
		{
			successResult = emaildtlDAO.modify(emaildtlDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(EmaildtlDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(EmaildtlDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(EmaildtlDTO) : Enter");
		EmaildtlSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		EmaildtlDTO emaildtlDTO = null;		
		if(genericDTO instanceof EmaildtlDTO)
		{
			emaildtlDTO = (EmaildtlDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF EmaildtlDTO.");
		}
		
		logger.info("remove(EmaildtlDTO) : code = "+ emaildtlDTO.getUniqueCode());
		logger.info("remove(EmaildtlDTO) : componentId = "+ emaildtlDTO.getComponentId());
		
		try
		{
			successResult = emaildtlDAO.remove(emaildtlDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(EmaildtlDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(EmaildtlDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		EmaildtlDTO emaildtlDTO = null;		
		try
		{
			emaildtlDTO = emaildtlDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return emaildtlDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		EmaildtlDTO emaildtlDTO = null;		
		try
		{
			emaildtlDTO = emaildtlDAO.get(uniqueCode);
			if(emaildtlDTO == null)
			{
				throw new EmaildtlNotFoundException("emaildtl.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return emaildtlDTO;
	}
	
	 public List<EmaildtlDTO> getList(Long groupId, EmaildtlSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<EmaildtlDTO> result = null ;
			try
			{
				result = emaildtlDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<EmaildtlDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<EmaildtlDTO> result = null ;
			try
			{
				result = emaildtlDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(EmaildtlDTO emaildtlDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			emaildtlDTO = emaildtlDAO.get(emaildtlDTO.getUniqueCode());
			if(emaildtlDTO != null)
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
