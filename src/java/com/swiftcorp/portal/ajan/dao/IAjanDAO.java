package com.swiftcorp.portal.ajan.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.ajan.AjanSuccessResult;
import com.swiftcorp.portal.ajan.dto.AjanDTO;
import com.swiftcorp.portal.ajan.exception.AjanNotFoundException;
import com.swiftcorp.portal.ajan.dao.IAjanDAO.AjanSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IAjanDAO 
{
  public enum AjanSortBy {uniqueCode, adminType, firstName, lastname};
  public enum AjanWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public AjanDTO get(Long componentId)throws SystemException; 
  public AjanDTO get(String unicodeCode)throws SystemException; 
  public AjanSuccessResult add(AjanDTO ajanDTO)throws SystemException;
  public AjanSuccessResult modify(AjanDTO ajanDTO)throws SystemException;
  public AjanSuccessResult remove(AjanDTO ajanDTO)throws SystemException;
  
  public ArrayList<AjanDTO> getList() throws SystemException;
  public ArrayList<AjanDTO> getList(Long groupId,AjanSortBy sortby) throws SystemException;
	
}
