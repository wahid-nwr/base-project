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
<%
	String com_id = request.getParameter("com_id");
	ConnectDB connector = null;
	Statement stmt = null;
	long noWaiting = 0;
	String qry = "";
	try{
		connector = new ConnectDB();
		stmt = connector.connect();
		if(com_id!=null && !com_id.equals("null") && com_id.length()>0){
			qry = "SELECT CONTACT_NAME,EMAIL,(SELECT GROUP_NAME FROM EMAIL_GROUP WHERE EMAIL_GROUP_ID = CONTACT.EMAIL_GROUP_ID) AS EMAIL_GROUP ";
			qry += "FROM CONTACT WHERE USER_ID = "+com_id+" ";
			ResultSet rs = stmt.executeQuery(qry);
			//System.out.println("sql"+qry);	
			%><table width="100%" border="0" cellspacing="0" cellpadding="0"><%
			while(rs.next()){
				%>
				<tr><td class="m_t2" colspan="2"><a href="" onclick="" title="<%=rs.getString("EMAIL") %>">
					<%=rs.getString("CONTACT_NAME") %> (<%=rs.getString("EMAIL_GROUP") %>) </a>&nbsp;</td></tr>
				<%
			}
			%> </table><%
			}
		else{
			qry = "SELECT DISTINCT ID,COMPANY_NAME,EMAIL FROM USERS WHERE COMPANY_NAME IS NOT NULL AND EMAIL IS NOT NULL ORDER BY COMPANY_NAME";
			//System.out.println("sql"+qry);
			ResultSet rs = stmt.executeQuery(qry);
			%><table width="100%" border="0" cellspacing="0" cellpadding="0"><%
			while(rs.next()){
				%>
				<tr><td class="m_t2" colspan="2">
					<a href="" onclick="check_com_other_mail('<%=rs.getString("ID")%>');return false;" title="<%=rs.getString("EMAIL") %>">
					<%=rs.getString("COMPANY_NAME") %></a>&nbsp;</td></tr>
				<tr><td></td><td class="m_t2"><div id="com_<%=rs.getString("ID")%>"></div></td></tr>	
				<%} 
			%></table><%
		}	
	 
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
