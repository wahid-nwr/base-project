/**
 * 
 */
package com.swiftcorp.portal.schedule.dto;

import java.util.ArrayList;

/**
 * @author asraf
 * 
 */
public class HouseholdVisitItem extends ScheduleVisitItem
{
	// hh id
	private String householdId;
	
	// mother list
	private ArrayList<HHMotherVisitItem> hhMotherList;
	
	// child list
	private ArrayList<HHChildVisitItem> hhChildList;
	
	public String getHouseholdId ( )
	{
		return householdId;
	}
	
	public void setHouseholdId ( String householdId )
	{
		this.householdId = householdId;
	}
	
	public ArrayList<HHMotherVisitItem> getHhMotherList ( )
	{
		return hhMotherList;
	}
	
	public void setHhMotherList ( ArrayList<HHMotherVisitItem> hhMotherList )
	{
		this.hhMotherList = hhMotherList;
	}
	
	public ArrayList<HHChildVisitItem> getHhChildList ( )
	{
		return hhChildList;
	}
	
	public void setHhChildList ( ArrayList<HHChildVisitItem> hhChildList )
	{
		this.hhChildList = hhChildList;
	}
	
}
