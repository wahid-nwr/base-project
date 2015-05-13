<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareEmailRecvAction(actionName);
	} 
		
	function prepareEmailRecvAction(actionName)
	{
		if(document.getElementById('emailrecvForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('emailrecvForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddEmailRecv';
		}
		else if(actionName == 'search')
		{
			path += 'searchEmailRecvFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchEmailRecv';
		}
		document.getElementById('emailrecvForm').action = path;
		document.getElementById('emailrecvForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=emailrecv.search.screenname&screenTipTextKey=emailrecv.search.tiptext"></jsp:include>
 
<html:form styleId="emailrecvForm" action="emailrecvAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareEmailRecvAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/ExtSearchResult.jsp?component=emailrecv"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareEmailRecvAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
