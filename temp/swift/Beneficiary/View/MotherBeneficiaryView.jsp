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
<%@ page import="com.swiftcorp.portal.household.dto.HouseholdMemberVisitDTO"%>
<%@ page import="com.swiftcorp.portal.household.dto.HouseholdMotherVisitDTO"%>

<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="com.swiftcorp.portal.common.util.CalendarUtils"%>
<%@ page import="com.swiftcorp.portal.common.ViewConstants"%>

<%@ page import="com.swiftcorp.portal.common.ApplicationConstants"%>
<%@ page import="com.swiftcorp.portal.common.dto.FunctionDTO"%>
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%>
<%@ page import="com.swiftcorp.portal.user.dto.UserDTO"%>
<%@ page import="com.swiftcorp.portal.common.login.dto.LoginDetailInfoDTO"%>
<%@ page import="com.swiftcorp.portal.geo.dto.GeoDTO"%>
<%@ page import="com.swiftcorp.portal.risk.dto.RiskDTO"%>
<%@page import="com.swiftcorp.portal.risk.service.RiskConstants"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.util.Calendar"%>

<link rel="stylesheet" type="text/css" href="./googleApiTest/css/default-clickdig.css">
<script language="javascript" src="./googleApiTest/script/jquery-1.2.6.js"></script>
<script language="javascript" src="./googleApiTest/script/admin_patient.js"></script>        
<link rel="stylesheet" href="./googleApiTest/modalbox/mbox.css" type="text/css">
<script type="text/javascript" src="./googleApiTest/modalbox/mbox.js"></script>
<script type="text/javascript" src="./googleApiTest/modalbox/ajax-dynamic-content.js"></script>
<script type="text/javascript" src="./googleApiTest/modalbox/ajax.js"></script> 
<script type="text/javascript" src="http://www.google.com/jsapi"></script>
        

<style type="text/css" rel="stylesheet">
			.large {
			  	height:250;
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
			  	color: #0066FF;
			  	float: left;
       			width: 380px;
			} 
			.c {
			  color: #0066FF;
			  float: left;
       			width: 180px;
			}
			
		</style>
