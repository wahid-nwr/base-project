<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>

<%--Shows Success messages if present--%>
<logic:messagesPresent message="true">				
	<tr id="success_message">
		<td colspan="2">
			<table class="successmessage" border="0" width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15px">
						<img border="0px" src="Common/Styles/Images/successmessage.gif" width="15px" height="15px">
					</td>
					<td>							
						<html:messages id="message" message="true">
							<p class="successmessagetext"><bean:write name="message"/>
						</html:messages>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</logic:messagesPresent>