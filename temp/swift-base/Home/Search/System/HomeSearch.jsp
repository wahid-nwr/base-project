<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareHomeAction(actionName);
	} 
		
	function prepareHomeAction(actionName)
	{
		if(document.getElementById('homeForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('homeForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddHome';
		}
		else if(actionName == 'search')
		{
			path += 'searchHomeFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchHome';
		}
		document.getElementById('homeForm').action = path;
		document.getElementById('homeForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=home.search.screenname&screenTipTextKey=home.search.tiptext"></jsp:include>
 
<html:form styleId="homeForm" action="homeAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareHomeAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/ExtSearchResult.jsp?component=home"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareHomeAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
