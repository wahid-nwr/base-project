package com.swiftcorp.portal.mailinfo.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.swiftcorp.portal.common.util.CalendarUtils;

public class MailSqlGenerator {
	//contacts_company_json
	
	public static String getCompanyContactsByCompany()
	{
		String sql = "SELECT DISTINCT componentId,companyname,email FROM users WHERE companyname IS NOT NULL AND email IS NOT NULL ORDER BY companyname";
		return sql;
	}
	
	public static String getCompanyContactsByOffice()
	{
		String sql = "SELECT NAME,email,OMS_USER_ID FROM users WHERE email IS NOT NULL";
		return sql;
	}
	//initial_load
	public static String getTotalEmailRecipients(String user_id)
	{
		String sql= "SELECT COUNT(*) AS total FROM emailrecipients WHERE contactid ='"+user_id+"' AND messagetype ='SHARE' AND emailstatus ='U'" ;
		return sql;
	}
	
	public static String getEmailDtlFromEmailRecv(String user_id)
	{
		String sql= " SELECT em_rc.componentId,em_rc.companyemailaddress, em_rc.displayname ,(SELECT COUNT(*) AS total  FROM  emailrecipients em_rcp"+ 
		  "	WHERE em_rcp.emailrecvid = em_rc.componentId AND em_rcp.emailstatus = 'U')AS total "+
		  " FROM emailrecv em_rc  WHERE taggeduserid ='"+user_id+"' ";
		return sql;
	}
	
	public static String getCompanyInbox()
	{
		String sql= "SELECT DISTINCT componentId,companyname,email FROM users WHERE companyname IS NOT NULL AND email IS NOT NULL ORDER BY companyname";
		return sql;
	}
	
	public static String getCompanyInbox(String user_id,String comId)
	{
		String sql = "SELECT COUNT(*) AS total, (SELECT groupname FROM emailgroup em_gr WHERE em_gr.componentId = em.emailgroupid) AS groupname, ";
		sql += " em.emailgroupid FROM email em,emailrecipients em_rcp  WHERE em_rcp.messageid = em.componentId AND em_rcp.emailstatus = 'U'";
		sql += " AND em_rcp.contactid ='"+user_id+"' AND em.comid ='"+comId+"' GROUP BY em.emailgroupid ";
		return sql;
	}
	
	public static String getCompanyUnread(String comId)
	{
		String sql = " SELECT COUNT(*) AS total, (SELECT em_gr.groupname FROM emailgroup em_gr WHERE em_gr.componentId = em.emailgroupid) AS groupname,";
		sql += " em.emailgroupid FROM email em WHERE em.comid = '"+comId+"' GROUP BY em.emailgroupid";
		return sql;
	}
	
	public static String getCompanyUnread()
	{
		/*
		 * 
		SELECT componentId,comments,( select max(componentId) from msgdetail where msgid=componentId) msgId, 
			ownerid,creationtime,refid,type,
		(firstName||' '||lastName) as NAME FROM 
			(select hi.componentid AS treeitem, comments,ownerid,creationtime,refid,type,level	
			FROM    (     
			SELECT  hierarchy_connect_by_parent_eq_prior_id(componentid) AS componentid, @level AS level 
			     FROM    (    
			         SELECT  @start_with := -1, 
			            @componentid := @start_with,
			             @level := 0 
			            ) vars, comments   
			  WHERE   @componentid IS NOT NULL ) ho JOIN    
			comments hi ON  
			    hi.componentid = ho.componentid) oc,users ou where oc.ownerid=ou.componentId
		 */
		List<String> fields = new ArrayList<String>();
		fields.add("comments");
		fields.add("ownerid");
		fields.add("creationtime");
		fields.add("type");
		
		String sql = "SELECT oc.componentId,COMMENTS,( select max(id) from MSG_DETAIL where msg_id=oc.id) msgId, OWNER_ID,CREATION_TIME,REF_ID,TYPE, ";
		sql += " (first_name||' '||last_name) as NAME FROM COMMENTS oc,OMS_USERS@OMS_LINK ou where oc.OWNER_ID=ou.componentId ";
		sql += " CONNECT BY PRIOR oc.componentId=REF_ID START WITH REF_ID=0 order siblings by creation_time desc";
		
		sql = "SELECT componentId,comments,( select max(componentId) from msgdetail where msgid=componentId) msgId," +
				"			ownerid,creationtime,refid,type," +
				"		(firstName||' '||lastName) as name FROM (";
		sql += getHeirarchicalQuery(fields, "refid", "comments");
		sql += ") oc,users ou where oc.ownerid=ou.componentId";
		System.out.println("heirarchical query::"+sql);
		return sql;
	}
	
