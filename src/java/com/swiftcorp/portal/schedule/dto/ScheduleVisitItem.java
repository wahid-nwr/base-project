package com.swiftcorp.portal.schedule.dto;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class ScheduleVisitItem extends PersistentCapableDTO
{
	private static final long serialVersionUID = 1L;
	
	// schedule dto for every item
	protected ScheduleDTO scheduleDTO;
	protected int dtoType;
	
	public ScheduleVisitItem ( )
	{
		
	}
	
	public int getDtoType ( )
	{
		return dtoType;
	}
	
	public void setDtoType ( int dtoType )
	{
		this.dtoType = dtoType;
	}
	
	public ScheduleDTO getScheduleDTO ( )
	{
		return scheduleDTO;
	}
	
	public void setScheduleDTO ( ScheduleDTO scheduleDTO )
	{
		this.scheduleDTO = scheduleDTO;
	}
}
