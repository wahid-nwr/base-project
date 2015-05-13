<%@ include file="/Common/Include/taglib.jsp"%>

<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.samplecom.dto.SamplecomDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>


<script type="text/javascript">
	function isSamplecomModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareSamplecomModifyAction(actionName)
	{
		if(document.getElementById('samplecomForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('samplecomForm').action;			
		if(actionName == 'add')
		{
			path = 'samplecomActionWithValidation.csmp?method=addSamplecom';
		}
		else if(actionName == 'modify')
		{
			path = 'samplecomActionWithValidation.csmp?method=modifySamplecom';
		}
		else if(actionName == 'remove')
		{
			path += 'removeSamplecom';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelSamplecomOperation';
		}
		document.getElementById('samplecomForm').action = path;
		document.getElementById('samplecomForm').submit();
	}
	

		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>

<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=samplecom.add.screenname&screenTipTextKey=samplecom.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=samplecom.modify.screenname&screenTipTextKey=samplecom.modify.tiptext"></jsp:include>
</logic:equal>

<html:form styleId="samplecomForm"  action="samplecomAction.cms?method=" method="POST" onsubmit="return isSamplecomModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareSamplecomModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareSamplecomModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>

			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of samplecom -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.samplecom.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="samplecom.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>


					 <!--  description of samplecom -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.samplecom.description" /></label>
						</td>
						<td>
							<html:text property="samplecom.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												
