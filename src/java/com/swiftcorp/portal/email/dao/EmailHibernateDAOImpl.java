package com.swiftcorp.portal.email.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.email.EmailSuccessResult;
import com.swiftcorp.portal.email.dto.EmailDTO;
import com.swiftcorp.portal.email.exception.EmailAlreadyExistsException;
import com.swiftcorp.portal.email.exception.EmailCreationException;
import com.swiftcorp.portal.email.exception.EmailNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.email.dao.IEmailDAO.EmailSortBy;
import com.swiftcorp.portal.email.dao.IEmailDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailHibernateDAOImpl extends HibernateDaoSupport implements IEmailDAO
{
	protected static final Log log = LogFactory.getLog(EmailHibernateDAOImpl.class);
	public EmailDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		EmailDTO emailDTO = null ;
		try
		{	
			emailDTO = (EmailDTO) getHibernateTemplate().get(EmailDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return emailDTO;
	}
	
	
	public EmailDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		EmailDTO emailDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from EmailDTO emailDTO where emailDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				emailDTO = (EmailDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return emailDTO ;
	}
	
	
	public ArrayList<EmailDTO> getList() throws SystemException 
	{	
		return getList(null,EmailSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<EmailDTO> getList(Long groupId,EmailSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<EmailDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT emailDTO FROM EmailDTO emailDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE emailDTO.groupId="+groupId);	
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
	
	
	public EmailSuccessResult add(EmailDTO emailDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		EmailSuccessResult successResult = new EmailSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(emailDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(emailDTO);
		}
		catch(Exception e)
		{
			log.debug("add(EmailDTO emailDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public EmailSuccessResult modify(EmailDTO emailDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		EmailSuccessResult successResult = new EmailSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(emailDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(emailDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(EmailDTO emailDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public EmailSuccessResult remove(EmailDTO emailDTO) throws SystemException
	{
		log.info("remove(): Enter");
		EmailSuccessResult successResult = new EmailSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(emailDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(emailDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(EmailDTO emailDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(EmailSortBy sortBy)
	{
		// default value
		String resultStr = "emailDTO.uniqueCode" ;
		if(sortBy == EmailSortBy.uniqueCode)
		{
			resultStr = "emailDTO.uniqueCode" ;
		}
		else if(sortBy == EmailSortBy.firstName)
		{
			resultStr = "emailDTO.firstName" ;
		}
		else if(sortBy == EmailSortBy.lastname)
		{
			resultStr = "emailDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
