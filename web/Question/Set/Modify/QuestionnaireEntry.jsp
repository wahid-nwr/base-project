<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.question.dto.QuestionDTO"%>
<%@ page import="com.swiftcorp.portal.question.dto.QuestionnaireDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="com.swiftcorp.portal.common.search.SearchOperationResult"%>
<%@ page import="com.swiftcorp.portal.common.search.SearchUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Enumeration"%>
<%
	boolean isReadOnly = false;
	QuestionDTO qsDTO = null;
	List<QuestionDTO> questionList = (List<QuestionDTO>)session.getAttribute(SESSION_KEYS.QUESTION_LIST);
	QuestionnaireDTO questionnaireDTO = null;
	List questionModifyList=null;
	String questionnaireComponentId="";
	String method=request.getParameter("method");
	System.out.println("SESSION_KEYS.OPERATION_TYPE::::::::::::::::"+SESSION_KEYS.OPERATION_TYPE);
	if(method!=null && method.equals("promptModifyQuestionnaire")){
		questionnaireDTO = (QuestionnaireDTO)session.getAttribute(SESSION_KEYS.QUESTIONNAIRE_TO_MODIFY);
		if(questionnaireDTO!=null){
		System.out.println("questionnaireDTO status::"+questionnaireDTO.getQuestionnaireStatus());
		questionnaireComponentId=""+questionnaireDTO.getComponentId();
			questionModifyList=questionnaireDTO.getQuestionList();
			if(questionModifyList!=null && questionModifyList.size()>0){
				System.out.println("questionModifyList:::"+questionModifyList.size());
			}
		}
	}
	Enumeration en=request.getParameterNames();
	String st="";
	while(en.hasMoreElements()){
		st=(String)en.nextElement();
		System.out.println("parameter name::"+st+" value::"+request.getParameter(st));
	}
	
	//getQuestionId
	
%>

