<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.report.dto.ReportInputDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>

<style type="text/css" rel="stylesheet">
			
			.hidden {			  	
			  	visibility: hidden;       			
			} 	
		</style>
<script type="text/javascript">
	function isCodeModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareCodeModifyAction(actionName)
	{
		if(document.getElementById('reportForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('reportForm').action;			
		if(actionName == 'ok')
		{
			path = 'reportDownloadAction.csmp';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelOperation';
		}
		
		//alert('path val: '+ path);
		var tr = document.getElementById('busypane');	
	    tr.style.display = '' ;
	
		document.getElementById('reportForm').action = path;
		document.getElementById('reportForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<%
String reportId = request.getParameter("reportId");
System.out.println("reportId:::::::::::::::::::::::::::::::::::::::::"+reportId);
%>
<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=report.home.screenname&screenTipTextKey=report.home.tiptext"></jsp:include>

<html:form styleId="reportForm"  action="reportDownloadAction.csm?method=" method="POST" onsubmit="return isCodeModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
		 <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareCodeModifyAction&methodParams=ok,cancel"></jsp:include>
			
			<tr height='20'>
			   <td id='busypane' colspan="100" align='center' style='display:none'> 
			   <!--<html:img src="Images/busy.gif" width="30" height="30" />&nbsp; -->
			    <b>
			    <font face="Verdana" color="#0000FF" size="2">Please Wait while generating the report. It may take few minutes.....</font><font face="Verdana" color="#008000" size="4">.</font></b>
			   </td>
			</tr>	
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Report Home Page
					</legend>
					<table><tbody>
			
                   <%--
				   <tr>
						<td>
						  <label class="desc"> Select Report Type</label>
						</td>
						<td>
							   <html:radio property="report.catagory" value="1" checked="checked" /> CL Report   &nbsp;
						   	   <html:radio property="report.catagory" value="2"/> CIB Report  &nbsp;
	 						   <html:radio property="report.catagory" value="3"/>SBS Report   &nbsp;   							
						</td>
					</tr> 
				    
				  	<tr>
						<td>
						  <label class="desc">Select Month</label>
						</td>
						<td>								
							<html:select property="report.month" >
								<html:option value="-1">---Select---</html:option>														
								<html:options collection="<%=SESSION_KEYS.MONTH_LIST%>" property="value" labelProperty="view"/>
							</html:select>
						</td>						
					</tr>
					--%>
					<tr>
						<td>
						  <label class="desc">Select Year</label>
						</td>
						<td>								
							<html:select property="report.year" >
								<html:option value="-1">---Select---</html:option>														
								<html:options collection="<%=SESSION_KEYS.YEAR_LIST%>" property="value" labelProperty="view"/>
							</html:select>
						</td>						
					</tr>	
					<%--
				   <tr>
						<td><label class="desc"> Select Report</label></td>
						<td>								
							<html:select property="report.reportId" >
								<html:option value="-1">---Select---</html:option>														
								<html:options collection="<%=SESSION_KEYS.REPORT_ID_LIST%>" property="value" labelProperty="view"/>
							</html:select>
						</td>
					</tr>	
					
				   <tr>
						<td><label class="desc"> Select Group</label></td>
						<td>								
							<html:select property="report.group" >
								<html:option value="ClickDiagnostics mHealth Platform">ClickDiagnostics mHealth Platform</html:option>
								<html:option value="HSBO">HSBO</html:option>																															
							</html:select>
						</td>
					</tr>														
					--%>
					<html:text styleClass="hidden" property="report.reportId" value="<%=reportId%>"/>
				
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
		
		<tr height='10'><td colspan="100"> &nbsp;</td></tr>	
					
		<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareCodeModifyAction&methodParams=ok,cancel"></jsp:include>
	
		</tbody> 
	</table>	
</html:form>
