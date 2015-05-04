<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.dcrinfo.dto.DcrinfoDTO"%>
<%@ page import="com.swiftcorp.portal.dcrinfo.dto.DcrProductInfoDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>

<%@ page import="java.util.Vector"%>

<%
	boolean isReadOnly = false;
Vector v = new Vector();
v.add("This");
v.add(" ");
v.add("is");
v.add(" ");
v.add("a");
v.add(" ");
v.add("very");
v.add(" ");
v.add("simple");
v.add(" ");
v.add("example.");
pageContext.setAttribute("v", v);
DcrProductInfoDTO dcrProductInfoDTO = new DcrProductInfoDTO();
pageContext.setAttribute("dcrProductInfoDTO", dcrProductInfoDTO);
//session.setAttribute("list",v);

%>
<script type="text/javascript">
	function isDcrinfoModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareDcrinfoModifyAction(actionName)
	{
		if(document.getElementById('dcrinfoForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('dcrinfoForm').action;			
		if(actionName == 'add')
		{
			path = 'dcrinfoActionWithValidation.csmp?method=addDcrinfo';
		}
		else if(actionName == 'modify')
		{
			path = 'dcrinfoActionWithValidation.csmp?method=modifyDcrinfo';
		}
		else if(actionName == 'remove')
		{
			path += 'removeDcrinfo';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelDcrinfoOperation';
		}
		document.getElementById('dcrinfoForm').action = path;
		document.getElementById('dcrinfoForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=dcrinfo.add.screenname&screenTipTextKey=dcrinfo.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=dcrinfo.modify.screenname&screenTipTextKey=dcrinfo.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="dcrinfoForm"  action="dcrinfoAction.cms?method=" method="POST" onsubmit="return isDcrinfoModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareDcrinfoModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareDcrinfoModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of dcrinfo -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.dcrinfo.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="dcrinfo.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of dcrinfo -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.dcrinfo.description" /></label>
						</td>
						<td>
							<html:text property="dcrinfo.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc">Date</label>
						</td>
						<td>
							<html:text property="dcrinfo.date" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="true"></html:text>
						</td>
					</tr>
					
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc">Session</label>
						</td>
						<td>
							<html:text property="dcrinfo.session" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="true"></html:text>
						</td>
					</tr>
					
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc">Location</label>
						</td>
						<td>
							<html:text property="dcrinfo.location" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="true"></html:text>
						</td>
					</tr>															
					
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc">Doctor Name</label>
						</td>
						<td>
							<html:text property="dcrinfo.doctorName" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="true"></html:text>
						</td>
					</tr>	
					<tr>
					<td colspan="2">
					<label class="desc">Product Info List</label>
					</td>
					</tr>
					<tr>
						<td colspan="2">
							<logic:present name="productInfoList"> 
					        <logic:iterate id="myCollectionElement" name="productInfoList"> 
					          <bean:write name="myCollectionElement" /><br /> 
					        </logic:iterate> 
					        </logic:present> 
							
						</td>
					</tr>							
										
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareDcrinfoModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareDcrinfoModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
