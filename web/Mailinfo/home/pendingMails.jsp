<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
<%
	String id = request.getParameter("id");
	String uId = session.getId();
	String user_id = (String)session.getAttribute("user_id");
	String user_name = (String)session.getAttribute("user_name");
	
	System.out.println("id::::"+id);
	if(id == null ||(id != null && id.equals(uId) == false))
		{
		out.println("timeOut");
		return;
		}	
%>
<%
	ConnectDB con = null;
	java.sql.Statement stmt = null;
	ResultSet rs = null;
	String total = "";
	String sql = "";
	String object_id = "";
	String sender = "";
	String senderName = "";
	String senderEmail = "";
	String schedule_time = "";
	String send_status = "";
	String sub = "";
	String output = "";
	String recipient = "";

	try
	{
		con = new ConnectDB();
		stmt = con.connect();

		sql = "SELECT COUNT(*)AS TOTAL FROM NOTIFICATION_OUTBOX WHERE SEND_STATUS='pending'";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			total = rs.getString("TOTAL");
			}
		output = "{\"totalCount\":\""+total+"\",\"topics\":[";	
		sql = "SELECT EMAIL.ID,EMAIL.SENDER,EMAIL.RECIPIENT,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE, EMAIL.SUBJECT, OUTBOX.SEND_STATUS"; 
		sql += " FROM EMAIL, NOTIFICATION_OUTBOX OUTBOX WHERE EMAIL.ID = OUTBOX.OBJECT_ID AND OUTBOX.SEND_STATUS = 'pending'"; 
		//System.out.println("sql"+sql);
		rs = stmt.executeQuery(sql);
		int count = 1;
		while(rs.next()){
			object_id = rs.getString("ID");
			sender = rs.getString("SENDER");
			recipient = rs.getString("RECIPIENT");
			schedule_time = rs.getString("SEND_DATE");
			sub = rs.getString("SUBJECT");
			send_status = rs.getString("SEND_STATUS");
			
			sender = sender.replace("\"", "");
			sub = sub.replace("\'", "");
			int start = sender.indexOf("<");
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
			//System.out.println("Sender:"+senderName);
			//System.out.println("Subject:"+sub);								

			output += "{";
			output += "\"object_id\":\""+object_id+"\",";
			output += "\"senderName\":\""+senderName+"\",";
			output += "\"senderEmail\":\""+senderEmail+"\",";
			output += "\"recipient\":\""+recipient+"\",";
			output += "\"schedule_time\":\""+schedule_time+"\",";
			output += "\"subject\":\""+sub+"\","; 
			output += "\"send_status\":\""+send_status+"\"";
			if(count == Integer.parseInt(total))
				output += "}";
			else 
				output += "},";
			count++;
			}
			output += "]}";
			out.print(output);
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