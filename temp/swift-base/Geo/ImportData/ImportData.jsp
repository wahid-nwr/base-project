<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.geo.dto.GeoDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
%>
<script type="text/javascript">
	function isGeoModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareGeoModifyAction(actionName)
	{
		if(document.getElementById('geoImportForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('geoImportForm').action;			
		if(actionName == 'add')
		{
			path = 'geoActionWithValidation.csmp?method=addGeo';
		}
		else if(actionName == 'modify')
		{
			path = 'geoActionWithValidation.csmp?method=modifyGeo';
		}
		else if(actionName == 'remove')
		{
			path += 'removeGeo';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelGeoOperation';
		}
		else if(actionName == 'import')
		{
			path += 'importGeoOperation';
		}
		document.getElementById('geoImportForm').action = path;
		document.getElementById('geoImportForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	
	
</script>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=geo.add.screenname&screenTipTextKey=geo.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=geo.modify.screenname&screenTipTextKey=geo.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="geoImportForm"  action="geoImportAction.cms?method=" method="POST" onsubmit="return isGeoModifyValidSubmit()" target="_self" enctype="multipart/form-data">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
			
					 <!--  uniqueCode of geo -->
					

					 <!--  name of geo -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.geo.name" /></label>
						</td>
						<td>
							<html:file property="fileObject.theFile"/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						  <input type="submit" name="submit" value="Import" onclick="prepareGeoModifyAction('import')"/>
						</td>
					</tr>
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		
			
			
		</tbody> 
	</table>	
</html:form>