	//com_gr_mail_json
	public static String getCompanyUnreadBySender(String com_id,String gr_id)
	{
		String sql = " SELECT COUNT(*) AS total,em.sender FROM email em WHERE em.comid = '"+com_id+"' ";
		sql += " AND em.emailgroupid = '"+gr_id+"' GROUP BY em.sender " ;
		return sql;
	}
	
	//inbox
	/**
	*	Return sent  messages
	*/
	public static String getSentMsg(String user_id)
	{
		String sql = "";
		sql = "SELECT  email.componentId,email.sender,email.recipient,email.cc,email.senddate AS senddate,"; 
		sql += " email.subject,emailrecipients.emailstatus";
		sql += " FROM email,emailrecipients WHERE emailrecipients.contactid='"+user_id+"' AND emailrecipients.messageid = email.componentId";
		sql += " AND emailrecipients.messagetype='SEND_EMAIL'";
		return sql;
	}
	
	/**
	*	Return draft  messages
	*/
	public static String getDraftMsg(String user_id)
	{
		String sql = "";
		sql = "";
		sql = "SELECT  email.componentId,email.sender,email.recipient,email.cc,email.senddate AS senddate,"; 
		sql += " email.subject,emailrecipients.emailstatus";
		sql += " FROM email,emailrecipients WHERE emailrecipients.contactid='"+user_id+"' AND emailrecipients.messageid = email.componentId";
		sql += " AND emailrecipients.messagetype='DRAFT_EMAIL'";
		return sql;
	}
	/**
	*	Return share  messages
	*/
	
	public static String getShareMsg(String user_id)
	{
		String sql = "";
		sql = "SELECT  email.componentId,email.sender,email.recipient,email.cc,email.senddate AS senddate,"; 
		sql += " email.subject,emailrecipients.emailstatus";
		sql += " FROM email,emailrecipients WHERE emailrecipients.contactid='"+user_id+"' AND emailrecipients.messageid = email.componentId";
		sql += " AND emailrecipients.messagetype='SHARE'";
		return sql;
	}
	
	/**
	*	Return group -> Company  messages
	*/
	
	public static String getCompanyGroupMsg(String user_id,String com_id)
	{
		String sql = "";
		sql = " SELECT  email.componentId,email.sender,email.recipient,email.cc,email.senddate AS senddate,";
		sql += " email.subject,emailrecipients.emailstatus ";
		sql += " FROM email,emailrecipients WHERE  emailrecipients.contactid = '"+user_id+"' AND emailrecipients.contactid = email.componentId";
		sql += " AND comid = '"+com_id+"' ORDER BY email.senddate DESC";
		return sql;
	}
	
	/**
	*	Return Company -> Person Mails
	*/
	
	public static String getPersonalMsg(String user_id,String com_id,String email_address)
	{
		String sql = "";
		sql = "SELECT email.componentId,email.sender,email.recipient,email.cc,email.senddate AS senddate,";
		sql += " email.subject,emailrecipients.emailstatus ";
		sql += " FROM email,emailrecipients WHERE emailrecipients.contactid = email.componentId AND emailrecipients.contactid = '"+user_id+"' ";
		sql += " AND email.sender LIKE '%"+email_address+"%'  AND email.comid = '"+com_id+"' "; 
		sql += "  ORDER BY email.senddate DESC";
		return sql;
	}
	
