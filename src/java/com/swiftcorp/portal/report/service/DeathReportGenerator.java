/**
 * 
 */
package com.swiftcorp.portal.report.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.report.dao.IDeathReportDAO;
import com.swiftcorp.portal.report.dto.BeneficiaryDeathRecord;
import com.swiftcorp.portal.report.dto.BeneficiaryDeathReportSection;
import com.swiftcorp.portal.report.dto.ChildDeathRecord;
import com.swiftcorp.portal.report.dto.ChildDeathReportConstants;
import com.swiftcorp.portal.report.dto.DeathReportDTOConstants;
import com.swiftcorp.portal.report.dto.MonthwiseCountRecord;
import com.swiftcorp.portal.report.dto.MotherDeathRecord;
import com.swiftcorp.portal.report.dto.MotherDeathReportConstants;
import com.swiftcorp.portal.report.util.ReportUtils;

/**
 * @author asraful.haque
 * 
 */
public class DeathReportGenerator
{
	// template name
	private String motherDeathReportTemplateName;
	
	// child death report name
	private String childDeathReportTemplateName;
	
	// mother death report name
	private String motherDeathReportName;
	
	// child death report name
	private String childDeathReportName;
	
	// report dao to fetch death record
	private IDeathReportDAO deathReportDAO;
	
	// death place for mother
	private List<String> motherDeathPlaceColNameList;
	private Map<String, String> motherDeathPlaceColNameDBValueMap;
	
	// death place for child
	private List<String> childDeathPlaceColNameList;
	private Map<String, String> childDeathPlaceColNameDBValueMap;
	// death cause for mother
	private List<String> motherDeathCauseColNameList;
	private Map<String, String> motherDeathCauseColNameDBValueMap;
	// death cause for child
	private List<String> childDeathCauseColNameList;
	private Map<String, String> childDeathCauseColNameDBValueMap;
	// preg stage
	private List<String> pregStageColNameList;
	private Map<String, String> pregStageColNameDBValueMap;
	
	// child gestational life col list
	private List<String> childGestLifeColNameList;
	private Map<String, String> childGestLifeColNameDBValueMap;
	
	// gravid of mother
	private List<String> motherGravidColNameList;
	private Map<String, String> motherGravidColNameDBValueMap;
	
	// age of mother
	private List<String> motherAgeColNameList;
	private Map<String, String> motherAgeColNameDBValueMap;
	
	// economic condition
	private List<String> economicCondColNameList;
	private Map<String, String> economicCondColNameDBValueMap;
	
	public String generateMotherDeathReport ( String templateDir, String reportDir, int year )
	{
		// get the mother record
		MotherDeathRecord motherDeathRecord = this.getMotherDeathRecord ( year );
		Map beans = new HashMap ();
		beans.put ( "motherDeathRecord", motherDeathRecord );
		
		String templateFilePath = templateDir + File.separatorChar + this.motherDeathReportTemplateName;
		String reportFullFilePath = ReportUtils.getDeathReportFileName ( reportDir, this.motherDeathReportName, year );
		
		// now generate report
		ReportUtils.generateReport ( templateFilePath, beans, reportFullFilePath );
		
		// return the path
		return reportFullFilePath;
	}
	
	public String generateChildDeathReport ( String templateDir, String reportDir, int year )
	{
		// get the mother record
		ChildDeathRecord childDeathRecord = this.getChildDeathRecord ( year );
		Map beans = new HashMap ();
		beans.put ( "childDeathRecord", childDeathRecord );
		
		String templateFilePath = templateDir + File.separatorChar + this.childDeathReportTemplateName;
		String reportFullFilePath = ReportUtils.getDeathReportFileName ( reportDir, this.childDeathReportName, year );
		
		// now generate report
		ReportUtils.generateReport ( templateFilePath, beans, reportFullFilePath );
		
		// return the path
		return reportFullFilePath;
	}
	
