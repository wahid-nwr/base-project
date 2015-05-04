<%@ include file="/Common/Include/taglib.jsp"%>

<%@ page import="com.swiftcorp.portal.beneficiary.dto.BeneficiaryDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.child.ChildBeneficiaryDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.child.ChildFirstVisitRecord"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.child.NeoNatalVisitRecord"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.child.NeoNatalComplicacyRecord"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.child.NeoNatalVisitInfo"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.child.InfantVisitRecord"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.child.ChildVisitRecord"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.child.ChildComplicacyRecord"%>
<%@ page import="com.swiftcorp.portal.household.dto.HouseholdMemberDTO"%>
<%@ page import="com.swiftcorp.portal.household.dto.HouseholdDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.VisitDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.ReferralRecordDTO"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="com.swiftcorp.portal.common.util.CalendarUtils"%>
<%@ page import="com.swiftcorp.portal.common.ViewConstants"%>
<%@ page import="com.swiftcorp.portal.geo.dto.GeoDTO"%>
<%@ page import="com.swiftcorp.portal.risk.dto.RiskDTO"%>
<%@page import="com.swiftcorp.portal.risk.service.RiskConstants"%>

<%@ page import="org.apache.commons.logging.Log"%>
<%@ page import="org.apache.commons.logging.LogFactory"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.util.Calendar"%>

<style type="text/css" rel="stylesheet">
			.large {
			  	height:320;
				//width:800;
				//position: absolute;
				top:0%;
				left: 0%;
				//margin-top: -100px;
				//margin-left: -50px;
				overflow:scroll;
				border:1px solid gray;
				margin-bottom: 1em;
				padding: 10px;
			} 
			.b {			  	
			  	float: left;
       			width: 380px;
			} 
			.c {
			  	float: left;
       			width: 150px;
			} 
			.d {
			  color: #0066FF;
			  float: left;
       			width: 180px;
			}
			
		</style>
<script type="text/javascript">
function isBeneficiaryModifyValidSubmit()
	{
		return true;
	}
	
	
	function prepareBeneficiaryModifyAction(actionName)
	{
		if(document.getElementById('beneficiaryForm') == null)
		{
			return;
		}
					
		var path = document.getElementById('beneficiaryForm').action;			
		if(actionName == 'add')
		{
			path = 'beneficiaryActionWithValidation.csmp?method=addBeneficiary';
		}
		else if(actionName == 'modify')
		{
			path = 'beneficiaryActionWithValidation.csmp?method=modifyBeneficiary';
		}
		else if(actionName == 'remove')
		{
			path += 'removeBeneficiary';
		}
		else if(actionName == 'cancel')
		{
			path += 'cancelBeneficiaryOperation';
		}
		document.getElementById('beneficiaryForm').action = path;
		document.getElementById('beneficiaryForm').submit();
	}
	
		
	function isValidSubmit()
	{
		return true;
	}
	function showHide(id)
	{
		var div = document.getElementById(id);	
			
		if(div.style.display=='none'){
	  		div.style.display = 'block';		  		  	
	  	}
	  	else
	  	{
	  		div.style.display = 'none';
	  	}
	  	
	}
	
