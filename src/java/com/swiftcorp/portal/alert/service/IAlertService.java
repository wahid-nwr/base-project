/*
 * @ (#) IAlertService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.alert.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.swiftcorp.portal.alert.dao.IAlertDAO.AlertSortBy;
import com.swiftcorp.portal.alert.dto.AlertDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public interface IAlertService extends IGenericService
{
	public SearchOperationResult search ( String searchQuery )
			throws SystemException, BusinessRuleViolationException;
	
	public List<AlertDTO> getList ( Long groupId, AlertSortBy sortby )
			throws SystemException;
	
	public List<AlertDTO> getList ( ) throws SystemException;
	
	public ArrayList<AlertDTO> generateAlertBySkId ( String skId, Calendar date )
			throws SystemException;
	
	public void saveAlertList ( List<AlertDTO> alertList );
	
}
