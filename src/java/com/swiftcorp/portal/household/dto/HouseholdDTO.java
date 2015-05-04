package com.swiftcorp.portal.household.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.swiftcorp.portal.beneficiary.dto.AbstractVisitRecord;
import com.swiftcorp.portal.geo.dto.GeoDTO;

public class HouseholdDTO extends AbstractVisitRecord
{
	@Deprecated
	// not used for now
	private String householdId = null;
	private String houseNo = null;
	private String chiefName = null;
	private String financialType = null;
	private String sanitationType = null;
	private boolean tubewell;
	private int totalMember = 0;
	private String isFamilyOrMess;
	private int isFamilyPresent;
	
	private Set<HouseholdMemberDTO> householdMemberSet = new HashSet<HouseholdMemberDTO> ();
	
	// added when geo location was implemented
	private GeoDTO branch;
	// ssid
	private String ssId;
	
	public Set<HouseholdMemberDTO> getHouseholdMemberSet ( )
	{
		return householdMemberSet;
	}
	
	public void setHouseholdMemberSet ( Set<HouseholdMemberDTO> householdMemberSet )
	{
		this.householdMemberSet = householdMemberSet;
	}
	
	public String getHouseholdId ( )
	{
		return householdId;
	}
	
	public void setHouseholdId ( String householdId )
	{
		this.householdId = householdId;
	}
	
	public String getHouseNo ( )
	{
		return houseNo;
	}
	
	public void setHouseNo ( String houseNo )
	{
		this.houseNo = houseNo;
	}
	
	public String getChiefName ( )
	{
		return chiefName;
	}
	
	public void setChiefName ( String chiefName )
	{
		this.chiefName = chiefName;
	}
	
	public String getFinancialType ( )
	{
		return financialType;
	}
	
	public void setFinancialType ( String financialType )
	{
		this.financialType = financialType;
	}
	
	public String getSanitationType ( )
	{
		return sanitationType;
	}
	
	public void setSanitationType ( String sanitationType )
	{
		this.sanitationType = sanitationType;
	}
	
	public boolean isTubewell ( )
	{
		return tubewell;
	}
	
	public void setTubewell ( boolean tubewell )
	{
		this.tubewell = tubewell;
	}
	
	public int getTotalMember ( )
	{
		return totalMember;
	}
	
	public void setTotalMember ( int totalMember )
	{
		this.totalMember = totalMember;
	}
	
	public HouseholdMemberDTO getCurrentHouseholdMember ( )
	{
		// if there is one in the list just return it
		if ( this.householdMemberSet.size () == 1 )
		{
			// get the iterator to return
			return householdMemberSet.iterator ().next ();
		}
		
		// if it has some element
		else if ( this.householdMemberSet.size () > 1 )
		{
			List<HouseholdMemberDTO> sortedRecordList = new ArrayList<HouseholdMemberDTO> ( this.householdMemberSet );
			// Collections.sort(sortedRecordList);
			
			// now return the first element
			return sortedRecordList.get ( 0 );
		}
		
		// return null
		return null;
	}

	public GeoDTO getBranch ( )
	{
		return branch;
	}

	public void setBranch ( GeoDTO branch )
	{
		this.branch = branch;
	}

	public String getSsId ( )
	{
		return ssId;
	}

	public void setSsId ( String ssId )
	{
		this.ssId = ssId;
	}

	
	public int getIsFamilyPresent ( )
	{
		return isFamilyPresent;
	}

	public void setIsFamilyPresent ( int isFamilyPresent )
	{
		this.isFamilyPresent = isFamilyPresent;
	}

	public String getIsFamilyOrMess ( )
	{
		return isFamilyOrMess;
	}

	public void setIsFamilyOrMess ( String isFamilyOrMess )
	{
		this.isFamilyOrMess = isFamilyOrMess;
	}
	
	
}
