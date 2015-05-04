<%@ include file="/Common/Include/taglib.jsp"%>

<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%> 
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%> 

<%

 String uniqueCode = WebUtils.getUniqueCode(request);
 RoleDTO role = WebUtils.getUserRole(request);
%>

<div id="header">
	<a href="http://extjs.com" style="float:right;margin-right:10px;"><img src="resources/logo.gif" style="width:83px;height:24px;margin-top:1px;"/></a>

	<div class="api-title">Micro Banker Demo 1.0</div>
	
	<span> 
	        <span>   
	        <span>
		        <div width="330">					    
						<span>|</span>
						<span>Welcome</span> 
						<span><%=uniqueCode%></span>
						<span>[<%=role.getUniqueCode()%>]</span>
						<span>|</span>
						<span><b><a href="loginAction.csmp?method=logout">Logout</b></a></span>					
				</div>
			</span>
  </div>
  
     
	        
	

 

 