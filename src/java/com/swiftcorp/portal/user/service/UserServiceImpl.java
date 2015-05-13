/*
 * @ (#) UserServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */

package com.swiftcorp.portal.user.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.encryption.EncryptionUtil;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.login.service.ILoginService;
import com.swiftcorp.portal.common.search.ISearcher;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.user.UserSuccessResult;
import com.swiftcorp.portal.user.dao.IUserDAO;
import com.swiftcorp.portal.user.dao.IUserDAO.UserSortBy;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.user.exception.InvalidPasswordException;
import com.swiftcorp.portal.user.exception.UserAlreadyExistsException;
import com.swiftcorp.portal.user.exception.UserNotFoundException;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class UserServiceImpl implements IUserService
{
	private static final Log logger = LogFactory.getLog ( UserServiceImpl.class );
	
	private IUserDAO userDAO;
	private ISearcher searcher;
	private ILoginService loginService;
	
	public static Log getLogger ( )
	{
		return logger;
	}
	
	public ILoginService getLoginService ( )
	{
		return loginService;
	}
	
	public void setLoginService ( ILoginService loginService )
	{
		this.loginService = loginService;
	}
	
	public IUserDAO getUserDAO ( )
	{
		return userDAO;
	}
	
	public void setUserDAO ( IUserDAO userDAO )
	{
		this.userDAO = userDAO;
	}
	
	public ISearcher getSearcher ( )
	{
		return searcher;
	}
	
	public void setSearcher ( ISearcher searcher )
	{
		this.searcher = searcher;
	}
	
	public BusinessOperationResult add ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "add(UserDTO) : Enter" );
		UserDTO userDTO = null;
		
		UserSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "Dto must not null" );
		}
		
		if ( genericDTO instanceof UserDTO )
		{
			userDTO = (UserDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		
		// check duplicacy
		boolean isExist = checkUniqueCodeDuplicacy ( userDTO );
		logger.info ( "add(UserDTO) : isExist = " + isExist );
		if ( isExist )
		{
			throw new UserAlreadyExistsException ( "exception.UserAlreadyExistException" );
		}
		
		logger.info ( "add(UserDTO) : componentId = " + userDTO.getComponentId () );
		
		// check the group ID.if NO group is selected, then add in orphan group
		// (system default group)
		/*
		 * Long groupId = userDTO.getGroupId() ; if(groupId != null) {
		 * userDTO.setGroupId(groupId); } else { userDTO.setGroupId(new
		 * Long(GlobalConstants.GROUPID_OF_ORPHAN_USERS)); }
		 */

		try
		{
			// encrypting the password
			// Since it is newly add that's here treating as plain password. So
			// need to encrypted first
			String encryptedPass = EncryptionUtil.encryptToHexValue ( userDTO.getPassword () );
			userDTO.setPassword ( encryptedPass );
			
			successResult = userDAO.add ( userDTO );
		}
		catch (Exception e)
		{
			logger.info ( "add(UserDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "add(UserDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult modify ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "modify(UserDTO) : Enter" );
		UserDTO userDTO = null;
		
		UserSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new SystemException ( "DTO MUST NOT NULL." );
		}
		
		if ( genericDTO instanceof UserDTO )
		{
			userDTO = (UserDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "operation.failure" );
		}
		logger.info ( "modify(UserDTO) : componentId = " + userDTO.getComponentId () );
		
		try
		{
			// need to check that password is encrypted or not
			// if not then we need to encrypt this plain password before save
			String encryptedPass = EncryptionUtil.encryptToHexValue ( userDTO.getPassword () );
			userDTO.setPassword ( encryptedPass );
			
			successResult = userDAO.modify ( userDTO );
		}
		catch (Exception e)
		{
			logger.info ( "modify(UserDTO) :", e );
			throw new SystemException ( "operation.failure" );
		}
		logger.info ( "modify(UserDTO) : Exit" );
		return successResult;
	}
	
	public BusinessOperationResult remove ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "remove(UserDTO) : Enter" );
		UserSuccessResult successResult;
		if ( genericDTO == null )
		{
			throw new RuntimeException ( "DTO MUST NOT NULL." );
		}
		
		UserDTO userDTO = null;
		if ( genericDTO instanceof UserDTO )
		{
			userDTO = (UserDTO) genericDTO;
		}
		else
		{
			throw new RuntimeException ( "INVALID DTO TYPE. MUST BE INSTANCE OF UserDTO." );
		}
		
		logger.info ( "remove(UserDTO) : code = " + userDTO.getUniqueCode () );
		logger.info ( "remove(UserDTO) : componentId = " + userDTO.getComponentId () );
		
		try
		{
			successResult = userDAO.remove ( userDTO );
		}
		catch (Exception e)
		{
			logger.info ( "remove(UserDTO) :", e );
			throw new SystemException ( e );
		}
		logger.info ( "remove(UserDTO) : Exit" );
		return successResult;
	}
	
	public GenericDTO get ( Long componentId ) throws SystemException
	{
		logger.info ( "get(componentId) : Enter" );
		logger.info ( "get(componentId) : componentId = " + componentId );
		UserDTO userDTO = null;
		try
		{
			userDTO = userDAO.get ( componentId );
			if ( userDTO != null )
			{
				// since database contains the encrypted value of the password
				// we need to decrypt it first
				String decryptedPass = EncryptionUtil.decryptFromHexValue ( userDTO.getPassword () );
				userDTO.setPassword ( decryptedPass );
			}
		}
		catch (RuntimeException e)
		{
			logger.error ( "get(componentId)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(componentId) : Exit" );
		return userDTO;
	}
	
	public GenericDTO get ( String uniqueCode )
			throws SystemException, BusinessRuleViolationException
	{
		logger.info ( "get(code) : Enter" );
		UserDTO userDTO = null;
		System.out.println ( "in get userid to get user for " + uniqueCode );
		try
		{
			userDTO = userDAO.get ( uniqueCode );
			if ( userDTO == null )
			{
				throw new UserNotFoundException ( "user.not.found" );
			}
			
			// decrypting the password
			
			String decryptedPass = EncryptionUtil.decryptFromHexValue ( userDTO.getPassword () );
			userDTO.setPassword ( decryptedPass );
			
		}
		catch (Exception e)
		{
			logger.error ( "get(code)", e );
			throw new SystemException ( e );
		}
		logger.info ( "get(code) : Exit" );
		return userDTO;
	}
	
	public List<UserDTO> getList ( Long groupId, UserSortBy sortby )
			throws SystemException
	{
		logger.info ( "getList(groupId,sortby) : Enter" );
		ArrayList<UserDTO> result = null;
		try
		{
			result = userDAO.getList ( groupId, sortby );
		}
		catch (Exception e)
		{
			logger.error ( "getList(groupId,sortby)", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList(groupId,sortby) : Exit" );
		return result;
	}
	
	public List<UserDTO> getList ( ) throws SystemException
	{
		logger.info ( "getList() : Enter" );
		ArrayList<UserDTO> result = null;
		try
		{
			result = userDAO.getList ();
		}
		catch (Exception e)
		{
			logger.error ( "getList()", e );
			throw new SystemException ( e );
		}
		logger.info ( "getList() : Exit" );
		return result;
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
	
	private boolean checkUniqueCodeDuplicacy ( UserDTO userDTO )
			throws SystemException
	{
		boolean isExist = false;
		try
		{
			userDTO = userDAO.get ( userDTO.getUniqueCode () );
			if ( userDTO != null )
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
	
	public BusinessOperationResult authenticate ( LoginDTO LoginDTO )
			throws SystemException, UserNotFoundException,
			InvalidPasswordException
	{
		UserSuccessResult userSuccessResult = new UserSuccessResult ();
		
		String userId = LoginDTO.getUserId ();
		String password = LoginDTO.getPassword ();
		
		UserDTO userDTO = null;
		try
		{
			userDTO = (UserDTO) get ( userId );
		}
		catch (Exception e)
		{
			throw new SystemException ();
		}
		
		if ( userDTO == null )
		{
			throw new UserNotFoundException ( "Invalid User ID" );
		}
		
		if ( password.equals ( userDTO.getPassword () ) )
		{
			userSuccessResult.setMessage ( "AUTHENTICATED." );
			userSuccessResult.setOperationResult ( userDTO );
		}
		else
		{
			throw new InvalidPasswordException ( "Invalid password" );
		}
		
		return userSuccessResult;
	}
}
