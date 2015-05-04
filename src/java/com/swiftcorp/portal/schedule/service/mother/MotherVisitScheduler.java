/**
 * 
 */
package com.swiftcorp.portal.schedule.service.mother;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.util.CalendarUtils;
import com.swiftcorp.portal.schedule.dto.MotherSchedulerInfo;
import com.swiftcorp.portal.schedule.dto.ScheduleDTO;
import com.swiftcorp.portal.schedule.dto.ScheduleMakerInfo;
import com.swiftcorp.portal.schedule.dto.SchedulingConstants;
import com.swiftcorp.portal.schedule.service.IScheduleService;
import com.swiftcorp.portal.schedule.service.RoutineVisitScheduler;
import com.swiftcorp.portal.user.dto.UserDTO;

/**
 * @author asraful.haque
 * 
 */
public class MotherVisitScheduler extends RoutineVisitScheduler
{
	// log
	private final Log logger = LogFactory.getLog ( this.getClass () );
	
	// schedule service
	private IScheduleService scheduleService;
	
	@Override
	public List<ScheduleDTO> schedule ( ScheduleMakerInfo scheduleMakerInfo, UserDTO user )
	{
		// TODO needs to check the user type, for now we do it for sk only
		// it is for mother so it is mother scheduling info
		MotherSchedulerInfo motherSchedulerInfo = (MotherSchedulerInfo) scheduleMakerInfo;
		
		// get the patient stage
		String patientStage = motherSchedulerInfo.getPatientStage ();
		
		// schedule list to return
		List<ScheduleDTO> scheduleList = null;
		
		// if it is predelivery
		if ( patientStage.equalsIgnoreCase ( SchedulingConstants.PRE_DELIVERY ) )
		{
			scheduleList = this.getPredeliveryScheduleList ( motherSchedulerInfo, user );
		}
		// if it is post delivery
		else if ( patientStage.equalsIgnoreCase ( SchedulingConstants.POST_DELIVERY ) )
		{
			scheduleList = this.getPostDeliveryScheduleList ( motherSchedulerInfo, user );
		}
		
		this.scheduleService.saveScheduleList ( scheduleList );
		
		// free resources
		motherSchedulerInfo = null;
		
		return scheduleList;
	}
	
	/**
	 * 
	 * @param motherSchedulerInfo
	 * @param user
	 * @return
	 */
	private List<ScheduleDTO> getPredeliveryScheduleList ( MotherSchedulerInfo motherSchedulerInfo, UserDTO user )
	{
		// get the list
		List<ScheduleDTO> scheduleList = new ArrayList<ScheduleDTO> ();
		
		// get the current visit date
		Calendar pregnancyIdentificationCal = motherSchedulerInfo.getPregIdentificationDate ();
		Date pregnancyIdentificationDate = pregnancyIdentificationCal.getTime ();
		
		int visitInterval = 0;
		
		logger.info ( "Now generating schedule for pregnant woman" );
		
		for ( int i = 1; i <= SchedulingConstants.PREDELIVERY_NUMBER_OF_VISIT; i++ )
		{
			// get the visit interval
			visitInterval = i * SchedulingConstants.PREDELIVERY_VISIT_INTERVAL;
			
			// make a new calendar
			Calendar nextVisitCalendar = Calendar.getInstance ();
			
			nextVisitCalendar.setTime ( pregnancyIdentificationDate );
			// now as it is the predelivery stage, visit will be 30 days after
			// this visit
			nextVisitCalendar = CalendarUtils.addDayToCalendar ( nextVisitCalendar, visitInterval );
			
			// make the schedule dto
			ScheduleDTO scheduleDTO = this.getScheduleDTOFromMotherInfo ( motherSchedulerInfo, user );
			scheduleDTO.setScheduleDate ( nextVisitCalendar );
			
			// now add this schedule dto to the list
			scheduleList.add ( scheduleDTO );
		}
		
		return scheduleList;
	}
	
	private List<ScheduleDTO> getPostDeliveryScheduleList ( MotherSchedulerInfo motherSchedulerInfo, UserDTO user )
	{
		// get the list
		List<ScheduleDTO> scheduleList = new ArrayList<ScheduleDTO> ();
		
		logger.info ( "Now generating schedule for post delivery woman" );
		// now get the second to fifth schedule, as the first schedule will be
		// done automatically
		ScheduleDTO secondScheduleDTO = this.getScheduleDTOForPostDelivery ( motherSchedulerInfo, user, SchedulingConstants.POST_DELIVERY_2ND_VISIT_DAY_INTERVAL );
		ScheduleDTO thirdScheduleDTO = this.getScheduleDTOForPostDelivery ( motherSchedulerInfo, user, SchedulingConstants.POST_DELIVERY_3RD_VISIT_DAY_INTERVAL );
		ScheduleDTO fourthScheduleDTO = this.getScheduleDTOForPostDelivery ( motherSchedulerInfo, user, SchedulingConstants.POST_DELIVERY_4TH_VISIT_DAY_INTERVAL );
		ScheduleDTO fifthScheduleDTO = this.getScheduleDTOForPostDelivery ( motherSchedulerInfo, user, SchedulingConstants.POST_DELIVERY_5TH_VISIT_DAY_INTERVAL );
		
		// add it to the list
		scheduleList.add ( secondScheduleDTO );
		scheduleList.add ( thirdScheduleDTO );
		scheduleList.add ( fourthScheduleDTO );
		scheduleList.add ( fifthScheduleDTO );
		
		// return the list
		return scheduleList;
	}
	
	private ScheduleDTO getScheduleDTOForPostDelivery ( MotherSchedulerInfo motherSchedulerInfo, UserDTO user, int dayToAdd )
	{
		// get the current visit date
		Calendar deliveryDate = motherSchedulerInfo.getDeliveryDate ();
		
		// now get the second schedule, as the first schedule will be done
		// automatically
		ScheduleDTO scheduleDTO = this.getScheduleDTOFromMotherInfo ( motherSchedulerInfo, user );
		// now add the day to delivery date for the second visit
		Calendar nextVisitDate = CalendarUtils.addDayToCalendar ( deliveryDate, dayToAdd );
		scheduleDTO.setScheduleDate ( nextVisitDate );
		
		// return dto
		return scheduleDTO;
		
	}
	
	private ScheduleDTO getScheduleDTOFromMotherInfo ( MotherSchedulerInfo motherSchedulerInfo, UserDTO user )
	{
		// make the schedule dto
		ScheduleDTO scheduleDTO = new ScheduleDTO ();
		
		int visitItemType = motherSchedulerInfo.getVisitItemType ();
		// get the current visit date
		// Calendar lastVisitDate = motherSchedulerInfo.getLastVisitDate ();
		// get the beneficiary id
		String beneficiaryId = motherSchedulerInfo.getVisitItemId ();
		
		// now add the next date and last date
		scheduleDTO.setVisitItemId ( beneficiaryId );
		scheduleDTO.setScheduleBy ( SchedulingConstants.SCHEDULE_BY_SYSTEM );
		scheduleDTO.setVisitItemType ( visitItemType );
		
		// set the user id
		scheduleDTO.setUser ( user );
		
		return scheduleDTO;
	}
	
	public IScheduleService getScheduleService ( )
	{
		return scheduleService;
	}
	
	public void setScheduleService ( IScheduleService scheduleService )
	{
		this.scheduleService = scheduleService;
	}
}
