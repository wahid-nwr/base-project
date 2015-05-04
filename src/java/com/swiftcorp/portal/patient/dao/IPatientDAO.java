package com.swiftcorp.portal.patient.dao;
import java.util.ArrayList;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.patient.PatientSuccessResult;
import com.swiftcorp.portal.patient.dao.IPatientDAO.PatientSortBy;
import com.swiftcorp.portal.patient.dto.PatientDTO;
import com.swiftcorp.portal.patient.exception.PatientNotFoundException;
public interface IPatientDAO 
{
  public enum PatientSortBy {uniqueCode, adminType, firstName, lastname};
  public enum PatientWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public PatientDTO get(Long componentId)throws SystemException; 
  public PatientDTO get(String unicodeCode)throws SystemException; 
  public PatientSuccessResult add(PatientDTO patientDTO)throws SystemException;
  public PatientSuccessResult modify(PatientDTO patientDTO)throws SystemException;
  public PatientSuccessResult remove(PatientDTO patientDTO)throws SystemException;
  
  public ArrayList<PatientDTO> getList() throws SystemException;
  public ArrayList<PatientDTO> getList(Long groupId,PatientSortBy sortby) throws SystemException;
	
}
