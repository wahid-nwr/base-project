/*
 * @ (#) UserSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.user;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class UserSuccessResult extends BusinessOperationResult
{
	private static final long serialVersionUID = 1L;
	private UserDTO operationResult;
	
	public UserDTO getOperationResult ( )
	{
		return operationResult;
	}
	
	public void setOperationResult ( UserDTO operationResult )
	{
		this.operationResult = operationResult;
	}
	
}
