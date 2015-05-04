<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.geo.dto.GeoDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%
	boolean isReadOnly = false;
List<ArrayList<String>> rowDataList = (List<ArrayList<String>>)request.getSession().getAttribute("thefile");
request.getSession().removeAttribute("thefile");
%>
<script type="text/javascript">
	function isGeoModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareGeoModifyAction(actionName)
	{
		if(document.getElementById('importForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('importForm').action;			
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
			var thefile = getElementsByName_iefix('input','fileObject.theFile')[0].value;
			thefile = thefile.substr(thefile.lastIndexOf('.')+1,thefile.length);
			if(thefile=='xls' || thefile=='xlsx' || thefile=='csv')
			{
				
			}
			else
			{
				alert("Please select a valid Excell or CSV file not a ."+thefile +" file.");
				return false;
			}
			//if()
			path += 'importCSVOperation';
		}
		document.getElementById('importForm').action = path;
		document.getElementById('importForm').submit();
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
<html:form styleId="importForm"  action="importAction.cms?method=" method="POST" onsubmit="return isGeoModifyValidSubmit()" target="_self" enctype="multipart/form-data">
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
						  <input type="submit" name="submit" value="Import" onclick="return prepareGeoModifyAction('import')"/>
						</td>
					</tr>
					</tbody>
				</table>
			</fieldset>
			<%
			if(rowDataList!=null && rowDataList.size()>0)
			{
				ArrayList<String> headerRow = rowDataList.get( 0 );
				ArrayList<String> dataRow = null;
			%>
			<fieldset>
				<legend>
					File Info
				</legend>
				<table>
					<tbody>
			
					 <!--  uniqueCode of geo -->
					

					 <!--  name of geo -->
					<tr>
						<%
						for(int i=0;headerRow!=null && i<headerRow.size(); i++)
						{
						%>
						<td width="<%=(100/headerRow.size()) %>%">
						  <b><label class="desc"><%=headerRow.get(i) %></label></b>
						</td>
						<%
						}
						%>
					</tr>
					
						<%
						for(int i=1;rowDataList!=null && rowDataList.size()>1 && i<rowDataList.size(); i++)
						{
							dataRow = rowDataList.get(i);
							%>
							<tr>
							<%
							for(int j=0;dataRow!=null && j<dataRow.size(); j++)
							{
							%>
							<td width="<%=(100/dataRow.size()) %>%">
							  <label class="desc"><%=dataRow.get(j) %></label>
							</td>
							<%
							}	
							%>
							</tr>
							<%
						}
						%>
					
					</tbody>
				</table>
			</fieldset>
			<%
			}
			%>
		</td></tr>
		
			
			
		</tbody> 
	</table>	
</html:form>