<style>
tr.visible
{
visibility: visible;
}
tr.invisible
{
visibility: hidden;
}
</style>
<script type="text/javascript">
	var questionArray=new Array();
	var questionArrayDtl=new Array();
	var questionSelected=new Array();
	var limit=5;
	var start=0;
	var end=5;	
	var added=false;
	var statusOption='';
	<%
	if(questionnaireDTO!=null ){%>
	statusOption='<%=questionnaireDTO.getQuestionnaireStatus().getComponentId()%>';
	<%}%>	
	function IsNumeric(sText)

	{
	   var ValidChars = "0123456789";
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
	
	function selectStatus(){
	//alert('sdfas');
		var options=getElementsByName_iefix('select','questionnaire.componentId');
		for(var i=0;i<options[0].childNodes.length;i++){
			if(options[0].childNodes[i].nodeName=='OPTION'){
				//alert(options[0].childNodes[i].nodeName+'sdfasdf:::'+options[0].childNodes[i].value);
				if(options[0].childNodes[i].value==statusOption){					
					options[0].childNodes[i].selected='yes';
				}
			}
		}
		//alert('sdfasdf:::'+options[0].childNodes.length);
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
	function checkUncheck(){
		var ckBox = getElementsByName_iefix("input", "checkgroup");	
		//var ckBox=document.getElementsByName('checkgroup');			
		for(var i=0;i<ckBox.length;i++){
		added=false;
			if(!ckBox[i].checked){				
				for(var j=0;j<questionSelected.length;j++){			
		 			if(ckBox[i].value==questionSelected[j]){	 				
		 				questionSelected.splice(j,1);
						break;
					}
		 		}
		 	}
		 	else{
		 		for(var j=0;j<questionSelected.length;j++){	
		 			if(ckBox[i].value==questionSelected[j]){		 			
		 				added=true;
					}
		 		}
		 		
		 		if(!added){
		 			questionSelected.push(ckBox[i].value);
		 		}
		 	}
		 }
	}
	function changeQuestionLimit(){	
		limit=parseInt(document.getElementById('changeLimit').value);
		start=0;
		if(end>questionArray.length){
			end=questionArray.length;
		}
		else if(end<limit && limit<questionArray.length){
			end=limit;
		}
		else{
			if(limit<questionArray.length){
				end=start+limit;
			}
			else{
				end=questionArray.length;
			}
		}
		var fieldArea = document.getElementById('question_area');
		while (fieldArea.childNodes[0])
		{		
			fieldArea.removeChild(fieldArea.childNodes[0]);
		}
		var tr = null;
		var td = null;
		var td1 = null;
		var td2 = null;
		for(var i=start;i<end;i++){
			questionArrayDtl=new Array();
			questionArrayDtl=questionArray[i];
		 	tr=document.createElement("tr");
		 	td=document.createElement("td");
		 	td1=document.createElement("td");
		 	td2=document.createElement("td");
		 	label=document.createElement("label");
		 	label.innerHTML=questionArrayDtl[1];
		 	input=document.createElement("input");
		 	input.type = "checkbox";
		 	input.onclick = function(){  
                   				checkUncheck();
                   			}; 
		 	input.name = "checkgroup";
		 	input.value = questionArrayDtl[0];
		 	td1.appendChild(input);
		 	td2.appendChild(label);
		 	label=document.createElement("label");
		 	label.innerHTML = (i+1);
		 	td.appendChild(label);
		 	tr.appendChild(td);	
		 	tr.appendChild(td1);
		 	tr.appendChild(td2);				 
		 	fieldArea.appendChild(tr);
		 	for(var j=0;j<questionSelected.length;j++){
		 		if(questionArrayDtl[0]==questionSelected[j]){
		 			input.checked=true;
		 			break;
		 		}
		 	}						 
		}
		document.getElementById('questionPaging').innerHTML='Question '+(start+1)+' to '+end+' of '+questionArray.length;
	}
	function next(){
		start+=limit;
		end+=limit;
		if(end>questionArray.length){
			end=questionArray.length;
		}
		if(start>questionArray.length || start==questionArray.length){
			start-=limit;
		}
		
		var fieldArea = document.getElementById('question_area');
		
		while (fieldArea.childNodes[0])
		{			
			fieldArea.removeChild(fieldArea.childNodes[0]);
		}
	 	var tr = null;
		var td = null;
		var td1 = null;
		var td2 = null;
	 	var input = null;
	 	var label=null;
	 	for(var i=start;i<end;i++){
	 		questionArrayDtl=new Array();
			questionArrayDtl=questionArray[i];
		 	tr=document.createElement("tr");
		 	td=document.createElement("td");
		 	td1=document.createElement("td");
		 	td2=document.createElement("td");
		 	label=document.createElement("label");
		 	label.innerHTML=questionArrayDtl[1];
		 	input=document.createElement("input");
		 	input.type = "checkbox";
		 	input.name = "checkgroup";
		 	input.value = questionArrayDtl[0];
		 	input.onclick = function(){  
                   				checkUncheck();
                   			}; 
		 	
		 	td1.appendChild(input);
		 	td2.appendChild(label);	
		 	label=document.createElement("label");
		 	label.innerHTML = (i+1);
		 	td.appendChild(label);
		 	tr.appendChild(td);
		 	tr.appendChild(td1);
		 	tr.appendChild(td2);				 
		 	fieldArea.appendChild(tr);	
		 	for(var j=0;j<questionSelected.length;j++){
		 		if(questionArrayDtl[0]==questionSelected[j]){
		 			input.checked=true;
		 			break;
		 		}
		 	}					 
		}		
		document.getElementById('questionPaging').innerHTML='Question '+(start+1)+' to '+end+' of '+questionArray.length;
	}
	function prev(){		
		if(end==questionArray.length){
			start-=limit;
			end=start+limit;
		}
		else{
			if(limit<questionArray.length){
				start-=limit;
				end-=limit;
			}
		}
		if(start<0){
			start=0;
			if(limit<questionArray.length){
				end=limit;
			}
			else{
				end=questionArray.length;
			}			
		}
		if(end<limit && limit<questionArray.length){
			end=limit;
		}
		var fieldArea = document.getElementById('question_area');
		while (fieldArea.childNodes[0])
		{			
			fieldArea.removeChild(fieldArea.childNodes[0]);
		}
	 	var tr = null;
	 	var td = null;
		var td1 = null;
		var td2 = null;
	 	var input = null;
	 	var label=null;
	 	for(var i=start;i<end;i++){
	 		questionArrayDtl=new Array();
			questionArrayDtl=questionArray[i];
		 	tr=document.createElement("tr");
		 	td=document.createElement("td");
		 	td1=document.createElement("td");
		 	td2=document.createElement("td");
		 	label=document.createElement("label");
		 	label.innerHTML=questionArrayDtl[1];
		 	input=document.createElement("input");
		 	input.type = "checkbox";
		 	input.name = "checkgroup";
		 	input.value = questionArrayDtl[0];		 	
		 	input.onclick = function(){  
                   				checkUncheck();
                   			}; 
		 	
		 	td1.appendChild(input);
		 	td2.appendChild(label);
		 	label=document.createElement("label");
		 	label.innerHTML = (i+1);
		 	td.appendChild(label);
		 	tr.appendChild(td);	
		 	tr.appendChild(td1);
		 	tr.appendChild(td2);				 
		 	fieldArea.appendChild(tr);
		 	for(var j=0;j<questionSelected.length;j++){
		 		if(questionArrayDtl[0]==questionSelected[j]){
		 			input.checked=true;
		 			break;
		 		}
		 	}						 
		}
		document.getElementById('questionPaging').innerHTML='Question '+(start+1)+' to '+end+' of '+questionArray.length;
	}
	function isQuestionnaireModifyValidSubmit()
	{
		return true;
	}
	
	function prepareQuestionnaireModifyAction(actionName)
	{	
		var validate=false;
		if(document.getElementById('questionnaireForm') == null)
		{
			return;
		}
		validate=validateQuestionNumber(actionName);			
		var path = document.getElementById('questionnaireForm').action;			
		if(actionName == 'add')
		{					
			if(validate){
				path = 'questionnaireActionWithValidation.csmp?method=addQuestionnaire';								
				addCheckedOptions();
			}
		}
		else if(actionName == 'modify')
		{						
			if(validate){
				path = 'questionnaireActionWithValidation.csmp?method=modifyQuestionnaire';				
				addCheckedOptions();
			}
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
		if(validate){
			document.getElementById('questionnaireForm').submit();
		}
		
	}
	function validateQuestionNumber(actionName){
		var options=document.getElementById('questionnaire.questionnaireStatus').value;		
		var noOfQuestion=document.getElementById('questionnaire.numberOfQuestion').value;
		var version=document.getElementById('questionnaire.questionnaireVersion').value;
		var questionnaireId = document.getElementById('questionnaireId').value
		if(actionName == 'cancel')
		{
			return true;
		}
		if((document.getElementById('questionnaireName').value).length==0){
			alert('Enter Question Name');
			return false;
		}
		if(questionnaireId.length==0)
		{
			alert('Please Enter questionnaire Id');
			return false;
		}
		if(version.length==0)
		{
			alert('Please Enter version');
			return false;
		}
		
		if(options=='-1'){
			alert('Select a Status');
			return false;
		}
		if(questionSelected.length==0){
			alert('Please choose at least one question');
			return false;
		}
		if(questionSelected.length!=noOfQuestion){
			if(!IsNumeric(noOfQuestion))
			{
				alert('Please enter numeric value in Number of Question');
				return false;
			}
			alert('You have checked '+questionSelected.length+' question and NO. of question entered '+parseFloat(noOfQuestion));
			return false;
		}
		
		return true;
	}
	function addCheckedOptions(){
		var fieldArea = document.getElementById('question_area');
		var ckBox=getElementsByName_iefix("input", "checkgroup");	
		var tr = null;
		var td = null;
		var td1 = null;
		var td2 = null;
		for(var j=0;j<questionSelected.length;j++){		
			added=false;	
			for(var i=0;i<ckBox.length;i++){							
				if(ckBox[i].checked){	
		 			if(ckBox[i].value==questionSelected[j]){		 			
		 				added=true;
		 				break;
					}
				}
			}
			if(!added){
				for(var i=0;i<questionArray.length;i++){
				
			 		questionArrayDtl=new Array();
					questionArrayDtl=questionArray[i];
					if(questionArrayDtl[0]==questionSelected[j]){
					 	tr=document.createElement("tr");
					 	td=document.createElement("td");
		 				td1=document.createElement("td");
		 				td2=document.createElement("td");
					 	label=document.createElement("label");
					 	label.innerHTML=questionArrayDtl[1];
					 	input=document.createElement("input");
					 	input.type = "checkbox";
					 	input.name = "checkgroup";
					 	input.checked=true;
					 	input.value = questionArrayDtl[0];
					 	input.onclick = function(){  
                   				checkUncheck();
                   			};
               								 	
					 	td1.appendChild(input);
		 				td2.appendChild(label);
		 				label=document.createElement("label");
					 	label.innerHTML = (i+1);
					 	td.appendChild(label);
					 	tr.appendChild(td); 	
		 				tr.appendChild(td1);
		 				tr.appendChild(td2);				 
		 				fieldArea.appendChild(tr);
					 	for(var j=0;j<questionSelected.length;j++){
					 		if(questionArrayDtl[0]==questionSelected[j]){
					 			input.checked=true;
					 			break;
					 		}
					 	}
				 	}					 
				}
			}
 		}			
	}
		
	function isValidSubmit()
	{
		return true;
	}
	function checkLimit(fieldId,value,size){
	if(value.length>size)
		{
			//alert('Cannot enter more than '+size+' characters in this field'+fieldId);
			document.getElementById(fieldId).focus();
		}		
	}
	
	
</script>
<%	
		for(int i=0;questionModifyList!=null && i<questionModifyList.size();i++){
			qsDTO=(QuestionDTO)questionModifyList.get(i);
			
			%>
				<script type="text/javascript">						
						
						questionSelected.push('<%=qsDTO.getComponentId()%>');
					</script>
			<%
		}
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
						<td>
						  <label class="desc"><bean:message key="label.questionnaire.questionnaireName" /></label>
						</td>
						<td>
							<html:text property="questionnaire.questionnaireName" styleId="questionnaireName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					 <!--  questionnaire id -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.questionnaire.questionnaireId" /></label>
						</td>
						<td>
							<html:text property="questionnaire.questionnaireId" styleId="questionnaireId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.questionnaire.questionnaireVersion" /></label>
						</td>
						<td>
							<html:text property="questionnaire.questionnaireVersion" styleId="questionnaire.questionnaireVersion" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.questionnaire.questionnaireStatus" /></label>
						</td>
						<td>								
								<html:select property="questionnaire.componentId" styleId="questionnaire.questionnaireStatus"  tabindex="7"  >
									<html:option value="-1">---Select---</html:option>
									<html:options collection="<%=SESSION_KEYS.QUESTIONNAIRE_STATUS_LIST%>" property="componentId" labelProperty="statusName"/>		
								</html:select>
							</td>
					</tr>			
							
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.questionnaire.questionnaireDescription" /></label>
						</td>
						<td>						
							<textarea rows="5" cols="50" id="questionnaire.questionnaireDescription" name="questionnaire.questionnaireDescription" onchange="checkLimit(this.id,this.value,'20')"><%=(questionnaireDTO!=null && questionnaireDTO.getQuestionnaireDescription()!=null)?questionnaireDTO.getQuestionnaireDescription():""%></textarea>							
						</td>
					</tr>
					
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.questionnaire.numberOfQuestion" /></label>
						</td>
						<td>
							<html:text property="questionnaire.numberOfQuestion" styleId="questionnaire.numberOfQuestion" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>	
					<tr>
						<td>
						  &nbsp;
						</td>
						<td>							
							<input type="text" class="SingleLineTextField" size="30" name="changeLimit" id="changeLimit" onblur="changeQuestionLimit();" value="5"/>
						</td>
					</tr>	
					
					
					<tr>
						<td colspan="2"> <label class="desc">Assign Question For Role </label></td>
						
					</tr>
					<tr>
						<td colspan="2">								
							<table id="question_area">
							
							</table>
						</td>
			
					</tr>
					<%
					for ( QuestionDTO questionDTO : questionList )
					{
						
					%>
					<script type="text/javascript">						
						questionArrayDtl=new Array();
						questionArrayDtl.push('<%=questionDTO.getComponentId()%>');
						questionArrayDtl.push('<%=(questionDTO.getQuestionName()).replaceAll("'","")%>');
						questionArray.push(questionArrayDtl);
					</script>
					
					<!--tr>
						<td>
						<input type="checkbox" name="checkgroup" /><%//=questionDTO.getQuestionName()%>
						</td>
					</tr-->
					<%	
					}
					%>
					<script type="text/javascript">	
						selectStatus();									
						var fieldArea = document.getElementById('question_area');
					 	var tr = null;
					 	var td1 = null;
					 	var td2 = null;
					 	var td = null;
					 	var input = null;
					 	var label=null;
					 	for(var i=start;i<end;i++){
					 		var questionArrayDtl=new Array();
					 		questionArrayDtl=questionArray[i];
						 	tr=document.createElement("tr");
						 	td=document.createElement("td");
						 	td1=document.createElement("td");
						 	td2=document.createElement("td");
						 	input=document.createElement("input");
						 	input.type = "checkbox";
						 	input.name = "checkgroup";
						 	input.value = questionArrayDtl[0];
						 	input.onclick = function(){  
                   				checkUncheck();
                   			};
						 	td1.appendChild(input);
						 	label=document.createElement("label");
						 	label.innerHTML=questionArrayDtl[1];						 	
						 	td2.appendChild(label); 
						 	label=document.createElement("label");
						 	label.innerHTML = (i+1);
						 	td.appendChild(label);
						 	tr.appendChild(td);
						 	tr.appendChild(td1);                 									 	
						 	tr.appendChild(td2);
						 	//li.appendChild(input);
						 	//li.appendChild(label);					 
						 	fieldArea.appendChild(tr);	
						 	for(var j=0;j<questionSelected.length;j++){
						 		if(questionArrayDtl[0]==questionSelected[j]){
						 			input.checked=true;
						 			break;
						 		}
						 	}					 
						}
												
					</script>
					<tr>
						<td colspan="2">
							<ol id="question_no_area">								
								<script type="text/javascript">
									var length = 0;
									if(end>questionArray.length){
										length = questionArray.length;
									}
									else{
										length = end;
									}
									document.writeln('<label id="questionPaging">Question '+(start+1)+' to '+length+' of '+questionArray.length+'</label>');
								</script>
								<input type="button" value="Previous" onclick="prev();"/>
								<input type="button" value="Next" onclick="next();"/>								
							</ol>
						</td>
					</tr>
					<input type="hidden" name="questionnairecomponentId" id="questionnairecomponentId" value="<%=questionnaireComponentId%>">	
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

