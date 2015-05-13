<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
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
		out.println("timeOut");
		return;
		}	
%>
<%
	ConnectDB con = null;
	java.sql.Statement stmt = null;
	String com_id = request.getParameter("com_id");
	String gr_id = request.getParameter("gr_id");
	ResultSet rs = null;
	String total = "";
	String sql = "";
	String sender = "";
	String senderName = "";
	String senderEmail = "";
	try
	{
		con = new ConnectDB();
		stmt = con.connect();		
		sql = " SELECT COUNT(*) AS TOTAL,EMAIL.SENDER FROM EMAIL WHERE EMAIL.COM_ID = '"+com_id+"' ";
		sql += " AND EMAIL_GROUP_ID = '"+gr_id+"' GROUP BY EMAIL.SENDER " ;
		//System.out.println("SQL:"+sql);
		rs = stmt.executeQuery(sql);
		%><table width="100%" border="0" cellspacing="0" cellpadding="0"><%
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

			%><tr><td>&nbsp;</td><td class="m_t">
			<a href="#" title=<%=senderEmail%>" onclick="show_inbox('','tab_title','<%=com_id%>','<%=gr_id%>','<%=senderEmail%>','company'); return false;" >
				<%=senderName%>(<%=total%>)</a>
			</td></tr><%
			}
		%></table><%	
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