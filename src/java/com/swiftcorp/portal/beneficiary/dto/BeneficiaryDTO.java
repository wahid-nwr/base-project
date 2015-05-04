package com.swiftcorp.portal.beneficiary.dto;

import java.util.ArrayList;
import java.util.List;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class BeneficiaryDTO extends PersistentCapableDTO
{
	
	/**
	 * @author Arup
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// bene ficiary i
	private String beneficiaryId;
	
	
	private String beneficiaryName = null;
	private String beneficiaryNameFile = null;
	private String houseAddress = null;
	private String houseAddressFile = null;
	private String husbandName = null;
	private String husbandNameFile = null;
	private String fatherName = null;
	private String fatherNameFile = null;
	
	private String patientPictureFile = null;
	
	private String patientAge = null;
	private int maritalStatus;
	private int patientWeight;
	private String houseNumber = null;
	private int contactNumber;
	private int pregnantStatus;
	private int husbandAliveness;
	
	// beneficiary exam list
	List<BeneficiaryExamDTO> beneficiaryExamDTOList = new ArrayList<BeneficiaryExamDTO> ();
	
	// beneficiary history list
	List<BeneficiaryHistoryDTO> beneficiaryHistoryDTOList = new ArrayList<BeneficiaryHistoryDTO> ();
	
	
	public String getBeneficiaryId ( )
	{
		return beneficiaryId;
	}
	public void setBeneficiaryId ( String beneficiaryId )
	{
		this.beneficiaryId = beneficiaryId;
	}
	
	public String getHouseAddress ( )
	{
		return houseAddress;
	}
	public void setHouseAddress ( String houseAddress )
	{
		this.houseAddress = houseAddress;
	}
	public String getHouseAddressFile ( )
	{
		return houseAddressFile;
	}
	public void setHouseAddressFile ( String houseAddressFile )
	{
		this.houseAddressFile = houseAddressFile;
	}
	public String getHusbandName ( )
	{
		return husbandName;
	}
	public void setHusbandName ( String husbandName )
	{
		this.husbandName = husbandName;
	}
	public String getHusbandNameFile ( )
	{
		return husbandNameFile;
	}
	public void setHusbandNameFile ( String husbandNameFile )
	{
		this.husbandNameFile = husbandNameFile;
	}
	public String getFatherName ( )
	{
		return fatherName;
	}
	public void setFatherName ( String fatherName )
	{
		this.fatherName = fatherName;
	}
	public String getFatherNameFile ( )
	{
		return fatherNameFile;
	}
	public void setFatherNameFile ( String fatherNameFile )
	{
		this.fatherNameFile = fatherNameFile;
	}
	public String getPatientPictureFile ( )
	{
		return patientPictureFile;
	}
	public void setPatientPictureFile ( String patientPictureFile )
	{
		this.patientPictureFile = patientPictureFile;
	}
	public String getPatientAge ( )
	{
		return patientAge;
	}
	public void setPatientAge ( String patientAge )
	{
		this.patientAge = patientAge;
	}
	public int getMaritalStatus ( )
	{
		return maritalStatus;
	}
	public void setMaritalStatus ( int maritalStatus )
	{
		this.maritalStatus = maritalStatus;
	}
	public int getPatientWeight ( )
	{
		return patientWeight;
	}
	public void setPatientWeight ( int patientWeight )
	{
		this.patientWeight = patientWeight;
	}
	public String getHouseNumber ( )
	{
		return houseNumber;
	}
	public void setHouseNumber ( String houseNumber )
	{
		this.houseNumber = houseNumber;
	}
	public int getContactNumber ( )
	{
		return contactNumber;
	}
	public void setContactNumber ( int contactNumber )
	{
		this.contactNumber = contactNumber;
	}
	public int getPregnantStatus ( )
	{
		return pregnantStatus;
	}
	public void setPregnantStatus ( int pregnantStatus )
	{
		this.pregnantStatus = pregnantStatus;
	}
	public int getHusbandAliveness ( )
	{
		return husbandAliveness;
	}
	public void setHusbandAliveness ( int husbandAliveness )
	{
		this.husbandAliveness = husbandAliveness;
	}
	public List<BeneficiaryExamDTO> getBeneficiaryExamDTOList ( )
	{
		return beneficiaryExamDTOList;
	}
	public void setBeneficiaryExamDTOList ( List<BeneficiaryExamDTO> beneficiaryExamDTOList )
	{
		this.beneficiaryExamDTOList = beneficiaryExamDTOList;
	}
	public List<BeneficiaryHistoryDTO> getBeneficiaryHistoryDTOList ( )
	{
		return beneficiaryHistoryDTOList;
	}
	public void setBeneficiaryHistoryDTOList ( List<BeneficiaryHistoryDTO> beneficiaryHistoryDTOList )
	{
		this.beneficiaryHistoryDTOList = beneficiaryHistoryDTOList;
	}
	public String getBeneficiaryName ( )
	{
		return beneficiaryName;
	}
	public void setBeneficiaryName ( String beneficiaryName )
	{
		this.beneficiaryName = beneficiaryName;
	}
	public String getBeneficiaryNameFile ( )
	{
		return beneficiaryNameFile;
	}
	public void setBeneficiaryNameFile ( String beneficiaryNameFile )
	{
		this.beneficiaryNameFile = beneficiaryNameFile;
	}
	
}
