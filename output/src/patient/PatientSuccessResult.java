/*
 * @ (#) PatientSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.patient;
import com.swiftcorp.portal.patient.dto.PatientDTO;
import com.swiftcorp.portal.common.BusinessOperationResult;
/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class PatientSuccessResult extends BusinessOperationResult
{
	private PatientDTO operationResult;
	public PatientDTO getOperationResult()
	{
		return operationResult;
	}
	public void setOperationResult(PatientDTO operationResult)
	{
		this.operationResult = operationResult;
	}	
	 
	 
}
