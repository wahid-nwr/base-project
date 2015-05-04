<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.schedule.dto.ScheduleDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isScheduleModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareScheduleModifyAction(actionName)
	{
		if(document.getElementById('scheduleForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('scheduleForm').action;			
		if(actionName == 'add')
		{
			path = 'scheduleActionWithValidation.csmp?method=addSchedule';
		}
		else if(actionName == 'modify')
		{
			path = 'scheduleActionWithValidation.csmp?method=modifySchedule';
		}
		else if(actionName == 'remove')
		{
			path += 'removeSchedule';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelScheduleOperation';
		}
		document.getElementById('scheduleForm').action = path;
		document.getElementById('scheduleForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=schedule.add.screenname&screenTipTextKey=schedule.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=schedule.modify.screenname&screenTipTextKey=schedule.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="scheduleForm"  action="scheduleAction.cms?method=" method="POST" onsubmit="return isScheduleModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareScheduleModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareScheduleModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
					<!--  uniqueCode of schedule -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.schedule.visitItemId" /></label>
						</td>
						<td>
							<html:text property="schedule.visitItemId" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					<!--  uniqueCode of schedule -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.schedule.user" /></label>
						</td>
						<td>
							<html:text property="schedule.user.componentId" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  uniqueCode of schedule -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.schedule.scheduleby" /></label>
						</td>
						<td>
							<html:text property="schedule.scheduleBy" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>					
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareScheduleModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareScheduleModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
