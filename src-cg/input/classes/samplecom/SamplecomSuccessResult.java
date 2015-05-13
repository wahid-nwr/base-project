/*
 * @ (#) SamplecomSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.samplecom;

import com.swiftcorp.portal.samplecom.dto.SamplecomDTO;
import com.swiftcorp.portal.common.BusinessOperationResult;

/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class SamplecomSuccessResult extends BusinessOperationResult
{
	private SamplecomDTO operationResult;

	public SamplecomDTO getOperationResult()
	{
		return operationResult;
	}

	public void setOperationResult(SamplecomDTO operationResult)
	{
		this.operationResult = operationResult;
	}	
	 
	 
}
