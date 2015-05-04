<%@ include file="/Common/Include/taglib.jsp"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.common.ApplicationConstants"%>
<%@ page import="com.swiftcorp.portal.question.dto.QuestionDTO"%>
<%@ page import="com.swiftcorp.portal.question.dto.MCQOptionDTO"%>
<%@ page import="com.swiftcorp.portal.question.dto.ValidationDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.question.validation.ValidationDTOConstants"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%!
public String getValidationFields(ValidationDTO validationDTO, int option)
{
	String htmlInput="";
	String firstOr2ndField = "";
	String firstOr2ndFieldLabel = "";
	String firstFieldValue = "";
	String secondFieldValue = "";
	String validationName = "";
	String validationValue = "";
	if(validationDTO!=null)
	{
		if(option==0)
		{
			firstOr2ndField = "";
			validationName = validationDTO.getValidationName();
			validationValue = validationDTO.getValidationValue();
		}
		else if(option==1)
		{
			firstOr2ndField = "first";
			firstOr2ndFieldLabel = "First Field ";
			validationName = validationDTO.getValidationName();
			validationValue = validationDTO.getValidationValue();
		}
		else if(option==2)
		{
			firstOr2ndField = "second";
			firstOr2ndFieldLabel = "Second Field ";
			validationName = validationDTO.getSecondValidationName();
			validationValue = validationDTO.getSecondValidationValue();
		}
		
		System.out.println("validationDTO.getValidationName()::"+validationDTO.getValidationName()+" option::"+option);
		if(validationName!=null && validationName.equalsIgnoreCase(ValidationDTOConstants.FIXED_LENGTH))
		{
			htmlInput = "<li><label>"+firstOr2ndFieldLabel+"Length :</label><input type=\"text\" name=\""+firstOr2ndField+validationName+"Validation\""+
				" id=\""+firstOr2ndField+validationName+"Validation\" value=\""+validationValue+"\"></li>";																
		}
		else if(validationName!=null && validationName.equalsIgnoreCase(ValidationDTOConstants.LENGTH_RANGE))
		{
			String[] value = null;
			if(validationValue!=null && validationValue.contains(";"))
			{
				value=(validationValue).split(";");	
				firstFieldValue = value[0];
				secondFieldValue = value[1];
			}
			htmlInput = "<li><label>"+firstOr2ndFieldLabel+"Lower Length Limit :</label><input type=\"text\" name=\""+firstOr2ndField+validationName+"LowerLimit\""+
				" id=\""+firstOr2ndField+validationName+"LowerLimit\" value=\""+firstFieldValue+"\"></li>"+
				"<li><label>"+firstOr2ndFieldLabel+"Upper Length Limit :</label><input type=\"text\" name=\""+firstOr2ndField+validationName+"UpperLimit\""+
				" id=\""+firstOr2ndField+validationName+"UpperLimit\" value=\""+secondFieldValue+"\"></li>";
		}
		else if(validationName!=null && validationName.equalsIgnoreCase(ValidationDTOConstants.VALUE_IN_RANGE))
		{
			String[] value = null;
			if(validationValue!=null && validationValue.contains(";"))
			{
				value=(validationValue).split(";");	
				firstFieldValue = value[0];
				secondFieldValue = value[1];
			}
			htmlInput = "<li><label>"+firstOr2ndFieldLabel+"Lower Value Limit :</label><input type=\"text\" name=\""+firstOr2ndField+validationName+"LowerLimit\""+
				" id=\""+firstOr2ndField+validationName+"LowerLimit\" value=\""+firstFieldValue+"\"></li>"+
				"<li><label>"+firstOr2ndFieldLabel+"Upper Value Limit :</label><input type=\"text\" name=\""+firstOr2ndField+validationName+"UpperLimit\""+
				" id=\""+firstOr2ndField+validationName+"UpperLimit\" value=\""+secondFieldValue+"\"></li>";
		}
		else if(validationName!=null && validationName.equalsIgnoreCase(ValidationDTOConstants.STARTS_WITH_CHARS))
		{
			htmlInput = "<li><label>"+firstOr2ndFieldLabel+"Should Start With :</label><input type=\"text\" name=\""+firstOr2ndField+validationName+"Validation\"" +
			" id=\""+firstOr2ndField+validationName+"Validation\" value=\""+validationValue+"\"></li>";
		}
		else if(validationName!=null && validationName.equals(ValidationDTOConstants.CONTAIN_CHARS))
		{
			System.out.println("firstOr2ndField+validationDTO.getValidationName()::"+firstOr2ndField+validationName);
			htmlInput = "<li><label>"+firstOr2ndFieldLabel+"Should Contain :</label><input type=\"text\" name=\""+firstOr2ndField+validationName+"Validation\""+
				" id=\""+firstOr2ndField+validationName+"Validation\" value=\""+validationValue+"\"></li>";
		}
	}
	if(validationDTO!=null && validationDTO.getAnsTypeCode() == ValidationDTOConstants.DATE_ANSTYPE_CODE)
	{
		System.out.println("validationDTO.getValidationName()::"+validationDTO.getValidationName());
		if(validationName!=null && validationName.equalsIgnoreCase(ValidationDTOConstants.MAX_DATE))
		{
			htmlInput =	"<li><label>Maximum Date :</label><input type=\"text\" name=\""+validationName+"\""+
				" id=\""+validationName+"\" value=\""+validationValue+"\">"+
				"<a onclick=\"NewCssCal('"+ValidationDTOConstants.MAX_DATE+"','yyyymmdd','dropdown',true);\">"+
				"<img src=\"./DatePicker/images/cal.gif\" width=\"16\" height=\"16\" alt=\"Pick A Date\">"+
				"</a></li>";
		}
		else if(validationName!=null && validationName.equalsIgnoreCase(ValidationDTOConstants.MIN_DATE))
		{
			
			htmlInput = "<li><label>Minimum Date :</label><input type=\"text\" name=\""+validationName+"\""+
				" id=\""+validationName+"ValidationLowerLimit\" value=\""+validationValue+"\">"+
				"<a onclick=\"NewCssCal('"+ValidationDTOConstants.MIN_DATE+"','yyyymmdd','dropdown',true);\">"+
				"<img src=\"./DatePicker/images/cal.gif\" width=\"16\" height=\"16\" alt=\"Pick A Date\"></a></li>";
		}
		else if(validationName!=null && validationName.equalsIgnoreCase(ValidationDTOConstants.DATE_RANGE))
		{
			String[] value = null;
			if(validationValue!=null && validationValue.contains(";"))
			{
				value=(validationValue).split(";");	
				firstFieldValue = value[0];
				secondFieldValue = value[1];
			}
			htmlInput = "<li><label>Start Date :</label><input type=\"text\" name=\"startDate\" id=\"startDate\" value=\""+firstFieldValue+"\">"+
				"<a onclick=\"NewCssCal('startDate','yyyymmdd','dropdown',true);\">"+
				"<img src=\"./DatePicker/images/cal.gif\" width=\"16\" height=\"16\" alt=\"Pick A Date\"></a></li>"+
				"<li><label>End Date :</label><input type=\"text\" name=\"endDate\" id=\"endDate\" value=\""+secondFieldValue+"\">"+
				"<a onclick=\"NewCssCal('endDate','yyyymmdd','dropdown',true);\">"+
				"<img src=\"./DatePicker/images/cal.gif\" width=\"16\" height=\"16\" alt=\"Pick A Date\"></a></li>";
		}
		else if(validationName!=null && validationName.equalsIgnoreCase(ValidationDTOConstants.NOT_AFTER))
		{
			htmlInput = "<li><label>Not After :</label><input type=\"text\" name=\""+validationName+"\""+
				" id=\""+validationName+"\" value=\""+validationValue+"\">"+
				"<a onclick=\"NewCssCal('"+ValidationDTOConstants.NOT_AFTER+"','yyyymmdd','dropdown',true);\">"+
				"<img src=\"./DatePicker/images/cal.gif\" width=\"16\" height=\"16\" alt=\"Pick A Date\"></a></li>";
		}
		else if(validationName!=null && validationName.equals(ValidationDTOConstants.NOT_BEFORE))
		{
			System.out.println("validationDTO.getValidationName()::"+validationName);
			htmlInput = "<li><label>Not Before :</label><input type=\"text\" name=\""+validationName+"\""+
				" id=\""+validationName+"\" value=\""+validationValue+"\">"+
				"<a onclick=\"NewCssCal('"+ValidationDTOConstants.NOT_BEFORE+"','yyyymmdd','dropdown',true);\">"+
				"<img src=\"./DatePicker/images/cal.gif\" width=\"16\" height=\"16\" alt=\"Pick A Date\"></a></li>";
		}
	}
	System.out.println("htmlInput::"+htmlInput);
	return htmlInput;
}
%>
<script type="text/javascript">
	var firstFieldValue = '';
	var secondFieldValue = '';
	var isModifyFirstTime = false;
	</script>