	/**
	*	Return group messages
	*/
	
	public static String getGroupMsg(String user_id,String com_id,String email_group_id)
	{
		String sql = "";
		sql = "SELECT email.componentId,email.sender,email.recipient,email.cc,email.senddate AS senddate,";
		sql += " email.subject,emailrecipients.emailstatus ";
		sql += " FROM email,emailrecipients WHERE emailrecipients.contactid = email.componentId AND emailrecipients.contactid = '"+user_id+"' ";
		sql += " AND email.emailgroupid = '"+email_group_id+"' AND email.comid = '"+com_id+"' "; 
		sql += "  ORDER BY email.senddate DESC";
		return sql;
	}
	
	/**
	*	Return all/ one recv_id  messages
	*/	
	public static String getMailInbox(String user_id,String recv_id)
	{
		String sql = "";
		System.out.println("recv_id::::"+recv_id);
		sql = " SELECT email.componentId,email.sender,email.recipient,email.cc,email.senddate AS senddate, "; 
		sql += " email.subject,emailrecipients.emailstatus FROM email,emailrecipients ";
		sql += " WHERE emailrecipients.messagetype='MAIL' and emailrecipients.messageid = email.componentId AND emailrecipients.contactid = '"+user_id+"' ";
		if(recv_id!=null && !recv_id.equals("null") && recv_id.length()>0)
		{
			sql += " AND emailrecipients.emailrecvid = "+recv_id+"";
		}
		sql += " ORDER BY email.senddate DESC";
		return sql;
	}
	
	//markAsRead
	public static String getMarkAsRead(String total_id)
	{
		String sql = "";
		sql = "UPDATE emailrecipients SET emailstatus='I' WHERE messageid IN ("+total_id+")";
		return sql;
	}
	
	//markAsUnRead
	public static String getMarkAsUnRead(String total_id)
	{
		String sql = "";
		sql = "UPDATE emailrecipients SET emailstatus='U' WHERE messageid IN ("+total_id+")";
		return sql;
	}
	
	//pendingMails
	public static String getPendingTotal()
	{
		String sql = "";
		sql = "SELECT COUNT(*)AS total FROM notificationoutbox WHERE sendstatus='pending'";
		return sql;
	}
	
	public static String getPendingDtl()
	{
		String sql = "";
		sql = "SELECT email.componentId,email.sender,email.recipient,TO_CHAR(email.senddate,'mm/dd/yyyy') AS senddate, email.subject, OUTBOX.sendstatus"; 
		sql += " FROM email, notificationoutbox OUTBOX WHERE email.componentId = OUTBOX.OBJECT_ID AND OUTBOX.sendstatus = 'pending'";
		return sql;
	}
	
	//mailDetails
	public static String getRecipient(String email_id)
	{
		String sql = "";
		sql = "SELECT email.recipient ,email.cc	FROM email WHERE email.componentId = '"+email_id+"'";
		return sql;
	}
	public static String getContents(String email_id)
	{
		String sql = "";
		sql = "SELECT content FROM emaildtl WHERE emailid = '"+email_id+"' AND contenttype = 'ATTACHMENT'";
		return sql;
	}
	
	public static String getOfficeContacts()
	{
		String sql = "";
		sql = "SELECT componentId,(FIRST_NAME||' '||LAST_NAME) AS NAME FROM OMS_USERS@OMS_LINK WHERE USERROLE='OFFICE'";
		return sql;
	}
	
	public static String getMailReference(String email_id)
	{
		String sql = "";
		sql = "SELECT refid,(SELECT refdtl FROM emailreferance  WHERE componentId = email.refid) AS refdtl,";
		sql += " emailgroupid, (SELECT groupname FROM emailgroup  WHERE emailgroup.componentId = email.emailgroupid) AS ";
		sql += " groupname,comid,(SELECT companyname FROM users WHERE componentId=email.comid) AS comname FROM email WHERE componentId='"+email_id+"'";
		return sql;
	}
	
