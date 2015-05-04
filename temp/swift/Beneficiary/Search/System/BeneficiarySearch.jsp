<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript" src="./DatePicker/datetimepicker_css.js"></script>
<style type="text/css">
.bottomtext img
{
  border:none;
}
A{text-decoration: none;} 
A:link
{
	color: #3399cc;
}
A:visited{color: #3399cc;}
A:hover
{
	color: #cc0033;
}
A img
{
  border:none;
}
</style>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{	
      prepareBeneficiaryAction(actionName);
	} 
		
	function prepareBeneficiaryAction(actionName)
	{
		
		if(document.getElementById('beneficiaryForm') == null)
		{
			alert('Error:: Form is found null');
			return;
		}
		
		var path = document.getElementById('beneficiaryForm').action;
		if(actionName == 'add')
		{
			path += 'promptAddBeneficiary';
		}
		else if(actionName == 'search')
		{
			path += 'searchBeneficiaryFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchBeneficiary';
		}
		document.getElementById('beneficiaryForm').action = path;
		document.getElementById('beneficiaryForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=beneficiary.search.screenname&screenTipTextKey=beneficiary.search.tiptext"></jsp:include>
 
<html:form styleId="beneficiaryForm" action="beneficiaryAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareBeneficiaryAction&methodParams=cancelsearch"></jsp:include>

			<jsp:include flush="true" page="/Beneficiary/Search/System/SearchResult.jsp?component=beneficiary"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareBeneficiaryAction&methodParams=cancelsearch"></jsp:include>
			
		</tbody> 
	</table>
	<!--link href="./DatePicker/rfnet.css" rel="stylesheet" type="text/css"-->
</html:form>
