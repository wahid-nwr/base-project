/**
 * 
 */
package com.swiftcorp.portal.household.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.dto.request.QuestionAnswerMap;
import com.swiftcorp.portal.beneficiary.dto.VisitDTO;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.service.MhealthDTOAccesor;
import com.swiftcorp.portal.common.util.MhealthReflectionUtil;
import com.swiftcorp.portal.dto.request.AlgQuestionAnswerReqDTO;
import com.swiftcorp.portal.household.dto.FamilyPlanningInfoDTO;
import com.swiftcorp.portal.household.dto.HouseHoldRegResponseDTO;
import com.swiftcorp.portal.household.dto.HouseholdDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberVisitDTO;
import com.swiftcorp.portal.household.dto.HouseholdMotherVisitDTO;
import com.swiftcorp.portal.schedule.service.RoutineVisitScheduler;
import com.swiftcorp.portal.schedule.service.ScheduleServiceImpl;
import com.swiftcorp.portal.user.service.UserServiceImpl;

/**
 * @author Arup sarker
 * 
 */

public class HouseholdUpdateService extends AbstractHouseholdAlgService
{
	// routine visit scheduler for mother reg
	private RoutineVisitScheduler routineVisitScheduler;
	
	// beneficiary questionnaireId dtoAccessor
	private MhealthDTOAccesor householdMotherVisitDTOAccessor;
	
	// newPregnance questionnaireId dtoAccessor
	
	private MhealthDTOAccesor familyPlanningInfoDTOAccessor;
	private MhealthDTOAccesor householdChildVisitDTOAccessor;
	private MhealthDTOAccesor visitDTOAccessor;
	private MhealthDTOAccesor householdDTOAccessor;
	private MhealthDTOAccesor householdMemberDTOAccessor;
	
	HouseholdServiceImpl householdService;
	UserServiceImpl userServiceImpl;
	ScheduleServiceImpl scheduleServiceImpl;
	private MhealthDTOAccesor visitDTOAccesor;
	
	public void setVisitDTOAccesor ( MhealthDTOAccesor visitDTOAccesor )
	{
		this.visitDTOAccesor = visitDTOAccesor;
	}
	
	public void setScheduleServiceImpl ( ScheduleServiceImpl scheduleServiceImpl )
	{
		this.scheduleServiceImpl = scheduleServiceImpl;
	}
	
	private static final Log logger = LogFactory.getLog ( HouseholdUpdateService.class );
	
