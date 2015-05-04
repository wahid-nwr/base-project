/**
 * 
 */
package com.swiftcorp.portal.household.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.dto.request.QuestionAnswerMap;
import com.swiftcorp.portal.household.dto.CASHouseholdMemberDTO;
import com.swiftcorp.portal.alert.service.IAlertService;
import com.swiftcorp.portal.beneficiary.dto.VisitDTO;
import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.common.dao.CommonDAOUtil;
import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.common.service.MhealthDTOAccesor;
import com.swiftcorp.portal.common.util.CalendarUtils;
import com.swiftcorp.portal.common.util.IDGeneratorUtil;
import com.swiftcorp.portal.common.util.MhealthReflectionUtil;
import com.swiftcorp.portal.dto.request.AlgQuestionAnswerReqDTO;
import com.swiftcorp.portal.dto.request.ChildAlgQuestionAnswerReqDTO;
import com.swiftcorp.portal.geo.dto.GeoDTO;
import com.swiftcorp.portal.household.dto.HouseHoldRegResponseDTO;
import com.swiftcorp.portal.household.dto.HouseholdDTO;
import com.swiftcorp.portal.household.dto.HouseholdMemberDTO;
import com.swiftcorp.portal.schedule.service.RoutineVisitScheduler;
import com.swiftcorp.portal.schedule.service.ScheduleServiceImpl;
import com.swiftcorp.portal.user.dto.UserDTO;
import com.swiftcorp.portal.user.service.UserServiceImpl;

/**
 * 
 * @author Arup sarker
 * 
 * 
 */

public class HouseholdRegService extends AbstractHouseholdAlgService
{
	// routine visit scheduler for mother reg
	private RoutineVisitScheduler routineVisitScheduler;
	
	private MhealthDTOAccesor householdDTOAccessor;
	
	private MhealthDTOAccesor householdMemberDTOAccessor;
	private MhealthDTOAccesor householdMotherVisitDTOAccessor;
	CommonDAOUtil commonDAOUtil;
	
	// house hold member max component id
	private long householdMemberMaxCompId;
	
	HouseholdServiceImpl householdService;
	UserServiceImpl userServiceImpl;
	
	ScheduleServiceImpl scheduleServiceImpl;
	private MhealthDTOAccesor visitDTOAccesor;
	
	// for alert generation
	private IAlertService alertService;
	
	private static final Log logger = LogFactory.getLog ( HouseholdRegService.class );
	
