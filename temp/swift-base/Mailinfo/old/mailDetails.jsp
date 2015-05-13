<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.maestro.crb.orig_billing.ConnectDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
<%@page import="java.util.regex.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Clob"%>
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
	String total = "";
	String sql = "";
	String contents_type = "";
	String contents = "";
	String type = "";
	String chrset = "";
	Clob clob_body=null;
	Clob clob_cc=null;
	String body = "";
	String mailBoby = "";
	String attachments = "";
	String email_id = request.getParameter("mail_id");
	String ref_id = "";
	String refId = "";
	String ref_dtl = "";
	String refDtl = "";
	String email_group_name = "";
	String email_groupId = "";
	String email_group_id = "";
	String rate_list = "";
	String email_group_list = "";
	String ref_group_list = "";
	String email_com_id = "";
	String email_com_name = "";
	String attachment = ""; 
	String recipients = "";
	String cc = "";
	String attachment_loc = "";
	String output = ""; // CONTACTS OUTPUT
	String output_2 = ""; // REF OUTPUT
	String output_3 = ""; // MAIL BOBY 
	try
	{
		con = new ConnectDB();
		stmt = con.connect();
		
		/**
		* RECEIPENTS
		*/
		sql = "SELECT RECIPIENT ,CC	FROM EMAIL WHERE ID = '"+email_id+"'";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			recipients = rs.getString("RECIPIENT");
			clob_cc=rs.getClob("CC");
			if(clob_cc!=null)
				cc=clob_cc.getSubString(1,(int)clob_cc.length());				
			}
		
		if(recipients!=null && !recipients.equals("null") && recipients.length()>0 ){	
			recipients = recipients.replaceAll("<", "&lt;");
			recipients = recipients.replaceAll(">", "&gt;");
			}
		
		if(cc!=null && !cc.equals("null") && cc.length()>0 ){
			cc = cc.replaceAll("<", "&lt;");
			cc = cc.replaceAll(">", "&gt;");
			}
		//System.out.println("CC"+cc);
			
		/**
		* PROCESS ATTACHMENTS	
		*/
		sql = "SELECT CONTENTS FROM EMAIL_DTL WHERE EMAIL_ID = '"+email_id+"' AND CONTENT_TYPE = 'ATTACHMENT'";
		rs = stmt.executeQuery(sql);
		if(rs.next()){
				output += "<br><span class='t1'>Attachments</span>";
				output += "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";		  
				sql = "SELECT CONTENTS FROM EMAIL_DTL WHERE EMAIL_ID = '"+email_id+"' AND CONTENT_TYPE = 'ATTACHMENT'";
				//System.out.println("SQL:"+sql);
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					clob_body=rs.getClob("CONTENTS");
					if(clob_body!=null)
					attachment_loc=clob_body.getSubString(1,(int)clob_body.length());
					attachment=clob_body.getSubString(4,(int)clob_body.length());					
					output += "<tr><td width=\"3%\"><img src=\"images/attachment-icon.png\" /></td>";
					output += "<td align=\"left\" class=\"t1\">";
					output += "<a href=\"download.jsp?file_name="+attachment_loc+"\" target=\"_blank\">"+attachment+"</a></td></tr>";
					}
				output += "</table><br>";
			}
		
		/**
		* CRATE LIST OF SHARE OFFICE CONTACTS	
		*/
		sql = "SELECT ID,(FIRST_NAME||' '||LAST_NAME) AS NAME FROM OMS_USERS@OMS_LINK WHERE USERROLE='OFFICE'";		
		rs = stmt.executeQuery(sql);
		String oms_user_list="";
		oms_user_list = "<select name=\"select\" class=\"ref_sel\" id=\"sel_share_"+email_id+"\">";
		while(rs.next()){
			String oms_user_id = rs.getString("ID");
			String oms_user_name = rs.getString("NAME");
			oms_user_list += "<option value=\""+oms_user_id+"\">"+oms_user_name+"</option>";
			}
		oms_user_list += "</select>";
		
		/**
		* MANAGE REFERENCE	
		*/
		output_2 += "<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
		output_2 += "<tr>";
		output_2 +=	"<td width=\"30%\" class=\"t1\" align=\"left\" onclick=\"show('share_"+email_id+"')\" style=\"cursor:pointer;\">";
		output_2 +=	"<img src=\"images/add-user-icon.png\" /> Share with other </td>";
		output_2 += "<td width=\"70%\" class=\"t1\" align=\"right\">";
			
		sql = "SELECT REF_ID,(SELECT REF_DTL FROM EMAIL_REFERENCE  WHERE REF_ID = EMAIL.REF_ID) AS REF_DTL,";
		sql += " EMAIL_GROUP_ID, (SELECT GROUP_NAME FROM EMAIL_GROUP  WHERE EMAIL_GROUP_ID = EMAIL.EMAIL_GROUP_ID) AS ";
		sql += " GROUP_NAME,COM_ID,(SELECT COMPANY_NAME FROM USERS WHERE ID=EMAIL.COM_ID) AS COM_NAME FROM EMAIL WHERE ID='"+email_id+"'";
		System.out.println("SQL:>>>>>>>"+sql);
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			if(rs.getString("REF_ID")!=null) refId = rs.getString("REF_ID");
			if(rs.getString("REF_DTL")!=null) ref_dtl = rs.getString("REF_DTL");
			if(rs.getString("EMAIL_GROUP_ID")!=null) email_groupId = rs.getString("EMAIL_GROUP_ID"); 
			if(rs.getString("GROUP_NAME")!=null) email_group_name = rs.getString("GROUP_NAME");
			if(rs.getString("COM_ID")!=null) email_com_id = rs.getString("COM_ID");
			if(rs.getString("COM_NAME")!=null) email_com_name = rs.getString("COM_NAME");
			 
			String fresh_ref_dtl = ref_dtl;
			if(email_com_name!=null && !email_com_name.equals("null") && email_com_name.length()>0)
				output_2 += email_com_name+" | ";			
				
			if(email_group_name!=null && !email_group_name.equals("null") && email_group_name.length()>0)
				output_2 += "Group: "+email_group_name;			
				
			if(ref_dtl!=null && !ref_dtl.equals("null") && ref_dtl.length()>0)
      			output_2 += " ,Reference:"+ref_dtl;
				}
			if(refId!=null && !refId.equals("null") && refId.length()>0 && email_groupId!=null){		      		  
			output_2 +=	"<div style=\"border:1px solid red;\" id=\"maes_ref_"+email_id+"\" class=\"hidden\">";
            output_2 +=	"<div>Ref No.Maes:"+email_com_id+"/"+email_groupId+"/"+refId+"</div></div>";
			output_2 += "&nbsp;&nbsp;<a href=\"#\" onclick=\"show('ref_edit_"+email_id+"'); return false;\">";
			output_2 += "<img border=\"0\" src=\"images/edit.gif\" /></a>";
			output_2 +=	"</td></tr></table>"; 				
				}
			 else{
			output_2 += "<div  id=\"maes_ref_"+email_id+"\"></div>";
			output_2 += " No Reference. <a href=\"#\" onclick=\"show('ref_edit_"+email_id+"'); return false;\">";
			output_2 += " <img border=\"0\" src=\"images/edit.gif\" /></a></td></tr></table>";
				}				
		output_2 += "<div  id=\"ref_mess_"+email_id+"\" class=\"hidden\"></div>";	
		refDtl = ref_dtl;
		sql = "SELECT EMAIL_GROUP_ID,GROUP_NAME FROM EMAIL_GROUP";	
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			email_group_id = rs.getString("EMAIL_GROUP_ID");
			email_group_name = rs.getString("GROUP_NAME");
			if(email_groupId.equals(email_group_id))			
				ref_group_list += "<option value=\""+email_group_id+"\" selected>"+email_group_name+"</option>";
			else
				ref_group_list += "<option value=\""+email_group_id+"\" >"+email_group_name+"</option>";
			}
		sql = "SELECT REF_ID, REF_DTL, EMAIL_GROUP_ID FROM EMAIL_REFERENCE";
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			ref_id = rs.getString("REF_ID");
			ref_dtl = rs.getString("REF_DTL");
			if(ref_id.equals(refId)){
				email_group_list += "<option value=\""+ref_id+"\" selected>"+ref_dtl+"</option>";
				}
			else
				email_group_list += "<option value=\""+ref_id+"\" >"+ref_dtl+"</option>";	
			}
	
		String com_id = "";
		String com_name = "";
		String com_list = "";	
		sql = "SELECT DISTINCT ID,COMPANY_NAME FROM USERS WHERE COMPANY_NAME IS NOT NULL AND EMAIL IS NOT NULL ORDER BY COMPANY_NAME";
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			com_id = rs.getString("ID");
			com_name = rs.getString("COMPANY_NAME");
			if(email_com_id.equals(com_id))			
				com_list += "<option value=\""+com_id+"\" selected>"+com_name+"</option>";
			else
				com_list += "<option value=\""+com_id+"\">"+com_name+"</option>";	
			}
		output_2 += "<div  id=\"ref_edit_"+email_id+"\" class=\"hidden\" >";
		output_2 += "<table width=\"100%\" border=\"0\" cellspacing=\"2\" cellpadding=\"3\">";		
		output_2 +=	"<tr><td>";
		output_2 += "<input type=\"radio\" name=\"ref_group\" checked=\"checked\" value=\"radio\" "; 
		output_2 += "id=\"ref_ch1\" onclick=\"checked_item('"+email_id+"')\" /></td>";
		output_2 += "<td class=\"t1\"> Change Comp/ Group/ Reference: </td><td>";
		output_2 += "<select id=\"opt_1_"+email_id+"\" class=\"ref_sel\">"+com_list+"</select>";
		output_2 += "<select id=\"opt_2_"+email_id+"\" class=\"ref_sel\">"+ref_group_list+"</select>";
		output_2 += "<select id=\"opt_3_"+email_id+"\" class=\"ref_sel\">"+email_group_list+"</select>";
		output_2 +=	"<input type=\"hidden\" id=\"opt_2_dtl_"+email_id+"\" value=\"ref_dtl\" />";
		output_2 += "</td></tr><tr><td>";
		output_2 += "<input type=\"radio\" name=\"ref_group\" value=\"radio\" id=\"ref_ch2\" onclick=\"checked_item2('"+email_id+"')\" />";
		output_2 += "</td><td class=\"t1\"> Assign New Reference: </td><td>";
		output_2 += "<select id=\"opt_4_"+email_id+"\" disabled=\"true\" class=\"ref_sel\">"+com_list+"</select>";
		output_2 += "<select id=\"opt_5_"+email_id+"\" disabled=\"true\" class=\"ref_sel\">"+ref_group_list+"</select>";
		output_2 +=	"<input id=\"opt_6_"+email_id+"\" name=\"\" type=\"text\" class=\"ref_in\" disabled=\"true\"/>";
		output_2 += "</td></tr><tr><td></td><td>";
		output_2 += "<input type=\"button\" name=\"Chage\" value=\"Submit\" class=\"ref_btn\" onclick=\"set_ref('"+email_id+"');\" /></td>";
		output_2 += "</tr></table></div>";
		
		output_2 += "<div   id=\"share_"+email_id+"\" class=\"hidden\">";
		output_2 += oms_user_list;
		output_2 += "<input type=\"button\" value=\"Share\" id=\"share_mail_"+email_id+"\" class=\"ref_btn\" "; 
		output_2 += "onclick=\"share_mail('"+email_id+"','"+email_id+"');\" />";
		output_2 += "</div>";
			
			
		
		/**
		* MAIL BODY	
		*/	
		sql = "SELECT CONTENT_TYPE, CONTENTS FROM EMAIL_DTL WHERE EMAIL_ID = "+email_id+" AND CONTENT_TYPE like '%text/html%'";
		//System.out.println("\nSQL:"+sql);  
		rs = stmt.executeQuery(sql);
		if(rs.next()){
			contents_type = rs.getString("CONTENT_TYPE");
			clob_body=rs.getClob("CONTENTS");
			body="";
			if(clob_body!=null)
				mailBoby=clob_body.getSubString(1,(int)clob_body.length());		
			}
		else{
			sql = "SELECT CONTENT_TYPE, CONTENTS FROM EMAIL_DTL WHERE EMAIL_ID = "+email_id+" AND AND CONTENT_TYPE != 'ATTACHMENT'";
			//System.out.println("\nSQL:"+sql);  
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				contents_type = rs.getString("CONTENT_TYPE");
				clob_body=rs.getClob("CONTENTS");
				body="";
				if(clob_body!=null)
					mailBoby=clob_body.getSubString(1,(int)clob_body.length());		
				}
			}	
		sql = "UPDATE RECIPIENTS SET STATUS='I' WHERE MESSAGE_ID="+email_id+"";
		stmt.executeQuery(sql); 
		output_3 = "<div  id=\"mail_body_text_"+email_id+"\">"+mailBoby+"</div>";
			 
		if(output!=null && !output.equals("null") && output.length()>0){
			out.print(output_2+"<@@@###%%%BODY>"+output_3+"<@@@###%%%ATTACHMENT>"+output+"<@@@###%%%RECIPIENT>"+recipients);
			if(cc!=null && !cc.equals("null") && cc.length()>0)
				out.print("<@@@###%%%CC>"+cc+"<@@@###%%%FINISH>");
			else
			out.print("<@@@###%%%CC><@@@###%%%FINISH>");	
			}
		else{
			out.print(output_2+"<@@@###%%%BODY>"+output_3+"<@@@###%%%ATTACHMENT><@@@###%%%RECIPIENT>"+recipients);
			if(cc!=null && !cc.equals("null") && cc.length()>0)
				out.print("<@@@###%%%CC>"+cc+"<@@@###%%%FINISH>");
			else
				out.print("<@@@###%%%CC><@@@###%%%FINISH>");	
			}
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