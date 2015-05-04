package com.swiftcorp.portal.beneficiary.dto;

import java.util.Calendar;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class BeneficiaryExamDTO extends PersistentCapableDTO
{
	
	
	private static final long serialVersionUID = 1L;
	
	private String rightBreastExResult = null;
	private String rightMassOrLesionPictureAccessorFile = null;
	private String leftBreastExResult = null;
	private String leftMassOrLesionPictureFile = null;
	private String advice = null;
	
	private int rightBreastMassSize;
	private int leftBreastMassSize;
	private int underarmLump;
	private int underarmLumpSize;
	
	private Calendar nextDate;

	// beneficiary -- it will be used as foreign key
	private long beneficiary;
	
	public String getRightBreastExResult ( )
	{
		return rightBreastExResult;
	}

	public void setRightBreastExResult ( String rightBreastExResult )
	{
		this.rightBreastExResult = rightBreastExResult;
	}

	
	
	public String getRightMassOrLesionPictureAccessorFile ( )
	{
		return rightMassOrLesionPictureAccessorFile;
	}

	public void setRightMassOrLesionPictureAccessorFile ( String rightMassOrLesionPictureAccessorFile )
	{
		this.rightMassOrLesionPictureAccessorFile = rightMassOrLesionPictureAccessorFile;
	}

	
	
	public String getLeftBreastExResult ( )
	{
		return leftBreastExResult;
	}

	public void setLeftBreastExResult ( String leftBreastExResult )
	{
		this.leftBreastExResult = leftBreastExResult;
	}

	
	
	public String getLeftMassOrLesionPictureFile ( )
	{
		return leftMassOrLesionPictureFile;
	}

	public void setLeftMassOrLesionPictureFile ( String leftMassOrLesionPictureFile )
	{
		this.leftMassOrLesionPictureFile = leftMassOrLesionPictureFile;
	}

	
	
	public int getRightBreastMassSize ( )
	{
		return rightBreastMassSize;
	}

	public void setRightBreastMassSize ( int rightBreastMassSize )
	{
		this.rightBreastMassSize = rightBreastMassSize;
	}

	
	
	public int getLeftBreastMassSize ( )
	{
		return leftBreastMassSize;
	}

	public void setLeftBreastMassSize ( int leftBreastMassSize )
	{
		this.leftBreastMassSize = leftBreastMassSize;
	}

	
	
	public int getUnderarmLump ( )
	{
		return underarmLump;
	}

	public void setUnderarmLump ( int underarmLump )
	{
		this.underarmLump = underarmLump;
	}

	
	
	public int getUnderarmLumpSize ( )
	{
		return underarmLumpSize;
	}

	public void setUnderarmLumpSize ( int underarmLumpSize )
	{
		this.underarmLumpSize = underarmLumpSize;
	}

	
	
	public Calendar getNextDate ( )
	{
		return nextDate;
	}

	public void setNextDate ( Calendar nextDate )
	{
		this.nextDate = nextDate;
	}

	public String getAdvice ( )
	{
		return advice;
	}

	public void setAdvice ( String advice )
	{
		this.advice = advice;
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
