package com.swiftcorp.portal.emailrecipients.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.emailrecipients.EmailrecipientsSuccessResult;
import com.swiftcorp.portal.emailrecipients.dto.EmailrecipientsDTO;
import com.swiftcorp.portal.emailrecipients.exception.EmailrecipientsAlreadyExistsException;
import com.swiftcorp.portal.emailrecipients.exception.EmailrecipientsCreationException;
import com.swiftcorp.portal.emailrecipients.exception.EmailrecipientsNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.emailrecipients.dao.IEmailrecipientsDAO.EmailrecipientsSortBy;
import com.swiftcorp.portal.emailrecipients.dao.IEmailrecipientsDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailrecipientsHibernateDAOImpl extends HibernateDaoSupport implements IEmailrecipientsDAO
{
	protected static final Log log = LogFactory.getLog(EmailrecipientsHibernateDAOImpl.class);
	public EmailrecipientsDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		EmailrecipientsDTO emailrecipientsDTO = null ;
		try
		{	
			emailrecipientsDTO = (EmailrecipientsDTO) getHibernateTemplate().get(EmailrecipientsDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return emailrecipientsDTO;
	}
	
	
	public EmailrecipientsDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		EmailrecipientsDTO emailrecipientsDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from EmailrecipientsDTO emailrecipientsDTO where emailrecipientsDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				emailrecipientsDTO = (EmailrecipientsDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return emailrecipientsDTO ;
	}
	
	
	public ArrayList<EmailrecipientsDTO> getList() throws SystemException 
	{	
		return getList(null,EmailrecipientsSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<EmailrecipientsDTO> getList(Long groupId,EmailrecipientsSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<EmailrecipientsDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT emailrecipientsDTO FROM EmailrecipientsDTO emailrecipientsDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE emailrecipientsDTO.groupId="+groupId);	
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
	
	
	public EmailrecipientsSuccessResult add(EmailrecipientsDTO emailrecipientsDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		EmailrecipientsSuccessResult successResult = new EmailrecipientsSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(emailrecipientsDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(emailrecipientsDTO);
		}
		catch(Exception e)
		{
			log.debug("add(EmailrecipientsDTO emailrecipientsDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public EmailrecipientsSuccessResult modify(EmailrecipientsDTO emailrecipientsDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		EmailrecipientsSuccessResult successResult = new EmailrecipientsSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(emailrecipientsDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(emailrecipientsDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(EmailrecipientsDTO emailrecipientsDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public EmailrecipientsSuccessResult remove(EmailrecipientsDTO emailrecipientsDTO) throws SystemException
	{
		log.info("remove(): Enter");
		EmailrecipientsSuccessResult successResult = new EmailrecipientsSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(emailrecipientsDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(emailrecipientsDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(EmailrecipientsDTO emailrecipientsDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(EmailrecipientsSortBy sortBy)
	{
		// default value
		String resultStr = "emailrecipientsDTO.uniqueCode" ;
		if(sortBy == EmailrecipientsSortBy.uniqueCode)
		{
			resultStr = "emailrecipientsDTO.uniqueCode" ;
		}
		else if(sortBy == EmailrecipientsSortBy.firstName)
		{
			resultStr = "emailrecipientsDTO.firstName" ;
		}
		else if(sortBy == EmailrecipientsSortBy.lastname)
		{
			resultStr = "emailrecipientsDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
