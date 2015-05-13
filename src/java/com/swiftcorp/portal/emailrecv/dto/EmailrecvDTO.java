package com.swiftcorp.portal.emailrecv.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class EmailrecvDTO extends PersistentCapableDTO
{
 
 private String companyEmailAddress = null ;
 private String popHost = null ;
 private String otherPartyEmailAddress = null ;
 private String isSSL = null ;
 private int popPort = 0 ;
 private String password = null ;
 private String taggedUserId = null ;
 private String smtpHost = null ;
 private int smtpPort = 0 ;
 private String displayName = null ;
 
 public String getCompanyEmailAddress( )
 {
 	 return this.companyEmailAddress;
 }
 public String getPopHost( )
 {
 	 return this.popHost;
 }
 public String getOtherPartyEmailAddress( )
 {
 	 return this.otherPartyEmailAddress;
 }
 public String getIsSSL( )
 {
 	 return this.isSSL;
 }
 public int getPopPort( )
 {
 	 return this.popPort;
 }
 public String getPassword( )
 {
 	 return this.password;
 }
 public String getTaggedUserId( )
 {
 	 return this.taggedUserId;
 }
 public String getSmtpHost( )
 {
 	 return this.smtpHost;
 }
 public int getSmtpPort( )
 {
 	 return this.smtpPort;
 }
 public String getDisplayName( )
 {
 	 return this.displayName;
 }
 
 public void setCompanyEmailAddress(String companyEmailAddress)
 {
 	 this.companyEmailAddress = companyEmailAddress ;
 }
 public void setPopHost(String popHost)
 {
 	 this.popHost = popHost ;
 }
 public void setOtherPartyEmailAddress(String otherPartyEmailAddress)
 {
 	 this.otherPartyEmailAddress = otherPartyEmailAddress ;
 }
 public void setIsSSL(String isSSL)
 {
 	 this.isSSL = isSSL ;
 }
 public void setPopPort(int popPort)
 {
 	 this.popPort = popPort ;
 }
 public void setPassword(String password)
 {
 	 this.password = password ;
 }
 public void setTaggedUserId(String taggedUserId)
 {
 	 this.taggedUserId = taggedUserId ;
 }
 public void setSmtpHost(String smtpHost)
 {
 	 this.smtpHost = smtpHost ;
 }
 public void setSmtpPort(int smtpPort)
 {
 	 this.smtpPort = smtpPort ;
 }
 public void setDisplayName(String displayName)
 {
 	 this.displayName = displayName ;
 }
 
}
