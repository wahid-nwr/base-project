<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareScheduleAction(actionName);
	} 
		
	function prepareScheduleAction(actionName)
	{
		if(document.getElementById('scheduleForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('scheduleForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddSchedule';
		}
		else if(actionName == 'search')
		{
			path += 'searchScheduleFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchSchedule';
		}
		document.getElementById('scheduleForm').action = path;
		document.getElementById('scheduleForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=schedule.search.screenname&screenTipTextKey=schedule.search.tiptext"></jsp:include>
 
<html:form styleId="scheduleForm" action="scheduleAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareScheduleAction&methodParams=cancelsearch,search"></jsp:include>
			
			<jsp:include flush="true" page="/Schedule/Search/System/SearchResult.jsp?component=schedule"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareScheduleAction&methodParams=cancelsearch,search"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
