/*
 * @ (#) LoginDTO.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.login.dto;

import com.swiftcorp.portal.common.dto.GenericDTO;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class LoginDTO extends GenericDTO
{
	private String userId;
	
	private String password;
	
	public String getPassword ( )
	{
		return password;
	}
	
	public void setPassword ( String password )
	{
		this.password = password;
	}
	
	public String getUserId ( )
	{
		return userId;
	}
	
	public void setUserId ( String userId )
	{
		this.userId = userId;
	}
}
