/**
 * 
 */
package com.swiftcorp.portal.schedule.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.schedule.dto.HouseholdVisitItem;
import com.swiftcorp.portal.schedule.dto.PNCMotherVisitItem;
import com.swiftcorp.portal.schedule.dto.PregMotherVisitItem;
import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.household.dto.HouseholdDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberDTO;
import com.swiftcorp.portal.household.service.IHouseholdService;
import com.swiftcorp.portal.schedule.dto.HHChildVisitItem;
import com.swiftcorp.portal.schedule.dto.HHMotherVisitItem;
import com.swiftcorp.portal.schedule.dto.ScheduleDTO;
import com.swiftcorp.portal.schedule.dto.ScheduledVisitDTO;
import com.swiftcorp.portal.schedule.dto.SchedulingConstants;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.user.service.IUserService;

/**
 * @author asraful.haque
 * 
 */
public class UserScheduleFetcherService
{
	private final Log logger = LogFactory.getLog ( this.getClass () );
	
	// user service
	private IUserService userService;
	
	// service to get the schedule
	private IScheduleService scheduleService;
	
	
	
	// house hold service
	private IHouseholdService householdService;
	
	public ScheduledVisitDTO getScheduleVisitDTO ( String userId, Calendar scheduleDate )
	{
		// schedule visit list to return
		// ArrayList<ScheduledVisitDTO> scheduleVisitList = new
		// ArrayList<ScheduledVisitDTO> ();
		ScheduledVisitDTO scheduledVisitDTO = new ScheduledVisitDTO ();
		
		// House hold list
		ArrayList<HouseholdVisitItem> householdVisitItemList = new ArrayList<HouseholdVisitItem> ();
		
		// pregnant mother list
		ArrayList<PregMotherVisitItem> pregMotherVisitItemList = new ArrayList<PregMotherVisitItem> ();
		
		// pnc mother list
		ArrayList<PNCMotherVisitItem> pncMotherVisitItemList = new ArrayList<PNCMotherVisitItem> ();
		
		// get the user dto first
		try
		{
			UserDTO user = (UserDTO) this.userService.get ( userId );
			List<ScheduleDTO> scheduleList = this.scheduleService.getScheduleListByUserAndDate ( user, scheduleDate );
			logger.debug ( userId + "Has schedules:: " + scheduleList.size () );
			
			// iterate the list
			for ( ScheduleDTO scheduleDTO : scheduleList )
			{
				// get the visit item type first
				int visitItemType = scheduleDTO.getVisitItemType ();
				
				// get the visit item id
				String visitItemId = scheduleDTO.getVisitItemId ();
				
				switch ( visitItemType )
				{
					case SchedulingConstants.PREGNANT_MOTHER:
						logger.debug ( "Getting the preg mother Visit item" );
						// get the visit item
						//PregMotherVisitItem pregMotherVisitItem = this.getScheduleVisitItemForPregMother ( scheduleDTO );
						// add it to list
					//	pregMotherVisitItemList.add ( pregMotherVisitItem );
						break;
					/*case SchedulingConstants.POSTDELIVERY_MOTHER:
						logger.debug ( "Getting the pnc mother Visit item" );
						PNCMotherVisitItem pncMotherVisitItem = this.getScheduleVisitItemForPNCMother ( scheduleDTO );
						pncMotherVisitItemList.add ( pncMotherVisitItem );
						break;
					case SchedulingConstants.HOUSEHOLD:
						logger.debug ( "Getting the Household Visit item" );
						HouseholdVisitItem householdVisitItem = this.getHouseholdVisitItem ( scheduleDTO );
						householdVisitItemList.add ( householdVisitItem );
						break;*/
					default:
						break;
				}
				
			}
			
			// now add the list to the schedule visit
			scheduledVisitDTO.setHouseholdVisitItemList ( householdVisitItemList );
			scheduledVisitDTO.setPncMotherVisitItemList ( pncMotherVisitItemList );
			scheduledVisitDTO.setPregMotherVisitItemList ( pregMotherVisitItemList );
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		
		// return the list
		return scheduledVisitDTO;
	}
	
	
	
	private HouseholdVisitItem getHouseholdVisitItem ( ScheduleDTO scheduleDTO )
	{
		HouseholdVisitItem householdVisitItem = new HouseholdVisitItem ();
		
		ArrayList<HHMotherVisitItem> hhMotherVisitItemList = new ArrayList<HHMotherVisitItem> ();
		// child list
		ArrayList<HHChildVisitItem> hhChildList = new ArrayList<HHChildVisitItem> ();
		
		// get the house hold id
		String householdId = scheduleDTO.getVisitItemId ();
		
		try
		{
			// get the household by id
			HouseholdDTO householdDTO = this.householdService.getHouseholeByHouseholdId ( householdId );
			
			// get the member list
			Set<HouseholdMemberDTO> householdMemberSet = householdDTO.getHouseholdMemberSet ();
			
			for ( HouseholdMemberDTO householdMemberDTO : householdMemberSet )
			{
				// get the member type
				String memberType = householdMemberDTO.getMemberType ();
				// now get the mother
				if ( memberType.equalsIgnoreCase ( DTOConstants.HOUSEHOLD_MOTHER_VISITTYPE ) )
				{
					// TODO: for now do nothing need to get the lmp
					/*
					 * HouseholdMotherVisitDTO householdMotherVisitDTO =
					 * (HouseholdMotherVisitDTO)
					 * householdMemberDTO.getLatestMemberVisit (); if (
					 * householdMotherVisitDTO != null ) {
					 * householdMotherVisitDTO.getFamilyPlanningInfoDTOSet (); }
					 */
					String motherId = householdMemberDTO.getBeneficiaryId ();
					HHMotherVisitItem hhMotherVisitItem = new HHMotherVisitItem ();
					hhMotherVisitItem.setBeneficiaryId ( motherId );
					
					// add it to list
					hhMotherVisitItemList.add ( hhMotherVisitItem );
				}
				// get the child member
				else if ( memberType.equalsIgnoreCase ( DTOConstants.HOUSEHOLD_CHILD_VISITTYPE ) )
				{
					String childId = householdMemberDTO.getBeneficiaryId ();
					HHChildVisitItem hhChildVisitItem = new HHChildVisitItem ();
					hhChildVisitItem.setChildBeneficiaryId ( childId );
					
					// add it to list
					hhChildList.add ( hhChildVisitItem );
				}
			}
			
			householdVisitItem.setHhMotherList ( hhMotherVisitItemList );
			householdVisitItem.setHhChildList ( hhChildList );
		}
		catch (SystemException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		
		return householdVisitItem;
	}
	
	
}
