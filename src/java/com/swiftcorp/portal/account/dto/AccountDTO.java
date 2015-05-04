package com.swiftcorp.portal.account.dto;
 
import java.util.Calendar;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class AccountDTO extends PersistentCapableDTO
{
 
 private String code = null ;
 private String name = null ;
 private long parent = 0 ;
 
 public String getCode( )
 {
 	 return this.code;
 }
 public String getName( )
 {
 	 return this.name;
 }
 public long getParent( )
 {
 	 return this.parent;
 }
 
 public void setCode(String code)
 {
 	 this.code = code ;
 }
 public void setName(String name)
 {
 	 this.name = name ;
 }
 public void setParent(long parent)
 {
 	 this.parent = parent ;
 }
 
}