</script>
<%
	boolean isReadOnly = false;
	//private static final Log logger = LogFactory.getLog ( ChildBeneficiaryDTO.class );
	//logger.info("from child beneficiary");
	ChildBeneficiaryDTO childBeneficiaryDTO = (ChildBeneficiaryDTO)session.getAttribute(SESSION_KEYS.BENEFICIARY_TO_MODIFY);
	HouseholdDTO householdDTO = (HouseholdDTO)session.getAttribute(SESSION_KEYS.HHDTO_OF_MEMBER);
	HouseholdMemberDTO householdMemberDTO = (HouseholdMemberDTO)session.getAttribute(SESSION_KEYS.HHMEMBERDTO_OF_MEMBER);
	RiskDTO riskDTO = (RiskDTO)session.getAttribute(SESSION_KEYS.RISK_OF_BENEFICIARY);
	String riskLevel = "";
	int levelOfRisk = 0;
	if(riskDTO!=null)
	{
		levelOfRisk = riskDTO.getRiskLevel();
		if(levelOfRisk == RiskConstants.MILD_RISK_LEVEL)
		{
			riskLevel = "<font color=\"yellow\">Mild Risk</font>";
		}
		else if(levelOfRisk == RiskConstants.MODERATE_RISK_LEVEL)
		{
			riskLevel = "<font color=\"orange\">Moderate Risk</font>";
		}
		else if(levelOfRisk == RiskConstants.SEVERE_RISK_LEVEL)
		{
			riskLevel = "<font color=\"red\">Severe Risk</font>";
		}
	}
	String skId = (String)session.getAttribute(SESSION_KEYS.SK_ID);
	String ssId = "";
	String householdNo = "";
	GeoDTO branchGeo = null;
	GeoDTO regionGeo = null;
	if(householdDTO!=null)
	{
		ssId = householdDTO.getSsId();
		branchGeo = householdDTO.getBranch();
		regionGeo = branchGeo.getParentArea();
		householdNo = householdDTO.getHouseNo();
		if(householdNo.indexOf("/")>-1)
		{
			householdNo = householdNo.substring(householdNo.lastIndexOf("/")+1,householdNo.length());
		}
	}
	String bgcolor="#eeeeee";
	double childWieght = 0.0;
	ChildFirstVisitRecord childFirstVisitRecord = null;
	InfantVisitRecord infantVisitRecord =null;	
	Set<ReferralRecordDTO> referralSectionSet = new HashSet<ReferralRecordDTO>();
	ReferralRecordDTO referralRecordDTO =null;
	Set<ChildVisitRecord> childVisitRecordSet = null;
	Set<InfantVisitRecord> infantVisitRecordSet = null;
	Set<NeoNatalVisitRecord> neoNatalVisitRecordSet = null;
	NeoNatalVisitInfo neoNatalVisitInfo = null;
	int i=0;
	if(childBeneficiaryDTO!=null)
	{
		childBeneficiaryDTO.getBirthWieght ( );
		childFirstVisitRecord = childBeneficiaryDTO.getChildFirstVisitRecord ( );
		childVisitRecordSet = childBeneficiaryDTO. getChildVisitRecordSet ( );
		infantVisitRecordSet = childBeneficiaryDTO.getInfantVisitRecordSet ( );
		neoNatalVisitRecordSet = childBeneficiaryDTO.getNeoNatalVisitRecordSet ( );
		
		for(InfantVisitRecord infantVisitRecordCh:infantVisitRecordSet)
		{
			if(infantVisitRecord==null)
			{
				infantVisitRecord = infantVisitRecordCh;
			}
			referralSectionSet.add(infantVisitRecordCh.getReferralRecordDTO ( ));
			i++;
		}
		for(ChildVisitRecord childVisitRecordCh:childVisitRecordSet)
		{
			referralSectionSet.add(childVisitRecordCh.getReferralRecordDTO ( ));
			i++;
		}
		for(NeoNatalVisitRecord neoNatalVisitRecordCh:neoNatalVisitRecordSet)
		{
			referralSectionSet.add(neoNatalVisitRecordCh.getReferralRecordDTO ( ));
			i++;
		}
		if(childFirstVisitRecord!=null)
		{
			i++;
		}
	}
	
