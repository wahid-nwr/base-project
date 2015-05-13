/*
 * @ (#) BusinessOperationResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common;

import java.io.Serializable;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class BusinessOperationResult implements Serializable
{
	public static final long serialVersionUID = 1955514104587786716L;
	public static final String SUCCESS = "success";
	public static final String FAILED = "failed";
	
	private String message;
	
	public String getMessage ( )
	{
		return message;
	}
	
	public void setMessage ( String message )
	{
		this.message = message;
	}
	
}
