<%@ page contentType="text/html charset=UTF-8"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.*"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ include file="/Common/Include/taglib.jsp"%>
<%@ include file="login_progress.jsp" %>
 <html xmlns="http://www.w3.org/1999/xhtml">
	<head> 
	<title>Enterprise Resource Planning Platform</title>
  	<link rel="shortcut icon" href="../images/hsbc-icon.jpg" type="image/jpg" />
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<link rel="stylesheet" type="text/css" href="./Common/JavaScript/resources/all.css"></link>   
	<script type="text/javascript" src="./js/yui/yahoo/yahoo-min.js"></script>
	<script type="text/javascript" src="./js/yui/dom/dom-min.js"></script>
	<script type="text/javascript" src="./js/yui/event/event-min.js"></script>
	<script type="text/javascript" src="./js/yui/connection/connection-min.js"></script>
	<script type="text/javascript" src="./js/prototype.js"></script>
	<script type="text/javascript" src="./js/rico.js"></script>
	<script type="text/javascript" src="./js/mm_functions.js"></script>
	<script type="text/javascript" src="./js/script.js"></script>
	<script type="text/javascript" src="./js/layout.js"></script>
	<script type="text/javascript" src="./js/operations.js"></script> 
<script type="text/javascript">
function getContextPath()
{
	var contextPath = '<%=request.getContextPath()%>';
	//alert('contextPath::'+contextPath);
    window.location = contextPath+'/loginAction.csmp?method=loginSuccess';
}
  	
</script>
  
</head>



<body oncontextmenu="return false" onselectstart="return selectstart(event)" onload="initLogin(event)">
<div id="login" align="center" style="visibility:hidden">
   	  <form id="loginForm" method="post">
		<div id="mylogin">
		  <div id="loginResult">Please Login</div>
		  <div id="loginTable">
		  	<table border="0" cellspacing="1" cellpadding="5" width="400">
		  	  <tr>
		  		<td width="130" align="right"><font size="1.1">Username : </font></td>
		  		<td width="270" align="left"><input name="username" type="text" id="username" tabindex="1" onkeydown="return(loginCatchEnter(event))" style="width:175px"/></td>
		  	  </tr>
		  	  <tr>
		  	  	<td align="right"><font size="1.1">Password : </font></td>
		  	  	<td align="left"><input name="password" type="password" id="password" tabindex="2" onkeydown="return(loginCatchEnter(event))" style="width:175px"/></td>
		  	  </tr>
		  	  <tr>
		  	  	<td>&nbsp;</td>
		  	  	<td align="left">
				  <table height="23" border="0" cellspacing="0" cellpadding="0" width="50">
					<tr>
					  <td width="1%"><img src="images/button-left-bg.gif" width="9" height="23"/></td>
					  <td nowrap="nowrap" style="background-image: url(images/button-bg.gif);text-align:center;padding-left:15px;padding-right:15px;cursor:pointer" width="98%" height="23" onclick="login();"><font size="1.1">Login</font></td>
					  <td width="1%"><img src="images/button-right-bg.gif" width="9" height="23"/></td>
					</tr>
				  </table>
		  	  	</td>
		  	  </tr>
			  <tr>
				<td align="right">&nbsp;</td>
				<td align="right" style="padding-top:0px;"><a href="http://www.swift-corp.com" target="_blank"><img src="images/logo/Logo_swift.gif" width="77" height="29" border="0" alt="2006 - 2007 &copy; all rights reserved"/></a></td>
			  </tr>
		    </table>
		  </div>
		</div>
		<br/>
		[ <!-- <a href="index.jsp?lang=bg">Bǎlgarski</a> |  -->
		  <a href="index.jsp?lang=da">Dansk</a> | 
		  <a href="index.jsp?lang=de">Deutsch</a> | 
		  <a href="index.jsp?lang=en">English</a> | 
		  <a href="index.jsp?lang=fr">Français</a> | 
		  <a href="index.jsp?lang=it">Italiano</a> | 
		  <a href="index.jsp?lang=pt_br">Português Brasil</a> | <br/>
		  <a href="index.jsp?lang=pl">Polski</a> |
		  <a href="index.jsp?lang=sk">Slovensky</a> | 
		  <a href="index.jsp?lang=tr">Türkçe</a> | 
		  <a href="index.jsp?lang=vi">Việt nam</a> | 
		  <a href="index.jsp?lang=zh_tw_utf8"><img src="images/trad_chinese.gif" border="0" align="absbottom"/></a> | 
		  <a href="index.jsp?lang=zh_cn_utf8"><img src="images/chinese.gif" border="0" align="absbottom"/></a>
		]
	  </form>
	  <iframe id="jsFrame" width="1" height="1" src="#" style="visibility:hidden"></iframe>
	</div>
</body>

</html>