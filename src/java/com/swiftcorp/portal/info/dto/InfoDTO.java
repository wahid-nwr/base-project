package com.swiftcorp.portal.info.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class InfoDTO extends PersistentCapableDTO
{
 
 private String infoId = null ;
 private String infoDetail = null ;
 
 public String getInfoId( )
 {
 	 return this.infoId;
 }
 public String getInfoDetail( )
 {
 	 return this.infoDetail;
 }
 
 public void setInfoId(String infoId)
 {
 	 this.infoId = infoId ;
 }
 public void setInfoDetail(String infoDetail)
 {
 	 this.infoDetail = infoDetail ;
 }
 
}
