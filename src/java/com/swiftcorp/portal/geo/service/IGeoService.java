/*
 * @ (#) IGeoService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.geo.service;
import java.util.List;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.geo.dao.IGeoDAO.GeoSortBy;
import com.swiftcorp.portal.geo.dto.GeoDTO;
/**
 * @author swift
 * @since mar 3, 2011
 */
public interface IGeoService extends IGenericService
{
	public SearchOperationResult search(String searchQuery) throws SystemException, BusinessRuleViolationException;
	public List<GeoDTO> getList(Long groupId, GeoSortBy sortby)throws SystemException; 
	public List<GeoDTO> getList()throws SystemException;	
	public List<GeoDTO> getCCList()throws SystemException;
	public List<GeoDTO> getBranchList() throws SystemException;
	public List<GeoDTO> getRegionList() throws SystemException;
}
