package com.swiftcorp.portal.item.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.item.ItemSuccessResult;
import com.swiftcorp.portal.item.dto.ItemDTO;
import com.swiftcorp.portal.item.exception.ItemNotFoundException;
import com.swiftcorp.portal.item.dao.IItemDAO.ItemSortBy;
public interface IItemDAO 
{
  public enum ItemSortBy {uniqueCode, adminType, firstName, lastname};
  public enum ItemWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public ItemDTO get(Long componentId)throws SystemException; 
  public ItemDTO get(String unicodeCode)throws SystemException; 
  public ItemSuccessResult add(ItemDTO itemDTO)throws SystemException;
  public ItemSuccessResult modify(ItemDTO itemDTO)throws SystemException;
  public ItemSuccessResult remove(ItemDTO itemDTO)throws SystemException;
  
  public ArrayList<ItemDTO> getList() throws SystemException;
  public ArrayList<ItemDTO> getList(Long groupId,ItemSortBy sortby) throws SystemException;
	
}
