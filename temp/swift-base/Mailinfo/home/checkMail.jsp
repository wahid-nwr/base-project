<%@page import="org.json.simple.JSONObject"%>
<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.swiftcorp.portal.mailinfo.util.ConnectDB"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.MailSqlGenerator"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.EmailFetcher"%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Clob"%>
<%@page import="java.io.Reader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.CheckMailSingleUser"%>
<%
	
	JSONObject mailObj = new JSONObject();
	String id = request.getParameter("id");
	String uId = session.getId();
	String user_id = (String)session.getAttribute("user_id");
	String user_name = (String)session.getAttribute("user_name");
	user_id = "1";
	System.out.println("id::::"+id);
	if(id == null ||(id != null && id.equals(uId) == false))
		{
		System.out.println("timeOut");
		//return;
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
		/* EmailFetcher emailFetcher = new EmailFetcher();
		emailFetcher.receiveEmails(stmt,request.getRealPath("/swift_erp/attachments")); */
		CheckMailSingleUser checkMailSingleUser = new CheckMailSingleUser();
		checkMailSingleUser.receiveEmails(stmt,request.getRealPath("/attachments"),user_id);
		mailObj.put("success",true);
		out.println(mailObj);
		System.out.println("done");
		%>
		<script type="text/javascript">
		alert('loading');
		var showMask = document.getElementById('checkMailDiv');
		var classname = showMask.className;
		classname = classname.replace('show','hidden');
		document.getElementById('checkMailDiv').className = classname;
		alert('loading finished');
		</script>
		<%
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