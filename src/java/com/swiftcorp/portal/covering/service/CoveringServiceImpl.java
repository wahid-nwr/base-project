/*
 * @ (#) CoveringServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.covering.service;
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
import com.swiftcorp.portal.covering.CoveringSuccessResult;
import com.swiftcorp.portal.covering.dao.ICoveringDAO;
import com.swiftcorp.portal.covering.dto.CoveringDTO;
import com.swiftcorp.portal.covering.exception.CoveringCreationException;
import com.swiftcorp.portal.covering.exception.CoveringNotFoundException;
import com.swiftcorp.portal.covering.exception.CoveringAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.covering.dao.ICoveringDAO.CoveringSortBy;
import com.swiftcorp.portal.covering.service.ICoveringService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class CoveringServiceImpl implements ICoveringService 
{
	private static final Log logger = LogFactory.getLog(CoveringServiceImpl.class);
	
	private ICoveringDAO coveringDAO;	
	private ISearcher searcher;
	public void setCoveringDAO(ICoveringDAO coveringDAO) 
	{
		this.coveringDAO = coveringDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(CoveringDTO) : Enter");
		CoveringDTO coveringDTO = null;
		
		CoveringSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof CoveringDTO)
		{
			coveringDTO = (CoveringDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(coveringDTO);
		logger.info("add(CoveringDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new CoveringAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(CoveringDTO) : componentId = "+ coveringDTO.getComponentId());
		
		try
		{
			successResult = coveringDAO.add(coveringDTO);
		}
		catch(Exception e)
		{
			logger.info("add(CoveringDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(CoveringDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(CoveringDTO) : Enter");
		CoveringDTO coveringDTO = null;	
		CoveringSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof CoveringDTO)
		{
			coveringDTO = (CoveringDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(CoveringDTO) : componentId = "+ coveringDTO.getComponentId());
		
		try
		{
			successResult = coveringDAO.modify(coveringDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(CoveringDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(CoveringDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(CoveringDTO) : Enter");
		CoveringSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		CoveringDTO coveringDTO = null;		
		if(genericDTO instanceof CoveringDTO)
		{
			coveringDTO = (CoveringDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF CoveringDTO.");
		}
		
		logger.info("remove(CoveringDTO) : code = "+ coveringDTO.getUniqueCode());
		logger.info("remove(CoveringDTO) : componentId = "+ coveringDTO.getComponentId());
		
		try
		{
			successResult = coveringDAO.remove(coveringDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(CoveringDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(CoveringDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		CoveringDTO coveringDTO = null;		
		try
		{
			coveringDTO = coveringDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return coveringDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		CoveringDTO coveringDTO = null;		
		try
		{
			coveringDTO = coveringDAO.get(uniqueCode);
			if(coveringDTO == null)
			{
				throw new CoveringNotFoundException("covering.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return coveringDTO;
	}
	
	 public List<CoveringDTO> getList(Long groupId, CoveringSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<CoveringDTO> result = null ;
			try
			{
				result = coveringDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<CoveringDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<CoveringDTO> result = null ;
			try
			{
				result = coveringDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(CoveringDTO coveringDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			coveringDTO = coveringDAO.get(coveringDTO.getUniqueCode());
			if(coveringDTO != null)
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
