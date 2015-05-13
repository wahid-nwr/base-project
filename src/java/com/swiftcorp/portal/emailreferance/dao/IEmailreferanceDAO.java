package com.swiftcorp.portal.emailreferance.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.emailreferance.EmailreferanceSuccessResult;
import com.swiftcorp.portal.emailreferance.dto.EmailreferanceDTO;
import com.swiftcorp.portal.emailreferance.exception.EmailreferanceNotFoundException;
import com.swiftcorp.portal.emailreferance.dao.IEmailreferanceDAO.EmailreferanceSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IEmailreferanceDAO 
{
  public enum EmailreferanceSortBy {uniqueCode, adminType, firstName, lastname};
  public enum EmailreferanceWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public EmailreferanceDTO get(Long componentId)throws SystemException; 
  public EmailreferanceDTO get(String unicodeCode)throws SystemException; 
  public EmailreferanceSuccessResult add(EmailreferanceDTO emailreferanceDTO)throws SystemException;
  public EmailreferanceSuccessResult modify(EmailreferanceDTO emailreferanceDTO)throws SystemException;
  public EmailreferanceSuccessResult remove(EmailreferanceDTO emailreferanceDTO)throws SystemException;
  
  public ArrayList<EmailreferanceDTO> getList() throws SystemException;
  public ArrayList<EmailreferanceDTO> getList(Long groupId,EmailreferanceSortBy sortby) throws SystemException;
	
}