<%
	boolean isReadOnly = false;
	QuestionDTO questionDTO=(QuestionDTO)session.getAttribute(SESSION_KEYS.QUESTION);
	if(questionDTO!=null){
		
		//System.out.println("mcq list size::::::::::::::::::::::::::::::"+questionDTO.getMcqOptionList().size());
	}
	int questionMCQNumber = 0;
	MCQOptionDTO mcqOptionDTO = null;
	ValidationDTO validationDTO = null;
	List mcqOptionList= null;
	List<ValidationDTO> validationDTOList = null;
	if(questionDTO!=null && questionDTO.getMcqOptionList()!=null){
		validationDTOList = questionDTO.getValidationDTOList();
		if(validationDTOList!=null && validationDTOList.size()>0)
		{
			validationDTO = validationDTOList.get(0);
		}
		mcqOptionList = (List)questionDTO.getMcqOptionList();
		questionMCQNumber=mcqOptionList.size();
		for(int i=0;i<mcqOptionList.size();i++)
		{
			//mcqOptionDTO = (MCQOptionDTO)mcqOptionList.get(i);
			System.out.println("desc::::::::::"+((MCQOptionDTO)mcqOptionList.get(i)).getName());
		}
	}
	if(validationDTO!=null)
	{
	%>
	<script type="text/javascript">
	firstFieldValue = '<%=validationDTO.getValidationName()%>';
	secondFieldValue = '<%=validationDTO.getSecondValidationName()%>';
	isModifyFirstTime = true;
	</script>
	<%
	}
