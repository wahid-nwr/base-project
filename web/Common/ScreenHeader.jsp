<%@ include file="/Common/Include/taglib.jsp"%>

<%
	String screenNameKey = request.getParameter("screenNameKey");
	String screenTipTextKey = request.getParameter("screenTipTextKey");
%>

<%
	if(screenNameKey != null)
	{
%>
<table  align="center" width="100%" class="ScreenHeader">
  <tbody>
  

		<tr height='30' valign='bottom'>
	  		<td class="screenName">
	  			<bean:message   key="<%= screenNameKey %>"/>
	  		</td>
	  	</tr>
	<%
		if(screenTipTextKey != null)
		{
	%>  
		  	<tr height='25' valign='top'>
		  		<td class="tip">
		  			<bean:message key="<%= screenTipTextKey %>"/>
		  		</td>
		  	</tr>
	<%
		}
	%>	
		  	
    </tbody>
</table>
<%
	}
%>
