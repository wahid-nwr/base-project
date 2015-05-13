package com.swiftcorp.portal.home.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.home.HomeSuccessResult;
import com.swiftcorp.portal.home.dto.HomeDTO;
import com.swiftcorp.portal.home.exception.HomeAlreadyExistsException;
import com.swiftcorp.portal.home.exception.HomeCreationException;
import com.swiftcorp.portal.home.exception.HomeNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.home.dao.IHomeDAO.HomeSortBy;
import com.swiftcorp.portal.home.dao.IHomeDAO;
public class HomeHibernateDAOImpl extends HibernateDaoSupport implements IHomeDAO
{
	protected static final Log log = LogFactory.getLog(HomeHibernateDAOImpl.class);
	public HomeDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		HomeDTO homeDTO = null ;
		try
		{	
			homeDTO = (HomeDTO) getHibernateTemplate().get(HomeDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return homeDTO;
	}
	
	
	public HomeDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		HomeDTO homeDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from HomeDTO homeDTO where homeDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				homeDTO = (HomeDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return homeDTO ;
	}
	
	
	public ArrayList<HomeDTO> getList() throws SystemException 
	{	
		return getList(null,HomeSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<HomeDTO> getList(Long groupId,HomeSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<HomeDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT homeDTO FROM HomeDTO homeDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE homeDTO.groupId="+groupId);	
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
	
	
	public HomeSuccessResult add(HomeDTO homeDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		HomeSuccessResult successResult = new HomeSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(homeDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(homeDTO);
		}
		catch(Exception e)
		{
			log.debug("add(HomeDTO homeDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public HomeSuccessResult modify(HomeDTO homeDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		HomeSuccessResult successResult = new HomeSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(homeDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(homeDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(HomeDTO homeDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public HomeSuccessResult remove(HomeDTO homeDTO) throws SystemException
	{
		log.info("remove(): Enter");
		HomeSuccessResult successResult = new HomeSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(homeDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(homeDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(HomeDTO homeDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(HomeSortBy sortBy)
	{
		// default value
		String resultStr = "homeDTO.uniqueCode" ;
		if(sortBy == HomeSortBy.uniqueCode)
		{
			resultStr = "homeDTO.uniqueCode" ;
		}
		else if(sortBy == HomeSortBy.firstName)
		{
			resultStr = "homeDTO.firstName" ;
		}
		else if(sortBy == HomeSortBy.lastname)
		{
			resultStr = "homeDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
