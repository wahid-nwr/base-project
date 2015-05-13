<%@ include file="/Common/Include/taglib.jsp"%>

<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.user.dto.UserDTO"%>
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="com.swiftcorp.portal.geo.dto.GeoDTO"%>
<%@ page import="com.swiftcorp.portal.common.dto.DTOConstants"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
	String cancelPath = "userAction.csmp?method=cancelUserOperation" ;
	UserDTO modifyUserDTO=(UserDTO)session.getAttribute(SESSION_KEYS.USER_TO_MODIFY);
	GeoDTO geoDTO = (GeoDTO) session.getAttribute(SESSION_KEYS.GEO);
	if(modifyUserDTO!=null){
		System.out.println("modifyUserDTO role:::::::::::::::::::::::::::::::::::"+modifyUserDTO.getRole().getComponentId());
	}
%>

<script type="text/javascript">
	var modifyUserRole=''; 
	function IsNumeric(sText)

	{
	   var ValidChars = "0123456789.";
	   var IsNumber=true;
	   var Char;
	
	 
	   for (i = 0; i < sText.length && IsNumber == true; i++) 
	      { 
	      Char = sText.charAt(i); 
	      if (ValidChars.indexOf(Char) == -1) 
	         {
	         IsNumber = false;
	         }
	      }
	   return IsNumber;
	}
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
	function loadModify(){	
		if(modifyUserRole!='' || modifyUserRole!=null){			
			var options=getElementsByName_iefix('select','role');
			for(var i=0;i<options[0].childNodes.length;i++){
				if(options[0].childNodes[i].nodeName=='OPTION'){					
					if(options[0].childNodes[i].value==modifyUserRole){						
						options[0].childNodes[i].selected='yes';
					}
				}
			}
		}
	}
	function isUserModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareUserModifyAction(actionName)
	{
		if(document.getElementById('userForm') == null)
		{
			return;
		}
		
		var path = document.getElementById('userForm').action;
		var cancelPath = '<%=cancelPath%>' ;
		
		if(actionName == 'add' || actionName == 'modify')
		{		 
		 	   var pass = document.getElementById("user.password");
		       var confirm = document.getElementById("user.confirmPassword");	
		       var userId = document.getElementById("user.uniqueCode");
		       var firstName = document.getElementById("user.firstName");
		       var lastName = document.getElementById("user.lastName");	
			   var role = document.getElementById('role');
			
			if ((userId.value).length==0)
			{
				alert("UserId blank.");
				return ;
			}
			else if ((pass.value ).length==0)
			{
				alert("Password is missing.");
				return ;
			}
			if (pass.value != confirm.value)
			{
				alert("Passwords do not match.");
				return ;
			}
			else if ((firstName.value).length==0)
			{
				alert("Enter Your First Name.");
				return ;
			}
			else if ((lastName.value).length==0)
			{
				alert("Enter Your Last Name.");
				return ;
			}
			
			if (role.value == '-1')
			{
				alert('Please select role for user');
				return ;
			}
		}

		if(actionName == 'add')
		{
			path = 'userAction.csmp?method=addUser&isStay=false';
		}
		else if(actionName == 'modify')
		{
			path = 'userAction.csmp?method=modifyUser&isStay=false';
		}
		else if(actionName == 'remove')
		{
			path = 'userAction.csmp?method=removeUser&isStay=false';
		}
		else if(actionName == 'cancel')
		{
			path = cancelPath;
		}
		
		document.getElementById('userForm').action = path;
		document.getElementById('userForm').submit();
	}
		
	function isValidSubmit()
	{
		return true;
	
	}
	
	
</script>
<%
	if(modifyUserDTO!=null){
	%>
	<script type="text/javascript">
	modifyUserRole='<%=modifyUserDTO.getRole().getComponentId()%>';
	</script>
	<%
		System.out.println("modifyUserDTO role:::::::::::::::::::::::::::::::::::"+modifyUserDTO.getRole().getComponentId());
	}
%>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=user.add.screenname&screenTipTextKey=user.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=user.modify.screenname&screenTipTextKey=user.modify.tiptext"></jsp:include>
</logic:equal>

<html:form styleId="userForm"  action="userAction.cms?method=" method="POST" onsubmit="return isUserModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareUserModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareUserModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>

			
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>	
				 	 <tr>
							<td>
								<label class="desc"><bean:message key="label.user.userArea"/></label>
							</td>
							<td>
									<html:select property="user.userAreaId" styleId="user.userAreaId"  tabindex="7"  >
									<html:option value="-1">---Select---</html:option>
									<html:options collection="<%=SESSION_KEYS.GEO_LIST%>" property="componentId" labelProperty="name"></html:options>
									</html:select>
							</td>
						</tr>									
					</tbody></table>
			</fieldset>
		</td></tr>
		
		<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareUserModifyAction&methodParams=add,cancel"></jsp:include>
		</logic:equal>
			
		<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareUserModifyAction&methodParams=modify,cancel"></jsp:include>
		</logic:equal>

			
		</tbody> 
	</table>
	
	<script type="text/javascript">
		loadModify();
		var userFormElement = document.getElementById('userForm');
		userFormElement.setAttribute("autocomplete", "off");
	</script>
</html:form>																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																							
																																																																																																																																																																																																																																																																																																																																																	