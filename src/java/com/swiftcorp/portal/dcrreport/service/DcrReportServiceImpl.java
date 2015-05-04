/*
 * @ (#) DcrReportServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.dcrreport.service;
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
import com.swiftcorp.portal.dcrreport.DcrReportSuccessResult;
import com.swiftcorp.portal.dcrreport.dao.IDcrReportDAO;
import com.swiftcorp.portal.dcrreport.dao.IDcrReportDAO.DcrReportSortBy;
import com.swiftcorp.portal.dcrreport.dto.DcrReportDTO;
import com.swiftcorp.portal.dcrreport.exception.DcrReportAlreadyExistsException;
import com.swiftcorp.portal.dcrreport.exception.DcrReportCreationException;
import com.swiftcorp.portal.dcrreport.exception.DcrReportNotFoundException;
import com.swiftcorp.portal.dcrreport.service.IDcrReportService;
/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class DcrReportServiceImpl implements IDcrReportService 
{
	private static final Log logger = LogFactory.getLog(DcrReportServiceImpl.class);
	
	private IDcrReportDAO dcrreportDAO;	
	private ISearcher searcher;
	public void setDcrreportDAO(IDcrReportDAO dcrreportDAO) 
	{
		this.dcrreportDAO = dcrreportDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(DcrReportDTO) : Enter");
		DcrReportDTO dcrreportDTO = null;
		
		DcrReportSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof DcrReportDTO)
		{
			dcrreportDTO = (DcrReportDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(dcrreportDTO);
		logger.info("add(DcrReportDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new DcrReportAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(DcrReportDTO) : componentId = "+ dcrreportDTO.getComponentId());
		
		try
		{
			successResult = dcrreportDAO.add(dcrreportDTO);
		}
		catch(Exception e)
		{
			logger.info("add(DcrReportDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(DcrReportDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(DcrReportDTO) : Enter");
		DcrReportDTO dcrreportDTO = null;	
		DcrReportSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof DcrReportDTO)
		{
			dcrreportDTO = (DcrReportDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(DcrReportDTO) : componentId = "+ dcrreportDTO.getComponentId());
		
		try
		{
			successResult = dcrreportDAO.modify(dcrreportDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(DcrReportDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(DcrReportDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(DcrReportDTO) : Enter");
		DcrReportSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		DcrReportDTO dcrreportDTO = null;		
		if(genericDTO instanceof DcrReportDTO)
		{
			dcrreportDTO = (DcrReportDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF DcrReportDTO.");
		}
		
		logger.info("remove(DcrReportDTO) : code = "+ dcrreportDTO.getUniqueCode());
		logger.info("remove(DcrReportDTO) : componentId = "+ dcrreportDTO.getComponentId());
		
		try
		{
			successResult = dcrreportDAO.remove(dcrreportDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(DcrReportDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(DcrReportDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		DcrReportDTO dcrreportDTO = null;		
		try
		{
			dcrreportDTO = dcrreportDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return dcrreportDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		DcrReportDTO dcrreportDTO = null;		
		try
		{
			dcrreportDTO = dcrreportDAO.get(uniqueCode);
			if(dcrreportDTO == null)
			{
				throw new DcrReportNotFoundException("dcrreport.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return dcrreportDTO;
	}
	
	public GenericDTO get() throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		DcrReportDTO dcrreportDTO = null;		
		try
		{
			dcrreportDTO = dcrreportDAO.get();
			if(dcrreportDTO == null)
			{
				throw new DcrReportNotFoundException("dcrreport.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return dcrreportDTO;
	}
	
	 public List<DcrReportDTO> getList(Long groupId, DcrReportSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<DcrReportDTO> result = null ;
			try
			{
				result = dcrreportDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<DcrReportDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<DcrReportDTO> result = null ;
			try
			{
				result = dcrreportDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(DcrReportDTO dcrreportDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			dcrreportDTO = dcrreportDAO.get(dcrreportDTO.getUniqueCode());
			if(dcrreportDTO != null)
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
