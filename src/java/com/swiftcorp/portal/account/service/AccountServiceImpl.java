/*
 * @ (#) AccountServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.account.service;
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
import com.swiftcorp.portal.account.AccountSuccessResult;
import com.swiftcorp.portal.account.dao.IAccountDAO;
import com.swiftcorp.portal.account.dao.IAccountDAO.AccountSortBy;
import com.swiftcorp.portal.account.dto.AccountDTO;
import com.swiftcorp.portal.account.exception.AccountAlreadyExistsException;
import com.swiftcorp.portal.account.exception.AccountCreationException;
import com.swiftcorp.portal.account.exception.AccountNotFoundException;
import com.swiftcorp.portal.account.service.IAccountService;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.common.util.ValidationUtil;
/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class AccountServiceImpl implements IAccountService 
{
	private static final Log logger = LogFactory.getLog(AccountServiceImpl.class);
	
	private IAccountDAO accountDAO;	
	private ISearcher searcher;
	public void setAccountDAO(IAccountDAO accountDAO) 
	{
		this.accountDAO = accountDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(AccountDTO) : Enter");
		AccountDTO accountDTO = null;
		
		AccountSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof AccountDTO)
		{
			accountDTO = (AccountDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(accountDTO);
		logger.info("add(AccountDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new AccountAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(AccountDTO) : componentId = "+ accountDTO.getComponentId());
		
		try
		{
			successResult = accountDAO.add(accountDTO);
		}
		catch(Exception e)
		{
			logger.info("add(AccountDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(AccountDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(AccountDTO) : Enter");
		AccountDTO accountDTO = null;	
		AccountSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof AccountDTO)
		{
			accountDTO = (AccountDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(AccountDTO) : componentId = "+ accountDTO.getComponentId());
		
		try
		{
			successResult = accountDAO.modify(accountDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(AccountDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(AccountDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(AccountDTO) : Enter");
		AccountSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		AccountDTO accountDTO = null;		
		if(genericDTO instanceof AccountDTO)
		{
			accountDTO = (AccountDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF AccountDTO.");
		}
		
		logger.info("remove(AccountDTO) : code = "+ accountDTO.getUniqueCode());
		logger.info("remove(AccountDTO) : componentId = "+ accountDTO.getComponentId());
		
		try
		{
			successResult = accountDAO.remove(accountDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(AccountDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(AccountDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		AccountDTO accountDTO = null;		
		try
		{
			accountDTO = accountDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return accountDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		AccountDTO accountDTO = null;		
		try
		{
			accountDTO = accountDAO.get(uniqueCode);
			if(accountDTO == null)
			{
				throw new AccountNotFoundException("account.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return accountDTO;
	}
	
	 public List<AccountDTO> getList(Long groupId, AccountSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<AccountDTO> result = null ;
			try
			{
				result = accountDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<AccountDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<AccountDTO> result = null ;
			try
			{
				result = accountDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(AccountDTO accountDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			accountDTO = accountDAO.get(accountDTO.getUniqueCode());
			if(accountDTO != null)
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
