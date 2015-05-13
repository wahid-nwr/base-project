<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareEmailrecipientsAction(actionName);
	} 
		
	function prepareEmailrecipientsAction(actionName)
	{
		if(document.getElementById('emailrecipientsForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('emailrecipientsForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddEmailrecipients';
		}
		else if(actionName == 'search')
		{
			path += 'searchEmailrecipientsFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchEmailrecipients';
		}
		document.getElementById('emailrecipientsForm').action = path;
		document.getElementById('emailrecipientsForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emailrecipients.search.screenname&screenTipTextKey=emailrecipients.search.tiptext"></jsp:include>
 
<html:form styleId="emailrecipientsForm" action="emailrecipientsAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareEmailrecipientsAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/ExtSearchResult.jsp?component=emailrecipients"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareEmailrecipientsAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
