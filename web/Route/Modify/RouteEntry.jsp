<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.route.dto.RouteDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isRouteModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareRouteModifyAction(actionName)
	{
		if(document.getElementById('routeForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('routeForm').action;			
		if(actionName == 'add')
		{
			path = 'routeActionWithValidation.csmp?method=addRoute';
		}
		else if(actionName == 'modify')
		{
			path = 'routeActionWithValidation.csmp?method=modifyRoute';
		}
		else if(actionName == 'remove')
		{
			path += 'removeRoute';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelRouteOperation';
		}
		document.getElementById('routeForm').action = path;
		document.getElementById('routeForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=route.add.screenname&screenTipTextKey=route.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=route.modify.screenname&screenTipTextKey=route.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="routeForm"  action="routeAction.cms?method=" method="POST" onsubmit="return isRouteModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRouteModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRouteModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of route -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.route.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="route.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of route -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.route.description" /></label>
						</td>
						<td>
							<html:text property="route.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  startPoint of route -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.route.startPoint" /></label>
						</td>
						<td>
							<html:text property="route.startPoint" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  destination of route -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.route.destination" /></label>
						</td>
						<td>
							<html:text property="route.destination" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  distance of route -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.route.distance" /></label>
						</td>
						<td>
							<html:text property="route.distance" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRouteModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRouteModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
