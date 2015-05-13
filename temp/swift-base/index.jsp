<%@ include file="/Common/Include/taglib.jsp"%>
<%@page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>

<%
	String sendRedirect = "loginAction.csmp?method=promptLogin";
	String isSessionExpire = (String) request.getAttribute(SESSION_KEYS.IS_SESSION_EXPIRE);

	if("true".equals(isSessionExpire))
	{
		sendRedirect += "&" + SESSION_KEYS.IS_SESSION_EXPIRE + "=true";
	}
	
	response.sendRedirect(sendRedirect);
%>