%>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=beneficiary.add.screenname&screenTipTextKey=beneficiary.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=beneficiary.modify.screenname&screenTipTextKey=beneficiary.view.child.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="beneficiaryForm"  action="beneficiaryAction.cms?method=" method="POST" onsubmit="return isBeneficiaryModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>			
			
			<!-- Child detail start -->
			<div style="height:50px;width:20%;position: relative;top:0; float: right;border: 1px solid #000000;" >
						<span><label><bean:message key="label.beneficiary.riskLevel" /></label></span><br>
						<span><label><%=riskLevel%></label></span>										
					</div>
			<div id="childCommon" style="height:200px;width:75%">
				<div id="picture" style="height:200px;width:20%;float:left">
					<img width="100" border="2" height="120" src="<%=householdMemberDTO.getMemberPicture()%>" alt=""/>
				</div>
				<div id="basicInfo" style="height:200px;width:60%;float:left">
					<span class="d"><label><bean:message key="label.beneficiary.skId" /></label></span>
					<span><%=skId%></span><br/>
					<span class="d"><label><bean:message key="label.beneficiary.householdId" /></label></span>
					<span><%=householdNo%></span><br/>
					<span class="d"><label><bean:message key="label.beneficiary.branchGeo" /></label></span>
					<span><%=branchGeo.getCode()%></span><br/>
					<span class="d"><label><bean:message key="label.beneficiary.regionGeo" /></label></span>
					<span><%=regionGeo.getCode()%></span><br/>
					<span class="d"><label><bean:message key="label.beneficiary.ssId" /></label></span>
					<span><%=ssId%></span><br/>
					<span class="d"><label><bean:message key="label.beneficiary.motherId" /></label></span>
					<span><%=childBeneficiaryDTO.getMotherId()%></span><br/>
					<span class="d"><label><bean:message key="label.beneficiary.childName" /></label></span>
					<span>
						<OBJECT CLASSID="clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B" WIDTH="180"
						    HEIGHT="25" CODEBASE="http://www.apple.com/qtactivex/qtplugin.cab">
						
						<PARAM name="SRC" VALUE="http://www.parkerriver.com/films/who_bene2.mov">
						
						<PARAM name="AUTOPLAY" VALUE="false">
						
						<PARAM name="CONTROLLER" VALUE="true">
						
						<EMBED SRC=
						   "<%=householdMemberDTO.getMemberName()%>" 
						    WIDTH="180" HEIGHT="25" 
						    AUTOPLAY="false" CONTROLLER=
						    "true" PLUGINSPAGE="http://www.apple.com/quicktime/download/">
						
						</EMBED>
						
						</OBJECT>
						
						</span><br/>					
					<span class="d"><label><bean:message key="label.beneficiary.childGender" /></label></span>
					<span><%=childBeneficiaryDTO.getSex()%></span><br/>
				</div>
			</div>
			<br>
			
			<a href="#" onclick="showHide('visitList');return false;">Visit List</a>&nbsp;&nbsp;
			
			<div id="visitList" style="display:none;text-align: center;border: 1px solid #000000;">
			<span class="c">Visit No#</span>
			<span class="c">Visit Date</span>&nbsp;
			<br>
			<%
				for(ChildVisitRecord childVisitRecordCh:childVisitRecordSet)
				{
					VisitDTO visitDTO = childVisitRecordCh.getVisitDTO();
					%>					
					<span class="c"><%=i%></span>
					<span class="c"><a href="beneficiaryAction.csmp?method=promptShowChildBeneficiary&visitId=<%=childVisitRecordCh.getComponentId()%>&visitType=child&componentId=<%=childBeneficiaryDTO.getComponentId()%>"><%=CalendarUtils.calendarToString(visitDTO.getVisitDate())%></a></span><br>
					<%
					i--;
				}
				for(InfantVisitRecord infantVisitRecordCh:infantVisitRecordSet)
				{
					VisitDTO visitDTO = infantVisitRecordCh.getVisitDTO();
					%>					
					<span class="c"><%=i%></span>
					<span class="c"><a href="beneficiaryAction.csmp?method=promptShowChildBeneficiary&visitId=<%=infantVisitRecordCh.getComponentId()%>&visitType=infant&componentId=<%=childBeneficiaryDTO.getComponentId()%>"><%=CalendarUtils.calendarToString(visitDTO.getVisitDate())%></a></span><br>
					<%
					i--;
				}				
				for(NeoNatalVisitRecord neoNatalVisitRecordCh:neoNatalVisitRecordSet)
				{
					VisitDTO visitDTO = neoNatalVisitRecordCh.getVisitDTO();
					%>					
					<span class="c"><%=i%></span>
					<span class="c"><a href="beneficiaryAction.csmp?method=promptShowChildBeneficiary&visitId=<%=neoNatalVisitRecordCh.getComponentId()%>&visitType=neoNatal&componentId=<%=childBeneficiaryDTO.getComponentId()%>"><%=CalendarUtils.calendarToString(visitDTO.getVisitDate())%></a></span><br>
					<%
					i--;
				}
				if(childFirstVisitRecord!=null)
				{
					VisitDTO visitDTO = childFirstVisitRecord.getVisitDTO();
					%>					
					<span class="c"><%=i%></span>
					<span class="c"><a href="beneficiaryAction.csmp?method=promptShowChildBeneficiary&visitId=<%=childFirstVisitRecord.getComponentId()%>&visitType=first&componentId=<%=childBeneficiaryDTO.getComponentId()%>"><%=CalendarUtils.calendarToString(visitDTO.getVisitDate())%></a></span><br>
					<%
					i--;
				}
			%>
			</div>
			<br>
			<!-- Child detail end -->
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%=SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareBeneficiaryModifyAction&methodParams=cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>