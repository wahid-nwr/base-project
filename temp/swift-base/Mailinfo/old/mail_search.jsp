<%@ page contentType="text/html; charset=utf-8" language="java"  errorPage="" %>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
<%@page import="java.util.regex.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Clob"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
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
	String str_start = request.getParameter("start");
	String str_limit = request.getParameter("limit");
	String topic = request.getParameter("topic");
	 topic = topic.toLowerCase();
	int qStart = Integer.parseInt(str_start);
	int limit = Integer.parseInt(str_limit);
	int qEnd = qStart+limit;
	String id = "";
	String sender ="";
	String send_date ="";
	String subject = "";
	String body_text = "";
	String senderName = "";
	String senderEmail = "";
	Clob clob_body=null;
	String status = "";
	int total = 0;
	int count = 1;
	String output = "";
	String ttl = "";
	try
	{
		con = new ConnectDB();
		stmt = con.connect();
		sql = "SELECT COUNT(*) AS TOTAL ";
		sql += " FROM ( SELECT EMAIL.ID,EMAIL.SENDER,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE, EMAIL.SUBJECT,";
		sql += " SUBSTR(EMAIL_DTL.CONTENTS,1,200) AS BODY_TEXT FROM EMAIL, EMAIL_DTL WHERE EMAIL.ID = EMAIL_DTL.EMAIL_ID";
		sql += " AND SUBSTR(EMAIL_DTL.CONTENT_TYPE,1,10)='text/plain' AND ( LOWER(EMAIL.SUBJECT) LIKE '% "+topic+" %'";
		sql += " OR LOWER(EMAIL.SUBJECT) LIKE '% "+topic+"' OR LOWER(EMAIL.SUBJECT) LIKE '"+topic+" %' OR LOWER(EMAIL_DTL.CONTENTS) LIKE '% "+topic+" %'";
		sql += " OR LOWER(EMAIL_DTL.CONTENTS) LIKE '"+topic+" %' OR LOWER(EMAIL_DTL.CONTENTS) LIKE '% "+topic+"' ))EMAIL,";
		sql += " ( SELECT MESSAGE_ID,STATUS FROM RECIPIENTS WHERE CONTACT_ID = '"+user_id+"' )REC WHERE EMAIL.ID = REC.MESSAGE_ID";
		rs = stmt.executeQuery(sql);
		if(rs.next())
			ttl = rs.getString("TOTAL"); 
	 	if(ttl!=null && !ttl.equals("null") && ttl.length()>0){
			total = Integer.parseInt(ttl);
			}
		output = "{\"totalCount\":\""+total+"\",\"topics\":[";	
		sql = "";
		sql = "SELECT EMAIL.ID,EMAIL.SENDER,EMAIL.SEND_DATE, EMAIL.SUBJECT,EMAIL.BODY_TEXT, REC.STATUS ";
		sql += " FROM ( SELECT EMAIL.ID,EMAIL.SENDER,TO_CHAR(EMAIL.SEND_DATE,'mm/dd/yyyy') AS SEND_DATE, EMAIL.SUBJECT,";
		sql += " SUBSTR(EMAIL_DTL.CONTENTS,1,1000) AS BODY_TEXT FROM EMAIL, EMAIL_DTL WHERE EMAIL.ID = EMAIL_DTL.EMAIL_ID";
		sql += " AND SUBSTR(EMAIL_DTL.CONTENT_TYPE,1,10)='text/plain' AND ( LOWER(EMAIL.SUBJECT) LIKE '% "+topic+" %'";
		sql += " OR LOWER(EMAIL.SUBJECT) LIKE '% "+topic+"' OR LOWER(EMAIL.SUBJECT) LIKE '"+topic+" %' OR LOWER(EMAIL_DTL.CONTENTS) LIKE '% "+topic+" %'";
		sql += " OR LOWER(EMAIL_DTL.CONTENTS) LIKE '"+topic+" %' OR LOWER(EMAIL_DTL.CONTENTS) LIKE '% "+topic+"' ))EMAIL,";
		sql += " ( SELECT MESSAGE_ID,STATUS FROM RECIPIENTS WHERE CONTACT_ID = '"+user_id+"' )REC WHERE EMAIL.ID = REC.MESSAGE_ID";
		//System.out.println("SQL:"+sql);
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			 id = rs.getString("ID");
			 sender = rs.getString("SENDER");
			 send_date = rs.getString("SEND_DATE");
			 subject = rs.getString("SUBJECT");
			 clob_body=rs.getClob("BODY_TEXT");
			 status = rs.getString("STATUS");
			 subject = subject.replace("\"", "");
			 sender = sender.replace("\"", "");
			 body_text=clob_body.getSubString(1,(int)clob_body.length());
			 
			 body_text = body_text.replaceAll("\"", "");
			 body_text = body_text.replaceAll("'", "");
			 body_text = body_text.replaceAll(""+(char)20, "");
			 body_text = body_text.replaceAll(""+(char)10, "");
			 body_text = body_text.replaceAll(""+(char)13, "");
			 body_text = body_text.replaceAll("_", "");
			 body_text = body_text.trim();
			 
			 Pattern pattern = Pattern.compile("\\b"+topic+"\\b",Pattern.CASE_INSENSITIVE); 
			 Matcher matcher = pattern.matcher(body_text); 
			 body_text = matcher.replaceAll("<font color='green'><b>"+topic+"</b></font>");
			 
			 matcher = pattern.matcher(subject); 
			 subject = matcher.replaceAll("<font color='green'><b>"+topic+"</b></font>");
			 

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
			else{
				senderName = sender;
				senderEmail = sender;
				}

			if( count >= qStart && count<= qEnd){
				output += "{";
				output += "\"email_id\":\""+id+"\",";
				//System.out.println("email_id:"+id);
				//System.out.println("BODY:"+body_text+"\n");
				output += "\"senderName\":\""+senderName+"\",";
				output += "\"senderEmail\":\""+senderEmail+"\",";
				output += "\"subject\":\""+subject+"\",";
				output += "\"send_date\":\""+send_date+"\",";
				output += "\"body_text\":\""+body_text+"\",";
				output += "\"status\":\""+status+"\" "; 
				if(count == qEnd || count == total)
					output += "}";
				else 
					output += "},";
				count++;	
				}
			}
		output += "]}";	
		out.print(output);		
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