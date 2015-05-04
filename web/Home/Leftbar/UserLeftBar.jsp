
<%@ include file="/Common/Include/taglib.jsp"%>
<link rel="stylesheet" type="text/css" href="./Common/Styles/common-styles.css">

<%@ page import="java.util.Set"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.common.ApplicationConstants"%>
<%@ page import="com.swiftcorp.portal.common.dto.FunctionDTO"%>
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%>
<%@ page import="com.swiftcorp.portal.user.dto.UserDTO"%>
<%@ page import="com.swiftcorp.portal.common.login.dto.LoginDetailInfoDTO"%>


<table align='center' border="0" width="95%">
<tr height='10'>
  <td >&nbsp;</td>
</tr>	
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

if ( functionDTOList != null && functionDTOList.size() != 0 )
{
	//out.println("Function dto list size is "+functionDTOList.size());
	for ( FunctionDTO functionDTO : functionDTOList )
	{
		String functionName = functionDTO.getFunctionName ();
		int functionId = functionDTO.getFunctionId();
		System.out.println("functionName::"+functionName+" functionId::"+functionDTO.getFunctionId());	
		/*if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_LISTS)
		{*/
		%>
			<!--
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="userAction.csmp?method=promptUserSearchSystemLevel"><%//=add_label%>User</a></b></font></td>
			</tr>
			-->
		<%
		/*
		}
		else*/ if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_ROLE_FUNCTION)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="roleFunctionAction.csmp?method=getFunctions"><%=add_label%>Role Function</a></b></font></td>
			</tr>
		<%
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_QUESTION)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="questionAction.csmp?method=promptQuestionSearchSystemLevel"><%=add_label%>Question</a></b></font></td>
			</tr>
		<%
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_QUESTIONNAIRE)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="questionnaireAction.csmp?method=promptQuestionnaireSearchSystemLevel"><%=add_label%>Questionnaire</a></b></font></td>
			</tr>
		<%
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_BENEFICIARY)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="beneficiaryAction.csmp?method=showBeneficiary"><%=add_label%>Beneficiary</a></b></font></td>
			</tr>
		<%
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_ALERTS)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="alertAction.csmp?method=promptAlertSearchSystemLevel"><%=add_label%>Alert</a></b></font></td>
			</tr>
		<%
		}	
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_WORK_SCHEDULE)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="scheduleAction.csmp?method=promptScheduleSearchSystemLevel"><%=add_label%>Schedule</a></b></font></td>
			</tr>
		<%
		}	
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_VIEW_HOUSEHOLD)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="householdAction.csmp?method=promptHouseholdSearchSystemLevel"><%=add_label%>House Hold</a></b></font></td>
			</tr>
		<%
		}	
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_REPORT_MOTHER)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="reportAction.csmp?method=promptInputReport&reportId=1"><%=add_label%>Mother Death Report</a></b></font></td>
			</tr>
		<%
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_REPORT_CHILD)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="reportAction.csmp?method=promptInputReport&reportId=2"><%=add_label%>Child Death Report</a></b></font></td>
			</tr>
		<%
		}	
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_REPORT_MPR)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="reportAction.csmp?method=promptInputReport&reportId=3"><%=add_label%>MPR Report</a></b></font></td>
			</tr>
		<%
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_ALGORITHM)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="algorithmAction.csmp?method=promptAlgorithmSearchSystemLevel"><%=add_label%>Algorithm</a></b></font></td>
			</tr>
		<%
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_GEOGRAPHICAL_INFO)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="geoAction.csmp?method=promptImportGeo">Import Geographical Information</a></b></font></td>
			</tr>
		<%
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_GEOGRAPHICAL_INFO_VIEW)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="geoAction.csmp?method=promptGeoSearchSystemLevel"><%=add_label%>Geographical Information</a></b></font></td>
			</tr>
		<%
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_DCR_INFO)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="dcrinfoAction.csmp?method=promptDcrinfoSearchSystemLevel"><%=add_label%>DCR Info</a></b></font></td>
			</tr>
		<%
		}
		else if(functionId == ApplicationConstants.ROLE_FUNCTION_DCR_REPORT)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="dcrreportAction.csmp?method=promptDcrreportSearchSystemLevel"><%=add_label%>DCR Report</a></b></font></td>
			</tr>
		<%
		}
		
		else if(functionId == 1)
		{
		%>
			<tr>
				<td class='bottom_border'>&nbsp;<font size="2"><b><a href="dcrinfoAction.csmp?method=promptImport">Import CSV File</a></b></font></td>
			</tr>
		<%
		}
	}
}

%>			
</table>
           
   

   