<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.risk.dto.RiskDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isRiskModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareRiskModifyAction(actionName)
	{
		if(document.getElementById('riskForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('riskForm').action;			
		if(actionName == 'add')
		{
			path = 'riskActionWithValidation.csmp?method=addRisk';
		}
		else if(actionName == 'modify')
		{
			path = 'riskActionWithValidation.csmp?method=modifyRisk';
		}
		else if(actionName == 'remove')
		{
			path += 'removeRisk';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelRiskOperation';
		}
		document.getElementById('riskForm').action = path;
		document.getElementById('riskForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=risk.add.screenname&screenTipTextKey=risk.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=risk.modify.screenname&screenTipTextKey=risk.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="riskForm"  action="riskAction.cms?method=" method="POST" onsubmit="return isRiskModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRiskModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRiskModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of risk -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.risk.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="risk.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of risk -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.risk.description" /></label>
						</td>
						<td>
							<html:text property="risk.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  code of risk -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.risk.code" /></label>
						</td>
						<td>
							<html:text property="risk.code" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  name of risk -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.risk.name" /></label>
						</td>
						<td>
							<html:text property="risk.name" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRiskModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRiskModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
