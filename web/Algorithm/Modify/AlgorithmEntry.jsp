<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.algorithm.dto.AlgorithmDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isAlgorithmModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareAlgorithmModifyAction(actionName)
	{
		if(document.getElementById('algorithmForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('algorithmForm').action;			
		if(actionName == 'add')
		{
			path = 'algorithmActionWithValidation.csmp?method=addAlgorithm';
		}
		else if(actionName == 'modify')
		{
			path = 'algorithmActionWithValidation.csmp?method=modifyAlgorithm';
		}
		else if(actionName == 'remove')
		{
			path += 'removeAlgorithm';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelAlgorithmOperation';
		}
		document.getElementById('algorithmForm').action = path;
		document.getElementById('algorithmForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=algorithm.add.screenname&screenTipTextKey=algorithm.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=algorithm.modify.screenname&screenTipTextKey=algorithm.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="algorithmForm"  action="algorithmAction.cms?method=" method="POST" onsubmit="return isAlgorithmModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlgorithmModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlgorithmModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of algorithm -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.algorithm.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="algorithm.algId" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  name of algorithm -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.algorithm.name" /></label>
						</td>
						<td>
							<html:text property="algorithm.name" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					 <!--  description of algorithm -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.algorithm.description" /></label>
						</td>
						<td>
							<html:text property="algorithm.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
					
					 <!--  version of algorithm -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.algorithm.version" /></label>
						</td>
						<td>
							<html:text property="algorithm.version" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					 <!--  status of algorithm -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.algorithm.status" /></label>
						</td>
						<td>
							<html:text property="algorithm.status" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
			<input type="hidden" name="forwardPage" value="/Algorithm/Modify/FirstAlgEntry.jsp"/>
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlgorithmModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlgorithmModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
