package com.swiftcorp.portal.emailreferance.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.emailreferance.EmailreferanceSuccessResult;
import com.swiftcorp.portal.emailreferance.dto.EmailreferanceDTO;
import com.swiftcorp.portal.emailreferance.exception.EmailreferanceAlreadyExistsException;
import com.swiftcorp.portal.emailreferance.exception.EmailreferanceCreationException;
import com.swiftcorp.portal.emailreferance.exception.EmailreferanceNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.emailreferance.dao.IEmailreferanceDAO.EmailreferanceSortBy;
import com.swiftcorp.portal.emailreferance.dao.IEmailreferanceDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailreferanceHibernateDAOImpl extends HibernateDaoSupport implements IEmailreferanceDAO
{
	protected static final Log log = LogFactory.getLog(EmailreferanceHibernateDAOImpl.class);
	public EmailreferanceDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		EmailreferanceDTO emailreferanceDTO = null ;
		try
		{	
			emailreferanceDTO = (EmailreferanceDTO) getHibernateTemplate().get(EmailreferanceDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return emailreferanceDTO;
	}
	
	
	public EmailreferanceDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		EmailreferanceDTO emailreferanceDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from EmailreferanceDTO emailreferanceDTO where emailreferanceDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				emailreferanceDTO = (EmailreferanceDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return emailreferanceDTO ;
	}
	
	
	public ArrayList<EmailreferanceDTO> getList() throws SystemException 
	{	
		return getList(null,EmailreferanceSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<EmailreferanceDTO> getList(Long groupId,EmailreferanceSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<EmailreferanceDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT emailreferanceDTO FROM EmailreferanceDTO emailreferanceDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE emailreferanceDTO.groupId="+groupId);	
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
	
	
	public EmailreferanceSuccessResult add(EmailreferanceDTO emailreferanceDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		EmailreferanceSuccessResult successResult = new EmailreferanceSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(emailreferanceDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(emailreferanceDTO);
		}
		catch(Exception e)
		{
			log.debug("add(EmailreferanceDTO emailreferanceDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public EmailreferanceSuccessResult modify(EmailreferanceDTO emailreferanceDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		EmailreferanceSuccessResult successResult = new EmailreferanceSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(emailreferanceDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(emailreferanceDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(EmailreferanceDTO emailreferanceDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public EmailreferanceSuccessResult remove(EmailreferanceDTO emailreferanceDTO) throws SystemException
	{
		log.info("remove(): Enter");
		EmailreferanceSuccessResult successResult = new EmailreferanceSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(emailreferanceDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(emailreferanceDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(EmailreferanceDTO emailreferanceDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(EmailreferanceSortBy sortBy)
	{
		// default value
		String resultStr = "emailreferanceDTO.uniqueCode" ;
		if(sortBy == EmailreferanceSortBy.uniqueCode)
		{
			resultStr = "emailreferanceDTO.uniqueCode" ;
		}
		else if(sortBy == EmailreferanceSortBy.firstName)
		{
			resultStr = "emailreferanceDTO.firstName" ;
		}
		else if(sortBy == EmailreferanceSortBy.lastname)
		{
			resultStr = "emailreferanceDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
