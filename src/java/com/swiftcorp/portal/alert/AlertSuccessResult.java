/*
 * @ (#) AlertSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.alert;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.alert.dto.AlertDTO;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class AlertSuccessResult extends BusinessOperationResult
{
	private AlertDTO operationResult;
	
	public AlertDTO getOperationResult ( )
	{
		return operationResult;
	}
	
	public void setOperationResult ( AlertDTO operationResult )
	{
		this.operationResult = operationResult;
	}
	
}
