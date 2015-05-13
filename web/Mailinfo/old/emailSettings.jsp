<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.net.*"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.*"%>
<%@page import="org.json.*"%>
<%@page import="org.json.simple.parser.*"%>
<%@page import="org.json.simple.JSONValue"%>
	<%
	String id = request.getParameter("s_id");
	String uId = session.getId();
	String user_id = (String)session.getAttribute("user_id");
	String user_name = (String)session.getAttribute("user_name");
	/*
	System.out.println("id::::"+id);
	if(id == null ||(id != null && id.equals(uId) == false))
		{
		out.println("timeOut");
		return;
		}
	*/
	String method = request.getMethod().toString();
	System.out.println("look@me >>>>>URL<<<<<< :"+request.getMethod());	
	ConnectDB dbcon = null;
	Statement stmt = null;
	JSONObject message = new JSONObject();
	JSONObject mailObj = new JSONObject();
	JSONArray arr = new JSONArray();
	JSONParser jparser = new JSONParser();
	JSONValue jvalue = new JSONValue();	
	int start;
	String new_row_id = "";
	String req_row_id = "";
	String req_email = "";
	String req_pop_host = "";
	String req_pop_port = "";
	String req_smtp_host = "";
	String req_smtp_port = "";
	String req_uPass = "";
	String req_ssl = "";
	String req_displayName = "";
	
	String req_data = request.getParameter("data");
	System.out.println("look@me >>>>>	DATA 	<<<<<< :"+req_data); 
	if(req_data!=null && !req_data.equals("null") && req_data.length()>0 ){			
		Object obj=JSONValue.parse(req_data);
		mailObj.put("total", obj);
		ContainerFactory containerFactory = new ContainerFactory(){
			public List creatArrayContainer() {
			  return new LinkedList();
			}
			public Map createObjectContainer() {
			  return new LinkedHashMap();
			}				
	 	 };

		try{
			Map json = (Map)jparser.parse(req_data, containerFactory);
			Iterator iter = json.entrySet().iterator();
			System.out.println("==iterate result==");
			while(iter.hasNext()){
				Map.Entry entry = (Map.Entry)iter.next();
			  	System.out.println(entry.getKey() + "=>" + entry.getValue());
				String key = (String)entry.getKey();
						  
			  	if(key.equals("row_id"))
			  		req_row_id = (String)entry.getValue();
			  	if(key.equals("email"))
			  		req_email = (String)entry.getValue();			  
			  	if(key.equals("pop_host"))
			  		req_pop_host = (String)entry.getValue();			  
			  	if(key.equals("pop_port"))
			  		req_pop_port = (String)entry.getValue();	 		
			  	if(key.equals("smtp_host"))
			  		req_smtp_host = (String)entry.getValue();
				if(key.equals("smtp_port"))
			  		req_smtp_port = (String)entry.getValue();
				if(key.equals("uPass"))
			  		req_uPass = (String)entry.getValue();
				if(key.equals("ssl"))
			  		req_ssl = (String)entry.getValue();
				if(key.equals("displayName"))
			  		req_displayName = (String)entry.getValue();					 			  
			}
		  }
		  catch(ParseException pe){
			System.out.println(pe);
		  }
		}
		
	try{
		
		dbcon = new ConnectDB();
		stmt = dbcon.connect(false);
		ResultSet rs;
		String sql = "";
		
		// INITIAL (PUT)
		if(method!=null && !method.equals("null") && method.length()>0 && method.equals("PUT") ){
				sql += "SELECT ID,COMPANY_EMAIL_ADDRESS,POP_HOST,IS_SSL,POP_PORT,PASSWORD,TAGGED_USER_ID,SMTP_HOST,SMTP_PORT,DISPLAY_NAME	";
				sql += "FROM EMAIL_RECV WHERE TAGGED_USER_ID = '"+user_id+"' ";	
				rs = stmt.executeQuery(sql);			
				String row_id;
				String email;
				String pop_host;
				String pop_port;
				String smtp_port;
				String smtp_host;
				String uPass;
				String displayName;
				String ssl;
				while(rs.next()){
					row_id = rs.getString("ID");
					email = rs.getString("COMPANY_EMAIL_ADDRESS");
					pop_host = rs.getString("POP_HOST");
					ssl = rs.getString("IS_SSL");
					pop_port = rs.getString("POP_PORT");
					uPass = rs.getString("PASSWORD");
					smtp_host = rs.getString("SMTP_HOST");
					smtp_port = rs.getString("SMTP_PORT");
					displayName = rs.getString("DISPLAY_NAME");

					mailObj = new JSONObject();
					mailObj.put("row_id", row_id);
					mailObj.put("email", email);
					mailObj.put("pop_host", pop_host);
					mailObj.put("pop_port", pop_port);
					mailObj.put("smtp_host", smtp_host);
					mailObj.put("smtp_port", smtp_port);
					mailObj.put("uPass", "*****");
					mailObj.put("ssl", ssl);
					mailObj.put("displayName", displayName);
					arr.put(mailObj);	
				}
				mailObj = new JSONObject();
				mailObj.put("success", true);
				mailObj.put("message", "Loaded data");
				mailObj.put("data", arr);
				out.print(mailObj);
			}
		
		// ADD NEW (POST)	
		if(method!=null && !method.equals("null") && method.length()>0 && method.equals("POST") ){			
			sql = "";
			sql = "SELECT email_recv_sq.nextval AS ROW_ID FROM DUAL";
			rs = stmt.executeQuery(sql);
			if (rs.next())
				new_row_id = rs.getString("ROW_ID");
				
			sql = "";
			sql += "INSERT INTO EMAIL_RECV ";
			sql += "(COMPANY_EMAIL_ADDRESS,POP_HOST,IS_SSL,POP_PORT,PASSWORD,TAGGED_USER_ID,";
			sql += "SMTP_HOST,SMTP_PORT,ID,DISPLAY_NAME) ";
			sql += "VALUES ('"+req_email+"','"+req_pop_host+"','"+req_ssl+"', '"+req_pop_port+"', '"+req_uPass+"', '"+user_id+"', ";
			sql += "'"+req_smtp_host+"', '"+req_smtp_port+"', '"+new_row_id+"', '"+req_displayName+"')";
			stmt.executeUpdate(sql);
			stmt.getConnection().commit();
			
			JSONObject added_data = new JSONObject();
			added_data.put("row_id", new_row_id);
			added_data.put("email", req_email);
			added_data.put("pop_host", req_pop_host);
			added_data.put("pop_port", req_pop_port);
			added_data.put("smtp_host", req_smtp_host);
			added_data.put("smtp_port", req_smtp_port);
			added_data.put("uPass", "******");
			added_data.put("ssl", req_ssl);
			added_data.put("displayName", req_displayName);
			
			message = new JSONObject();
			message.put("success", true);
			message.put("message", "Created new User");
			message.put("data", added_data);
			out.print(message);
			}
			
		// DELETE(DELETE)	
		if(method!=null && !method.equals("null") && method.length()>0 && method.equals("DELETE") ){			
			start = id.indexOf("/");
			if(start > 0 ){
				req_row_id = id.substring(start+1, id.length());
				req_row_id = req_row_id.trim();
				}
			
			sql = "";
			sql += "DELETE FROM EMAIL_RECV WHERE ID="+req_row_id+"";
			stmt.executeUpdate(sql);
			stmt.getConnection().commit();
			
			message = new JSONObject();
			message.put("success", true);
			message.put("message", "Destroyed User"+req_row_id);
			arr = new JSONArray();
			message.put("data", arr);
			out.print(message);
			}
			
		// EDIT(GET)	
		if(method!=null && !method.equals("null") && method.length()>0 && method.equals("GET") ){
			String aa = (String)request.getRequestURI();
			System.out.println("========|| LOOK@ME :"+aa);
			start = id.indexOf("/");
			if(start > 0 ){
				req_row_id = id.substring(start+1, id.length());
				req_row_id = req_row_id.trim();
				}
			
			sql="";
			sql += "UPDATE EMAIL_RECV SET COMPANY_EMAIL_ADDRESS = '"+req_email+"', POP_HOST='"+req_pop_host+"', IS_SSL='"+req_ssl+"', ";
			sql += "POP_PORT='"+req_pop_port+"', PASSWORD='"+req_uPass+"', SMTP_HOST='"+req_smtp_host+"', SMTP_PORT='"+req_smtp_port+"', ";
			sql += "DISPLAY_NAME='"+req_displayName+"' WHERE ID = "+req_row_id+"";
			System.out.println("========|| SQL:"+sql);
			stmt.executeQuery(sql);
			System.out.println("===|| 1	||===");	
			stmt.getConnection().commit();
			System.out.println("===|| 2	||===");	
			/*
			JSONObject edited_data = new JSONObject();
			edited_data.put("row_id", req_row_id);
			edited_data.put("email", req_email);
			edited_data.put("pop_host", req_pop_host);
			edited_data.put("pop_port", req_pop_port);
			edited_data.put("smtp_host", req_smtp_host);
			edited_data.put("smtp_port", req_smtp_port);
			edited_data.put("uPass", "******");
			edited_data.put("ssl", req_ssl);
			edited_data.put("displayName", req_displayName);
			*/
			message = new JSONObject();
			message.put("success", true);
			message.put("message", "Updated User"+req_row_id);
			System.out.println("===|| DONE	||===");	
			//message.put("data", edited_data);
			out.print(message);	
			System.out.println("===|| DONE	||===");	
			}
			
	}catch(Exception e){
			message = new JSONObject();
			message.put("success", false);
			message.put("message", "Failed due to "+e);
			out.print(message);	
	}finally{
		try{
			stmt.close();
		}catch(Exception e){
			}
		try{
			dbcon.close();		
		}catch(Exception e){}
	}
%>