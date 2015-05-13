<%@page import="com.swiftcorp.portal.mailinfo.util.ConnectDB"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.MailSqlGenerator"%>
<%@page import="java.sql.Statement"%>
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
		System.out.println("timeOut");
		//return;
		}	
%>
<%
	ConnectDB connector = null;
	Statement stmt = null;
	Statement stmt2 = null;
	long noWaiting = 0;
	String qry = "";
	String qry2 = "";
	String comId = "";
	String comName = "";
	String comEmail ="";
	String con_name = "";
	String total = "";
	String total_detail = "";
	String totalMail = "";
	String group_name = "";
	String group_id = "";
	String email = "";
	int total_mail = 0;
	int gr_total_mail = 0;
	String tmp = "";
	String tmp_str = "";
	try{
		connector = new ConnectDB();
		stmt = connector.connect();
		
		qry = "SELECT DISTINCT ID,COMPANY_NAME,EMAIL FROM USERS WHERE COMPANY_NAME IS NOT NULL AND EMAIL IS NOT NULL ORDER BY COMPANY_NAME";
		qry = MailSqlGenerator.getCompanyFromUsers();
		ResultSet rs = stmt.executeQuery(qry);
		Vector vt = new Vector();
		Vector vt_undead = new Vector();
		Vector vt_read = new Vector();
		String str[] = null;
		String str2[] = null;
		String tmpQry = "";
		while(rs.next()){
				comId = rs.getString("componentId");
				comName = rs.getString("companyname");
				comEmail = rs.getString("email");
				vt.add(new String [] {comId, comName,comEmail});	
			}
		for(int i=0;i<vt.size();i++){
			str = (String [])vt.get(i);
			comId = str[0];
			comName = str[1];
			comEmail = str[2];
			qry = "";
			/* 
			qry += "SELECT COUNT(*) AS TOTAL, (SELECT GROUP_NAME FROM EMAIL_GROUP WHERE EMAIL_GROUP_ID = EMAIL.EMAIL_GROUP_ID) AS GROUP_NAME, ";
			qry += " EMAIL.EMAIL_GROUP_ID FROM EMAIL,RECIPIENTS WHERE RECIPIENTS.MESSAGE_ID = EMAIL.ID AND RECIPIENTS.STATUS = 'U' ";
			qry += " AND RECIPIENTS.CONTACT_ID='"+user_id+"' AND EMAIL.COM_ID='"+comId+"' GROUP BY EMAIL.EMAIL_GROUP_ID ";
			 */
			qry = MailSqlGenerator.getCompanyInboxDtlByUser(user_id,comId);
			////System.out.println("SQL:"+qry);
			rs = stmt.executeQuery(qry);			
			tmp_str += "<table width=\"100%\" border=\"0\" cellspacing=\"3\" cellpadding=\"3\" class=\"hidden\" id=\"tbl_1_"+comId+"\" >";
			while(rs.next()){
				////System.out.println("hello....");
				total = rs.getString("total");
				group_name = rs.getString("groupname");
				group_id = rs.getString("emailgroupid");
				if(!total.equals("null") && total.length()>0 && !total.equals("null")) {
					////System.out.println("TOTAL:"+qry);
					if(!group_name.equals("null") && group_name.length()>0 && !group_name.equals("null")){
						total_mail = Integer.parseInt(total);
						gr_total_mail = gr_total_mail + total_mail;
						tmp_str += "<tr><td width=\"5%\">&nbsp;</td><td class=\"m_t2\"> ";
						tmp_str += " <a href=\"\" onclick=\"show_inbox('','"+comName+"("+group_name+")','"+comId+"','"+group_id+"','group'); return false;\"> ";
						tmp_str += " <b>"+group_name+"("+total_mail+")"+"</b></a> ";
						tmp_str += " <table class='hidden' id='unread_"+comId+"_"+group_id+"' width='100%' border='1' cellspacing='0' cellpadding='0'>";
						tmp_str += " 	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>";
						tmp_str += " </table></td></tr>";						
						}
				 	}
				}
			tmp_str += "</table>";
			if(gr_total_mail > 0){
				tmp = new Integer(gr_total_mail).toString();
				vt_undead.add(new String [] {comId, comName,comEmail,tmp,tmp_str});
				gr_total_mail = 0;
				}
			else{
			}				
			tmp_str = "";	
			/* 
			qry = " SELECT COUNT(*) AS TOTAL, (SELECT GROUP_NAME FROM EMAIL_GROUP WHERE EMAIL_GROUP_ID = EMAIL.EMAIL_GROUP_ID) AS GROUP_NAME,";
			qry += " EMAIL.EMAIL_GROUP_ID FROM EMAIL WHERE EMAIL.COM_ID = '"+comId+"' GROUP BY EMAIL.EMAIL_GROUP_ID";
			 */
			qry = MailSqlGenerator.getCompanyInboxDtl(comId);
			////System.out.println("SQL2:"+qry);
			rs = stmt.executeQuery(qry);
			tmp_str += "<table width=\"100%\" border=\"0\" cellspacing=\"3\" cellpadding=\"3\" class=\"hidden\" id=\"tbl_"+comId+"\" >";
			while(rs.next()){
				total = rs.getString("total");
				group_name = rs.getString("groupname");
				group_id = rs.getString("emailgroupid");
				if(!total.equals("null") && total.length()>0 && !total.equals("null")) {
					if(!group_name.equals("null") && group_name.length()>0 && !group_name.equals("null")){
						total_mail = Integer.parseInt(total);
						gr_total_mail = gr_total_mail + total_mail;
						tmp_str += "<tr><td width=\"5%\">&nbsp;</td><td class=\"m_t2\"> ";
						tmp_str += " <a href=\"\" onclick=\"show_inbox('','"+comName+"("+group_name+")','"+comId+"','"+group_id+"','','group'); return false;\"> ";
						tmp_str += " <b>"+group_name+"("+total_mail+")"+"</b></a>   &nbsp;&nbsp;  ";
						tmp_str += "<img style=\"cursor:pointer\" src=\"images/arrow2.gif\" onclick=\"show_gr_dtl('"+comId+"','"+group_id+"'); return false;\" />";
						tmp_str += " <table class='hidden' id='"+comId+"_"+group_id+"' width='100%' border='0' cellspacing='0' cellpadding='0'>";
						tmp_str += " 	<tr><td>&nbsp;</td><td>&nbsp;</td></tr>";
						tmp_str += " </table></td></tr>";												
						}
				 	}
				}
			//recv_id,tab_title,com_id,email_group_id,recv_email_address,type	
			tmp_str += "</table>";
			if(gr_total_mail > 0){
				tmp = new Integer(gr_total_mail).toString();
				vt_read.add(new String [] {comId, comName,comEmail,tmp,tmp_str});
				gr_total_mail = 0;
				}				
			tmp_str = "";	
			}// END OF vt FOR LOOP
		%>
		
		<!-- ONLY NEW MAILS -->
		<%
		if(vt_undead.size()>0){
		%>
			<div class="th2">New Mails </div>
		<% } %>
		<table width="100%" border="0" cellspacing="3" cellpadding="3"><%
		
		for(int i=0;i<vt_undead.size();i++){		
			str = (String [])vt_undead.get(i);
			comId = str[0];
			comName = str[1];
			comEmail = str[2];
			tmp = str[3];
			total_detail = str[4];
			%><tr><td  class="m_t2" onclick="show('tbl_1_<%=comId%>'); return false;"><a href="#"><b><%=comName%>(<%=tmp%>)</b></a></td></tr>
			<tr><td class="m_t2"><%=total_detail%></td></tr><%
			}
		%></table>
		<br>
		<div class="t1"> &nbsp; &nbsp; &nbsp;ALL Mails </div>
			<table width="100%" border="0" cellspacing="3" cellpadding="3"><%
				
				for(int i=0;i<vt_read.size();i++){		
					str = (String [])vt_read.get(i);
					comId = str[0];
					comName = str[1];
					comEmail = str[2];
					tmp = str[3];
					total_detail = str[4];
					%><tr><td  class="m_t2" onclick="show('tbl_<%=comId%>'); return false;"><a href="#"><b><%=comName%>(<%=tmp%>)</b></a></td></tr>
					<tr><td class="m_t2"><%=total_detail%></td></tr><%
					}
					
				%></table>
		<%				
		}catch(Exception e){
%>
		<span style="color: red;font-weight: bold;">Error Occurred: <%=e.getMessage() %></span>
		
<%			
		}finally{
			try{
				stmt.close();
				
			}catch(Exception e){}
			try{
				connector.close();
			}catch(Exception e){}
		}
%>
