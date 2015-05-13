<%@page import="com.maestro.crb.orig_billing.info.Inf"%>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
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
	String step = request.getParameter("node");
	ConnectDB connector = null;
	Statement stmt = null;
	String qry = "";
	
	JSONObject mailObj = new JSONObject();
	JSONArray arr = new JSONArray();
	
	if(step != null && step.equals("first") && step.length()>0){
		mailObj.put("text", "Company Contacts");
		mailObj.put("id", "company");
		arr.put(mailObj);
		
		mailObj = new JSONObject();
		mailObj.put("text", "Office Contacts");
		mailObj.put("id", "office");
		arr.put(mailObj);
		out.print(arr);
	}else{			
		try{
			connector = new ConnectDB();
			stmt = connector.connect();
			if(step != null && step.equals("company") && step.length()>0){
				arr = new JSONArray();
				qry = "SELECT DISTINCT ID,COMPANY_NAME,EMAIL FROM USERS WHERE COMPANY_NAME IS NOT NULL AND EMAIL IS NOT NULL ORDER BY COMPANY_NAME";
				ResultSet rs = stmt.executeQuery(qry);
				while(rs.next()){
					mailObj = new JSONObject();
					mailObj.put("text", rs.getString("COMPANY_NAME"));
					mailObj.put("com_email", rs.getString("EMAIL"));
					mailObj.put("id", rs.getString("ID"));
					arr.put(mailObj);
					}
				out.print(arr);	
				}
			
			if(step != null && step.equals("office") && step.length()>0){
				qry = "SELECT NAME,EMAIL,OMS_USER_ID FROM USERS WHERE EMAIL IS NOT NULL";
				ResultSet rs = stmt.executeQuery(qry);
				while(rs.next()){
					mailObj = new JSONObject();
					mailObj.put("text", rs.getString("NAME"));
					mailObj.put("com_email", rs.getString("EMAIL"));
					mailObj.put("id", rs.getString("OMS_USER_ID"));
					mailObj.put("leaf", true);
					arr.put(mailObj);
					}
				out.print(arr);		
				}
				
			}catch(Exception e){
				out.print(e.getMessage());		
			}finally{
				try{
					stmt.close();
				}catch(Exception e){}
				try{
					connector.close();
				}catch(Exception e){}
			}
		}
%>
