<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareRouteAction(actionName);
	} 
		
	function prepareRouteAction(actionName)
	{
		if(document.getElementById('routeForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('routeForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddRoute';
		}
		else if(actionName == 'search')
		{
			path += 'searchRouteFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchRoute';
		}
		document.getElementById('routeForm').action = path;
		document.getElementById('routeForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=route.search.screenname&screenTipTextKey=route.search.tiptext"></jsp:include>
 
<html:form styleId="routeForm" action="routeAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareRouteAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/ExtSearchResult.jsp?component=route"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareRouteAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
