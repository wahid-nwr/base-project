/*
 * @ (#) IPatientService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.patient.service;
import java.util.ArrayList;
import java.util.List;
import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.patient.dao.IPatientDAO.PatientSortBy;
import com.swiftcorp.portal.patient.dto.PatientDTO;
import com.swiftcorp.portal.patient.exception.PatientNotFoundException;
/**
 * @author mosa
 * @since Sep 8, 2008
 */
public interface IPatientService extends IGenericService
{
	public SearchOperationResult search(String searchQuery) throws SystemException, BusinessRuleViolationException;
	public List<PatientDTO> getList(Long groupId, PatientSortBy sortby)throws SystemException; 
	public List<PatientDTO> getList()throws SystemException; 
}
