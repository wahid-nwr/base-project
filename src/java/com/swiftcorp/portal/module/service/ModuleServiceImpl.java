/*
 * @ (#) ModuleServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.module.service;
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
import com.swiftcorp.portal.module.ModuleSuccessResult;
import com.swiftcorp.portal.module.dao.IModuleDAO;
import com.swiftcorp.portal.module.dto.ModuleDTO;
import com.swiftcorp.portal.module.exception.ModuleCreationException;
import com.swiftcorp.portal.module.exception.ModuleNotFoundException;
import com.swiftcorp.portal.module.exception.ModuleAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.module.dao.IModuleDAO.ModuleSortBy;
import com.swiftcorp.portal.module.service.IModuleService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class ModuleServiceImpl implements IModuleService 
{
	private static final Log logger = LogFactory.getLog(ModuleServiceImpl.class);
	
	private IModuleDAO moduleDAO;	
	private ISearcher searcher;
	public void setModuleDAO(IModuleDAO moduleDAO) 
	{
		this.moduleDAO = moduleDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(ModuleDTO) : Enter");
		ModuleDTO moduleDTO = null;
		
		ModuleSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof ModuleDTO)
		{
			moduleDTO = (ModuleDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(moduleDTO);
		logger.info("add(ModuleDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new ModuleAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(ModuleDTO) : componentId = "+ moduleDTO.getComponentId());
		
		try
		{
			successResult = moduleDAO.add(moduleDTO);
		}
		catch(Exception e)
		{
			logger.info("add(ModuleDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(ModuleDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(ModuleDTO) : Enter");
		ModuleDTO moduleDTO = null;	
		ModuleSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof ModuleDTO)
		{
			moduleDTO = (ModuleDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(ModuleDTO) : componentId = "+ moduleDTO.getComponentId());
		
		try
		{
			successResult = moduleDAO.modify(moduleDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(ModuleDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(ModuleDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(ModuleDTO) : Enter");
		ModuleSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		ModuleDTO moduleDTO = null;		
		if(genericDTO instanceof ModuleDTO)
		{
			moduleDTO = (ModuleDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF ModuleDTO.");
		}
		
		logger.info("remove(ModuleDTO) : code = "+ moduleDTO.getUniqueCode());
		logger.info("remove(ModuleDTO) : componentId = "+ moduleDTO.getComponentId());
		
		try
		{
			successResult = moduleDAO.remove(moduleDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(ModuleDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(ModuleDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		ModuleDTO moduleDTO = null;		
		try
		{
			moduleDTO = moduleDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return moduleDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		ModuleDTO moduleDTO = null;		
		try
		{
			moduleDTO = moduleDAO.get(uniqueCode);
			if(moduleDTO == null)
			{
				throw new ModuleNotFoundException("module.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return moduleDTO;
	}
	
	 public List<ModuleDTO> getList(Long groupId, ModuleSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<ModuleDTO> result = null ;
			try
			{
				result = moduleDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<ModuleDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<ModuleDTO> result = null ;
			try
			{
				result = moduleDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(ModuleDTO moduleDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			moduleDTO = moduleDAO.get(moduleDTO.getUniqueCode());
			if(moduleDTO != null)
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
