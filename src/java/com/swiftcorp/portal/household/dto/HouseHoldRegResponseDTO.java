/**
 * 
 */
package com.swiftcorp.portal.household.dto;

import java.util.ArrayList;
import java.util.Calendar;

import com.swiftcorp.portal.dto.ResponseDTO;

/**
 * @author asraful.haque
 * 
 */
public class HouseHoldRegResponseDTO extends ResponseDTO
{
	
	// household id and reg date
	private String householdId;
	private Calendar householdRegDate;
	
	ArrayList<CASHouseholdMemberDTO> householdMemberDTOList;
	
	// method to parse
	public String toXML ( )
	{
		String xmlString = super.toXML ();
		
		// return null for now
		return xmlString;
	}
	
	public String getHouseholdId ( )
	{
		return householdId;
	}
	
	public void setHouseholdId ( String householdId )
	{
		this.householdId = householdId;
	}
	
	public Calendar getHouseholdRegDate ( )
	{
		return householdRegDate;
	}
	
	public void setHouseholdRegDate ( Calendar householdRegDate )
	{
		this.householdRegDate = householdRegDate;
	}
	
	public ArrayList<CASHouseholdMemberDTO> getHouseholdMemberDTOList ( )
	{
		return householdMemberDTOList;
	}
	
	public void setHouseholdMemberDTOList ( ArrayList<CASHouseholdMemberDTO> householdMemberDTOList )
	{
		this.householdMemberDTOList = householdMemberDTOList;
	}
	
}
