<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.feature.FeatureAccessUtils"%>

<% 
	String 	buttons = request.getParameter("buttons");
	String 	methodName = request.getParameter("methodName");
	String 	methodParams = request.getParameter("methodParams");
	if(buttons == null || methodName == null || methodParams==null) 
	{
		return;
	}
	
	String[] buttonsList = buttons.split(",");
	String[] methodParamsList = methodParams.split(",");	
	if(buttonsList == null || methodParamsList == null) 
	{
		return;
	}
	
%>

<tr class="buttonbar">
	<td colspan="100">
<%
	for(int index = 0; index < buttonsList.length ; index++)
	{
		String button = buttonsList[index];
		String methodParam = methodParamsList[index];
		String onclickAction = methodName + "('"  + methodParam +"');";
				
	   // this is authorization code portion
		int authCode = FeatureAccessUtils.authorizationLevel(request) ;
		
		String buttonLabel = null;		
		if("ok".equalsIgnoreCase(button))
		{
	          buttonLabel = "   Ok   ";
		}
		else if("add".equalsIgnoreCase(button))
		{
			   buttonLabel = "   Add   ";			
		}		
		else if("remove".equalsIgnoreCase(button))
		{
 			buttonLabel = "Remove";
		}
		else if("cancel".equalsIgnoreCase(button))
		{
			buttonLabel = "Cancel";
		}
		else if("import".equalsIgnoreCase(button))
		{
			buttonLabel = "Import";
		}
		else if("modify".equalsIgnoreCase(button))
		{
			buttonLabel = "Modify";
		}
		else if("downloaderror".equalsIgnoreCase(button))
		{
			buttonLabel = "Download Error Records";
		}						
		else if("process".equalsIgnoreCase(button))
		{
			buttonLabel = "Process";
		}
		
		if(buttonLabel == null)
		{
		  continue ;
		}
%>
		 &nbsp;<input type="button" value="<%= buttonLabel %>" onclick="<%= onclickAction %>" property="<%= methodParam %>" />
<%
	} // end loop
%>

	</td>
</tr>