<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.question.dto.QuestionDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	//String path="/Question/Set/Modify/QuestionnaireSerial.jsp";
	boolean isReadOnly = false;
	String questionCompIds="";
	String method=request.getParameter("method");
	String questionnaireComponentId=request.getParameter("questionnairecomponentId");
	System.out.println("method name::::::::::::::::::::::"+method);
	List<QuestionDTO> questionList = (List<QuestionDTO>)session.getAttribute(SESSION_KEYS.QUESTION_LIST);
	String questionnaireStatus="";
	String questionnaireId = request.getParameter("questionnaire.questionnaireId");
	questionnaireStatus=request.getParameter("questionnaire.componentId");
	System.out.println("questionnaireStatus:::::::::::::::::::::::::::::::::::::::::"+questionnaireStatus);
	String[] checkbox=(String[])request.getParameterValues("checkgroup");
	/*
		if(checkbox!=null && !checkbox.equals("null")){
			out.println("this is checked value::"+checkbox.length);
			for(int i=0;i<checkbox.length;i++){
				out.println("this is checked value::"+checkbox[i]);
			}
		}
		*/
%>

<script type="text/javascript">
	function validateCombo(actionName){
		if(actionName=='cancel')
		{
			return true;
		}
		var questionIds=null; 
		var validateQuestion='';
		var validateList=new Array();
		var questionCompIds=document.getElementById('questionCompIds').value;
		questionIds=questionCompIds.split(",");
		var optionNumber=0;
		optionNumber=questionIds.length;
		for(var i=0;i<optionNumber;i++){
			validateQuestion=document.getElementById('questionnaireSerial_'+questionIds[i]).value;
			validateList.push(validateQuestion);
		}
		for(var i=0;i<optionNumber;i++){
			validateQuestion=document.getElementById('questionnaireSerial_'+questionIds[i]).value;
			for(var j=0;j<optionNumber;j++){
				if(i!=j && validateQuestion==validateList[j]){
					alert('Error:: Same order cannot be assigned');
					return false;
				}
			}
		}
		return true;
	}
	function isQuestionnaireModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareQuestionnaireModifyAction(actionName)
	{
		if(document.getElementById('questionnaireForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('questionnaireForm').action;			
		if(actionName == 'add')
		{
			path = 'questionnaireActionWithValidation.csmp?method=addQuestionnaireSerial';
		}
		else if(actionName == 'modify')
		{
			path = 'questionnaireActionWithValidation.csmp?method=modifyQuestionnaireSerial';
		}
		else if(actionName == 'remove')
		{
			path += 'removeQuestionnaire';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelQuestionnaireOperation';
		}		
		document.getElementById('questionnaireForm').action = path;
		if(validateCombo(actionName)){
			document.getElementById('questionnaireForm').submit();
		}
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
	
</script>
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
						<td> <label class="desc">Assign Question Serial for this Questionnaire </label></td>						
					</tr>
					<input type="hidden" id="questionnaireStatus.componentId" name="questionnaireStatus.componentId" value="<%=questionnaireStatus%>">
					<input type="hidden" id="questionnaire.componentId" name="questionnaire.componentId" value="<%=questionnaireComponentId%>">
					<input type="hidden" id="numberOfQuestion" name="numberOfQuestion" value="<%if(checkbox!=null && !checkbox.equals("null")){%><%=checkbox.length%><%}else{%><%=0%><%}%>">
					<%
					if(checkbox!=null && !checkbox.equals("null") && checkbox.length>0){
					%>
					<tr>
						<td> <label class="desc">Question Name</label></td>						
						<td> <label class="desc">Question Serial</label></td>
					</tr>
					<%
					}
					else{
					%>
					There was no question selected for this questionnaire. 
					<%}%>
					<%
					for ( QuestionDTO questionDTO : questionList )
					{
						for(int i=0;checkbox!=null && i<checkbox.length;i++){
							if(Integer.parseInt(checkbox[i])==questionDTO.getComponentId()){
							
							if(questionCompIds!=null && !questionCompIds.equals("null") && questionCompIds.length()>0){
								questionCompIds+=",";
								questionCompIds+=""+questionDTO.getComponentId();
							}
							else{
								questionCompIds+=""+questionDTO.getComponentId();
							}
							%>
							<tr>
								<td>
									<%=questionDTO.getQuestionName()%>
								</td>
								<td>
									<select id="questionnaireSerial_<%=questionDTO.getComponentId()%>" name="questionnaireSerial_<%=questionDTO.getComponentId()%>">
									<%
									for(int j=0;j<checkbox.length;j++)
									{
										%>
										<option value="<%=j%>">Position at <%=(j+1)%></option>
										<%
									}
									%>
									</select>
								</td>
							</tr>
							<%	
							}
						}
					}
					%>
					<input type="hidden" name="questionnaire.questionnaireId" value="<%=questionnaireId%>">	
					<input type="hidden" id="questionCompIds" name="questionCompIds" value="<%=questionCompIds%>">
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

