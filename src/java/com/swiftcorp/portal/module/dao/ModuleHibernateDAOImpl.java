package com.swiftcorp.portal.module.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.module.ModuleSuccessResult;
import com.swiftcorp.portal.module.dto.ModuleDTO;
import com.swiftcorp.portal.module.exception.ModuleAlreadyExistsException;
import com.swiftcorp.portal.module.exception.ModuleCreationException;
import com.swiftcorp.portal.module.exception.ModuleNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.module.dao.IModuleDAO.ModuleSortBy;
import com.swiftcorp.portal.module.dao.IModuleDAO;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class ModuleHibernateDAOImpl extends HibernateDaoSupport implements IModuleDAO
{
	protected static final Log log = LogFactory.getLog(ModuleHibernateDAOImpl.class);
	public ModuleDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		ModuleDTO moduleDTO = null ;
		try
		{	
			moduleDTO = (ModuleDTO) getHibernateTemplate().get(ModuleDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return moduleDTO;
	}
	
	
	public ModuleDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		ModuleDTO moduleDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from ModuleDTO moduleDTO where moduleDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				moduleDTO = (ModuleDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return moduleDTO ;
	}
	
	
	public ArrayList<ModuleDTO> getList() throws SystemException 
	{	
		return getList(null,ModuleSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<ModuleDTO> getList(Long groupId,ModuleSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<ModuleDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT moduleDTO FROM ModuleDTO moduleDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE moduleDTO.groupId="+groupId);	
		}
		/*
		queryStr.append(" ORDER BY ");
		queryStr.append(getSortByStr(sortby));
		*/
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
	
	
	public ModuleSuccessResult add(ModuleDTO moduleDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		ModuleSuccessResult successResult = new ModuleSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(moduleDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(moduleDTO);
		}
		catch(Exception e)
		{
			log.debug("add(ModuleDTO moduleDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public ModuleSuccessResult modify(ModuleDTO moduleDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		ModuleSuccessResult successResult = new ModuleSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(moduleDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(moduleDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(ModuleDTO moduleDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public ModuleSuccessResult remove(ModuleDTO moduleDTO) throws SystemException
	{
		log.info("remove(): Enter");
		ModuleSuccessResult successResult = new ModuleSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(moduleDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(moduleDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(ModuleDTO moduleDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(ModuleSortBy sortBy)
	{
		// default value
		String resultStr = "moduleDTO.uniqueCode" ;
		if(sortBy == ModuleSortBy.uniqueCode)
		{
			resultStr = "moduleDTO.uniqueCode" ;
		}
		else if(sortBy == ModuleSortBy.firstName)
		{
			resultStr = "moduleDTO.firstName" ;
		}
		else if(sortBy == ModuleSortBy.lastname)
		{
			resultStr = "moduleDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
