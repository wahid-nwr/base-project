<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>

 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=dcrinfo.search.screenname&screenTipTextKey=dcrinfo.search.tiptext"></jsp:include>
 
<html:form styleId="dcrinfoForm" action="dcrinfoAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareDcrinfoAction&methodParams=add,cancelsearch"></jsp:include>
			
			<jsp:include flush="true" page="/Common/Search/ExtSearchResult.jsp?component=dcrinfo"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel&methodName=prepareDcrinfoAction&methodParams=add,cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
	
</html:form>
