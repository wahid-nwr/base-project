package com.swiftcorp.portal.beneficiary.service;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.search.SearchItemInfo;
import com.swiftcorp.portal.common.search.SearchVisitDTO;
import com.swiftcorp.portal.household.service.HouseholdServiceImpl;

public class SearchBeneficiaryService
{
	private BeneficiaryServiceImpl beneficiaryService;
	HouseholdServiceImpl householdService;
	private static final Log logger = LogFactory.getLog ( SearchBeneficiaryService.class );
	
	public void setHouseholdService ( HouseholdServiceImpl householdService )
	{
		this.householdService = householdService;
	}
	
	public void setBeneficiaryService ( BeneficiaryServiceImpl beneficiaryService )
	{
		this.beneficiaryService = beneficiaryService;
	}
	
	public ArrayList<SearchItemInfo> searchBeneficiary ( String beneficiaryId, int beneficiaryType )
	{
		ArrayList searchResult = null;
		//SearchResponseDTO searchResponseDTO = new SearchResponseDTO ();
		ArrayList<SearchItemInfo> searchItemInfoList = new ArrayList<SearchItemInfo> ();
		/*
		try
		{
			searchItemInfoList = beneficiaryService.getBeneficiaryForMobileById ( beneficiaryId, beneficiaryType );
			for ( SearchItemInfo searchItemInfo : searchItemInfoList )
			{
				HouseholdMemberDTO householdMemberDTO = householdService.getHouseholdMemberByMemberId ( beneficiaryId );
				searchItemInfoList.remove ( searchItemInfo );
				searchItemInfo.setHouseholdId ( householdMemberDTO.getHouseholdId () );
				searchItemInfoList.add ( searchItemInfo );
			}
			
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}*/
		
		return searchItemInfoList;
	}
	
	@Deprecated
	private ArrayList<SearchItemInfo> getDummySearchItemInfo ( )
	{
		ArrayList<SearchItemInfo> searchItemInfoList = new ArrayList<SearchItemInfo> ();
		SearchItemInfo searchItemInfo = new SearchItemInfo ();
		SearchVisitDTO searchVisitDTO = new SearchVisitDTO ();
		SearchVisitDTO searchVisitDTO1 = new SearchVisitDTO ();
		
		searchItemInfo.setSearchItemId ( "123" );
		searchItemInfo.setItemType ( 5 );
		searchItemInfo.setRegDate ( Calendar.getInstance () );
		searchItemInfo.setHouseholdId ( "1234" );
		searchVisitDTO.setVisitType ( "anc" );
		searchVisitDTO.setVisitDate ( Calendar.getInstance () );
		searchVisitDTO.setBloodPressure ( "75;120" );
		searchVisitDTO.setEdima ( "1" );
		searchVisitDTO.setAnaemia ( "1" );
		searchVisitDTO.setComplications ( "severeFever;convulsions" );
		searchVisitDTO1.setVisitType ( "pnc" );
		searchVisitDTO1.setVisitDate ( Calendar.getInstance () );
		searchVisitDTO1.setBloodPressure ( "75;120" );
		searchVisitDTO1.setEdima ( "1" );
		searchVisitDTO1.setAnaemia ( "1" );
		searchVisitDTO1.setComplications ( "severeFever;convulsions" );
		
		ArrayList<SearchVisitDTO> searchVisitDTOList = new ArrayList<SearchVisitDTO> ();
		searchVisitDTOList.add ( searchVisitDTO );
		searchVisitDTOList.add ( searchVisitDTO1 );
		
		searchItemInfo.setSearchVisitDTOList ( searchVisitDTOList );
		searchItemInfoList.add ( searchItemInfo );
		return searchItemInfoList;
	}
}
