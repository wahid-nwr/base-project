<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.ajan.dto.AjanDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isAjanModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareAjanModifyAction(actionName)
	{
		if(document.getElementById('ajanForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('ajanForm').action;			
		if(actionName == 'add')
		{
			path = 'ajanActionWithValidation.csmp?method=addAjan';
		}
		else if(actionName == 'modify')
		{
			path = 'ajanActionWithValidation.csmp?method=modifyAjan';
		}
		else if(actionName == 'remove')
		{
			path += 'removeAjan';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelAjanOperation';
		}
		document.getElementById('ajanForm').action = path;
		document.getElementById('ajanForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=ajan.add.screenname&screenTipTextKey=ajan.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=ajan.modify.screenname&screenTipTextKey=ajan.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="ajanForm"  action="ajanAction.cms?method=" method="POST" onsubmit="return isAjanModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAjanModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAjanModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of ajan -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.ajan.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="ajan.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of ajan -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.ajan.description" /></label>
						</td>
						<td>
							<html:text property="ajan.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  ajanId of ajan -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.ajan.ajanId" /></label>
						</td>
						<td>
							<html:text property="ajan.ajanId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  ajanName of ajan -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.ajan.ajanName" /></label>
						</td>
						<td>
							<html:text property="ajan.ajanName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAjanModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAjanModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
