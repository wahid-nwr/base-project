/**
 * 
 */
package com.swiftcorp.portal.schedule.service.mother;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.util.CalendarUtils;
import com.swiftcorp.portal.schedule.dto.ScheduleDTO;
import com.swiftcorp.portal.schedule.service.IScheduleService;
import com.swiftcorp.portal.schedule.service.MissedVisitScheduler;

/**
 * @author asraful.haque
 * 
 */
public class MotherSchedulingService
{
	// log
	private final Log logger = LogFactory.getLog ( this.getClass () );
	
	// schedule service
	private IScheduleService scheduleService;
	private MissedVisitScheduler missedVisitScheduler;
	
	/**
	 * 
	 */
	public void scheduleMissedVisit ( )
	{
		// get the today date
		Calendar now = Calendar.getInstance ();
		now.setTime ( new Date () );
		
		// now shift it to previous day
		Calendar previous = CalendarUtils.addDayToCalendar ( now, -1 );
		
		try
		{
			logger.info ( "Getting the missed Scedule" );
			// now check is there any schedule missed yesterday
			List<ScheduleDTO> missedScheduleList = scheduleService.getMissedScheduleListByDate ( previous );
			
			for ( ScheduleDTO scheduleDTO : missedScheduleList )
			{
				List<ScheduleDTO> reScheduledList = missedVisitScheduler.schedule ( scheduleDTO );
				
				// save the list
				this.scheduleService.saveScheduleList ( reScheduledList );
			}
		}
		catch (Exception ex)
		{
			logger.error ( "Error occured while scheduling" + ex );
		}
		
	}
	
	public IScheduleService getScheduleService ( )
	{
		return scheduleService;
	}
	
	public void setScheduleService ( IScheduleService scheduleService )
	{
		this.scheduleService = scheduleService;
	}
	
	public MissedVisitScheduler getMissedVisitScheduler ( )
	{
		return missedVisitScheduler;
	}
	
	public void setMissedVisitScheduler ( MissedVisitScheduler missedVisitScheduler )
	{
		this.missedVisitScheduler = missedVisitScheduler;
	}
}
