<%@ page pageEncoding="UTF-8" %>     
<%@ page language="java" contentType="text/html;charset=UTF-8" %>  

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%
   /*
   String strRecordId = request.getParameter("recordId");
   int recordId = Integer.parseInt(strRecordId);
   String currentDate = utility.getCurrentDate("yyyy-MM-dd HH:mm:ss");
   
   String patientType = request.getParameter("patientType");
   String titleDetail="Patient Details";
   if(patientType.equalsIgnoreCase("m")){
    titleDetail += "-Mother";
   }else if(patientType.equalsIgnoreCase("c")){
    titleDetail += "-Child";
   }
   */
   
   String advice = (String) session.getAttribute("advice");
   %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <link rel="stylesheet" type="text/css" href="./googleApiTest/css/default-clickdig.css">
        <script language="javascript" src="./googleApiTest/script/jquery-1.2.6.js"></script>
        <script language="javascript" src="./googleApiTest/script/admin_patient.js"></script>        
        <link rel="stylesheet" href="./googleApiTest/modalbox/mbox.css" type="text/css">
        <script type="text/javascript" src="./googleApiTest/modalbox/mbox.js"></script>
        <script type="text/javascript" src="./googleApiTest/modalbox/ajax-dynamic-content.js"></script>
        <script type="text/javascript" src="./googleApiTest/modalbox/ajax.js"></script> 
        <script type="text/javascript" src="http://www.google.com/jsapi">
    </script>
    <script type="text/javascript">

        google.load("elements", "1", {packages: "transliteration"});
        function OnLoad() {
          var content = document.getElementById('content').innerHTML;

          var options = {
                      sourceLanguage:
                              google.elements.transliteration.LanguageCode.ENGLISH,
                      destinationLanguage:
                              [google.elements.transliteration.LanguageCode.BENGALI],
                      shortcutKey: 'ctrl+g',
                      transliterationEnabled: true
                    };
             var control =new google.elements.transliteration.TransliterationControl(options);
             control.makeTransliteratable(['phAdvice']);
             control.makeTransliteratable(['motherNameAudio']);
             control.makeTransliteratable(['husbandNameAudio']);
             control.makeTransliteratable(['occupationAudio']);
             control.makeTransliteratable(['houseOwnerNameAudio']);
             control.makeTransliteratable(['houseHoldNameAudio']);

        }
        google.setOnLoadCallback(OnLoad);

    </script>
        <title>Patient Details</title>
    </head>
    <body>
   <% out.print("Advice is :"+advice+"<br>");%>
        <table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
            <colgroup>
                <col width="63%">
                <col width="2%">
                <col width="35%">
            </colgroup>
            <tr>
                <td colspan="3">
                    
                </td>
            </tr>
           
             <tr height="20"><td colspan="3"></td></tr>
             
 <!-- doctor -->   
  
    <tr>
        <td colspan="3"><font size="2" color="blue">[Ctrl + g] for switching between Bangla and English</font></td>
    </tr>
                            
            <tr bgcolor="#DDD">
                 <td colspan="3">
                 <fieldset><legend><label class="titleText">Physician's Advice </label>
                     <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        
                         <tr height="10"><td colspan="3"></td></tr>
                         <tr><td><div id="content">
                         
                         <form name="medicalAdviceForm" action="medicalAdviceAction.csmp?method=" method="POST" >
                         <textarea rows="7" cols="110" id="phAdvice" name ="phAdvice" align="left"><%=advice%></textarea>
                         </form></div></td></tr>
                         <tr height="10"><td colspan="3"></td></tr>
                         <tr align="right">                           
                             <td><a href="javascript:void(0);" onclick="saveDoctorsComment();"><input type="button" value="Submit" class="button"></a></td>
                         </tr>
                     </table>
                   </legend></fieldset>
                </td>
            </tr>
  <!-- doctor -->   

            <tr>
                <td colspan="3">
                   
                </td>
            </tr>
        </table> 

<script type="text/javascript">
function saveDoctorsComment()
{
	alert('clicked');
	//setUni2JavaValue(document.getElementById('phAdvice'), document.getElementById('phAdvice')); 
	
	path='medicalAdviceAction.csmp?method=addMedicalAdvice';
	document.medicalAdviceForm.action = path;
	document.medicalAdviceForm.submit();
		
	//document.medicalAdviceForm.submit();
	
}

function hexdigit(v) {
	   symbs = "0123456789ABCDEF";
	   return symbs.charAt(v & 0x0f);
	}

	function hexval(v) {
	   return hexdigit(v >>> 12) + hexdigit(v >>> 8) + hexdigit(v >>> 4) + hexdigit(v);
	}

	function uni2j(val) {
	   if (val == 10) return "\\n"
	   else if (val == 13) return "\\r"
	   else if (val == 92) return "\\\\"
	   else if (val == 34) return "\\\""
	   else if (val < 32 || val > 126) return "\\u" + hexval(val)
	   else return String.fromCharCode(val);
	}

	function uni2java(uni, fld_java) {
	   lit = '';
	   for (i = 0; i < uni.length; i++) {
	      v = uni.charCodeAt(i);
	      lit = lit + uni2j(v);
	   }
	
	   fld_java.value = lit + '';
	}
	
	function setUni2JavaValue(fromElement, toElement) 
	{
	   if(fromElement != null 
	   		&& fromElement != undefined
	   		&& toElement != null 
	   		&& toElement != undefined
	   		&& fromElement.value != '')
		{				   
	   		uni2java(fromElement.value, toElement);
	   	}	
	}
	
messageObj = new DHTML_modalMessage();	// We only create one object of this class
messageObj.setShadowOffset(0);	// Large shadow
</script>
    </body>
</html>
