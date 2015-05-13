package com.swiftcorp.portal.home.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class HomeDTO extends PersistentCapableDTO
{
 
 private String homeId = null ;
 private String homeChiefName = null ;
 private Calendar regDate = null ;
 private String homeMember = null ;
 
 public String getHomeId( )
 {
 	 return this.homeId;
 }
 public String getHomeChiefName( )
 {
 	 return this.homeChiefName;
 }
 public Calendar getRegDate( )
 {
 	 return this.regDate;
 }
 public String getHomeMember( )
 {
 	 return this.homeMember;
 }
 
 public void setHomeId(String homeId)
 {
 	 this.homeId = homeId ;
 }
 public void setHomeChiefName(String homeChiefName)
 {
 	 this.homeChiefName = homeChiefName ;
 }
 public void setRegDate(Calendar regDate)
 {
 	 this.regDate = regDate ;
 }
 public void setHomeMember(String homeMember)
 {
 	 this.homeMember = homeMember ;
 }
 
}
