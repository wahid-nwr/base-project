/**
 * 
 */
package com.swiftcorp.portal.common.search;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author asraful.haque
 * 
 */
public class SearchItemInfo
{
	// generic for all
	private String searchItemId;
	private int itemType;
	private Calendar regDate;
	private String householdId;
	private ArrayList<SearchVisitDTO> searchVisitDTOList;
	
	// for mother
	
	// Getters and setters
	public String getSearchItemId ( )
	{
		return searchItemId;
	}
	
	public void setSearchItemId ( String searchItemId )
	{
		this.searchItemId = searchItemId;
	}
	
	public int getItemType ( )
	{
		return itemType;
	}
	
	public void setItemType ( int itemType )
	{
		this.itemType = itemType;
	}
	
	public Calendar getRegDate ( )
	{
		return regDate;
	}
	
	public void setRegDate ( Calendar regDate )
	{
		this.regDate = regDate;
	}
	
	public String getHouseholdId ( )
	{
		return householdId;
	}
	
	public void setHouseholdId ( String householdId )
	{
		this.householdId = householdId;
	}
	
	public void setSearchVisitDTOList ( ArrayList<SearchVisitDTO> searchVisitDTOList )
	{
		this.searchVisitDTOList = searchVisitDTOList;
	}
	
	public ArrayList<SearchVisitDTO> getSearchVisitDTOList ( )
	{
		return searchVisitDTOList;
	}
	
}
