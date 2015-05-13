/*
 * @ (#) ItemSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.item;
import com.swiftcorp.portal.item.dto.ItemDTO;
import com.swiftcorp.portal.common.BusinessOperationResult;
/**
 * @author swift
 * @since mar 3, 2011
 */
public class ItemSuccessResult extends BusinessOperationResult
{
	private ItemDTO operationResult;
	public ItemDTO getOperationResult()
	{
		return operationResult;
	}
	public void setOperationResult(ItemDTO operationResult)
	{
		this.operationResult = operationResult;
	}	
	 
	 
}
