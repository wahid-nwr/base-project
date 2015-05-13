package com.swiftcorp.portal.mailinfo.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.mailinfo.MailinfoSuccessResult;
import com.swiftcorp.portal.mailinfo.dto.MailinfoDTO;
import com.swiftcorp.portal.mailinfo.exception.MailinfoNotFoundException;
import com.swiftcorp.portal.mailinfo.dao.IMailinfoDAO.MailinfoSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IMailinfoDAO 
{
  public enum MailinfoSortBy {uniqueCode, adminType, firstName, lastname};
  public enum MailinfoWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public MailinfoDTO get(Long componentId)throws SystemException; 
  public MailinfoDTO get(String unicodeCode)throws SystemException; 
  public MailinfoSuccessResult add(MailinfoDTO mailinfoDTO)throws SystemException;
  public MailinfoSuccessResult modify(MailinfoDTO mailinfoDTO)throws SystemException;
  public MailinfoSuccessResult remove(MailinfoDTO mailinfoDTO)throws SystemException;
  
  public ArrayList<MailinfoDTO> getList() throws SystemException;
  public ArrayList<MailinfoDTO> getList(Long groupId,MailinfoSortBy sortby) throws SystemException;
	
}
