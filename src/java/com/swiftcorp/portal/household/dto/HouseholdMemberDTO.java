package com.swiftcorp.portal.household.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class HouseholdMemberDTO extends PersistentCapableDTO
{
	
	private String memberPicture = null;
	private String beneficiaryId = null;
	private String memberName = null;
	private String mobileNo = null;
	private String gender = null;
	private String husbandName = null;
	private String occupation = null;
	private String pregnancyCondition = null;
	private boolean otherInfo;
	private int memberAge = 0;
	
	private String householdId = null;
	
	private String memberType = null;
	
	private Set<HouseholdMemberVisitDTO> householdMemberVisitSet = new HashSet<HouseholdMemberVisitDTO> ();
	
	public HouseholdMemberVisitDTO getLatestMemberVisit ( )
	{
		
		// if there is one in the list just return it
		if ( this.householdMemberVisitSet.size () == 1 )
		{
			// get the iterator to return
			return householdMemberVisitSet.iterator ().next ();
		}
		
		// if it has some element
		else if ( this.householdMemberVisitSet.size () > 1 )
		{
			List<HouseholdMemberVisitDTO> sortedRecordList = new ArrayList<HouseholdMemberVisitDTO> ( this.householdMemberVisitSet );
			Collections.sort ( sortedRecordList );
			
			// now return the first element
			return sortedRecordList.get ( 0 );
		}
		
		// return nullO
		return null;
		
	}
	
	public String getHouseholdId ( )
	{
		return householdId;
	}
	
	public void setHouseholdId ( String householdId )
	{
		this.householdId = householdId;
	}
	
	public String getBeneficiaryId ( )
	{
		return beneficiaryId;
	}
	
	public void setBeneficiaryId ( String beneficiaryId )
	{
		this.beneficiaryId = beneficiaryId;
	}
	
	public String getMemberPicture ( )
	{
		return memberPicture;
	}
	
	public void setMemberPicture ( String memberPicture )
	{
		this.memberPicture = memberPicture;
	}
	
	public String getMemberName ( )
	{
		return memberName;
	}
	
	public void setMemberName ( String memberName )
	{
		this.memberName = memberName;
	}
	
	public String getMobileNo ( )
	{
		return mobileNo;
	}
	
	public void setMobileNo ( String mobileNo )
	{
		this.mobileNo = mobileNo;
	}
	
	public String getGender ( )
	{
		return gender;
	}
	
	public void setGender ( String gender )
	{
		this.gender = gender;
	}
	
	public String getHusbandName ( )
	{
		return husbandName;
	}
	
	public void setHusbandName ( String husbandName )
	{
		this.husbandName = husbandName;
	}
	
	public String getOccupation ( )
	{
		return occupation;
	}
	
	public void setOccupation ( String occupation )
	{
		this.occupation = occupation;
	}
	
	public String getPregnancyCondition ( )
	{
		return pregnancyCondition;
	}
	
	public void setPregnancyCondition ( String pregnancyCondition )
	{
		this.pregnancyCondition = pregnancyCondition;
	}
	
	public boolean isOtherInfo ( )
	{
		return otherInfo;
	}
	
	public void setOtherInfo ( boolean otherInfo )
	{
		this.otherInfo = otherInfo;
	}
	
	public int getMemberAge ( )
	{
		return memberAge;
	}
	
	public void setMemberAge ( int memberAge )
	{
		this.memberAge = memberAge;
	}
	
	public String getMemberType ( )
	{
		return memberType;
	}
	
	public void setMemberType ( String memberType )
	{
		this.memberType = memberType;
	}
	
	public Set<HouseholdMemberVisitDTO> getHouseholdMemberVisitSet ( )
	{
		return householdMemberVisitSet;
	}
	
	public void setHouseholdMemberVisitSet ( Set<HouseholdMemberVisitDTO> householdMemberVisitSet )
	{
		this.householdMemberVisitSet = householdMemberVisitSet;
	}
	
}
