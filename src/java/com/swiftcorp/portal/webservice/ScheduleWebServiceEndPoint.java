/**
 * 
 */
package com.swiftcorp.portal.webservice;

import java.util.Calendar;

import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.schedule.dto.ScheduledVisitDTO;
import com.swiftcorp.portal.schedule.service.UserScheduleFetcherService;

/**
 * @author asraful.haque
 * 
 */
@WebService(serviceName = "scheduleService")
public class ScheduleWebServiceEndPoint
{
	private final Log logger = LogFactory.getLog ( this.getClass () );
	// schedule fetcher service
	private UserScheduleFetcherService userScheduleFetcherService;
	
	public ScheduledVisitDTO getSchedules ( String userId, Calendar calendar )
	{
		logger.debug ( "Getting the Schedules...." );
		// get the list
		ScheduledVisitDTO scheduledVisitDTO = userScheduleFetcherService.getScheduleVisitDTO ( userId, calendar );
		
		// now return the list
		return scheduledVisitDTO;
	}
	
	public UserScheduleFetcherService getUserScheduleFetcherService ( )
	{
		return userScheduleFetcherService;
	}
	
	public void setUserScheduleFetcherService ( UserScheduleFetcherService userScheduleFetcherService )
	{
		this.userScheduleFetcherService = userScheduleFetcherService;
	}
}
