/*
 * @ (#) ILoginService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.login.service;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.LoginSuccessResult;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.user.exception.InvalidPasswordException;
import com.swiftcorp.portal.user.exception.UserNotFoundException;

/**
 * @author swift
 * @since mar 3, 2011
 */
public interface ILoginService
{
	public LoginSuccessResult authenticate ( LoginDTO LoginDTO )
			throws SystemException, UserNotFoundException,
			InvalidPasswordException;
}
