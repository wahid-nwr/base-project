package com.swiftcorp.portal.samplecom.dao;

import java.util.ArrayList;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.samplecom.SamplecomSuccessResult;
import com.swiftcorp.portal.samplecom.dto.SamplecomDTO;
import com.swiftcorp.portal.samplecom.exception.SamplecomNotFoundException;
import com.swiftcorp.portal.samplecom.dao.ISamplecomDAO.SamplecomSortBy;

/*
 * @author swift corporation
 * @since mar 3, 2011
 */

public interface ISamplecomDAO 
{
  public enum SamplecomSortBy {uniqueCode, adminType, firstName, lastname};
  public enum SamplecomWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public SamplecomDTO get(Long componentId)throws SystemException; 
  public SamplecomDTO get(String unicodeCode)throws SystemException; 
  public SamplecomSuccessResult add(SamplecomDTO samplecomDTO)throws SystemException;
  public SamplecomSuccessResult modify(SamplecomDTO samplecomDTO)throws SystemException;
  public SamplecomSuccessResult remove(SamplecomDTO samplecomDTO)throws SystemException;
  
  public ArrayList<SamplecomDTO> getList() throws SystemException;
  public ArrayList<SamplecomDTO> getList(Long groupId,SamplecomSortBy sortby) throws SystemException;
	
}
