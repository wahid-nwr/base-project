<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
<%@page import="java.util.regex.*"%>
<%@page import="java.util.*"%>
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
	String sql = "";
	String ref = request.getParameter("ref");
	String action = request.getParameter("action");
	String group_id = request.getParameter("group_id");
	String ref_id = request.getParameter("ref_id");
	String email_id = request.getParameter("email_id");
	String com_id = request.getParameter("com_id");
	String next_ref_id = "";
	try
	{
		con = new ConnectDB();
		stmt = con.connect();
		if(action.equals("insert")){
			//System.out.println(" IN INSERT  ------ ");
			sql="SELECT MAX(REF_ID)+1 AS REFID FROM EMAIL_REFERENCE";
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				next_ref_id = rs.getString("REFID");
				}
			sql = "INSERT INTO EMAIL_REFERENCE(REF_ID, REF_DTL, EMAIL_GROUP_ID) VALUES('"+next_ref_id+"','"+ref+"','"+group_id+"')";
			//System.out.println("SQL:"+sql);
			rs = stmt.executeQuery(sql);
			sql="UPDATE EMAIL SET REF_ID = '"+next_ref_id+"',EMAIL_GROUP_ID='"+group_id+"',COM_ID='"+com_id+"'  WHERE ID = '"+email_id+"' " ;
			//System.out.println("SQL:"+sql);
			rs = stmt.executeQuery(sql);
			}
		if(action.equals("update")){
			//System.out.println(" IN UPDATE ");
			sql = "UPDATE EMAIL SET REF_ID = '"+ref_id+"', EMAIL_GROUP_ID='"+group_id+"', COM_ID='"+com_id+"'  WHERE ID = '"+email_id+"' ";
			//System.out.println("SQL:"+sql);
			rs = stmt.executeQuery(sql);
			}
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