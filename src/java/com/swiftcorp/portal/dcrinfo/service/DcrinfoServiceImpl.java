/*
 * @ (#) DcrinfoServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.dcrinfo.service;
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
import com.swiftcorp.portal.dcrinfo.DcrinfoSuccessResult;
import com.swiftcorp.portal.dcrinfo.dao.IDcrinfoDAO;
import com.swiftcorp.portal.dcrinfo.dao.IDcrinfoDAO.DcrinfoSortBy;
import com.swiftcorp.portal.dcrinfo.dto.DcrinfoDTO;
import com.swiftcorp.portal.dcrinfo.exception.DcrinfoAlreadyExistsException;
import com.swiftcorp.portal.dcrinfo.exception.DcrinfoCreationException;
import com.swiftcorp.portal.dcrinfo.exception.DcrinfoNotFoundException;
import com.swiftcorp.portal.dcrinfo.service.IDcrinfoService;
/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class DcrinfoServiceImpl implements IDcrinfoService 
{
	private static final Log logger = LogFactory.getLog(DcrinfoServiceImpl.class);
	
	private IDcrinfoDAO dcrinfoDAO;	
	private ISearcher searcher;
	public void setDcrinfoDAO(IDcrinfoDAO dcrinfoDAO) 
	{
		this.dcrinfoDAO = dcrinfoDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(DcrinfoDTO) : Enter");
		DcrinfoDTO dcrinfoDTO = null;
		
		DcrinfoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof DcrinfoDTO)
		{
			dcrinfoDTO = (DcrinfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(dcrinfoDTO);
		logger.info("add(DcrinfoDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new DcrinfoAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(DcrinfoDTO) : componentId = "+ dcrinfoDTO.getComponentId());
		
		try
		{
			successResult = dcrinfoDAO.add(dcrinfoDTO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.info("add(DcrinfoDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(DcrinfoDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(DcrinfoDTO) : Enter");
		DcrinfoDTO dcrinfoDTO = null;	
		DcrinfoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof DcrinfoDTO)
		{
			dcrinfoDTO = (DcrinfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(DcrinfoDTO) : componentId = "+ dcrinfoDTO.getComponentId());
		
		try
		{
			successResult = dcrinfoDAO.modify(dcrinfoDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(DcrinfoDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(DcrinfoDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(DcrinfoDTO) : Enter");
		DcrinfoSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		DcrinfoDTO dcrinfoDTO = null;		
		if(genericDTO instanceof DcrinfoDTO)
		{
			dcrinfoDTO = (DcrinfoDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF DcrinfoDTO.");
		}
		
		logger.info("remove(DcrinfoDTO) : code = "+ dcrinfoDTO.getUniqueCode());
		logger.info("remove(DcrinfoDTO) : componentId = "+ dcrinfoDTO.getComponentId());
		
		try
		{
			successResult = dcrinfoDAO.remove(dcrinfoDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(DcrinfoDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(DcrinfoDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		DcrinfoDTO dcrinfoDTO = null;		
		try
		{
			dcrinfoDTO = dcrinfoDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return dcrinfoDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		DcrinfoDTO dcrinfoDTO = null;		
		try
		{
			dcrinfoDTO = dcrinfoDAO.get(uniqueCode);
			if(dcrinfoDTO == null)
			{
				throw new DcrinfoNotFoundException("dcrinfo.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return dcrinfoDTO;
	}
	
	 public List<DcrinfoDTO> getList(Long groupId, DcrinfoSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<DcrinfoDTO> result = null ;
			try
			{
				result = dcrinfoDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<DcrinfoDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<DcrinfoDTO> result = null ;
			try
			{
				result = dcrinfoDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(DcrinfoDTO dcrinfoDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			dcrinfoDTO = dcrinfoDAO.get(dcrinfoDTO.getUniqueCode());
			if(dcrinfoDTO != null)
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
