package com.swiftcorp.portal.module.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.module.ModuleSuccessResult;
import com.swiftcorp.portal.module.dto.ModuleDTO;
import com.swiftcorp.portal.module.exception.ModuleNotFoundException;
import com.swiftcorp.portal.module.dao.IModuleDAO.ModuleSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IModuleDAO 
{
  public enum ModuleSortBy {uniqueCode, adminType, firstName, lastname};
  public enum ModuleWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public ModuleDTO get(Long componentId)throws SystemException; 
  public ModuleDTO get(String unicodeCode)throws SystemException; 
  public ModuleSuccessResult add(ModuleDTO moduleDTO)throws SystemException;
  public ModuleSuccessResult modify(ModuleDTO moduleDTO)throws SystemException;
  public ModuleSuccessResult remove(ModuleDTO moduleDTO)throws SystemException;
  
  public ArrayList<ModuleDTO> getList() throws SystemException;
  public ArrayList<ModuleDTO> getList(Long groupId,ModuleSortBy sortby) throws SystemException;
	
}
