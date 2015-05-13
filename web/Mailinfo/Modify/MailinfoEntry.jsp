<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.mailinfo.dto.MailinfoDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isMailinfoModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareMailinfoModifyAction(actionName)
	{
		if(document.getElementById('mailinfoForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('mailinfoForm').action;			
		if(actionName == 'add')
		{
			path = 'mailinfoActionWithValidation.csmp?method=addMailinfo';
		}
		else if(actionName == 'modify')
		{
			path = 'mailinfoActionWithValidation.csmp?method=modifyMailinfo';
		}
		else if(actionName == 'remove')
		{
			path += 'removeMailinfo';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelMailinfoOperation';
		}
		document.getElementById('mailinfoForm').action = path;
		document.getElementById('mailinfoForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=mailinfo.add.screenname&screenTipTextKey=mailinfo.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=mailinfo.modify.screenname&screenTipTextKey=mailinfo.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="mailinfoForm"  action="mailinfoAction.cms?method=" method="POST" onsubmit="return isMailinfoModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareMailinfoModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareMailinfoModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of mailinfo -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.mailinfo.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="mailinfo.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of mailinfo -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.mailinfo.description" /></label>
						</td>
						<td>
							<html:text property="mailinfo.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  companyMail of mailinfo -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.mailinfo.companyMail" /></label>
						</td>
						<td>
							<html:text property="mailinfo.companyMail" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareMailinfoModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareMailinfoModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
