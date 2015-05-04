/**
 * 
 */
package com.swiftcorp.portal.common.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.timer.ScheduledTimerTask;

/**
 * @author asraful.haque
 * 
 */
public abstract class MhealthJobScheduler extends ScheduledTimerTask
{
	// log
	private final Log logger = LogFactory.getLog ( this.getClass () );
	
	// milis in a day
	public static long MILIS_IN_HOUR = 1000 * 60 * 60 * 60;
	
	// interval in hour
	protected int intervalHour;
	
	// time to start in 24 hour to start in format hh:mm
	protected String scheduleTime;
	
	@Override
	public long getDelay ( )
	{
		// get the hour and min
		
		DateFormat dateFormat = new SimpleDateFormat ( "HH:mm" );
		long delay = 1000;
		
		Date date;
		try
		{
			// get the hour and min in date
			date = dateFormat.parse ( scheduleTime );
			
			// now get the hour and min
			int scheduleHour = date.getHours ();
			int scheduleMin = date.getMinutes ();
			
			logger.info ( "Getting the hour and min is : " + scheduleHour + " : " + scheduleMin );
			// now get the schedule cale
			Calendar scheduleCal = Calendar.getInstance ();
			scheduleCal.setTime ( new Date () );
			scheduleCal.set ( Calendar.HOUR_OF_DAY, scheduleHour );
			scheduleCal.set ( Calendar.MINUTE, scheduleMin );
			
			// now get the current caledar time
			Calendar now = Calendar.getInstance ();
			now.setTime ( new Date () );
			
			// now check if the schedule time is befor the current time
			if ( scheduleCal.before ( now ) )
			{
				// now add the 1 day to the schedule ca
				scheduleCal.add ( Calendar.DAY_OF_MONTH, 1 );
			}
			
			// now get the difference
			delay = (scheduleCal.getTimeInMillis () - now.getTimeInMillis ());
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		logger.debug ( "Getting the delay : " + delay );
		
		// return the delay
		return delay;
	}
	
	@Override
	public long getPeriod ( )
	{
		// get the interval
		long period = this.intervalHour * MILIS_IN_HOUR;
		
		// return the period
		return period;
	}
	
	public int getIntervalHour ( )
	{
		return intervalHour;
	}
	
	public void setIntervalHour ( int intervalHour )
	{
		this.intervalHour = intervalHour;
	}
	
	public String getScheduleTime ( )
	{
		return scheduleTime;
	}
	
	public void setScheduleTime ( String scheduleTime )
	{
		this.scheduleTime = scheduleTime;
	}
	
}
