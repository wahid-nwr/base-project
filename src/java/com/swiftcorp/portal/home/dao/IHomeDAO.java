package com.swiftcorp.portal.home.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.home.HomeSuccessResult;
import com.swiftcorp.portal.home.dto.HomeDTO;
import com.swiftcorp.portal.home.exception.HomeNotFoundException;
import com.swiftcorp.portal.home.dao.IHomeDAO.HomeSortBy;
public interface IHomeDAO 
{
  public enum HomeSortBy {uniqueCode, adminType, firstName, lastname};
  public enum HomeWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public HomeDTO get(Long componentId)throws SystemException; 
  public HomeDTO get(String unicodeCode)throws SystemException; 
  public HomeSuccessResult add(HomeDTO homeDTO)throws SystemException;
  public HomeSuccessResult modify(HomeDTO homeDTO)throws SystemException;
  public HomeSuccessResult remove(HomeDTO homeDTO)throws SystemException;
  
  public ArrayList<HomeDTO> getList() throws SystemException;
  public ArrayList<HomeDTO> getList(Long groupId,HomeSortBy sortby) throws SystemException;
	
}
