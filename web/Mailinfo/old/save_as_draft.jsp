<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.File"%>
<%@page import="com.maestro.rateserver.util.EmailSenderNew"%>
<%@page import="java.sql.Statement"%>
<%@page import="oracle.sql.CLOB"%>
<%@page import="java.util.StringTokenizer"%>
<%
	String id = request.getParameter("id");
	String uId = session.getId();
	String user_id = (String)session.getAttribute("user_id");
	String user_name = (String)session.getAttribute("user_name");
	String msg = "";
	
	System.out.println("id::::"+id);
	if(id == null ||(id != null && id.equals(uId) == false))
		{
		out.println("timeOut");
		return;
		}
		
		
%>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%><%
	//System.out.println("ENTERED DRAFT SAVING  FLOW..... ");
	String action = request.getParameter("action");
	ConnectDB connecor = null;
	Statement stmt = null;
	String sql;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String dbLink = com.maestro.rateserver.common.AppConst.DB_LINK_ACC;
    String companyid="1";
	try{
		connecor = new ConnectDB();
		stmt = connecor.connect();
		String priority = request.getParameter("priority");
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String cc = request.getParameter("cc");
		String attachments = request.getParameter("attachments");
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		String repliedEmailId = request.getParameter("repliedEmailId");
		String path  = request.getRealPath("/");
		String email_id = request.getParameter("email_id");
		
		//System.out.println("from:"+from);
		//System.out.println("to:"+to);
		//System.out.println("cc:"+cc);
		//System.out.println("attachments:"+attachments);
		//System.out.println("subject:"+subject);
		////System.out.println("body:"+body);
		//System.out.println("repliedEmailId:"+repliedEmailId);
		//System.out.println("path:"+path);
		//System.out.println("email_id:"+email_id);
		
		
		String smtp_host="";
		String smtp_port="";
		String pass = "";
		String isssl = "";
		path = path+"email_new"+File.separator+"attachments"+File.separator;
		EmailSenderNew emailer = new EmailSenderNew();
		
		to = to.replaceAll("&lt;", "<");
		to = to.replaceAll("&gt;", ">");
				
		from = from.replaceAll("&lt;", "<");
		from = from.replaceAll("&gt;", ">");
		
		if(from ==null || from.length()<1){	
			sql="SELECT RECIPIENT FROM EMAIL WHERE ID="+email_id;
			//System.out.println("SQL:"+sql);
			rs = stmt.executeQuery(sql);
			if(rs.next())
			from = rs.getString("RECIPIENT");
			}
		
		from = from.replaceAll("\"", "");
		int start = from.indexOf("<");
		if(start > 0 ){
			int end = from.indexOf(">");			
			if(start > 0 && end > 0){
				from = from.substring(start+1, end); 
				from = from.trim();
				}
			}
			
		start = from.indexOf("(");
		if(start > 0 ){
			int end = from.indexOf(")");			
			if(start > 0 && end > 0){
				from = from.substring(start+1, end); 
				from = from.trim();
				}
			}								
		String send_mail_id = ""; 
		String email_recv_id = "";
		sql = "SELECT EMAIL_SQ.nextval AS EMAIL_ID FROM DUAL";
		rs = stmt.executeQuery(sql);
		if (rs.next())
			send_mail_id = rs.getString("EMAIL_ID");
				
		sql = "SELECT ID FROM EMAIL_RECV WHERE COMPANY_EMAIL_ADDRESS LIKE '%"+from+"%' AND TAGGED_USER_ID = '"+user_id+"'  ";
		//System.out.println("\n");
		//System.out.println("SQL>"+sql);
		rs = stmt.executeQuery(sql);
		if (rs.next())
			email_recv_id = rs.getString("ID");
		
		// DATA SATVE TO RECIPIENTS
		sql = "INSERT INTO RECIPIENTS(ID,MESSAGE_ID,CONTACT_ID,STATUS,EMAIL_RECV_ID,MESSAGE_TYPE,SEND_DATE	) VALUES(ID.NEXTVAL, ";
		sql += ""+send_mail_id+",'"+user_id+"','U','"+email_recv_id+"','DRAFT_EMAIL',SYSDATE )";
		//System.out.println("\n");
		//System.out.println("SQL>"+sql);
		stmt.executeQuery(sql);
		
		// DATA SAVE TO EMAIL
		sql = " INSERT INTO EMAIL(ID,SENDER,RECIPIENT,CC,SEND_DATE,SUBJECT,ALL_RECIPIENTS,E_CHECKED,EMAIL_GROUP_ID)" +
				" VALUES("+send_mail_id+",?,?,?,SYSDATE,?,'','U','')";
		pstmt = stmt.getConnection().prepareStatement(sql);					
		pstmt.setString(1, from);
		pstmt.setString(2, to);
		CLOB ccClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
		ccClob.putString(1,cc);
		pstmt.setClob(3,ccClob);
		pstmt.setString(4, subject);
		//System.out.println("\n");
		//System.out.println("SQL>"+sql);					
		pstmt.execute();
		
		// DATA SATVE TO EMAIL_DTL
		sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
			" VALUES(EMAIL_SQ.nextval," + send_mail_id + ",'text/html; charset = \"UTF-8\"',?)";
		pstmt = stmt.getConnection().prepareStatement(sql);
		CLOB newClob = CLOB.createTemporary(stmt.getConnection(),false,CLOB.DURATION_CALL);
		newClob.putString(1,body);        
		pstmt.setClob(1, newClob);
		//System.out.println("\n");
		//System.out.println("SQL>"+sql);					
		pstmt.execute();
		
		//HANDLE ATTACHMENTS
		if(attachments!=null && !attachments.equals("null") && attachments.length()>0){
			StringTokenizer st_to = new StringTokenizer(attachments, ",");
			int tokenCount_to = st_to.countTokens();
			for (int i = 0; st_to.hasMoreTokens(); i++) {
		            String msgTo_to = st_to.nextToken();
					msgTo_to = msgTo_to.trim();
					sql = "INSERT INTO EMAIL_DTL(ID,EMAIL_ID,CONTENT_TYPE,CONTENTS)" +
							" VALUES(EMAIL_SQ.nextval," + send_mail_id + ",'ATTACHMENT',?)";
					pstmt = stmt.getConnection().prepareStatement(sql);
					pstmt.setString(1, msgTo_to);
					pstmt.execute();

				}
		}

		

		out.print("{success:true}");
		
	}catch(Exception e){
		e.printStackTrace();
		msg = "<span style=\"color:red;font-weight:bold;\">Error occurred: "+e.getMessage()+"<span>";
	}finally{
		try{
			stmt.close();	
		}catch(Exception e){}
		try{
			connecor.close();
		}catch(Exception e){}
	}
%>