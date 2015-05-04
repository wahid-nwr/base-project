package com.swiftcorp.portal.beneficiary.dto;

import java.util.Calendar;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class BeneficiaryHistoryDTO extends PersistentCapableDTO
{
	
	
	private static final long serialVersionUID = 1L;
	
	private int breastMass;
	private int breastMassOperation;
	private int lumpDifferent;
	private Calendar lumpDifferentLongivity;
	private String physicalExaminationFile = null;
	
	// beneficiary -- it will be used as foreign key
	private long beneficiary;
	
	public int getBreastMass ( )
	{
		return breastMass;
	}
	public void setBreastMass ( int breastMass )
	{
		this.breastMass = breastMass;
	}
	
	
	public int getBreastMassOperation ( )
	{
		return breastMassOperation;
	}
	public void setBreastMassOperation ( int breastMassOperation )
	{
		this.breastMassOperation = breastMassOperation;
	}
	
	
	public int getLumpDifferent ( )
	{
		return lumpDifferent;
	}
	public void setLumpDifferent ( int lumpDifferent )
	{
		this.lumpDifferent = lumpDifferent;
	}
	
	
	public Calendar getLumpDifferentLongivity ( )
	{
		return lumpDifferentLongivity;
	}
	public void setLumpDifferentLongivity ( Calendar lumpDifferentLongivity )
	{
		this.lumpDifferentLongivity = lumpDifferentLongivity;
	}
	
	
	public String getPhysicalExaminationFile ( )
	{
		return physicalExaminationFile;
	}
	public void setPhysicalExaminationFile ( String physicalExaminationFile )
	{
		this.physicalExaminationFile = physicalExaminationFile;
	}
	public long getBeneficiary ( )
	{
		return beneficiary;
	}
	public void setBeneficiary ( long beneficiary )
	{
		this.beneficiary = beneficiary;
	}
	
}