<script type="text/javascript">
	
	

        

    
	var reader = new FileReader();
	 
	reader.onload = function (evt) {
		alert('evt::'+evt);
	    // do something with the file once it's loaded
	    var data = evt.target.result, // file is stored in the result attribute;
	    img = document.createElement("img");	 
	    img.src = data;
	    document.getElementsByTagName("body").appendChild(img);
	}
	reader.readAsDataURL(file);
	
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
	LoginDetailInfoDTO loginInfo = (LoginDetailInfoDTO)request.getSession ().getAttribute ( SESSION_KEYS.LOGIN_INFO );
	UserDTO user = (UserDTO) loginInfo.getUser ();
	RoleDTO roleDTO = user.getRole ();
	long roleId = roleDTO.getComponentId ();
	boolean isReadOnly = false;
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
	
	MotherBeneficiaryDTO motherBeneficiaryDTO = (MotherBeneficiaryDTO)session.getAttribute(SESSION_KEYS.BENEFICIARY_TO_MODIFY);
	List<ChildBeneficiaryDTO> childBeneficiaryDTOSet = (List<ChildBeneficiaryDTO>)session.getAttribute(SESSION_KEYS.CHILDREN_OF_MOTHER);
	HouseholdDTO householdDTO = (HouseholdDTO)session.getAttribute(SESSION_KEYS.HHDTO_OF_MEMBER);
	GeoDTO branchGeo = null;
	GeoDTO regionGeo = null;
	HouseholdMemberDTO householdMemberDTO = (HouseholdMemberDTO)session.getAttribute(SESSION_KEYS.HHMEMBERDTO_OF_MEMBER);
	String visitId = request.getParameter("visitId");
	String visitType = request.getParameter("detailType");
	System.out.println("visitId::"+visitId+" visitType::"+visitType);
	HouseholdMotherVisitDTO lastMotherVisitDTO = null;
	String householdNo = "";
	Set<HouseholdMotherVisitDTO> householdMotherVisitDTOSet = new HashSet<HouseholdMotherVisitDTO>();
	
	if(householdDTO!=null)
	{
		branchGeo = householdDTO.getBranch();
		regionGeo = branchGeo.getParentArea();
		
		householdNo = householdDTO.getHouseNo();
		if(householdNo.indexOf("/")>-1)
		{
			householdNo = householdNo.substring(householdNo.lastIndexOf("/")+1,householdNo.length());
		}
		Set<HouseholdMemberDTO> householdMemberDTOSet = householdDTO.getHouseholdMemberSet ();
		
		for ( HouseholdMemberDTO householdMemberDTO2 : householdMemberDTOSet )
		{
		
			Set<HouseholdMemberVisitDTO> householdMemberVisitDTOSet = householdMemberDTO2.getHouseholdMemberVisitSet ( );
			
			for( HouseholdMemberVisitDTO householdMemberVisitDTO : householdMemberVisitDTOSet )
			{
				if(householdMemberVisitDTO.getMemberVisitType ( )==1)
				{
					lastMotherVisitDTO = (HouseholdMotherVisitDTO)householdMemberVisitDTO;
					break;
				}
			}
			
		}
		
		/*
		if(householdMemberVisitDTOSet!=null && householdMemberVisitDTOSet.size()>0)
		{
			System.out.println("householdMotherVisitDTOSet.size:::"+householdMotherVisitDTOSet.size());
			
			for(HouseholdMotherVisitDTO householdMotherVisitDTO:householdMemberVisitDTOSet)
			{
				if(lastMotherVisitDTO==null)
				{
					lastMotherVisitDTO = householdMotherVisitDTO;
				}
			}
			//lastMotherVisitDTO = (HouseholdMotherVisitDTO)householdMotherVisitDTOSet.get(1);//householdMotherVisitDTOSet.size()-1
		}
		*/
	}
	
	MotherBeneficiaryHistoryDTO motherBeneficiaryHistoryDTO = null;
	if(motherBeneficiaryDTO!=null)
	{
		motherBeneficiaryHistoryDTO = motherBeneficiaryDTO.getMotherBeneficiaryHistoryDTO();
	}
	Set<PregnancyRecordDTO> pregnancyRecordSet = null;
	
	PregnancyRecordDTO currentPregnancyRecorDTO = null;
	TrimesterHealthInfoDTO trimesterHealthInfoDTO = null;
	ReferralRecordDTO referralRecordDTO = null;
	FirstTrimesterRecordDTO lastFirstTrimesterRecordDTO = null;
	SecondTrimesterRecordDTO lastSecondTrimesterRecordDTO = null;
	ThirdTrimesterRecordDTO lastThirdTrimesterRecordDTO = null;
	DeliveryRecordDTO deliveryRecordDTO = null;
	MiscarriageSectionDTO miscarriageSectionDTO = null;
	PNCSectionDTO lastPNCSectionDTO = null;
	VisitDTO currentPregVisit = null;
	VisitDTO lastVisit = null;
	String skId = "";	
	String ssId = "";
	if(householdDTO!=null)
	{
		ssId = householdDTO.getSsId();
	}
	if(motherBeneficiaryDTO!=null)
	{
		currentPregnancyRecorDTO = motherBeneficiaryDTO.getCurrentPregnancyRecord();
		
		if(currentPregnancyRecorDTO!=null)
		{		
			currentPregVisit = currentPregnancyRecorDTO.getVisitDTO();
			if(currentPregVisit!=null)
			{
				skId = currentPregVisit.getUserDTO().getUniqueCode();
			}				
			Set<FirstTrimesterRecordDTO> firstTrimesterRecordSet = (Set<FirstTrimesterRecordDTO>)currentPregnancyRecorDTO.getFirstTrimesterSet ();
			Set<SecondTrimesterRecordDTO> secondTrimesterRecordSet = (Set<SecondTrimesterRecordDTO>)currentPregnancyRecorDTO.getSecondTrimesterSet ();
			Set<ThirdTrimesterRecordDTO> thirdTrimesterRecordSet= (Set<ThirdTrimesterRecordDTO>)currentPregnancyRecorDTO.getThirdTrimesterSet ();							
										
			Set<TrimesterHealthInfoDTO> trimesterHealthInfoSet = (Set<TrimesterHealthInfoDTO>)currentPregnancyRecorDTO.getTrimesterHealthInfoSet ();
			Set<ReferralRecordDTO> referralSectionSet= (Set<ReferralRecordDTO>)currentPregnancyRecorDTO.getReferralSectionSet ();
			Set<PNCSectionDTO> pncSectionSet= (Set<PNCSectionDTO>)currentPregnancyRecorDTO.getPncSectionSet ();
			
			miscarriageSectionDTO = (MiscarriageSectionDTO)currentPregnancyRecorDTO.getMiscarriageSectionDTO();
			deliveryRecordDTO = (DeliveryRecordDTO)currentPregnancyRecorDTO.getDeliveryRecordDTO();
			
			if(visitType.equalsIgnoreCase(ViewConstants.THIRD_TRIMESTER))
			{
				for(ThirdTrimesterRecordDTO thirdTrimesterRecordDTO:thirdTrimesterRecordSet)
				{
					if((""+thirdTrimesterRecordDTO.getComponentId()).equalsIgnoreCase(visitId))
					{
						trimesterHealthInfoDTO = thirdTrimesterRecordDTO.getTrimesterHealthInfoDTO ();					
						lastThirdTrimesterRecordDTO = thirdTrimesterRecordDTO;
						lastVisit = thirdTrimesterRecordDTO.getVisitDTO();
					}
				}
			}
			if(visitType.equalsIgnoreCase(ViewConstants.SECOND_TRIMESTER))
			{			
				for(SecondTrimesterRecordDTO secondTrimesterRecordDTO:secondTrimesterRecordSet)
				{
					if((""+secondTrimesterRecordDTO.getComponentId()).equalsIgnoreCase(visitId))
					{
						trimesterHealthInfoDTO = secondTrimesterRecordDTO.getTrimesterHealthInfoDTO ();					
						lastSecondTrimesterRecordDTO = secondTrimesterRecordDTO;
						lastVisit = secondTrimesterRecordDTO.getVisitDTO();
					}
				}
			}
			if(visitType.equalsIgnoreCase(ViewConstants.FIRST_TRIMESTER))
			{
				for(FirstTrimesterRecordDTO firstTrimesterRecordDTO:firstTrimesterRecordSet)
				{
					if((""+firstTrimesterRecordDTO.getComponentId()).equalsIgnoreCase(visitId))
					{
						lastFirstTrimesterRecordDTO = firstTrimesterRecordDTO;					
						trimesterHealthInfoDTO = firstTrimesterRecordDTO.getTrimesterHealthInfoDTO ();
						lastVisit = firstTrimesterRecordDTO.getVisitDTO();
					}
				}
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
					lastVisit = pncSectionDTO.getVisitDTO();
				}
			}
			
		}
	}
	
	
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
					<img width="100" border="2" height="120" src="<%=lastVisit.getVisitPic()%>" alt=""/>
					</div>
					<div id="basicInfo" style="height:220px;width:60%;float:left">
					
						<span class="c"><label><bean:message key="label.beneficiary.skId" /></label></span>
						<span><%=skId%></span><br/>
						<span class="c"><label><bean:message key="label.beneficiary.ssId" /></label></span>
						<span><%=ssId%></span><br/>
						<span class="c"><label><bean:message key="label.beneficiary.householdId" /></label></span>
						<span><%=householdNo%></span><br/>
						<span class="c"><label><bean:message key="label.beneficiary.branchGeo" /></label></span>
						<span><%=branchGeo.getCode()%></span><br/>
						<span class="c"><label><bean:message key="label.beneficiary.regionGeo" /></label></span>
						<span><%=regionGeo.getCode()%></span><br/>
						<span class="c"><label><bean:message key="label.beneficiary.contactNo" /></label></span>
						<span><%=householdMemberDTO!=null?householdMemberDTO.getMobileNo():""%></span><br/>
						<span class="c"><label><bean:message key="label.beneficiary.nationalId" /></label></span>
						<span></span><br/>
						<span class="c"><label><bean:message key="label.beneficiary.motherName" /></label></span>
						<span><span>
						<OBJECT CLASSID="clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B" WIDTH="180"
						    HEIGHT="25" CODEBASE="http://www.apple.com/qtactivex/qtplugin.cab">
						
						<PARAM name="SRC" VALUE="http://www.parkerriver.com/films/who_bene2.mov">
						
						<PARAM name="AUTOPLAY" VALUE="false">
						
						<PARAM name="CONTROLLER" VALUE="true">
						
						<EMBED SRC=
						   "<%=motherBeneficiaryDTO.getNameFile()%>" 
						    WIDTH="180" HEIGHT="20" 
						    AUTOPLAY="false" CONTROLLER=
						    "true" PLUGINSPAGE="http://www.apple.com/quicktime/download/">
						
						</EMBED>
						
						</OBJECT>
						
						</span></span><br/>
						<span class="c"><label><bean:message key="label.beneficiary.motherBirthDate" /></label></span>
						<span></span><br/>
						<span class="c"><label><bean:message key="label.beneficiary.husbandName" /></label></span>
						<span><%=householdMemberDTO!=null?householdMemberDTO.getHusbandName():""%></span><br/>						
						<span class="c"><label><bean:message key="label.beneficiary.age" /></label></span>
						<span><%=householdMemberDTO!=null?householdMemberDTO.getMemberAge():""%></span><br/>
					</div>					
				</div>
				
				<br style="clear: left" />
				<div id="maternalTabs" class="indentmenu">
					<ul>
						<li><a href="#" rel="maternalMedical" class="selected"><bean:message key="label.beneficiary.mother.medical" /></a></li>
						<li><a href="#" rel="maternalNonMedical"><bean:message key="label.beneficiary.mother.nonMedical" /></a></li>					
					</ul>
				<br style="clear: left" />
				</div>
			<div class="large">			
				<div id="maternalMedical">
				<h3><bean:message key="label.beneficiary.mother.medical" /></h3>
					<%						
						//for(NeoNatalVisitRecord neoNatalVisitRecord:neoNatalVisitRecordSet)
						//{
						if(lastVisit!=null){
							%>
							<h4><bean:message key="label.visit.visitInfo" /></h4>
							<div id="visitInfo">
								<span class="b"><label><bean:message key="label.visit.startTimeStamp" /></label></span>
								<span><%=CalendarUtils.calendarToStringDateTimeFormat(lastVisit.getStartTimeStamp())%></span><br/>
								<span class="b"><label><bean:message key="label.visit.endTimeStamp" /></label></span>
								<span><%=CalendarUtils.calendarToStringDateTimeFormat(lastVisit.getEndTimeStamp())%></span><br/>
								<span class="b"><label><bean:message key="label.visit.pictureTimeStamp" /></label></span>
								<span><%=CalendarUtils.calendarToStringDateTimeFormat(lastVisit.getVisitPicTimeStamp())%></span><br/>
								<span class="b"><label><bean:message key="label.visit.dataSavingTime" /></label></span>
								<span><%=CalendarUtils.calendarToStringDateTimeFormat(lastVisit.getDataArrivingTime())%></span><br/>
								<span class="b"><label><bean:message key="label.visit.diffBetweenvisitPicTimeStart" /></label></span>
								<span><%=lastVisit.getDiffBetweenvisitPicTime()%></span><br/>
								<span class="b"><label><bean:message key="label.visit.diffBetweenvisitPicTimeEnd" /></label></span>
								<span><%=lastVisit.getDiffBetweenvisitPicAndQEndTime()%></span><br/>
								<span class="b"><label><bean:message key="label.visit.differanceBetweenTime" /></label></span>
								<span><%=lastVisit.getDifferanceBetweenTime()%></span><br/>
								
							</div>
							<%
						}
					%>
					<h4><bean:message key="label.beneficiary.mother.current.visit" /></h4>
					<div id="Current visit information">
						<span class="b"><label><bean:message key="label.beneficiary.mother.current.visit.pregnant" /></label></span>
						<span><%=currentPregnancyRecorDTO!=null?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.current.visit.edd" /></label></span>
						<span><%=currentPregnancyRecorDTO!=null?CalendarUtils.calendarToString(currentPregnancyRecorDTO.getEDD()):""%></span><br/>
					</div>
					<br>
					<br>
					<%
					if(trimesterHealthInfoDTO!=null)
					{
					%>
					<h4><bean:message key="label.beneficiary.mother.presentingComplaints" /></h4>
					<div id="Presenting Complaints">
						<span class="b"><label><bean:message key="label.beneficiary.mother.presentingComplaints.severeHeadache" /></label></span>
						<span><%=trimesterHealthInfoDTO.getSevereHeadache ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.presentingComplaints.blurredVision" /></label></span>
						<span><%=trimesterHealthInfoDTO.getBluredVision ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.presentingComplaints.thirsty" /></label></span>
						<span><%=trimesterHealthInfoDTO.getFeelIncreasedThirsty ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.presentingComplaints.unusualFatigue" /></label></span>
						<span><%=trimesterHealthInfoDTO.getUnusalFatigue ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.presentingComplaints.vaginalBleeding" /></label></span>
						<span><%=trimesterHealthInfoDTO.getUrinalBleeding ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.presentingComplaints.vaginalInfections" /></label></span>
						<span><%=trimesterHealthInfoDTO.getVarginalInfection ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.presentingComplaints.UrinatingMoreFrequently" /></label></span>
						<span><%=trimesterHealthInfoDTO.getExtraUrinate ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.presentingComplaints.Convulsion" /></label></span>
						<span><%=trimesterHealthInfoDTO.getIsConvulsion ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.presentingComplaints.Jaundice" /></label></span>
						<span><%=trimesterHealthInfoDTO.getIsJaundice ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.presentingComplaints.presentingComplience.Others" /></label></span>
						<span><%=trimesterHealthInfoDTO.getOtherProblem ( )%></span><br/>
					</div>
					<%
					}
					if(motherBeneficiaryHistoryDTO!=null)
					{
					%>
					<br>
					<br>
					<h4><bean:message key="label.beneficiary.mother.obstetric" /></h4>
					<div id="Obstetric history">
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.previousPregnancies" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getTotPreg()%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.aliveChildren" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getlChildLive()%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.AgeOfLastChild" /></label></span>
						<span><%=lastMotherVisitDTO!=null?lastMotherVisitDTO.getAgeOfYoungerChild():""%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.abortions" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getMrCount ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.CaesarianBirths" /></label>
						</span><span><%=motherBeneficiaryHistoryDTO.getCesrCnt ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.Episiotomy" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getApcoTomy ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.stillbirthsOrIntraUterineDeaths" /></label>
						</span><span></span><br/>
						
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.neonatalDeathsWithinAweek" /></label>
						</span><span><%=motherBeneficiaryHistoryDTO.getDth7Day ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.infantsagedbetween8daysand1" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getDth1Year ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.infantsagedwithin5" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getDth5Year ( )%></span><br/>						
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.infantsagedabove5" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getDth5Pls ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.premature" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getEarlyDel ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.obstructed" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getDelProb ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.excessivebleeding" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getEXbleed ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.convulsionsduringpregnancy" /></label></span>
						<span><%//=%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.deliveringsmallchild" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getSmallChild ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.obstetric.birthDefects" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getDefectChild ( )%></span><br/>
					</div>
					<br>
					<br>
					<h4><bean:message key="label.beneficiary.mother.pastMedical" /></h4>
					<div id="Past medical & TT dose history">
						<span class="b"><label><bean:message key="label.beneficiary.mother.pastMedical.dosesOfTT" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getTitiTica ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pastMedical.Diabetes" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getDiabetcs()==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pastMedical.Asthma" /></label></span>
						<span><%=motherBeneficiaryHistoryDTO.getAsma ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
					</div>
					<%
					}
					if(trimesterHealthInfoDTO!=null)
					{
					%>
					<br>
					<br>
					<h4><bean:message key="label.beneficiary.mother.physicalexamination" /></h4>
					<div id="Findings of physical examination & point-of-care tests">
						<span class="b"><label><bean:message key="label.beneficiary.mother.physicalexamination.Anemia" /></label></span>
						<span><%=trimesterHealthInfoDTO.getSevereAnemia ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.physicalexamination.Jaundice" /></label></span>
						<span><%=trimesterHealthInfoDTO.getIsJaundice ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.physicalexamination.BloodPressure" /></label></span>
						<span><%=trimesterHealthInfoDTO.getBloodPressure ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.physicalexamination.Highfever" /></label></span>
						<span><%=trimesterHealthInfoDTO.getHighFever ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.physicalexamination.Heightofuterus" /></label></span>
						<span><%=trimesterHealthInfoDTO.getUterusHeight ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.physicalexamination.Edema" /></label></span>
						<span><%=trimesterHealthInfoDTO.getEdema ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.physicalexamination.Convulsions" /></label></span>
						<span><%=trimesterHealthInfoDTO.getIsConvulsion ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.physicalexamination.striptestpositive" /></label></span>
						<span><%//=%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.physicalexamination.filetolisten" /></label></span>
						<span><%=trimesterHealthInfoDTO.getOtherComplication ( )%></span><br/>
					</div>
					<%
					}
					if(referralRecordDTO!=null)
					{
					%>
					<br>
					<br>
					<h4><bean:message key="label.beneficiary.mother.referralInfo" /></h4>
					<div id="Referral Information">
						<span class="b"><label><bean:message key="label.beneficiary.mother.referralInfo.referredTo" /></label></span>
						<span><%=(referralRecordDTO!=null)?referralRecordDTO.getPlaceOfReferral ( ):""%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.referralInfo.patientBeenReferred" /></label></span>
						<span><%=(referralRecordDTO!=null)?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.referralInfo.dateOfReferral" /></label></span>
						<span><%=(referralRecordDTO!=null)?CalendarUtils.calendarToString(referralRecordDTO.getDateOfReferral ( )):""%></span><br/>
					</div>
					<br>
					<br>
					<%
					}
					if(lastSecondTrimesterRecordDTO!=null)
					{
					%>
					
					<h4><bean:message key="label.beneficiary.mother.trimesterInfo" /></h4>					
					<div id="Trimester information">
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.trimester" /></label></span>
						<span><%="Second"%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.ironTablets" /></label></span>
						<span><%=lastSecondTrimesterRecordDTO.getNoOfIronTablet ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.secondTTDoseDate" /></label></span>
						<span><%=lastSecondTrimesterRecordDTO.getDateOfSecondTitiDos ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.firstTTDoseDate" /></label></span>
						<span><%=lastSecondTrimesterRecordDTO.getDateOfFirstTitiDos ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.movementOfBaby" /></label></span>
						<span><%=lastSecondTrimesterRecordDTO.getMovementOfChild ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.Anthelmintic" /></label></span>
						<span><%=lastSecondTrimesterRecordDTO.getIsHelminthicDrug ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
					</div>
					<%
					}
					if(lastThirdTrimesterRecordDTO!=null)
					{
					%>
					
					<h4><bean:message key="label.beneficiary.mother.trimesterInfo" /></h4>					
					<div id="Trimester information">
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.trimester" /></label></span>
						<span><%="Third"%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.deliveryKit" /></label></span>
						<span><%=lastThirdTrimesterRecordDTO.getDeliveryKit ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.relationshipWithPatient" /></label></span>
						<span><%=lastThirdTrimesterRecordDTO.getRelationshipWithPatient ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.isFullCheckUp" /></label></span>
						<span><%=lastThirdTrimesterRecordDTO.getIsFullCheckUp ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.isChildPlacementNatural" /></label></span>
						<span><%=lastThirdTrimesterRecordDTO.getIsChildPlacementNatural ( )==1?ViewConstants.ANSWER_NORMAL:ViewConstants.ANSWER_ABNORMAL%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.movementOfChild" /></label></span>
						<span><%=lastThirdTrimesterRecordDTO.getMovementOfChild ( )==1?ViewConstants.ANSWER_NORMAL:ViewConstants.ANSWER_ABNORMAL%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.trimesterInfo.heartbeatOfChild" /></label></span>
						<span><%=lastThirdTrimesterRecordDTO.getHeartbeatOfChild ( )%></span><br/>
					</div>
					<%
					}
					%>
					<br>
					<br>
					<%if(deliveryRecordDTO!=null && deliveryRecordDTO.getDateOfDelivery ( )!=null){%>
					<h4><bean:message key="label.beneficiary.mother.delivery" /></h4>
					<div id="Delivery information">
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.SSPresent" /></label></span>
						<span><%=deliveryRecordDTO.getPresenceOfSK ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.birthKit" /></label></span>
						<span><%=deliveryRecordDTO.getDeliveryKit ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.Misoprostol" /></label></span>
						<span><%=deliveryRecordDTO.getMisoprostal ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.typeOfDelivery" /></label></span>
						<span><%=deliveryRecordDTO.getTypeOfDelivery ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.tymeOfDelivery" /></label></span>
						<span><%=CalendarUtils.calendarToString(deliveryRecordDTO.getDateOfDelivery ( ))%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.complications" /></label></span>
						<span><%=deliveryRecordDTO.getTypeOfDeliveryComplication ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.fileToListen" /></label></span>
						<span><%=deliveryRecordDTO.getOtherDeliveryComplication ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.treatment" /></label></span>
						<span><%=deliveryRecordDTO.getPostHealthService ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.bornMacerated" /></label></span>
						<span><%=deliveryRecordDTO.getMeltDeathChild ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.bornDead" /></label></span>
						<span><%=deliveryRecordDTO.getNoOfDeathChild ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.bornAlive" /></label></span>
						<span><%=deliveryRecordDTO.getNoOfAliveChild ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.location" /></label></span>
						<span><%=deliveryRecordDTO.getPlaceOfDelivery ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.delivery.assisted" /></label></span>
						<span><%=deliveryRecordDTO.getDeliveryAssistant ( )%></span><br/>
					</div>
					<%}%>
					<br>
					<br>
					<%
					if(lastPNCSectionDTO!=null)
					{
					%>
					<h4><bean:message key="label.beneficiary.mother.pnc" /></h4>
					<div id="Post Natal Care Information">
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.vitaminA" /></label></span>
						<span><%=lastPNCSectionDTO.getVitaminACapsul ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.tornPerineum" /></label></span>
						<span><%=lastPNCSectionDTO.getTornPerineum ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.highFever" /></label></span>
						<span><%=lastPNCSectionDTO.getIsfever ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.severeHeadache" /></label></span>
						<span><%=lastPNCSectionDTO.getSevereHeadache ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.abdomenPain" /></label></span>
						<span><%=lastPNCSectionDTO.getAbdomenPain ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.fileToListen" /></label></span>
						<span><%=lastPNCSectionDTO.getOtherSymptom ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.ironFolic" /></label></span>
						<span><%=lastPNCSectionDTO.getIronTablet ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.excessiveBleeding" /></label></span>
						<span><%=lastPNCSectionDTO.getExcessiveBleeding ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.edema" /></label></span>
						<span><%//=%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.temperature" /></label></span>
						<span><%=lastPNCSectionDTO.getBodyTemperature ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.breastProblems" /></label></span>
						<span><%=lastPNCSectionDTO.getProblemOnBreast ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.bloodPressure" /></label></span>
						<span><%=lastPNCSectionDTO.getBloodPressure ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.odorousDischarge" /></label></span>
						<span><%=lastPNCSectionDTO.getOdorousDischarge ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.pnc.convulsions" /></label></span>
						<span><%=lastPNCSectionDTO.getIsConvulsion ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
					</div>
					<%}%>
					<br>
					<br>
					<%if(miscarriageSectionDTO!=null && miscarriageSectionDTO.getWayOfAbortion()!=null){%>
					<h4><bean:message key="label.beneficiary.mother.miscarriage" /></h4>
					<div id="Miscarriage information">
						<span class="b"><label><bean:message key="label.beneficiary.mother.miscarriage.terminatedBy" /></label></span>
						<span><%=miscarriageSectionDTO.getWayOfAbortion ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.miscarriage.motherDead" /></label></span>
						<span><%=miscarriageSectionDTO.getIsMotherAlive ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.miscarriage.DateOfDeath" /></label></span>
						<span><%=CalendarUtils.calendarToString(miscarriageSectionDTO.getDateOfDeath ( ))%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.miscarriage.careReceivedAbortion" /></label></span>
						<span><%=miscarriageSectionDTO.getSeviceAfterAbortion ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.miscarriage.abortionConducted" /></label></span>
						<span><%=miscarriageSectionDTO.getHelperOfAbortion ( )%></span><br/>
					</div>
					<%}%>
					<br>
					<br>
				</div>
				
				<div id="maternalNonMedical">
				<h3><bean:message key="label.beneficiary.mother.maternalNonMedical" /></h3>
				<%if(lastThirdTrimesterRecordDTO!=null){%>
					<h4><bean:message key="label.beneficiary.mother.maternalNonMedical.deliveryPreparation" /></h4>
					<div id="Preparation for the Delivery">
						<span class="b"><label><bean:message key="label.beneficiary.mother.maternalNonMedical.deliveryPreparation.savings" /></label></span>
						<span><%=lastThirdTrimesterRecordDTO.getSavedMoney ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.maternalNonMedical.deliveryPreparation.driver" /></label></span>
						<span>
						<OBJECT CLASSID="clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B" WIDTH="180"
						    HEIGHT="25" CODEBASE="http://www.apple.com/qtactivex/qtplugin.cab">
						
						<PARAM name="SRC" VALUE="http://www.parkerriver.com/films/who_bene2.mov">
						
						<PARAM name="AUTOPLAY" VALUE="false">
						
						<PARAM name="CONTROLLER" VALUE="true">
						
						<EMBED SRC=
						   "<%=lastThirdTrimesterRecordDTO.getNameOfDriver ( )%>" 
						    WIDTH="180" HEIGHT="20" 
						    AUTOPLAY="false" CONTROLLER=
						    "true" PLUGINSPAGE="http://www.apple.com/quicktime/download/">
						
						</EMBED>
						
						</OBJECT>
						</span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.maternalNonMedical.deliveryPreparation.place" /></label></span>
						<span><%=lastThirdTrimesterRecordDTO.getPlaceOfDelivery ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.mother.maternalNonMedical.deliveryPreparation.accompany" /></label></span>
						<span><%=lastThirdTrimesterRecordDTO.getDeliveryAssistant ( )%></span><br/>
					</div>
				<%}else{
					out.println("No Information");
				}%>
				</div>
				<br>
				<br>				
			</div>
			<script type="text/javascript">
				var myflowers=new ddtabcontent("maternalTabs") //enter ID of Tab Container
				myflowers.setpersist(true) //toogle persistence of the tabs' state
				myflowers.setselectedClassTarget("linkparent") //"link" or "linkparent"
				myflowers.init();
			</script>				
			</html:form>
			
			<table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
	            <colgroup>
	                <col width="63%">
	                <col width="2%">
	                <col width="35%">
	            </colgroup>	            
	  			<%
				if(roleId == ApplicationConstants.ROLE_DOCTOR)
				{
				%>
			    <tr>
			        <td colspan="3"><font size="2" color="blue">[Ctrl + g] for switching between Bangla and English</font></td>
			    </tr>
	                            
	            <tr bgcolor="#eeeeee">
	                 <td colspan="3">
	                 <fieldset><legend><label class="titleText">Physician's Advice </label>
	                     <table width="100%" border="0" cellpadding="0" cellspacing="0">
	                        
	                         <tr valign="top">
		                         <td>
			                         <div id="content">	                         
				                         <form name="medicalAdviceForm" action="medicalAdviceAction.csmp?method=" method="POST" >
				                         	<input type="hidden" id="beneficiaryId" name="beneficiaryId" value="<%=motherBeneficiaryDTO.getBeneficiaryId()%>">
				                         	<input type="hidden" id="skId" name="skId" value="<%=skId%>">
				                         	<input type="hidden" id="householdId" name="householdId" value="<%=householdDTO!=null?householdDTO.getHouseNo ( ):""%>">
				                         	<input type="hidden" id="beneficiaryType" name="beneficiaryType" value="mother">
				                         	<textarea rows="3" cols="110" id="phAdvice" name ="phAdvice" align="left"></textarea>
				                         	<a href="javascript:void(0);" onclick="saveDoctorsComment();"><input type="button" value="Submit" class="button"></a>
				                         </form>
			                         </div>
		                         </td>
	                         </tr>                       
	                     </table>
	                   </legend></fieldset>
	                </td>
	            </tr> 
	            <%
	            }	            
	            %>
	            <tr>            
	            <logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareBeneficiaryModifyAction&methodParams=cancel"></jsp:include>
				</logic:equal>
	            </tr>	
       		 </table>
			
		</tbody> 
	</table>	