	public static String getMailGroup()
	{
		String sql = "";
		sql = "SELECT componentId,groupname FROM emailgroup";
		return sql;
	}
	public static String getRefDtl()
	{
		String sql = "";
		sql = "SELECT componentId, refdtl, emailgroupid FROM emailreferance";
		return sql;
	}
	public static String getDistinctContact()
	{
		String sql = "";
		sql = "SELECT DISTINCT componentId,companyname FROM users WHERE companyname IS NOT NULL AND email IS NOT NULL ORDER BY companyname";
		return sql;
	}
	
	public static String getMailBodyAttachment(String email_id)
	{
		String sql = "";
		sql = "SELECT contenttype, content FROM emaildtl WHERE emailid = "+email_id+" AND contenttype like '%text/html%'";
		return sql;
	}
	
	public static String getMailBody(String email_id)
	{
		String sql = "";
		sql = "SELECT contenttype, content FROM emaildtl WHERE emailid = "+email_id+" AND AND contenttype != 'ATTACHMENT'";
		return sql;
	}
	
	public static String updateStatus(String email_id)
	{
		String sql = "";
		sql = "UPDATE emailrecipients SET emailstatus='I' WHERE messageid="+email_id+"";
		return sql;
	}
	//contacts_company
	public static String getCompanyContacts(String com_id)
	{
		String sql = "";
		sql = "SELECT CONTACT_NAME,email,(SELECT groupname FROM emailgroup WHERE emailgroup.componentId = CONTACT.emailgroupid) AS emailgroup ";
		sql += "FROM CONTACT WHERE USER_ID = "+com_id+" ";
		return sql;
	}
	public static String getCompanyContactsWithoutComId()
	{
		String sql = "";
		sql = "SELECT DISTINCT componentId,companyname,email FROM users WHERE companyname IS NOT NULL AND email IS NOT NULL ORDER BY companyname";
		return sql;
	}
	//office_contacts
	public static String getOfficeContactsWithoutParent()
	{
		String sql = "";
		sql = "SELECT id, name, level FROM ADMIN CONNECT BY PRIOR id = parent START WITH parent = -1";
		return sql;
	}
	//taggedEmail
	public static String getTaggedEmail(String user_id)
	{
		String sql = "";
		sql = "SELECT company_email_address,pop_host,pop_port,is_ssl FROM emailrecv where taggeduserid = '"+user_id+"' ";
		return sql;
	}
	
	//count_inbox
	public static String getInboxMailCount(String user_id)
	{
		String sql = "";
		sql = " SELECT componentId,companyemailaddress, displayname FROM emailrecv  WHERE taggeduserid = '"+user_id+"' ";
		return sql;
	}
	public static String getInboxMailCountTotal(String sub_id)
	{
		String sql = "";
		sql = "SELECT COUNT(*) AS total  FROM  emailrecipients WHERE emailrecipients.componentId = "+sub_id+" AND emailstatus = 'U' ";
		return sql;
	}
	
	//company_inbox
	public static String getCompanyFromUsers()
	{
		String sql = "";
		sql = "SELECT DISTINCT componentId,companyname,email FROM users WHERE companyname IS NOT NULL AND email IS NOT NULL ORDER BY companyname";
		return sql;
	}
	
	public static String getCompanyInboxDtlByUser(String user_id,String com_id)
	{
		String sql = "";
		sql += "SELECT COUNT(*) AS total, (SELECT groupname FROM emailgroup WHERE emailgroup.componentId = email.emailgroupid) AS groupname, ";
		sql += " email.emailgroupid FROM email,emailrecipients WHERE emailrecipients.contactid = email.componentId AND emailrecipients.emailstatus = 'U' ";
		sql += " AND emailrecipients.contactid='"+user_id+"' AND email.comid='"+com_id+"' GROUP BY email.emailgroupid ";
		return sql;
	}
	
