<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareDcrreportAction(actionName);
	} 
		
	function prepareDcrreportAction(actionName)
	{
		if(document.getElementById('dcrreportForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('dcrreportForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddDcrreport';
		}
		else if(actionName == 'search')
		{
			path += 'searchDcrreportFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchDcrreport';
		}
		document.getElementById('dcrreportForm').action = path;
		document.getElementById('dcrreportForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=dcrreport.search.screenname&screenTipTextKey=dcrreport.search.tiptext"></jsp:include>
 
<html:form styleId="dcrreportForm" action="dcrreportAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareDcrreportAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/SearchResult.jsp?component=dcrreport"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareDcrreportAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