<script type="text/javascript">

<%
	if(roleId == ApplicationConstants.ROLE_DOCTOR)
	{
	%>	
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
		     //control.makeTransliteratable(['motherNameAudio']);
		   	 //control.makeTransliteratable(['husbandNameAudio']);
		     //control.makeTransliteratable(['occupationAudio']);
		     //control.makeTransliteratable(['houseOwnerNameAudio']);
		    // control.makeTransliteratable(['houseHoldNameAudio']);
		
		}
		google.setOnLoadCallback(OnLoad);
	<%
	}
	%>
function saveDoctorsComment()
{
	//alert('clicked');
	//setUni2JavaValue(document.getElementById('phAdvice'), document.getElementById('phAdvice')); 
	
	path='medicalAdviceAction.csmp?method=addMedicalAdviceByDoctor';
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
<%
if(roleId == ApplicationConstants.ROLE_DOCTOR)
{
%>	
	messageObj = new DHTML_modalMessage();	// We only create one object of this class
	messageObj.setShadowOffset(0);	// Large shadow
<%
}
%>

//var myflowers=new ddtabcontent("flowertabs") //enter ID of Tab Container
//myflowers.setpersist(true) //toogle persistence of the tabs' state
//myflowers.setselectedClassTarget("link") //"link" or "linkparent"
//myflowers.init();


//myflowers.init(3000);
</script>