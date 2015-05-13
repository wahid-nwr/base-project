<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareMailinfoAction(actionName);
	} 
		
	function prepareMailinfoAction(actionName)
	{
		if(document.getElementById('mailinfoForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('mailinfoForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddMailinfo';
		}
		else if(actionName == 'search')
		{
			path += 'searchMailinfoFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchMailinfo';
		}
		document.getElementById('mailinfoForm').action = path;
		document.getElementById('mailinfoForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=mailinfo.search.screenname&screenTipTextKey=mailinfo.search.tiptext"></jsp:include>
 
<html:form styleId="mailinfoForm" action="mailinfoAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareMailinfoAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/ExtSearchResult.jsp?component=mailinfo"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareMailinfoAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
