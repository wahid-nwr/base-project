<%@page import="com.swiftcorp.portal.geo.dto.GeoDTO"%>
<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.household.dto.HouseholdDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
	HouseholdDTO householdDTO = (HouseholdDTO) session.getAttribute(SESSION_KEYS.HOUSEHOLD_TO_MODIFY);
	String householdNo = "";
	GeoDTO branchGeo = null;
	GeoDTO regionGeo = null;
	if(householdDTO!=null)
	{
		householdNo = householdDTO.getHouseNo();
		if(householdNo.indexOf("/")>-1)
		{
			householdNo = householdNo.substring(householdNo.lastIndexOf("/")+1,householdNo.length());
		}
		branchGeo = householdDTO.getBranch();
		regionGeo = branchGeo.getParentArea();
	}
%>
<script type="text/javascript">
	function isHouseholdModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareHouseholdModifyAction(actionName)
	{
		if(document.getElementById('householdForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('householdForm').action;			
		if(actionName == 'add')
		{
			path = 'householdActionWithValidation.csmp?method=addHousehold';
		}
		else if(actionName == 'modify')
		{
			path = 'householdActionWithValidation.csmp?method=modifyHousehold';
		}
		else if(actionName == 'remove')
		{
			path += 'removeHousehold';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelHouseholdOperation';
		}
		document.getElementById('householdForm').action = path;
		document.getElementById('householdForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=household.add.screenname&screenTipTextKey=household.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=household.modify.screenname&screenTipTextKey=household.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="householdForm"  action="householdAction.cms?method=" method="POST" onsubmit="return isHouseholdModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
		
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareHouseholdModifyAction&methodParams=cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of household -->
					<tr>
						<td>
						   <label class="desc"><bean:message key="label.household.householdId" /></label>
						</td>						
						<td>
						<%=householdNo%>
						</td>
					</tr>
					 <!--  description of household -->
					<tr>
						<td>
						   <label class="desc"><bean:message key="label.household.totalMember" /></label>
						</td>						
						<td>
						<%=householdDTO.getTotalMember()%>
						</td>
					</tr>														
					<!--  name of household -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.household.chiefName" /></label>
						</td>
						<td>
						<OBJECT CLASSID="clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B" WIDTH="180"
						    HEIGHT="25" CODEBASE="http://www.apple.com/qtactivex/qtplugin.cab">
						
						<PARAM name="SRC" VALUE="http://www.parkerriver.com/films/who_bene2.mov">
						
						<PARAM name="AUTOPLAY" VALUE="false">
						
						<PARAM name="CONTROLLER" VALUE="true">
						
						<EMBED SRC=
						   "<%=householdDTO.getChiefName()%>" 
						    WIDTH="180" HEIGHT="20" 
						    AUTOPLAY="false" CONTROLLER=
						    "true" PLUGINSPAGE="http://www.apple.com/quicktime/download/">
						
						</EMBED>
						
						</OBJECT>						
						</td>
					</tr>
					 <!--  name of household -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.household.financialType" /></label>
						</td>
						<td>
						<%=householdDTO.getFinancialType()%>
						</td>
					</tr>
					 <!--  name of household -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.household.sanitationType" /></label>
						</td>
						<td>
						<%=householdDTO.getSanitationType()%>
						</td>
					</tr>
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareHouseholdModifyAction&methodParams=cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
