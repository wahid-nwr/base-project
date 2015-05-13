/**
 * 
 */
package com.swiftcorp.portal.report.dto;

/**
 * @author asraf
 * 
 */
public class MotherDeathRecord
{
	// death time report section
	private BeneficiaryDeathReportSection deathTimeReportSection;
	private BeneficiaryDeathReportSection deathPlaceReportSection;
	private BeneficiaryDeathReportSection pregStageReportSection;
	private BeneficiaryDeathReportSection deathCauseReportSection;
	private BeneficiaryDeathReportSection motherGravidReportSection;
	
	private BeneficiaryDeathReportSection motherAgeReportSection;
	private BeneficiaryDeathReportSection economicConditionReportSection;
	
	private BeneficiaryDeathReportSection deathReportSectionForMother;
	
	public BeneficiaryDeathReportSection getDeathReportSectionForMother() {
		return deathReportSectionForMother;
	}

	public void setDeathReportSectionForMother(
			BeneficiaryDeathReportSection deathReportSectionForMother) {
		this.deathReportSectionForMother = deathReportSectionForMother;
	}

	public BeneficiaryDeathReportSection getDeathTimeReportSection ( )
	{
		return deathTimeReportSection;
	}
	
	public void setDeathTimeReportSection ( BeneficiaryDeathReportSection deathTimeReportSection )
	{
		this.deathTimeReportSection = deathTimeReportSection;
	}
	
	public BeneficiaryDeathReportSection getDeathPlaceReportSection ( )
	{
		return deathPlaceReportSection;
	}
	
	public void setDeathPlaceReportSection ( BeneficiaryDeathReportSection deathPlaceReportSection )
	{
		this.deathPlaceReportSection = deathPlaceReportSection;
	}
	
	public BeneficiaryDeathReportSection getPregStageReportSection ( )
	{
		return pregStageReportSection;
	}
	
	public void setPregStageReportSection ( BeneficiaryDeathReportSection pregStageReportSection )
	{
		this.pregStageReportSection = pregStageReportSection;
	}
	
	public BeneficiaryDeathReportSection getDeathCauseReportSection ( )
	{
		return deathCauseReportSection;
	}
	
	public void setDeathCauseReportSection ( BeneficiaryDeathReportSection deathCauseReportSection )
	{
		this.deathCauseReportSection = deathCauseReportSection;
	}
	
	public BeneficiaryDeathReportSection getMotherGravidReportSection ( )
	{
		return motherGravidReportSection;
	}
	
	public void setMotherGravidReportSection ( BeneficiaryDeathReportSection motherGravidReportSection )
	{
		this.motherGravidReportSection = motherGravidReportSection;
	}
	
	public BeneficiaryDeathReportSection getMotherAgeReportSection ( )
	{
		return motherAgeReportSection;
	}
	
	public void setMotherAgeReportSection ( BeneficiaryDeathReportSection motherAgeReportSection )
	{
		this.motherAgeReportSection = motherAgeReportSection;
	}
	
	public BeneficiaryDeathReportSection getEconomicConditionReportSection ( )
	{
		return economicConditionReportSection;
	}
	
	public void setEconomicConditionReportSection ( BeneficiaryDeathReportSection economicConditionReportSection )
	{
		this.economicConditionReportSection = economicConditionReportSection;
	}
	
}