%>
<script type="text/javascript" src="./DatePicker/datetimepicker_css.js"></script>
<style type="text/css">
.bottomtext img
{
  border:none;
}
A{text-decoration: none;} 
A:link
{
	color: #3399cc;
}
A:visited{color: #3399cc;}
A:hover
{
	color: #cc0033;
}
A img
{
  border:none;
}
</style>
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
	var modQuestionCategory='';
	var modAnswerType='';
	var modAnswerTypeName='';
	var modMcqList=null;
	var firstFieldvalidationChecked=false;
	var secondFieldvalidationChecked=false;
	
	if(firstFieldValue.length>0)
	{
		firstFieldvalidationChecked = true;
	}
	if(secondFieldValue.length>0)
	{
		secondFieldvalidationChecked = true;
	}
	function IsNumeric(sText)

	{
	   var ValidChars = "0123456789.";
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
	function loadModify(){	
		if(modQuestionCategory!='' || modQuestionCategory.length>0){
			//document.getElementById('category').value=modQuestionCategory;
			var options=getElementsByName_iefix('select','category');
			for(var i=0;i<options[0].childNodes.length;i++){
				if(options[0].childNodes[i].nodeName=='OPTION'){
					//alert(options[0].childNodes[i].nodeName+'sdfasdf:::'+options[0].childNodes[i].value);
					if(options[0].childNodes[i].value==modQuestionCategory){
						//alert('should be selected');
						options[0].childNodes[i].selected='yes';
					}
				}
			}
		}
		else
		{	
			var options=getElementsByName_iefix('select','category');
			for(var i=0;i<options[0].childNodes.length;i++){
				if(options[0].childNodes[i].nodeName=='OPTION'){
					//alert(options[0].childNodes[i].nodeName+'sdfasdf:::'+options[0].childNodes[i].value);
					if(options[0].childNodes[i].value=='-1'){
						//alert('should be selected');
						options[0].childNodes[i].selected='yes';
					}
				}
			}
		}
		if(modAnswerType!='' || modAnswerType.length>0){
			//document.getElementById('answerType').value=modAnswerType;
			var options=getElementsByName_iefix('select','answerType');
			for(var i=0;i<options[0].childNodes.length;i++){
				if(options[0].childNodes[i].nodeName=='OPTION'){
					//alert(options[0].childNodes[i].nodeName+'sdfasdf:::'+options[0].childNodes[i].value);
					if(options[0].childNodes[i].value==modAnswerType){
						//alert('should be selected');
						options[0].childNodes[i].selected='yes';
						if(options[0].childNodes[i].value==modAnswerType){
							//alert('dfasdfas::::'+options[0].childNodes[i].value);
							mcqOptions (modAnswerType);
						}
					}
				}
			}
		}
		else
		{
			var options=getElementsByName_iefix('select','answerType');
			for(var i=0;i<options[0].childNodes.length;i++){
				if(options[0].childNodes[i].nodeName=='OPTION'){
					//alert(options[0].childNodes[i].nodeName+'sdfasdf:::'+options[0].childNodes[i].value);
					if(options[0].childNodes[i].value=='-1'){
						options[0].childNodes[i].selected='yes';
					}
				}
			}
		}
		
	}
	function validate(actionName){
		var questionName=document.getElementById("question.questionName").value;
		var category=document.getElementById('category').value;
		var answerType=document.getElementById('answerType').value;
		var mcqOption=document.getElementById('mcqOption').value;
		var questionId = document.getElementById('question.questionId').value;
		//var selectedNumberOfMCQ = document.getElementById().value;
		if(actionName == 'cancel')
		{
			return true;
		}
		if(questionName.length==0){
			alert('Please Enter Question Name');
			return false;
		}
		if(questionId.length==0 || questionId=='null')
		{
			alert('Please Enter Question Id');
			return false;			
		}
		if(category=='-1'){
			alert('Please Select Question Category');
			return false;
		}
		if(answerType=='-1'){
			alert('Please Select Answer Type');
			return false;
		}
		if(answerType=='<%=ApplicationConstants.MULTIPLE_CHOICE_QUESTION_TYPE%>'){
			if(mcqOption=='-1'){
				alert('Please Select Number Of MCQ');
				return false;
			}			
		}
		if(mcqOption>0)
		{
			for(var i=0;i<mcqOption;i++)
			{
				if((document.getElementById('mcq'+i).value).length == 0)
				{
					alert('Please Enter Name In No# '+(i+1)+' MCQ Field');
					return false;
				}
				if((document.getElementById('value'+i).value).length == 0)
				{
					alert('Please Enter Value In No# '+(i+1)+' Value Field');
					return false;
				}
				//alert(document.getElementById('mcq'+i).value);
			}
		}
		return true;
	}
	function isQuestionModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareQuestionModifyAction(actionName)
	{
		if(document.getElementById('questionForm') == null)
		{
			return;
		}
		if(validate(actionName)){			
			var path = document.getElementById('questionForm').action;			
			if(actionName == 'add')
			{
				path = 'questionActionWithValidation.csmp?method=addQuestion';
			}
			else if(actionName == 'modify')
			{
				path = 'questionActionWithValidation.csmp?method=modifyQuestion';
			}
			else if(actionName == 'remove')
			{
				path += 'removeQuestion';
			}
			else if(actionName == 'cancel')
			{
				path += 'cancelQuestionOperation';
			}
			document.getElementById('questionForm').action = path;
		
			document.getElementById('questionForm').submit();
		}
	}
	function mcqOptions(options){
		var tbRow=document.getElementById('mcqOptions');
	  	var style=document.getElementById('mcqOptions').style.visibility;
	  	//alert(style);
	  	if(options=='1'){ 
		  	if(style=='hidden'){
		  		document.getElementById('mcqOptions').style.visibility = 'visible';	  	
		  	}
		  	else{
		  		document.getElementById('mcqOptions').style.visibility = 'hidden';
		  	}
	  	}
	  	else{
	  		if(style=='visible'){
		  		document.getElementById('mcqOptions').style.visibility = 'hidden';
		  		document.getElementById('mcqOptionsDtl').style.visibility = 'hidden';	  	
		  	}
	  	}
	  	showHide();
	}
	function showMCQOptions ()
	{
		document.getElementById('mcqOptionsDtl').style.visibility = 'visible';
		var numberOfOption = document.getElementById('mcqOption').value;
		document.getElementById("mcqOptionNumber").value=document.getElementById('mcqOption').value;
		var fieldArea = document.getElementById('mcq_area');
		while (fieldArea.childNodes[0])
		{
			 fieldArea.removeChild(fieldArea.childNodes[0]);
		}
		
		for ( var i=0; i<numberOfOption; i++)
		{
			  
			  var li = document.createElement("li");
			  var label = document.createElement("label");
			  label.innerHTML= " Question : ";
			  li.appendChild(label);
			  var input = document.createElement("input");
			  input.id = "mcq"+i;
			  input.name = "mcq"+i;
			  input.type = "text"; 
			  li.appendChild(input);
			  label = document.createElement("label");
			  label.innerHTML= " Value : ";
			  li.appendChild(label);
			  input = document.createElement("input");
			  input.id = "value"+i;
			  input.name = "value"+i;
			  input.type = "text"; 
			  li.appendChild(input);
			  fieldArea.appendChild(li);

		}
	}
		
	function isValidSubmit()
	{
		return true;
	}
	
	function showValidation (validationAreaName,validationOption)
	{
		if(isModifyFirstTime)
		{
			isModifyFirstTime = false;
			return;
		}
		
		document.getElementById('validationDetail').style.visibility = 'visible';
		var validationType = document.getElementById('validationType');
		var firstFieldValidationType = document.getElementById('firstFieldValidationType');
		var secondFieldValidationType = document.getElementById('secondFieldValidationType');
		var validationTypeName = '';
		var validationType1stOr2nd = '';
		//document.getElementById("mcqOptionNumber").value=document.getElementById('mcqOption').value;
		var validationArea = null;
		var appendType = '';
		
		if(validationOption != null && validationOption == 'firstFieldValidation')
		{
			validationTypeName = firstFieldValidationType.value;
			validationType1stOr2nd = 'first_validation_area';
			validationArea = document.getElementById(validationType1stOr2nd);
			appendType = 'first';
		}
		else if(validationOption != null && validationOption == 'secondFieldValidation')
		{
			validationTypeName = secondFieldValidationType.value;
			validationType1stOr2nd = 'second_validation_area';
			validationArea = document.getElementById(validationType1stOr2nd);
			appendType = 'second';
		}
		else if(validationType!=null)
		{
			validationTypeName = validationType.value;			
			validationArea = document.getElementById(validationAreaName);				
		}
		
		//alert('validationArea::'+validationArea.id);
		while (validationArea.childNodes[0] && validationAreaName=='validation_area')
		{
			 validationArea.removeChild(validationArea.childNodes[0]);
		}
		//alert(validationAreaName);
		if(validationAreaName == 'validation_double_input_area')
		{
			removeDoubleInputValidation(validationTypeName,validationType1stOr2nd);	
		}
		appendType += validationTypeName;
		//alert("validationType::"+validationType);
		if(validationTypeName == '<%=ValidationDTOConstants.FIXED_LENGTH%>')
		{
			var li = document.createElement("li");
			var label = document.createElement("label");
			label.innerHTML= " Length : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = appendType+"Validation";
			input.name = appendType+"Validation";
			input.type = "text"; 
			li.appendChild(input);
			validationArea.appendChild(li);
		}			
		else if(validationTypeName == '<%=ValidationDTOConstants.LENGTH_RANGE%>')
		{
			var br = document.createElement("br");
			var li = document.createElement("li");
			var label = document.createElement("label");
			label.innerHTML= " Lower Length Limit : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = appendType+"LowerLimit";
			input.name = appendType+"LowerLimit";
			input.type = "text"; 
			li.appendChild(input);
			validationArea.appendChild(li);
			validationArea.appendChild(br);
			li = document.createElement("li");
			label = document.createElement("label");
			label.innerHTML= " Upper Length Limit : ";
			li.appendChild(label);
			input = document.createElement("input");
			input.id = appendType+"UpperLimit";
			input.name = appendType+"UpperLimit";
			input.type = "text"; 
			li.appendChild(input);
			validationArea.appendChild(li);
		}
		else if(validationTypeName == '<%=ValidationDTOConstants.VALUE_IN_RANGE%>')
		{
			var br = document.createElement("br");
			var li = document.createElement("li");
			var label = document.createElement("label");
			label.innerHTML= " Lower Value Limit : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = appendType+"LowerLimit";
			input.name = appendType+"LowerLimit";
			input.type = "text"; 
			li.appendChild(input);
			validationArea.appendChild(li);
			validationArea.appendChild(br);
			li = document.createElement("li");
			label = document.createElement("label");
			label.innerHTML= " Upper Value Limit : ";
			li.appendChild(label);
			input = document.createElement("input");
			input.id = appendType+"UpperLimit";
			input.name = appendType+"UpperLimit";
			input.type = "text"; 
			li.appendChild(input);
			validationArea.appendChild(li);
		}
		else if(validationTypeName == '<%=ValidationDTOConstants.STARTS_WITH_CHARS%>')
		{
			var li = document.createElement("li");
			var label = document.createElement("label");
			label.innerHTML= " Charecter : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = appendType+"Validation";
			input.name = appendType+"Validation";
			input.type = "text"; 
			li.appendChild(input);
			validationArea.appendChild(li);
		}
		else if(validationTypeName == '<%=ValidationDTOConstants.CONTAIN_CHARS%>')
		{
			var li = document.createElement("li");
			var label = document.createElement("label");
			label.innerHTML= " Charecter : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = appendType+"Validation";
			input.name = appendType+"Validation";
			input.type = "text"; 
			li.appendChild(input);
			validationArea.appendChild(li);
		}
		else if(validationTypeName == '<%=ValidationDTOConstants.MAX_DATE%>Validation')
		{
			var li = document.createElement("li");
			var label = document.createElement("label");
			label.innerHTML= " Max Date : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = "<%=ValidationDTOConstants.MAX_DATE%>";
			input.name = "<%=ValidationDTOConstants.MAX_DATE%>";
			input.type = "text"; 
			li.appendChild(input);
			var a = document.createElement('a');
			a.onclick = function() {
					NewCssCal('<%=ValidationDTOConstants.MAX_DATE%>','yyyymmdd','dropdown',true);
				};
			var img = document.createElement('img');
			img.src = "./DatePicker/images/cal.gif";
			img.width = 16;
			img.height = 16;
			img.alt = "Pick a date";
			a.appendChild(img);
		    li.appendChild(a);
			validationArea.appendChild(li);
		}
		else if(validationTypeName == '<%=ValidationDTOConstants.MIN_DATE%>Validation')
		{
			var li = document.createElement("li");
			var label = document.createElement("label");
			label.innerHTML= " Min Date : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = "<%=ValidationDTOConstants.MIN_DATE%>";
			input.name = "<%=ValidationDTOConstants.MIN_DATE%>";
			input.type = "text"; 
			li.appendChild(input);
			var a = document.createElement('a');
			a.onclick = function() {
					NewCssCal('<%=ValidationDTOConstants.MIN_DATE%>','yyyymmdd','dropdown',true);
				};
			var img = document.createElement('img');
			img.src = "./DatePicker/images/cal.gif";
			img.width = 16;
			img.height = 16;
			img.alt = "Pick a date";
			a.appendChild(img);
		    li.appendChild(a);
			validationArea.appendChild(li);
		}
		else if(validationTypeName == '<%=ValidationDTOConstants.DATE_RANGE%>Validation')
		{
			var li = document.createElement("li");
			var label = document.createElement("label");
			label.innerHTML= " Start Date : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = "startDate";
			input.name = "startDate";
			input.type = "text"; 
			li.appendChild(input);
			var a = document.createElement('a');
			a.onclick = function() {
					NewCssCal('startDate','yyyymmdd','dropdown',true);
				};
			var img = document.createElement('img');
			img.src = "./DatePicker/images/cal.gif";
			img.width = 16;
			img.height = 16;
			img.alt = "Pick a date";
			a.appendChild(img);
		    li.appendChild(a);
		    
		    label = document.createElement("label");
			label.innerHTML= " End Date : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = "endDate";
			input.name = "endDate";
			input.type = "text"; 
			li.appendChild(input);
			var a = document.createElement('a');
			a.onclick = function() {
					NewCssCal('endDate','yyyymmdd','dropdown',true);
				};
			var img = document.createElement('img');
			img.src = "./DatePicker/images/cal.gif";
			img.width = 16;
			img.height = 16;
			img.alt = "Pick a date";
			a.appendChild(img);
		    li.appendChild(a);
		    
			validationArea.appendChild(li);
		}
		else if(validationTypeName == '<%=ValidationDTOConstants.NOT_BEFORE%>Validation')
		{
			var li = document.createElement("li");
			var label = document.createElement("label");
			label.innerHTML= " Not Before : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = "<%=ValidationDTOConstants.NOT_BEFORE%>";
			input.name = "<%=ValidationDTOConstants.NOT_BEFORE%>";
			input.type = "text"; 
			li.appendChild(input);
			var a = document.createElement('a');
			a.onclick = function() {
					NewCssCal('<%=ValidationDTOConstants.NOT_BEFORE%>','yyyymmdd','dropdown',true);
				};
			var img = document.createElement('img');
			img.src = "./DatePicker/images/cal.gif";
			img.width = 16;
			img.height = 16;
			img.alt = "Pick a date";
			a.appendChild(img);
		    li.appendChild(a);
			validationArea.appendChild(li);
		}
		else if(validationTypeName == '<%=ValidationDTOConstants.DATE_RANGE%>Validation')
		{
			var li = document.createElement("li");
			var label = document.createElement("label");
			label.innerHTML= " Not After : ";
			li.appendChild(label);
			var input = document.createElement("input");
			input.id = "<%=ValidationDTOConstants.DATE_RANGE%>";
			input.name = "<%=ValidationDTOConstants.DATE_RANGE%>";
			input.type = "text"; 
			li.appendChild(input);
			var a = document.createElement('a');
			a.onclick = function() {
					NewCssCal('<%=ValidationDTOConstants.DATE_RANGE%>','yyyymmdd','dropdown',true);
				};
			var img = document.createElement('img');
			img.src = "./DatePicker/images/cal.gif";
			img.width = 16;
			img.height = 16;
			img.alt = "Pick a date";
			a.appendChild(img);
		    li.appendChild(a);
			validationArea.appendChild(li);
		}
		
		else if(validationTypeName == 'doubleInput')
		{
			
		}
	}	
	function removeDoubleInputValidation(validationTypeName,validationAreaName)
	{
		//alert('removeDoubleInputValidation::'+validationTypeName+' validationAreaName::'+validationAreaName);
		var validationArea = document.getElementById(validationAreaName);
		//var option = document.getElementById();
		//alert('aaa::'+validationArea.childNodes.length);
		while (validationArea.childNodes[0])
		{
			 validationArea.removeChild(validationArea.childNodes[0]);
		}
		
		//alert('validationArea.length::'+validationArea.length);
		
		/*for(var i = 0; i < validationArea.childNodes.length; i++ )
		{
			alert('validationArea.childNodes[i].type::'+validationArea.childNodes[i].nodeName);
			if( !(validationArea.childNodes[i].nodeName == 'LI' ) )
			validationArea.removeChild(validationArea.childNodes[i]);
			alert('eee');
		}*/
		
		
	}
	function showDoubeInput (validationOption)
	{
		
		var validationArea = null;
		var validationFieldArea = null;
		var input = document.createElement("select");		
		var checkbox = document.getElementById(validationOption);
		var checked = checkbox.checked;
			
		
		var label = document.createElement("label");
		var li = document.createElement("li");
		
		if(validationOption == 'firstFieldValidation')
		{			
			input = addNormalValidation(input,validationOption);
			input.id = validationOption+"Type";
			input.name = validationOption+"Type";
			label.innerHTML = 'First Field Validations';
			validationArea = document.getElementById('first_option_area');
			validationFieldArea = document.getElementById('first_validation_area');
		}
		else if(validationOption == 'secondFieldValidation')
		{
			input = addNormalValidation(input,validationOption);
			input.id = validationOption+"Type";
			input.name = validationOption+"Type";
			label.innerHTML = 'Second Field Validations';
			validationArea = document.getElementById('second_option_area');
			validationFieldArea = document.getElementById('second_validation_area');
		}
		if(checked)
		{
	  		li.appendChild(label);
			li.appendChild(input);
			validationArea.appendChild(li);
		}
		else
		{
			while (validationArea.childNodes[0])
			{
				 validationArea.removeChild(validationArea.childNodes[0]);
			}
			while (validationFieldArea.childNodes[0])
			{
				validationFieldArea.removeChild(validationFieldArea.childNodes[0]);
			}
		}
		validationArea.style.visibility = 'visible';
	}
	function addNormalValidation(input,validationOption)
	{
		var option = document.createElement('option');
		option.innerHTML = '---Select---';
		option.value = '-1';				
		input.appendChild(option);
		option = document.createElement('option');
		option.innerHTML = 'Entry should be fixed length';
		option.value = '<%=ValidationDTOConstants.FIXED_LENGTH%>';				
		input.appendChild(option);		
		
		option = document.createElement('option');
		option.innerHTML = 'Entry should have length range';
		option.value = '<%=ValidationDTOConstants.LENGTH_RANGE%>';				
		input.appendChild(option);
		
		option = document.createElement('option');
		option.innerHTML = 'Entry should have value range';
		option.value = '<%=ValidationDTOConstants.VALUE_IN_RANGE%>';				
		input.appendChild(option);
		
		option = document.createElement('option');
		option.innerHTML = 'Entry should start with';
		option.value = '<%=ValidationDTOConstants.STARTS_WITH_CHARS%>';				
		input.appendChild(option);
		
		option = document.createElement('option');
		option.innerHTML = 'Entry should contain';
		option.value = '<%=ValidationDTOConstants.CONTAIN_CHARS%>';				
		input.appendChild(option);			
		input.onchange = function(){  
           				showValidation('validation_double_input_area',validationOption);
           			};
        return input;
	}
	function showHide()
	{
		if(isModifyFirstTime)
		{
			isModifyFirstTime = false;
			return;
		}
		var ckBox = getElementsByName_iefix("input", "validationOption");
		//alert(ckBox.length)
		if(ckBox!=null && ckBox!='undefined')
		{
			//alert("ckBox::"+ckBox.length);
			var answerType = document.getElementById('answerType').value;
			//alert('answerType::'+answerType);
			var isDate = false;
			var isDoubleInput = false;
			
			if( answerType == <%=ApplicationConstants.ANSSER_TYPE_DATE_INPUT%> )
			{
				isDate = true;
			}
			else if( answerType == <%=ApplicationConstants.ANSSER_TYPE_DOUBLE_INPUT%> )
			{
				isDoubleInput = true;
			}
			var validationTypeTr = document.getElementById('validationTypeTr');
			if(!isDoubleInput)
			{
				var doubleInputDiv = document.getElementById('validation_double_input_area');
				for(var i=0;doubleInputDiv!=null && i<doubleInputDiv.childNodes.length; i++)
				{	
					var itemParent = doubleInputDiv.childNodes[i];
					var itemToRemove = doubleInputDiv.childNodes[i].childNodes;
					while(itemParent.childNodes[0])
					{
						itemParent.removeChild(itemParent.childNodes[0]);
					}
				}
			}
			
			if(ckBox[0].checked)
			{
				var validationArea = document.getElementById('validation_area');
				//validationArea.style.visibility = 'visible';
				
				//alert('validationArea::'+validationArea);
				while (validationArea!=null && validationArea.childNodes[0])
				{
					 validationArea.removeChild(validationArea.childNodes[0]);
				}
				while (validationTypeTr.childNodes[0])
				{
					 validationTypeTr.removeChild(validationTypeTr.childNodes[0]);
				}
				
				validationTypeTr.style.visibility="visible";
				var td = document.createElement("td");
				var label = document.createElement("label");
				label.innerHTML= " Validation Type : ";
				td.appendChild(label);
				validationTypeTr.appendChild(td);
				td = document.createElement("td");
				
				if(!isDate && !isDoubleInput)
				{			
					var input = document.createElement("select");
					var option = document.createElement('option');
					option.innerHTML = '---Select---';
					option.value = '-1';				
					input.appendChild(option);	
					option = document.createElement('option');
					option.innerHTML = 'Entry should be fixed length';
					option.value = '<%=ValidationDTOConstants.FIXED_LENGTH%>';				
					input.appendChild(option);
					
					option = document.createElement('option');
					option.innerHTML = 'Entry should have length range';
					option.value = '<%=ValidationDTOConstants.LENGTH_RANGE%>';				
					input.appendChild(option);
					
					option = document.createElement('option');
					option.innerHTML = 'Entry should have value range';
					option.value = '<%=ValidationDTOConstants.VALUE_IN_RANGE%>';				
					input.appendChild(option);
					
					option = document.createElement('option');
					option.innerHTML = 'Entry should start with';
					option.value = '<%=ValidationDTOConstants.STARTS_WITH_CHARS%>';				
					input.appendChild(option);
					
					option = document.createElement('option');
					option.innerHTML = 'Entry should contain';
					option.value = '<%=ValidationDTOConstants.CONTAIN_CHARS%>';				
					input.appendChild(option);	
					input.id = "validationType";
					input.name = "question.validationType";
					input.onchange = function(){  
		               				showValidation('validation_area');
		               			};
					//input.type = "text"; 
					td.appendChild(input);		
				}
				else if(isDate)
				{
					var input = document.createElement("select");
					var option = document.createElement('option');
					option.innerHTML = '---Select---';
					option.value = '-1';				
					input.appendChild(option);
					option = document.createElement('option');
					option.innerHTML = 'Maximum Date';
					option.value = '<%=ValidationDTOConstants.MAX_DATE%>Validation';				
					input.appendChild(option);
					
					option = document.createElement('option');
					option.innerHTML = 'Minimum Date';
					option.value = '<%=ValidationDTOConstants.MIN_DATE%>Validation';				
					input.appendChild(option);
					
					option = document.createElement('option');
					option.innerHTML = 'Date Range';
					option.value = '<%=ValidationDTOConstants.DATE_RANGE%>Validation';				
					input.appendChild(option);
					
					option = document.createElement('option');
					option.innerHTML = 'Not Before';
					option.value = '<%=ValidationDTOConstants.NOT_BEFORE%>Validation';				
					input.appendChild(option);
					
					option = document.createElement('option');
					option.innerHTML = 'Not After';
					option.value = '<%=ValidationDTOConstants.DATE_RANGE%>Validation';				
					input.appendChild(option);
					
					option = document.createElement('option');
					option.innerHTML = 'Past Date';
					option.value = '<%=ValidationDTOConstants.PAST_DATE%>Validation';				
					input.appendChild(option);
					
					option = document.createElement('option');
					option.innerHTML = 'Future Date';
					option.value = '<%=ValidationDTOConstants.FUTURE_DATE%>Validation';				
					input.appendChild(option);
					
					input.id = "validationType";
					input.name = "question.validationType";
					input.onchange = function(){  
		               				showValidation('validation_area');
		               			};
					//input.type = "text"; 
					td.appendChild(input);
				}
				else if(isDoubleInput)
				{
					var span = document.createElement("span");			
					var input=document.createElement("input");
				 	input.type = "checkbox";
				 	if(firstFieldvalidationChecked)
			 		{
				 		input.checked = true;
				 		firstFieldvalidationChecked =false;
			 		}
				 	input.id = "firstFieldValidation";
				 	input.name = "firstFieldValidation";
				 	input.value = "firstFieldValidation";
				 	input.onclick = function(){  
		   				showDoubeInput('firstFieldValidation');
		   			};
					span.appendChild(input);
					var label=document.createElement("label");
				 	label.innerHTML='First Field Validation';						 	
				 	span.appendChild(label); 
					td.appendChild(span);
					
					span = document.createElement("span");
					
					input=document.createElement("input");
				 	input.type = "checkbox";
				 	if(secondFieldvalidationChecked)
			 		{
				 		input.checked = true;
				 		secondFieldvalidationChecked =false;
			 		}
				 	input.id = "secondFieldValidation";
				 	input.name = "secondFieldValidation";
				 	input.value = "secondFieldValidation";
				 	input.onclick = function(){  
		   				showDoubeInput('secondFieldValidation');
		   			};
					span.appendChild(input);
					var label=document.createElement("label");
				 	label.innerHTML='Second Field Validation';						 	
				 	span.appendChild(label); 
					td.appendChild(span);
				}
				
				validationTypeTr.appendChild(td);
			}
			else
			{
				validationTypeTr.style.visibility="hidden";
			}
		}
		
	}
	
	
</script>
	<%
	if(questionDTO!=null)
	{
		%>
		<script type="text/javascript">
			modQuestionCategory='<%=(questionDTO.getCategoryType()).getComponentId()%>';
			modAnswerType='<%=(questionDTO.getAnswerType()).getComponentId()%>';
			modAnswerTypeName='<%=(questionDTO.getAnswerType()).getAnswerTypeName()%>';
		</script>
		<%
		
		System.out.println("name::::::::::::::::::::::::::::"+questionDTO.getQuestionName());
		System.out.println("category::::::::::::::::::::::::::::"+(questionDTO.getCategoryType()).getComponentId());
		System.out.println("answer type::::::::::::::::::::::::::::"+(questionDTO.getAnswerType()).getComponentId());
	}
	%>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=question.add.screenname&screenTipTextKey=question.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=question.modify.screenname&screenTipTextKey=question.modify.tiptext"></jsp:include>
</logic:equal>
<html:form  styleId="questionForm"  action="questionAction.cms?method=" method="POST" onsubmit="return isQuestionModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareQuestionModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareQuestionModifyAction&methodParams=modify,cancel"></jsp:include>
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
						  <label class="desc"><bean:message key="label.question.name" /></label>
						</td>
						<td>
							<html:text property="question.questionName" styleId="question.questionName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					<!-- question id-->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.question.questionId" /></label>
						</td>
						<td>
							<html:text property="question.questionId" styleId="question.questionId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					<!--  Category of question -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.question.categoryType" /></label>
						</td>
						<td>								
								<html:select property="category" styleId="category"  tabindex="7"  >
									<html:option value="-1">---Select---</html:option>
									<html:options collection="<%=SESSION_KEYS.CATEGORY_LIST%>" property="componentId" labelProperty="categoryName"/>
								</html:select>
							</td>
					</tr>
					
					
					 <!--  Answer type of question -->
					<tr>
						<td>
						   <font color="red">*</font> <label class="desc"><bean:message key="label.question.answerType" /></label>
						</td>
						<td>								
								<html:select property="answerType" styleId="answerType" onchange="mcqOptions(answerType.value);" tabindex="7"  >
									<html:option value="-1">---Select---</html:option>
									<html:options collection="<%=SESSION_KEYS.ANSWERTYPE_LIST%>"  property="componentId" labelProperty="answerTypeName"/>
								</html:select>
						</td>
						
						
					</tr>
			
					
					
					<tr id="mcqOptions" style="visibility:hidden">
					<td>
					     <font color="red">*</font> <label class="desc"><bean:message key="label.question.mcqNumber" /></label>
					</td>
					<td>								
							<select name="question.optionList" id="mcqOption"  tabindex="7" onchange="showMCQOptions();"  >
								<option value="-1">---Select---</option>
								<option <%if(questionMCQNumber==2){out.println("selected");}%> value="2">2</option>
								<option <%if(questionMCQNumber==3){out.println("selected");}%> value="3">3</option>
								<option <%if(questionMCQNumber==4){out.println("selected");}%> value="4">4</option>
								<option <%if(questionMCQNumber==5){out.println("selected");}%> value="5">5</option>
								<option <%if(questionMCQNumber==6){out.println("selected");}%> value="6">6</option>
								<option <%if(questionMCQNumber==7){out.println("selected");}%> value="7">7</option>
								<option <%if(questionMCQNumber==8){out.println("selected");}%> value="8">8</option>
								<option <%if(questionMCQNumber==9){out.println("selected");}%> value="9">9</option>
								<option <%if(questionMCQNumber==10){out.println("selected");}%> value="10">10</option>
								<option <%if(questionMCQNumber==11){out.println("selected");}%> value="11">11</option>
							</select>
					</td>
					
					</tr>	
					<%
					String visibility="hidden";
					if(questionMCQNumber>0)
					{
						visibility = "visible";
					}
					%>
					<tr id="mcqOptionsDtl" style="visibility:<%=visibility%>">
						<td colspan="2">								
							<ol id="mcq_area">
							<%
							if(questionDTO!=null && questionDTO.getMcqOptionList()!=null){
								for(int i=0;i<mcqOptionList.size();i++)
								{		
									mcqOptionDTO = (MCQOptionDTO)mcqOptionList.get(i);						
									%>
									<li>
										Question : <input type="text" id="mcq<%=i%>" name="mcq<%=i%>" value="<%=mcqOptionDTO.getName()%>"/>
										Value : <input type="text" id="value<%=i%>" name="value<%=i%>" value="<%=mcqOptionDTO.getValue()%>"/>
									</li>
									<%
								}
							}
							%>
							</ol>
						</td>
			
					</tr>
					<tr> 
					<td>Validation</td>
					
					<td><input type="checkbox" name="validationOption" onclick="showHide();"></td>
					</tr>
					<%
					String validationType = "";
					if(validationDTO!=null)
					{
						visibility="visible";
						%>
							<script type="text/javascript">
								getElementsByName_iefix('input','validationOption')[0].checked = true;								
							</script>
						<%
						
					}
					else
					{
						visibility="hidden";
					}
					%>
					<tr id="validationTypeTr" style="visibility:<%=visibility%>">
						<td>
					    	<label class="desc"><bean:message key="label.question.validationType" /></label>
						</td>
						<td>		
						<% 
						if(validationDTO!=null && validationDTO.getAnsTypeCode() == ValidationDTOConstants.DOUBLE_VALUE_ANSTYPE_CODE)
						{
							System.out.println("first::"+validationDTO.getValidationValue()+" second::"+validationDTO.getSecondValidationValue());
						%>
							<input type="checkbox" id="firstFieldValidation" name="firstFieldValidation" <%=validationDTO.getValidationName()!=null?"checked='yes'":""%> onclick="showDoubeInput('firstFieldValidation');">
							<label>First Field Validation</label>							 
							
							<input type="checkbox" id="secondFieldValidation" name="secondFieldValidation" <%=validationDTO.getSecondValidationName()!=null?"checked='yes'":""%> onclick="showDoubeInput('secondFieldValidation');">
							<label>Second Field Validation</label>
						<%
						}
						else if (validationDTO!=null && validationDTO.getAnsTypeCode() == ValidationDTOConstants.DATE_ANSTYPE_CODE)
						{
							System.out.println("in date input");
							validationType = validationDTO.getValidationName();
							if(validationType!=null)
							{
							%>	
								<select name="question.validationType" id="validationType"  tabindex="8" onchange="showValidation('validation_area','');"  >
									<option value="-1">---Select---</option>
									<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.MAX_DATE)){out.println("selected");}%> value="<%=ValidationDTOConstants.MAX_DATE%>Validation">Maximum Date</option>								
									<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.MIN_DATE)){out.println("selected");}%> value="<%=ValidationDTOConstants.MIN_DATE%>Validation">Minimum Date</option>
									<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.DATE_RANGE)){out.println("selected");}%> value="<%=ValidationDTOConstants.DATE_RANGE%>Validation">Date Range</option>
									<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.NOT_AFTER)){out.println("selected");}%> value="<%=ValidationDTOConstants.DATE_RANGE%>Validation">Not After</option>
									<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.NOT_BEFORE)){out.println("selected");}%> value="<%=ValidationDTOConstants.NOT_BEFORE%>Validation">Not Before</option>								
									<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.PAST_DATE)){out.println("selected");}%> value="<%=ValidationDTOConstants.PAST_DATE%>Validation">Past Date</option>
									<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.FUTURE_DATE)){out.println("selected");}%> value="<%=ValidationDTOConstants.FUTURE_DATE%>Validation">Future Date</option>
								</select>
							<%
							}
						}
						else if(validationDTO!=null)
						{
							System.out.println("in else");
							validationType = validationDTO.getValidationName();
							if(validationType!=null)
							{
							%>
							<select name="question.validationType" id="validationType"  tabindex="8" onchange="showValidation('validation_area');"  >
								<option value="-1">---Select---</option>
								<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.FIXED_LENGTH)){out.println("selected");}%> value="<%=ValidationDTOConstants.FIXED_LENGTH%>">Entry should be fixed length</option>								
								<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.LENGTH_RANGE)){out.println("selected");}%> value="<%=ValidationDTOConstants.LENGTH_RANGE%>">Entry should have length range</option>
								<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.VALUE_IN_RANGE)){out.println("selected");}%> value="<%=ValidationDTOConstants.VALUE_IN_RANGE%>">Entry should have value range</option>
								<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.STARTS_WITH_CHARS)){out.println("selected");}%> value="<%=ValidationDTOConstants.STARTS_WITH_CHARS%>">Entry should start with</option>
								<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.CONTAIN_CHARS)){out.println("selected");}%> value="<%=ValidationDTOConstants.CONTAIN_CHARS%>">Entry should contain</option>								
							</select>
							<%
							}
						}
						%>
						</td>
					</tr>
					<tr id="validationDetail" style="visibility:<%=visibility%>">
						<td colspan="2">
							<div id="validation_double_input_area">
								<span id="first_option_area">
								<%
								if(validationDTO!=null && validationDTO.getAnsTypeCode()==ValidationDTOConstants.DOUBLE_VALUE_ANSTYPE_CODE && validationDTO.getValidationValue()!=null)
								{
									validationType = validationDTO.getValidationName();
								%>
									<li>
									<label>First Field Validations</label>
									<select name="firstFieldValidationType" id="firstFieldValidationType"  tabindex="8" onchange="showValidation('validation_double_input_area','firstFieldValidation');"  >
										<option value="-1">---Select---</option>
										<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.FIXED_LENGTH)){out.println("selected");}%> value="<%=ValidationDTOConstants.FIXED_LENGTH%>">Entry should be fixed length</option>								
										<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.LENGTH_RANGE)){out.println("selected");}%> value="<%=ValidationDTOConstants.LENGTH_RANGE%>">Entry should have length range</option>
										<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.VALUE_IN_RANGE)){out.println("selected");}%> value="<%=ValidationDTOConstants.VALUE_IN_RANGE%>">Entry should have value range</option>
										<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.STARTS_WITH_CHARS)){out.println("selected");}%> value="<%=ValidationDTOConstants.STARTS_WITH_CHARS%>">Entry should start with</option>
										<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.CONTAIN_CHARS)){out.println("selected");}%> value="<%=ValidationDTOConstants.CONTAIN_CHARS %>">Entry should contain</option>								
									</select>
									</li>
								<%
								}
								%>
								</span>
								<span id="first_validation_area">
								<%
									if(validationDTO!=null && validationDTO.getAnsTypeCode()==ValidationDTOConstants.DOUBLE_VALUE_ANSTYPE_CODE)
									{
										if( validationDTO.getValidationValue()!=null && ! validationDTO.getValidationValue().equals("null") && validationDTO.getValidationValue().length()>0)
										{
											System.out.println("what happend");
											out.println(getValidationFields(validationDTO,1));
										}
									}									
								%>
								</span>
								<span id="second_option_area">
								<%
								if(validationDTO!=null && validationDTO.getSecondValidationValue()!=null && validationDTO.getSecondValidationValue().length()>0 && !validationDTO.getSecondValidationValue().equals("null"))
								{
									validationType = validationDTO.getSecondValidationName();
									System.out.println("validationDTO.getSecondValidationName()::"+validationDTO.getSecondValidationName());
								%>
									<li>
									<label>Second Field Validations</label>
									<select name="secondFieldValidationType" id="secondFieldValidationType"  tabindex="8" onchange="showValidation('validation_double_input_area','secondFieldValidation');"  >
										<option value="-1">---Select---</option>
										<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.FIXED_LENGTH)){out.println("selected");}%> value="<%=ValidationDTOConstants.FIXED_LENGTH%>">Entry should be fixed length</option>								
										<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.LENGTH_RANGE)){out.println("selected");}%> value="<%=ValidationDTOConstants.LENGTH_RANGE%>">Entry should have length range</option>
										<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.VALUE_IN_RANGE)){out.println("selected");}%> value="<%=ValidationDTOConstants.VALUE_IN_RANGE%>">Entry should have value range</option>
										<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.STARTS_WITH_CHARS)){out.println("selected");}%> value="<%=ValidationDTOConstants.STARTS_WITH_CHARS%>">Entry should start with</option>
										<option <%if(validationType.equalsIgnoreCase(ValidationDTOConstants.CONTAIN_CHARS)){out.println("selected");}%> value="<%=ValidationDTOConstants.CONTAIN_CHARS %>">Entry should contain</option>								
									</select>
									</li>
								<%
								}
								%>
								</span>
								<span id="second_validation_area">
									<%
										if(validationDTO!=null && validationDTO.getAnsTypeCode()==ValidationDTOConstants.DOUBLE_VALUE_ANSTYPE_CODE)
										{
											if( validationDTO.getSecondValidationValue()!=null && !validationDTO.getSecondValidationValue().equals("null") &&  validationDTO.getSecondValidationValue().length()>0)
											{
												out.println(getValidationFields(validationDTO,2));
											}
										}
									%>
								</span>
							</div>								
							<ol id="validation_area">
							<%
								if(validationDTO!=null && validationDTO.getAnsTypeCode()!=ValidationDTOConstants.DOUBLE_VALUE_ANSTYPE_CODE)
								{
									System.out.println("in normal input");
									out.println(getValidationFields(validationDTO,0));
								}
							%>
							</ol>							
						</td>
					</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>		
		<script type="text/javascript">loadModify();</script>
			<input type="hidden" id="mcqOptionNumber" name="mcqOptionNumber" value="<%=questionMCQNumber%>">
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareQuestionModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareQuestionModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>

