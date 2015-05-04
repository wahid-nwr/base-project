package com.swiftcorp.portal.dcrinfo.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.dcrinfo.DcrinfoSuccessResult;
import com.swiftcorp.portal.dcrinfo.dao.IDcrinfoDAO;
import com.swiftcorp.portal.dcrinfo.dao.IDcrinfoDAO.DcrinfoSortBy;
import com.swiftcorp.portal.dcrinfo.dto.DcrinfoDTO;
import com.swiftcorp.portal.dcrinfo.exception.DcrinfoAlreadyExistsException;
import com.swiftcorp.portal.dcrinfo.exception.DcrinfoCreationException;
import com.swiftcorp.portal.dcrinfo.exception.DcrinfoNotFoundException;
public class DcrinfoHibernateDAOImpl extends HibernateDaoSupport implements IDcrinfoDAO
{
	protected static final Log log = LogFactory.getLog(DcrinfoHibernateDAOImpl.class);
	public DcrinfoDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		DcrinfoDTO dcrinfoDTO = null ;
		try
		{	
			dcrinfoDTO = (DcrinfoDTO) getHibernateTemplate().get(DcrinfoDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return dcrinfoDTO;
	}
	
	
	public DcrinfoDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		DcrinfoDTO dcrinfoDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from DcrinfoDTO dcrinfoDTO where dcrinfoDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				dcrinfoDTO = (DcrinfoDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return dcrinfoDTO ;
	}
	
	
	public ArrayList<DcrinfoDTO> getList() throws SystemException 
	{	
		return getList(null,DcrinfoSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<DcrinfoDTO> getList(Long groupId,DcrinfoSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<DcrinfoDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT dcrinfoDTO FROM DcrinfoDTO dcrinfoDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE dcrinfoDTO.groupId="+groupId);	
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
	
	
	public DcrinfoSuccessResult add(DcrinfoDTO dcrinfoDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		DcrinfoSuccessResult successResult = new DcrinfoSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(dcrinfoDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(dcrinfoDTO);
		}
		catch(Exception e)
		{
			log.debug("add(DcrinfoDTO dcrinfoDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public DcrinfoSuccessResult modify(DcrinfoDTO dcrinfoDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		DcrinfoSuccessResult successResult = new DcrinfoSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(dcrinfoDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(dcrinfoDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(DcrinfoDTO dcrinfoDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public DcrinfoSuccessResult remove(DcrinfoDTO dcrinfoDTO) throws SystemException
	{
		log.info("remove(): Enter");
		DcrinfoSuccessResult successResult = new DcrinfoSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(dcrinfoDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(dcrinfoDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(DcrinfoDTO dcrinfoDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(DcrinfoSortBy sortBy)
	{
		// default value
		String resultStr = "dcrinfoDTO.uniqueCode" ;
		if(sortBy == DcrinfoSortBy.uniqueCode)
		{
			resultStr = "dcrinfoDTO.uniqueCode" ;
		}
		else if(sortBy == DcrinfoSortBy.firstName)
		{
			resultStr = "dcrinfoDTO.firstName" ;
		}
		else if(sortBy == DcrinfoSortBy.lastname)
		{
			resultStr = "dcrinfoDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
