<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="java.util.ArrayList"%>

<script type="text/javascript">

		
	function prepareSubmitAction(actionName)
	{
      prepareUserAction(actionName);
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
	function prepareUserAction(actionName)
	{
		if(document.getElementById('userForm') == null)
		{
			alert('Error:: Form is Found Null');
			return;
		}
		var path = document.getElementById('userForm').action;
		
		if(actionName == 'remove' )
		{				
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
				alert("Please Select At Least One User To Remove");
				return;
			}
			else if(chks == 'undefiend' || chks.length==0)
			{
				alert("There Is No User To Remove");
				return;
			}				
		}
		
		if(actionName == 'add')
		{
			path += 'promptAddUser';
		}
		else if(actionName == 'search')
		{
			path += 'searchUserFromSystemLevel';
		}		
		else if(actionName == 'cancelsearch')
		{
			path += 'cancelSearchUser';
		}
		else if(actionName == 'remove')
		{
			path += 'removeUser';
		}
		document.getElementById('userForm').action = path;
		document.getElementById('userForm').submit();
	}
	
	function isValidSubmit()
	{
		
			return true;
		
	}
</script>


<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=user.search.screenname&screenTipTextKey=user.search.tiptext"></jsp:include>
 
<html:form styleId="userForm" action="userAction.cms?method=" method="POST" onsubmit="return isValidSubmit()" target="_self">
	<table style="width: 100%;">
		<tbody>
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			

             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel,remove&methodName=prepareUserAction&methodParams=add,cancelsearch,remove"></jsp:include>
			
			<jsp:include flush="true" page="/User/Search/System/SearchResult.jsp?component=user"></jsp:include>
			
             <jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=add,cancel,remove&methodName=prepareUserAction&methodParams=add,cancelsearch,remove"></jsp:include>
			
		</tbody> 
	</table>
</html:form>





