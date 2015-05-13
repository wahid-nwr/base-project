<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="org.json.JSONArray"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%
response.setContentType("application/javascript;");
	JSONObject mails = (JSONObject)request.getAttribute(SESSION_KEYS.MAILINFO_INITIAL_LOAD);
	System.out.println(mails);
	out.println(mails);
%>