<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.dcrreport.dto.DcrReportDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript" src="./DatePicker/datetimepicker_css.js"></script>
<style type="text/css">
.bottomtext img
{
  border:none;
}
A{text-decoration: none;} 
A:link
{
	color: #3399cc;
}
A:visited{color: #3399cc;}
A:hover
{
	color: #cc0033;
}
A img
{
  border:none;
}
</style>

<script type="text/javascript">
	function isDcrreportModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareDcrreportModifyAction(actionName)
	{
		if(document.getElementById('dcrreportForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('dcrreportForm').action;			
		if(actionName == 'add')
		{
			path = 'dcrreportActionWithValidation.csmp?method=addDcrreport';
		}
		else if(actionName == 'modify')
		{
			path = 'dcrreportActionWithValidation.csmp?method=modifyDcrreport';
		}
		else if(actionName == 'remove')
		{
			path += 'removeDcrreport';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelDcrreportOperation';
		}
		document.getElementById('dcrreportForm').action = path;
		document.getElementById('dcrreportForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=dcrreport.add.screenname&screenTipTextKey=dcrreport.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=dcrreport.modify.screenname&screenTipTextKey=dcrreport.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="dcrreportForm"  action="dcrreportAction.cms?method=" method="POST" onsubmit="return isDcrreportModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareDcrreportModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareDcrreportModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of dcrreport -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.dcrreport.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="dcrreport.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of dcrreport -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.dcrreport.description" /></label>
						</td>
						<td>
							<html:text property="dcrreport.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc">Month</label>
						</td>
						<td>
							<html:text property="dcrreport.month" styleId="month" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
							<a href="javascript: NewCssCal('month','yyyymmdd','dropdown',true)">
	       						<img src="./DatePicker/images/cal.gif" width="16" height="16" alt="Pick a date">
	       					</a>
						</td>
					</tr>
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc">Target</label>
						</td>
						<td>
							<html:text property="dcrreport.target"  styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
							
						</td>
					</tr>
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc">Achievement</label>
						</td>
						<td>
							<html:text property="dcrreport.achievement" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					
										
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareDcrreportModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareDcrreportModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
