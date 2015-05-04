<%@page import="com.swiftcorp.portal.risk.service.RiskConstants"%>
<%@page import="com.swiftcorp.portal.common.ApplicationConstants"%>
<%@page import="com.swiftcorp.portal.common.dto.DTOConstants"%>
<%@ include file="/Common/Include/taglib.jsp"%>
<%@ page import="com.swiftcorp.portal.common.web.SESSION_KEYS"%>
<%@ page import="com.swiftcorp.portal.common.GlobalConstants"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.BeneficiaryDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.MotherBeneficiaryDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.PregnancyRecordDTO"%>

<%@ page import="com.swiftcorp.portal.beneficiary.dto.FirstTrimesterRecordDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.SecondTrimesterRecordDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.ThirdTrimesterRecordDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.DeliveryRecordDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.MiscarriageSectionDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.TrimesterHealthInfoDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.ReferralRecordDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.PNCSectionDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.MotherBeneficiaryHistoryDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.child.ChildBeneficiaryDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.VisitDTO"%>
<%@ page import="com.swiftcorp.portal.household.dto.HouseholdMemberDTO"%>
<%@ page import="com.swiftcorp.portal.household.dto.HouseholdDTO"%>
<%@ page import="com.swiftcorp.portal.household.dto.HouseholdMotherVisitDTO"%>
<%@ page import="com.swiftcorp.portal.geo.dto.GeoDTO"%>
<%@ page import="com.swiftcorp.portal.risk.dto.RiskDTO"%>

