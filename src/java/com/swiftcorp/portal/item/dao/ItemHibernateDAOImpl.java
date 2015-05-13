package com.swiftcorp.portal.item.dao;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.swiftcorp.portal.item.ItemSuccessResult;
import com.swiftcorp.portal.item.dto.ItemDTO;
import com.swiftcorp.portal.item.exception.ItemAlreadyExistsException;
import com.swiftcorp.portal.item.exception.ItemCreationException;
import com.swiftcorp.portal.item.exception.ItemNotFoundException;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.InvalidDateException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.item.dao.IItemDAO.ItemSortBy;
import com.swiftcorp.portal.item.dao.IItemDAO;
public class ItemHibernateDAOImpl extends HibernateDaoSupport implements IItemDAO
{
	protected static final Log log = LogFactory.getLog(ItemHibernateDAOImpl.class);
	public ItemDTO get(Long componentId) throws SystemException 
	{
		log.info("get(id): Enter");
		log.info("get(id): componentId = "+ componentId);
		ItemDTO itemDTO = null ;
		try
		{	
			itemDTO = (ItemDTO) getHibernateTemplate().get(ItemDTO.class,componentId);
		}
		catch (Exception e) 
		{
			log.info("get(id): ",e);
			throw new SystemException(e);
		}
		log.info("get(id): Exit");
		return itemDTO;
	}
	
	
	public ItemDTO get(String unicodeCode) throws SystemException 
	{
		log.info("get(code): Enter");
		log.info("get(code): code = "+ unicodeCode);
		ItemDTO itemDTO =  null ;
		try
		{	
			ArrayList list = (ArrayList) getHibernateTemplate().find("from ItemDTO itemDTO where itemDTO.uniqueCode=?", unicodeCode);				
			if (list.size() > 0)
			{
				itemDTO = (ItemDTO) list.get(0);
			}
		}
		catch (Exception e) 
		{
			log.error("get(String uniqueCode): ", e);
			throw new SystemException(e);
		}
		
		log.info("get(code): Exit");
		return itemDTO ;
	}
	
	
	public ArrayList<ItemDTO> getList() throws SystemException 
	{	
		return getList(null,ItemSortBy.uniqueCode);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<ItemDTO> getList(Long groupId,ItemSortBy sortby) throws SystemException 
	{
		log.info("getList: Enter");
		log.info("getList: sortby = "+ sortby);
		
		ArrayList<ItemDTO> result = null ;
		StringBuffer queryStr = new StringBuffer();
		queryStr.append(" SELECT itemDTO FROM ItemDTO itemDTO");
		if(groupId != null)
		{
			queryStr.append(" WHERE itemDTO.groupId="+groupId);	
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
	
	
	public ItemSuccessResult add(ItemDTO itemDTO) throws  SystemException 
	{
		log.info("add(): Enter");
				
		ItemSuccessResult successResult = new ItemSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.save(itemDTO);			
			successResult.setMessage("Added Successfully.");
			successResult.setOperationResult(itemDTO);
		}
		catch(Exception e)
		{
			log.debug("add(ItemDTO itemDTO): Failed to add." + e);
			throw new SystemException(e);
		}
		log.info("add(): Exit");
		return successResult ;
	}
	
	
	public ItemSuccessResult modify(ItemDTO itemDTO) throws SystemException 
	{
		log.info("modify(): Enter");
		ItemSuccessResult successResult = new ItemSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.update(itemDTO);			
			successResult.setMessage("Modified Successfully.");
			successResult.setOperationResult(itemDTO);
		}
		catch(Exception e)
		{
			log.debug("modify(ItemDTO itemDTO): Failed to modify.",e);
			throw new SystemException(e);
		}
		log.info("modify(): Exit");
		return successResult ;
	}
	
	
	public ItemSuccessResult remove(ItemDTO itemDTO) throws SystemException
	{
		log.info("remove(): Enter");
		ItemSuccessResult successResult = new ItemSuccessResult();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.delete(itemDTO);			
			successResult.setMessage("removed Successfully.");
			successResult.setOperationResult(itemDTO);
		}
		catch(Exception e)
		{
			log.debug("remove(ItemDTO itemDTO): Failed to remove." + e);
			throw new SystemException(e);
		}
		log.info("remove(): Exit");
		return successResult ;
	}
	
	private String getSortByStr(ItemSortBy sortBy)
	{
		// default value
		String resultStr = "itemDTO.uniqueCode" ;
		if(sortBy == ItemSortBy.uniqueCode)
		{
			resultStr = "itemDTO.uniqueCode" ;
		}
		else if(sortBy == ItemSortBy.firstName)
		{
			resultStr = "itemDTO.firstName" ;
		}
		else if(sortBy == ItemSortBy.lastname)
		{
			resultStr = "itemDTO.lastName" ;
		}
		return resultStr ;
		
	}
}
