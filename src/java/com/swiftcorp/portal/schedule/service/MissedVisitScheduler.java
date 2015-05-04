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
public abstract class MissedVisitScheduler
{
	public abstract List<ScheduleDTO> schedule ( ScheduleDTO missedScheduleDTO );
}
