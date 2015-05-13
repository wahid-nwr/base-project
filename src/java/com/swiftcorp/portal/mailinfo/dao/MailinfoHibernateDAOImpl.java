package com.swiftcorp.portal.mailinfo.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.mailinfo.MailinfoSuccessResult;
import com.swiftcorp.portal.mailinfo.dto.MailinfoDTO;
import com.swiftcorp.portal.mailinfo.exception.MailinfoAlreadyExistsException;
import com.swiftcorp.portal.mailinfo.exception.MailinfoCreationException;
import com.swiftcorp.portal.mailinfo.exception.MailinfoNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.mailinfo.dao.IMailinfoDAO.MailinfoSortBy;
import com.swiftcorp.portal.mailinfo.dao.IMailinfoDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class MailinfoHibernateDAOImpl extends HibernateDaoSupport implements IMailinfoDAO
{
	protected static final Log log = LogFactory.getLog(MailinfoHibernateDAOImpl.class);
	public MailinfoDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		MailinfoDTO mailinfoDTO = null ;
		try
		{	
			mailinfoDTO = (MailinfoDTO) getHibernateTemplate().get(MailinfoDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return mailinfoDTO;
	}
	
	
	public MailinfoDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		MailinfoDTO mailinfoDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from MailinfoDTO mailinfoDTO where mailinfoDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				mailinfoDTO = (MailinfoDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return mailinfoDTO ;
	}
	
	
	public ArrayList<MailinfoDTO> getList() throws SystemException 
	{	
		return getList(null,MailinfoSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<MailinfoDTO> getList(Long groupId,MailinfoSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<MailinfoDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT mailinfoDTO FROM MailinfoDTO mailinfoDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE mailinfoDTO.groupId="+groupId);	
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
	
	
	public MailinfoSuccessResult add(MailinfoDTO mailinfoDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		MailinfoSuccessResult successResult = new MailinfoSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(mailinfoDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(mailinfoDTO);
		}
		catch(Exception e)
		{
			log.debug("add(MailinfoDTO mailinfoDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public MailinfoSuccessResult modify(MailinfoDTO mailinfoDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		MailinfoSuccessResult successResult = new MailinfoSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(mailinfoDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(mailinfoDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(MailinfoDTO mailinfoDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public MailinfoSuccessResult remove(MailinfoDTO mailinfoDTO) throws SystemException
	{
		log.info("remove(): Enter");
		MailinfoSuccessResult successResult = new MailinfoSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(mailinfoDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(mailinfoDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(MailinfoDTO mailinfoDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(MailinfoSortBy sortBy)
	{
		// default value
		String resultStr = "mailinfoDTO.uniqueCode" ;
		if(sortBy == MailinfoSortBy.uniqueCode)
		{
			resultStr = "mailinfoDTO.uniqueCode" ;
		}
		else if(sortBy == MailinfoSortBy.firstName)
		{
			resultStr = "mailinfoDTO.firstName" ;
		}
		else if(sortBy == MailinfoSortBy.lastname)
		{
			resultStr = "mailinfoDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
