/*
 * @ (#) EmailSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.email;
import com.swiftcorp.portal.email.dto.EmailDTO;
import com.swiftcorp.portal.common.BusinessOperationResult;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailSuccessResult extends BusinessOperationResult
{
	private EmailDTO operationResult;
	public EmailDTO getOperationResult()
	{
		return operationResult;
	}
	public void setOperationResult(EmailDTO operationResult)
	{
		this.operationResult = operationResult;
	}	
	 
	 
}
