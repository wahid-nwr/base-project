<%@ page import="java.util.Set"%>
<%@ page import="java.io.*" %>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.common.ApplicationConstants"%>
<%@ page import="com.swiftcorp.portal.common.dto.FunctionDTO"%>
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%>
<%@ page import="com.swiftcorp.portal.user.dto.UserDTO"%>
<%@ page import="com.swiftcorp.portal.common.login.dto.LoginDetailInfoDTO"%>
<%

LoginDetailInfoDTO loginInfo = (LoginDetailInfoDTO)request.getSession ().getAttribute ( SESSION_KEYS.LOGIN_INFO );
UserDTO user = (UserDTO) loginInfo.getUser ();
RoleDTO roleDTO = user.getRole ();
long roleId = roleDTO.getComponentId ();
String add_label = "";
if(roleId == ApplicationConstants.ROLE_SYSTEM_ADMIN)
{
	add_label = "Manage ";
}

Set<FunctionDTO> functionDTOList = (Set<FunctionDTO>)session.getAttribute(SESSION_KEYS.FUNCTIONDTO_SET);
String result = "[{"
	+"\"id\":\"apidocs\","
	+"\"iconCls\":\"icon-docs\","
	+"\"text\":\"Demo Application Root\","
	+"\"singleClickExpand\":true,"
	+"\"children\":[";
	                
String children = "";
if ( functionDTOList != null && functionDTOList.size() != 0 )
{
	//out.println("Function dto list size is "+functionDTOList.size());
	for ( FunctionDTO functionDTO : functionDTOList )
	{
		String functionName = functionDTO.getFunctionName ();
		int functionId = functionDTO.getFunctionId();
		System.out.println("functionName::"+functionName+" functionId::"+functionDTO.getFunctionId());	
		
		if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_ROLE_FUNCTION)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Role Functions\",\"id\":\"rolePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Role Functions\",\"id\":\"rolePanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_USERS)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"User\",\"id\":\"userPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"User\",\"id\":\"userPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		if(functionId == ApplicationConstants.ROLE_FUNCTION_INFO)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Info\",\"id\":\"infoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Info\",\"id\":\"infoPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		if(functionId == ApplicationConstants.ROLE_FUNCTION_AJAN)
		{
			if(children!=null && !children.equals("null") && children.length()>0)
			{
				children += ",{\"text\":\"Ajan\",\"id\":\"ajanPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
			else
			{
				children = "{\"text\":\"Ajan\",\"id\":\"ajanPanel\",\"isClass\":true,\"iconCls\":\"icon-static\",\"cls\":\"cls\",\"leaf\":true}";
			}
		}
		//add new functions here
	}
}
result += children;
result += "]}]";
response.setContentType("text/javascript");
PrintWriter xmlout = response.getWriter();
xmlout.println(result);
System.out.println("result::"+result);

%>
