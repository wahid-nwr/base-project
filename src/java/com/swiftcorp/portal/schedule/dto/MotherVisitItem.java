/**
 * 
 */
package com.swiftcorp.portal.schedule.dto;

import java.util.Calendar;

/**
 * @author asraf
 * 
 */
public abstract class MotherVisitItem extends ScheduleVisitItem
{
	// ben id
	protected String beneficiaryId;
	
	// lmp
	protected Calendar lmp;
	
	public String getBeneficiaryId ( )
	{
		return beneficiaryId;
	}
	
	public void setBeneficiaryId ( String beneficiaryId )
	{
		this.beneficiaryId = beneficiaryId;
	}
	
	public Calendar getLmp ( )
	{
		return lmp;
	}
	
	public void setLmp ( Calendar lmp )
	{
		this.lmp = lmp;
	}
	
}
