/*
 * @ (#) GeneralprofileServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.generalprofile.service;
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
import com.swiftcorp.portal.generalprofile.GeneralprofileSuccessResult;
import com.swiftcorp.portal.generalprofile.dao.IGeneralprofileDAO;
import com.swiftcorp.portal.generalprofile.dto.GeneralprofileDTO;
import com.swiftcorp.portal.generalprofile.exception.GeneralprofileCreationException;
import com.swiftcorp.portal.generalprofile.exception.GeneralprofileNotFoundException;
import com.swiftcorp.portal.generalprofile.exception.GeneralprofileAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.generalprofile.dao.IGeneralprofileDAO.GeneralprofileSortBy;
import com.swiftcorp.portal.generalprofile.service.IGeneralprofileService;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class GeneralprofileServiceImpl implements IGeneralprofileService 
{
	private static final Log logger = LogFactory.getLog(GeneralprofileServiceImpl.class);
	
	private IGeneralprofileDAO generalprofileDAO;	
	private ISearcher searcher;
	public void setGeneralprofileDAO(IGeneralprofileDAO generalprofileDAO) 
	{
		this.generalprofileDAO = generalprofileDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(GeneralprofileDTO) : Enter");
		GeneralprofileDTO generalprofileDTO = null;
		
		GeneralprofileSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof GeneralprofileDTO)
		{
			generalprofileDTO = (GeneralprofileDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(generalprofileDTO);
		logger.info("add(GeneralprofileDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new GeneralprofileAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(GeneralprofileDTO) : componentId = "+ generalprofileDTO.getComponentId());
		
		try
		{
			successResult = generalprofileDAO.add(generalprofileDTO);
		}
		catch(Exception e)
		{
			logger.info("add(GeneralprofileDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(GeneralprofileDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(GeneralprofileDTO) : Enter");
		GeneralprofileDTO generalprofileDTO = null;	
		GeneralprofileSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof GeneralprofileDTO)
		{
			generalprofileDTO = (GeneralprofileDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(GeneralprofileDTO) : componentId = "+ generalprofileDTO.getComponentId());
		
		try
		{
			successResult = generalprofileDAO.modify(generalprofileDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(GeneralprofileDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(GeneralprofileDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(GeneralprofileDTO) : Enter");
		GeneralprofileSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		GeneralprofileDTO generalprofileDTO = null;		
		if(genericDTO instanceof GeneralprofileDTO)
		{
			generalprofileDTO = (GeneralprofileDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF GeneralprofileDTO.");
		}
		
		logger.info("remove(GeneralprofileDTO) : code = "+ generalprofileDTO.getUniqueCode());
		logger.info("remove(GeneralprofileDTO) : componentId = "+ generalprofileDTO.getComponentId());
		
		try
		{
			successResult = generalprofileDAO.remove(generalprofileDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(GeneralprofileDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(GeneralprofileDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		GeneralprofileDTO generalprofileDTO = null;		
		try
		{
			generalprofileDTO = generalprofileDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return generalprofileDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		GeneralprofileDTO generalprofileDTO = null;		
		try
		{
			generalprofileDTO = generalprofileDAO.get(uniqueCode);
			if(generalprofileDTO == null)
			{
				throw new GeneralprofileNotFoundException("generalprofile.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return generalprofileDTO;
	}
	
	 public List<GeneralprofileDTO> getList(Long groupId, GeneralprofileSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<GeneralprofileDTO> result = null ;
			try
			{
				result = generalprofileDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<GeneralprofileDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<GeneralprofileDTO> result = null ;
			try
			{
				result = generalprofileDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(GeneralprofileDTO generalprofileDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			generalprofileDTO = generalprofileDAO.get(generalprofileDTO.getUniqueCode());
			if(generalprofileDTO != null)
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
