package com.swiftcorp.portal.email.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.email.EmailSuccessResult;
import com.swiftcorp.portal.email.dto.EmailDTO;
import com.swiftcorp.portal.email.exception.EmailNotFoundException;
import com.swiftcorp.portal.email.dao.IEmailDAO.EmailSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IEmailDAO 
{
  public enum EmailSortBy {uniqueCode, adminType, firstName, lastname};
  public enum EmailWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public EmailDTO get(Long componentId)throws SystemException; 
  public EmailDTO get(String unicodeCode)throws SystemException; 
  public EmailSuccessResult add(EmailDTO emailDTO)throws SystemException;
  public EmailSuccessResult modify(EmailDTO emailDTO)throws SystemException;
  public EmailSuccessResult remove(EmailDTO emailDTO)throws SystemException;
  
  public ArrayList<EmailDTO> getList() throws SystemException;
  public ArrayList<EmailDTO> getList(Long groupId,EmailSortBy sortby) throws SystemException;
	
}
