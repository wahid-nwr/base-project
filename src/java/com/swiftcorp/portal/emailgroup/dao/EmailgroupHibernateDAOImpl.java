package com.swiftcorp.portal.emailgroup.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.emailgroup.EmailgroupSuccessResult;
import com.swiftcorp.portal.emailgroup.dto.EmailgroupDTO;
import com.swiftcorp.portal.emailgroup.exception.EmailgroupAlreadyExistsException;
import com.swiftcorp.portal.emailgroup.exception.EmailgroupCreationException;
import com.swiftcorp.portal.emailgroup.exception.EmailgroupNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.emailgroup.dao.IEmailgroupDAO.EmailgroupSortBy;
import com.swiftcorp.portal.emailgroup.dao.IEmailgroupDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailgroupHibernateDAOImpl extends HibernateDaoSupport implements IEmailgroupDAO
{
	protected static final Log log = LogFactory.getLog(EmailgroupHibernateDAOImpl.class);
	public EmailgroupDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		EmailgroupDTO emailgroupDTO = null ;
		try
		{	
			emailgroupDTO = (EmailgroupDTO) getHibernateTemplate().get(EmailgroupDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return emailgroupDTO;
	}
	
	
	public EmailgroupDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		EmailgroupDTO emailgroupDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from EmailgroupDTO emailgroupDTO where emailgroupDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				emailgroupDTO = (EmailgroupDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return emailgroupDTO ;
	}
	
	
	public ArrayList<EmailgroupDTO> getList() throws SystemException 
	{	
		return getList(null,EmailgroupSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<EmailgroupDTO> getList(Long groupId,EmailgroupSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<EmailgroupDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT emailgroupDTO FROM EmailgroupDTO emailgroupDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE emailgroupDTO.groupId="+groupId);	
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
	
	
	public EmailgroupSuccessResult add(EmailgroupDTO emailgroupDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		EmailgroupSuccessResult successResult = new EmailgroupSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(emailgroupDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(emailgroupDTO);
		}
		catch(Exception e)
		{
			log.debug("add(EmailgroupDTO emailgroupDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public EmailgroupSuccessResult modify(EmailgroupDTO emailgroupDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		EmailgroupSuccessResult successResult = new EmailgroupSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(emailgroupDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(emailgroupDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(EmailgroupDTO emailgroupDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public EmailgroupSuccessResult remove(EmailgroupDTO emailgroupDTO) throws SystemException
	{
		log.info("remove(): Enter");
		EmailgroupSuccessResult successResult = new EmailgroupSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(emailgroupDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(emailgroupDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(EmailgroupDTO emailgroupDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(EmailgroupSortBy sortBy)
	{
		// default value
		String resultStr = "emailgroupDTO.uniqueCode" ;
		if(sortBy == EmailgroupSortBy.uniqueCode)
		{
			resultStr = "emailgroupDTO.uniqueCode" ;
		}
		else if(sortBy == EmailgroupSortBy.firstName)
		{
			resultStr = "emailgroupDTO.firstName" ;
		}
		else if(sortBy == EmailgroupSortBy.lastname)
		{
			resultStr = "emailgroupDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
