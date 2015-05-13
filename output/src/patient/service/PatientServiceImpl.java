/*
 * @ (#) PatientServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.patient.service;
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
import com.swiftcorp.portal.patient.PatientSuccessResult;
import com.swiftcorp.portal.patient.dao.IPatientDAO;
import com.swiftcorp.portal.patient.dto.PatientDTO;
import com.swiftcorp.portal.patient.exception.PatientCreationException;
import com.swiftcorp.portal.patient.exception.PatientNotFoundException;
import com.swiftcorp.portal.patient.exception.PatientAlreadyExistsException;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.patient.dao.IPatientDAO.PatientSortBy;
import com.swiftcorp.portal.patient.service.IPatientService;
/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class PatientServiceImpl implements IPatientService 
{
	private static final Log logger = LogFactory.getLog(PatientServiceImpl.class);
	
	private IPatientDAO patientDAO;	
	private ISearcher searcher;
	public void setPatientDAO(IPatientDAO patientDAO) 
	{
		this.patientDAO = patientDAO;
	}
	public void setSearcher(ISearcher searcher) 
	{
		this.searcher = searcher;
	}
	public BusinessOperationResult add(GenericDTO genericDTO) throws SystemException,BusinessRuleViolationException
	{	
		logger.info("add(PatientDTO) : Enter");
		PatientDTO patientDTO = null;
		
		PatientSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("Dto must not null");
		}
		
		if(genericDTO instanceof PatientDTO)
		{
			patientDTO = (PatientDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		
		
		// check duplicacy
		/*
		boolean isExist = checkUniqueCodeDuplicacy(patientDTO);
		logger.info("add(PatientDTO) : isExist = " + isExist);
		if(isExist)
		{
			throw new PatientAlreadyExistsException("already.exist.same.code");
		}*/		
		logger.info("add(PatientDTO) : componentId = "+ patientDTO.getComponentId());
		
		try
		{
			successResult = patientDAO.add(patientDTO);
		}
		catch(Exception e)
		{
			logger.info("add(PatientDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("add(PatientDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult modify(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("modify(PatientDTO) : Enter");
		PatientDTO patientDTO = null;	
		PatientSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new SystemException("DTO MUST NOT NULL.");
		}
		
		if(genericDTO instanceof PatientDTO)
		{
			patientDTO = (PatientDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("operation.failure");
		}
		logger.info("modify(PatientDTO) : componentId = "+ patientDTO.getComponentId());
		
		try
		{
			successResult = patientDAO.modify(patientDTO);
		}
		catch(Exception e)
		{
			logger.info("modify(PatientDTO) :",e);
			throw new SystemException("operation.failure");
		}
		logger.info("modify(PatientDTO) : Exit");
		return successResult;
	}
	
	
	public BusinessOperationResult remove(GenericDTO genericDTO) throws SystemException, BusinessRuleViolationException
	{
		logger.info("remove(PatientDTO) : Enter");
		PatientSuccessResult successResult;	
		if(genericDTO == null)
		{
			throw new RuntimeException("DTO MUST NOT NULL.");
		}
		
		PatientDTO patientDTO = null;		
		if(genericDTO instanceof PatientDTO)
		{
			patientDTO = (PatientDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException("INVALID DTO TYPE. MUST BE INSTANCE OF PatientDTO.");
		}
		
		logger.info("remove(PatientDTO) : code = "+ patientDTO.getUniqueCode());
		logger.info("remove(PatientDTO) : componentId = "+ patientDTO.getComponentId());
		
		try
		{
			successResult = patientDAO.remove(patientDTO);
		}
		catch(Exception e)
		{
			logger.info("remove(PatientDTO) :",e);
			throw new SystemException(e);
		}
		logger.info("remove(PatientDTO) : Exit");
		return successResult;
	}
	
	
	
	public GenericDTO get(Long componentId) throws SystemException
	{
		logger.info("get(componentId) : Enter");
		logger.info("get(componentId) : componentId = " + componentId);
		PatientDTO patientDTO = null;		
		try
		{
			patientDTO = patientDAO.get(componentId);
		}
		catch (RuntimeException e) 
		{
			logger.error("get(componentId)",e);
			throw new SystemException(e);
		}
		logger.info("get(componentId) : Exit");
		return patientDTO;
	}
	
	
	public GenericDTO get(String uniqueCode) throws SystemException, BusinessRuleViolationException  
	{
		logger.info("get(code) : Enter");
		PatientDTO patientDTO = null;		
		try
		{
			patientDTO = patientDAO.get(uniqueCode);
			if(patientDTO == null)
			{
				throw new PatientNotFoundException("patient.not.found");	
			}
		}
		catch (Exception e) 
		{
			logger.error("get(code)",e);
			throw new SystemException(e);
		}	
		logger.info("get(code) : Exit");
		return patientDTO;
	}
	
	 public List<PatientDTO> getList(Long groupId, PatientSortBy sortby)throws SystemException
	 {
			logger.info("getList(groupId,sortby) : Enter");
			ArrayList<PatientDTO> result = null ;
			try
			{
				result = patientDAO.getList(groupId,sortby);
			}
			catch (Exception e) 
			{
				logger.error("getList(groupId,sortby)",e);
				throw new SystemException(e);
			}	
			logger.info("getList(groupId,sortby) : Exit");
			return result; 
	 }
	 
	 public List<PatientDTO> getList()throws SystemException
	 {
			logger.info("getList() : Enter");
			ArrayList<PatientDTO> result = null ;
			try
			{
				result = patientDAO.getList() ;
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
	
	private boolean checkUniqueCodeDuplicacy(PatientDTO patientDTO) throws SystemException
	{
		boolean isExist = false ;
		try 
		{
			patientDTO = patientDAO.get(patientDTO.getUniqueCode());
			if(patientDTO != null)
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