	public static String getCompanyInboxDtl(String com_id)
	{
		String sql = "";
		sql = " SELECT COUNT(*) AS total, (SELECT groupname FROM emailgroup WHERE emailgroup.componentId = email.emailgroupid) AS groupname,";
		sql += " email.emailgroupid FROM email WHERE email.comid = '"+com_id+"' GROUP BY email.emailgroupid";
		return sql;
	}
	
	//mail_search
	
	public static String getEmailSearchTotal(String user_id,String topic)
	{
		String sql = "";
		sql = "SELECT COUNT(*) AS total ";
		sql += " FROM ( SELECT email.componentId,email.sender,email.senddate AS senddate, email.subject,";
		sql += " SUBSTR(emaildtl.content,1,200) AS BODY_TEXT FROM email, emaildtl WHERE email.componentId = emaildtl.emailid";
		sql += " AND SUBSTR(emaildtl.contenttype,1,10)='text/plain' AND ( LOWER(email.subject) LIKE '% "+topic+" %'";
		sql += " OR LOWER(email.subject) LIKE '% "+topic+"' OR LOWER(email.subject) LIKE '"+topic+" %' OR LOWER(emaildtl.content) LIKE '% "+topic+" %'";
		sql += " OR LOWER(emaildtl.content) LIKE '"+topic+" %' OR LOWER(emaildtl.content) LIKE '% "+topic+"' ))email,";
		sql += " ( SELECT messageid,emailstatus FROM emailrecipients WHERE contactid = '"+user_id+"' )REC WHERE email.componentId = REC.messageid";
		return sql;
	}
	
	public static String getEmailSearch(String user_id,String topic)
	{
		String sql = "";
		sql = "SELECT email.componentId,email.sender,email.senddate, email.subject,email.BODY_TEXT, REC.emailstatus ";
		sql += " FROM ( SELECT email.componentId,email.sender,email.senddate AS senddate, email.subject,";
		sql += " SUBSTR(emaildtl.content,1,1000) AS BODY_TEXT FROM email, emaildtl WHERE email.componentId = emaildtl.emailid";
		sql += " AND SUBSTR(emaildtl.contenttype,1,10)='text/plain' AND ( LOWER(email.subject) LIKE '% "+topic+" %'";
		sql += " OR LOWER(email.subject) LIKE '% "+topic+"' OR LOWER(email.subject) LIKE '"+topic+" %' OR LOWER(emaildtl.content) LIKE '% "+topic+" %'";
		sql += " OR LOWER(emaildtl.content) LIKE '"+topic+" %' OR LOWER(emaildtl.content) LIKE '% "+topic+"' ))email,";
		sql += " ( SELECT messageid,emailstatus FROM emailrecipients WHERE contactid = '"+user_id+"' )REC WHERE email.componentId = REC.messageid";
		return sql;
	}
	
	//show_gr_dtl
	public static String getGroupDtl(String com_id,String gr_id)
	{
		String sql = "";
		sql = " SELECT COUNT(*) AS total,email.sender FROM email WHERE email.comid = '"+com_id+"' ";
		sql += " AND emailgroupid = '"+gr_id+"' GROUP BY email.sender " ;
		return sql;
	}
	public static String getHeirarchicalQuery(List<String> fields,String parent,String table)
	{
		String sql = "";
		
		sql += "	SELECT  hi.componentid AS treeitem, "+parent;
		for(int i=0;fields!=null && i<fields.size();i++)
		{
			sql += ","+fields.get(i);
		}
		sql += ",level";
		sql +="		FROM    (" +
				"		SELECT  hierarchy_connect_by_parent_eq_prior_id(componentid) AS componentid, @level AS level" +
				"       FROM    (" +
				"		         SELECT  @start_with := -1," +
				"                @componentid := @start_with," +
				"	             @level := 0" +
				"	            ) vars, "+table+" hierarchy_connect_by_parent_eq_prior_id" +
				"       WHERE   @componentid IS NOT NULL ) ho JOIN" +
				"		"+table+" hi ON" +
				"	hi.componentid = ho.componentid";
		
		return sql;
	}
	
	//CheckMailSingleUser
	
