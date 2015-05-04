/**
 * 
 */
package com.swiftcorp.portal.schedule.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.schedule.dto.ScheduleMakerInfo;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author asraful.haque
 * 
 */
public class VisitSchedulingThread implements Runnable
{
	// scheduling service
	protected RoutineVisitScheduler routineVisitScheduler;
	
	// schedule maker info
	protected ScheduleMakerInfo scheduleMakerInfo;
	// user dto
	protected UserDTO user;
	private static final Log logger = LogFactory.getLog ( VisitSchedulingThread.class );
	
	public VisitSchedulingThread ( RoutineVisitScheduler routineVisitScheduler, ScheduleMakerInfo scheduleMakerInfo, UserDTO user )
	{
		this.routineVisitScheduler = routineVisitScheduler;
		this.scheduleMakerInfo = scheduleMakerInfo;
		this.user = user;
	}
	
	@Override
	public void run ( )
	{
		try
		{
			this.routineVisitScheduler.schedule ( scheduleMakerInfo, user );
		}
		catch(Exception e)
		{
			logger.error ( "Error in routine Visit Scheduler " + e );
		}
		
	}
	
	public RoutineVisitScheduler getRoutineVisitScheduler ( )
	{
		return routineVisitScheduler;
	}
	
	public void setRoutineVisitScheduler ( RoutineVisitScheduler routineVisitScheduler )
	{
		this.routineVisitScheduler = routineVisitScheduler;
	}
	
	public ScheduleMakerInfo getScheduleMakerInfo ( )
	{
		return scheduleMakerInfo;
	}
	
	public void setScheduleMakerInfo ( ScheduleMakerInfo scheduleMakerInfo )
	{
		this.scheduleMakerInfo = scheduleMakerInfo;
	}
	
	public UserDTO getUser ( )
	{
		return user;
	}
	
	public void setUser ( UserDTO user )
	{
		this.user = user;
	}
	
}
