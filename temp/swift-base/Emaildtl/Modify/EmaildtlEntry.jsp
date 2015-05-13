<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.emaildtl.dto.EmaildtlDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isEmaildtlModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareEmaildtlModifyAction(actionName)
	{
		if(document.getElementById('emaildtlForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('emaildtlForm').action;			
		if(actionName == 'add')
		{
			path = 'emaildtlActionWithValidation.csmp?method=addEmaildtl';
		}
		else if(actionName == 'modify')
		{
			path = 'emaildtlActionWithValidation.csmp?method=modifyEmaildtl';
		}
		else if(actionName == 'remove')
		{
			path += 'removeEmaildtl';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelEmaildtlOperation';
		}
		document.getElementById('emaildtlForm').action = path;
		document.getElementById('emaildtlForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emaildtl.add.screenname&screenTipTextKey=emaildtl.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emaildtl.modify.screenname&screenTipTextKey=emaildtl.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="emaildtlForm"  action="emaildtlAction.cms?method=" method="POST" onsubmit="return isEmaildtlModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmaildtlModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmaildtlModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of emaildtl -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.emaildtl.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="emaildtl.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of emaildtl -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.emaildtl.description" /></label>
						</td>
						<td>
							<html:text property="emaildtl.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  emailId of emaildtl -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emaildtl.emailId" /></label>
						</td>
						<td>
							<html:text property="emaildtl.emailId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  contentType of emaildtl -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emaildtl.contentType" /></label>
						</td>
						<td>
							<html:text property="emaildtl.contentType" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  content of emaildtl -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emaildtl.content" /></label>
						</td>
						<td>
							<html:text property="emaildtl.content" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmaildtlModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmaildtlModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
