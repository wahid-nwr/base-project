package com.swiftcorp.portal.samplecom.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.samplecom.SamplecomSuccessResult;
import com.swiftcorp.portal.samplecom.dto.SamplecomDTO;
import com.swiftcorp.portal.samplecom.exception.SamplecomAlreadyExistsException;
import com.swiftcorp.portal.samplecom.exception.SamplecomCreationException;
import com.swiftcorp.portal.samplecom.exception.SamplecomNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.samplecom.dao.ISamplecomDAO.SamplecomSortBy;
import com.swiftcorp.portal.samplecom.dao.ISamplecomDAO;

/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class SamplecomHibernateDAOImpl extends HibernateDaoSupport implements ISamplecomDAO
{
	protected static final Log log = LogFactory.getLog(SamplecomHibernateDAOImpl.class);
	public SamplecomDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		SamplecomDTO samplecomDTO = null ;
		try
		{	
			samplecomDTO = (SamplecomDTO) getHibernateTemplate().get(SamplecomDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return samplecomDTO;
	}
	
	
	public SamplecomDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		SamplecomDTO samplecomDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from SamplecomDTO samplecomDTO where samplecomDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				samplecomDTO = (SamplecomDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return samplecomDTO ;
	}
	
	
	public ArrayList<SamplecomDTO> getList() throws SystemException 
	{	
		return getList(null,SamplecomSortBy.uniqueCode);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SamplecomDTO> getList(Long groupId,SamplecomSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<SamplecomDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT samplecomDTO FROM SamplecomDTO samplecomDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE samplecomDTO.groupId="+groupId);	
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
	
	

	public SamplecomSuccessResult add(SamplecomDTO samplecomDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		SamplecomSuccessResult successResult = new SamplecomSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(samplecomDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(samplecomDTO);
		}
		catch(Exception e)
		{
			log.debug("add(SamplecomDTO samplecomDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}

	
	
	public SamplecomSuccessResult modify(SamplecomDTO samplecomDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		SamplecomSuccessResult successResult = new SamplecomSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(samplecomDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(samplecomDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(SamplecomDTO samplecomDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	

	public SamplecomSuccessResult remove(SamplecomDTO samplecomDTO) throws SystemException
	{
		log.info("remove(): Enter");
		SamplecomSuccessResult successResult = new SamplecomSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(samplecomDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(samplecomDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(SamplecomDTO samplecomDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}

	
	private String getSortByStr(SamplecomSortBy sortBy)
	{
		// default value
		String resultStr = "samplecomDTO.uniqueCode" ;
		if(sortBy == SamplecomSortBy.uniqueCode)
		{
			resultStr = "samplecomDTO.uniqueCode" ;
		}
		else if(sortBy == SamplecomSortBy.firstName)
		{
			resultStr = "samplecomDTO.firstName" ;
		}
		else if(sortBy == SamplecomSortBy.lastname)
		{
			resultStr = "samplecomDTO.lastName" ;
		}
		return resultStr ;
		
	}



}
