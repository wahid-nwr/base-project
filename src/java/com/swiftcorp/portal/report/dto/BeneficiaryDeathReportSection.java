/**
 * 
 */
package com.swiftcorp.portal.report.dto;

import java.util.List;

/**
 * @author asraful.haque
 * 
 */
public class BeneficiaryDeathReportSection extends AbstractReportSection
{

	

	public BeneficiaryDeathReportSection (  )
	{
	}
	
	public BeneficiaryDeathReportSection ( String sectionName )
	{
		this.name = sectionName;
	}
	
	
	
	// list of record
	private List<BeneficiaryDeathRecord> beneficiaryDeathRecordList;
	
	public List<BeneficiaryDeathRecord> getBeneficiaryDeathRecordList ( )
	{
		return beneficiaryDeathRecordList;
	}
	
	public void setBeneficiaryDeathRecordList ( List<BeneficiaryDeathRecord> beneficiaryDeathRecordList )
	{
		this.beneficiaryDeathRecordList = beneficiaryDeathRecordList;

		
	}
}
