/*
 * @ (#) IGeneralprofileService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.generalprofile.service;
import java.util.ArrayList;
import java.util.List;
import com.swiftcorp.portal.generalprofile.exception.GeneralprofileNotFoundException;
import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.login.dto.LoginDTO;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.generalprofile.dao.IGeneralprofileDAO.GeneralprofileSortBy;
import com.swiftcorp.portal.generalprofile.dto.GeneralprofileDTO;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IGeneralprofileService extends IGenericService
{
	public SearchOperationResult search(String searchQuery) throws SystemException, BusinessRuleViolationException;
	public List<GeneralprofileDTO> getList(Long groupId, GeneralprofileSortBy sortby)throws SystemException; 
	public List<GeneralprofileDTO> getList()throws SystemException; 
}
