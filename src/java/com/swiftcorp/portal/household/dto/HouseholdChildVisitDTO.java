package com.swiftcorp.portal.household.dto;

import com.swiftcorp.portal.beneficiary.dto.VisitDTO;
import com.swiftcorp.portal.common.dto.DTOConstants;

public class HouseholdChildVisitDTO extends HouseholdMemberVisitDTO
{
	
	private VisitDTO visitDTO = new VisitDTO ();
	
	public HouseholdChildVisitDTO ( )
	{
		this.memberVisitType = DTOConstants.CHILD_MEMBERTYPE;
	}
	
	public int getMemberVisitType ( )
	{
		return memberVisitType;
	}
	
	public void setMemberVisitType ( int memberVisitType )
	{
		this.memberVisitType = memberVisitType;
	}
	
	public VisitDTO getVisitDTO ( )
	{
		return visitDTO;
	}
	
	public void setVisitDTO ( VisitDTO visitDTO )
	{
		this.visitDTO = visitDTO;
	}
	
}
