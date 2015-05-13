package com.swiftcorp.portal.user.dao;

import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.user.UserSuccessResult;
import com.swiftcorp.portal.user.dto.UserDTO;

public interface IUserDAO
{
	public enum UserSortBy
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public enum UserWhereCondition
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public UserDTO get ( Long componentId ) throws SystemException;
	
	public UserDTO get ( String unicodeCode ) throws SystemException;
	
	public UserSuccessResult add ( UserDTO userDTO ) throws SystemException;
	
	public UserSuccessResult modify ( UserDTO userDTO ) throws SystemException;
	
	public UserSuccessResult remove ( UserDTO userDTO ) throws SystemException;
	
	public ArrayList<UserDTO> getList ( ) throws SystemException;
	
	public ArrayList<UserDTO> getList ( Long groupId, UserSortBy sortby )
			throws SystemException;
}
