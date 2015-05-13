<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>

<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareSamplecomAction(actionName);
	} 
		
	function prepareSamplecomAction(actionName)
	{
		if(document.getElementById('samplecomForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('samplecomForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddSamplecom';
		}
		else if(actionName == 'search')
		{
			path += 'searchSamplecomFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchSamplecom';
		}
		document.getElementById('samplecomForm').action = path;
		document.getElementById('samplecomForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>


 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=samplecom.search.screenname&screenTipTextKey=samplecom.search.tiptext"></jsp:include>
 
<html:form styleId="samplecomForm" action="samplecomAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareSamplecomAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/ExtSearchResult.jsp?component=samplecom"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareSamplecomAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>