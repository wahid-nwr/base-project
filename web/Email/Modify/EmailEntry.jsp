<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.email.dto.EmailDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isEmailModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareEmailModifyAction(actionName)
	{
		if(document.getElementById('emailForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('emailForm').action;			
		if(actionName == 'add')
		{
			path = 'emailActionWithValidation.csmp?method=addEmail';
		}
		else if(actionName == 'modify')
		{
			path = 'emailActionWithValidation.csmp?method=modifyEmail';
		}
		else if(actionName == 'remove')
		{
			path += 'removeEmail';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelEmailOperation';
		}
		document.getElementById('emailForm').action = path;
		document.getElementById('emailForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=email.add.screenname&screenTipTextKey=email.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=email.modify.screenname&screenTipTextKey=email.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="emailForm"  action="emailAction.cms?method=" method="POST" onsubmit="return isEmailModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of email -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.email.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="email.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of email -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.email.description" /></label>
						</td>
						<td>
							<html:text property="email.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  sender of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.sender" /></label>
						</td>
						<td>
							<html:text property="email.sender" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  sendDate of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.sendDate" /></label>
						</td>
						<td>
							<html:text property="email.sendDate" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  subject of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.subject" /></label>
						</td>
						<td>
							<html:text property="email.subject" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  type of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.type" /></label>
						</td>
						<td>
							<html:text property="email.type" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  eChecked of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.eChecked" /></label>
						</td>
						<td>
							<html:text property="email.eChecked" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  senderType of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.senderType" /></label>
						</td>
						<td>
							<html:text property="email.senderType" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  others of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.others" /></label>
						</td>
						<td>
							<html:text property="email.others" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  ossBy of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.ossBy" /></label>
						</td>
						<td>
							<html:text property="email.ossBy" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  refId of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.refId" /></label>
						</td>
						<td>
							<html:text property="email.refId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  allRecipients of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.allRecipients" /></label>
						</td>
						<td>
							<html:text property="email.allRecipients" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  cc of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.cc" /></label>
						</td>
						<td>
							<html:text property="email.cc" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  recepient of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.recepient" /></label>
						</td>
						<td>
							<html:text property="email.recepient" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  internalUserId of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.internalUserId" /></label>
						</td>
						<td>
							<html:text property="email.internalUserId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  ossStatus of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.ossStatus" /></label>
						</td>
						<td>
							<html:text property="email.ossStatus" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  priority of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.priority" /></label>
						</td>
						<td>
							<html:text property="email.priority" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  emailGroupId of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.emailGroupId" /></label>
						</td>
						<td>
							<html:text property="email.emailGroupId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  comId of email -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.email.comId" /></label>
						</td>
						<td>
							<html:text property="email.comId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
