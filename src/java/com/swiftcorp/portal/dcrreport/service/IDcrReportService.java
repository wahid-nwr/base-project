/*
 * @ (#) IDcrReportService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.dcrreport.service;
import java.util.ArrayList;
import java.util.List;
import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.dcrreport.dao.IDcrReportDAO.DcrReportSortBy;
import com.swiftcorp.portal.dcrreport.dto.DcrReportDTO;
import com.swiftcorp.portal.dcrreport.exception.DcrReportNotFoundException;
/**
 * @author mosa
 * @since Sep 8, 2008
 */
public interface IDcrReportService extends IGenericService
{
	public SearchOperationResult search(String searchQuery) throws SystemException, BusinessRuleViolationException;
	public List<DcrReportDTO> getList(Long groupId, DcrReportSortBy sortby)throws SystemException; 
	public List<DcrReportDTO> getList()throws SystemException; 
	public GenericDTO get ()
	throws SystemException, BusinessRuleViolationException;
}
