/**
 * 
 */
package com.swiftcorp.portal.schedule.service;

import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.schedule.service.mother.MotherSchedulingService;

/**
 * @author asraful.haque
 * 
 */
public class SchedulerTimerTask extends TimerTask
{
	// log
	private final Log logger = LogFactory.getLog ( this.getClass () );
	
	// scheduling service for mother
	private MotherSchedulingService motherSchedulingService;
	
	@Override
	public void run ( )
	{
		// log message
		logger.debug ( "Scheduler started for rescheduling " );
		
		// now schedule for mother
		this.motherSchedulingService.scheduleMissedVisit ();
		
	}
	
	public MotherSchedulingService getMotherSchedulingService ( )
	{
		return motherSchedulingService;
	}
	
	public void setMotherSchedulingService ( MotherSchedulingService motherSchedulingService )
	{
		this.motherSchedulingService = motherSchedulingService;
	}
	
}
