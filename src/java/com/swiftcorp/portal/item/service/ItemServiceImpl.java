/*
 * @ (#) ItemServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.item.service;
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
import com.swiftcorp.portal.item.ItemSuccessResult;
import com.swiftcorp.portal.item.dao.IItemDAO;
import com.swiftcorp.portal.item.dto.ItemDTO;
import com.swiftcorp.portal.item.exception.ItemCreationException;
import com.swiftcorp.portal.item.exception.ItemNotFoundException;
import com.swiftcorp.portal.item.exception.ItemAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.item.dao.IItemDAO.ItemSortBy;
import com.swiftcorp.portal.item.service.IItemService;
/**
 * @author swift
 * @since mar 3, 2011
 */
public class ItemServiceImpl implements IItemService 
{
	private static final Log logger = LogFactory.getLog(ItemServiceImpl.class);
	
	private IItemDAO itemDAO;	
	private ISearcher searcher;
	public void setItemDAO(IItemDAO itemDAO) 
	{
		this.itemDAO = itemDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(ItemDTO) : Enter");
		ItemDTO itemDTO = null;
		
		ItemSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof ItemDTO)
		{
			itemDTO = (ItemDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(itemDTO);
		logger.info("add(ItemDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new ItemAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(ItemDTO) : componentId = "+ itemDTO.getComponentId());
		
		try
		{
			successResult = itemDAO.add(itemDTO);
		}
		catch(Exception e)
		{
			logger.info("add(ItemDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(ItemDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(ItemDTO) : Enter");
		ItemDTO itemDTO = null;	
		ItemSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof ItemDTO)
		{
			itemDTO = (ItemDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(ItemDTO) : componentId = "+ itemDTO.getComponentId());
		
		try
		{
			successResult = itemDAO.modify(itemDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(ItemDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(ItemDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(ItemDTO) : Enter");
		ItemSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		ItemDTO itemDTO = null;		
		if(genericDTO instanceof ItemDTO)
		{
			itemDTO = (ItemDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF ItemDTO.");
		}
		
		logger.info("remove(ItemDTO) : code = "+ itemDTO.getUniqueCode());
		logger.info("remove(ItemDTO) : componentId = "+ itemDTO.getComponentId());
		
		try
		{
			successResult = itemDAO.remove(itemDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(ItemDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(ItemDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		ItemDTO itemDTO = null;		
		try
		{
			itemDTO = itemDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return itemDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		ItemDTO itemDTO = null;		
		try
		{
			itemDTO = itemDAO.get(uniqueCode);
			if(itemDTO == null)
			{
				throw new ItemNotFoundException("item.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return itemDTO;
	}
	
	 public List<ItemDTO> getList(Long groupId, ItemSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<ItemDTO> result = null ;
			try
			{
				result = itemDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<ItemDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<ItemDTO> result = null ;
			try
			{
				result = itemDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(ItemDTO itemDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			itemDTO = itemDAO.get(itemDTO.getUniqueCode());
			if(itemDTO != null)
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
