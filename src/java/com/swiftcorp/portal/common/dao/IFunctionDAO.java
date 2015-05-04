package com.swiftcorp.portal.common.dao;

import java.util.ArrayList;

import com.swiftcorp.portal.common.FunctionSuccessResult;
import com.swiftcorp.portal.common.dto.FunctionDTO;
import com.swiftcorp.portal.common.exception.SystemException;

public interface IFunctionDAO
{
	public enum FunctionSortBy
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public enum FunctionWhereCondition
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public FunctionDTO get ( Long componentId ) throws SystemException;
	
	public FunctionDTO get ( String unicodeCode ) throws SystemException;
	
	public FunctionSuccessResult add ( FunctionDTO functionDTO )
			throws SystemException;
	
	public FunctionSuccessResult modify ( FunctionDTO functionDTO )
			throws SystemException;
	
	public FunctionSuccessResult remove ( FunctionDTO functionDTO )
			throws SystemException;
	
	public ArrayList<FunctionDTO> getList ( ) throws SystemException;
	
	public ArrayList<FunctionDTO> getList ( Long groupId, FunctionSortBy sortby )
			throws SystemException;
	
}
