/**
 * 
 */
package com.swiftcorp.portal.schedule.dto;

import java.util.Calendar;

/**
 * @author asraf
 * 
 */
public class HouseholdSchedulerInfo extends ScheduleMakerInfo
{
	public HouseholdSchedulerInfo ( int visitItemType )
	{
		this.visitItemType = SchedulingConstants.HOUSEHOLD;
	}
	
	// reg date
	private Calendar householdRegDate;
	
	public Calendar getHouseholdRegDate ( )
	{
		return householdRegDate;
	}
	
	public void setHouseholdRegDate ( Calendar householdRegDate )
	{
		this.householdRegDate = householdRegDate;
	}
}
