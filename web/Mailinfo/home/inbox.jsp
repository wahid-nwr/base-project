<%@page import="com.swiftcorp.portal.mailinfo.util.StringUtil"%>
<%@ page contentType="text/javascript; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.swiftcorp.portal.mailinfo.util.ConnectDB"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.MailSqlGenerator"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
<%
	String id = request.getParameter("id");
	String uId = session.getId();
	String user_id = (String)session.getAttribute("user_id");
	String user_name = (String)session.getAttribute("user_name");
	user_id = "1";
	System.out.println("id::::"+id);
	if(id == null ||(id != null && id.equals(uId) == false))
		{
		//out.println("timeOut");
		//return;
		}	
%>
<%
	ConnectDB con = null;
	java.sql.Statement stmt = null;
	ResultSet rs = null;
	String sql = "";
	String email_id = "";
	String sender = "";
	String recipients = "";
	String cc = "";
	String send_date = "";
	String subject = "";
	String status = "";
	String output = "";
	String senderName = "";
	String senderEmail = "";
	String str_start = request.getParameter("start");
	String str_limit = request.getParameter("limit");
	String recv_id = request.getParameter("recv_id");
	String com_id = request.getParameter("com_id");
	String email_group_id = request.getParameter("email_group_id");
	String email_address = request.getParameter("email_address");
	String type = request.getParameter("type");
	
	//System.out.println("type:"+type);
	int qStart = Integer.parseInt(str_start);
	int limit = Integer.parseInt(str_limit);
	String tmpQry = "";
	String comEmail = "";
	String email = "";
	int qEnd = qStart+limit;
	
	System.out.println("recv_id::"+recv_id);
	try
	{
		con = new ConnectDB();
		stmt = con.connect();

		/**
		*	Return draft  messages
		*/
		if(type!=null && !type.equals("null") && type.equals("sent") && type.length()>0 ){
			sql = "";
			/* 
			sql = "SELECT  EMAIL.ID,EMAIL.SENDER,EMAIL.RECIPIENT,EMAIL.CC,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE,"; 
			sql += " EMAIL.SUBJECT,RECIPIENTS.STATUS";
			sql += " FROM EMAIL,RECIPIENTS WHERE RECIPIENTS.CONTACT_ID='"+user_id+"' AND RECIPIENTS.MESSAGE_ID = EMAIL.ID";
			sql += " AND RECIPIENTS.MESSAGE_TYPE='SEND_EMAIL'";
			 */
			sql = MailSqlGenerator.getSentMsg(user_id);
		}
		
		/**
		*	Return draft  messages
		*/
		if(type!=null && !type.equals("null") && type.equals("draft") && type.length()>0 ){
			sql = "";
			/* 
			sql = "SELECT  EMAIL.ID,EMAIL.SENDER,EMAIL.RECIPIENT,EMAIL.CC,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE,"; 
			sql += " EMAIL.SUBJECT,RECIPIENTS.STATUS";
			sql += " FROM EMAIL,RECIPIENTS WHERE RECIPIENTS.CONTACT_ID='"+user_id+"' AND RECIPIENTS.MESSAGE_ID = EMAIL.ID";
			sql += " AND RECIPIENTS.MESSAGE_TYPE='DRAFT_EMAIL'";
			 */
			sql = MailSqlGenerator.getDraftMsg(user_id);
		}
		
		/**
		*	Return share  messages
		*/
		if(type!=null && !type.equals("null") && type.equals("share") && type.length()>0 ){
			sql = "";
			/* 
			sql = "SELECT  EMAIL.ID,EMAIL.SENDER,EMAIL.RECIPIENT,EMAIL.CC,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE,"; 
			sql += " EMAIL.SUBJECT,RECIPIENTS.STATUS";
			sql += " FROM EMAIL,RECIPIENTS WHERE RECIPIENTS.CONTACT_ID='"+user_id+"' AND RECIPIENTS.MESSAGE_ID = EMAIL.ID";
			sql += " AND RECIPIENTS.MESSAGE_TYPE='SHARE'";
			 */
			sql = MailSqlGenerator.getShareMsg(user_id);
		}
			
		/**
		*	Return group -> Company  messages
		*/
		if(type!=null && !type.equals("null") && type.equals("company") && type.length()>0 ){
			sql = "";
			/* 
			sql = " SELECT  EMAIL.ID,EMAIL.SENDER,EMAIL.RECIPIENT,EMAIL.CC,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE,";
			sql += " EMAIL.SUBJECT,RECIPIENTS.STATUS ";
			sql += " FROM EMAIL,RECIPIENTS WHERE  RECIPIENTS.CONTACT_ID = '"+user_id+"' AND RECIPIENTS.MESSAGE_ID = EMAIL.ID";
			sql += " AND COM_ID = '"+com_id+"' ORDER BY EMAIL.SEND_DATE DESC";
			 */
			sql = MailSqlGenerator.getCompanyGroupMsg(user_id,com_id);
		}
		
		/**
		*	Return Company -> Person Mails
		*/
		if(type!=null && !type.equals("null") && type.equals("company_person") && type.length()>0 ){
			sql = "";
			/* 
			sql = "SELECT EMAIL.ID,EMAIL.SENDER,EMAIL.RECIPIENT,EMAIL.CC,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE,";
			sql += " EMAIL.SUBJECT,RECIPIENTS.STATUS ";
			sql += " FROM EMAIL,RECIPIENTS WHERE RECIPIENTS.MESSAGE_ID = EMAIL.ID AND RECIPIENTS.CONTACT_ID = '"+user_id+"' ";
			sql += " AND EMAIL.SENDER LIKE '%"+email_address+"%'  AND EMAIL.COM_ID = '"+com_id+"' "; 
    		sql += "  ORDER BY EMAIL.SEND_DATE DESC";
			 */
			sql = MailSqlGenerator.getPersonalMsg(user_id,com_id,email_address);
		}	
				
		/**
		*	Return group messages
		*/
		if(type!=null && !type.equals("null") && type.equals("group") && type.length()>0 ){
	 		sql = "";
	 		/* 
			sql = "SELECT EMAIL.ID,EMAIL.SENDER,EMAIL.RECIPIENT,EMAIL.CC,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE,";
			sql += " EMAIL.SUBJECT,RECIPIENTS.STATUS ";
			sql += " FROM EMAIL,RECIPIENTS WHERE RECIPIENTS.MESSAGE_ID = EMAIL.ID AND RECIPIENTS.CONTACT_ID = '"+user_id+"' ";
			sql += " AND EMAIL.EMAIL_GROUP_ID = '"+email_group_id+"' AND EMAIL.COM_ID = '"+com_id+"' "; 
    		sql += "  ORDER BY EMAIL.SEND_DATE DESC";
			 */
	 		sql = MailSqlGenerator.getGroupMsg(user_id,com_id,email_group_id);
		}
			
		/**
		*	Return all/ one recv_id  messages
		*/	
		if(type!=null && !type.equals("null") && type.equals("mailInbox") && type.length()>0 ){
			sql = "";	
			/*
			sql = " SELECT EMAIL.ID,EMAIL.SENDER,EMAIL.RECIPIENT,EMAIL.CC,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE, "; 
			sql += " EMAIL.SUBJECT,RECIPIENTS.STATUS FROM EMAIL,RECIPIENTS ";
			sql += " WHERE RECIPIENTS.MESSAGE_ID = EMAIL.ID AND RECIPIENTS.CONTACT_ID = '"+user_id+"' ";
			if(recv_id!=null && !recv_id.equals("null") && recv_id.length()>0)
				sql += " AND EMAIL_RECV_ID="+recv_id+""; 
			sql += " ORDER BY EMAIL.SEND_DATE DESC";
			*/
			sql = MailSqlGenerator.getMailInbox(user_id,recv_id);
		}
			
		/*
		SELECT * FROM (
		SELECT A.*,ROWNUM R FROM (
		SELECT EMAIL.ID,EMAIL.SENDER,EMAIL.RECIPIENT,EMAIL.CC,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE,  
			EMAIL.SUBJECT,RECIPIENTS.STATUS 
		FROM EMAIL,RECIPIENTS  
		WHERE RECIPIENTS.MESSAGE_ID = EMAIL.ID 
		AND RECIPIENTS.CONTACT_ID = '3285'  
		AND EMAIL_RECV_ID=10 
		ORDER BY EMAIL.SEND_DATE DESC
		)A 
		)  WHERE R BETWEEN 5 AND 20
		*/	
		System.out.println("TYPE:"+type+" - SQL:"+sql);	
		rs = stmt.executeQuery(sql);
		int count = 1;
		int total = 0;
		int x = 0;
		while(rs.next()){
			email_id = rs.getString("componentId");
			sender = rs.getString("sender");
			sender = StringUtil.removeEscapeChar(sender);
			recipients = rs.getString("recipient");
			//recipients = recipients.replace("'","");
			//recipients = recipients.replace("\"","\\\"");
			recipients = StringUtil.removeEscapeChar(recipients);
			cc = rs.getString("cc");
			System.out.println("cc::"+cc);
			cc = StringUtil.removeEscapeChar(cc);
						
			System.out.println("cc::"+cc);
			send_date = rs.getString("senddate");
			send_date = send_date.substring(0,send_date.length()-2);
			//System.out.println("send_date::"+send_date);
			subject = rs.getString("subject");
			status = rs.getString("emailstatus");
			
			subject = StringUtil.removeEscapeChar(subject);			
			
			
			if(recipients!=null && !recipients.equals("null") && recipients.length()>0 ){
				recipients = recipients.replaceAll("<", "(");
				recipients = recipients.replaceAll(">", ")");
				recipients = recipients.replaceAll(",", " ; ");
				//out.print(recipients+"<br>");
				}
			
			
			if(cc!=null && !cc.equals("null") && cc.length()>0 ){
					cc = cc.replaceAll("<", "(");
					cc = cc.replaceAll(">", ")");
				}
			
			
			int start = sender.indexOf("<");
			if(start > 0 )
			{
				int end = sender.indexOf(">");			
				if(start > 0){
					senderName = sender.substring(0, start);
					senderName = senderName.trim();
					}
				if(start > 0 && end > 0){
					senderEmail = sender.substring(start+1, end); 
					senderEmail = senderEmail.trim();
					}
				if(senderName==null && senderName.equals("null") && senderName.length()<0){
					senderName = senderEmail;
					}
				}
			else
				{
				senderName = sender;
				senderEmail = sender;
				}

			if( count >= qStart && count<= qEnd){
				if(output!=null && !output.equals("null") && output.length()>0)
				{
					output+=",";
				}
				output += "{";
				output += "\"email_id\":\""+email_id+"\",";
				output += "\"senderName\":\""+senderName+"\",";
				output += "\"senderEmail\":\""+senderEmail+"\",";
				output += "\"recipients\":\""+recipients+"\",";
				output += "\"cc\":\""+cc+"\",";
				output += "\"subject\":\""+subject+"\",";
				output += "\"send_date\":\""+send_date+"\",";
				output += "\"status\":\""+status+"\" "; 
				output += "}";
				}
			count++; total++;
			}
			/* if(output!=null && !output.equals("null") && output.length()>0)
				output = output.substring(0,output.length()-1); */
			output += "]}";	
			String initial = "{\"totalCount\":\""+total+"\",\"topics\":[";
			String mainoutput = initial+output;
			out.println(mainoutput);			
	}
	catch(Exception e)
	{
		%><p>&nbsp;</p><p><font color=red><b><%= e.getMessage() %></b></font> <%
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