	public static String getMaxSendDate(String user_id,String emailId)
	{
		String sql = "";
		sql = 	"SELECT MAX(senddate) FROM emailrecipients WHERE emailrecvid = (SELECT componentId FROM emailrecv WHERE ";
		sql +=  " taggeduserid ="+user_id+" AND companyemailaddress LIKE '%"+emailId+"%')";
		return sql;
	}
	
	public static String getMaxEmailRecipientsComponentId(String user_id,String emailId)
	{
		String sql = "";
		sql = "SELECT max(componentId)+1 as EMAIL_ID FROM emailrecipients";
		return sql;
	}
	
	public static String getEmailRecvComponentId(String emailId)
	{
		String sql = "";
		sql = "SELECT componentId FROM emailrecv WHERE companyemailaddress LIKE '%"+emailId+"%' ";
		return sql;
	}
	
	public static String getEmailRecipientsMaxComponentId()
	{
		String sql = "";
		sql = "SELECT max(componentId)+1 FROM emailrecipients";
		return sql;
	}
	
	public static String getInsertEmailRecipients(String componentId,String user_id,String email_id,String status, String email_recv_id,String message_type,Date sendDate)
	{
		SimpleDateFormat sd = new SimpleDateFormat(CalendarUtils.MYSQL_DATETIME_FORMAT);	
		String sql = "";
		String date = "";
		if(sendDate!=null)
		{
			date = sd.format(sendDate);
		}
		else
		{
			date = sd.format(new Date());
		}
		sql = "INSERT INTO emailrecipients(componentId,messageid,contactid,emailstatus,emailrecvid,messagetype,senddate	) VALUES("+componentId+", ";
		sql += ""+email_id+",'"+user_id+"','"+status+"','"+email_recv_id+"','"+message_type+"','"+date+"' )";
		return sql;
	}
	
	public static String getMaxEmailRecvComponentId(String user_id,String emailId)
	{
		String sql = "";
		sql = "SELECT componentId FROM emailrecv WHERE companyemailaddress LIKE '%"+emailId+"%' ";
		return sql;
	}
	
	public static String getEmailGroupId(String senderEmail)
	{
		String sql = "";
		sql = "SELECT emailgroupid FROM emailcontact WHERE email LIKE'%"+senderEmail+"%'";
		return sql;
	}
	
	public static String getUsers(String senderEmail)
	{
		String sql = "";
		sql = "SELECT * FROM users WHERE email LIKE'%"+senderEmail+"%'";
		return sql;
	}
	
	public static String getMaxEmailComponentId()
	{
		String sql = "";
		sql = "SELECT max(componentId)+1 as EMAIL_ID FROM email";
		return sql;
	}
	
	public static String getInsertIntoEmail(String email_id,String priority,String email_group_id)
	{
		String sql = "";
		sql = " INSERT INTO email(componentId,sender,recipient,cc,senddate,subject,allrecipients,echecked,priority";
		if(email_group_id!=null && !email_group_id.equals("null") && email_group_id.length()>0)
		{
			sql += ",emailgroupid";
		}
		sql +=	")";
		sql +=  " VALUES("+email_id+",?,?,?,?,?,?,'U','"+priority+"'" ;
		if(email_group_id!=null && !email_group_id.equals("null") && email_group_id.length()>0)
		{
			sql += ",'"+email_group_id+"'";
		}
		sql += ")";
		return sql;
	}
	
	public static String getInsertIntoEmailEcheck(String email_id,String priority,String echeck)
	{
		String sql = "";
		sql = " INSERT INTO email(componentId,sender,recipient,cc,senddate,subject,allrecipients,echecked,priority)" +
		  " VALUES("+email_id+",?,?,?,?,?,?,'"+echeck+"','"+priority+"')";
		return sql;
	}
	
	public static String getMaxEmailDtlComponentId()
	{
		String sql = "";
		sql = "SELECT max(componentId)+1 as EMAIL_DTL_ID FROM emaildtl";
		return sql;
	}
	
