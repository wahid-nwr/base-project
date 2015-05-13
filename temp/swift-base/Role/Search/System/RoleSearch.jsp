<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareRoleAction(actionName);
	} 
	
	
	function prepareRoleAction(actionName)
	{
		if(document.getElementById('roleForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('roleForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddRole';
		}
		else if(actionName == 'search')
		{
			path += 'searchRoleFromSystemLevel';
		}						
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchRole';
		}
		document.getElementById('roleForm').action = path;
		document.getElementById('roleForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=role.search.screenname&screenTipTextKey=role.search.tiptext"></jsp:include>
 
<html:form styleId="roleForm" action="roleAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareRoleAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/SearchResult.jsp?component=role"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareRoleAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
