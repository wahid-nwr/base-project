<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page contentType="text/javascript; charset=iso-8859-1" language="java"  errorPage="" %>
<%@page import="com.swiftcorp.portal.mailinfo.util.ConnectDB"%>
<%@page import="com.swiftcorp.portal.mailinfo.util.MailSqlGenerator"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.Reader"%>
<%@page import="java.util.regex.*"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.Clob"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.user.dto.UserDTO"%>
<%@ page import="com.swiftcorp.portal.common.login.dto.LoginDetailInfoDTO"%>
<%
JSONObject mailObj = null;
JSONObject mailObj2 = null;
LoginDetailInfoDTO loginInfo = (LoginDetailInfoDTO)request.getSession ().getAttribute ( SESSION_KEYS.LOGIN_INFO );

	if(loginInfo!=null)
	{
		UserDTO user = (UserDTO) loginInfo.getUser ();
		mailObj = new JSONObject();
		mailObj2 = new JSONObject();
		JSONArray arr = new JSONArray();
		
		mailObj = new JSONObject();
		mailObj.put("userId", user.getComponentId());
		mailObj.put("userName", user.getFirstName());		
		mailObj2.put("userInfo",mailObj);
		mailObj2.put("success",true);
		arr.add(mailObj2);			
		out.print(mailObj2);
	}	
	else
	{
		//out.println("timeOut");
		mailObj2 = new JSONObject();
		mailObj = new JSONObject();
		mailObj.put("userId", "");
		mailObj.put("userName", "");		
		mailObj2.put("userInfo",mailObj);
		mailObj2.put("success",false);
		out.println(mailObj2);
		return;
	}
%>