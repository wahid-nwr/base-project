<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareQuestionAction(actionName);
	} 
		
	function prepareQuestionAction(actionName)
	{
		if(document.getElementById('questionForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('questionForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddQuestion';
		}
		else if(actionName == 'search')
		{
			path += 'searchQuestionFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchQuestion';
		}
		document.getElementById('questionForm').action = path;
		document.getElementById('questionForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=question.search.screenname&screenTipTextKey=question.search.tiptext"></jsp:include>
 
<html:form styleId="questionForm" action="questionAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
           <!--  <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareQuestionAction&methodParams=add,cancelsearch"></jsp:include>  -->
			
			  <tr>
					<td class='bottom_border'>&nbsp;<font size="2"><b><a href="questionAction.csmp?method=promptQuestionSearchSystemLevel">Add Question</a></b></font></td>
			  
			 
					<td class='bottom_border'>&nbsp;<font size="2"><b><a href="questionAction.csmp?method=promptQuestionSearchSystemLevel">View Question</a></b></font></td>
			 
			  
					<td class='bottom_border'>&nbsp;<font size="2"><b><a href="questionAction.csmp?method=promptQuestionSearchSystemLevel">Update Question</a></b></font></td>
			  
			</tr>
           <!--  <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareQuestionAction&methodParams=add,cancelsearch"></jsp:include>  -->
			
		</tbody> 
	</table>
</html:form>
