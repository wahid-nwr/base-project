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
	var userAreaType = null;
	var userArea = null;
	<%
	if(modifyUserDTO!=null)
	{
		geoDTO = modifyUserDTO.getUserArea();
		if(geoDTO!=null)
		{
			%>
			userAreaType = '<%=geoDTO.getGeoType()%>';
			userArea = '<%=geoDTO.getComponentId()%>';
			<%
		}
	}
	%>
	var ccList = null;
	var rgList = null;
	var brList = null;
	var defaultList = null;
	function showUserArea(areaType)
	{
		var areaList = document.getElementById('areaList');
		while(areaList.childNodes[0])
		{
			areaList.removeChild(areaList.childNodes[0]);
		}
		if(areaType=='5')
		{
			//alert(document.getElementById('ccAreaList').parentNode.parentNode.id);
			areaList.appendChild(ccList);
			//document.getElementById('ccAreaList').parentNode.style.visibility = "visible";
			//document.getElementById('rgAreaList').parentNode.style.visibility = "hidden";
			//document.getElementById('brAreaList').parentNode.style.visibility = "hidden";
		}
		else if(areaType=='10')
		{
			areaList.appendChild(rgList);
			//document.getElementById('rgAreaList').parentNode.style.visibility = "visible";
			//document.getElementById('ccAreaList').parentNode.style.visibility = "hidden";
			//document.getElementById('brAreaList').parentNode.style.visibility = "hidden";
		}
		else if(areaType=='15')
		{
			areaList.appendChild(brList);
			//document.getElementById('brAreaList').parentNode.style.visibility = "visible";
			//document.getElementById('ccAreaList').parentNode.parentNode.style.visibility = "hidden";
			//document.getElementById('rgAreaList').parentNode.parentNode.style.visibility = "hidden";
		}
		else
		{
			areaList.appendChild(defaultList);
		}
	}
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
		if(userArea!=null || userArea!='')			
		{	
			//alert(userArea+' '+userAreaType);
			if(userAreaType=='5')
			{
				areaList.appendChild(ccList);
				for(var i =0;i<ccList.length;i++)
				{					
					if(ccList[i].value==userArea){						
						ccList[i].selected='yes';
					}
				}
			}
			else if(userAreaType=='10')
			{
				areaList.appendChild(rgList);
				for(var i =0;i<rgList.length;i++)
				{					
					if(rgList[i].value==userArea){						
						rgList[i].selected='yes';
					}
				}
			}
			else if(userAreaType=='15')
			{
				areaList.appendChild(brList);
				for(var i =0;i<brList.length;i++)
				{					
					if(brList[i].value==userArea){						
						brList[i].selected='yes';
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
							<td><font color="red">*</font> <label class="desc"><bean:message key="label.user.userId" /></label></td>
							<td>								
								<html:text property="user.uniqueCode"  styleId="user.uniqueCode"  styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>" ></html:text>
						</tr>
						<tr>
							<td><font color="red">*</font> <label class="desc"><bean:message key="label.user.password" /></label></td>
							<td>
								<html:password property="user.password"  styleId="user.password" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>" ></html:password>
							</td>
						</tr>
						
						<tr>
							<td><font color="red">*</font> <label class="desc"><bean:message key="label.user.confirmPassword" /></label></td>
							<td>
								<html:password property="user.confirmPassword" styleId="user.confirmPassword" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:password>
							</td>
						</tr>
			
						<tr>
							<td><font color="red">*</font> <label class="desc"><bean:message key="label.user.firstName" /></label></td>
							<td>
								<html:text property="user.firstName" styleId="user.firstName" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
							</td>
						</tr>
								
						
						<tr>
							<td><font color="red">*</font> <label class="desc"><bean:message key="label.user.lastName" /></label></td>
							<td>
								<html:text property="user.lastName" styleId="user.lastName" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
							</td>
						</tr>
						
						<tr>
							<td><label class="desc"><bean:message key="label.user.email" /></label></td>
							<td>
								<html:text property="user.email" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
							</td>
						</tr>
						
						<tr>
							<td><label class="desc"><bean:message key="label.user.role" /></label></td>
							<td>								
								<html:select property="role" styleId="role"  tabindex="7"  >
									<html:option value="-1">---Select---</html:option>
									<html:options collection="<%=SESSION_KEYS.ROLE_LIST%>" property="componentId" labelProperty="uniqueCode"/>
								</html:select>
							</td>
						</tr>
						<!--  user area type -->
					<%
						int geoType = 0;
						if(modifyUserDTO!=null)
						{
							geoType = modifyUserDTO.getAreaType();
							System.out.println("geoType::"+geoType);
						}
					%>
					 <tr>
						<td>
						   <label class="desc"><bean:message key="label.user.areaType" /></label>
						</td>	
						<td>
							<select name="user.areaType" id="areaType"  tabindex="8" onchange="showUserArea(areaType.value);">
							<option value="-1">---Select---</option>
								<option <%if(geoType == DTOConstants.GEO_TYPE_CITY_CORPORATION){out.println("selected");}%> value= "5" >City Corporation</option>								
								<option <%if(geoType == DTOConstants.GEO_TYPE_REGION){out.println("selected");}%> value= "10" >Region</option>	
								<option <%if(geoType == DTOConstants.GEO_TYPE_BRANCH){out.println("selected");}%> value= "15" >Branch</option>										
							</select>
						</td>
					</tr>
					<tr>
					</tr>
					<tr>
					<td colspan="2">
					
						<div>
							<span style="width:100px"><label class="desc"><bean:message key="label.user.userArea" /></label></span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="areaList">
								
							</span>
							
						</div>
					</td>
					</tr>
					
				</tbody></table>
			</fieldset>
		</td></tr>
		<div id="removeElement">
		<span style="visibility:hidden;width:100px"><html:select property="user.userAreaId" styleId="ccAreaList"  tabindex="7"  >
										<html:option value="-1">---Select---</html:option>
										<html:options collection="<%=SESSION_KEYS.GEO_LIST_CC%>" property="componentId" labelProperty="name"/>
									</html:select></span>		
		<span style="visibility:hidden"><html:select property="user.userAreaId" styleId="rgAreaList"  tabindex="7"  >
									<html:option value="-1">---Select---</html:option>
									<html:options collection="<%=SESSION_KEYS.GEO_LIST_REGION%>" property="componentId" labelProperty="name"/>
								</html:select>
							</span>
							<span style="visibility:hidden"><html:select property="user.userAreaId" styleId="brAreaList"  tabindex="7"  >
									<html:option value="-1">---Select---</html:option>
									<html:options collection="<%=SESSION_KEYS.GEO_LIST_BRANCH%>" property="componentId" labelProperty="name"/>
								</html:select>
							</span>
							<span style="visibility:hidden"><html:select property="user.userAreaId" styleId="defaultList"  tabindex="7"  >
									<html:option value="-1">---Select---</html:option>									
								</html:select>
							</span>
			</div>
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareUserModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareUserModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>

			
		</tbody> 
	</table>
	
	<script type="text/javascript">
		
		ccList = document.getElementById('ccAreaList');
		rgList = document.getElementById('rgAreaList');
		brList = document.getElementById('brAreaList');
		defaultList = document.getElementById('defaultList');
		var removeElement = document.getElementById('removeElement');
		//removeElement.innerHTML = '';
		while(removeElement.childNodes[0])
		{
			//alert('ddddd:::'+removeElement.childNodes[0]);
			removeElement.removeChild(removeElement.childNodes[0]);
		}
		//alert(removeElement.innerHTML);
		loadModify();
		var userFormElement = document.getElementById('userForm');
		userFormElement.setAttribute("autocomplete", "off");
		
	</script>

</html:form>