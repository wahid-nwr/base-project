package com.swiftcorp.portal.household.dto;

import java.util.Set;

import com.swiftcorp.portal.beneficiary.dto.VisitDTO;

public class HouseholdMotherVisitDTO extends HouseholdMemberVisitDTO
{
	
	private String causeOfAbsence = null;
	private int presenceOfMother;
	private int newRegistration;
	private int totalChildren = 0;
	private int ageOfYoungerChild = 0;
	private int memberVisitType = 0;
	Set<FamilyPlanningInfoDTO> familyPlanningInfoDTOSet;
	
	public Set<FamilyPlanningInfoDTO> getFamilyPlanningInfoDTOSet ( )
	{
		return familyPlanningInfoDTOSet;
	}
	
	public void setFamilyPlanningInfoDTOSet ( Set<FamilyPlanningInfoDTO> familyPlanningInfoDTOSet )
	{
		this.familyPlanningInfoDTOSet = familyPlanningInfoDTOSet;
	}
	
	private VisitDTO visitDTO = new VisitDTO ();
	
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
	
	public String getCauseOfAbsence ( )
	{
		return causeOfAbsence;
	}
	
	public void setCauseOfAbsence ( String causeOfAbsence )
	{
		this.causeOfAbsence = causeOfAbsence;
	}
	
	public int getTotalChildren ( )
	{
		return totalChildren;
	}
	
	public void setTotalChildren ( int totalChildren )
	{
		this.totalChildren = totalChildren;
	}
	
	public int getAgeOfYoungerChild ( )
	{
		return ageOfYoungerChild;
	}
	
	public void setAgeOfYoungerChild ( int ageOfYoungerChild )
	{
		this.ageOfYoungerChild = ageOfYoungerChild;
	}
	
	public int getPresenceOfMother ( )
	{
		return presenceOfMother;
	}
	
	public void setPresenceOfMother ( int presenceOfMother )
	{
		this.presenceOfMother = presenceOfMother;
	}
	
	public int getNewRegistration ( )
	{
		return newRegistration;
	}
	
	public void setNewRegistration ( int newRegistration )
	{
		this.newRegistration = newRegistration;
	}
	
}
