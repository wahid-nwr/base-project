package com.swiftcorp.portal.patient.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.patient.PatientSuccessResult;
import com.swiftcorp.portal.patient.dao.IPatientDAO;
import com.swiftcorp.portal.patient.dao.IPatientDAO.PatientSortBy;
import com.swiftcorp.portal.patient.dto.PatientDTO;
import com.swiftcorp.portal.patient.exception.PatientAlreadyExistsException;
import com.swiftcorp.portal.patient.exception.PatientCreationException;
import com.swiftcorp.portal.patient.exception.PatientNotFoundException;
public class PatientHibernateDAOImpl extends HibernateDaoSupport implements IPatientDAO
{
	protected static final Log log = LogFactory.getLog(PatientHibernateDAOImpl.class);
	public PatientDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		PatientDTO patientDTO = null ;
		try
		{	
			patientDTO = (PatientDTO) getHibernateTemplate().get(PatientDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return patientDTO;
	}
	
	
	public PatientDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		PatientDTO patientDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from PatientDTO patientDTO where patientDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				patientDTO = (PatientDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return patientDTO ;
	}
	
	
	public ArrayList<PatientDTO> getList() throws SystemException 
	{	
		return getList(null,PatientSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<PatientDTO> getList(Long groupId,PatientSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<PatientDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT patientDTO FROM PatientDTO patientDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE patientDTO.groupId="+groupId);	
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
	
	
	public PatientSuccessResult add(PatientDTO patientDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		PatientSuccessResult successResult = new PatientSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(patientDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(patientDTO);
		}
		catch(Exception e)
		{
			log.debug("add(PatientDTO patientDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public PatientSuccessResult modify(PatientDTO patientDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		PatientSuccessResult successResult = new PatientSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(patientDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(patientDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(PatientDTO patientDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public PatientSuccessResult remove(PatientDTO patientDTO) throws SystemException
	{
		log.info("remove(): Enter");
		PatientSuccessResult successResult = new PatientSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(patientDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(patientDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(PatientDTO patientDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(PatientSortBy sortBy)
	{
		// default value
		String resultStr = "patientDTO.uniqueCode" ;
		if(sortBy == PatientSortBy.uniqueCode)
		{
			resultStr = "patientDTO.uniqueCode" ;
		}
		else if(sortBy == PatientSortBy.firstName)
		{
			resultStr = "patientDTO.firstName" ;
		}
		else if(sortBy == PatientSortBy.lastname)
		{
			resultStr = "patientDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
