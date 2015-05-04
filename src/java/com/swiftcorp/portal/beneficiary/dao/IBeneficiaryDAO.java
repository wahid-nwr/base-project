package com.swiftcorp.portal.beneficiary.dao;

import java.util.ArrayList;

import com.swiftcorp.portal.beneficiary.BeneficiarySuccessResult;
import com.swiftcorp.portal.beneficiary.dto.BeneficiaryDTO;
import com.swiftcorp.portal.common.exception.SystemException;


public interface IBeneficiaryDAO
{
	public enum BeneficiarySortBy
	{
		beneficiaryId, beneficiaryName, fatherName, husbandName
	};
	
	public enum BeneficiaryWhereCondition
	{
		uniqueCode, beneficiaryName, fatherName, husbandName
	};
	
	
	public BeneficiaryDTO get ( Long componentId ) throws SystemException;
	public BeneficiaryDTO getBeneficiaryByBenId ( String beneficiaryId ) throws SystemException;
	public ArrayList<BeneficiaryDTO> getList ( ) throws SystemException;
	public ArrayList<BeneficiaryDTO> getList ( Long groupId, BeneficiarySortBy sortby ) 	throws SystemException;
	public BeneficiarySuccessResult modifyBeneficiary ( BeneficiaryDTO beneficiaryDTO ) 	throws SystemException;
	public BeneficiarySuccessResult addBeneficiary ( BeneficiaryDTO beneficiaryDTO ) 	throws SystemException;
	
	
	
}
