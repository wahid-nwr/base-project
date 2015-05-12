/*
 * @ (#) AjanSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.ajan;
import com.swiftcorp.portal.ajan.dto.AjanDTO;
import com.swiftcorp.portal.common.BusinessOperationResult;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class AjanSuccessResult extends BusinessOperationResult
{
	private AjanDTO operationResult;
	public AjanDTO getOperationResult()
	{
		return operationResult;
	}
	public void setOperationResult(AjanDTO operationResult)
	{
		this.operationResult = operationResult;
	}	
	 
	 
}
