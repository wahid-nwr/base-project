package com.swiftcorp.portal.ajan.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.ajan.AjanSuccessResult;
import com.swiftcorp.portal.ajan.dto.AjanDTO;
import com.swiftcorp.portal.ajan.exception.AjanAlreadyExistsException;
import com.swiftcorp.portal.ajan.exception.AjanCreationException;
import com.swiftcorp.portal.ajan.exception.AjanNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.ajan.dao.IAjanDAO.AjanSortBy;
import com.swiftcorp.portal.ajan.dao.IAjanDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class AjanHibernateDAOImpl extends HibernateDaoSupport implements IAjanDAO
{
	protected static final Log log = LogFactory.getLog(AjanHibernateDAOImpl.class);
	public AjanDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		AjanDTO ajanDTO = null ;
		try
		{	
			ajanDTO = (AjanDTO) getHibernateTemplate().get(AjanDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return ajanDTO;
	}
	
	
	public AjanDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		AjanDTO ajanDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from AjanDTO ajanDTO where ajanDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				ajanDTO = (AjanDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return ajanDTO ;
	}
	
	
	public ArrayList<AjanDTO> getList() throws SystemException 
	{	
		return getList(null,AjanSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<AjanDTO> getList(Long groupId,AjanSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<AjanDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT ajanDTO FROM AjanDTO ajanDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE ajanDTO.groupId="+groupId);	
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
	
	
	public AjanSuccessResult add(AjanDTO ajanDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		AjanSuccessResult successResult = new AjanSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(ajanDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(ajanDTO);
		}
		catch(Exception e)
		{
			log.debug("add(AjanDTO ajanDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public AjanSuccessResult modify(AjanDTO ajanDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		AjanSuccessResult successResult = new AjanSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(ajanDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(ajanDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(AjanDTO ajanDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public AjanSuccessResult remove(AjanDTO ajanDTO) throws SystemException
	{
		log.info("remove(): Enter");
		AjanSuccessResult successResult = new AjanSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(ajanDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(ajanDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(AjanDTO ajanDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(AjanSortBy sortBy)
	{
		// default value
		String resultStr = "ajanDTO.uniqueCode" ;
		if(sortBy == AjanSortBy.uniqueCode)
		{
			resultStr = "ajanDTO.uniqueCode" ;
		}
		else if(sortBy == AjanSortBy.firstName)
		{
			resultStr = "ajanDTO.firstName" ;
		}
		else if(sortBy == AjanSortBy.lastname)
		{
			resultStr = "ajanDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
