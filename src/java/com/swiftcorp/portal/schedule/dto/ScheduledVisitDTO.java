package com.swiftcorp.portal.schedule.dto;

import java.util.ArrayList;

import com.swiftcorp.portal.common.dto.GenericDTO;

public class ScheduledVisitDTO extends GenericDTO
{
	
	// House hold list
	private ArrayList<HouseholdVisitItem> householdVisitItemList;
	
	// pregnant mother list
	private ArrayList<PregMotherVisitItem> pregMotherVisitItemList;
	
	// pnc mother list
	private ArrayList<PNCMotherVisitItem> pncMotherVisitItemList;
	
	public ArrayList<HouseholdVisitItem> getHouseholdVisitItemList ( )
	{
		return householdVisitItemList;
	}
	
	public void setHouseholdVisitItemList ( ArrayList<HouseholdVisitItem> householdVisitItemList )
	{
		this.householdVisitItemList = householdVisitItemList;
	}
	
	public ArrayList<PregMotherVisitItem> getPregMotherVisitItemList ( )
	{
		return pregMotherVisitItemList;
	}
	
	public void setPregMotherVisitItemList ( ArrayList<PregMotherVisitItem> pregMotherVisitItemList )
	{
		this.pregMotherVisitItemList = pregMotherVisitItemList;
	}
	
	public ArrayList<PNCMotherVisitItem> getPncMotherVisitItemList ( )
	{
		return pncMotherVisitItemList;
	}
	
	public void setPncMotherVisitItemList ( ArrayList<PNCMotherVisitItem> pncMotherVisitItemList )
	{
		this.pncMotherVisitItemList = pncMotherVisitItemList;
	}
	
}
