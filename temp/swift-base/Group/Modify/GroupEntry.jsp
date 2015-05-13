<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
	String cancelPath = "groupAction.csmp?method=cancelGroupOperation" ;
%>
<script type="text/javascript">
	function isGroupModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareGroupModifyAction(actionName)
	{
		if(document.getElementById('groupForm') == null)
		{
			return;
		}
			
		var path = document.getElementById('groupForm').action;
		var cancelPath = '<%=cancelPath%>' ;
		
			
		if(actionName == 'add')
		{
			path = 'groupActionWithValidation.csmp?method=addGroup';
		}
		else if(actionName == 'modify')
		{
			path = 'groupActionWithValidation.csmp?method=modifyGroup';
		}
		else if(actionName == 'remove')
		{
			path += 'removeGroup';
		}
		else if(actionName == 'cancel')
		{
			path = cancelPath;
		}
		document.getElementById('groupForm').action = path;
		document.getElementById('groupForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/GroupScreenHeader.jsp?screenNameKey=group.add.screenname&screenTipTextKey=group.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/GroupScreenHeader.jsp?screenNameKey=group.modify.screenname&screenTipTextKey=group.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="groupForm"  action="groupAction.cms?method=" method="POST" onsubmit="return isGroupModifyValidSubmit()" target="_self">
	<table width='100%' class="AddModifyForm">
		<tbody>
		
		  	<input type="hidden" name="featureId" id="featureId" value="101">
		  	
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareGroupModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareGroupModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
				<tr><td>						
					 <table width='60%'>

				 <!--   group -->
					<tr><td colspan="2">		
						<fieldset>
							<legend>
								Basic Information
							</legend>
							 <table><tbody>

					 <!--  groupId of group -->
					<tr>
						<td width='120'>
						  <font color="red">*</font>&nbsp; <label class="desc"><bean:message key="label.group.uniqueCode" /></label>
						</td>
						<td  align='left'>
							<html:text property="group.uniqueCode" styleClass="SingleLineTextField" size="50" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  groupName of group -->
					<tr>
						<td>
						  <font color="red">*</font> &nbsp;<label class="desc"><bean:message key="label.group.description" /></label>
						</td>
						<td>
							<html:text property="group.description" styleClass="SingleLineTextField" size="50" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					
					</tbody></table>
				</fieldset>
			</td></tr>					

					
					</td></tr>						
				</table>	
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareGroupModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareGroupModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
