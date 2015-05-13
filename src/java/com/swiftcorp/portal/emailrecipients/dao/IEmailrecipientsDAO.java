package com.swiftcorp.portal.emailrecipients.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.emailrecipients.EmailrecipientsSuccessResult;
import com.swiftcorp.portal.emailrecipients.dto.EmailrecipientsDTO;
import com.swiftcorp.portal.emailrecipients.exception.EmailrecipientsNotFoundException;
import com.swiftcorp.portal.emailrecipients.dao.IEmailrecipientsDAO.EmailrecipientsSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IEmailrecipientsDAO 
{
  public enum EmailrecipientsSortBy {uniqueCode, adminType, firstName, lastname};
  public enum EmailrecipientsWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public EmailrecipientsDTO get(Long componentId)throws SystemException; 
  public EmailrecipientsDTO get(String unicodeCode)throws SystemException; 
  public EmailrecipientsSuccessResult add(EmailrecipientsDTO emailrecipientsDTO)throws SystemException;
  public EmailrecipientsSuccessResult modify(EmailrecipientsDTO emailrecipientsDTO)throws SystemException;
  public EmailrecipientsSuccessResult remove(EmailrecipientsDTO emailrecipientsDTO)throws SystemException;
  
  public ArrayList<EmailrecipientsDTO> getList() throws SystemException;
  public ArrayList<EmailrecipientsDTO> getList(Long groupId,EmailrecipientsSortBy sortby) throws SystemException;
	
}
