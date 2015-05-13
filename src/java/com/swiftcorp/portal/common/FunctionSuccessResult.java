/*
 * @ (#) FunctionSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */

package com.swiftcorp.portal.common;

import com.swiftcorp.portal.common.dto.FunctionDTO;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class FunctionSuccessResult extends BusinessOperationResult
{
	private FunctionDTO operationResult;
	
	public FunctionDTO getOperationResult ( )
	{
		return operationResult;
	}
	
	public void setOperationResult ( FunctionDTO operationResult )
	{
		this.operationResult = operationResult;
	}
	
}
