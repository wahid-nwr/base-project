/*
 * @ (#) GeoSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.geo;
import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.geo.dto.GeoDTO;
/**
 * @author swift
 * @since mar 3, 2011
 */
public class GeoSuccessResult extends BusinessOperationResult
{
	private GeoDTO operationResult;
	public GeoDTO getOperationResult()
	{
		return operationResult;
	}
	public void setOperationResult(GeoDTO operationResult)
	{
		this.operationResult = operationResult;
	}	
	 
	 
}
