<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.BeneficiaryDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	BeneficiaryDTO beneficiaryDTO=null;
	List<BeneficiaryDTO> beneficiaryList =(List<BeneficiaryDTO>)session.getAttribute(SESSION_KEYS.BENEFICIARY_LIST);
	
%>

<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=questionnaire.add.screenname&screenTipTextKey=questionnaire.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=questionnaire.modify.screenname&screenTipTextKey=questionnaire.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="questionnaireForm"  action="questionnaireAction.cms?method=" method="POST" onsubmit="return isQuestionnaireModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareQuestionnaireModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareQuestionnaireModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
					 <!--  name of question -->					
					<tr>
						<td> <label class="desc">Beneficiary Detail</label></td>						
					</tr>
					
					<tr>
						<td> <label class="desc">Age</label></td>						
						<td> <label class="desc">National Id</label></td>
						<td> <label class="desc">Contact No</label></td>
					</tr>
					<%
					for(int i=0;beneficiaryList!=null && i<beneficiaryList.size(); i++){
						beneficiaryDTO=(BeneficiaryDTO)beneficiaryList.get(i);
						if(beneficiaryDTO!=null){
					%>
					<tr>
												
						<td> <label class="desc"><%=beneficiaryDTO.getAge()%></label></td>
						<td> <label class="desc"><%=beneficiaryDTO.getNationalId()%></label></td>
						<td> <label class="desc"><%=beneficiaryDTO.getContactNo()%></label></td>
					</tr>
					<%}}%>
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareQuestionnaireModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareQuestionnaireModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>

