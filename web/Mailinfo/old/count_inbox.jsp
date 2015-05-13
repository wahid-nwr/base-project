<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
<%@page import = "java.util.*"%>
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
	String sub_id = "";
	String sql = "";
	String email = "";
	String display_name = "";
	String total = "";
	String display_from_contacts = "";
	try
	{
		con = new ConnectDB();
		stmt = con.connect();
		sql = " SELECT ID,COMPANY_EMAIL_ADDRESS, DISPLAY_NAME FROM EMAIL_RECV  WHERE TAGGED_USER_ID = '"+user_id+"' ";
		////System.out.println("sql"+sql);
		rs = stmt.executeQuery(sql);
		Vector vt = new Vector();
		String str[] = null;
		while(rs.next()){
			sub_id = rs.getString("ID");
			email = rs.getString("COMPANY_EMAIL_ADDRESS");
			display_name = rs.getString("DISPLAY_NAME");
			vt.add(new String [] {sub_id, email,display_name});	
		//	display_from_contacts += "["+sub_id+", '"+display_name+"&lt;"+email+"&gt;'],";	
			}
		
		//if(display_from_contacts!=null && !display_from_contacts.equals("null") && display_from_contacts.length()>0)
		//		display_from_contacts = display_from_contacts.substring(0,display_from_contacts.length()-1);
		//session.setAttribute("display_from_contacts",display_from_contacts);
					 	
		%><table><%	
		for(int i=0;i<vt.size();i++){
			str = (String [])vt.get(i);
			sub_id = str[0];
			email = str[1];
			display_name = str[2];
			sql = "SELECT COUNT(*) AS TOTAL  FROM  RECIPIENTS WHERE EMAIL_RECV_ID = "+sub_id+" AND STATUS = 'U' ";
			////System.out.println("QRY:"+sql);
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				total = rs.getString("TOTAL");
				}
				//show_inbox(recv_id,tab_title,com_id,email_group_id,email_address,type,recv_email)
			%>
			<tr><td id="nav_menu2" onClick="show_inbox('<%=sub_id%>','<%=display_name%>','','','<%=email%>','mailInbox');" class="inbox_group" >
				<A href="#" onClick="return false;" title="<%=email%>" > <%=display_name%> (<%=total%>)</A></td></tr>
			<%	
			}
			%>			
		</table>
			<%
			//System.out.println("display_from_contacts - Count Inbox"+display_from_contacts);
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