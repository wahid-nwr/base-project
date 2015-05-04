package com.swiftcorp.portal.schedule.dto;

import java.util.Calendar;

import com.swiftcorp.portal.beneficiary.dto.VisitDTO;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
import com.swiftcorp.portal.user.dto.UserDTO;

public class ScheduleDTO extends PersistentCapableDTO
{
	
	// user dto
	private UserDTO user;
	
	// beneficiary id
	private String visitItemId;
	
	// state of the schedule
	private int state;
	
	// schecudule by which role
	private String scheduleBy;
	
	// remarks if any
	private String remarks;
	
	// visit item type, it may be mother, child, or household
	private int visitItemType;
	// visit dto when schedule visited
	private VisitDTO visitDTO;
	
	private Calendar scheduleDate;
	
	private Calendar executionDate;
	
	public Calendar getScheduleDate ( )
	{
		return scheduleDate;
	}
	
	public void setScheduleDate ( Calendar scheduleDate )
	{
		this.scheduleDate = scheduleDate;
	}
	
	public Calendar getExecutionDate ( )
	{
		return executionDate;
	}
	
	public void setExecutionDate ( Calendar executionDate )
	{
		this.executionDate = executionDate;
	}
	
	public VisitDTO getVisitDTO ( )
	{
		return visitDTO;
	}
	
	public void setVisitDTO ( VisitDTO visitDTO )
	{
		this.visitDTO = visitDTO;
	}
	
	public UserDTO getUser ( )
	{
		return user;
	}
	
	public void setUser ( UserDTO user )
	{
		this.user = user;
	}
	
	public int getState ( )
	{
		return state;
	}
	
	public void setState ( int state )
	{
		this.state = state;
	}
	
	public String getScheduleBy ( )
	{
		return scheduleBy;
	}
	
	public void setScheduleBy ( String scheduleBy )
	{
		this.scheduleBy = scheduleBy;
	}
	
	public String getRemarks ( )
	{
		return remarks;
	}
	
	public void setRemarks ( String remarks )
	{
		this.remarks = remarks;
	}
	
	public int getVisitItemType ( )
	{
		return visitItemType;
	}
	
	public void setVisitItemType ( int visitItemType )
	{
		this.visitItemType = visitItemType;
	}
	
	public String getVisitItemId ( )
	{
		return visitItemId;
	}
	
	public void setVisitItemId ( String visitItemId )
	{
		this.visitItemId = visitItemId;
	}
	
}
