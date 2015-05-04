/**
 * 
 */
package com.swiftcorp.portal.schedule.service;

import java.util.List;

import com.swiftcorp.portal.schedule.dto.ScheduleDTO;
import com.swiftcorp.portal.schedule.dto.ScheduleMakerInfo;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author asraful.haque
 * 
 */
public abstract class RoutineVisitScheduler
{
	public abstract List<ScheduleDTO> schedule ( ScheduleMakerInfo scheduleMakerInfo, UserDTO user );
}
