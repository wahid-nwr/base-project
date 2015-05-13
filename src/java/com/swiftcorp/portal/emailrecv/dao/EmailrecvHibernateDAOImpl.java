package com.swiftcorp.portal.emailrecv.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.emailrecv.EmailrecvSuccessResult;
import com.swiftcorp.portal.emailrecv.dto.EmailrecvDTO;
import com.swiftcorp.portal.emailrecv.exception.EmailrecvAlreadyExistsException;
import com.swiftcorp.portal.emailrecv.exception.EmailrecvCreationException;
import com.swiftcorp.portal.emailrecv.exception.EmailrecvNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.emailrecv.dao.IEmailrecvDAO.EmailrecvSortBy;
import com.swiftcorp.portal.emailrecv.dao.IEmailrecvDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmailrecvHibernateDAOImpl extends HibernateDaoSupport implements IEmailrecvDAO
{
	protected static final Log log = LogFactory.getLog(EmailrecvHibernateDAOImpl.class);
	public EmailrecvDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		EmailrecvDTO emailrecvDTO = null ;
		try
		{	
			emailrecvDTO = (EmailrecvDTO) getHibernateTemplate().get(EmailrecvDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return emailrecvDTO;
	}
	
	
	public EmailrecvDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		EmailrecvDTO emailrecvDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from EmailrecvDTO emailrecvDTO where emailrecvDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				emailrecvDTO = (EmailrecvDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return emailrecvDTO ;
	}
	
	
	public ArrayList<EmailrecvDTO> getList() throws SystemException 
	{	
		return getList(null,EmailrecvSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<EmailrecvDTO> getList(Long groupId,EmailrecvSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<EmailrecvDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT emailrecvDTO FROM EmailrecvDTO emailrecvDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE emailrecvDTO.groupId="+groupId);	
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
	
	
	public EmailrecvSuccessResult add(EmailrecvDTO emailrecvDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		EmailrecvSuccessResult successResult = new EmailrecvSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(emailrecvDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(emailrecvDTO);
		}
		catch(Exception e)
		{
			log.debug("add(EmailrecvDTO emailrecvDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public EmailrecvSuccessResult modify(EmailrecvDTO emailrecvDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		EmailrecvSuccessResult successResult = new EmailrecvSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(emailrecvDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(emailrecvDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(EmailrecvDTO emailrecvDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public EmailrecvSuccessResult remove(EmailrecvDTO emailrecvDTO) throws SystemException
	{
		log.info("remove(): Enter");
		EmailrecvSuccessResult successResult = new EmailrecvSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(emailrecvDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(emailrecvDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(EmailrecvDTO emailrecvDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(EmailrecvSortBy sortBy)
	{
		// default value
		String resultStr = "emailrecvDTO.uniqueCode" ;
		if(sortBy == EmailrecvSortBy.uniqueCode)
		{
			resultStr = "emailrecvDTO.uniqueCode" ;
		}
		else if(sortBy == EmailrecvSortBy.firstName)
		{
			resultStr = "emailrecvDTO.firstName" ;
		}
		else if(sortBy == EmailrecvSortBy.lastname)
		{
			resultStr = "emailrecvDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
