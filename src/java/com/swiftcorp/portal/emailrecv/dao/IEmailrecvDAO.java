package com.swiftcorp.portal.emailrecv.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.emailrecv.EmailrecvSuccessResult;
import com.swiftcorp.portal.emailrecv.dto.EmailrecvDTO;
import com.swiftcorp.portal.emailrecv.exception.EmailrecvNotFoundException;
import com.swiftcorp.portal.emailrecv.dao.IEmailrecvDAO.EmailrecvSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IEmailrecvDAO 
{
  public enum EmailrecvSortBy {uniqueCode, adminType, firstName, lastname};
  public enum EmailrecvWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public EmailrecvDTO get(Long componentId)throws SystemException; 
  public EmailrecvDTO get(String unicodeCode)throws SystemException; 
  public EmailrecvSuccessResult add(EmailrecvDTO emailrecvDTO)throws SystemException;
  public EmailrecvSuccessResult modify(EmailrecvDTO emailrecvDTO)throws SystemException;
  public EmailrecvSuccessResult remove(EmailrecvDTO emailrecvDTO)throws SystemException;
  
  public ArrayList<EmailrecvDTO> getList() throws SystemException;
  public ArrayList<EmailrecvDTO> getList(Long groupId,EmailrecvSortBy sortby) throws SystemException;
	
}
