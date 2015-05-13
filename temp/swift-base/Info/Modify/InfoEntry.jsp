<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.info.dto.InfoDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isInfoModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareInfoModifyAction(actionName)
	{
		if(document.getElementById('infoForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('infoForm').action;			
		if(actionName == 'add')
		{
			path = 'infoActionWithValidation.csmp?method=addInfo';
		}
		else if(actionName == 'modify')
		{
			path = 'infoActionWithValidation.csmp?method=modifyInfo';
		}
		else if(actionName == 'remove')
		{
			path += 'removeInfo';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelInfoOperation';
		}
		document.getElementById('infoForm').action = path;
		document.getElementById('infoForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=info.add.screenname&screenTipTextKey=info.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=info.modify.screenname&screenTipTextKey=info.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="infoForm"  action="infoAction.cms?method=" method="POST" onsubmit="return isInfoModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareInfoModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareInfoModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of info -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.info.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="info.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of info -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.info.description" /></label>
						</td>
						<td>
							<html:text property="info.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  infoId of info -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.info.infoId" /></label>
						</td>
						<td>
							<html:text property="info.infoId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  infoDetail of info -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.info.infoDetail" /></label>
						</td>
						<td>
							<html:text property="info.infoDetail" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareInfoModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareInfoModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
