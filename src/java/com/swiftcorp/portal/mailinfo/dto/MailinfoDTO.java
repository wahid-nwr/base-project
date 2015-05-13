package com.swiftcorp.portal.mailinfo.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class MailinfoDTO extends PersistentCapableDTO
{
 
 private String companyMail = null ;
 
 public String getCompanyMail( )
 {
 	 return this.companyMail;
 }
 
 public void setCompanyMail(String companyMail)
 {
 	 this.companyMail = companyMail ;
 }
 
}
