<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
	String cancelPath = "roleAction.csmp?method=cancelRoleOperation" ;
%>
<script type="text/javascript">
	function isRoleModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareRoleModifyAction(actionName)
	{
		if(document.getElementById('roleForm') == null)
		{
			return;
		}

			
		var path = document.getElementById('roleForm').action;
		var cancelPath = '<%=cancelPath%>' ;
					
		if(actionName == 'add')
		{
			path = 'roleActionWithValidation.csmp?method=addRole';
		}
		else if(actionName == 'modify')
		{
			path = 'roleActionWithValidation.csmp?method=modifyRole';
		}
		else if(actionName == 'remove')
		{
			path += 'removeRole';
		}
		else if(actionName == 'cancel')
		{
			path = cancelPath;
		}
		document.getElementById('roleForm').action = path;
		document.getElementById('roleForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=role.add.screenname&screenTipTextKey=role.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=role.modify.screenname&screenTipTextKey=role.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="roleForm"  action="roleAction.cms?method=" method="POST" onsubmit="return isRoleModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRoleModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRoleModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of role -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.role.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="role.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					
  				       <!--  accessLevel of role -->
					   <tr>
							<td><font color="red">*</font> <label class="desc"><bean:message key="label.role.accessLevel" /></label></td>
							<td>								
								<html:select property="role.accessLevel" >
									<html:option value="">---Select---</html:option>
									<html:options collection="<%=SESSION_KEYS.ACCESS_LEVEL_LIST%>" property="value" labelProperty="view"/>
								</html:select>
							</td>
						</tr> 
					
					 <!--  description of role -->
					<tr>
						<td>
						   <label class="desc"><bean:message key="label.role.description" /></label>
						</td>
						<td>
							<html:text property="role.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
											
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRoleModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRoleModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			

		</tbody> 
	</table>	
</html:form>
