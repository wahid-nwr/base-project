<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.algorithm.dto.AlgorithmDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.question.dto.CategoryTypeDTO" %>
<%@ page import="com.swiftcorp.portal.question.dto.QuestionDTO" %>
<%@ page import="com.swiftcorp.portal.question.dto.QuestionnaireDTO" %>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="com.swiftcorp.portal.common.dto.DTOConstants" %>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
	List<CategoryTypeDTO> categoryTypeList = (List<CategoryTypeDTO>)request.getSession().getAttribute(SESSION_KEYS.CATEGORY_LIST);
	List<QuestionDTO> questionList = (List<QuestionDTO>)request.getSession().getAttribute(SESSION_KEYS.QUESTION_LIST);
	List<QuestionnaireDTO> questionnaireList = (List<QuestionnaireDTO>)request.getSession().getAttribute(SESSION_KEYS.QUESTIONNAIRE_LIST);
%>
<script type="text/javascript">
	var questionIdCategoryMap = new Array();
	var questionMapDTL = new Array();
	var questionnaireMapDTL = new Array();
	var questionnaireIdMap = new Array();
	<%
	for(QuestionDTO questionDTO : questionList)
	{
	%>
	mapDTL = new Array();
	mapDTL.push('<%=questionDTO.getQuestionId()%>');
	mapDTL.push('<%=questionDTO.getCategoryType()%>');
	mapDTL.push('<%=questionDTO.getQuestionName()%>');
	questionIdCategoryMap.push(mapDTL);
	<%
	}
	for(QuestionnaireDTO questionnaireDTO:questionnaireList)
	{
	%>
		questionnaireMapDTL = new Array();
		questionnaireMapDTL.push('<%=questionnaireDTO.getQuestionnaireId()%>');
		questionnaireMapDTL.push('<%=questionnaireDTO.getQuestionnaireName()%>');
		questionnaireIdMap.push(questionnaireMapDTL);
	<%
	}
	%>
	function getQuestionList(questionSelectinput)
	{
		for( var i = 0; i< questionIdCategoryMap.length; i++ )
		{
			questionMapDTL = questionIdCategoryMap[i];
			option = document.createElement("option");
			option.value = questionMapDTL[0];
			option.innerHTML = questionMapDTL[2];
			questionSelectinput.appendChild(option);
		}
		return questionSelectinput;
	}
	function getQuestionnaireList(questionnaireSelectinput)
	{
		for( var i = 0; i< questionnaireIdMap.length; i++ )
		{
			questionnaireMapDTL = questionnaireIdMap[i];
			option = document.createElement("option");
			option.value = questionnaireMapDTL[0];
			option.innerHTML = questionnaireMapDTL[1];
			questionnaireSelectinput.appendChild(option);
		}
		return questionnaireSelectinput;
	}
	function showAlgQuestionBranch()
	{
		
		var algQuestionBranchNumber = document.getElementById('algQuestionBranchNumber').value;
		alert('algQuestionBranchNumber::'+algQuestionBranchNumber);
		var algQuestionBranchArea = document.getElementById('algQuestionBranchArea');
		while (algQuestionBranchArea.childNodes[0])
		{
			algQuestionBranchArea.removeChild(algQuestionBranchArea.childNodes[0]);
		}
		
		
		//input.type = "select";
		
		
		for(var i=0; i<algQuestionBranchNumber; i++)
		{
			var li = document.createElement("li");
			var legend = document.createElement("legend");
			var label = null;
			var input = null;
			var br = null;
			var option = null;
			li.appendChild(legend);
			legend.innerHTML = 'Next Algorithm Question '+(i+1)+': ';
			
			input = document.createElement("input");
			input.id = "qqType_"+i;
			input.name = "qqType_"+i;
			input.type = "radio";
			input.value = '<%=DTOConstants.QUESTION_TYPE %>';
			li.appendChild(input);
			label = document.createElement("label");
			label.innerHTML= " Question ";
			li.appendChild(label);
			input = document.createElement("input");
			input.id = "qqType_"+i;
			input.name = "qqType_"+i;
			input.type = "radio"; 
			input.value = '<%=DTOConstants.QUESTIONNAIRE_TYPE %>';
			li.appendChild(input);
			label = document.createElement("label");
			label.innerHTML= " Questionnaire ";
			li.appendChild(label);
			br = document.createElement('br');			
			li.appendChild(br);
			br = document.createElement('br');
			li.appendChild(br);
			var questionListlabel = document.createElement("label");
			questionListlabel.innerHTML= " Question List ";
			
			var questionSelectinput = document.createElement("select");
			questionSelectinput = getQuestionList(questionSelectinput);
			//input.type = "select";
			
			
			
			var questionnaireListlabel = document.createElement("label");
			questionnaireListlabel.innerHTML= " Questionnaire List ";
			var questionnaireSelectinput = document.createElement("select");
			questionnaireSelectinput = getQuestionnaireList(questionnaireSelectinput);
			li.appendChild(questionnaireListlabel);
			
			questionSelectinput.id = "nextQuestion_"+i;
			questionSelectinput.name = "nextQuestion_"+i;
			
			li.appendChild(questionSelectinput);
			
			li.appendChild(br);
			li.appendChild(questionListlabel);
			
			questionnaireSelectinput.id = "nextQuestionnaire_"+i;
			questionnaireSelectinput.name = "nextQuestionnaire_"+i;
			
			li.appendChild(questionnaireSelectinput);
			br = document.createElement('br');			
			li.appendChild(br);
			label = document.createElement("label");
			label.innerHTML= " Algorithm Answer ";
			li.appendChild(label);
			input = document.createElement("input");
			input.id = "algAnswer_"+i;
			input.name = "algAnswer_"+i;
			li.appendChild(input);
			algQuestionBranchArea.appendChild(li);
			
			//algQuestionBranchArea.appendChild(li);
		}
	}
	function isAlgorithmModifyValidSubmit()
	{
		return true;
	}
	function showQuestionByCategory(calegoryElement)
	{
		alert(calegoryElement.value);
	}
	
	function prepareAlgorithmModifyAction(actionName)
	{
		alert('actionName::'+actionName);	
		if(document.getElementById('algorithmForm') == null)
		{
			return;
		}
				
		var path = document.getElementById('algorithmForm').action;			
		if(actionName == 'add')
		{
			path = 'algorithmActionWithValidation.csmp?method=addNextAlgQuestion';
		}
		else if(actionName == 'modify')
		{
			path = 'algorithmActionWithValidation.csmp?method=modifyAlgorithm';
		}
		else if(actionName == 'remove')
		{
			path += 'removeAlgorithm';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelAlgorithmOperation';
		}
		document.getElementById('algorithmForm').action = path;
		document.getElementById('algorithmForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=algorithm.add.screenname&screenTipTextKey=algorithm.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=algorithm.modify.screenname&screenTipTextKey=algorithm.modify.tiptext"></jsp:include>
</logic:equal>
<!-- html:form styleId="algorithmForm"  action="algorithmAction.cms?method=" method="POST" onsubmit="return isAlgorithmModifyValidSubmit()" target="_self"-->
<form styleId="algorithmForm" id="algorithmForm" name="algorithmForm" action="algorithmAction.cms?method=" method="POST" onsubmit="return isAlgorithmModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlgorithmModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlgorithmModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table>
						<tbody>
							<tr>
								<td><input type="radio" name="qqType" value="<%=DTOConstants.QUESTION_TYPE %>">Question</td>
								<td><input type="radio" name="qqType" value="<%=DTOConstants.QUESTIONNAIRE_TYPE %>">Questionnaire</td>
							</tr>
							<tr>
								<td>
								  	<label class="desc"><bean:message key="label.question.categoryType" /></label>
								</td>
								<td>
									<select name="questionCategory" onchange="showQuestionByCategory(this)">
									<option></option>
									<%
									for(CategoryTypeDTO categoryTypeDTO : categoryTypeList)
									{
									%>
									<option value="<%=categoryTypeDTO.getComponentId()%>"><%=categoryTypeDTO.getCategoryName() %></option>
									<%
									}
									%>
									</select>							
									
								</td>
							</tr>
							<tr>
								<td>
								  	<label class="desc"><bean:message key="label.question.questionList" /></label>
								</td>
								<td>
									<select name="question">
									<option></option>
									<%
									for(QuestionDTO questionDTO : questionList)
									{
									%>
									<option value="<%=questionDTO.getQuestionId()%>"><%=questionDTO.getQuestionName()%></option>
									<%
									}
									%>
									</select>							
									
								</td>
							</tr>
							<tr>
								<td>
								  	<label class="desc"><bean:message key="label.question.questionnaireList" /></label>
								</td>
								<td>
									<select name="questionnaire">
									<option></option>
									<%
									for(QuestionnaireDTO questionnaireDTO : questionnaireList)
									{
									%>
									<option value="<%=questionnaireDTO.getQuestionnaireId()%>"><%=questionnaireDTO.getQuestionnaireName() %></option>
									<%
									}
									%>
									</select>							
									
								</td>
							</tr>
							<tr>
								<td>
									<label class="desc"><bean:message key="label.algorithm.firstAlgQuestion"/></label>
								</td>
								<td><input type="text" name="algQuestionName" value=""></td>
							</tr>
							<tr>
								<td>
									<label class="desc"><bean:message key="label.algorithm.description"/></label>
								</td>
								<td><input type="text" name="algQuestionDesc" value="" ></td>
							</tr>
							<tr>
								<td>
									<label class="desc"><bean:message key="label.algorithm.description"/></label>
								</td>
								<td><input type="text" id="algQuestionBranchNumber" name="algQuestionBranchNumber" value="" onblur="showAlgQuestionBranch();"></td>
							</tr>
							<tr>
								<td colspan="2">
								<ol id="algQuestionBranchArea">
								</ol>
								</td>
							</tr>
						</tbody>
					</table>
				</fieldset>
			</td></tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlgorithmModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareAlgorithmModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
	</form>
<!--/html:form-->
