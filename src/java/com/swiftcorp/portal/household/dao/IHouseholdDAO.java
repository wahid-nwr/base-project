package com.swiftcorp.portal.household.dao;

import java.util.ArrayList;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.household.FamilyPlanningInfoSuccessResult;
import com.swiftcorp.portal.household.HouseholdMemberSuccessResult;
import com.swiftcorp.portal.household.HouseholdMemberVisitSuccessResult;
import com.swiftcorp.portal.household.HouseholdSuccessResult;
import com.swiftcorp.portal.household.dto.FamilyPlanningInfoDTO;
import com.swiftcorp.portal.household.dto.HouseholdDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberVisitDTO;

public interface IHouseholdDAO
{
	public enum HouseholdSortBy
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public enum HouseholdWhereCondition
	{
		uniqueCode, adminType, firstName, lastname
	};
	
	public HouseholdDTO get ( Long componentId ) throws SystemException;
	
	public HouseholdMemberDTO getHouseholdMemberByMemberId ( String componentId )
			throws SystemException;
	
	public HouseholdDTO get ( String householdId ) throws SystemException;
	
	public HouseholdSuccessResult add ( HouseholdDTO householdDTO )
			throws SystemException;
	
	public HouseholdSuccessResult modify ( HouseholdDTO householdDTO )
			throws SystemException;
	
	public HouseholdSuccessResult remove ( HouseholdDTO householdDTO )
			throws SystemException;
	
	public ArrayList<HouseholdDTO> getList ( ) throws SystemException;
	
	public ArrayList<HouseholdDTO> getList ( Long groupId, HouseholdSortBy sortby )
			throws SystemException;
	
	public HouseholdMemberSuccessResult addHouseholdMember ( HouseholdMemberDTO householdMemberDTO )
			throws SystemException;
	
	public HouseholdMemberSuccessResult modifyHouseholdMember ( HouseholdMemberDTO householdMemberDTO )
			throws SystemException;
	
	public FamilyPlanningInfoSuccessResult addFamilyPlanningInfo ( FamilyPlanningInfoDTO familyPlanningInfoDTO )
			throws SystemException;
	
	public FamilyPlanningInfoSuccessResult modifyFamilyPlanningInfo ( FamilyPlanningInfoDTO familyPlanningInfoDTO )
			throws SystemException;
	
	public HouseholdMemberVisitSuccessResult addHouseholdMemberVisit ( HouseholdMemberVisitDTO householdMemberVisitDTO )
			throws SystemException;
	
	public HouseholdMemberVisitSuccessResult modifyHouseholdMemberVisit ( HouseholdMemberVisitDTO householdMemberVisitDTO )
			throws SystemException;
	
	public HouseholdDTO getHouseholdByHouseholdId ( String householdId )
			throws SystemException;
	
}
