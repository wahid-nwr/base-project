/*
 * @ (#) AlgorithmServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.algorithm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.algorithm.AlgQuestionSuccessResult;
import com.swiftcorp.portal.algorithm.AlgorithmSuccessResult;
import com.swiftcorp.portal.algorithm.dao.IAlgorithmDAO;
import com.swiftcorp.portal.algorithm.dao.IAlgorithmDAO.AlgorithmSortBy;
import com.swiftcorp.portal.algorithm.dto.AlgQuestionDTO;
import com.swiftcorp.portal.algorithm.dto.AlgorithmDTO;
import com.swiftcorp.portal.algorithm.exception.AlgorithmNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.common.util.StringUtils;
import com.swiftcorp.portal.question.dto.QDTO;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class AlgorithmServiceImpl implements IAlgorithmService
{
	private static final Log logger = LogFactory.getLog ( AlgorithmServiceImpl.class );
	
	private IAlgorithmDAO algorithmDAO;
	private ISearcher searcher;
	
	public void setAlgorithmDAO ( IAlgorithmDAO algorithmDAO )
	{
		this.algorithmDAO = algorithmDAO;
	}
	
	public void setSearcher ( ISearcher searcher )
	{
		this.searcher = searcher;
	}
	
	public BusinessOperationResult add ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(AlgorithmDTO) : Enter" );
		AlgorithmDTO algorithmDTO = null;
		
		AlgorithmSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof AlgorithmDTO )
		{
			algorithmDTO = (AlgorithmDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(algorithmDTO);
		 * logger.info("add(AlgorithmDTO) : isExist = " + isExist); if(isExist)
		 * { throw new
		 * AlgorithmAlreadyExistsException("already.exist.same.code"); }
		 */
		logger.info ( "add(AlgorithmDTO) : componentId = " + algorithmDTO.getComponentId () );
		
		try
		{
			successResult = algorithmDAO.add ( algorithmDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(AlgorithmDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(AlgorithmDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult addAlgquestion ( GenericDTO genericDTO )
	throws SystemException
	{
		logger.info ( "add(AlgorithmDTO) : Enter" );
		AlgQuestionDTO algQuestionDTO = null;
		
		AlgQuestionSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof AlgQuestionDTO )
		{
			algQuestionDTO = (AlgQuestionDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(algorithmDTO);
		 * logger.info("add(AlgorithmDTO) : isExist = " + isExist); if(isExist)
		 * { throw new
		 * AlgorithmAlreadyExistsException("already.exist.same.code"); }
		 */
		logger.info ( "add(AlgorithmDTO) : componentId = " + algQuestionDTO.getComponentId () );
		
		try
		{
			successResult = algorithmDAO.addAlgQuestion ( algQuestionDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(AlgorithmDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(AlgorithmDTO) : Exit" );
		return successResult;
	}
	public BusinessOperationResult modifyAlgquestion ( GenericDTO genericDTO )
	throws SystemException
	{
		logger.info ( "add(AlgorithmDTO) : Enter" );
		AlgQuestionDTO algQuestionDTO = null;
		
		AlgQuestionSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof AlgQuestionDTO )
		{
			algQuestionDTO = (AlgQuestionDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(algorithmDTO);
		 * logger.info("add(AlgorithmDTO) : isExist = " + isExist); if(isExist)
		 * { throw new
		 * AlgorithmAlreadyExistsException("already.exist.same.code"); }
		 */
		logger.info ( "add(AlgorithmDTO) : componentId = " + algQuestionDTO.getComponentId () );
		
		try
		{
			successResult = algorithmDAO.modifyAlgQuestion ( algQuestionDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(AlgorithmDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(AlgorithmDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult removeAlgquestion ( GenericDTO genericDTO )
	throws SystemException
	{
		logger.info ( "add(AlgorithmDTO) : Enter" );
		AlgQuestionDTO algQuestionDTO = null;
		
		AlgQuestionSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof AlgQuestionDTO )
		{
			algQuestionDTO = (AlgQuestionDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		/*
		 * boolean isExist = checkUniqueCodeDuplicacy(algorithmDTO);
		 * logger.info("add(AlgorithmDTO) : isExist = " + isExist); if(isExist)
		 * { throw new
		 * AlgorithmAlreadyExistsException("already.exist.same.code"); }
		 */
		logger.info ( "add(AlgorithmDTO) : componentId = " + algQuestionDTO.getComponentId () );
		
		try
		{
			successResult = algorithmDAO.removeAlgQuestion ( algQuestionDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(AlgorithmDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(AlgorithmDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modify ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modify(AlgorithmDTO) : Enter" );
		AlgorithmDTO algorithmDTO = null;
		AlgorithmSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof AlgorithmDTO )
		{
			algorithmDTO = (AlgorithmDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modify(AlgorithmDTO) : componentId = " + algorithmDTO.getComponentId () );
		
		try
		{
			successResult = algorithmDAO.modify ( algorithmDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modify(AlgorithmDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modify(AlgorithmDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult remove ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "remove(AlgorithmDTO) : Enter" );
		AlgorithmSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "DTO MUST NOT NULL." );
		}
		
		AlgorithmDTO algorithmDTO = null;
		if ( genericDTO instanceof AlgorithmDTO )
		{
			algorithmDTO = (AlgorithmDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "INVALID DTO TYPE. MUST BE INSTANCE OF AlgorithmDTO." );
		}
		
		logger.info ( "remove(AlgorithmDTO) : code = " + algorithmDTO.getUniqueCode () );
		logger.info ( "remove(AlgorithmDTO) : componentId = " + algorithmDTO.getComponentId () );
		
		try
		{
			successResult = algorithmDAO.remove ( algorithmDTO );
		}
		catch (Exception e)
		{
			logger.info ( "remove(AlgorithmDTO) :", e );
			throw new SystemException ( e );
		}
		logger.info ( "remove(AlgorithmDTO) : Exit" );
		return successResult;
	}
	
	public GenericDTO get ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		AlgorithmDTO algorithmDTO = null;
		try
		{
			algorithmDTO = algorithmDAO.get ( componentId );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return algorithmDTO;
	}
	
	public GenericDTO getAlgQuestionByQQId ( QDTO qdto ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : qDTOId = " + qdto );
		AlgQuestionDTO algQuestionDTO = null;
		try
		{
			algQuestionDTO = algorithmDAO.getAlgQuestionByQQId ( qdto );
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return algQuestionDTO;
	}
	
	public GenericDTO get ( String uniqueCode )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "get(code) : Enter" );
		AlgorithmDTO algorithmDTO = null;
		try
		{
			algorithmDTO = algorithmDAO.get ( uniqueCode );
			if ( algorithmDTO == null )
			{
				throw new AlgorithmNotFoundException ( "algorithm.not.found" );
			}
		}
		catch (Exception e)
		{
			logger.error ( "get(code)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(code) : Exit" );
		return algorithmDTO;
	}
	
	public List<AlgorithmDTO> getList ( Long groupId, AlgorithmSortBy sortby )
			throws SystemException
	{
		logger.info ( "getList(groupId,sortby) : Enter" );
		ArrayList<AlgorithmDTO> result = null;
		try
		{
			result = algorithmDAO.getList ( groupId, sortby );
		}
		catch (Exception e)
		{
			logger.error ( "getList(groupId,sortby)", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList(groupId,sortby) : Exit" );
		return result;
	}
	
	public List<AlgorithmDTO> getList ( ) throws SystemException
	{
		logger.info ( "getList() : Enter" );
		ArrayList<AlgorithmDTO> result = null;
		try
		{
			result = algorithmDAO.getList ();
		}
		catch (Exception e)
		{
			logger.error ( "getList()", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList() : Exit" );
		return result;
	}
	
	/**
	 * Get the algorithm List for Sync, At first check whether the mobile has
	 * this algorithm if not then send it, if it has then check the version, if
	 * the mobile has lower version then send it
	 * 
	 * @param mobileAlgList
	 * @return
	 */
	public List<AlgorithmDTO> getSyncAlgorithmList ( List<AlgorithmDTO> mobileAlgList )
	{
		// algorithm list to return
		List<AlgorithmDTO> syncAlgList = new ArrayList<AlgorithmDTO> ();
		
		try
		{
			// get the existing sdp list
			List<AlgorithmDTO> sdpAlgorithmList = this.getList ();
			
			// make the map for the sdp list
			Map<String, AlgorithmDTO> mobileAlgIdAlgorithmDTOMap = new HashMap<String, AlgorithmDTO> ();
			
			// if there is some alg in the mobile list
			if ( mobileAlgList != null && mobileAlgList.size () != 0 )
			{
				// make a map for mobile algid and algorithm
				for ( AlgorithmDTO mobileAlgorithmDTO : mobileAlgList )
				{
					// if it is not null, put it to map
					if ( mobileAlgorithmDTO != null )
					{
						// get the alg id
						String algId = mobileAlgorithmDTO.getAlgId ();
						mobileAlgIdAlgorithmDTOMap.put ( algId, mobileAlgorithmDTO );
						
					}
				}
				// iterate the algorithm of the sdp
				for ( AlgorithmDTO sdpAlgorithmDTO : sdpAlgorithmList )
				{
					// get the alg id
					String sdpAlgId = sdpAlgorithmDTO.getAlgId ();
					String sdpAlgVersion = sdpAlgorithmDTO.getVersion ();
					
					// get the algorithm from the sdp
					AlgorithmDTO mobileAlgorithmDTO = mobileAlgIdAlgorithmDTOMap.get ( sdpAlgId );
					if ( mobileAlgorithmDTO != null )
					{
						String mobileAlgVersion = mobileAlgorithmDTO.getVersion ();
						
						// now checke if the verion in the sdp is upper version
						if ( StringUtils.isUpperVersion ( sdpAlgVersion, mobileAlgVersion ) )
						{
							syncAlgList.add ( sdpAlgorithmDTO );
						}
					}
					
					// mobile alg is null so mobile hasn't this dto, so add it
					// to the list
					else
					{
						syncAlgList.add ( sdpAlgorithmDTO );
					}
					
				}
				System.out.println ( "syncAlgList sizze::::::::::" + syncAlgList.size () );
			}
			
			// if the mobile alg size is zero then return the whole list
			// this will happen for the first time when the mobile has no
			// algorithm
			else
			{
				syncAlgList = sdpAlgorithmList;
			}
			
			// free the resources
			sdpAlgorithmList = null;
			mobileAlgIdAlgorithmDTOMap = null;
		}
		catch (SystemException e)
		{
			logger.error ( "Error occured while fetching the sync Algorithm" );
		}
		
		// return the sycn alg list
		return syncAlgList;
	}
	
	public SearchOperationResult search ( String query )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "search() : Enter" );
		SearchOperationResult searchResult = null;
		try
		{
			searchResult = searcher.search ( query );
		}
		catch (InvalidSQLSyntaxException e)
		{
			logger.info ( "search() :", e );
			throw e;
		}
		catch (SystemException e)
		{
			logger.info ( "search() :", e );
			throw e;
		}
		logger.info ( "search() : Exit" );
		return searchResult;
	}
	
	private boolean checkUniqueCodeDuplicacy ( AlgorithmDTO algorithmDTO )
			throws SystemException
	{
		boolean isExist = false;
		try
		{
			algorithmDTO = algorithmDAO.get ( algorithmDTO.getUniqueCode () );
			if ( algorithmDTO != null )
			{
				isExist = true;
			}
		}
		catch (SystemException e)
		{
			throw e;
		}
		return isExist;
	}
	
}
