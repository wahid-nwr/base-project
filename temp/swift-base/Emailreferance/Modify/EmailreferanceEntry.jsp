<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.emailreferance.dto.EmailreferanceDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isEmailreferanceModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareEmailreferanceModifyAction(actionName)
	{
		if(document.getElementById('emailreferanceForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('emailreferanceForm').action;			
		if(actionName == 'add')
		{
			path = 'emailreferanceActionWithValidation.csmp?method=addEmailreferance';
		}
		else if(actionName == 'modify')
		{
			path = 'emailreferanceActionWithValidation.csmp?method=modifyEmailreferance';
		}
		else if(actionName == 'remove')
		{
			path += 'removeEmailreferance';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelEmailreferanceOperation';
		}
		document.getElementById('emailreferanceForm').action = path;
		document.getElementById('emailreferanceForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emailreferance.add.screenname&screenTipTextKey=emailreferance.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emailreferance.modify.screenname&screenTipTextKey=emailreferance.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="emailreferanceForm"  action="emailreferanceAction.cms?method=" method="POST" onsubmit="return isEmailreferanceModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailreferanceModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailreferanceModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of emailreferance -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.emailreferance.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="emailreferance.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of emailreferance -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.emailreferance.description" /></label>
						</td>
						<td>
							<html:text property="emailreferance.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  companyEmailAddress of emailreferance -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailreferance.companyEmailAddress" /></label>
						</td>
						<td>
							<html:text property="emailreferance.companyEmailAddress" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  refDtl of emailreferance -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailreferance.refDtl" /></label>
						</td>
						<td>
							<html:text property="emailreferance.refDtl" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  emailGroupId of emailreferance -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.emailreferance.emailGroupId" /></label>
						</td>
						<td>
							<html:text property="emailreferance.emailGroupId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailreferanceModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareEmailreferanceModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
