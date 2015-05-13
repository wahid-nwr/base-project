<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.emailrecipients.dto.EmailrecipientsDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isEmailrecipientsModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareEmailrecipientsModifyAction(actionName)
	{
		if(document.getElementById('emailrecipientsForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('emailrecipientsForm').action;			
		if(actionName == 'add')
		{
			path = 'emailrecipientsActionWithValidation.csmp?method=addEmailrecipients';
		}
		else if(actionName == 'modify')
		{
			path = 'emailrecipientsActionWithValidation.csmp?method=modifyEmailrecipients';
		}
		else if(actionName == 'remove')
		{
			path += 'removeEmailrecipients';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelEmailrecipientsOperation';
		}
		document.getElementById('emailrecipientsForm').action = path;
		document.getElementById('emailrecipientsForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emailrecipients.add.screenname&screenTipTextKey=emailrecipients.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emailrecipients.modify.screenname&screenTipTextKey=emailrecipients.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="emailrecipientsForm"  action="emailrecipientsAction.cms?method=" method="POST" onsubmit="return isEmailrecipientsModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailrecipientsModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailrecipientsModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of emailrecipients -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.emailrecipients.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="emailrecipients.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of emailrecipients -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.emailrecipients.description" /></label>
						</td>
						<td>
							<html:text property="emailrecipients.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  companyEmailAddress of emailrecipients -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecipients.companyEmailAddress" /></label>
						</td>
						<td>
							<html:text property="emailrecipients.companyEmailAddress" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  messageId of emailrecipients -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecipients.messageId" /></label>
						</td>
						<td>
							<html:text property="emailrecipients.messageId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  contactId of emailrecipients -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecipients.contactId" /></label>
						</td>
						<td>
							<html:text property="emailrecipients.contactId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  recipientsType of emailrecipients -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecipients.recipientsType" /></label>
						</td>
						<td>
							<html:text property="emailrecipients.recipientsType" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  emailStatus of emailrecipients -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecipients.emailStatus" /></label>
						</td>
						<td>
							<html:text property="emailrecipients.emailStatus" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  emailRecvId of emailrecipients -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecipients.emailRecvId" /></label>
						</td>
						<td>
							<html:text property="emailrecipients.emailRecvId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  messageType of emailrecipients -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecipients.messageType" /></label>
						</td>
						<td>
							<html:text property="emailrecipients.messageType" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailrecipientsModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailrecipientsModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
