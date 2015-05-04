/**
 * 
 */
package com.swiftcorp.portal.household.dto;

/**
 * @author asraf
 * 
 */
public class CASHouseholdMemberDTO
{
	private String householdMemberId;
	private int householdMemberType;
	private int householdMemberOrder;
	
	public String getHouseholdMemberId ( )
	{
		return householdMemberId;
	}
	
	public void setHouseholdMemberId ( String householdMemberId )
	{
		this.householdMemberId = householdMemberId;
	}
	
	public int getHouseholdMemberType ( )
	{
		return householdMemberType;
	}
	
	public void setHouseholdMemberType ( int householdMemberType )
	{
		this.householdMemberType = householdMemberType;
	}
	
	public int getHouseholdMemberOrder ( )
	{
		return householdMemberOrder;
	}
	
	public void setHouseholdMemberOrder ( int householdMemberOrder )
	{
		this.householdMemberOrder = householdMemberOrder;
	}
}
