package com.swiftcorp.portal.alert.dao;

import java.util.ArrayList;
import java.util.Calendar;

import com.swiftcorp.portal.alert.AlertSuccessResult;
import com.swiftcorp.portal.alert.dto.AlertDTO;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.user.dto.UserDTO;

public interface IAlertDAO
{
	public enum AlertSortBy
	{
		alertId, adminType, firstName, lastname
	};
	
	public enum AlertWhereCondition
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public AlertDTO get ( Long componentId ) throws SystemException;
	
	public AlertDTO get ( String unicodeCode ) throws SystemException;
	
	public AlertSuccessResult add ( AlertDTO alertDTO ) throws SystemException;
	
	public AlertSuccessResult modify ( AlertDTO alertDTO )
			throws SystemException;
	
	public AlertSuccessResult remove ( AlertDTO alertDTO )
			throws SystemException;
	
	public ArrayList<AlertDTO> getList ( ) throws SystemException;
	
	public ArrayList<AlertDTO> getList ( Long groupId, AlertSortBy sortby )
			throws SystemException;
	
	public ArrayList<AlertDTO> getAlertBySkId ( UserDTO userDTO, Calendar date )
			throws SystemException;
	
	
	
}
