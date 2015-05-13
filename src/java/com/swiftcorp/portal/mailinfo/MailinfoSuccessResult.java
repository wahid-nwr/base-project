/*
 * @ (#) MailinfoSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.mailinfo;
import com.swiftcorp.portal.mailinfo.dto.MailinfoDTO;
import com.swiftcorp.portal.common.BusinessOperationResult;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class MailinfoSuccessResult extends BusinessOperationResult
{
	private MailinfoDTO operationResult;
	public MailinfoDTO getOperationResult()
	{
		return operationResult;
	}
	public void setOperationResult(MailinfoDTO operationResult)
	{
		this.operationResult = operationResult;
	}	
	 
	 
}
