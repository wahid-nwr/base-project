<%@page import="com.maestro.rateserver.util.SimpleCipher"%>
<%@page import="com.maestro.rateserver.util.Info"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="oracle.sql.CLOB"%>
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
	String cid = request.getParameter("cid");
	String comment = request.getParameter("comment");

	String msg="";
	String sql = "";	
	
	ConnectDB dbcon = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	try{
		dbcon = new ConnectDB();
		stmt = dbcon.connect(false);
		sql += "INSERT INTO COMMENTS (ID,OWNER_ID,CREATION_TIME,REF_ID,TYPE,ROLE,GROUP_ID,COMMENTS,TASK_ID) ";
		sql += " values (COMMENTS_SQ.NEXTVAL,"+user_id+",SYSDATE,"+cid+",'POLICY','1','1',?,'')";
		System.out.println("SQL:"+sql);
		
		pstmt = stmt.getConnection().prepareStatement(sql);					
		CLOB commClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
		commClob.putString(1,comment);
		pstmt.setClob(1,commClob);
		pstmt.execute();

		//stmt.executeUpdate(sql);
		//stmt.getConnection().commit();	
		out.print("{success:true}");	
	}catch(Exception e){
		msg = "<span style=\"color:red;font-weight:bold;\">Error Occured: "+e.getMessage()+"</span>";
		out.print("{success:false}");
	}finally{
		try{
			stmt.close();
		}catch(Exception e){}
		try{
			dbcon.close();
		}catch(Exception e){}
	}
%>