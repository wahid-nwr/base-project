/*
 * @ (#) LoginInfo.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.login.dto;

import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author mosa
 * @since Oct 15, 2008
 */
public class LoginDetailInfoDTO
{
	private UserDTO user;
	
	public void setUser ( UserDTO user )
	{
		this.user = user;
	}
	
	public UserDTO getUser ( )
	{
		return user;
	}
	
}
