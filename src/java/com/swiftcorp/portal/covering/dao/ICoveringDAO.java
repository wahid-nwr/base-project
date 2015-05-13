package com.swiftcorp.portal.covering.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.covering.CoveringSuccessResult;
import com.swiftcorp.portal.covering.dto.CoveringDTO;
import com.swiftcorp.portal.covering.exception.CoveringNotFoundException;
import com.swiftcorp.portal.covering.dao.ICoveringDAO.CoveringSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface ICoveringDAO 
{
  public enum CoveringSortBy {uniqueCode, adminType, firstName, lastname};
  public enum CoveringWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public CoveringDTO get(Long componentId)throws SystemException; 
  public CoveringDTO get(String unicodeCode)throws SystemException; 
  public CoveringSuccessResult add(CoveringDTO coveringDTO)throws SystemException;
  public CoveringSuccessResult modify(CoveringDTO coveringDTO)throws SystemException;
  public CoveringSuccessResult remove(CoveringDTO coveringDTO)throws SystemException;
  
  public ArrayList<CoveringDTO> getList() throws SystemException;
  public ArrayList<CoveringDTO> getList(Long groupId,CoveringSortBy sortby) throws SystemException;
	
}
