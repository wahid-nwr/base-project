package com.swiftcorp.portal.geo.dao;
import java.util.ArrayList;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.geo.GeoSuccessResult;
import com.swiftcorp.portal.geo.dto.GeoDTO;
public interface IGeoDAO 
{
  public enum GeoSortBy {code, adminType, firstName, lastname};
  public enum GeoSortByName {name};
  public enum GeoWhereCondition {uniqueCode, adminType, firstName, lastname};
	
  public GeoDTO get(Long componentId)throws SystemException; 
  public GeoDTO get(String unicodeCode)throws SystemException; 
  public GeoSuccessResult add(GeoDTO geoDTO)throws SystemException;
  public GeoSuccessResult modify(GeoDTO geoDTO)throws SystemException;
  public GeoSuccessResult remove(GeoDTO geoDTO)throws SystemException;
  
  public ArrayList<GeoDTO> getList() throws SystemException;
  public ArrayList<GeoDTO> getList(Long groupId,GeoSortBy sortby) throws SystemException;
  
  public ArrayList<GeoDTO> getGeoListByGeoType(int geoType) throws SystemException ;
  public ArrayList<GeoDTO> getListByGeoType(int geoType,GeoSortByName geoName) throws SystemException ;
  
	
}