	public ChildDeathRecord getChildDeathRecord ( int year )
	{
		// instantiate the mother record
		ChildDeathRecord childDeathRecord = new ChildDeathRecord ();
		
		// get the death time section record
		BeneficiaryDeathReportSection deathTimeReportSection = this.getDeathTimeReportSection ( DeathReportDTOConstants.CHILD_BENEFICIARY_TYPE, year );
		childDeathRecord.setDeathTimeReportSection ( deathTimeReportSection );
		
		// get the death place report section
		BeneficiaryDeathReportSection deathPlaceReportSection = this.getDeathPlaceReportSectionForChild ( year );
		childDeathRecord.setDeathPlaceReportSection ( deathPlaceReportSection );
		
		// get the preg stage section
		BeneficiaryDeathReportSection gestLifeReportSection = this.getGestLifeReportSection ( year );
		childDeathRecord.setGestLifeReportSection ( gestLifeReportSection );
		
		// get the death cause section
		BeneficiaryDeathReportSection deathCauseReportSection = this.getDeathCauseReportSectionForChild ( year );
		childDeathRecord.setDeathCauseReportSection ( deathCauseReportSection );
		
		// get the gravid mother report section
		BeneficiaryDeathReportSection motherGravidReportSection = this.getMotherGravidReportSection ( DeathReportDTOConstants.CHILD_BENEFICIARY_TYPE, year );
		childDeathRecord.setMotherGravidReportSection ( motherGravidReportSection );
		
		// get the mother age report section
		BeneficiaryDeathReportSection motherAgeReportSection = this.getMotherAgeReportSection ( DeathReportDTOConstants.CHILD_BENEFICIARY_TYPE, year );
		childDeathRecord.setMotherAgeReportSection ( motherAgeReportSection );
		
		// get the socio economic report section
		BeneficiaryDeathReportSection economicConditionReportSection = this.getEconomicCondReportSection ( DeathReportDTOConstants.CHILD_BENEFICIARY_TYPE, year );
		childDeathRecord.setEconomicConditionReportSection ( economicConditionReportSection );
		
		// return null for now
		return childDeathRecord;
	}
	
	public MotherDeathRecord getMotherDeathRecord ( int year )
	{
		// instantiate the mother record
		MotherDeathRecord motherDeathRecord = new MotherDeathRecord ();
		
		// get the death time section record
		BeneficiaryDeathReportSection deathTimeReportSection = this.getDeathTimeReportSection ( DeathReportDTOConstants.MOTHER_BENEFICIARY_TYPE, year );
		motherDeathRecord.setDeathTimeReportSection ( deathTimeReportSection );
		
		// get the death place report section
		BeneficiaryDeathReportSection deathPlaceReportSection = this.getDeathPlaceReportSectionForMother ( year );
		motherDeathRecord.setDeathPlaceReportSection ( deathPlaceReportSection );
		
		// get the preg stage section
		BeneficiaryDeathReportSection pregStageReportSection = this.getPregStageReportSection ( year );
		motherDeathRecord.setPregStageReportSection ( pregStageReportSection );
		
		// get the death cause section
		BeneficiaryDeathReportSection deathCauseReportSection = this.getDeathCauseReportSectionForMother ( year );
		motherDeathRecord.setDeathCauseReportSection ( deathCauseReportSection );
		
		// get the gravid mother report section
		BeneficiaryDeathReportSection motherGravidReportSection = this.getMotherGravidReportSection ( DeathReportDTOConstants.MOTHER_BENEFICIARY_TYPE, year );
		motherDeathRecord.setMotherGravidReportSection ( motherGravidReportSection );
		
		// get the mother age report section
		BeneficiaryDeathReportSection motherAgeReportSection = this.getMotherAgeReportSection ( DeathReportDTOConstants.MOTHER_BENEFICIARY_TYPE, year );
		motherDeathRecord.setMotherAgeReportSection ( motherAgeReportSection );
		
		// get the socio economic report section
		BeneficiaryDeathReportSection economicConditionReportSection = this.getEconomicCondReportSection ( DeathReportDTOConstants.MOTHER_BENEFICIARY_TYPE, year );
		motherDeathRecord.setEconomicConditionReportSection ( economicConditionReportSection );
		
		// return null for now
		return motherDeathRecord;
	}
	
