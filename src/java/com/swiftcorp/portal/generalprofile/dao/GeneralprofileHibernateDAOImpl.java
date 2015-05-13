package com.swiftcorp.portal.generalprofile.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.generalprofile.GeneralprofileSuccessResult;
import com.swiftcorp.portal.generalprofile.dto.GeneralprofileDTO;
import com.swiftcorp.portal.generalprofile.exception.GeneralprofileAlreadyExistsException;
import com.swiftcorp.portal.generalprofile.exception.GeneralprofileCreationException;
import com.swiftcorp.portal.generalprofile.exception.GeneralprofileNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.generalprofile.dao.IGeneralprofileDAO.GeneralprofileSortBy;
import com.swiftcorp.portal.generalprofile.dao.IGeneralprofileDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class GeneralprofileHibernateDAOImpl extends HibernateDaoSupport implements IGeneralprofileDAO
{
	protected static final Log log = LogFactory.getLog(GeneralprofileHibernateDAOImpl.class);
	public GeneralprofileDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		GeneralprofileDTO generalprofileDTO = null ;
		try
		{	
			generalprofileDTO = (GeneralprofileDTO) getHibernateTemplate().get(GeneralprofileDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return generalprofileDTO;
	}
	
	
	public GeneralprofileDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		GeneralprofileDTO generalprofileDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from GeneralprofileDTO generalprofileDTO where generalprofileDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				generalprofileDTO = (GeneralprofileDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return generalprofileDTO ;
	}
	
	
	public ArrayList<GeneralprofileDTO> getList() throws SystemException 
	{	
		return getList(null,GeneralprofileSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<GeneralprofileDTO> getList(Long groupId,GeneralprofileSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<GeneralprofileDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT generalprofileDTO FROM GeneralprofileDTO generalprofileDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE generalprofileDTO.groupId="+groupId);	
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
	
	
	public GeneralprofileSuccessResult add(GeneralprofileDTO generalprofileDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		GeneralprofileSuccessResult successResult = new GeneralprofileSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(generalprofileDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(generalprofileDTO);
		}
		catch(Exception e)
		{
			log.debug("add(GeneralprofileDTO generalprofileDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public GeneralprofileSuccessResult modify(GeneralprofileDTO generalprofileDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		GeneralprofileSuccessResult successResult = new GeneralprofileSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(generalprofileDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(generalprofileDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(GeneralprofileDTO generalprofileDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public GeneralprofileSuccessResult remove(GeneralprofileDTO generalprofileDTO) throws SystemException
	{
		log.info("remove(): Enter");
		GeneralprofileSuccessResult successResult = new GeneralprofileSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(generalprofileDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(generalprofileDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(GeneralprofileDTO generalprofileDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(GeneralprofileSortBy sortBy)
	{
		// default value
		String resultStr = "generalprofileDTO.uniqueCode" ;
		if(sortBy == GeneralprofileSortBy.uniqueCode)
		{
			resultStr = "generalprofileDTO.uniqueCode" ;
		}
		else if(sortBy == GeneralprofileSortBy.firstName)
		{
			resultStr = "generalprofileDTO.firstName" ;
		}
		else if(sortBy == GeneralprofileSortBy.lastname)
		{
			resultStr = "generalprofileDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
