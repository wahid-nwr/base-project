<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.patient.dto.PatientDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isPatientModifyValidSubmit()
	{
		return true;
	}
	
	
	function preparePatientModifyAction(actionName)
	{
		if(document.getElementById('patientForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('patientForm').action;			
		if(actionName == 'add')
		{
			path = 'patientActionWithValidation.csmp?method=addPatient';
		}
		else if(actionName == 'modify')
		{
			path = 'patientActionWithValidation.csmp?method=modifyPatient';
		}
		else if(actionName == 'remove')
		{
			path += 'removePatient';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelPatientOperation';
		}
		document.getElementById('patientForm').action = path;
		document.getElementById('patientForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=patient.add.screenname&screenTipTextKey=patient.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=patient.modify.screenname&screenTipTextKey=patient.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="patientForm"  action="patientAction.cms?method=" method="POST" onsubmit="return isPatientModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=preparePatientModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=preparePatientModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of patient -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.patient.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="patient.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of patient -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.patient.description" /></label>
						</td>
						<td>
							<html:text property="patient.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  patientId of patient -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.patient.patientId" /></label>
						</td>
						<td>
							<html:text property="patient.patientId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  name of patient -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.patient.name" /></label>
						</td>
						<td>
							<html:text property="patient.name" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  birthDate of patient -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.patient.birthDate" /></label>
						</td>
						<td>
							<html:text property="patient.birthDate" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  age of patient -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.patient.age" /></label>
						</td>
						<td>
							<html:text property="patient.age" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  mobileNo of patient -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.patient.mobileNo" /></label>
						</td>
						<td>
							<html:text property="patient.mobileNo" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  email of patient -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.patient.email" /></label>
						</td>
						<td>
							<html:text property="patient.email" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  address of patient -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.patient.address" /></label>
						</td>
						<td>
							<html:text property="patient.address" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=preparePatientModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=preparePatientModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
