package com.swiftcorp.portal.ajan.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class AjanDTO extends PersistentCapableDTO
{
 
 private String ajanId = null ;
 private String ajanName = null ;
 
 public String getAjanId( )
 {
 	 return this.ajanId;
 }
 public String getAjanName( )
 {
 	 return this.ajanName;
 }
 
 public void setAjanId(String ajanId)
 {
 	 this.ajanId = ajanId ;
 }
 public void setAjanName(String ajanName)
 {
 	 this.ajanName = ajanName ;
 }
 
}
