<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>

<%--Shows Error messages if present--%>
<logic:messagesPresent>		
	<tr id="error_message">
		<td colspan="2">
			<table class="errormessage" border="0" width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15px">
						<img border="0px" src="Common/Styles/Images/errormessage.gif" width="15px" height="15px">
					</td>
					<td>
						<p class="errormessagetext">
							<html:errors bundle="Errors"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>		
</logic:messagesPresent>