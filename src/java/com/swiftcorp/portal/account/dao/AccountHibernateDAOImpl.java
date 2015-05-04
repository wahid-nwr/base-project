package com.swiftcorp.portal.account.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.account.AccountSuccessResult;
import com.swiftcorp.portal.account.dao.IAccountDAO;
import com.swiftcorp.portal.account.dao.IAccountDAO.AccountSortBy;
import com.swiftcorp.portal.account.dto.AccountDTO;
import com.swiftcorp.portal.account.exception.AccountAlreadyExistsException;
import com.swiftcorp.portal.account.exception.AccountCreationException;
import com.swiftcorp.portal.account.exception.AccountNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
public class AccountHibernateDAOImpl extends HibernateDaoSupport implements IAccountDAO
{
	protected static final Log log = LogFactory.getLog(AccountHibernateDAOImpl.class);
	public AccountDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		AccountDTO accountDTO = null ;
		try
		{	
			accountDTO = (AccountDTO) getHibernateTemplate().get(AccountDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return accountDTO;
	}
	
	
	public AccountDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		AccountDTO accountDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from AccountDTO accountDTO where accountDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				accountDTO = (AccountDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return accountDTO ;
	}
	
	
	public ArrayList<AccountDTO> getList() throws SystemException 
	{	
		return getList(null,AccountSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<AccountDTO> getList(Long groupId,AccountSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<AccountDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT accountDTO FROM AccountDTO accountDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE accountDTO.groupId="+groupId);	
		}
		queryStr.append(" ORDER BY ");
		queryStr.append(getSortByStr(sortby));
		try
		{	
			result = (ArrayList) getHibernateTemplate().find(queryStr.toString());
			log.info("getList(): size = "+ result.size());
		}
		catch (Exception e) 
		{
			throw new SystemException(e);
		}
		log.info("getList: Exit");
		return result;
	}
	
	
	public AccountSuccessResult add(AccountDTO accountDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		AccountSuccessResult successResult = new AccountSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(accountDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(accountDTO);
		}
		catch(Exception e)
		{
			log.debug("add(AccountDTO accountDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public AccountSuccessResult modify(AccountDTO accountDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		AccountSuccessResult successResult = new AccountSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(accountDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(accountDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(AccountDTO accountDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public AccountSuccessResult remove(AccountDTO accountDTO) throws SystemException
	{
		log.info("remove(): Enter");
		AccountSuccessResult successResult = new AccountSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(accountDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(accountDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(AccountDTO accountDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(AccountSortBy sortBy)
	{
		// default value
		String resultStr = "accountDTO.uniqueCode" ;
		if(sortBy == AccountSortBy.uniqueCode)
		{
			resultStr = "accountDTO.uniqueCode" ;
		}
		else if(sortBy == AccountSortBy.firstName)
		{
			resultStr = "accountDTO.firstName" ;
		}
		else if(sortBy == AccountSortBy.lastname)
		{
			resultStr = "accountDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
