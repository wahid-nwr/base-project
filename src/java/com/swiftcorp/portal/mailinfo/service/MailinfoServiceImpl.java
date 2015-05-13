/*
 * @ (#) MailinfoServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.mailinfo.service;
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
import com.swiftcorp.portal.mailinfo.MailinfoSuccessResult;
import com.swiftcorp.portal.mailinfo.dao.IMailinfoDAO;
import com.swiftcorp.portal.mailinfo.dto.MailinfoDTO;
import com.swiftcorp.portal.mailinfo.exception.MailinfoCreationException;
import com.swiftcorp.portal.mailinfo.exception.MailinfoNotFoundException;
import com.swiftcorp.portal.mailinfo.exception.MailinfoAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.mailinfo.dao.IMailinfoDAO.MailinfoSortBy;
import com.swiftcorp.portal.mailinfo.service.IMailinfoService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class MailinfoServiceImpl implements IMailinfoService 
{
	private static final Log logger = LogFactory.getLog(MailinfoServiceImpl.class);
	
	private IMailinfoDAO mailinfoDAO;	
	private ISearcher searcher;
	public void setMailinfoDAO(IMailinfoDAO mailinfoDAO) 
	{
		this.mailinfoDAO = mailinfoDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(MailinfoDTO) : Enter");
		MailinfoDTO mailinfoDTO = null;
		
		MailinfoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof MailinfoDTO)
		{
			mailinfoDTO = (MailinfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(mailinfoDTO);
		logger.info("add(MailinfoDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new MailinfoAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(MailinfoDTO) : componentId = "+ mailinfoDTO.getComponentId());
		
		try
		{
			successResult = mailinfoDAO.add(mailinfoDTO);
		}
		catch(Exception e)
		{
			logger.info("add(MailinfoDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(MailinfoDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(MailinfoDTO) : Enter");
		MailinfoDTO mailinfoDTO = null;	
		MailinfoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof MailinfoDTO)
		{
			mailinfoDTO = (MailinfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(MailinfoDTO) : componentId = "+ mailinfoDTO.getComponentId());
		
		try
		{
			successResult = mailinfoDAO.modify(mailinfoDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(MailinfoDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(MailinfoDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(MailinfoDTO) : Enter");
		MailinfoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		MailinfoDTO mailinfoDTO = null;		
		if(genericDTO instanceof MailinfoDTO)
		{
			mailinfoDTO = (MailinfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF MailinfoDTO.");
		}
		
		logger.info("remove(MailinfoDTO) : code = "+ mailinfoDTO.getUniqueCode());
		logger.info("remove(MailinfoDTO) : componentId = "+ mailinfoDTO.getComponentId());
		
		try
		{
			successResult = mailinfoDAO.remove(mailinfoDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(MailinfoDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(MailinfoDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		MailinfoDTO mailinfoDTO = null;		
		try
		{
			mailinfoDTO = mailinfoDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return mailinfoDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		MailinfoDTO mailinfoDTO = null;		
		try
		{
			mailinfoDTO = mailinfoDAO.get(uniqueCode);
			if(mailinfoDTO == null)
			{
				throw new MailinfoNotFoundException("mailinfo.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return mailinfoDTO;
	}
	
	 public List<MailinfoDTO> getList(Long groupId, MailinfoSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<MailinfoDTO> result = null ;
			try
			{
				result = mailinfoDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<MailinfoDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<MailinfoDTO> result = null ;
			try
			{
				result = mailinfoDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(MailinfoDTO mailinfoDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			mailinfoDTO = mailinfoDAO.get(mailinfoDTO.getUniqueCode());
			if(mailinfoDTO != null)
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