	public static String getInsertIntoEmailDtl(String email_id,String contentType,String email_dtl_id)
	{
		String sql = "";
		sql = "INSERT INTO emaildtl(componentId,emailid,contenttype,content)" +
		" VALUES("+email_dtl_id+"," + email_id + ",'" + contentType + "',?)";
		return sql;
	}
	
	public static String getUpdateEmail(String email_id,String mail_com_id,String ref_group_id,String mail_group_id)
	{
		String sql = "";
		sql = "UPDATE email SET comid='"+mail_com_id+"', refid='"+ref_group_id+"',";
		sql += " emailgroupid='"+mail_group_id+"' WHERE componentId = "+email_id+"";
		return sql;
	}
	
	public static String getRecieveEmail(String user_id)
	{
		String sql = "";
		sql = "SELECT companyemailaddress, pophost, popport, password, isssl,componentId FROM emailrecv";
		if(user_id!=null && !user_id.equals("null") && user_id.length()>0)
		{
			 sql += " where taggeduserid = '"+user_id+"' ";
		}
		return sql;
	}
	
	public static String getMaxDate(String emailId)
	{
		String sql = "";
		sql = "SELECT (MAX(senddate),TO_DATE(TO_CHAR(SYSDATE,'dd-mm-rrrr')||' 00:00:00', 'dd-mm-rrrr hh24:mi:ss' ))  " +
				"FROM email WHERE recipient LIKE '%" + emailId + "%'";
		return sql;
	}
	
	//mailDetails
	public static String getRecipientByEmailId(String email_id)
	{
		String sql = "";
		sql = "SELECT recipient ,cc	FROM email WHERE componentId = '"+email_id+"'";
		return sql;
	}
	
	public static String getAttachmentByEmailId(String email_id)
	{
		String sql = "";
		sql = "SELECT content FROM emaildtl WHERE emailid = '"+email_id+"' AND contenttype = 'ATTACHMENT'";
		return sql;
	}
	
	public static String getOfficeContact()
	{
		String sql = "";
		sql = "SELECT componentId,concat(firstName,' ',lastName) AS name FROM users";//WHERE USERROLE='OFFICE'
		return sql;
	}
	
	public static String getReferenceByEmailId(String email_id)
	{
		String sql = "";
		sql = "SELECT refid,(SELECT refdtl FROM emailreferance em_ref  WHERE em_ref.componentId = email.refid) AS refdtl,";
		sql += " emailgroupid, (SELECT groupname FROM emailgroup  WHERE emailgroup.componentId = email.emailgroupid) AS ";
		sql += " groupname,comid,(SELECT companyname FROM users WHERE users.componentId=email.comid) AS comname FROM email WHERE email.componentId='"+email_id+"'";
		return sql;
	}
	
	public static String getGroupDtl()
	{
		String sql = "";
		sql = "SELECT componentId,groupname FROM emailgroup";	
		return sql;
	}
	
	public static String getReferalDtl()
	{
		String sql = "";
		sql = "SELECT componentId, refdtl, emailgroupid FROM emailreferance";	
		return sql;
	}
	
	public static String getUsersDtl()
	{
		String sql = "";
		sql = "SELECT DISTINCT componentId,companyname FROM users WHERE companyname IS NOT NULL AND email IS NOT NULL ORDER BY companyname";	
		return sql;
	}
	
	public static String getHtmlMailDtlByEmailId(String email_id)
	{
		String sql = "";
		sql = "SELECT contenttype, content FROM emaildtl WHERE emailid = "+email_id+" AND contenttype like '%text/html%'";	
		return sql;
	}
	
	public static String getNoAttachmentMailDtlByEmailId(String email_id)
	{
		String sql = "";
		sql = "SELECT contenttype, content FROM emaildtl WHERE emailid = "+email_id+" AND contenttype != 'ATTACHMENT'";	
		return sql;
	}
	
	public static String getUpdateRecipientByEmailId(String email_id)
	{
		String sql = "";
		sql = "UPDATE emailrecipients SET emailstatus='I' WHERE messageid="+email_id+"";	
		return sql;
	}
	
