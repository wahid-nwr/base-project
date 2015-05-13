<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareTransactionAction(actionName);
	} 
		
	function prepareTransactionAction(actionName)
	{
		if(document.getElementById('transactionForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('transactionForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddTransaction';
		}
		else if(actionName == 'search')
		{
			path += 'searchTransactionFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchTransaction';
		}
		document.getElementById('transactionForm').action = path;
		document.getElementById('transactionForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=transaction.search.screenname&screenTipTextKey=transaction.search.tiptext"></jsp:include>
 
<html:form styleId="transactionForm" action="transactionAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareTransactionAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/ExtSearchResult.jsp?component=transaction"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareTransactionAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
