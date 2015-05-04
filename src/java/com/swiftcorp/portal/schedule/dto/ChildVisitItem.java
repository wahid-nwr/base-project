/**
 * 
 */
package com.swiftcorp.portal.schedule.dto;

import java.util.Calendar;

/**
 * @author asraf
 * 
 */
public abstract class ChildVisitItem
{
	// child id
	protected String childBeneficiaryId;
	
	// date of birth
	protected Calendar dateOfBirth;
	
	public String getChildBeneficiaryId ( )
	{
		return childBeneficiaryId;
	}
	
	public void setChildBeneficiaryId ( String childBeneficiaryId )
	{
		this.childBeneficiaryId = childBeneficiaryId;
	}
	
	public Calendar getDateOfBirth ( )
	{
		return dateOfBirth;
	}
	
	public void setDateOfBirth ( Calendar dateOfBirth )
	{
		this.dateOfBirth = dateOfBirth;
	}
}
