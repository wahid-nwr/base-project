/*
 * @ (#) BeneficiaryServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.beneficiary.service;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.beneficiary.BeneficiarySuccessResult;
import com.swiftcorp.portal.beneficiary.dao.IBeneficiaryDAO;
import com.swiftcorp.portal.beneficiary.dto.BeneficiaryDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class BeneficiaryServiceImpl implements IBeneficiaryService
{
	private static final Log logger = LogFactory.getLog ( BeneficiaryServiceImpl.class );
	
	private IBeneficiaryDAO beneficiaryDAO;
	private ISearcher searcher;
	
	public void setBeneficiaryDAO ( IBeneficiaryDAO beneficiaryDAO )
	{
		this.beneficiaryDAO = beneficiaryDAO;
	}
	
	public void setSearcher ( ISearcher searcher )
	{
		this.searcher = searcher;
	}

	@Override
	public BusinessOperationResult add ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(MotherBeneficiaryDTO) : Enter" );
		BeneficiaryDTO beneficiaryDTO = null;
		
		BeneficiarySuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof BeneficiaryDTO )
		{
			beneficiaryDTO = (BeneficiaryDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		
		try
		{
			successResult = beneficiaryDAO.addBeneficiary ( beneficiaryDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(MotherBeneficiaryDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(MotherBeneficiaryDTO) : Exit" );
		return successResult;
	}

	@Override
	public BusinessOperationResult modify ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException,
			SQLException, Exception
	{
		this.beneficiaryDAO.modifyBeneficiary ( (BeneficiaryDTO) theDTO );
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BusinessOperationResult remove ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericDTO get ( Long componentId )
			throws SystemException, BusinessRuleViolationException
	{
		GenericDTO genericDTO = beneficiaryDAO.get ( componentId );
		
		// TODO Auto-generated method stub
		return genericDTO;
	}

	@Override
	public GenericDTO get ( String uniqueCode )
			throws SystemException, BusinessRuleViolationException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public SearchOperationResult search ( String query ) throws SystemException, BusinessRuleViolationException
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
		
}
