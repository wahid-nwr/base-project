<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.swiftcorp.portal.mailinfo.util.ConnectDB"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.MailSqlGenerator"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
<%@page import="java.util.regex.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Clob"%>
<%
	String id = request.getParameter("id");
	String uId = session.getId();
	String user_id = (String)session.getAttribute("user_id");
	String user_name = (String)session.getAttribute("user_name");
	
	
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
	String total_id = request.getParameter("total_id");
	try
	{
		con = new ConnectDB();
		stmt = con.connect();
			String sql = "";
			//sql = "UPDATE RECIPIENTS SET STATUS='I' WHERE MESSAGE_ID IN ("+total_id+")";
			sql = MailSqlGenerator.getMarkAsRead(total_id);
			System.out.println("SQL:"+sql);
			stmt.executeUpdate(sql); 
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