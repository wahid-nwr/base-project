<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>
<script type="text/javascript">
	
		
	function prepareSubmitAction(actionName)
	{
      prepareAlertAction(actionName);
	} 
	function getElementsByName_iefix(tag, name) {     
	     var elem = document.getElementsByTagName(tag);
	     var arr = new Array();
	     for(i = 0,iarr = 0; i < elem.length; i++) {
	          att = elem[i].getAttribute("name");
	          if(att == name) {
	               arr[iarr] = elem[i];
	               iarr++;
	          }
	     }
	     return arr;
	}	
	function prepareAlertAction(actionName)
	{
		if(document.getElementById('alertForm') == null)
		{
			alert('Error:: Form is Found Null');
			return;
		}
		
		var path = document.getElementById('alertForm').action;
		
		if(actionName == 'remove' )
		{
			//var chks = document.getElementsByName('deleteCheck');
			var chks = getElementsByName_iefix('input','deleteCheck');
			var checkedAtLeastOne = false;
			
			for(var i=0;i<chks.length;i++)
			{
				if(chks[i].checked)
				{
					checkedAtLeastOne = true;
					break;
				}						
			}
			if(chks.length>0 && !checkedAtLeastOne)
			{
				alert("Please Select At Least One Alert To Remove");
				return;
			}
			else if(chks == 'undefiend' || chks.length==0)
			{
				alert("There Is No Alert To Remove");
				return;
			}	
		}
		
		
		if(actionName == 'add')
		{
			path += 'promptAddAlert';
		}
		else if(actionName == 'search')
		{
			path += 'searchAlertFromSystemLevel';
		}
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchAlert';
		}
		else if(actionName == 'remove')
		{
			path += 'removeAlert';
		}
		document.getElementById('alertForm').action = path;
		document.getElementById('alertForm').submit();
	}
	
	function isValidSubmit()
	{
		return true;
	}
</script>
 <jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=alert.search.screenname&screenTipTextKey=alert.search.tiptext"></jsp:include>
 
<html:form styleId="alertForm" action="alertAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel,remove&methodName=prepareAlertAction&methodParams=add,cancelsearch,remove"></jsp:include>
			
			<jsp:include flush="true" page="/Alert/Search/System/SearchResult.jsp?component=alert"></jsp:include>
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel,remove&methodName=prepareAlertAction&methodParams=add,cancelsearch,remove"></jsp:include>
			
		</tbody> 
	</table>
</html:form>