	//send mail
	
	public static String getOnlyRecipientByEmailId(String email_id)
	{
		String sql = "";
		sql = "SELECT recipient FROM email WHERE componentId="+email_id+"";	
		return sql;
	}
	
	public static String getMailServerInfo(String from)
	{
		String sql = "";		
		sql = "SELECT smtphost, smtpport, isssl, password FROM emailrecv WHERE companyemailaddress like '%"+from+"%' ";
		return sql;
	}
	
	public static String getMaxEmailRecvId(String from,String user_id)
	{
		String sql = "";		
		sql = "SELECT componentId FROM emailrecv WHERE companyemailaddress LIKE '%"+from+"%' AND taggeduserid = '"+user_id+"'  ";
		return sql;
	}
	
	public static String getMailServerInfo2(String from,String user_id)
	{
		String sql = "";		
		sql = "SELECT ID FROM emailrecv WHERE companyemailaddress LIKE '%"+from+"%' AND taggeduserid = '"+user_id+"'  ";
		return sql;
	}
	
	// email settings
	public static String getSettings(String user_id)
	{
		String sql = "";
		sql = "SELECT componentId,companyemailaddress,pophost,popport,isssl,password,taggeduserid,smtphost,smtpport,displayname FROM emailrecv where taggeduserid = '"+user_id+"' ";
		return sql;
	}
	
	public static String getMaxEmailRecvComponentId()
	{
		String sql = "";
		sql = "SELECT max(componentId)+1 as ROW_ID FROM emailrecv";
		return sql;
	}
	
	public static String getEmailRecvInsert(String req_email,String req_pop_host,String req_ssl,String req_pop_port,String req_uPass,String user_id,
			String req_smtp_host,String req_smtp_port,String new_row_id,String req_displayName)
	{
		String sql = "";
		sql += "INSERT INTO emailrecv ";
		sql += "(companyemailaddress,pophost,isssl,popport,password,taggeduserid,";
		sql += "smtphost,smtpport,componentId,displayname) ";
		sql += "VALUES ('"+req_email+"','"+req_pop_host+"','"+req_ssl+"', '"+req_pop_port+"', '"+req_uPass+"', '"+user_id+"', ";
		sql += "'"+req_smtp_host+"', '"+req_smtp_port+"', '"+new_row_id+"', '"+req_displayName+"')";
		return sql;
	}
	
	public static String getEmailRecvDelete(String req_row_id)
	{
		String sql = "";
		sql += "DELETE FROM emailrecv WHERE componentId="+req_row_id+"";
		return sql;
	}
	
	public static String getEmailRecvUpdate(String req_email,String req_pop_host,String req_ssl,String req_pop_port,String req_uPass,
			String req_smtp_host,String req_smtp_port,String req_row_id,String req_displayName)
	{
		String sql = "";
		sql += "UPDATE emailrecv SET companyemailaddress = '"+req_email+"', pophost='"+req_pop_host+"', isssl='"+req_ssl+"', ";
		sql += "popport='"+req_pop_port+"', password='"+req_uPass+"', smtphost='"+req_smtp_host+"', smtpport='"+req_smtp_port+"', ";
		sql += "displayname='"+req_displayName+"' WHERE componentId = "+req_row_id+"";
		return sql;
	}
	
	/**
	*	get max component id of share messages
	*/
	
	public static String getMaxComponentIdShareMsg(String user_id,String email_id)
	{
		String sql = "";
		sql = "SELECT max(componentId)+1 as ROW_ID FROM emailrecipients";
		return sql;
	}
	/**
	*	insert share messages
	*/
	
	public static String getInsertShareMsg(String componentId,String user_id,String email_id)
	{
		String sql = "";
		sql = "INSERT INTO emailrecipients(componentId,messageid,contactid,recipientstype,emailstatus,emailrecvid,messagetype) ";
		sql += " VALUES("+componentId+",'"+email_id+"','"+user_id+"','','U',,'SHARE')";
		return sql;
	}
}
