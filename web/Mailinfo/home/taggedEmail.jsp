
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%><html>
	<head>
		<title>Addd Email</title>
		<link href="../styles/maestro.css" rel=stylesheet>
	</head>
	<body>
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
	<table align="center" cellspacing="1" width="60%">
	<%
	//out.print(request.getRealPath("/email/"));
	ConnectDB dbcon = null;
	Statement stmt = null;
	try{
		dbcon = new ConnectDB();
		stmt = dbcon.connect(false);
		ResultSet rs = stmt.executeQuery("SELECT company_email_address,pop_host,pop_port,is_ssl FROM email_recv where tagged_user_id = '"+user_id+"' ");
		String email;
		String host;
		String port;
		String ssl;
		while(rs.next()){
			email = rs.getString("company_email_address");
			host = rs.getString("pop_host");
			port = rs.getString("pop_port");
			ssl = rs.getString("is_ssl");
			if(ssl.equals("Y"))
				ssl = "Yes";
			else
				ssl = "No";
		%>
			<tr>							
			<td class="m_t2"><%=email %></td>
			</tr>
	<%
		}
	%>
	</table>
	<%	
	}catch(Exception e){
	}finally{
		try{
			stmt.close();
		}catch(Exception e){}
		try{
			dbcon.close();
		}catch(Exception e){}
	}
%>

	</table>
	</body>
</html>