<%@ include file="/Common/Include/taglib.jsp"%>

<%@ page import="com.swiftcorp.portal.beneficiary.dto.BeneficiaryDTO"%>
<%@ page import="com.swiftcorp.portal.beneficiary.dto.MedicalAdviceDTO"%>
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

<%@ page import="com.swiftcorp.portal.common.ApplicationConstants"%>
<%@ page import="com.swiftcorp.portal.common.dto.FunctionDTO"%>
<%@ page import="com.swiftcorp.portal.role.dto.RoleDTO"%>
<%@ page import="com.swiftcorp.portal.user.dto.UserDTO"%>
<%@ page import="com.swiftcorp.portal.common.login.dto.LoginDetailInfoDTO"%>
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
<%@ page pageEncoding="UTF-8" %>     
<%@ page language="java" contentType="text/html;charset=UTF-8" %>  
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
		
	
	boolean isReadOnly = false;
	//private static final Log logger = LogFactory.getLog ( ChildBeneficiaryDTO.class );
	//logger.info("from child beneficiary");
	ChildBeneficiaryDTO childBeneficiaryDTO = (ChildBeneficiaryDTO)session.getAttribute(SESSION_KEYS.BENEFICIARY_TO_MODIFY);
	HouseholdDTO householdDTO = (HouseholdDTO)session.getAttribute(SESSION_KEYS.HHDTO_OF_MEMBER);
	HouseholdMemberDTO householdMemberDTO = (HouseholdMemberDTO)session.getAttribute(SESSION_KEYS.HHMEMBERDTO_OF_MEMBER);
	String visitType = request.getParameter("visitType");
	String visitId = request.getParameter("visitId");
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
		
	Set<ReferralRecordDTO> referralSectionSet = new HashSet<ReferralRecordDTO>();
	Set<ChildVisitRecord> childVisitRecordSet = null;
	Set<InfantVisitRecord> infantVisitRecordSet = null;
	Set<NeoNatalVisitRecord> neoNatalVisitRecordSet = null;
	NeoNatalVisitInfo neoNatalVisitInfo = null;
	ChildVisitRecord childVisitRecord = null;
	ChildFirstVisitRecord childFirstVisitRecord = null;
	InfantVisitRecord infantVisitRecord = null;
	NeoNatalVisitRecord neoNatalVisitRecord = null;
	ReferralRecordDTO referralRecordDTO = null;
	VisitDTO lastVisitDTO = null;
	if(childBeneficiaryDTO!=null)
	{
		childBeneficiaryDTO.getBirthWieght ( );
		childFirstVisitRecord = childBeneficiaryDTO.getChildFirstVisitRecord ( );
		childVisitRecordSet = childBeneficiaryDTO. getChildVisitRecordSet ( );
		infantVisitRecordSet = childBeneficiaryDTO.getInfantVisitRecordSet ( );
		neoNatalVisitRecordSet = childBeneficiaryDTO.getNeoNatalVisitRecordSet ( );
		if(visitType.equalsIgnoreCase("infant"))
		{
			for(InfantVisitRecord infantVisitRecordCh:infantVisitRecordSet)
			{
				if((""+infantVisitRecordCh.getComponentId()).equalsIgnoreCase(visitId))
				{
					infantVisitRecord = infantVisitRecordCh;
					lastVisitDTO = infantVisitRecord.getVisitDTO();
					referralRecordDTO = infantVisitRecord.getReferralRecordDTO();
				}			
			}
		}
		else if(visitType.equalsIgnoreCase("child"))
		{
			for(ChildVisitRecord childVisitRecordCh:childVisitRecordSet)
			{
				if((""+childVisitRecordCh.getComponentId()).equalsIgnoreCase(visitId))
				{
					childVisitRecord = childVisitRecordCh;
					lastVisitDTO = childVisitRecord.getVisitDTO();
					referralRecordDTO = childVisitRecord.getReferralRecordDTO();
				}
				//referralSectionSet.add(childVisitRecordCh.getReferralRecordDTO ( ));
			}
		}
		else if(visitType.equalsIgnoreCase("neoNatal"))
		{
			for(NeoNatalVisitRecord neoNatalVisitRecordCh:neoNatalVisitRecordSet)
			{
				if((""+neoNatalVisitRecordCh.getComponentId()).equalsIgnoreCase(visitId))
				{
					neoNatalVisitRecord = neoNatalVisitRecordCh;
					lastVisitDTO = neoNatalVisitRecord.getVisitDTO();
					referralRecordDTO = neoNatalVisitRecord.getReferralRecordDTO();
				}
				//referralSectionSet.add(neoNatalVisitRecordCh.getReferralRecordDTO ( ));
			}
		}
		else if(visitType.equalsIgnoreCase("first"))
		{
			referralRecordDTO = childFirstVisitRecord.getReferralRecordDTO();
			lastVisitDTO = childFirstVisitRecord.getVisitDTO();
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
					<img width="100" border="2" height="120" src="<%=lastVisitDTO.getVisitPic()%>" alt=""/>
				</div>
				<div id="basicInfo" style="height:200px;width:60%;float:left">
					<span class="c"><label><bean:message key="label.beneficiary.skId" /></label></span>
					<span><%=skId%></span><br/>
					<span class="c"><label><bean:message key="label.beneficiary.householdId" /></label></span>
					<span><%=householdNo%></span><br/>
					<span class="c"><label><bean:message key="label.beneficiary.branchGeo" /></label></span>
					<span><%=branchGeo.getCode()%></span><br/>
					<span class="c"><label><bean:message key="label.beneficiary.regionGeo" /></label></span>
					<span><%=regionGeo.getCode()%></span><br/>
					<span class="c"><label><bean:message key="label.beneficiary.ssId" /></label></span>
					<span><%=ssId %></span><br/>
					<span class="c"><label><bean:message key="label.beneficiary.motherId" /></label></span>
					<span><%=childBeneficiaryDTO.getMotherId()%></span><br/>
					<span class="c"><label><bean:message key="label.beneficiary.childName" /></label></span>
					<span>
						<OBJECT CLASSID="clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B" WIDTH="180"
						    HEIGHT="25" CODEBASE="http://www.apple.com/qtactivex/qtplugin.cab">
						
						<PARAM name="SRC" VALUE="http://www.parkerriver.com/films/who_bene2.mov">
						
						<PARAM name="AUTOPLAY" VALUE="false">
						
						<PARAM name="CONTROLLER" VALUE="true">
						
						<EMBED SRC=
						   "<%=childBeneficiaryDTO.getNameFile()%>" 
						    WIDTH="180" HEIGHT="25" 
						    AUTOPLAY="false" CONTROLLER=
						    "true" PLUGINSPAGE="http://www.apple.com/quicktime/download/">
						
						</EMBED>
						
						</OBJECT>
						
						</span><br/><br/>
					<span class="c"><label><bean:message key="label.beneficiary.childGender" /></label></span>
					<span><%=childBeneficiaryDTO.getSex()%></span><br/>
				</div>
			</div>
			<br>
			<br style="clear: left" />
			<div id="childTabs" class="indentmenu">
				<ul>
					<li><a href="#" rel="childMedical" class="selected"><bean:message key="label.beneficiary.child.medical" /></a></li>
					<li><a href="#" rel="childNonMedical"><bean:message key="label.beneficiary.child.nonMedical" /></a></li>					
				</ul>
				<br style="clear: left" />
			</div>
			<div class="large">					
				<div id="childMedical">
					<h3><bean:message key="label.beneficiary.child.medical" /></h3>
					<%
					
					%>
					<%						
						//for(NeoNatalVisitRecord neoNatalVisitRecord:neoNatalVisitRecordSet)
						//{
						if(lastVisitDTO!=null){
							%>
							<h4><bean:message key="label.visit.visitInfo" /></h4>
							<div id="visitInfo">
								<span class="b"><label><bean:message key="label.visit.startTimeStamp" /></label></span>
								<span><%=CalendarUtils.calendarToStringDateTimeFormat(lastVisitDTO.getStartTimeStamp())%></span><br/>
								<span class="b"><label><bean:message key="label.visit.endTimeStamp" /></label></span>
								<span><%=CalendarUtils.calendarToStringDateTimeFormat(lastVisitDTO.getEndTimeStamp())%></span><br/>
								<span class="b"><label><bean:message key="label.visit.pictureTimeStamp" /></label></span>
								<span><%=CalendarUtils.calendarToStringDateTimeFormat(lastVisitDTO.getVisitPicTimeStamp())%></span><br/>
								<span class="b"><label><bean:message key="label.visit.dataSavingTime" /></label></span>
								<span><%=CalendarUtils.calendarToStringDateTimeFormat(lastVisitDTO.getDataArrivingTime())%></span><br/>
								<span class="b"><label><bean:message key="label.visit.diffBetweenvisitPicTimeStart" /></label></span>
								<span><%=lastVisitDTO.getDiffBetweenvisitPicTime()%></span><br/>
								<span class="b"><label><bean:message key="label.visit.diffBetweenvisitPicTimeEnd" /></label></span>
								<span><%=lastVisitDTO.getDiffBetweenvisitPicAndQEndTime()%></span><br/>
								<span class="b"><label><bean:message key="label.visit.differanceBetweenTime" /></label></span>
								<span><%=lastVisitDTO.getDifferanceBetweenTime()%></span><br/>
								
							</div>
							<%
						}
					%>
					<h4><bean:message key="label.beneficiary.mother.presentingComplaints" /></h4>
					<%
						if(neoNatalVisitRecord!=null)
						{
							NeoNatalComplicacyRecord neoNatalComplicacyRecord =  neoNatalVisitRecord.getNeoNatalComplicacyRecord ( );
							%>
							
					
							<div id="Presenting complaints / Current Visit">
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.currentWeight" /></label></span>
								<span><%=childWieght%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.unbilicusDryAndClean" /></label></span>
								<span><%=neoNatalVisitRecord.getIsUmbilicalCordDried()==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.canSuckWell" /></label></span>
								<span><%=neoNatalComplicacyRecord.getIsChildBreastFeedNormal ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.vomiting" /></label></span>
								<span><%=neoNatalComplicacyRecord.getIsRapidBomiting ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.diarrhea" /></label></span>
								<span><%=neoNatalComplicacyRecord.getIsDiarrhea ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.breathingFast" /></label></span>
								<span><%=neoNatalComplicacyRecord.getIsRapidBreathing ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.chestIndrawing" /></label></span>
								<span><%=neoNatalComplicacyRecord.getIsChildChestGoDownWhileBreathing ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.umbilicalInfection" /></label></span>
								<span><%=neoNatalComplicacyRecord.getIsInfectedBellyButton ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.unconsciouenessDrowsiness" /></label></span>
								<span><%=neoNatalComplicacyRecord.getIsFaint ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.seizures" /></label></span>
								<span><%=neoNatalComplicacyRecord.getIsChildConvultion ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.tenPustules" /></label></span>
								<span><%=neoNatalComplicacyRecord.getIsMoreThanTen ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
								<span class="b"><label><bean:message key="label.beneficiary.child.presentingComplaints.otherComplaints" /></label></span>
								<span><%=neoNatalComplicacyRecord.getOtherInfantComplication ( )%></span><br/>
							</div>
						<%
						}
					%>
					<br>
					<br>
					<%
					if( childFirstVisitRecord != null )
					{
						neoNatalVisitInfo = childFirstVisitRecord.getNeoNatalVisitInfo ( );
						%>
						<h4><bean:message key="label.beneficiary.child.birthAndNeonatalHistory" /></h4>
						<div id="Birth & Neonatal History">
							<span class="b"><label><bean:message key="label.beneficiary.child.birthAndNeonatalHistory.dateOfBirth" /></label></span>
							<span><%=CalendarUtils.calendarToString(childBeneficiaryDTO.getDateOfBirth ( ))%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.birthAndNeonatalHistory.birthAsphyxia" /></label></span>
							<span><%=childFirstVisitRecord.getIsBirthAsphyxiaIdentified ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.birthAndNeonatalHistory.resuscitated" /></label></span>
							<span><%=childFirstVisitRecord.getIsAsphyxiaRevived ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.birthAndNeonatalHistory.birthWeight" /></label></span>
							<span><%=childBeneficiaryDTO.getBirthWieght()%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.birthAndNeonatalHistory.lowBirthWegiht" /></label></span>
							<span><%=ViewConstants.ANSWER_NO%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.birthAndNeonatalHistory.babyJacketGiven" /></label></span>
							<span><%=childFirstVisitRecord.getIsBodyCoverd ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.birthAndNeonatalHistory.maternalSkinContact" /></label></span>
							<span><%//=%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.birthAndNeonatalHistory.birthDefectPresent" /></label></span>
							<span><%=childFirstVisitRecord.getIsBirthDefect ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>				
						</div>
						<br>
						<br>
						
						<h4><bean:message key="label.beneficiary.child.feedingHistory" /></h4>
						<div id="Feeding History">
							<span class="b"><label><bean:message key="label.beneficiary.child.feedingHistory.fedColostrum" /></label></span>
							<span><%=childFirstVisitRecord.getIsBrestFedWithinOneHour ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.feedingHistory.fedOther" /></label></span>
							<span><%=childFirstVisitRecord.getIsBreastFedFirst ( )==1?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.feedingHistory.exclusivelyBreastFed" /></label></span>
							<span><%=(neoNatalVisitInfo!=null && neoNatalVisitInfo.getIsOnlyBreastFed ( )==1)?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.feedingHistory.weaningStarted" /></label></span>
							<span><%=(infantVisitRecord!=null && infantVisitRecord.getIsSupplymentaryFoodAfterSixMonth ( )==1)?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>
						</div>
					<%
					}
					%>
					<br>
					<br>
					<h4><bean:message key="label.beneficiary.child.immunizationHistory" /></h4>
					<div id="Immunization History">
						<span class="b"><label><bean:message key="label.beneficiary.child.immunizationHistory.immunization" /></label></span>
						<span><%=(infantVisitRecord!=null && infantVisitRecord.getIsTtTakenAsPerAge ( )==1)?ViewConstants.ANSWER_YES:ViewConstants.ANSWER_NO%></span><br/>				
					</div>
					<br>
					<br>
					<%
					if(neoNatalVisitInfo!=null)
					{
					%>
					<h4><bean:message key="label.beneficiary.child.physicalExaminationFindings" /></h4>
					<div id="Physical Examination Findings">
						<span class="b"><label><bean:message key="label.beneficiary.child.physicalExaminationFindings.temperature" /></label></span>
						<span><%=neoNatalVisitInfo.getChildBodyTempareture ( )%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.child.physicalExaminationFindings.breathingNormal" /></label></span>
						<span><%=neoNatalVisitInfo.getIsChildBreathingNormal ( )==1?ViewConstants.ANSWER_NORMAL:ViewConstants.ANSWER_ABNORMAL%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.child.physicalExaminationFindings.conditionOfEyes" /></label></span>
						<span><%=neoNatalVisitInfo.getIsChildEyeNormal ( )==1?ViewConstants.ANSWER_NORMAL:ViewConstants.ANSWER_ABNORMAL%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.child.physicalExaminationFindings.conditionOfSkin" /></label></span>
						<span><%=neoNatalVisitInfo.getIsChildSkinNormal ( )==1?ViewConstants.ANSWER_NORMAL:ViewConstants.ANSWER_ABNORMAL%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.child.physicalExaminationFindings.activityLevelNormal" /></label></span>
						<span><%=neoNatalVisitInfo.getIsChildMovementNormal ( )==1?ViewConstants.ANSWER_NORMAL:ViewConstants.ANSWER_ABNORMAL%></span><br/>
						<span class="b"><label><bean:message key="label.beneficiary.child.physicalExaminationFindings.overallAppearance" /></label></span>
						<span><%//=%></span><br/>
					</div>
					<br>
					<br>
					<%
					}					
					
					if(referralRecordDTO != null)
					{
					%>				
						<h4><bean:message key="label.beneficiary.child.referralInformation" /></h4>
						<div id="Referral Information">
							<span class="b"><label><bean:message key="label.beneficiary.child.referralInformation.referred" /></label></span>
							<span><%=ViewConstants.ANSWER_YES%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.referralInformation.causeOfReferral" /></label></span>
							<span><%//=referralRecordDTO.getReasonOfRefferal ( )%></span><br/>
							<span class="b"><label><bean:message key="label.beneficiary.child.referralInformation.referredWhere" /></label></span>
							<span><%=referralRecordDTO.getPlaceOfReferral ( )%></span><br/>
						</div>
						<br>
						<br>
					<%
					}
					%>
				</div>
				<div id="childNonMedical">
					<h3><bean:message key="label.beneficiary.child.nonMedical" /></h3>
					<span>No Information</span>
				</div>
			</div>
			<script type="text/javascript">
				var myflowers=new ddtabcontent("childTabs") //enter ID of Tab Container			
				myflowers.setpersist(true) //toogle persistence of the tabs' state
				myflowers.setselectedClassTarget("link") //"link" or "linkparent"
				myflowers.init();
			</script>
			</html:form>
			<table width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
			<%
			
				MedicalAdviceDTO medicalAdviceDTO = (MedicalAdviceDTO)request.getSession().getAttribute("advice");
				String advice = "";
				if(medicalAdviceDTO!=null)
				{
					advice = medicalAdviceDTO.getAdvice ();
				}
				%>
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
	                        
	                         <tr>
		                         <td>
			                         <div id="content">	                         
				                         <form name="medicalAdviceForm" action="medicalAdviceAction.csmp?method=" method="POST" >
				                         	<input type="hidden" id="beneficiaryId" name="beneficiaryId" value="<%=childBeneficiaryDTO.getChildId()%>">
				                         	<input type="hidden" id="skId" name="skId" value="<%=skId%>">
				                         	<input type="hidden" id="householdId" name="householdId" value="<%=householdDTO!=null?householdDTO.getHouseNo ( ):""%>">
				                         	<input type="hidden" id="beneficiaryType" name="beneficiaryType" value="child">
				                         	<textarea rows="3" cols="110" id="phAdvice" name ="phAdvice" align="left"><%//=advice%></textarea>
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
			<!-- Child detail end -->
			
			
		</tbody> 
	</table>	

<script type="text/javascript">

//var myflowers=new ddtabcontent("flowertabs") //enter ID of Tab Container
//myflowers.setpersist(true) //toogle persistence of the tabs' state
//myflowers.setselectedClassTarget("link") //"link" or "linkparent"
//myflowers.init();

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
	     control.makeTransliteratable(['motherNameAudio']);
	     control.makeTransliteratable(['husbandNameAudio']);
	     control.makeTransliteratable(['occupationAudio']);
	     control.makeTransliteratable(['houseOwnerNameAudio']);
	     control.makeTransliteratable(['houseHoldNameAudio']);
	
	}
	google.setOnLoadCallback(OnLoad);
<%
}
%>
function saveDoctorsComment()
{
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

//myflowers.init(3000);
</script>