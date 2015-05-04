<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.BeneficiaryDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isBeneficiaryModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareBeneficiaryModifyAction(actionName)
	{
		if(document.getElementById('beneficiaryForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('beneficiaryForm').action;			
		if(actionName == 'add')
		{
			path = 'beneficiaryActionWithValidation.csmp?method=addBeneficiary';
		}
		else if(actionName == 'modify')
		{
			path = 'beneficiaryActionWithValidation.csmp?method=modifyBeneficiary';
		}
		else if(actionName == 'remove')
		{
			path += 'removeBeneficiary';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelBeneficiaryOperation';
		}
		document.getElementById('beneficiaryForm').action = path;
		document.getElementById('beneficiaryForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=beneficiary.add.screenname&screenTipTextKey=beneficiary.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=beneficiary.modify.screenname&screenTipTextKey=beneficiary.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="beneficiaryForm"  action="beneficiaryAction.cms?method=" method="POST" onsubmit="return isBeneficiaryModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareBeneficiaryModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareBeneficiaryModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of beneficiary -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.beneficiary.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="beneficiary.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of beneficiary -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.beneficiary.description" /></label>
						</td>
						<td>
							<html:text property="beneficiary.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  code of beneficiary -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.code" /></label>
						</td>
						<td>
							<html:text property="beneficiary.code" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  name of beneficiary -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.name" /></label>
						</td>
						<td>
							<html:text property="beneficiary.name" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareBeneficiaryModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareBeneficiaryModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
