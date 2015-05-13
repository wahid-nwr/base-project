/*
 * @ (#) EmailrecipientsServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.emailrecipients.service;
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
import com.swiftcorp.portal.emailrecipients.EmailrecipientsSuccessResult;
import com.swiftcorp.portal.emailrecipients.dao.IEmailrecipientsDAO;
import com.swiftcorp.portal.emailrecipients.dto.EmailrecipientsDTO;
import com.swiftcorp.portal.emailrecipients.exception.EmailrecipientsCreationException;
import com.swiftcorp.portal.emailrecipients.exception.EmailrecipientsNotFoundException;
import com.swiftcorp.portal.emailrecipients.exception.EmailrecipientsAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.emailrecipients.dao.IEmailrecipientsDAO.EmailrecipientsSortBy;
import com.swiftcorp.portal.emailrecipients.service.IEmailrecipientsService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailrecipientsServiceImpl implements IEmailrecipientsService 
{
	private static final Log logger = LogFactory.getLog(EmailrecipientsServiceImpl.class);
	
	private IEmailrecipientsDAO emailrecipientsDAO;	
	private ISearcher searcher;
	public void setEmailrecipientsDAO(IEmailrecipientsDAO emailrecipientsDAO) 
	{
		this.emailrecipientsDAO = emailrecipientsDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(EmailrecipientsDTO) : Enter");
		EmailrecipientsDTO emailrecipientsDTO = null;
		
		EmailrecipientsSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof EmailrecipientsDTO)
		{
			emailrecipientsDTO = (EmailrecipientsDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(emailrecipientsDTO);
		logger.info("add(EmailrecipientsDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new EmailrecipientsAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(EmailrecipientsDTO) : componentId = "+ emailrecipientsDTO.getComponentId());
		
		try
		{
			successResult = emailrecipientsDAO.add(emailrecipientsDTO);
		}
		catch(Exception e)
		{
			logger.info("add(EmailrecipientsDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(EmailrecipientsDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(EmailrecipientsDTO) : Enter");
		EmailrecipientsDTO emailrecipientsDTO = null;	
		EmailrecipientsSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof EmailrecipientsDTO)
		{
			emailrecipientsDTO = (EmailrecipientsDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(EmailrecipientsDTO) : componentId = "+ emailrecipientsDTO.getComponentId());
		
		try
		{
			successResult = emailrecipientsDAO.modify(emailrecipientsDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(EmailrecipientsDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(EmailrecipientsDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(EmailrecipientsDTO) : Enter");
		EmailrecipientsSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		EmailrecipientsDTO emailrecipientsDTO = null;		
		if(genericDTO instanceof EmailrecipientsDTO)
		{
			emailrecipientsDTO = (EmailrecipientsDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF EmailrecipientsDTO.");
		}
		
		logger.info("remove(EmailrecipientsDTO) : code = "+ emailrecipientsDTO.getUniqueCode());
		logger.info("remove(EmailrecipientsDTO) : componentId = "+ emailrecipientsDTO.getComponentId());
		
		try
		{
			successResult = emailrecipientsDAO.remove(emailrecipientsDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(EmailrecipientsDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(EmailrecipientsDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		EmailrecipientsDTO emailrecipientsDTO = null;		
		try
		{
			emailrecipientsDTO = emailrecipientsDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return emailrecipientsDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		EmailrecipientsDTO emailrecipientsDTO = null;		
		try
		{
			emailrecipientsDTO = emailrecipientsDAO.get(uniqueCode);
			if(emailrecipientsDTO == null)
			{
				throw new EmailrecipientsNotFoundException("emailrecipients.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return emailrecipientsDTO;
	}
	
	 public List<EmailrecipientsDTO> getList(Long groupId, EmailrecipientsSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<EmailrecipientsDTO> result = null ;
			try
			{
				result = emailrecipientsDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<EmailrecipientsDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<EmailrecipientsDTO> result = null ;
			try
			{
				result = emailrecipientsDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(EmailrecipientsDTO emailrecipientsDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			emailrecipientsDTO = emailrecipientsDAO.get(emailrecipientsDTO.getUniqueCode());
			if(emailrecipientsDTO != null)
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
