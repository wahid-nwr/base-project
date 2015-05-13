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
	String email = request.getParameter("email");
	String host = request.getParameter("host");
	String port = request.getParameter("port");
	String smtp_host = request.getParameter("smtp_host");
	String smtp_port = request.getParameter("smtp_port");
	String password = request.getParameter("password");
	SimpleCipher cipher = new SimpleCipher(Info.CYPHER_KEY);
	password = cipher.encrypt(password);
	//password = cipher.decrypt(password);
	String ssl = request.getParameter("ssl");
	String displayName = request.getParameter("displayName");	
	String msg="";
	String sql = "";	
	
	ConnectDB dbcon = null;
	Statement stmt = null;
	try{
		dbcon = new ConnectDB();
		stmt = dbcon.connect(false);
		//System.out.println("Look@me.... 1");
		//SimpleCipher cipher = new SimpleCipher(Info.CYPHER_KEY);
		//password = cipher.encrypt(password);
	sql = "INSERT INTO email_recv(company_email_address,pop_host,pop_port,password,is_ssl,tagged_user_id,smtp_host,smtp_port,id,DISPLAY_NAME) ";
    sql += " VALUES('"+email+"','"+host+"','"+port+"','"+password+"','"+ssl+"','"+user_id+"','"+smtp_host+"','"+smtp_port+"',email_recv_sq.nextval,'"+displayName+"')";
		stmt.executeUpdate(sql);
		stmt.getConnection().commit();	
		out.print("{success:true}");	
	}catch(Exception e){
		msg = "<span style=\"color:red;font-weight:bold;\">Error Occured: "+e.getMessage()+"</span>";
	}finally{
		try{
			stmt.close();
		}catch(Exception e){}
		try{
			dbcon.close();
		}catch(Exception e){}
	}
%>