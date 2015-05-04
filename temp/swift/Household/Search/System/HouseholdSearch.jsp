<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareHouseholdAction(actionName);
	} 
		
	function prepareHouseholdAction(actionName)
	{
		if(document.getElementById('householdForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('householdForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddHousehold';
		}
		else if(actionName == 'search')
		{
			path += 'searchHouseholdFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchHousehold';
		}
		document.getElementById('householdForm').action = path;
		document.getElementById('householdForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=household.search.screenname&screenTipTextKey=household.search.tiptext"></jsp:include>
 
<html:form styleId="householdForm" action="householdAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareHouseholdAction&methodParams=cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/SearchResult.jsp?component=household"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareHouseholdAction&methodParams=cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
