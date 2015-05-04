/**
 * 
 */
package com.swiftcorp.portal.schedule.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.swiftcorp.portal.common.util.CalendarUtils;
import com.swiftcorp.portal.schedule.dto.HouseholdSchedulerInfo;
import com.swiftcorp.portal.schedule.dto.ScheduleDTO;
import com.swiftcorp.portal.schedule.dto.ScheduleMakerInfo;
import com.swiftcorp.portal.schedule.dto.SchedulingConstants;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author asraful.haque
 * 
 */
public class HouseHoldVisitScheduler extends RoutineVisitScheduler
{
	
	@Override
	public List<ScheduleDTO> schedule ( ScheduleMakerInfo scheduleMakerInfo, UserDTO user )
	{
		// get the scheduler info
		HouseholdSchedulerInfo householdSchedulerInfo = (HouseholdSchedulerInfo) scheduleMakerInfo;
		
		// get the reg date
		Calendar hhRegDate = householdSchedulerInfo.getHouseholdRegDate ();
		
		// make the schedule dto
		ScheduleDTO scheduleDTO = new ScheduleDTO ();
		
		// get the item type
		int visitItemType = householdSchedulerInfo.getVisitItemType ();
		
		// visit item id, in this case household id
		String visitItemId = householdSchedulerInfo.getVisitItemId ();
		
		// now add the next date and last date
		scheduleDTO.setVisitItemId ( visitItemId );
		scheduleDTO.setScheduleBy ( SchedulingConstants.SCHEDULE_BY_SYSTEM );
		scheduleDTO.setVisitItemType ( visitItemType );
		
		// set the user
		scheduleDTO.setUser ( user );
		
		// now get the schedule
		Calendar nextVisitCalendar = CalendarUtils.addDayToCalendar ( hhRegDate, SchedulingConstants.HOUSEHOLD_VISIT_INTERVAL );
		scheduleDTO.setScheduleDate ( nextVisitCalendar );
		
		// make the list
		List<ScheduleDTO> scheduleList = new ArrayList<ScheduleDTO> ();
		scheduleList.add ( scheduleDTO );
		
		// return the schedule list
		return scheduleList;
	}
	
}
