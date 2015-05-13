package com.swiftcorp.portal.emailgroup.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.emailgroup.EmailgroupSuccessResult;
import com.swiftcorp.portal.emailgroup.dto.EmailgroupDTO;
import com.swiftcorp.portal.emailgroup.exception.EmailgroupNotFoundException;
import com.swiftcorp.portal.emailgroup.dao.IEmailgroupDAO.EmailgroupSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IEmailgroupDAO 
{
  public enum EmailgroupSortBy {uniqueCode, adminType, firstName, lastname};
  public enum EmailgroupWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public EmailgroupDTO get(Long componentId)throws SystemException; 
  public EmailgroupDTO get(String unicodeCode)throws SystemException; 
  public EmailgroupSuccessResult add(EmailgroupDTO emailgroupDTO)throws SystemException;
  public EmailgroupSuccessResult modify(EmailgroupDTO emailgroupDTO)throws SystemException;
  public EmailgroupSuccessResult remove(EmailgroupDTO emailgroupDTO)throws SystemException;
  
  public ArrayList<EmailgroupDTO> getList() throws SystemException;
  public ArrayList<EmailgroupDTO> getList(Long groupId,EmailgroupSortBy sortby) throws SystemException;
	
}
