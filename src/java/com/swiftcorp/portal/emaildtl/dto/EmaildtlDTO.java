package com.swiftcorp.portal.emaildtl.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class EmaildtlDTO extends PersistentCapableDTO
{
 
 private long emailId = 0 ;
 private String contentType = null ;
 private long content = 0 ;
 
 public long getEmailId( )
 {
 	 return this.emailId;
 }
 public String getContentType( )
 {
 	 return this.contentType;
 }
 public long getContent( )
 {
 	 return this.content;
 }
 
 public void setEmailId(long emailId)
 {
 	 this.emailId = emailId ;
 }
 public void setContentType(String contentType)
 {
 	 this.contentType = contentType ;
 }
 public void setContent(long content)
 {
 	 this.content = content ;
 }
 
}
