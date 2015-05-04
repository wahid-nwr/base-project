package com.swiftcorp.portal.schedule.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.schedule.ScheduleSuccessResult;
import com.swiftcorp.portal.schedule.dto.ScheduleDTO;
import com.swiftcorp.portal.user.dto.UserDTO;

public interface IScheduleDAO
{
	public enum ScheduleSortBy
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public enum ScheduleWhereCondition
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public ScheduleDTO get ( Long componentId ) throws SystemException;
	
	public ScheduleDTO get ( String unicodeCode ) throws SystemException;
	
	public ScheduleSuccessResult add ( ScheduleDTO scheduleDTO )
			throws SystemException;
	
	public ScheduleSuccessResult modify ( ScheduleDTO scheduleDTO )
			throws SystemException;
	
	public ScheduleSuccessResult remove ( ScheduleDTO scheduleDTO )
			throws SystemException;
	
	public ArrayList<ScheduleDTO> getList ( ) throws SystemException;
	
	public ArrayList<ScheduleDTO> getList ( Long groupId, ScheduleSortBy sortby )
			throws SystemException;
	
	public List<ScheduleDTO> getSheduleListByDateAndState ( Calendar date, int state )
			throws SystemException;
	
	public List<ScheduleDTO> getSheduleDTOByDateAndVisitItemId ( Calendar date, String visitItemId )
			throws SystemException;
	
	public ScheduleSuccessResult addOrUpdate ( ScheduleDTO scheduleDTO )
			throws SystemException;
	
	public List<ScheduleDTO> getScheduleListByUserAndDate ( UserDTO user, Calendar scheduleDate )
			throws SystemException;
	
}
