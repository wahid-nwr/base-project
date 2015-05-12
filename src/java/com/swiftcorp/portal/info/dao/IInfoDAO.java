package com.swiftcorp.portal.info.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.info.InfoSuccessResult;
import com.swiftcorp.portal.info.dto.InfoDTO;
import com.swiftcorp.portal.info.exception.InfoNotFoundException;
import com.swiftcorp.portal.info.dao.IInfoDAO.InfoSortBy;
public interface IInfoDAO 
{
  public enum InfoSortBy {uniqueCode, adminType, firstName, lastname};
  public enum InfoWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public InfoDTO get(Long componentId)throws SystemException; 
  public InfoDTO get(String unicodeCode)throws SystemException; 
  public InfoSuccessResult add(InfoDTO infoDTO)throws SystemException;
  public InfoSuccessResult modify(InfoDTO infoDTO)throws SystemException;
  public InfoSuccessResult remove(InfoDTO infoDTO)throws SystemException;
  
  public ArrayList<InfoDTO> getList() throws SystemException;
  public ArrayList<InfoDTO> getList(Long groupId,InfoSortBy sortby) throws SystemException;
	
}
