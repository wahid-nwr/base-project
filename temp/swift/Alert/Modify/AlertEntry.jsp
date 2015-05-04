<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.alert.dto.AlertDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isAlertModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareAlertModifyAction(actionName)
	{
		if(document.getElementById('alertForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('alertForm').action;			
		if(actionName == 'add')
		{
			path = 'alertActionWithValidation.csmp?method=addAlert';
		}
		else if(actionName == 'modify')
		{
			path = 'alertActionWithValidation.csmp?method=modifyAlert';
		}
		else if(actionName == 'remove')
		{
			path += 'removeAlert';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelAlertOperation';
		}
		document.getElementById('alertForm').action = path;
		document.getElementById('alertForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=alert.add.screenname&screenTipTextKey=alert.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=alert.modify.screenname&screenTipTextKey=alert.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="alertForm"  action="alertAction.cms?method=" method="POST" onsubmit="return isAlertModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlertModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlertModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of alert -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.alert.alertId" /></label>
						</td>
						<td>
							<html:text property="alert.alertId" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of alert -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.alert.description" /></label>
						</td>
						<td>
							<html:text property="alert.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>								


					 <!--  name of alert -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.alert.name" /></label>
						</td>
						<td>
							<html:text property="alert.name" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					<!--  header of alert -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.alert.header" /></label>
						</td>
						<td>
							<html:text property="alert.header" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					<!--  body of alert -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.alert.body" /></label>
						</td>
						<td>
							<html:text property="alert.body" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlertModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlertModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
