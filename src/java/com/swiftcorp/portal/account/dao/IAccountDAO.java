package com.swiftcorp.portal.account.dao;
import java.util.ArrayList;

import com.swiftcorp.portal.account.AccountSuccessResult;
import com.swiftcorp.portal.account.dao.IAccountDAO.AccountSortBy;
import com.swiftcorp.portal.account.dto.AccountDTO;
import com.swiftcorp.portal.account.exception.AccountNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
public interface IAccountDAO 
{
  public enum AccountSortBy {uniqueCode, adminType, firstName, lastname};
  public enum AccountWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public AccountDTO get(Long componentId)throws SystemException; 
  public AccountDTO get(String unicodeCode)throws SystemException; 
  public AccountSuccessResult add(AccountDTO accountDTO)throws SystemException;
  public AccountSuccessResult modify(AccountDTO accountDTO)throws SystemException;
  public AccountSuccessResult remove(AccountDTO accountDTO)throws SystemException;
  
  public ArrayList<AccountDTO> getList() throws SystemException;
  public ArrayList<AccountDTO> getList(Long groupId,AccountSortBy sortby) throws SystemException;
	
}