	private BeneficiaryDeathReportSection getDeathTimeReportSection ( int beneficiaryType, int year )
	{
		// TODO: the section name is same for both mother and child if it
		// changes then we have to select one
		BeneficiaryDeathReportSection deathTimeReportSection = new BeneficiaryDeathReportSection ( MotherDeathReportConstants.TIME_OF_DEATH );
		List<BeneficiaryDeathRecord> beneficiaryDeathRecordList = new ArrayList<BeneficiaryDeathRecord> ();
		
		// now get the beneficiary death record
		try
		{
			// get the record result for 8am to 4 pm
			ArrayList result = this.deathReportDAO.getDeathTimeRecord ( beneficiaryType, year, DeathReportDTOConstants.EIGHT_AM_4PM_LOWER_LIMIT, DeathReportDTOConstants.EIGHT_AM_4PM_UPEER_LIMIT );
			
			// get the eight am to 4 pm data record
			BeneficiaryDeathRecord eightAMBeneficiaryDeathRecord = new BeneficiaryDeathRecord ( MotherDeathReportConstants.EIGHT_AM_TO_4PM );
			
			// get the month wise record
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			eightAMBeneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			beneficiaryDeathRecordList.add ( eightAMBeneficiaryDeathRecord );
			
			// get the record result for 4pm to 12 am
			result = this.deathReportDAO.getDeathTimeRecord ( beneficiaryType, year, DeathReportDTOConstants.FOUR_PM_12AM_LOWER_LIMIT, DeathReportDTOConstants.FOUR_PM_12AM_UPEER_LIMIT );
			
			// get the eight am to 4 pm data record
			BeneficiaryDeathRecord fourPMBeneficiaryDeathRecord = new BeneficiaryDeathRecord ( MotherDeathReportConstants.FOUR_PM_TO_12AM );
			
			// get the month wise record
			monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			fourPMBeneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			beneficiaryDeathRecordList.add ( fourPMBeneficiaryDeathRecord );
			
			// get the record result for 12 am to 8 am
			result = this.deathReportDAO.getDeathTimeRecord ( beneficiaryType, year, DeathReportDTOConstants.TWELVE_AM_8AM_LOWER_LIMIT, DeathReportDTOConstants.TWELVE_AM_8AM_UPEER_LIMIT );
			
			// get the eight am to 4 pm data record
			BeneficiaryDeathRecord twelveAMBeneficiaryDeathRecord = new BeneficiaryDeathRecord ( MotherDeathReportConstants.TWELVE_AM_TO_8AM );
			
			// get the month wise record
			monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			twelveAMBeneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			beneficiaryDeathRecordList.add ( twelveAMBeneficiaryDeathRecord );
			
			// add the list to the beneficiayReport section
			deathTimeReportSection.setBeneficiaryDeathRecordList ( beneficiaryDeathRecordList );
		}
		catch (SystemException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		return deathTimeReportSection;
	}
	
	private BeneficiaryDeathReportSection getDeathPlaceReportSectionForMother ( int year )
	{
		BeneficiaryDeathReportSection deathPlaceReportSection = new BeneficiaryDeathReportSection ( MotherDeathReportConstants.PLACE_OF_DEATH );
		List<BeneficiaryDeathRecord> beneficiaryDeathRecordList = new ArrayList<BeneficiaryDeathRecord> ();
		
		// iterate the place for mother
		for ( String deathPlace : this.motherDeathPlaceColNameList )
		{
			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord ( deathPlace );
			String dbDeathPlace = this.motherDeathPlaceColNameDBValueMap.get ( deathPlace );
			
			ArrayList result = null;
			try
			{
				result = this.deathReportDAO.getDeathRecordByDeathPlace ( DeathReportDTOConstants.MOTHER_BENEFICIARY_TYPE, year, dbDeathPlace );
			}
			catch (SystemException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			beneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			
			// now add it to the list
			beneficiaryDeathRecordList.add ( beneficiaryDeathRecord );
		}
		
		// now add the list to section
		deathPlaceReportSection.setBeneficiaryDeathRecordList ( beneficiaryDeathRecordList );
		
		// return the report section
		return deathPlaceReportSection;
	}
	
	private BeneficiaryDeathReportSection getDeathPlaceReportSectionForChild ( int year )
	{
		BeneficiaryDeathReportSection deathPlaceReportSection = new BeneficiaryDeathReportSection ( ChildDeathReportConstants.PLACE_OF_DEATH );
		List<BeneficiaryDeathRecord> beneficiaryDeathRecordList = new ArrayList<BeneficiaryDeathRecord> ();
		
		// iterate the place for mother
		for ( String deathPlace : this.childDeathPlaceColNameList )
		{
			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord ( deathPlace );
			String dbDeathPlace = this.childDeathPlaceColNameDBValueMap.get ( deathPlace );
			
			ArrayList result = null;
			try
			{
				result = this.deathReportDAO.getDeathRecordByDeathPlace ( DeathReportDTOConstants.CHILD_BENEFICIARY_TYPE, year, dbDeathPlace );
			}
			catch (SystemException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			beneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			
			// now add it to the list
			beneficiaryDeathRecordList.add ( beneficiaryDeathRecord );
		}
		
		// now add the list to section
		deathPlaceReportSection.setBeneficiaryDeathRecordList ( beneficiaryDeathRecordList );
		
		// return the report section
		return deathPlaceReportSection;
	}
	
	private BeneficiaryDeathReportSection getPregStageReportSection ( int year )
	{
		BeneficiaryDeathReportSection pregStageReportSection = new BeneficiaryDeathReportSection ( MotherDeathReportConstants.STAGE_OF_PREGNANCY );
		List<BeneficiaryDeathRecord> beneficiaryDeathRecordList = new ArrayList<BeneficiaryDeathRecord> ();
		
		// iterate the place for mother
		for ( String pregStage : this.pregStageColNameList )
		{
			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord ( pregStage );
			String dbValue = this.pregStageColNameDBValueMap.get ( pregStage );
			
			ArrayList result = null;
			try
			{
				result = this.deathReportDAO.getDeathRecordByPregStage ( year, dbValue );
			}
			catch (SystemException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			beneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			
			// now add it to the list
			beneficiaryDeathRecordList.add ( beneficiaryDeathRecord );
		}
		
		// now add the list to section
		pregStageReportSection.setBeneficiaryDeathRecordList ( beneficiaryDeathRecordList );
		
		// return the report section
		return pregStageReportSection;
	}
	
	private BeneficiaryDeathReportSection getGestLifeReportSection ( int year )
	{
		BeneficiaryDeathReportSection pregStageReportSection = new BeneficiaryDeathReportSection ( ChildDeathReportConstants.GESTATIONAL_LIFE );
		List<BeneficiaryDeathRecord> beneficiaryDeathRecordList = new ArrayList<BeneficiaryDeathRecord> ();
		
		// iterate the place for mother
		for ( String gestLifeName : this.childGestLifeColNameList )
		{
			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord ( gestLifeName );
			String dbValue = this.childGestLifeColNameDBValueMap.get ( gestLifeName );
			
			ArrayList result = null;
			try
			{
				result = this.deathReportDAO.getDeathRecordByPregStage ( year, dbValue );
			}
			catch (SystemException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			beneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			
			// now add it to the list
			beneficiaryDeathRecordList.add ( beneficiaryDeathRecord );
		}
		
		// now add the list to section
		pregStageReportSection.setBeneficiaryDeathRecordList ( beneficiaryDeathRecordList );
		
		// return the report section
		return pregStageReportSection;
	}
	
	private BeneficiaryDeathReportSection getDeathCauseReportSectionForMother ( int year )
	{
		BeneficiaryDeathReportSection deathCauseReportSection = new BeneficiaryDeathReportSection ( MotherDeathReportConstants.CAUSE_OF_DEATH );
		List<BeneficiaryDeathRecord> beneficiaryDeathRecordList = new ArrayList<BeneficiaryDeathRecord> ();
		
		// iterate the place for mother
		for ( String deathCause : this.motherDeathCauseColNameList )
		{
			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord ( deathCause );
			String dbValue = this.motherDeathCauseColNameDBValueMap.get ( deathCause );
			
			ArrayList result = null;
			try
			{
				result = this.deathReportDAO.getDeathRecordByDeathCause ( DeathReportDTOConstants.MOTHER_BENEFICIARY_TYPE, year, deathCause );
			}
			catch (SystemException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			beneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			
			// now add it to the list
			beneficiaryDeathRecordList.add ( beneficiaryDeathRecord );
		}
		
		// now add the list to section
		deathCauseReportSection.setBeneficiaryDeathRecordList ( beneficiaryDeathRecordList );
		
		// return the report section
		return deathCauseReportSection;
	}
	
	private BeneficiaryDeathReportSection getDeathCauseReportSectionForChild ( int year )
	{
		BeneficiaryDeathReportSection deathCauseReportSection = new BeneficiaryDeathReportSection ( ChildDeathReportConstants.CAUSE_OF_DEATH );
		List<BeneficiaryDeathRecord> beneficiaryDeathRecordList = new ArrayList<BeneficiaryDeathRecord> ();
		
		// iterate the place for mother
		for ( String deathCause : this.childDeathCauseColNameList )
		{
			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord ( deathCause );
			String dbValue = this.childDeathCauseColNameDBValueMap.get ( deathCause );
			
			ArrayList result = null;
			try
			{
				result = this.deathReportDAO.getDeathRecordByDeathCause ( DeathReportDTOConstants.CHILD_BENEFICIARY_TYPE, year, deathCause );
			}
			catch (SystemException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			beneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			
			// now add it to the list
			beneficiaryDeathRecordList.add ( beneficiaryDeathRecord );
		}
		
		// now add the list to section
		deathCauseReportSection.setBeneficiaryDeathRecordList ( beneficiaryDeathRecordList );
		
		// return the report section
		return deathCauseReportSection;
	}
	
	private BeneficiaryDeathReportSection getMotherGravidReportSection ( int beneficiaryType, int year )
	{
		BeneficiaryDeathReportSection motherGravidReportSection = new BeneficiaryDeathReportSection ( MotherDeathReportConstants.GRAVID_OF_MOTHER );
		List<BeneficiaryDeathRecord> beneficiaryDeathRecordList = new ArrayList<BeneficiaryDeathRecord> ();
		
		int gravidListSize = this.motherGravidColNameList.size ();
		// iterate the place for mother
		for ( int i = 0; i < gravidListSize - 1; i++ )
		{
			String gravid = this.motherGravidColNameList.get ( i );
			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord ( gravid );
			String dbValue = this.motherGravidColNameDBValueMap.get ( gravid );
			
			int gravidInt = Integer.parseInt ( dbValue );
			ArrayList result = null;
			try
			{
				result = this.deathReportDAO.getDeathRecordByMotherGravid ( beneficiaryType, year, gravidInt );
			}
			catch (SystemException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			beneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			
			// now add it to the list
			beneficiaryDeathRecordList.add ( beneficiaryDeathRecord );
		}
		
		// for the upper limit get the another querry
		String upperLimitColName = this.motherGravidColNameList.get ( gravidListSize - 1 );
		String gravidString = this.motherGravidColNameDBValueMap.get ( upperLimitColName );
		int gravidUpperLimit = Integer.parseInt ( gravidString );
		
		try
		{
			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord ( "" + gravidUpperLimit );
			ArrayList result = this.deathReportDAO.getDeathRecordForUpperGravid ( beneficiaryType, year, gravidUpperLimit );
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			beneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			
			// now add it to the list
			beneficiaryDeathRecordList.add ( beneficiaryDeathRecord );
		}
		catch (SystemException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
		}
		
		// now add the list to section
		motherGravidReportSection.setBeneficiaryDeathRecordList ( beneficiaryDeathRecordList );
		
		// return the report section
		return motherGravidReportSection;
	}
	
	private BeneficiaryDeathReportSection getMotherAgeReportSection ( int beneficiaryType, int year )
	{
		BeneficiaryDeathReportSection motherAgeReportSection = new BeneficiaryDeathReportSection ( MotherDeathReportConstants.AGE_OF_MOTHER );
		List<BeneficiaryDeathRecord> beneficiaryDeathRecordList = new ArrayList<BeneficiaryDeathRecord> ();
		
		// iterate the place for mother
		for ( String ageLimit : this.motherAgeColNameList )
		{
			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord ( ageLimit );
			String ageRangeValue = this.motherAgeColNameDBValueMap.get ( ageLimit );
			
			ArrayList result = null;
			try
			{
				String[] ageArray = ageRangeValue.split ( ";" );
				int motherAgeLowerLimit = Integer.parseInt ( ageArray[0] );
				int motherAgeUpperLimit = Integer.parseInt ( ageArray[1] );
				result = this.deathReportDAO.getDeathRecordByMotherAge ( beneficiaryType, year, motherAgeUpperLimit, motherAgeLowerLimit );
			}
			catch (SystemException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			beneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			
			// now add it to the list
			beneficiaryDeathRecordList.add ( beneficiaryDeathRecord );
		}
		// now add the list to section
		motherAgeReportSection.setBeneficiaryDeathRecordList ( beneficiaryDeathRecordList );
		
		// return the report section
		return motherAgeReportSection;
	}
	
	private BeneficiaryDeathReportSection getEconomicCondReportSection ( int beneficiaryType, int year )
	{
		BeneficiaryDeathReportSection economicCondReportSection = new BeneficiaryDeathReportSection ( "" );
		List<BeneficiaryDeathRecord> beneficiaryDeathRecordList = new ArrayList<BeneficiaryDeathRecord> ();
		
		// iterate the place for mother
		for ( String economicCondition : this.economicCondColNameList )
		{
			BeneficiaryDeathRecord beneficiaryDeathRecord = new BeneficiaryDeathRecord ( economicCondition );
			String dbValue = this.economicCondColNameDBValueMap.get ( economicCondition );
			
			ArrayList result = null;
			try
			{
				
				result = this.deathReportDAO.getDeathRecordByEconomicCondition ( beneficiaryType, year, dbValue );
			}
			catch (SystemException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace ();
			}
			MonthwiseCountRecord monthwiseCountRecord = this.getMonthwiseCountFromResult ( result );
			beneficiaryDeathRecord.setMonthwiseCountRecord ( monthwiseCountRecord );
			
			// now add it to the list
			beneficiaryDeathRecordList.add ( beneficiaryDeathRecord );
		}
		
		// now add the list to section
		economicCondReportSection.setBeneficiaryDeathRecordList ( beneficiaryDeathRecordList );
		
		// return the report section
		return economicCondReportSection;
	}
	
	private MonthwiseCountRecord getMonthwiseCountFromResult ( ArrayList result )
	{
		MonthwiseCountRecord monthwiseCountRecord = new MonthwiseCountRecord ();
		
		if ( result == null || result.size () == 0 )
		{
			return monthwiseCountRecord;
		}
		for ( Object ob : result )
		{
			
			Object[] resultObjArray = (Object[]) ob;
			long count = (Long) resultObjArray[0];
			int month = (Integer) resultObjArray[1];
			System.out.println ( "Month : " + resultObjArray[1] + " Count: " + resultObjArray[0] );
			
			// now according to the month set the count
			switch ( month )
			{
				case DeathReportDTOConstants.JAN:
					monthwiseCountRecord.setJanCount ( count );
					break;
				case DeathReportDTOConstants.FEB:
					monthwiseCountRecord.setFebCount ( count );
					break;
				case DeathReportDTOConstants.MAR:
					monthwiseCountRecord.setMarCount ( count );
					break;
				case DeathReportDTOConstants.APR:
					monthwiseCountRecord.setAprCount ( count );
					break;
				case DeathReportDTOConstants.MAY:
					monthwiseCountRecord.setMayCount ( count );
					break;
				case DeathReportDTOConstants.JUN:
					monthwiseCountRecord.setJunCount ( count );
					break;
				case DeathReportDTOConstants.JUL:
					monthwiseCountRecord.setJulCount ( count );
					break;
				case DeathReportDTOConstants.AUG:
					monthwiseCountRecord.setAugCount ( count );
					break;
				case DeathReportDTOConstants.SEP:
					monthwiseCountRecord.setSepCount ( count );
					break;
				case DeathReportDTOConstants.OCT:
					monthwiseCountRecord.setOctCount ( count );
					break;
				case DeathReportDTOConstants.NOV:
					monthwiseCountRecord.setNovCount ( count );
					break;
				case DeathReportDTOConstants.DEC:
					monthwiseCountRecord.setDecCount ( count );
					break;
				default:
					break;
			}
		}
		
		return monthwiseCountRecord;
	}
	
	// Getters and setters
	public List<String> getMotherDeathPlaceColNameList ( )
	{
		return motherDeathPlaceColNameList;
	}
	
	public void setMotherDeathPlaceColNameList ( List<String> motherDeathPlaceColNameList )
	{
		this.motherDeathPlaceColNameList = motherDeathPlaceColNameList;
	}
	
	public Map<String, String> getMotherDeathPlaceColNameDBValueMap ( )
	{
		return motherDeathPlaceColNameDBValueMap;
	}
	
	public void setMotherDeathPlaceColNameDBValueMap ( Map<String, String> motherDeathPlaceColNameDBValueMap )
	{
		this.motherDeathPlaceColNameDBValueMap = motherDeathPlaceColNameDBValueMap;
	}
	
	public List<String> getChildDeathPlaceColNameList ( )
	{
		return childDeathPlaceColNameList;
	}
	
	public void setChildDeathPlaceColNameList ( List<String> childDeathPlaceColNameList )
	{
		this.childDeathPlaceColNameList = childDeathPlaceColNameList;
	}
	
	public Map<String, String> getChildDeathPlaceColNameDBValueMap ( )
	{
		return childDeathPlaceColNameDBValueMap;
	}
	
	public void setChildDeathPlaceColNameDBValueMap ( Map<String, String> childDeathPlaceColNameDBValueMap )
	{
		this.childDeathPlaceColNameDBValueMap = childDeathPlaceColNameDBValueMap;
	}
	
	public List<String> getMotherDeathCauseColNameList ( )
	{
		return motherDeathCauseColNameList;
	}
	
	public void setMotherDeathCauseColNameList ( List<String> motherDeathCauseColNameList )
	{
		this.motherDeathCauseColNameList = motherDeathCauseColNameList;
	}
	
	public Map<String, String> getMotherDeathCauseColNameDBValueMap ( )
	{
		return motherDeathCauseColNameDBValueMap;
	}
	
	public void setMotherDeathCauseColNameDBValueMap ( Map<String, String> motherDeathCauseColNameDBValueMap )
	{
		this.motherDeathCauseColNameDBValueMap = motherDeathCauseColNameDBValueMap;
	}
	
	public List<String> getChildDeathCauseColNameList ( )
	{
		return childDeathCauseColNameList;
	}
	
	public void setChildDeathCauseColNameList ( List<String> childDeathCauseColNameList )
	{
		this.childDeathCauseColNameList = childDeathCauseColNameList;
	}
	
	public Map<String, String> getChildDeathCauseColNameDBValueMap ( )
	{
		return childDeathCauseColNameDBValueMap;
	}
	
	public void setChildDeathCauseColNameDBValueMap ( Map<String, String> childDeathCauseColNameDBValueMap )
	{
		this.childDeathCauseColNameDBValueMap = childDeathCauseColNameDBValueMap;
	}
	
	public List<String> getPregStageColNameList ( )
	{
		return pregStageColNameList;
	}
	
	public void setPregStageColNameList ( List<String> pregStageColNameList )
	{
		this.pregStageColNameList = pregStageColNameList;
	}
	
	public Map<String, String> getPregStageColNameDBValueMap ( )
	{
		return pregStageColNameDBValueMap;
	}
	
	public void setPregStageColNameDBValueMap ( Map<String, String> pregStageColNameDBValueMap )
	{
		this.pregStageColNameDBValueMap = pregStageColNameDBValueMap;
	}
	
	public List<String> getMotherGravidColNameList ( )
	{
		return motherGravidColNameList;
	}
	
	public void setMotherGravidColNameList ( List<String> motherGravidColNameList )
	{
		this.motherGravidColNameList = motherGravidColNameList;
	}
	
	public Map<String, String> getMotherGravidColNameDBValueMap ( )
	{
		return motherGravidColNameDBValueMap;
	}
	
	public void setMotherGravidColNameDBValueMap ( Map<String, String> motherGravidColNameDBValueMap )
	{
		this.motherGravidColNameDBValueMap = motherGravidColNameDBValueMap;
	}
	
	public List<String> getMotherAgeColNameList ( )
	{
		return motherAgeColNameList;
	}
	
	public void setMotherAgeColNameList ( List<String> motherAgeColNameList )
	{
		this.motherAgeColNameList = motherAgeColNameList;
	}
	
	public Map<String, String> getMotherAgeColNameDBValueMap ( )
	{
		return motherAgeColNameDBValueMap;
	}
	
	public void setMotherAgeColNameDBValueMap ( Map<String, String> motherAgeColNameDBValueMap )
	{
		this.motherAgeColNameDBValueMap = motherAgeColNameDBValueMap;
	}
	
	public IDeathReportDAO getDeathReportDAO ( )
	{
		return deathReportDAO;
	}
	
	public void setDeathReportDAO ( IDeathReportDAO deathReportDAO )
	{
		this.deathReportDAO = deathReportDAO;
	}
	
	public List<String> getEconomicCondColNameList ( )
	{
		return economicCondColNameList;
	}
	
	public void setEconomicCondColNameList ( List<String> economicCondColNameList )
	{
		this.economicCondColNameList = economicCondColNameList;
	}
	
	public Map<String, String> getEconomicCondColNameDBValueMap ( )
	{
		return economicCondColNameDBValueMap;
	}
	
	public void setEconomicCondColNameDBValueMap ( Map<String, String> economicCondColNameDBValueMap )
	{
		this.economicCondColNameDBValueMap = economicCondColNameDBValueMap;
	}
	
	public String getMotherDeathReportName ( )
	{
		return motherDeathReportName;
	}
	
	public void setMotherDeathReportName ( String motherDeathReportName )
	{
		this.motherDeathReportName = motherDeathReportName;
	}
	
	public String getChildDeathReportName ( )
	{
		return childDeathReportName;
	}
	
	public void setChildDeathReportName ( String childDeathReportName )
	{
		this.childDeathReportName = childDeathReportName;
	}
	
	public String getMotherDeathReportTemplateName ( )
	{
		return motherDeathReportTemplateName;
	}
	
	public void setMotherDeathReportTemplateName ( String motherDeathReportTemplateName )
	{
		this.motherDeathReportTemplateName = motherDeathReportTemplateName;
	}
	
	public String getChildDeathReportTemplateName ( )
	{
		return childDeathReportTemplateName;
	}
	
	public void setChildDeathReportTemplateName ( String childDeathReportTemplateName )
	{
		this.childDeathReportTemplateName = childDeathReportTemplateName;
	}
	
	public List<String> getChildGestLifeColNameList ( )
	{
		return childGestLifeColNameList;
	}
	
	public void setChildGestLifeColNameList ( List<String> childGestLifeColNameList )
	{
		this.childGestLifeColNameList = childGestLifeColNameList;
	}
	
	public Map<String, String> getChildGestLifeColNameDBValueMap ( )
	{
		return childGestLifeColNameDBValueMap;
	}
	
	public void setChildGestLifeColNameDBValueMap ( Map<String, String> childGestLifeColNameDBValueMap )
	{
		this.childGestLifeColNameDBValueMap = childGestLifeColNameDBValueMap;
	}
	
}
