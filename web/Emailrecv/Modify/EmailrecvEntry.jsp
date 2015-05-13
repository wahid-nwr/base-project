<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.emailrecv.dto.EmailrecvDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isEmailrecvModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareEmailrecvModifyAction(actionName)
	{
		if(document.getElementById('emailrecvForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('emailrecvForm').action;			
		if(actionName == 'add')
		{
			path = 'emailrecvActionWithValidation.csmp?method=addEmailrecv';
		}
		else if(actionName == 'modify')
		{
			path = 'emailrecvActionWithValidation.csmp?method=modifyEmailrecv';
		}
		else if(actionName == 'remove')
		{
			path += 'removeEmailrecv';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelEmailrecvOperation';
		}
		document.getElementById('emailrecvForm').action = path;
		document.getElementById('emailrecvForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emailrecv.add.screenname&screenTipTextKey=emailrecv.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emailrecv.modify.screenname&screenTipTextKey=emailrecv.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="emailrecvForm"  action="emailrecvAction.cms?method=" method="POST" onsubmit="return isEmailrecvModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailrecvModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailrecvModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of emailrecv -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.emailrecv.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="emailrecv.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of emailrecv -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.emailrecv.description" /></label>
						</td>
						<td>
							<html:text property="emailrecv.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  companyEmailAddress of emailrecv -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecv.companyEmailAddress" /></label>
						</td>
						<td>
							<html:text property="emailrecv.companyEmailAddress" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  popHost of emailrecv -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecv.popHost" /></label>
						</td>
						<td>
							<html:text property="emailrecv.popHost" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  otherPartyEmailAddress of emailrecv -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecv.otherPartyEmailAddress" /></label>
						</td>
						<td>
							<html:text property="emailrecv.otherPartyEmailAddress" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  isSSL of emailrecv -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecv.isSSL" /></label>
						</td>
						<td>
							<html:text property="emailrecv.isSSL" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  popPort of emailrecv -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecv.popPort" /></label>
						</td>
						<td>
							<html:text property="emailrecv.popPort" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  password of emailrecv -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecv.password" /></label>
						</td>
						<td>
							<html:text property="emailrecv.password" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  taggedUserId of emailrecv -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecv.taggedUserId" /></label>
						</td>
						<td>
							<html:text property="emailrecv.taggedUserId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  smtpHost of emailrecv -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecv.smtpHost" /></label>
						</td>
						<td>
							<html:text property="emailrecv.smtpHost" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  smtpPort of emailrecv -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecv.smtpPort" /></label>
						</td>
						<td>
							<html:text property="emailrecv.smtpPort" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  displayName of emailrecv -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailrecv.displayName" /></label>
						</td>
						<td>
							<html:text property="emailrecv.displayName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailrecvModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailrecvModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
