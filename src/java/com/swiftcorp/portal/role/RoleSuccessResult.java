/*
 * @ (#) RoleSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.role;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.role.dto.RoleDTO;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class RoleSuccessResult extends BusinessOperationResult
{
	private static final long serialVersionUID = 1L;
	private RoleDTO operationResult;
	
	public RoleDTO getOperationResult ( )
	{
		return operationResult;
	}
	
	public void setOperationResult ( RoleDTO operationResult )
	{
		this.operationResult = operationResult;
	}
	
}
