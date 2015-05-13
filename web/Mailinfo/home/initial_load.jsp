<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.ConnectDB"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.MailSqlGenerator"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
<%@page import = "java.util.*"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.sql.Clob"%>
<%
	
	String id = request.getParameter("id");
	String uId = session.getId();
	String user_id = (String)session.getAttribute("user_id");
	String user_name = (String)session.getAttribute("user_name");
	System.out.println("id::::"+id);
	if(id == null ||(id != null && id.equals(uId) == false))
		{
		System.out.println("timeOut");
		//return;
		}
	user_id = "1";
%>	
<%
	ConnectDB con = null;
	java.sql.Statement stmt = null;
	ResultSet rs = null;
	String sub_id = "";
	String sql = "";
	String email = "";
	String display_name = "";
	String total = "";
	String display_from_contacts = "";
	String qry2 = "";
	String comId = "";
	String comName = "";
	String comEmail ="";
	String con_name = "";
	String total_detail = "";
	String totalMail = "";
	String group_name = "";
	String group_id = "";
	int total_mail = 0;
	int gr_total_mail = 0;
	int com_gr_total_mail = 0;
	String tmp = "";
	String tmp_str = "";
	Clob mess_clob_comment=null;
	try
	{
		con = new ConnectDB();
		stmt = con.connect();
		Vector vt = new Vector();
		
		JSONArray arr2 = new JSONArray();
		JSONArray arr3 = new JSONArray();
		JSONArray arr4 = new JSONArray();
		JSONObject mailObj_group = new JSONObject();
		JSONObject mailObj = new JSONObject();
		
		// SHARED MESSAGES
		sql = MailSqlGenerator.getTotalEmailRecipients(user_id);
		//sql = "SELECT COUNT(*) AS TOTAL FROM RECIPIENTS WHERE CONTACT_ID='"+user_id+"' AND MESSAGE_TYPE='SHARE' AND STATUS='U'" ;
		rs = stmt.executeQuery(sql);
		if(rs.next())
			total = rs.getString("TOTAL");
			mailObj = new JSONObject();			
			mailObj.put("total", total);
			JSONObject mails = new JSONObject();	
			mails.put("share_mail", mailObj);
						      	
		// MAIL IN BOX
		JSONArray mail_dtl = new JSONArray();	
		sql = MailSqlGenerator.getEmailDtlFromEmailRecv(user_id);
		/* 
		sql = " SELECT ID,COMPANY_EMAIL_ADDRESS, DISPLAY_NAME ,(SELECT COUNT(*) AS TOTAL  FROM  RECIPIENTS"+ 
			  "	WHERE EMAIL_RECV_ID = EMAIL_RECV.ID AND STATUS = 'U')AS TOTAL "+
			  " FROM EMAIL_RECV  WHERE TAGGED_USER_ID ='"+user_id+"' "; 
		*/
		//System.out.println("SQL:"+sql);
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			sub_id = rs.getString("componentId");
			email = rs.getString("companyemailaddress");
			display_name = rs.getString("displayname");
			total = rs.getString("total");			
			mailObj = new JSONObject();			
			mailObj.put("sub_id", sub_id);
			mailObj.put("email", email);
			mailObj.put("display_name", display_name);
			mailObj.put("total", total);
			mail_dtl.put(mailObj);						
			}				
		mails.put("personal_mail", mail_dtl);
					
		//COMPANY INBOX
		sql = MailSqlGenerator.getCompanyInbox();
		/*
		sql = "SELECT DISTINCT ID,COMPANY_NAME,EMAIL FROM USERS WHERE COMPANY_NAME IS NOT NULL AND EMAIL IS NOT NULL ORDER BY COMPANY_NAME";
		*/
		rs = stmt.executeQuery(sql);
		String str[] = null;
		String str2[] = null;
		String tmpQry = "";
		while(rs.next()){
				comId = rs.getString("componentId");
				comName = rs.getString("companyname");
				comEmail = rs.getString("email");
				vt.add(new String [] {comId, comName,comEmail});	
			}
	
		for(int i=0;i<vt.size();i++){
			str = (String [])vt.get(i);
			comId = str[0];
			comName = str[1];
			comEmail = str[2];
			
			sql = "";
			sql = MailSqlGenerator.getCompanyInbox(user_id,comId);
			/* 
			sql += "SELECT COUNT(*) AS TOTAL, (SELECT GROUP_NAME FROM EMAIL_GROUP WHERE EMAIL_GROUP_ID = EMAIL.EMAIL_GROUP_ID) AS GROUP_NAME, ";
			sql += " EMAIL.EMAIL_GROUP_ID FROM EMAIL,RECIPIENTS WHERE RECIPIENTS.MESSAGE_ID = EMAIL.ID AND RECIPIENTS.STATUS = 'U'";
			sql += " AND RECIPIENTS.CONTACT_ID='"+user_id+"' AND EMAIL.COM_ID='"+comId+"' GROUP BY EMAIL.EMAIL_GROUP_ID ";
			 */
			rs = stmt.executeQuery(sql);
			arr2 = new JSONArray();			
			while(rs.next()){
				total = rs.getString("total");
				group_name = rs.getString("groupname");
				group_id = rs.getString("emailgroupid");
				if(!total.equals("null") && total.length()>0 && !total.equals("null")) {
					if(!group_name.equals("null") && group_name.length()>0 && !group_name.equals("null"))
						total_mail = Integer.parseInt(total);
						gr_total_mail = gr_total_mail + total_mail;
				 	}					
				//CHILD				
				if(gr_total_mail > 0){					
					mailObj_group = new JSONObject();	
					mailObj_group.put("text", group_name+'('+total_mail+')');
					mailObj_group.put("id", comId+"___"+group_id);
					mailObj_group.put("comId", comId);
					mailObj_group.put("comName", comName);
					mailObj_group.put("group_id", group_id);
					mailObj_group.put("group_name", group_name);					
					mailObj_group.put("type", "group");
					arr2.put(mailObj_group);
					}								
				}
						
			//NODE
			if(gr_total_mail > 0){
				mailObj = new JSONObject();							
				mailObj.put("text", comName+'('+gr_total_mail+')');
				mailObj.put("id", comId);
				mailObj.put("comId", comId);
				mailObj.put("comName", comName);
				mailObj.put("type", "company");
				mailObj.put("children", arr2);
				arr4.put(mailObj);
				}
				
			// ROOT NODE - COMPANY UNREAD 
			mails.put("company_unread", arr4);
			gr_total_mail = 0;

			tmp_str = "";
			sql = MailSqlGenerator.getCompanyUnread(comId);
			/* 
			sql = " SELECT COUNT(*) AS TOTAL, (SELECT GROUP_NAME FROM EMAIL_GROUP WHERE EMAIL_GROUP_ID = EMAIL.EMAIL_GROUP_ID) AS GROUP_NAME,";
			sql += " EMAIL.EMAIL_GROUP_ID FROM EMAIL WHERE EMAIL.COM_ID = '"+comId+"' GROUP BY EMAIL.EMAIL_GROUP_ID";
			 */
			rs = stmt.executeQuery(sql);
			
			arr2 = new JSONArray();	
			while(rs.next()){
				total = rs.getString("total");
				group_name = rs.getString("groupname");
				group_id = rs.getString("emailgroupid");
				if(!total.equals("null") && total.length()>0 && !total.equals("null")) {
					if(!group_name.equals("null") && group_name.length()>0 && !group_name.equals("null")){
						total_mail = Integer.parseInt(total);
						gr_total_mail = gr_total_mail + total_mail;
					}
				 }
				//CHILD				
				if(gr_total_mail > 0){					
					mailObj_group = new JSONObject();
					mailObj_group.put("id", comId+"___"+group_id);	
					mailObj_group.put("text", group_name);
					mailObj_group.put("comId", comId);
					mailObj_group.put("comName", comName);
					mailObj_group.put("group_id", group_id);
					mailObj_group.put("group_name", group_name);					
					mailObj_group.put("type", "group");
					arr2.put(mailObj_group);
					}	
			}
			//NODE
			if(gr_total_mail > 0){
				mailObj = new JSONObject();							
				mailObj.put("text", comName);
				mailObj.put("id", comId);
				mailObj.put("comId", comId);
				mailObj.put("comName", comName);
				mailObj.put("type", "company");
				mailObj.put("children", arr2);
				arr3.put(mailObj);
				}
			mails.put("company_all", arr3);
			gr_total_mail = 0;	

		}
		// END OF vt FOR LOOP
		
		//INTERNAL MESSAGES
		String mess_id = "";
		String mess_comment = "";
		String mess_msgId = "";
		String mess_ownerId = "";
		String mess_creat_time = "";
		String mess_refId = "";
		String mess_type = "";
		String mess_name = "";
		Vector vt_mess = new Vector();
		Vector vt_mess2 = new Vector();
		
		sql = "";
		sql = MailSqlGenerator.getCompanyUnread();
		System.out.println("sql::"+sql);
		/* 
		sql += "SELECT oc.ID,COMMENTS,( select max(id) from MSG_DETAIL where msg_id=oc.id) msgId, OWNER_ID,CREATION_TIME,REF_ID,TYPE, ";
		sql += " (first_name||' '||last_name) as NAME FROM COMMENTS oc,OMS_USERS@OMS_LINK ou where oc.OWNER_ID=ou.ID "; 
 		sql += " CONNECT BY PRIOR oc.ID=REF_ID START WITH REF_ID=0 order siblings by creation_time desc";
 		 */
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			mess_id = rs.getString("componentId");
			mess_clob_comment=rs.getClob("comments");
			if(mess_clob_comment!=null)
				mess_comment=mess_clob_comment.getSubString(1,(int)mess_clob_comment.length());		
			mess_msgId = rs.getString("msgid");
			mess_ownerId = rs.getString("ownerid");
			mess_creat_time = rs.getString("creationtime");
			mess_refId = rs.getString("refid");
			mess_type = rs.getString("type");
			mess_name = rs.getString("name");
			vt_mess.add(new String [] {mess_id, mess_comment,mess_msgId,mess_ownerId,mess_creat_time,mess_refId,mess_type,mess_name});	
			vt_mess2.add(new String [] {mess_id, mess_comment,mess_msgId,mess_ownerId,mess_creat_time,mess_refId,mess_type,mess_name});		
			}
		int ref_int=1;
		int ref_int2=1;
		int ref_int_id=0;
		arr3 = new JSONArray();			
		for(int i=0;i<vt_mess.size();i++){
			str = (String [])vt_mess.get(i);			
			ref_int = Integer.parseInt(str[5]);
			ref_int_id = Integer.parseInt(str[0]);						
			if(ref_int == 0){
				mailObj_group = new JSONObject();
				mailObj_group.put("mess_id", str[0]);
				mailObj_group.put("mess_comment", str[1]);
				mailObj_group.put("mess_msgId", str[2]);
				mailObj_group.put("mess_ownerId", str[3]);
				mailObj_group.put("mess_creat_time", str[4]);
				mailObj_group.put("mess_refId", str[5]);
				mailObj_group.put("mess_type", str[6]);
				mailObj_group.put("mess_name", str[7]);
				arr2 = new JSONArray();	
				for(int j=0;j<vt_mess2.size();j++){
					str2 = (String [])vt_mess2.get(j);
					ref_int2 = Integer.parseInt(str2[5]);				
					if(ref_int_id == ref_int2 ){
						mailObj = new JSONObject();							
						mailObj.put("mess_id", str2[0]);
						mailObj.put("mess_comment", str2[1]);
						mailObj.put("mess_msgId", str2[2]);
						mailObj.put("mess_ownerId", str2[3]);
						mailObj.put("mess_creat_time", str2[4]);
						mailObj.put("mess_refId", str2[5]);
						mailObj.put("mess_type", str2[6]);
						mailObj.put("mess_name", str2[7]);
						arr2.put(mailObj);	
						}
					}
				mailObj_group.put("children", arr2);
				arr3.put(mailObj_group);
				}		
			}//END OF vt_mess LOOP
		mails.put("messages", arr3);
		out.print(mails);
	}
	catch(Exception e)
	{
		%><p>&nbsp;</p><p><font color=red><b><%=e.getMessage()%></b></font> <%
		e.printStackTrace();
	}
	finally
	{
		try{
			stmt.close();
		}catch(Exception e){}
		try
		{
			con.close();
		}
		catch(Exception exp){exp.printStackTrace();}
	}
%>