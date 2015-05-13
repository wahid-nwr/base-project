<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>

<%@page import="com.maestro.crb.orig_billing.info.Inf"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Clob"%>
<%@page import="java.io.Reader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.maestro.rateserver.util.CheckMailSingleUser"%>
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
	//System.out.println("user_id:"+user_id);
	ConnectDB con = null;
	java.sql.Statement stmt = null;
	ResultSet rs = null;
	try
	{
		con = new ConnectDB();
		stmt = con.connect();
		CheckMailSingleUser checkMailSingleUser = new CheckMailSingleUser();
		checkMailSingleUser.receiveEmails(stmt,request.getRealPath("/email_new/attachments"),user_id);
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
		//response.sendRedirect("emailUnchecked.jsp?id="+id);
	}
%>