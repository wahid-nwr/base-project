package com.swiftcorp.portal.generalprofile.dao;
import java.util.ArrayList;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.generalprofile.GeneralprofileSuccessResult;
import com.swiftcorp.portal.generalprofile.dto.GeneralprofileDTO;
import com.swiftcorp.portal.generalprofile.exception.GeneralprofileNotFoundException;
import com.swiftcorp.portal.generalprofile.dao.IGeneralprofileDAO.GeneralprofileSortBy;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public interface IGeneralprofileDAO 
{
  public enum GeneralprofileSortBy {uniqueCode, adminType, firstName, lastname};
  public enum GeneralprofileWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public GeneralprofileDTO get(Long componentId)throws SystemException; 
  public GeneralprofileDTO get(String unicodeCode)throws SystemException; 
  public GeneralprofileSuccessResult add(GeneralprofileDTO generalprofileDTO)throws SystemException;
  public GeneralprofileSuccessResult modify(GeneralprofileDTO generalprofileDTO)throws SystemException;
  public GeneralprofileSuccessResult remove(GeneralprofileDTO generalprofileDTO)throws SystemException;
  
  public ArrayList<GeneralprofileDTO> getList() throws SystemException;
  public ArrayList<GeneralprofileDTO> getList(Long groupId,GeneralprofileSortBy sortby) throws SystemException;
	
}
