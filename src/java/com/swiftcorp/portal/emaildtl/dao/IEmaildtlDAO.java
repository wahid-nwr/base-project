package com.swiftcorp.portal.emaildtl.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.emaildtl.EmaildtlSuccessResult;
import com.swiftcorp.portal.emaildtl.dto.EmaildtlDTO;
import com.swiftcorp.portal.emaildtl.exception.EmaildtlNotFoundException;
import com.swiftcorp.portal.emaildtl.dao.IEmaildtlDAO.EmaildtlSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IEmaildtlDAO 
{
  public enum EmaildtlSortBy {uniqueCode, adminType, firstName, lastname};
  public enum EmaildtlWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public EmaildtlDTO get(Long componentId)throws SystemException; 
  public EmaildtlDTO get(String unicodeCode)throws SystemException; 
  public EmaildtlSuccessResult add(EmaildtlDTO emaildtlDTO)throws SystemException;
  public EmaildtlSuccessResult modify(EmaildtlDTO emaildtlDTO)throws SystemException;
  public EmaildtlSuccessResult remove(EmaildtlDTO emaildtlDTO)throws SystemException;
  
  public ArrayList<EmaildtlDTO> getList() throws SystemException;
  public ArrayList<EmaildtlDTO> getList(Long groupId,EmaildtlSortBy sortby) throws SystemException;
	
}
