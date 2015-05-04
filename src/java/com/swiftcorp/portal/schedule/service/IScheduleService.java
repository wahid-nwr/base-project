/*
 * @ (#) IScheduleService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.schedule.service;

import java.util.Calendar;
import java.util.List;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.schedule.dao.IScheduleDAO.ScheduleSortBy;
import com.swiftcorp.portal.schedule.dto.ScheduleDTO;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public interface IScheduleService extends IGenericService
{
	public SearchOperationResult search ( String searchQuery )
			throws SystemException, BusinessRuleViolationException;
	
	public List<ScheduleDTO> getList ( Long groupId, ScheduleSortBy sortby )
			throws SystemException;
	
	public List<ScheduleDTO> getList ( ) throws SystemException;
	
	public List<ScheduleDTO> getMissedScheduleListByDate ( Calendar date );
	
	public BusinessOperationResult addOrUpdate ( GenericDTO genericDTO )
			throws SystemException, BusinessRuleViolationException;
	
	public void saveScheduleList ( List<ScheduleDTO> scheduledList );
	
	public List<ScheduleDTO> getScheduleListByUserAndDate ( UserDTO user, Calendar scheduleDate )
			throws SystemException;
}
