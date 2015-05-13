/*
 * @ (#) EmailServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.email.service;
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
import com.swiftcorp.portal.email.EmailSuccessResult;
import com.swiftcorp.portal.email.dao.IEmailDAO;
import com.swiftcorp.portal.email.dto.EmailDTO;
import com.swiftcorp.portal.email.exception.EmailCreationException;
import com.swiftcorp.portal.email.exception.EmailNotFoundException;
import com.swiftcorp.portal.email.exception.EmailAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.email.dao.IEmailDAO.EmailSortBy;
import com.swiftcorp.portal.email.service.IEmailService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailServiceImpl implements IEmailService 
{
	private static final Log logger = LogFactory.getLog(EmailServiceImpl.class);
	
	private IEmailDAO emailDAO;	
	private ISearcher searcher;
	public void setEmailDAO(IEmailDAO emailDAO) 
	{
		this.emailDAO = emailDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(EmailDTO) : Enter");
		EmailDTO emailDTO = null;
		
		EmailSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof EmailDTO)
		{
			emailDTO = (EmailDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(emailDTO);
		logger.info("add(EmailDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new EmailAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(EmailDTO) : componentId = "+ emailDTO.getComponentId());
		
		try
		{
			successResult = emailDAO.add(emailDTO);
		}
		catch(Exception e)
		{
			logger.info("add(EmailDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(EmailDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(EmailDTO) : Enter");
		EmailDTO emailDTO = null;	
		EmailSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof EmailDTO)
		{
			emailDTO = (EmailDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(EmailDTO) : componentId = "+ emailDTO.getComponentId());
		
		try
		{
			successResult = emailDAO.modify(emailDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(EmailDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(EmailDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(EmailDTO) : Enter");
		EmailSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		EmailDTO emailDTO = null;		
		if(genericDTO instanceof EmailDTO)
		{
			emailDTO = (EmailDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF EmailDTO.");
		}
		
		logger.info("remove(EmailDTO) : code = "+ emailDTO.getUniqueCode());
		logger.info("remove(EmailDTO) : componentId = "+ emailDTO.getComponentId());
		
		try
		{
			successResult = emailDAO.remove(emailDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(EmailDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(EmailDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		EmailDTO emailDTO = null;		
		try
		{
			emailDTO = emailDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return emailDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		EmailDTO emailDTO = null;		
		try
		{
			emailDTO = emailDAO.get(uniqueCode);
			if(emailDTO == null)
			{
				throw new EmailNotFoundException("email.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return emailDTO;
	}
	
	 public List<EmailDTO> getList(Long groupId, EmailSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<EmailDTO> result = null ;
			try
			{
				result = emailDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<EmailDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<EmailDTO> result = null ;
			try
			{
				result = emailDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(EmailDTO emailDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			emailDTO = emailDAO.get(emailDTO.getUniqueCode());
			if(emailDTO != null)
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
