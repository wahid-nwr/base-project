package com.swiftcorp.portal.covering.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.covering.CoveringSuccessResult;
import com.swiftcorp.portal.covering.dto.CoveringDTO;
import com.swiftcorp.portal.covering.exception.CoveringAlreadyExistsException;
import com.swiftcorp.portal.covering.exception.CoveringCreationException;
import com.swiftcorp.portal.covering.exception.CoveringNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.covering.dao.ICoveringDAO.CoveringSortBy;
import com.swiftcorp.portal.covering.dao.ICoveringDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class CoveringHibernateDAOImpl extends HibernateDaoSupport implements ICoveringDAO
{
	protected static final Log log = LogFactory.getLog(CoveringHibernateDAOImpl.class);
	public CoveringDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		CoveringDTO coveringDTO = null ;
		try
		{	
			coveringDTO = (CoveringDTO) getHibernateTemplate().get(CoveringDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return coveringDTO;
	}
	
	
	public CoveringDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		CoveringDTO coveringDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from CoveringDTO coveringDTO where coveringDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				coveringDTO = (CoveringDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return coveringDTO ;
	}
	
	
	public ArrayList<CoveringDTO> getList() throws SystemException 
	{	
		return getList(null,CoveringSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<CoveringDTO> getList(Long groupId,CoveringSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<CoveringDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT coveringDTO FROM CoveringDTO coveringDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE coveringDTO.groupId="+groupId);	
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
	
	
	public CoveringSuccessResult add(CoveringDTO coveringDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		CoveringSuccessResult successResult = new CoveringSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(coveringDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(coveringDTO);
		}
		catch(Exception e)
		{
			log.debug("add(CoveringDTO coveringDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public CoveringSuccessResult modify(CoveringDTO coveringDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		CoveringSuccessResult successResult = new CoveringSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(coveringDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(coveringDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(CoveringDTO coveringDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public CoveringSuccessResult remove(CoveringDTO coveringDTO) throws SystemException
	{
		log.info("remove(): Enter");
		CoveringSuccessResult successResult = new CoveringSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(coveringDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(coveringDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(CoveringDTO coveringDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(CoveringSortBy sortBy)
	{
		// default value
		String resultStr = "coveringDTO.uniqueCode" ;
		if(sortBy == CoveringSortBy.uniqueCode)
		{
			resultStr = "coveringDTO.uniqueCode" ;
		}
		else if(sortBy == CoveringSortBy.firstName)
		{
			resultStr = "coveringDTO.firstName" ;
		}
		else if(sortBy == CoveringSortBy.lastname)
		{
			resultStr = "coveringDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
