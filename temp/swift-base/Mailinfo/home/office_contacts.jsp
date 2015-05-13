<%@page import="com.maestro.crb.orig_billing.info.Inf"%>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
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
<table width="100%" border="0" cellspacing="3" cellpadding="3">
	<%
	ConnectDB connector = null;
		Statement stmt = null;
		long noWaiting = 0;
		try{
			connector = new ConnectDB();
			stmt = connector.connect();
			String adminId = (String)session.getAttribute("adminid");
			long level;
			String padding;
			ResultSet rs = stmt.executeQuery("SELECT id, name, level FROM ADMIN CONNECT BY PRIOR id = parent START WITH parent = -1");
			while(rs.next()){
				padding = "";
				level = rs.getLong("level");
				for(int i=0;i<level;i++)
				padding += "&nbsp;&nbsp;&nbsp;";
	%>
				<tr><td class="m_t2">
				<%=padding %> 
				<a href="emailActions.jsp?id=<%=id %>&user=<%=rs.getString("name") %>&usercat=Company user">
				<%=rs.getString("name") %></a>
				</td></tr>
	<%
		}
	%>
	</table>
	<%	
	 
		}catch(Exception e){
	%>
		<span style="color: red;font-weight: bold;">Error Occurred: <%=e.getMessage() %></span>
	<%			
		}finally{
			try{
				stmt.close();
			}catch(Exception e){}
			try{
				connector.close();
			}catch(Exception e){}
		}
	%>