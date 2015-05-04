<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.account.dto.AccountDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isAccountModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareAccountModifyAction(actionName)
	{
		if(document.getElementById('accountForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('accountForm').action;			
		if(actionName == 'add')
		{
			path = 'accountActionWithValidation.csmp?method=addAccount';
		}
		else if(actionName == 'modify')
		{
			path = 'accountActionWithValidation.csmp?method=modifyAccount';
		}
		else if(actionName == 'remove')
		{
			path += 'removeAccount';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelAccountOperation';
		}
		document.getElementById('accountForm').action = path;
		document.getElementById('accountForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=account.add.screenname&screenTipTextKey=account.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=account.modify.screenname&screenTipTextKey=account.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="accountForm"  action="accountAction.cms?method=" method="POST" onsubmit="return isAccountModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAccountModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAccountModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of account -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.account.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="account.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of account -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.account.description" /></label>
						</td>
						<td>
							<html:text property="account.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  code of account -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.account.code" /></label>
						</td>
						<td>
							<html:text property="account.code" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  name of account -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.account.name" /></label>
						</td>
						<td>
							<html:text property="account.name" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  parent of account -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.account.parent" /></label>
						</td>
						<td>
							<html:text property="account.parent" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAccountModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAccountModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
