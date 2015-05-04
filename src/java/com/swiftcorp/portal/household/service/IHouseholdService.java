/*
 * @ (#) IHouseholdService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.household.service;

import java.sql.SQLException;
import java.util.List;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.common.dto.GenericDTO;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.SearchOperationResult;
import com.swiftcorp.portal.common.service.IGenericService;
import com.swiftcorp.portal.household.dao.IHouseholdDAO.HouseholdSortBy;
import com.swiftcorp.portal.household.dto.HouseholdDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberDTO;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public interface IHouseholdService extends IGenericService
{
	public SearchOperationResult search ( String searchQuery )
			throws SystemException, BusinessRuleViolationException;
	
	public List<HouseholdDTO> getList ( Long groupId, HouseholdSortBy sortby )
			throws SystemException;
	
	public List<HouseholdDTO> getList ( ) throws SystemException;
	
	public BusinessOperationResult addFamilyPlanningInfo ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException;
	
	public BusinessOperationResult modifyFamilyPlanningInfo ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException,
			SQLException, Exception;
	
	public BusinessOperationResult addHouseholdMember ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException;
	
	public BusinessOperationResult modifyHouseholdMember ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException,
			SQLException, Exception;
	
	public BusinessOperationResult addHouseholdMemberVisit ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException;
	
	public BusinessOperationResult modifyHouseholdMemberVisit ( GenericDTO theDTO )
			throws SystemException, BusinessRuleViolationException,
			SQLException, Exception;
	
	public HouseholdMemberDTO getHouseholdMemberByMemberId ( String beneficiaryId )
			throws SystemException, BusinessRuleViolationException,
			SQLException, Exception;
	
	public HouseholdDTO getHouseholeByHouseholdId ( String householdId )
			throws SystemException;
	
}
