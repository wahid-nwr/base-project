/*
 * @ (#) GroupSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.group;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.group.dto.GroupDTO;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class GroupSuccessResult extends BusinessOperationResult
{
	private GroupDTO operationResult;
	
	public GroupDTO getOperationResult ( )
	{
		return operationResult;
	}
	
	public void setOperationResult ( GroupDTO operationResult )
	{
		this.operationResult = operationResult;
	}
}
