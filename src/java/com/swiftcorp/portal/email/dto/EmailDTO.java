package com.swiftcorp.portal.email.dto;
 
import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;
public class EmailDTO extends PersistentCapableDTO
{
 
 private String sender = null ;
 private Calendar sendDate = null ;
 private String subject = null ;
 private String type = null ;
 private String eChecked = null ;
 private String senderType = null ;
 private String others = null ;
 private String ossBy = null ;
 private long refId = 0 ;
 private long allRecipients = 0 ;
 private long cc = 0 ;
 private String recepient = null ;
 private long internalUserId = 0 ;
 private String ossStatus = null ;
 private int priority = 0 ;
 private long emailGroupId = 0 ;
 private long comId = 0 ;
 
 public String getSender( )
 {
 	 return this.sender;
 }
 public Calendar getSendDate( )
 {
 	 return this.sendDate;
 }
 public String getSubject( )
 {
 	 return this.subject;
 }
 public String getType( )
 {
 	 return this.type;
 }
 public String geteChecked( )
 {
 	 return this.eChecked;
 }
 public String getSenderType( )
 {
 	 return this.senderType;
 }
 public String getOthers( )
 {
 	 return this.others;
 }
 public String getOssBy( )
 {
 	 return this.ossBy;
 }
 public long getRefId( )
 {
 	 return this.refId;
 }
 public long getAllRecipients( )
 {
 	 return this.allRecipients;
 }
 public long getCc( )
 {
 	 return this.cc;
 }
 public String getRecepient( )
 {
 	 return this.recepient;
 }
 public long getInternalUserId( )
 {
 	 return this.internalUserId;
 }
 public String getOssStatus( )
 {
 	 return this.ossStatus;
 }
 public int getPriority( )
 {
 	 return this.priority;
 }
 public long getEmailGroupId( )
 {
 	 return this.emailGroupId;
 }
 public long getComId( )
 {
 	 return this.comId;
 }
 
 public void setSender(String sender)
 {
 	 this.sender = sender ;
 }
 public void setSendDate(Calendar sendDate)
 {
 	 this.sendDate = sendDate ;
 }
 public void setSubject(String subject)
 {
 	 this.subject = subject ;
 }
 public void setType(String type)
 {
 	 this.type = type ;
 }
 public void seteChecked(String eChecked)
 {
 	 this.eChecked = eChecked ;
 }
 public void setSenderType(String senderType)
 {
 	 this.senderType = senderType ;
 }
 public void setOthers(String others)
 {
 	 this.others = others ;
 }
 public void setOssBy(String ossBy)
 {
 	 this.ossBy = ossBy ;
 }
 public void setRefId(long refId)
 {
 	 this.refId = refId ;
 }
 public void setAllRecipients(long allRecipients)
 {
 	 this.allRecipients = allRecipients ;
 }
 public void setCc(long cc)
 {
 	 this.cc = cc ;
 }
 public void setRecepient(String recepient)
 {
 	 this.recepient = recepient ;
 }
 public void setInternalUserId(long internalUserId)
 {
 	 this.internalUserId = internalUserId ;
 }
 public void setOssStatus(String ossStatus)
 {
 	 this.ossStatus = ossStatus ;
 }
 public void setPriority(int priority)
 {
 	 this.priority = priority ;
 }
 public void setEmailGroupId(long emailGroupId)
 {
 	 this.emailGroupId = emailGroupId ;
 }
 public void setComId(long comId)
 {
 	 this.comId = comId ;
 }
 
}
