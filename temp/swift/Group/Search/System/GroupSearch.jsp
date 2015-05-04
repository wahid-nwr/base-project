<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareGroupAction(actionName);
	} 
	
	
	function prepareGroupAction(actionName)
	{
		if(document.getElementById('groupForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('groupForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddGroup';
		}
		else if(actionName == 'search')
		{
			path += 'searchGroupFromSystemLevel';
		}
		else if(actionName == 'search')
		{
			path += 'searchGroupFromSystemLevel';
		}				
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchGroup';
		}
		document.getElementById('groupForm').action = path;
		document.getElementById('groupForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>

<%
	request.setAttribute("featureId","100");
%>

<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=group.search.screenname&screenTipTextKey=group.search.tiptext"></jsp:include>
 
<html:form styleId="groupForm" action="groupAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareGroupAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/SearchResult.jsp?component=group"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareGroupAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
