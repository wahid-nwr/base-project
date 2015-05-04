package com.swiftcorp.portal.household.dto;

import java.util.Calendar;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class FamilyPlanningInfoDTO extends PersistentCapableDTO
{
	
	private String comments = null;
	private String causeOfChange = null;
	private String birthControlKit = null;
	
	private boolean iudCheck;
	private int daysOfPeriod = 0;
	private boolean stripCheck;
	
	private Calendar dateOfChange;
	private Calendar deadlineOfIUD;
	private Calendar iudDate;
	private Calendar deadlineOfNorplant;
	private Calendar norplantDate;
	private Calendar pillDate;
	
	public String getComments ( )
	{
		return comments;
	}
	
	public void setComments ( String comments )
	{
		this.comments = comments;
	}
	
	public String getCauseOfChange ( )
	{
		return causeOfChange;
	}
	
	public void setCauseOfChange ( String causeOfChange )
	{
		this.causeOfChange = causeOfChange;
	}
	
	public String getBirthControlKit ( )
	{
		return birthControlKit;
	}
	
	public void setBirthControlKit ( String birthControlKit )
	{
		this.birthControlKit = birthControlKit;
	}
	
	public boolean isIudCheck ( )
	{
		return iudCheck;
	}
	
	public void setIudCheck ( boolean iudCheck )
	{
		this.iudCheck = iudCheck;
	}
	
	public int getDaysOfPeriod ( )
	{
		return daysOfPeriod;
	}
	
	public void setDaysOfPeriod ( int daysOfPeriod )
	{
		this.daysOfPeriod = daysOfPeriod;
	}
	
	public boolean isStripCheck ( )
	{
		return stripCheck;
	}
	
	public void setStripCheck ( boolean stripCheck )
	{
		this.stripCheck = stripCheck;
	}
	
	public Calendar getDateOfChange ( )
	{
		return dateOfChange;
	}
	
	public void setDateOfChange ( Calendar dateOfChange )
	{
		this.dateOfChange = dateOfChange;
	}
	
	public Calendar getDeadlineOfIUD ( )
	{
		return deadlineOfIUD;
	}
	
	public void setDeadlineOfIUD ( Calendar deadlineOfIUD )
	{
		this.deadlineOfIUD = deadlineOfIUD;
	}
	
	public Calendar getIudDate ( )
	{
		return iudDate;
	}
	
	public void setIudDate ( Calendar iudDate )
	{
		this.iudDate = iudDate;
	}
	
	public Calendar getDeadlineOfNorplant ( )
	{
		return deadlineOfNorplant;
	}
	
	public void setDeadlineOfNorplant ( Calendar deadlineOfNorplant )
	{
		this.deadlineOfNorplant = deadlineOfNorplant;
	}
	
	public Calendar getNorplantDate ( )
	{
		return norplantDate;
	}
	
	public void setNorplantDate ( Calendar norplantDate )
	{
		this.norplantDate = norplantDate;
	}
	
	public Calendar getPillDate ( )
	{
		return pillDate;
	}
	
	public void setPillDate ( Calendar pillDate )
	{
		this.pillDate = pillDate;
	}
	
}
