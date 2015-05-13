<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.emailgroup.dto.EmailgroupDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isEmailgroupModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareEmailgroupModifyAction(actionName)
	{
		if(document.getElementById('emailgroupForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('emailgroupForm').action;			
		if(actionName == 'add')
		{
			path = 'emailgroupActionWithValidation.csmp?method=addEmailgroup';
		}
		else if(actionName == 'modify')
		{
			path = 'emailgroupActionWithValidation.csmp?method=modifyEmailgroup';
		}
		else if(actionName == 'remove')
		{
			path += 'removeEmailgroup';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelEmailgroupOperation';
		}
		document.getElementById('emailgroupForm').action = path;
		document.getElementById('emailgroupForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emailgroup.add.screenname&screenTipTextKey=emailgroup.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emailgroup.modify.screenname&screenTipTextKey=emailgroup.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="emailgroupForm"  action="emailgroupAction.cms?method=" method="POST" onsubmit="return isEmailgroupModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailgroupModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailgroupModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of emailgroup -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.emailgroup.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="emailgroup.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of emailgroup -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.emailgroup.description" /></label>
						</td>
						<td>
							<html:text property="emailgroup.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  groupName of emailgroup -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailgroup.groupName" /></label>
						</td>
						<td>
							<html:text property="emailgroup.groupName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailgroupModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailgroupModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
