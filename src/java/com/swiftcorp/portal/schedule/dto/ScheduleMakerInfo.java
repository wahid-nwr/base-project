/**
 * 
 */
package com.swiftcorp.portal.schedule.dto;

import java.util.Calendar;

/**
 * @author asraful.haque
 * 
 */
public abstract class ScheduleMakerInfo
{
	// visit item it, this may be mother beneficiary id, houshold id or child id
	protected String visitItemId;
	
	// visit item type
	protected int visitItemType;
	
	// last visit date
	protected Calendar lastVisitDate;
	
	public Calendar getLastVisitDate ( )
	{
		return lastVisitDate;
	}
	
	public void setLastVisitDate ( Calendar lastVisitDate )
	{
		this.lastVisitDate = lastVisitDate;
	}
	
	public String getVisitItemId ( )
	{
		return visitItemId;
	}
	
	public void setVisitItemId ( String visitItemId )
	{
		this.visitItemId = visitItemId;
	}
	
	public int getVisitItemType ( )
	{
		return visitItemType;
	}
	
	public void setVisitItemType ( int visitItemType )
	{
		this.visitItemType = visitItemType;
	}
	
}
