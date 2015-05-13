<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.home.dto.HomeDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isHomeModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareHomeModifyAction(actionName)
	{
		if(document.getElementById('homeForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('homeForm').action;			
		if(actionName == 'add')
		{
			path = 'homeActionWithValidation.csmp?method=addHome';
		}
		else if(actionName == 'modify')
		{
			path = 'homeActionWithValidation.csmp?method=modifyHome';
		}
		else if(actionName == 'remove')
		{
			path += 'removeHome';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelHomeOperation';
		}
		document.getElementById('homeForm').action = path;
		document.getElementById('homeForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=home.add.screenname&screenTipTextKey=home.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=home.modify.screenname&screenTipTextKey=home.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="homeForm"  action="homeAction.cms?method=" method="POST" onsubmit="return isHomeModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareHomeModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareHomeModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of home -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.home.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="home.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of home -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.home.description" /></label>
						</td>
						<td>
							<html:text property="home.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  homeId of home -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.home.homeId" /></label>
						</td>
						<td>
							<html:text property="home.homeId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  homeChiefName of home -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.home.homeChiefName" /></label>
						</td>
						<td>
							<html:text property="home.homeChiefName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  regDate of home -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.home.regDate" /></label>
						</td>
						<td>
							<html:text property="home.regDate" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  homeMember of home -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.home.homeMember" /></label>
						</td>
						<td>
							<html:text property="home.homeMember" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareHomeModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareHomeModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
