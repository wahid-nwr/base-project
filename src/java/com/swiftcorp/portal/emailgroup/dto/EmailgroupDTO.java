package com.swiftcorp.portal.emailgroup.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class EmailgroupDTO extends PersistentCapableDTO
{
 
 private String groupName = null ;
 
 public String getGroupName( )
 {
 	 return this.groupName;
 }
 
 public void setGroupName(String groupName)
 {
 	 this.groupName = groupName ;
 }
 
}
