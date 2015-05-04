/*
 * @ (#) AccountSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.account;
import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.account.dto.AccountDTO;
/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class AccountSuccessResult extends BusinessOperationResult
{
	private AccountDTO operationResult;
	public AccountDTO getOperationResult()
	{
		return operationResult;
	}
	public void setOperationResult(AccountDTO operationResult)
	{
		this.operationResult = operationResult;
	}	
	 
	 
}