<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="com.swiftcorp.portal.common.util.CalendarUtils"%>
<%@ page import="com.swiftcorp.portal.common.ViewConstants"%>
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
	function showHide(id)
	{
		var div = document.getElementById(id);
		//alert(div.style.display);
		if(id=='children')
		{
			document.getElementById('pregnancyRecord').style.display = 'none';
			document.getElementById('visitList').style.display = 'none';
			if(div.style.display=='none'){
		  		div.style.display = 'block';		  		  	
		  	}
		  	else
		  	{
		  		div.style.display = 'none';
		  	}
		}
		else if(id=='pregnancyRecord')
		{
			document.getElementById('children').style.display = 'none';
			document.getElementById('visitList').style.display = 'none';
			if(div.style.display=='none'){
		  		div.style.display = 'block';		  		  	
		  	}
		  	else
		  	{
		  		div.style.display = 'none';
		  	}
		}
		else if(id=='visitList'){
			document.getElementById('pregnancyRecord').style.display = 'none';
			document.getElementById('children').style.display = 'none';
			if(div.style.display=='none'){
		  		div.style.display = 'block';		  		  	
		  	}
		  	else
		  	{
		  		div.style.display = 'none';
		  	}
		}
	  	
	}
	
	
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
	
	
</script>
<%
	boolean isReadOnly = false;
	
	MotherBeneficiaryDTO motherBeneficiaryDTO = (MotherBeneficiaryDTO)session.getAttribute(SESSION_KEYS.BENEFICIARY_TO_MODIFY);
	List<ChildBeneficiaryDTO> childBeneficiaryDTOSet = (List<ChildBeneficiaryDTO>)session.getAttribute(SESSION_KEYS.CHILDREN_OF_MOTHER);
	HouseholdDTO householdDTO = (HouseholdDTO)session.getAttribute(SESSION_KEYS.HHDTO_OF_MEMBER);
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
	HouseholdMemberDTO householdMemberDTO = (HouseholdMemberDTO)session.getAttribute(SESSION_KEYS.HHMEMBERDTO_OF_MEMBER);
	Set<HouseholdMotherVisitDTO> householdMotherVisitDTOSet = new HashSet<HouseholdMotherVisitDTO>();	
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
	/*
	HouseholdMotherVisitDTO lastMotherVisitDTO = null;
	if(householdMemberDTO!=null)
	{
		householdMotherVisitDTOSet = householdMemberDTO.getHouseholdMotherVisitSet();
		if(householdMotherVisitDTOSet!=null && householdMotherVisitDTOSet.size()>0)
		{
			System.out.println("householdMotherVisitDTOSet.size:::"+householdMotherVisitDTOSet.size());			
			for(HouseholdMotherVisitDTO householdMotherVisitDTO:householdMotherVisitDTOSet)
			{
				if(lastMotherVisitDTO==null)
				{
					lastMotherVisitDTO = householdMotherVisitDTO;
				}
			}
			//lastMotherVisitDTO = (HouseholdMotherVisitDTO)householdMotherVisitDTOSet.get(1);//householdMotherVisitDTOSet.size()-1
		}
	}
	*/
	MotherBeneficiaryHistoryDTO motherBeneficiaryHistoryDTO = null;
	if(motherBeneficiaryDTO!=null)
	{
		motherBeneficiaryHistoryDTO = motherBeneficiaryDTO.getMotherBeneficiaryHistoryDTO();
	}
	Set<PregnancyRecordDTO> pregnancyRecordSet = null;
	
	PregnancyRecordDTO currentPregnancyRecorDTO = null;
	TrimesterHealthInfoDTO trimesterHealthInfoDTO = null;
	ReferralRecordDTO referralRecordDTO = null;
	SecondTrimesterRecordDTO lastSecondTrimesterRecordDTO = null;
	ThirdTrimesterRecordDTO lastThirdTrimesterRecordDTO = null;
	DeliveryRecordDTO deliveryRecordDTO = null;
	MiscarriageSectionDTO miscarriageSectionDTO = null;
	PNCSectionDTO lastPNCSectionDTO = null;
	Set<FirstTrimesterRecordDTO> firstTrimesterRecordSet = null;
	Set<SecondTrimesterRecordDTO> secondTrimesterRecordSet = null;
	Set<ThirdTrimesterRecordDTO> thirdTrimesterRecordSet = null;
	int i = 0;
	if(motherBeneficiaryDTO!=null)
	{
		currentPregnancyRecorDTO = motherBeneficiaryDTO.getCurrentPregnancyRecord();
		pregnancyRecordSet = motherBeneficiaryDTO.getPregnancyRecordSet();
		System.out.println("pregnancyRecordSet::"+pregnancyRecordSet.size());
		if(currentPregnancyRecorDTO!=null)
		{						
			firstTrimesterRecordSet = (Set<FirstTrimesterRecordDTO>)currentPregnancyRecorDTO.getFirstTrimesterSet ();
			secondTrimesterRecordSet = (Set<SecondTrimesterRecordDTO>)currentPregnancyRecorDTO.getSecondTrimesterSet ();
			thirdTrimesterRecordSet= (Set<ThirdTrimesterRecordDTO>)currentPregnancyRecorDTO.getThirdTrimesterSet ();							
										
			Set<TrimesterHealthInfoDTO> trimesterHealthInfoSet = (Set<TrimesterHealthInfoDTO>)currentPregnancyRecorDTO.getTrimesterHealthInfoSet ();
			Set<ReferralRecordDTO> referralSectionSet= (Set<ReferralRecordDTO>)currentPregnancyRecorDTO.getReferralSectionSet ();
			Set<PNCSectionDTO> pncSectionSet= (Set<PNCSectionDTO>)currentPregnancyRecorDTO.getPncSectionSet ();
			
			miscarriageSectionDTO = (MiscarriageSectionDTO)currentPregnancyRecorDTO.getMiscarriageSectionDTO();
			deliveryRecordDTO = (DeliveryRecordDTO)currentPregnancyRecorDTO.getDeliveryRecordDTO();
			
			for(ThirdTrimesterRecordDTO thirdTrimesterRecordDTO:thirdTrimesterRecordSet)
			{
				if(trimesterHealthInfoDTO==null)
				{
					trimesterHealthInfoDTO = thirdTrimesterRecordDTO.getTrimesterHealthInfoDTO ();
				}
				if(lastThirdTrimesterRecordDTO==null)
				{
					lastThirdTrimesterRecordDTO = thirdTrimesterRecordDTO;
				}
				i++;
			}			
			for(SecondTrimesterRecordDTO secondTrimesterRecordDTO:secondTrimesterRecordSet)
			{
				if(trimesterHealthInfoDTO==null)
				{
					trimesterHealthInfoDTO = secondTrimesterRecordDTO.getTrimesterHealthInfoDTO ();
				}
				if(lastSecondTrimesterRecordDTO==null)
				{
					lastSecondTrimesterRecordDTO = secondTrimesterRecordDTO;
				}
				i++;
			}
			for(FirstTrimesterRecordDTO firstTrimesterRecordDTO:firstTrimesterRecordSet)
			{
				if(trimesterHealthInfoDTO==null)
				{
					trimesterHealthInfoDTO = firstTrimesterRecordDTO.getTrimesterHealthInfoDTO ();
				}
				i++;
			}
			for(ReferralRecordDTO referralRecordCompareDTO:referralSectionSet)
			{
				if(referralRecordDTO==null)
				{
					referralRecordDTO = referralRecordCompareDTO;
				}
			}
			for(PNCSectionDTO pncSectionDTO:pncSectionSet)
			{
				if(lastPNCSectionDTO==null)
				{
					lastPNCSectionDTO = pncSectionDTO;
				}
			}
			
		}
	}
	VisitDTO currentPregVisit = currentPregnancyRecorDTO.getVisitDTO();
	String skId = currentPregVisit.getUserDTO().getUniqueCode();	
	
