<%@page import="com.maestro.rateserver.util.SimpleCipher"%>
<%@page import="com.maestro.rateserver.util.Info"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
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
	String oms_id = request.getParameter("oms_id");
	String email_id = request.getParameter("email_id");
	String sql = "";	
	ConnectDB dbcon = null;
	Statement stmt = null;
	try{
		dbcon = new ConnectDB();
		stmt = dbcon.connect(false);
		sql = "INSERT INTO RECIPIENTS(ID,MESSAGE_ID,CONTACT_ID,RECIPIENTS_TYPE,STATUS,EMAIL_RECV_ID,MESSAGE_TYPE) ";
		sql += " VALUES(RECIPIENTS_SQ.NEXTVAL,'"+email_id+"','"+oms_id+"','','U','','SHARE')";
		//System.out.println("SQL:"+sql);
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