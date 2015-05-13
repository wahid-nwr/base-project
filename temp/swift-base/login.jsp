
<%@page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page contentType="text/html" import="java.util.*"%>
<html>
  <head>
  <title>Demo Platform</title>
  <link rel="stylesheet" type="text/css" href="./Common/JavaScript/ext-2.0/resources/css/ext-all.css"/>
	
	<script type="text/javascript" src="./Common/JavaScript/ext-2.0/adapter/ext/ext-base.js"></script>
  <script type="text/javascript" src="./Common/JavaScript/ext-2.0/ext-all.js"></script>
  <link rel="shortcut icon" href="../images/hsbc-icon.jpg" type="image/jpg" />
  
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  
  <style type="text/css">
    div#main 
    {
        width: 97% !important;
		text-align: center;
        margin: 0;
    }
  </style>
  <script type="text/javascript">
  function convertForm(formId) {
	  alert(formId)
	    var frm = new Ext.form.BasicForm(formId);
	    //frm.render();

	    var fields = frm.getValues()

	    for (key in fields) {
			alert(key);
	        var elem = Ext.get(key);
	        if (elem && elem.hasClass('combo-box')) {
	            var cb = new Ext.form.ComboBox({
	                transform: elem.dom.name,
	                typeAhead: true,
	                triggerAction: 'all',
	                width: elem.getWidth(),
	                forceSelection: true
	            });
	        }
	        else 
	            if (elem && elem.hasClass('date-picker')) {
	                var df = new Ext.form.DateField({
	                    format: 'm/d/Y'
	                });
	                df.applyTo(elem.dom.name);
	            }

	        if (elem && elem.hasClass('resizeable')) {
	            var dwrapped = new Ext.Resizable(elem, {
	                wrap: true,
	                pinned: true,
	                width: 400,
	                height: 150,
	                minWidth: 200,
	                minHeight: 50,
	                dynamic: true
	            });
	        }
	    }
	}

  </script>
  
</head>



<body bgcolor="FFFFFF" text="#000000">

<%boolean errorPresent = false;%>
<html:form styleId="loginForm" action="loginAction.csmp?method=login" method="post">

	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="350">
	  <tr><td valign="middle" align="center">	  	

    
     &nbsp;<p>&nbsp;</p>
		<p>&nbsp;</p>

    
     <table>
     		<tr>
     		<td align="right">
                   <html:img src="../images/logo/brac_logo_big.jpg" width="100" height="50" alt="Your LOGO" />
                  </td>
              <td height="2">
                <table width="250" border="0" bgcolor="#0062A3" height="8">
                  <tr>
                  
                    <td height="2">
                      <div align="center">
                        <font color="#FFFFFF" face="Verdana">
                            Demo Platform
                        </font>
                      </div>
                    </td>
                   
                  </tr>
                </table>
              </td>
               <td align="left">
              <html:img src="../images/logo/click_logo_big.jpg" width="100" height="50" alt="Your LOGO"/>
                  </td>
            </tr>
			<tr> 
				<td colspan="3" width="300" align="center">
					&nbsp;
					<logic:messagesPresent>		
						<font size="3" color="red">
							<html:errors bundle="Errors"/>
							<%
							errorPresent=true;
							%>
						</font>
					</logic:messagesPresent>
				</td>
			</tr>	
						
		</table>
		
    
    
    <table width="30%" border="0" height="146"  bordercolor="#FFFFFF">
      <tr>
        <td height="189" >
          <table width="40%" border="0" align="center">
            
            <tr>
              <td>
                <table width="100%" border="0" bgcolor="#0062A3" height="80">
                  <tr>
                    <td height="119">
                      <table bgcolor="snow" border=0 cellpadding=2 cellspacing=1 width=400 height="117">
                        <tbody>
                        <tr>
                          <td colspan=3 bgcolor="#0062A3" align="center">&nbsp;<font color="white">Welcome</font> </td>
                        </tr>
                        <tr>
                          
                          <td align=right width="50%">
							<font face="Verdana" size="2">
							<b><font color="#0062A3">
							User ID</font>:</b></font></td>
                          <td width="50%">
								<html:text styleClass="text medium" property="authDTO.userId" styleId="authDTO.userId" tabindex="1" size="22"/>
                          </td>
                        </tr>
                        <tr>
                        	
                          <td align=right width="50%">
							<font face="Verdana" size="2"><b><font color=#0062A3>Password
                            </font>:</b></font></td>
                          <td width="50%">
	                           <html:password styleClass="text medium" property="authDTO.password" styleId="authDTO.password" tabindex="2" size="22"/>
                          </td>
                        </tr>
                        <tr>
                          <td colspan=3 height="2"><font color=#000000></font></td>
                        </tr>
                        <tr>                          
                          <td height="13" width="50%">
                            <div align="right">
							   <input type="submit" class="button" name="login" value="Sign In" />                            
			                </div>
                          </td>
                          <td height="13" width="50%">
                            <div align="left">
                              <input type="reset" class="button" value="Cancel" />
                            </div>
                          </td>
                        </tr>
                        <tr>
                          <td align=right colspan=3 bgcolor="#0062A3">&nbsp;
                          </td>
                        </tr>
                        </tbody>
                      </table>
                    </td>
                  </tr>
        </table>
</center>
	

</html:form>

</body>
<script type="text/javascript">
//convertForm('loginForm');
</script>
</html>