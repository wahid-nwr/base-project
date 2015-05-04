package com.swiftcorp.portal.role.dao;

import java.util.ArrayList;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.role.RoleSuccessResult;
import com.swiftcorp.portal.role.dto.RoleDTO;

public interface IRoleDAO
{
	public enum RoleSortBy
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public enum RoleWhereCondition
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public RoleDTO get ( Long componentId ) throws SystemException;
	
	public RoleDTO get ( String unicodeCode ) throws SystemException;
	
	public RoleSuccessResult add ( RoleDTO roleDTO ) throws SystemException;
	
	public RoleSuccessResult modify ( RoleDTO roleDTO ) throws SystemException;
	
	public RoleSuccessResult remove ( RoleDTO roleDTO ) throws SystemException;
	
	public ArrayList<RoleDTO> getList ( ) throws SystemException;
	
	public ArrayList<RoleDTO> getList ( Long groupId, RoleSortBy sortby )
			throws SystemException;
	
}
