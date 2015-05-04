package com.swiftcorp.portal.dcrinfo.dao;
import java.util.ArrayList;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.dcrinfo.DcrinfoSuccessResult;
import com.swiftcorp.portal.dcrinfo.dao.IDcrinfoDAO.DcrinfoSortBy;
import com.swiftcorp.portal.dcrinfo.dto.DcrinfoDTO;
import com.swiftcorp.portal.dcrinfo.exception.DcrinfoNotFoundException;
public interface IDcrinfoDAO 
{
  public enum DcrinfoSortBy {uniqueCode, adminType, firstName, lastname};
  public enum DcrinfoWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public DcrinfoDTO get(Long componentId)throws SystemException; 
  public DcrinfoDTO get(String unicodeCode)throws SystemException; 
  public DcrinfoSuccessResult add(DcrinfoDTO dcrinfoDTO)throws SystemException;
  public DcrinfoSuccessResult modify(DcrinfoDTO dcrinfoDTO)throws SystemException;
  public DcrinfoSuccessResult remove(DcrinfoDTO dcrinfoDTO)throws SystemException;
  
  public ArrayList<DcrinfoDTO> getList() throws SystemException;
  public ArrayList<DcrinfoDTO> getList(Long groupId,DcrinfoSortBy sortby) throws SystemException;
	
}
