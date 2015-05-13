<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
<%@page import="java.util.regex.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Clob"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
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
	
	String com_id = "";
	String gr_id = "";
	String node = request.getParameter("node");
	int parm_start = node.indexOf("___");
	if(parm_start > 0){
		com_id = node.substring(0, parm_start);
		com_id = com_id.trim();
		
		gr_id = node.substring(parm_start, node.length());
		gr_id = gr_id.replace("___", "");
		gr_id = gr_id.trim();
		}
	ResultSet rs = null;
	String total = "";
	String sql = "";
	String sender = "";
	String senderName = "";
	String senderEmail = "";
	
	//JSONArray arr = new JSONArray();
	JSONObject mailObj = new JSONObject();
	JSONObject mailObj2 = new JSONObject();
	
	try
	{
		con = new ConnectDB();
		stmt = con.connect();		
		sql = " SELECT COUNT(*) AS TOTAL,EMAIL.SENDER FROM EMAIL WHERE EMAIL.COM_ID = '"+com_id+"' ";
		sql += " AND EMAIL_GROUP_ID = '"+gr_id+"' GROUP BY EMAIL.SENDER " ;
		System.out.println("SQL:"+sql);
		rs = stmt.executeQuery(sql);
		JSONArray arr = new JSONArray();
		while(rs.next()){
			if(rs.getString("TOTAL")!=null) total = rs.getString("TOTAL");
			if(rs.getString("SENDER")!=null) sender = rs.getString("SENDER");
				sender = sender.replace("\"", "");
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
				mailObj = new JSONObject();
				mailObj.put("text", senderEmail);
				mailObj.put("leaf", true);				
				mailObj.put("comId", com_id);					
				mailObj.put("type", "company_person");
				arr.put(mailObj);			
			}
			//mailObj2 = new JSONObject();
			//mailObj2.put("mail_address", arr);
			out.print(arr);
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