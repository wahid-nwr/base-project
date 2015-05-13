<%@page import="java.util.Set"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%>
<%@ page import="com.swiftcorp.portal.common.dto.FunctionDTO"%>
<%
Set<FunctionDTO> functionList = (Set<FunctionDTO>)request.getAttribute("functionsSet");

String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
xml += "<ItemSearchResponse xmlns=\"http://webservices.amazon.com/AWSECommerceService/2006-06-28\">";
xml += "<Items>";
if(functionList!=null)
{
	
	for(FunctionDTO functionDTO:functionList)
	{
		//xml += "<Item id=\""+functionDTO.getComponentId()+"\">";
		xml += "<Item>";
		xml += "<id>"+functionDTO.getComponentId()+"</id>";
		xml += "<rolefunctionname>"+functionDTO.getDisplayName()+"</rolefunctionname>";
		
		xml += "</Item>";
	}
}

xml += "</Items>";
xml += "</ItemSearchResponse>";
response.setContentType("text/xml;charset=ISO-8859-1");
PrintWriter xmlout = response.getWriter();
System.out.println(xml);
xmlout.println(xml);
%>