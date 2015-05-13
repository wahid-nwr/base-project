package com.swiftcorp.portal.emailreferance.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class EmailreferanceDTO extends PersistentCapableDTO
{
 
 private String companyEmailAddress = null ;
 private String refDtl = null ;
 private long emailGroupId = 0 ;
 
 public String getCompanyEmailAddress( )
 {
 	 return this.companyEmailAddress;
 }
 public String getRefDtl( )
 {
 	 return this.refDtl;
 }
 public long getEmailGroupId( )
 {
 	 return this.emailGroupId;
 }
 
 public void setCompanyEmailAddress(String companyEmailAddress)
 {
 	 this.companyEmailAddress = companyEmailAddress ;
 }
 public void setRefDtl(String refDtl)
 {
 	 this.refDtl = refDtl ;
 }
 public void setEmailGroupId(long emailGroupId)
 {
 	 this.emailGroupId = emailGroupId ;
 }
 
}
