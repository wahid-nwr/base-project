package com.swiftcorp.portal.emaildtl.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.emaildtl.EmaildtlSuccessResult;
import com.swiftcorp.portal.emaildtl.dto.EmaildtlDTO;
import com.swiftcorp.portal.emaildtl.exception.EmaildtlAlreadyExistsException;
import com.swiftcorp.portal.emaildtl.exception.EmaildtlCreationException;
import com.swiftcorp.portal.emaildtl.exception.EmaildtlNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.emaildtl.dao.IEmaildtlDAO.EmaildtlSortBy;
import com.swiftcorp.portal.emaildtl.dao.IEmaildtlDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class EmaildtlHibernateDAOImpl extends HibernateDaoSupport implements IEmaildtlDAO
{
	protected static final Log log = LogFactory.getLog(EmaildtlHibernateDAOImpl.class);
	public EmaildtlDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		EmaildtlDTO emaildtlDTO = null ;
		try
		{	
			emaildtlDTO = (EmaildtlDTO) getHibernateTemplate().get(EmaildtlDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return emaildtlDTO;
	}
	
	
	public EmaildtlDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		EmaildtlDTO emaildtlDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from EmaildtlDTO emaildtlDTO where emaildtlDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				emaildtlDTO = (EmaildtlDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return emaildtlDTO ;
	}
	
	
	public ArrayList<EmaildtlDTO> getList() throws SystemException 
	{	
		return getList(null,EmaildtlSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<EmaildtlDTO> getList(Long groupId,EmaildtlSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<EmaildtlDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT emaildtlDTO FROM EmaildtlDTO emaildtlDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE emaildtlDTO.groupId="+groupId);	
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
	
	
	public EmaildtlSuccessResult add(EmaildtlDTO emaildtlDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		EmaildtlSuccessResult successResult = new EmaildtlSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(emaildtlDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(emaildtlDTO);
		}
		catch(Exception e)
		{
			log.debug("add(EmaildtlDTO emaildtlDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public EmaildtlSuccessResult modify(EmaildtlDTO emaildtlDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		EmaildtlSuccessResult successResult = new EmaildtlSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(emaildtlDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(emaildtlDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(EmaildtlDTO emaildtlDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public EmaildtlSuccessResult remove(EmaildtlDTO emaildtlDTO) throws SystemException
	{
		log.info("remove(): Enter");
		EmaildtlSuccessResult successResult = new EmaildtlSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(emaildtlDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(emaildtlDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(EmaildtlDTO emaildtlDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(EmaildtlSortBy sortBy)
	{
		// default value
		String resultStr = "emaildtlDTO.uniqueCode" ;
		if(sortBy == EmaildtlSortBy.uniqueCode)
		{
			resultStr = "emaildtlDTO.uniqueCode" ;
		}
		else if(sortBy == EmaildtlSortBy.firstName)
		{
			resultStr = "emaildtlDTO.firstName" ;
		}
		else if(sortBy == EmaildtlSortBy.lastname)
		{
			resultStr = "emaildtlDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
