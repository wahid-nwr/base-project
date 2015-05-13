/*
 * @ (#) LoginServiceImpl.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.login.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.LoginSuccessResult;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.util.ValidationUtil;
import com.swiftcorp.portal.user.UserSuccessResult;
import com.swiftcorp.portal.user.exception.InvalidPasswordException;
import com.swiftcorp.portal.user.exception.UserNotFoundException;
import com.swiftcorp.portal.user.service.IUserService;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class LoginServiceImpl implements ILoginService
{
	private static final Log logger = LogFactory.getLog ( LoginServiceImpl.class );
	
	private IUserService userService;
	
	public LoginServiceImpl ( )
	{
		
	}
	
	public void setUserService ( IUserService userService )
	{
		this.userService = userService;
	}
	
	public LoginSuccessResult authenticate ( LoginDTO loginDTO )
			throws SystemException, UserNotFoundException,
			InvalidPasswordException
	{
		logger.debug ( "authenticate(): Enter" );
		String userId = loginDTO.getUserId ();
		String password = loginDTO.getPassword ();
		
		logger.debug ( "userId: " + userId );
		logger.debug ( "password: " + password );
		
		if ( ValidationUtil.isEmpty ( userId ) )
		{
			throw new UserNotFoundException ( "User Id is empty" );
		}
		
		if ( ValidationUtil.isEmpty ( password ) )
		{
			throw new InvalidPasswordException ( "Password is empty" );
		}
		
		LoginSuccessResult loginSuccessResult = new LoginSuccessResult ();
		
		// Checking for valid User
		UserSuccessResult userSuccessResult = null;
		try
		{
			userSuccessResult = (UserSuccessResult) userService.authenticate ( loginDTO );
			loginSuccessResult.setOperationResult ( userSuccessResult.getOperationResult () );
			return loginSuccessResult;
		}
		catch (UserNotFoundException e)
		{
			logger.error ( e );
			throw e;
		}
		catch (InvalidPasswordException e)
		{
			logger.error ( e );
			throw e;
		}
	}
	
}
