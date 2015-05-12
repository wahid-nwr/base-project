<%@ page import="java.util.List"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%>
<%
List<RoleDTO> roleList = (List<RoleDTO>)request.getAttribute("roleList");
String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
xml += "<response>";
xml += "<dataset>";

if(roleList!=null)
{
	xml += "<itemCount>"+roleList.size()+"</itemCount>";
	for(RoleDTO roleDTO:roleList)
	{
		xml += "<Item id=\""+roleDTO.getComponentId()+"\">";
		xml += "<id>"+roleDTO.getComponentId()+"</id>";
		xml += "<description>"+roleDTO.getDescription()+"</description>";
		xml += "<roleName>"+roleDTO.getUniqueCode()+"</roleName>";
		xml += "</Item>";
	}
}
xml += "</dataset>";
xml += "</response>";
response.setContentType("text/xml;charset=ISO-8859-1");
PrintWriter xmlout = response.getWriter();
System.out.println(xml);
xmlout.println(xml);
%>