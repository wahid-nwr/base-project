package com.swiftcorp.portal.emailrecipients.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class EmailrecipientsDTO extends PersistentCapableDTO
{
 
 private String companyEmailAddress = null ;
 private String messageId = null ;
 private long contactId = 0 ;
 private String recipientsType = null ;
 private String emailStatus = null ;
 private long emailRecvId = 0 ;
 private String messageType = null ;
 
 public String getCompanyEmailAddress( )
 {
 	 return this.companyEmailAddress;
 }
 public String getMessageId( )
 {
 	 return this.messageId;
 }
 public long getContactId( )
 {
 	 return this.contactId;
 }
 public String getRecipientsType( )
 {
 	 return this.recipientsType;
 }
 public String getEmailStatus( )
 {
 	 return this.emailStatus;
 }
 public long getEmailRecvId( )
 {
 	 return this.emailRecvId;
 }
 public String getMessageType( )
 {
 	 return this.messageType;
 }
 
 public void setCompanyEmailAddress(String companyEmailAddress)
 {
 	 this.companyEmailAddress = companyEmailAddress ;
 }
 public void setMessageId(String messageId)
 {
 	 this.messageId = messageId ;
 }
 public void setContactId(long contactId)
 {
 	 this.contactId = contactId ;
 }
 public void setRecipientsType(String recipientsType)
 {
 	 this.recipientsType = recipientsType ;
 }
 public void setEmailStatus(String emailStatus)
 {
 	 this.emailStatus = emailStatus ;
 }
 public void setEmailRecvId(long emailRecvId)
 {
 	 this.emailRecvId = emailRecvId ;
 }
 public void setMessageType(String messageType)
 {
 	 this.messageType = messageType ;
 }
 
}
