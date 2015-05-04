package com.swiftcorp.portal.dcrreport.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.dcrreport.DcrReportSuccessResult;
import com.swiftcorp.portal.dcrreport.dao.IDcrReportDAO;
import com.swiftcorp.portal.dcrreport.dao.IDcrReportDAO.DcrReportSortBy;
import com.swiftcorp.portal.dcrreport.dto.DcrReportDTO;
import com.swiftcorp.portal.dcrreport.exception.DcrReportAlreadyExistsException;
import com.swiftcorp.portal.dcrreport.exception.DcrReportCreationException;
import com.swiftcorp.portal.dcrreport.exception.DcrReportNotFoundException;
public class DcrReportHibernateDAOImpl extends HibernateDaoSupport implements IDcrReportDAO
{
	protected static final Log log = LogFactory.getLog(DcrReportHibernateDAOImpl.class);
	public DcrReportDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		DcrReportDTO dcrreportDTO = null ;
		try
		{	
			dcrreportDTO = (DcrReportDTO) getHibernateTemplate().get(DcrReportDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return dcrreportDTO;
	}
	
	
	public DcrReportDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		DcrReportDTO dcrreportDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from DcrReportDTO dcrreportDTO where dcrreportDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				dcrreportDTO = (DcrReportDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return dcrreportDTO ;
	}
	
	public DcrReportDTO get() throws SystemException 
	{
		log.info("get(code): Enter");
		DcrReportDTO dcrreportDTO =  new DcrReportDTO() ;
		ArrayList<DcrReportDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT dcrreportDTO FROM DcrReportDTO dcrreportDTO");
		queryStr.append(" ORDER BY ");
		queryStr.append(getSortByStr(DcrReportSortBy.componentId));
		try
		{	
			result = (ArrayList) getHibernateTemplate().find(queryStr.toString());
			if(result!=null && result.size()>0)
			{
				dcrreportDTO = result.get(result.size()-1);
			}
			log.info("getList(): size = "+ result.size());
		}
		catch (Exception e) 
		{
			throw new SystemException(e);
		}
		log.info("getList: Exit");
		
		
		log.info("get(code): Exit");
		return dcrreportDTO ;
	}
	
	
	public ArrayList<DcrReportDTO> getList() throws SystemException 
	{	
		return getList(null,DcrReportSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<DcrReportDTO> getList(Long groupId,DcrReportSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<DcrReportDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT dcrreportDTO FROM DcrReportDTO dcrreportDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE dcrreportDTO.groupId="+groupId);	
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
	
	
	public DcrReportSuccessResult add(DcrReportDTO dcrreportDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		DcrReportSuccessResult successResult = new DcrReportSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(dcrreportDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(dcrreportDTO);
		}
		catch(Exception e)
		{
			log.debug("add(DcrReportDTO dcrreportDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public DcrReportSuccessResult modify(DcrReportDTO dcrreportDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		DcrReportSuccessResult successResult = new DcrReportSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(dcrreportDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(dcrreportDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(DcrReportDTO dcrreportDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public DcrReportSuccessResult remove(DcrReportDTO dcrreportDTO) throws SystemException
	{
		log.info("remove(): Enter");
		DcrReportSuccessResult successResult = new DcrReportSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(dcrreportDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(dcrreportDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(DcrReportDTO dcrreportDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(DcrReportSortBy sortBy)
	{
		// default value
		String resultStr = "dcrreportDTO.uniqueCode" ;
		if(sortBy == DcrReportSortBy.uniqueCode)
		{
			resultStr = "dcrreportDTO.uniqueCode" ;
		}
		else if(sortBy == DcrReportSortBy.firstName)
		{
			resultStr = "dcrreportDTO.firstName" ;
		}
		else if(sortBy == DcrReportSortBy.lastname)
		{
			resultStr = "dcrreportDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
