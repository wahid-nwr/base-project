<%@ include file="/Common/Include/taglib.jsp"%>

<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.user.dto.UserDTO"%>
<%@ page import="com.swiftcorp.portal.common.login.dto.LoginDetailInfoDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>

<%
	int accessLevel = WebUtils.getAccessLevel(request);		
	int workingLevel = WebUtils.getUserWorkingLevel(request);
    System.out.println("Breadcrum: access level :"+accessLevel);
	System.out.println("Breadcrum: working level :"+workingLevel);	
%>

<table width="100%" border="0" cellspacing="3" cellpadding="0"><tbody><tr><td class="Breadcrumb">
   
          
<%
if(accessLevel == GlobalConstants.SYSTEM_LEVEL)
{
	if(workingLevel == GlobalConstants.SYSTEM_LEVEL)
	{
%>	
	<a href="loginAction.csmp?method=loginUserHome"> &nbsp; Home</a>
<%
	}
	else if(workingLevel == GlobalConstants.GROUP_LEVEL)
	{
		GroupDTO group = (GroupDTO)session.getAttribute(SESSION_KEYS.GROUP);
%>
	<b>
	<a href="loginAction.csmp?method=loginUserHome">&nbsp; Home</a>
	>>
	<a href="groupAction.csmp?method=promptGroupHomeSystemAdmin&componentId=<%= group.getComponentId() %>"><%= group.getUniqueCode() %></a>
	</b>
<%
	}
	else if(workingLevel == GlobalConstants.END_USR_LEVEL)
	{
		UserDTO user = (UserDTO)session.getAttribute(SESSION_KEYS.USER);
		GroupDTO group = (GroupDTO)session.getAttribute(SESSION_KEYS.GROUP);
%>
	<b><a href="loginAction.csmp?method=loginUserHome">&nbsp; Home</a>
	>>
	<a href="groupAction.csmp?method=promptGroupHomeSystemAdmin&componentId=<%= group.getComponentId() %>"><%= group.getUniqueCode() %></a>
	>>
	<a href="userAction.csmp?method=promptUserHomeSystemLevel&componentId=<%= user.getComponentId() %>"><%= user.getUniqueCode() %></a>
	</b>
<%
	}
}
%>


 
<%
if(accessLevel == GlobalConstants.GROUP_LEVEL)
{
	if(workingLevel == GlobalConstants.GROUP_LEVEL)
	{
		GroupDTO group = (GroupDTO)session.getAttribute(SESSION_KEYS.GROUP);
%>
	<b><a href="groupAction.csmp?method=promptGroupHomeGroupAdmin&componentId=<%= group.getComponentId() %>"><%= group.getUniqueCode() %></a></b>
<%
	}
	else if(workingLevel == GlobalConstants.END_USR_LEVEL)
	{
		UserDTO user = (UserDTO)session.getAttribute(SESSION_KEYS.USER);
		GroupDTO group = (GroupDTO)session.getAttribute(SESSION_KEYS.GROUP);
%>
	<b><a href="groupAction.csmp?method=promptGroupHomeGroupAdmin&componentId=<%= group.getComponentId() %>"><%= group.getUniqueCode() %></a>
	>>
	<a href="userAction.csmp?method=promptGroupHomeGroupLevel&componentId=<%= user.getComponentId() %>"><%= user.getUniqueCode() %></a>
	</b>
<%
	}
}
%>

 

<%
if(accessLevel == GlobalConstants.END_USR_LEVEL)
{
		UserDTO user = (UserDTO)session.getAttribute(SESSION_KEYS.USER);
		GroupDTO group = (GroupDTO)session.getAttribute(SESSION_KEYS.GROUP);
%>
	<b><a href="userAction.csmp?method=promptUserHomeUserLevel&componentId=<%= user.getComponentId() %>"><%= user.getUniqueCode() %></a></b>
 <%
 }
%>

</td></tr></tbody></table>
