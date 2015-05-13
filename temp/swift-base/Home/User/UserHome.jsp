<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.user.dto.UserDTO" %>
<%@ page import="com.swiftcorp.portal.common.login.dto.LoginDetailInfoDTO" %>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS" %>
<link rel="stylesheet" type="text/css" href="./Common/Styles/common-styles.css">
<%
LoginDetailInfoDTO loginInfo = (LoginDetailInfoDTO)session.getAttribute(SESSION_KEYS.LOGIN_INFO);
UserDTO userDTO = loginInfo.getUser();
%>
<script type="text/javascript">
var userId='<%=userDTO.getComponentId()%>';
//window.open ("alertAction.csmp?method=promptAlertSearchByUser&userId=<%=userDTO.getComponentId()%>",
//"mywindow","menubar=1,resizable=1,width=550,height=350");
</script>
<table width="100%" valign='top' border="0" cellspacing="0" cellpadding="0" height="10%">
 
	<%--Shows Success messages if present--%>
	<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
	<%--Shows Error messages if present--%>
	<%@ include file="/Common/Message/ErrorMessage.jsp"%>
 								
</table>
