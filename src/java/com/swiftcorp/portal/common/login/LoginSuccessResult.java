/*
 * @ (#) LoginSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.login;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author swift
 * @since mar 3, 2011
 */

public class LoginSuccessResult extends BusinessOperationResult
{
	private GenericDTO operationResult;
	
	public GenericDTO getOperationResult ( )
	{
		return operationResult;
	}
	
	public void setOperationResult ( GenericDTO operationResult )
	{
		if ( operationResult instanceof UserDTO || operationResult instanceof UserDTO )
		{
			this.operationResult = operationResult;
		}
		else
		{
			throw new RuntimeException ( "OPERATION RESULT MUST BE OF TYPE UserDTO or UserDTO..." );
		}
		
	}
	
}
