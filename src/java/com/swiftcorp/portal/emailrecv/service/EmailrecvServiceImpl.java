/*
 * @ (#) EmailrecvServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.emailrecv.service;
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
import com.swiftcorp.portal.emailrecv.EmailrecvSuccessResult;
import com.swiftcorp.portal.emailrecv.dao.IEmailrecvDAO;
import com.swiftcorp.portal.emailrecv.dto.EmailrecvDTO;
import com.swiftcorp.portal.emailrecv.exception.EmailrecvCreationException;
import com.swiftcorp.portal.emailrecv.exception.EmailrecvNotFoundException;
import com.swiftcorp.portal.emailrecv.exception.EmailrecvAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.emailrecv.dao.IEmailrecvDAO.EmailrecvSortBy;
import com.swiftcorp.portal.emailrecv.service.IEmailrecvService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailrecvServiceImpl implements IEmailrecvService 
{
	private static final Log logger = LogFactory.getLog(EmailrecvServiceImpl.class);
	
	private IEmailrecvDAO emailrecvDAO;	
	private ISearcher searcher;
	public void setEmailrecvDAO(IEmailrecvDAO emailrecvDAO) 
	{
		this.emailrecvDAO = emailrecvDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(EmailrecvDTO) : Enter");
		EmailrecvDTO emailrecvDTO = null;
		
		EmailrecvSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof EmailrecvDTO)
		{
			emailrecvDTO = (EmailrecvDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(emailrecvDTO);
		logger.info("add(EmailrecvDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new EmailrecvAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(EmailrecvDTO) : componentId = "+ emailrecvDTO.getComponentId());
		
		try
		{
			successResult = emailrecvDAO.add(emailrecvDTO);
		}
		catch(Exception e)
		{
			logger.info("add(EmailrecvDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(EmailrecvDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(EmailrecvDTO) : Enter");
		EmailrecvDTO emailrecvDTO = null;	
		EmailrecvSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof EmailrecvDTO)
		{
			emailrecvDTO = (EmailrecvDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(EmailrecvDTO) : componentId = "+ emailrecvDTO.getComponentId());
		
		try
		{
			successResult = emailrecvDAO.modify(emailrecvDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(EmailrecvDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(EmailrecvDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(EmailrecvDTO) : Enter");
		EmailrecvSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		EmailrecvDTO emailrecvDTO = null;		
		if(genericDTO instanceof EmailrecvDTO)
		{
			emailrecvDTO = (EmailrecvDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF EmailrecvDTO.");
		}
		
		logger.info("remove(EmailrecvDTO) : code = "+ emailrecvDTO.getUniqueCode());
		logger.info("remove(EmailrecvDTO) : componentId = "+ emailrecvDTO.getComponentId());
		
		try
		{
			successResult = emailrecvDAO.remove(emailrecvDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(EmailrecvDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(EmailrecvDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		EmailrecvDTO emailrecvDTO = null;		
		try
		{
			emailrecvDTO = emailrecvDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return emailrecvDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		EmailrecvDTO emailrecvDTO = null;		
		try
		{
			emailrecvDTO = emailrecvDAO.get(uniqueCode);
			if(emailrecvDTO == null)
			{
				throw new EmailrecvNotFoundException("emailrecv.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return emailrecvDTO;
	}
	
	 public List<EmailrecvDTO> getList(Long groupId, EmailrecvSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<EmailrecvDTO> result = null ;
			try
			{
				result = emailrecvDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<EmailrecvDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<EmailrecvDTO> result = null ;
			try
			{
				result = emailrecvDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(EmailrecvDTO emailrecvDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			emailrecvDTO = emailrecvDAO.get(emailrecvDTO.getUniqueCode());
			if(emailrecvDTO != null)
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