%>
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=beneficiary.add.screenname&screenTipTextKey=beneficiary.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=beneficiary.modify.screenname&screenTipTextKey=beneficiary.view.mother.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="beneficiaryForm"  action="beneficiaryAction.cms?method=" method="POST" onsubmit="return isBeneficiaryModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<div style="height:50px;width:20%;position: relative;top:0; float: right;border: 1px solid #000000;" >
						<span><label><bean:message key="label.beneficiary.riskLevel" /></label></span><br>
						<span><label><%=riskLevel%></label></span>										
					</div>
			<div id="maternalCommon" style="height:220px;width:75%">
					<div id="picture" style="height:220px;width:20%;float:left">
					<img width="100" border="2" height="120" src="<%=householdMemberDTO.getMemberPicture()%>" alt=""/>
					</div>
					<div id="basicInfo" style="height:220px;width:60%;float:left">
					
						<span class="d"><label><bean:message key="label.beneficiary.skId" /></label></span>
						<span><%=skId%></span><br/>
						<span class="d"><label><bean:message key="label.beneficiary.ssId" /></label></span>
						<span><%=ssId%></span><br/>
						<span class="d"><label><bean:message key="label.beneficiary.householdId" /></label></span>
						<span><%=householdNo%></span><br/>
						<span class="d"><label><bean:message key="label.beneficiary.branchGeo" /></label></span>
						<span><%=branchGeo.getCode()%></span><br/>
						<span class="d"><label><bean:message key="label.beneficiary.regionGeo" /></label></span>
						<span><%=regionGeo.getCode()%></span><br/>
						<span class="d"><label><bean:message key="label.beneficiary.contactNo" /></label></span>
						<span><%=householdMemberDTO!=null?householdMemberDTO.getMobileNo():""%></span><br/>
						<span class="d"><label><bean:message key="label.beneficiary.nationalId" /></label></span>
						<span></span><br/>
						<span class="d"><label><bean:message key="label.beneficiary.motherName" /></label></span>
						<span>
						<OBJECT CLASSID="clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B" WIDTH="180"
						    HEIGHT="25" CODEBASE="http://www.apple.com/qtactivex/qtplugin.cab">
						
						<PARAM name="SRC" VALUE="http://www.parkerriver.com/films/who_bene2.mov">
						
						<PARAM name="AUTOPLAY" VALUE="false">
						
						<PARAM name="CONTROLLER" VALUE="true">
						
						<EMBED SRC=
						   "<%=householdMemberDTO.getMemberName()%>" 
						    WIDTH="180" HEIGHT="20" 
						    AUTOPLAY="false" CONTROLLER=
						    "true" PLUGINSPAGE="http://www.apple.com/quicktime/download/">
						
						</EMBED>
						
						</OBJECT>
						
						</span><br/>
						<span class="d"><label><bean:message key="label.beneficiary.motherBirthDate" /></label></span>
						<span></span><br/>
						<span class="d"><label><bean:message key="label.beneficiary.husbandName" /></label></span>
						<span><%=householdMemberDTO!=null?householdMemberDTO.getHusbandName():""%></span><br/>						
						<span class="d"><label><bean:message key="label.beneficiary.age" /></label></span>
						<span><%=householdMemberDTO!=null?householdMemberDTO.getMemberAge():""%></span><br/>
					</div>
					
				</div>
				<br>
				<br>
				<a href="#" onclick="showHide('visitList');return false;">Visit List</a>&nbsp;&nbsp;
				<a href="#" onclick="showHide('pregnancyRecord');">Previous Pregnancy</a>&nbsp;&nbsp;
				<a href="#" onclick="showHide('children');">Associated Child</a>&nbsp;&nbsp;
				<div id="visitList" style="display:none;border: 1px solid #000000;">				
				<div id="visitdetail">
				<br>
				<br>
					<span class="c">Visit No#</span>
					<span class="c">Visit Date</span>&nbsp;
					
				<%
				
				if(thirdTrimesterRecordSet!=null && thirdTrimesterRecordSet.size()>0)
				{
					%>
					<br><br><span class="c">Third Trimester Visit</span><br>
					<%
				}
				for(ThirdTrimesterRecordDTO thirdTrimesterRecordDTO:thirdTrimesterRecordSet)
				{
					VisitDTO visitDTO = thirdTrimesterRecordDTO.getVisitDTO();
					%>
					
					
					
					<span class="c">visit no # <%=i%></span>
					<span class="c"><a href="beneficiaryAction.csmp?method=promptShowMotherDetail&detailType=<%=ViewConstants.THIRD_TRIMESTER%>&componentId=<%=motherBeneficiaryDTO.getComponentId()%>&visitId=<%=thirdTrimesterRecordDTO.getComponentId()%>"><%=CalendarUtils.calendarToString(visitDTO.getVisitDate())%></a></span>
					
					<%
					i--;
				}	
				if(secondTrimesterRecordSet!=null && secondTrimesterRecordSet.size()>0)
				{
					%>
					<br><br><span class="c">Second Trimester Visit</span><br>
					<%
				}		
				for(SecondTrimesterRecordDTO secondTrimesterRecordDTO:secondTrimesterRecordSet)
				{					
					trimesterHealthInfoDTO = secondTrimesterRecordDTO.getTrimesterHealthInfoDTO ();
					VisitDTO visitDTO = secondTrimesterRecordDTO.getVisitDTO();					
					%>
					
					<span class="c">visit no # <%=i%></span>
					<span class="c"><a href="beneficiaryAction.csmp?method=promptShowMotherDetail&detailType=<%=ViewConstants.SECOND_TRIMESTER%>&componentId=<%=motherBeneficiaryDTO.getComponentId()%>&visitId=<%=secondTrimesterRecordDTO.getComponentId()%>"><%=CalendarUtils.calendarToString(visitDTO.getVisitDate())%></a></span>
					<%
					i--;
				}
				if(firstTrimesterRecordSet!=null && firstTrimesterRecordSet.size()>0)
				{
					%>
					<br><br><span class="c">First Trimester Visit</span><br>
					<%
				}
				for(FirstTrimesterRecordDTO firstTrimesterRecordDTO:firstTrimesterRecordSet)
				{
					trimesterHealthInfoDTO = firstTrimesterRecordDTO.getTrimesterHealthInfoDTO ();
					VisitDTO visitDTO = firstTrimesterRecordDTO.getVisitDTO();	
					%>					
					
					<span class="c">visit no # <%=i%></span>
					<span class="c"><a href="beneficiaryAction.csmp?method=promptShowMotherDetail&detailType=<%=ViewConstants.FIRST_TRIMESTER%>&componentId=<%=motherBeneficiaryDTO.getComponentId()%>&visitId=<%=firstTrimesterRecordDTO.getComponentId()%>"><%=CalendarUtils.calendarToString(visitDTO.getVisitDate())%></a></span>
					<br>
					<%
					i--;
				}
				%>
				</table>
				</div>
				<div id="pregnancyRecord" style="display:none;border: 1px solid #000000;">
				<br>
				<br>
				<span class="c">Pregnancy No #</span>
				<span class="c">Last Stage</span>&nbsp;
				<%
				i = pregnancyRecordSet.size();
				for(PregnancyRecordDTO pregnancyRecordDTO:pregnancyRecordSet)
				{
					if(pregnancyRecordDTO.getComponentId()!= currentPregnancyRecorDTO.getComponentId())
					{
					System.out.println(pregnancyRecordDTO.getComponentId()+" "+currentPregnancyRecorDTO.getComponentId());
					%>
					<br>
					<span class="c">Pregnancy no # <%=i%></span>
					<span class="c"><a href="#"><%=pregnancyRecordDTO.getCurrentStage()%></a></span>&nbsp;
					<%
					i--;
					}
				}
				%>
				</div>
				<div id="children" style="display:none;border: 1px solid #000000;">
				<br>
				<br>
				<%
				for(ChildBeneficiaryDTO childBeneficiaryDTO:childBeneficiaryDTOSet)
				{
					%>
					<a href="beneficiaryAction.csmp?method=promptModifyChildBeneficiary&componentId=<%=childBeneficiaryDTO.getComponentId()%>">Child borned on <%=CalendarUtils.calendarToString(childBeneficiaryDTO.getDateOfBirth())%></a><br/>					
					<%
				}
				%>
				</div>
				
				</div>	
			
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareBeneficiaryModifyAction&methodParams=cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>