/**
 * 
 */
package com.swiftcorp.portal.schedule.service;

import java.util.List;

import com.swiftcorp.portal.schedule.dto.ScheduleDTO;

/**
 * @author asraful.haque
 * 
 */
public class MhealthCalendarBasedScheduler extends AbstractCalendarBasedScheduler
{
	
	@Override
	public List<ScheduleDTO> schedule ( ScheduleDTO scheduleDTO )
	{
		// TODO: check the company calendar
		
		// TODO: check the user calendar if any
		
		return null;
	}
	
}
