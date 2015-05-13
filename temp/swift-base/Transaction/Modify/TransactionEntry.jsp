<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.transaction.dto.TransactionDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isTransactionModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareTransactionModifyAction(actionName)
	{
		if(document.getElementById('transactionForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('transactionForm').action;			
		if(actionName == 'add')
		{
			path = 'transactionActionWithValidation.csmp?method=addTransaction';
		}
		else if(actionName == 'modify')
		{
			path = 'transactionActionWithValidation.csmp?method=modifyTransaction';
		}
		else if(actionName == 'remove')
		{
			path += 'removeTransaction';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelTransactionOperation';
		}
		document.getElementById('transactionForm').action = path;
		document.getElementById('transactionForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=transaction.add.screenname&screenTipTextKey=transaction.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=transaction.modify.screenname&screenTipTextKey=transaction.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="transactionForm"  action="transactionAction.cms?method=" method="POST" onsubmit="return isTransactionModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareTransactionModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareTransactionModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of transaction -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.transaction.uniqueCode" /></label>
						</td>
						<td>
							<html:text property="transaction.uniqueCode" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>
					 <!--  description of transaction -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.transaction.description" /></label>
						</td>
						<td>
							<html:text property="transaction.description" styleClass="SingleLineTextField" size="30" tabindex="1" disabled="<%= isReadOnly %>"></html:text>
						</td>
					</tr>														
												


					 <!--  transactionNum of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.transactionNum" /></label>
						</td>
						<td>
							<html:text property="transaction.transactionNum" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  transactionDate of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.transactionDate" /></label>
						</td>
						<td>
							<html:text property="transaction.transactionDate" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  voucherType of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.voucherType" /></label>
						</td>
						<td>
							<html:text property="transaction.voucherType" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  auditStatus of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.auditStatus" /></label>
						</td>
						<td>
							<html:text property="transaction.auditStatus" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  auditComment of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.auditComment" /></label>
						</td>
						<td>
							<html:text property="transaction.auditComment" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  totalAmount of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.totalAmount" /></label>
						</td>
						<td>
							<html:text property="transaction.totalAmount" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  userId of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.userId" /></label>
						</td>
						<td>
							<html:text property="transaction.userId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  actionDate of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.actionDate" /></label>
						</td>
						<td>
							<html:text property="transaction.actionDate" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  amountTrans of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.amountTrans" /></label>
						</td>
						<td>
							<html:text property="transaction.amountTrans" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  approvedBy of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.approvedBy" /></label>
						</td>
						<td>
							<html:text property="transaction.approvedBy" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  approveStatus of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.approveStatus" /></label>
						</td>
						<td>
							<html:text property="transaction.approveStatus" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  dueApprovedBy of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.dueApprovedBy" /></label>
						</td>
						<td>
							<html:text property="transaction.dueApprovedBy" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  dueApproveStatus of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.dueApproveStatus" /></label>
						</td>
						<td>
							<html:text property="transaction.dueApproveStatus" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  commissionApprovedBy of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.commissionApprovedBy" /></label>
						</td>
						<td>
							<html:text property="transaction.commissionApprovedBy" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>


					 <!--  commissionApproveStatus of transaction -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.transaction.commissionApproveStatus" /></label>
						</td>
						<td>
							<html:text property="transaction.commissionApproveStatus" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareTransactionModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareTransactionModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