	@Override
	public HouseHoldRegResponseDTO saveHouseholdData ( AlgQuestionAnswerReqDTO algQuestionAnswerReqDTO )
	{
		// get the questionnaireId questionAnsMap from the request
		
		VisitDTO visitDTO = new VisitDTO ();
		try
		{
			// logger.info ( "before user get" );
			householdMemberMaxCompId = commonDAOUtil.getMaxComponentId ( "HouseholdMemberDTO householdMemberDTO" );
		}
		
		catch (Exception ex)
		{
			// TODO Auto-generated catch block
			ex.printStackTrace ();
		}
		
		HashMap<String, QuestionAnswerMap> questionnaireIdQuestionAnswerMap = algQuestionAnswerReqDTO.getQuestionnaireIdQuestionAnswerMap ();
		
		// question ans map
		QuestionAnswerMap questionAnswerMapObject = algQuestionAnswerReqDTO.getQuestionAnswerMap ();
		HashMap<String, Object> questionAnswerMap = questionAnswerMapObject.getQuestionAnswerMap ();
		
		visitDTO = (VisitDTO) MhealthReflectionUtil.getDTOFromAccessorAndAnswer ( visitDTOAccesor, questionnaireIdQuestionAnswerMap, questionAnswerMap );
		visitDTO.setVisitType(ApplicationConstants.VISIT_TYPE_HH_REG);
		// get the beneficiary dto
		HouseholdDTO householdDTO = (HouseholdDTO) MhealthReflectionUtil.getDTOFromAccessorAndAnswer ( householdDTOAccessor, questionnaireIdQuestionAnswerMap, questionAnswerMap );
		householdDTO.setVisitDTO ( visitDTO );
		
		householdDTO = processHouseholdDTO( householdDTO );
		// set pregnancy record to motherbeneficiary dto
		Set<HouseholdMemberDTO> householdMemberDTOSet = new HashSet<HouseholdMemberDTO> ();
		
		// now get the dto
		HouseholdMemberDTO householdMemberDTO = this.getHouseholdMemberDTOFromQuestionAns ( questionnaireIdQuestionAnswerMap, questionAnswerMap );
		// now add it to the set
		if ( householdMemberDTO != null )
		{
			householdMemberDTOSet.add ( householdMemberDTO );
		}
		
		// get the child alg list
		List<ChildAlgQuestionAnswerReqDTO> childAlgQuestionAnsList = algQuestionAnswerReqDTO.getChildAlgQuesAnsReqDTOList ();
		
		// now iterate through the child algorith
		if ( childAlgQuestionAnsList != null && childAlgQuestionAnsList.size () > 0 )
		{
			for ( ChildAlgQuestionAnswerReqDTO childAlgQuestionAns : childAlgQuestionAnsList )
			{
				if ( childAlgQuestionAns != null )
				{
					questionnaireIdQuestionAnswerMap = childAlgQuestionAns.getQuestionnaireIdQuestionAnswerMap ();
					
					// question ans map
					questionAnswerMapObject = childAlgQuestionAns.getQuestionAnswerMap ();
					questionAnswerMap = questionAnswerMapObject.getQuestionAnswerMap ();
					
					// now get the householdmember dto
					HouseholdMemberDTO childHouseholdMemberDTO = this.getHouseholdMemberDTOFromQuestionAns ( questionnaireIdQuestionAnswerMap, questionAnswerMap );
					// now add it to the set
					if ( householdMemberDTO != null )
					{
						householdMemberDTOSet.add ( childHouseholdMemberDTO );
					}
				}
			}
		}
		
		// now set the householdMemberDTOSet
		System.out.println ( "householdMemberDTOSet size:::" + householdMemberDTOSet.size () );
		householdDTO.setHouseholdMemberSet ( householdMemberDTOSet );
		
		logger.info ( "motherregservice dto populated" );
		try
		{
			Calendar calendar = Calendar.getInstance ();
			calendar.setTime ( new Date () );
			
			householdService.add ( householdDTO );
			scheduleServiceImpl.updateScheduleByVisit ( calendar, householdDTO.getHouseholdId (), visitDTO );
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		
		// set the response dto
		HouseHoldRegResponseDTO holdRegResponseDTO = new HouseHoldRegResponseDTO ();
		holdRegResponseDTO.setHouseholdId ( householdDTO.getHouseholdId () );
		// TODO: add the current cal for now
		holdRegResponseDTO.setHouseholdRegDate ( CalendarUtils.getCurrentCalendar () );
		
		ArrayList<CASHouseholdMemberDTO> casHHMemberList = this.getCASMemberDTOListFromMemberSet ( householdMemberDTOSet );
		holdRegResponseDTO.setHouseholdMemberDTOList ( casHHMemberList );
		
		// get the offline flag
		int offLineFlag = algQuestionAnswerReqDTO.getOffLineFlag ();
		
		
		
		// now return the response
		return holdRegResponseDTO;
	}
	
	private HouseholdMemberDTO getHouseholdMemberDTOFromQuestionAns ( HashMap<String, QuestionAnswerMap> questionnaireIdQuestionAnswerMap, HashMap<String, Object> questionAnswerMap )
	{
		// now get the dto
		HouseholdMemberDTO householdMemberDTO = null;
		
		try
		{
			householdMemberDTO = (HouseholdMemberDTO) MhealthReflectionUtil.getDTOFromAccessorAndAnswer ( this.householdMemberDTOAccessor, questionnaireIdQuestionAnswerMap, questionAnswerMap );
			String beneficiaryId = "";
			
			// get the ben id
			beneficiaryId = IDGeneratorUtil.generateMotherBeneficiaryId ( householdMemberMaxCompId );
			householdMemberDTO.setBeneficiaryId ( beneficiaryId );
			
			// increment it for other member
			householdMemberMaxCompId++;
		}
		catch (Exception e)
		{
			// TODO: handle exception
			logger.info ( e.getMessage () );
			e.printStackTrace ();
		}
		
		return householdMemberDTO;
	}
	
	private ArrayList<CASHouseholdMemberDTO> getCASMemberDTOListFromMemberSet ( Set<HouseholdMemberDTO> householdMemberDTOSet )
	{
		ArrayList<CASHouseholdMemberDTO> casHHMemberList = new ArrayList<CASHouseholdMemberDTO> ();
		
		for ( HouseholdMemberDTO householdMemberDTO : householdMemberDTOSet )
		{
			
			String memberType = householdMemberDTO.getMemberType ();
			if ( !memberType.equalsIgnoreCase ( DTOConstants.HOUSEHOLD_OTHER_MEMBER ) )
			{
				CASHouseholdMemberDTO casHouseholdMemberDTO = new CASHouseholdMemberDTO ();
				// default mother
				int memberTypeINXML = DTOConstants.MOTHER_MEMBERTYPE;
				
				// if it is not mother set it as childmother
				if ( !memberType.equalsIgnoreCase ( DTOConstants.HOUSEHOLD_MOTHER_MEMBER ) )
				{
					
					memberTypeINXML = DTOConstants.CHILD_MEMBERTYPE;
				}
				String householdMemberId = householdMemberDTO.getBeneficiaryId ();
				casHouseholdMemberDTO.setHouseholdMemberType ( memberTypeINXML );
				casHouseholdMemberDTO.setHouseholdMemberId ( householdMemberId );
				casHHMemberList.add ( casHouseholdMemberDTO );
			}
		}
		// now return the list
		return casHHMemberList;
	}
	
	private HouseholdDTO processHouseholdDTO ( HouseholdDTO householdDTO )
	{
		try
		{
			// get the visit dto
			UserDTO userDTO = householdDTO.getVisitDTO ().getUserDTO ();
			
			// get the skId, ssId and houseno
			String skId = userDTO.getUniqueCode ();
			String ssId = householdDTO.getSsId ();
			String houseNo = householdDTO.getHouseNo ();
			
			// now generate the household in the format skid/ssid/houseno
			houseNo = skId + DTOConstants.HHID_SEPARATOR + ssId + DTOConstants.HHID_SEPARATOR +houseNo;
			householdDTO.setHouseNo ( houseNo );
			//householdDTO.gets
			// get branch from userdto
			GeoDTO geoDTO = userDTO.getUserArea ();
			
			// set this geo dto as branch
			householdDTO.setBranch ( geoDTO );
		}
		catch ( Exception ex )
		{
			ex.printStackTrace ();
		}
		
		
		/// return the household
		return householdDTO;
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
	
	public ScheduleServiceImpl getScheduleServiceImpl ( )
	{
		return scheduleServiceImpl;
	}
	
	public void setUserServiceImpl ( UserServiceImpl userServiceImpl )
	{
		this.userServiceImpl = userServiceImpl;
	}
	
	public void setRoutineVisitScheduler ( RoutineVisitScheduler routineVisitScheduler )
	{
		this.routineVisitScheduler = routineVisitScheduler;
	}
	
	public void setCommonDAOUtil ( CommonDAOUtil commonDAOUtil )
	{
		this.commonDAOUtil = commonDAOUtil;
	}
	
	public MhealthDTOAccesor getHouseholdMotherVisitDTOAccessor ( )
	{
		return householdMotherVisitDTOAccessor;
	}
	
	public void setHouseholdMotherVisitDTOAccessor ( MhealthDTOAccesor householdMotherVisitDTOAccessor )
	{
		this.householdMotherVisitDTOAccessor = householdMotherVisitDTOAccessor;
	}
	
	public void setVisitDTOAccesor ( MhealthDTOAccesor visitDTOAccesor )
	{
		this.visitDTOAccesor = visitDTOAccesor;
	}
	
	public void setScheduleServiceImpl ( ScheduleServiceImpl scheduleServiceImpl )
	{
		this.scheduleServiceImpl = scheduleServiceImpl;
	}
	
	@Override
	public void saveHouseholdUpData ( AlgQuestionAnswerReqDTO algQuestionAnswerReqDTO )
	{
		// TODO Auto-generated method stub
		
	}

	public void setAlertService ( IAlertService alertService )
	{
		this.alertService = alertService;
	}
}
