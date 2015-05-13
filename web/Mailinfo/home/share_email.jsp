<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.MailSqlGenerator"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.ConnectDB"%>
<%@page import="java.sql.Statement"%>

	<%
	String id = request.getParameter("id");
	String uId = session.getId();
	String user_id = (String)session.getAttribute("user_id");
	user_id = "1";
	String user_name = (String)session.getAttribute("user_name");
	
	System.out.println("id::::"+id);
	if(id == null ||(id != null && id.equals(uId) == false))
		{
		//out.println("timeOut");
		//return;
		}	
	%>
	<%
	String oms_id = request.getParameter("oms_id");
	String email_id = request.getParameter("email_id");
	String sql = "";	
	ConnectDB dbcon = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String email_recipient_id = "";
	try{
		dbcon = new ConnectDB();
		stmt = dbcon.connect(false);
		sql = "INSERT INTO RECIPIENTS(ID,MESSAGE_ID,CONTACT_ID,RECIPIENTS_TYPE,STATUS,EMAIL_RECV_ID,MESSAGE_TYPE) ";
		sql += " VALUES(RECIPIENTS_SQ.NEXTVAL,'"+email_id+"','"+oms_id+"','','U','','SHARE')";
		sql = MailSqlGenerator.getMaxComponentIdShareMsg(user_id, email_id);
		rs = stmt.executeQuery(sql);
		if (rs.next())
			email_recipient_id = rs.getString("ROW_ID");
		sql = MailSqlGenerator.getInsertShareMsg(email_recipient_id, user_id, email_id);
		System.out.println("SQL:"+sql);
		stmt.executeUpdate(sql);
		
		stmt.getConnection().commit();	
		out.print("{success:true}");
		//System.out.println("OK.. Done");	
	}catch(Exception e){
		String msg = "<span style=\"color:red;font-weight:bold;\">Error Occured: "+e.getMessage()+"</span>";
		out.print(msg);
		stmt.getConnection().rollback();
	}finally{
		try{
			stmt.close();
		}catch(Exception e){}
		try{
			dbcon.close();
		}catch(Exception e){}
	}
%>