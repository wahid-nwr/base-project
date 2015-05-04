package com.swiftcorp.portal.dcrreport.dao;
import java.util.ArrayList;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.dcrreport.DcrReportSuccessResult;
import com.swiftcorp.portal.dcrreport.dao.IDcrReportDAO.DcrReportSortBy;
import com.swiftcorp.portal.dcrreport.dto.DcrReportDTO;
import com.swiftcorp.portal.dcrreport.exception.DcrReportNotFoundException;
public interface IDcrReportDAO 
{
  public enum DcrReportSortBy {uniqueCode, adminType, firstName, lastname,componentId};
  public enum DcrReportWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public DcrReportDTO get(Long componentId)throws SystemException; 
  public DcrReportDTO get(String unicodeCode)throws SystemException;
  public DcrReportDTO get()throws SystemException; 
  public DcrReportSuccessResult add(DcrReportDTO dcrreportDTO)throws SystemException;
  public DcrReportSuccessResult modify(DcrReportDTO dcrreportDTO)throws SystemException;
  public DcrReportSuccessResult remove(DcrReportDTO dcrreportDTO)throws SystemException;
  
  public ArrayList<DcrReportDTO> getList() throws SystemException;
  public ArrayList<DcrReportDTO> getList(Long groupId,DcrReportSortBy sortby) throws SystemException;
	
}