	@Override
	public void saveHouseholdUpData ( AlgQuestionAnswerReqDTO algQuestionAnswerReqDTO )
	{
		// get the questionnaireId questionAnsMap from the request
		System.out.println ( "algQuestionAnswerReqDTO is null::" + (algQuestionAnswerReqDTO == null) );
		Map<String, QuestionAnswerMap> questionnaireIdQuestionAnswerMap = algQuestionAnswerReqDTO.getQuestionnaireIdQuestionAnswerMap ();
		// question ans map
		QuestionAnswerMap questionAnswerMapObject = algQuestionAnswerReqDTO.getQuestionAnswerMap ();
		HashMap<String, Object> questionAnswerMap = questionAnswerMapObject.getQuestionAnswerMap ();
		HouseholdDTO householdDTO = null;
		HouseholdMemberDTO currentHouseholdMemberDTO = null;
		
		try
		{
			
			VisitDTO visitDTO = new VisitDTO ();
			
			visitDTO = (VisitDTO) MhealthReflectionUtil.getDTOFromAccessorAndAnswer ( visitDTOAccesor, questionnaireIdQuestionAnswerMap, questionAnswerMap );
			visitDTO.setVisitType(ApplicationConstants.VISIT_TYPE_HH_VISIT);
			System.out.println ( "house hold id::" + questionAnswerMap.get ( "houseHoldId" ) );
			System.out.println ( "house hold mother id::" + questionAnswerMap.get ( "memberId" ) );
			if ( questionAnswerMap.containsKey ( "houseHoldId" ) )
			{
				String householdIdString = ((String) questionAnswerMap.get ( "houseHoldId" )).trim ();
				String ssId = ((String) questionAnswerMap.get ( "ssId" )).trim ();
				String skId = ((String) questionAnswerMap.get ( "userId" )).trim ();
				String houseNo = skId+"/"+ssId+"/"+householdIdString;
				String houseHoldMemberId = ((String) questionAnswerMap.get ( "memberId" )).trim ();
				long householdID = Long.parseLong ( householdIdString );
				long householdRecordId = 0;
				// get the beneficiary dto from database to update
				householdDTO = (HouseholdDTO) householdService.get ( houseNo );
				System.out.println ( "householdDTO is null::" + (householdDTO == null) );
				if ( householdDTO != null )
				{
					// get current pregnancy record
					
					currentHouseholdMemberDTO = householdDTO.getCurrentHouseholdMember ();
					Set<HouseholdMemberDTO> houseHoldMemberSet = householdDTO.getHouseholdMemberSet ();
					HouseholdMemberDTO upHouseholdMemberDTO = null;
					for ( HouseholdMemberDTO householdMemberDTO : houseHoldMemberSet )
					{
						if ( houseHoldMemberId.equalsIgnoreCase ( householdMemberDTO.getBeneficiaryId () ) )
						{
							upHouseholdMemberDTO = householdMemberDTO;
						}
					}
					if ( upHouseholdMemberDTO != null )
					{
						// get third generalquestion dto in question exist
						Set<HouseholdMemberVisitDTO> householdMemberVisitDTOSet =  upHouseholdMemberDTO.getHouseholdMemberVisitSet();
																						// ();
						// get delivery record dto if question exist
						if ( householdMemberVisitDTOSet == null )
						{
							householdMemberVisitDTOSet = new HashSet<HouseholdMemberVisitDTO> ();
						}
						HouseholdMotherVisitDTO householdMotherVisitDTO = (HouseholdMotherVisitDTO) MhealthReflectionUtil.getDTOFromAccessorAndAnswer ( householdMotherVisitDTOAccessor, questionnaireIdQuestionAnswerMap, questionAnswerMap );
						System.out.println ( "totalChildren " + householdMotherVisitDTO.getTotalChildren () );
						System.out.println ( "ageOfYoungerChild " + householdMotherVisitDTO.getAgeOfYoungerChild () );
						householdMotherVisitDTO.setMemberVisitType(5);
						if ( householdMotherVisitDTO != null )
						{
							System.out.println ( "presence of mother :" + householdMotherVisitDTO.getPresenceOfMother () );
							householdMotherVisitDTO.setVisitDTO ( visitDTO );
							Set<FamilyPlanningInfoDTO> familyPlanningInfoDTOSet = householdMotherVisitDTO.getFamilyPlanningInfoDTOSet ();
							if ( familyPlanningInfoDTOSet == null )
							{
								familyPlanningInfoDTOSet = new HashSet<FamilyPlanningInfoDTO> ();
							}
							FamilyPlanningInfoDTO familyPlanningInfoDTO = (FamilyPlanningInfoDTO) MhealthReflectionUtil.getDTOFromAccessorAndAnswer ( familyPlanningInfoDTOAccessor, questionnaireIdQuestionAnswerMap, questionAnswerMap );
							if ( familyPlanningInfoDTO != null )
							{
								// familyPlanningInfoDTO.setComponentId (
								// Long.parseLong ( "0" ) );
								familyPlanningInfoDTOSet.add ( familyPlanningInfoDTO );
								householdMotherVisitDTO.setFamilyPlanningInfoDTOSet ( familyPlanningInfoDTOSet );
								
							}
							
							householdMemberVisitDTOSet.add ( householdMotherVisitDTO );
							
						}
						// upHouseholdMemberDTO.setHouseholdMotherVisitSet (
						// householdMotherVisitDTOSet );
						
					}
					
					// get pnc section dto if question exist
					
					/*
					 * String currentStage = ""; if (
					 * questionAnswerMap.containsKey ( "patientStage" ) ) {
					 * currentStage = ((String) questionAnswerMap.get (
					 * "patientStage" )).trim ();
					 * currentPregNancyRecordDTO.setCurrentStage ( currentStage
					 * ); }
					 */
					householdService.modify ( householdDTO );
					
					// System.out.println("patient stage::"+currentHouseholdMemberDTO.getCurrentStage
					// ());
					// beneficiaryService.modifyPregnancyRecordDetails (
					// currentPregNancyRecordDTO );
					Calendar cal = Calendar.getInstance ();
					// now set the current date
					cal.setTime ( new Date () );
					
					scheduleServiceImpl.updateScheduleByVisit ( cal, houseHoldMemberId, visitDTO );
					
				}
				else
				{
					logger.info ( "Mother beneficiary is null" );
				}
			}
			
		}
		catch (SystemException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		catch (BusinessRuleViolationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		
		// if current stage is post delivery then run the scheduler to schedul
		
	}
	
	public MhealthDTOAccesor getVisitDTOAccessor ( )
	{
		return visitDTOAccessor;
	}
	
	public void setVisitDTOAccessor ( MhealthDTOAccesor visitDTOAccessor )
	{
		this.visitDTOAccessor = visitDTOAccessor;
	}
	
	public MhealthDTOAccesor getHouseholdMotherVisitDTOAccessor ( )
	{
		return householdMotherVisitDTOAccessor;
	}
	
	public void setHouseholdMotherVisitDTOAccessor ( MhealthDTOAccesor householdMotherVisitDTOAccessor )
	{
		this.householdMotherVisitDTOAccessor = householdMotherVisitDTOAccessor;
	}
	
	public MhealthDTOAccesor getFamilyPlanningInfoDTOAccessor ( )
	{
		return familyPlanningInfoDTOAccessor;
	}
	
	public void setFamilyPlanningInfoDTOAccessor ( MhealthDTOAccesor familyPlanningInfoDTOAccessor )
	{
		this.familyPlanningInfoDTOAccessor = familyPlanningInfoDTOAccessor;
	}
	
	public MhealthDTOAccesor getHouseholdChildVisitDTOAccessor ( )
	{
		return householdChildVisitDTOAccessor;
	}
	
	public void setHouseholdChildVisitDTOAccessor ( MhealthDTOAccesor householdChildVisitDTOAccessor )
	{
		this.householdChildVisitDTOAccessor = householdChildVisitDTOAccessor;
	}
	
	public MhealthDTOAccesor getHouseholdDTOAccessor ( )
	{
		return householdDTOAccessor;
	}
	
	public void setHouseholdDTOAccessor ( MhealthDTOAccesor householdDTOAccessor )
	{
		this.householdDTOAccessor = householdDTOAccessor;
	}
	
	public MhealthDTOAccesor getHouseholdMemberDTOAccessor ( )
	{
		return householdMemberDTOAccessor;
	}
	
	public void setHouseholdMemberDTOAccessor ( MhealthDTOAccesor householdMemberDTOAccessor )
	{
		this.householdMemberDTOAccessor = householdMemberDTOAccessor;
	}
	
	public void setHouseholdService ( HouseholdServiceImpl householdService )
	{
		this.householdService = householdService;
	}
	
	public void setUserServiceImpl ( UserServiceImpl userServiceImpl )
	{
		this.userServiceImpl = userServiceImpl;
	}
	
	public void setRoutineVisitScheduler ( RoutineVisitScheduler routineVisitScheduler )
	{
		this.routineVisitScheduler = routineVisitScheduler;
	}
	
	@Override
	public HouseHoldRegResponseDTO saveHouseholdData ( AlgQuestionAnswerReqDTO algQuestionAnswerReqDTO )
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
