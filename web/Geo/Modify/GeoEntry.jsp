<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.dto.DTOConstants"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.geo.dto.GeoDTO"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="java.util.List"%>
<%
	boolean isReadOnly = false;
	GeoDTO geoDTO = (GeoDTO) session.getAttribute(SESSION_KEYS.GEO);
%>
<script type="text/javascript">
	var parentAreaId = '';
	<%
	if(geoDTO!=null && geoDTO.getParentArea()!=null)
	{
		%>
		parentAreaId = '<%=geoDTO.getParentArea().getComponentId()%>';
		<%
	}
	%>
	function loadModify()
	{
		var parentAreaList = document.getElementById('geo.parentAreaId');
		if(parentAreaId != '')
		{
			
			for(var i=0;i<parentAreaList.length;i++)
			{
				if(parentAreaList[i].value==parentAreaId)
				{
					parentAreaList[i].selected='yes';
				}
			}
		}
	}
	function isGeoModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareGeoModifyAction(actionName)
	{
		if(document.getElementById('geoForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('geoForm').action;			
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
		document.getElementById('geoForm').action = path;
		document.getElementById('geoForm').submit();
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
<html:form styleId="geoForm"  action="geoAction.cms?method=" method="POST" onsubmit="return isGeoModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareGeoModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareGeoModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
			<tr><td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table><tbody>
					 <!--  uniqueCode of geo -->
					 <tr>
						<td>
						   <label class="desc"><bean:message key="label.geo.geoId" /></label>
						</td>						
						<td>
							<html:text property="geo.code" styleId="geo.code" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr> 		
					<!--  description of geo -->
					 <tr>
						<td>
						   <label class="desc"><bean:message key="label.geo.name" /></label>
						</td>						
						<td>
							<html:text property="geo.name" styleId="geo.name" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					<%
					int geoType = 0;
					if(geoDTO!=null)
					{
						geoType = geoDTO.getGeoType();
						System.out.println("geoType::"+geoType);
					}
					%>
					<!--  type of geo -->
					 <tr>
						<td>
						   <label class="desc"><bean:message key="label.geo.type" /></label>
						</td>	
						<td>
							<select name="geo.geoType" id="geo.geoType"  tabindex="8" >
							<option value="-1">---Select---</option>
								<option <%if(geoType == DTOConstants.GEO_TYPE_CITY_CORPORATION){out.println("selected");}%> value= "5" >City Corporation</option>								
								<option <%if(geoType == DTOConstants.GEO_TYPE_REGION){out.println("selected");}%> value= "10" >Region</option>	
								<option <%if(geoType == DTOConstants.GEO_TYPE_BRANCH){out.println("selected");}%> value= "15" >Branch</option>										
							</select>
						</td>
					</tr>
					
					<!-- geo area no -->
					<tr>
						<td>
							<label class="desc"><bean:message key="label.geo.geoNumber"/></label>
						</td>
						<td>
							<html:text property="geo.geoNumber" styleId="geo.geoNumber" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					<!-- geo address -->
					<tr>
						<td>
							<label class="desc"><bean:message key="label.geo.address"/></label>
						</td>
						<td>
							<html:text property="geo.address" styleId="geo.address" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					<!-- geo location -->
					<tr>
						<td>
							<label class="desc"><bean:message key="label.geo.location"/></label>
						</td>
						<td>
							<html:text property="geo.location" styleId="geo.location" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					<!-- geo office phone number -->
					<tr>
						<td>
							<label class="desc"><bean:message key="label.geo.officePhoneNumber"/></label>
						</td>
						<td>
							<html:text property="geo.officePhoneNumber" styleId="geo.officePhoneNumber" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
				
					<!-- geo contact person -->
					<tr>
						<td>
							<label class="desc"><bean:message key="label.geo.contactPerson"/></label>
						</td>
						<td>
							<html:text property="geo.contactPerson" styleId="geo.contactPerson" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
						</td>
					</tr>
					
					<!-- geo parent area -->
						<tr>
							<td>
								<label class="desc"><bean:message key="label.geo.parent"/></label>
							</td>
							<td>
									<html:select property="geo.parentAreaId" styleId="geo.parentAreaId"  tabindex="7"  >
									<html:option value="-1">---Select---</html:option>
									<html:options collection="<%=SESSION_KEYS.GEO_PARENT_LIST%>" property="componentId" labelProperty="name"/>
								</html:select>
							</td>
						</tr>
					
					</tbody>
				</table>
			</fieldset>
		</td></tr>
		<script type="text/javascript">loadModify();</script>
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareGeoModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareGeoModifyAction&methodParams=modify,cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>
