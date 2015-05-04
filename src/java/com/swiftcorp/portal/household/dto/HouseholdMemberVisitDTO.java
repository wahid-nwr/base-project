package com.swiftcorp.portal.household.dto;

import com.swiftcorp.portal.beneficiary.dto.AbstractVisitRecord;

public class HouseholdMemberVisitDTO extends AbstractVisitRecord
{
	// member visit type
	protected int memberVisitType = 0;
	// household member id
	protected long householdMemberId = 0;
	
	public HouseholdMemberVisitDTO ( )
	{
		
	}
	
	public HouseholdMemberVisitDTO ( int memberVisitType )
	{
		this.memberVisitType = memberVisitType;
	}
	
	public int getMemberVisitType ( )
	{
		return memberVisitType;
	}
	
	public void setMemberVisitType ( int memberVisitType )
	{
		this.memberVisitType = memberVisitType;
	}
	
	public long getHouseholdMemberId ( )
	{
		return householdMemberId;
	}
	
	public void setHouseholdMemberId ( long householdMemberId )
	{
		this.householdMemberId = householdMemberId;
	}
	
}
