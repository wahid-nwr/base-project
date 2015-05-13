package com.swiftcorp.portal.user.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class UserInfo  extends PersistentCapableDTO
{

	private int userType;
	
	public UserInfo ( )
	{
		
	}
	
	public UserInfo ( int userType )
	{
		this.userType = userType;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
}
