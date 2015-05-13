package com.swiftcorp.portal.info.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.info.InfoSuccessResult;
import com.swiftcorp.portal.info.dto.InfoDTO;
import com.swiftcorp.portal.info.exception.InfoAlreadyExistsException;
import com.swiftcorp.portal.info.exception.InfoCreationException;
import com.swiftcorp.portal.info.exception.InfoNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.info.dao.IInfoDAO.InfoSortBy;
import com.swiftcorp.portal.info.dao.IInfoDAO;
public class InfoHibernateDAOImpl extends HibernateDaoSupport implements IInfoDAO
{
	protected static final Log log = LogFactory.getLog(InfoHibernateDAOImpl.class);
	public InfoDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		InfoDTO infoDTO = null ;
		try
		{	
			infoDTO = (InfoDTO) getHibernateTemplate().get(InfoDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return infoDTO;
	}
	
	
	public InfoDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		InfoDTO infoDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from InfoDTO infoDTO where infoDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				infoDTO = (InfoDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return infoDTO ;
	}
	
	
	public ArrayList<InfoDTO> getList() throws SystemException 
	{	
		return getList(null,InfoSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<InfoDTO> getList(Long groupId,InfoSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<InfoDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT infoDTO FROM InfoDTO infoDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE infoDTO.groupId="+groupId);	
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
	
	
	public InfoSuccessResult add(InfoDTO infoDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		InfoSuccessResult successResult = new InfoSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(infoDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(infoDTO);
		}
		catch(Exception e)
		{
			log.debug("add(InfoDTO infoDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public InfoSuccessResult modify(InfoDTO infoDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		InfoSuccessResult successResult = new InfoSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(infoDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(infoDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(InfoDTO infoDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public InfoSuccessResult remove(InfoDTO infoDTO) throws SystemException
	{
		log.info("remove(): Enter");
		InfoSuccessResult successResult = new InfoSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(infoDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(infoDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(InfoDTO infoDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(InfoSortBy sortBy)
	{
		// default value
		String resultStr = "infoDTO.uniqueCode" ;
		if(sortBy == InfoSortBy.uniqueCode)
		{
			resultStr = "infoDTO.uniqueCode" ;
		}
		else if(sortBy == InfoSortBy.firstName)
		{
			resultStr = "infoDTO.firstName" ;
		}
		else if(sortBy == InfoSortBy.lastname)
		{
			resultStr = "infoDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
