<%@ include file="/Common/Include/taglib.jsp"%>

<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%>
<%@ page import="com.swiftcorp.portal.common.dto.FunctionDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>


<%
	boolean isReadOnly = false;
	String cancelPath = "roleFunctionAction.csmp?method=cancelSearchRole" ;
	List<FunctionDTO> functionList = (List<FunctionDTO>)session.getAttribute(SESSION_KEYS.FUNCTIONDTO_LIST);
	List<RoleDTO> roleList = (List<RoleDTO>)session.getAttribute(SESSION_KEYS.ROLE_LIST);
	
%>
<script type="text/javascript">
	var roleFunctionList = null; 
	var roleList = null;
	var masterArray = new Array();
	function getElementsByName_iefix(tag, name) {     
	     var elem = document.getElementsByTagName(tag);
	     var arr = new Array();
	     for(i = 0,iarr = 0; i < elem.length; i++) {
	          att = elem[i].getAttribute("name");
	          if(att == name) {
	               arr[iarr] = elem[i];
	               iarr++;
	          }
	     }
	     return arr;
	}
	function selectFunctions(roleId)
	{
		var ckBox = getElementsByName_iefix("input", "checkgroup");
		for(var i=0;i<ckBox.length;i++)
		{
			ckBox[i].checked=false;
		}
		if(roleId!='-1')
		{
			for(var i=0;i<masterArray.length;i++)
			{	
				var role = masterArray[i];
				if(role[0]==roleId)
				{
					var roleFunctions = role[1];
					for(var j=0;j<roleFunctions.length;j++)
					{
						document.getElementById('roleFunction'+roleFunctions[j]).checked=true;						
					}
				}
			}
		}
	}
	function isRoleModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareRoleModifyAction(actionName)
	{
		//document.getElementById("message").innerHTML= "Function assigned";
		
		if(document.getElementById('roleFunctionForm') == null)
		{
			return;
		}
		document.roleFunctionForm.checkedIndex.value = "";
		for (i=0; i<document.roleFunctionForm.checkgroup.length; i++)
		{
			if (document.roleFunctionForm.checkgroup[i].checked==true)
			{
				document.roleFunctionForm.checkedIndex.value += i + ',';
				
			}
			
		}
		
		var path = document.getElementById('roleFunctionForm').action;
		var cancelPath = '<%=cancelPath%>' ;
		if(validate(actionName))
		{			
			if(actionName == 'add')
			{		
				path = 'roleFunctionAction.csmp?method=addRoleFunctions';
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
				path = cancelPath ;
			}
			document.getElementById('roleFunctionForm').action = path;
		
			document.getElementById('roleFunctionForm').submit();
		}
		
	}
	function validate (actionName){
		if(actionName=='add')
		{
			var role = document.getElementById('role').value;
			if(role=='-1')
			{
				alert('Please select a role');
				return false;
			}
			var ckBox = getElementsByName_iefix("input", "checkgroup");
			var chkarray = new Array();
			for(var i=0;i<ckBox.length;i++)
			{			
				//document.getElementById('roleFunction'+roleFunctions[j]).checked=true;
				if(ckBox[i].checked)
				{
					chkarray.push(ckBox[i]);
				}						
			}	
			if(chkarray.length==0)
			{
				//alert('Yes','No','You did not select any function for this role, are you sure to save?');
				if (confirm("You did not select any function for this role, are you sure to save?")) {
					return true;
				} else {
					return false;
				} 				
			}
			else
			{
				return true;
			}
		}
		return true;
	}
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<%
	for(int i=0;roleList!=null && i<roleList.size();i++)
	{
	%>
		<script type="text/javascript">
		roleList = new Array();
		roleFunctionList = new Array();
		</script>
		<%
		RoleDTO roleDTO = (RoleDTO)roleList.get(i);
		
		Set<FunctionDTO> functonListOfRoles = roleDTO.getFunctions();
		
		for( FunctionDTO functionDTO : functonListOfRoles )
		{
			System.out.println ( "functionDTO comid::"+functionDTO.getComponentId () );
			%>
			<script type="text/javascript">
				roleFunctionList.push('<%=functionDTO.getComponentId ()%>');
			</script>
			<%	
		}
		%>
		<script type="text/javascript">
		roleList.push('<%=roleDTO.getComponentId()%>');
		roleList.push(roleFunctionList);
		masterArray.push(roleList);
		</script>
		<%
	}
	%>

<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=role.add.screenname&screenTipTextKey=role.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=role.modify.screenname&screenTipTextKey=role.modify.tiptext"></jsp:include>
</logic:equal>

<html:form styleId="roleFunctionForm"  action="roleFunctionAction.csmp?method=" method="POST" onsubmit="return isRoleModifyValidSubmit()" target="_self">
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
				<tr>
					<td><label class="desc"><bean:message key="label.user.role" /></label></td>
					<td>								
						<html:select property="role" styleId="role"  tabindex="7" onchange="selectFunctions(role.value);" >
							<html:option value="-1">---Select---</html:option>
							<html:options collection="<%=SESSION_KEYS.ROLE_LIST%>" property="componentId" labelProperty="uniqueCode"/>
						</html:select>
					</td>
				</tr>
			
					<tr>
						<td> <label class="desc">Assign Functions For Role </label></td>
						
					</tr>
					<%
					for ( FunctionDTO functionDTO : functionList )
					{
							
					%>					
					<tr>
						<td>
						<input type="checkbox" name="checkgroup" id="roleFunction<%=functionDTO.getComponentId()%>" /><%=functionDTO.getDisplayName()%>
						</td>
					</tr>
					<%	
					}
					%>
					
																	
				</tbody></table>
		</fieldset>
	</td></tr>		
		  
	<input type="hidden" name="checkedIndex" />
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRoleModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareRoleModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			


		</tbody> 
	</table>
	<script type="text/javascript">
		document.onload = alertID();
		function alertID(){
			var roleId=document.getElementById('role').value;
			selectFunctions(roleId);
		}
		var roleFunctionForm = document.getElementById('roleFunctionForm');
		roleFunctionForm.setAttribute("autocomplete", "off");	
	</script>
	
</html:form>
