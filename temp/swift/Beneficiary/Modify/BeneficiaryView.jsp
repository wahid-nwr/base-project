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
<%@ page import="com.swiftcorp.portal.beneficiary.dto.VisitDTO"%>

<%@ page import="com.swiftcorp.portal.group.dto.GroupDTO"%>
<%@ page import="com.swiftcorp.portal.common.util.WebUtils"%>
<%@ page import="com.swiftcorp.portal.common.util.CalendarUtils"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.util.Calendar"%>
<%
	boolean isReadOnly = false;
	
	MotherBeneficiaryDTO motherBeneficiaryDTO = (MotherBeneficiaryDTO)session.getAttribute(SESSION_KEYS.BENEFICIARY_TO_MODIFY);
	MotherBeneficiaryHistoryDTO motherBeneficiaryHistoryDTO = null;
	if(motherBeneficiaryDTO!=null)
	{
		motherBeneficiaryHistoryDTO = motherBeneficiaryDTO.getMotherBeneficiaryHistoryDTO();
	}
	Set<PregnancyRecordDTO> pregnancyRecordSet = null;
	if(motherBeneficiaryDTO!=null)
	{
		pregnancyRecordSet = (Set<PregnancyRecordDTO>) motherBeneficiaryDTO.getPregnancyRecordSet ();
		for(PregnancyRecordDTO pregnancyRecordDTO:pregnancyRecordSet)
		{
			System.out.println ("Status::::::::::::::::::"+pregnancyRecordDTO.getStatus ());			
		}
	}
	PregnancyRecordDTO currentPregnancyRecorDTO = motherBeneficiaryDTO.getCurrentPregnancyRecord();
	VisitDTO currentPregVisit = currentPregnancyRecorDTO.getVisitDTO();
	String skId = currentPregVisit.getUserDTO().getUniqueCode();
	
		
	
%>
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
<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=beneficiary.add.screenname&screenTipTextKey=beneficiary.add.tiptext"></jsp:include>
</logic:equal>
	
<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
	<jsp:include flush="true" page="/Common/ScreenHeader.jsp?screenNameKey=beneficiary.modify.screenname&screenTipTextKey=beneficiary.modify.tiptext"></jsp:include>
</logic:equal>
<html:form styleId="beneficiaryForm"  action="beneficiaryAction.cms?method=" method="POST" onsubmit="return isBeneficiaryModifyValidSubmit()" target="_self">
	<table style="width: 100%;" class="AddModifyForm">
		<tbody>
		
			<%--Shows Success messages if present--%>
			<%@ include file="/Common/Message/SuccessMessage.jsp"%>
			
			<%--Shows Error messages if present--%>
			<%@ include file="/Common/Message/ErrorMessage.jsp"%>
			
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareBeneficiaryModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareBeneficiaryModifyAction&methodParams=cancel"></jsp:include>
			</logic:equal>
			
			<tr>
				<td colspan="100">		
				<fieldset>
					<legend>
						Basic Info
					</legend>
					<table>
					<tr>
					<td>
					<table><tbody>		
					<tr>
						<td>
						  <label class="desc">Sk ID</label>
						</td>
						<td>
							<%=skId%>
							<!--
							<html:text property="beneficiary.beneficiaryName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
							-->
						</td>
					</tr>													
					<!--  name of beneficiary -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.name" /></label>
						</td>
						<td>
							<%=motherBeneficiaryDTO.getBeneficiaryName()%>
							<!--
							<html:text property="beneficiary.beneficiaryName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
							-->
						</td>
					</tr>
					<!-- code of beneficiary -->
					<tr>
						<td>							
						  <label class="desc"><bean:message key="label.beneficiary.code" /></label>
						</td>
						<td>
							<%=motherBeneficiaryDTO.getBeneficiaryId()%>
							<!--
							<html:text property="beneficiary.uniqueCode" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
							-->
						</td>
					</tr>
					<!-- father name of beneficiary -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.fatherName" /></label>
						</td>
						<td>
							<%=motherBeneficiaryDTO.getFatherName()%>
							<!--
							<html:text property="beneficiary.fatherName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
							-->
						</td>
					</tr>
					<!-- husband name of beneficiary -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.husbandName" /></label>
						</td>
						<td>
							<%=motherBeneficiaryDTO.getHusbandName()%>
							<!--
							<html:text property="beneficiary.husbandName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
							-->
						</td>
					</tr>
					<!-- head of family -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.khanaHead" /></label>
						</td>
						<td>
							<%=motherBeneficiaryDTO.getHouseholdName()%>							
						</td>
					</tr>
					<!-- household name of beneficiary -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.householdName" /></label>
						</td>
						<td>
							<%=motherBeneficiaryDTO.getHouseholdName()%>
							<!--
							<html:text property="beneficiary.householdName" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
							-->
						</td>
					</tr>					
					<!-- nationalId -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.nationalId" /></label>
						</td>
						<td>
							<%=motherBeneficiaryDTO.getNationalId()%>
							<!--
							<html:text property="beneficiary.nationalId" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
							-->
						</td>
					</tr>
					<!-- contactNo -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.contactNo" /></label>
						</td>
						<td>
							<%=motherBeneficiaryDTO.getContactNo()%>
							<!--
							<html:text property="beneficiary.contactNo" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
							-->
						</td>
					</tr>
					<!-- age -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.age" /></label>
						</td>
						<td>
							<%=motherBeneficiaryDTO.getAge()%>
							<!--
							<html:text property="beneficiary.age" styleClass="SingleLineTextField" size="30" disabled="<%=isReadOnly%>"></html:text>
							-->
						</td>
					</tr>
					<!-- tititica -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.fullTTDose" /></label>
						</td>
						<td>
							<%=motherBeneficiaryHistoryDTO.getTitiTica()==0?"No":"Yes"%>							
						</td>
					</tr>
					<!-- asma -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.isAsma" /></label>
						</td>
						<td>
							<%=motherBeneficiaryHistoryDTO.getAsma()==0?"No":"Yes"%>							
						</td>
					</tr>
					<!-- diabetecs -->
					<tr>
						<td>
						  <label class="desc"><bean:message key="label.beneficiary.isDaibetecs" /></label>
						</td>
						<td>
							<%=motherBeneficiaryHistoryDTO.getDiabetcs()==0?"No":"Yes"%>							
						</td>
					</tr>
					
					</tbody>
					</table>
					</td>
					<td width="300" align="right">
					<!--table>
					<tr>
					<%=motherBeneficiaryDTO.getPictureFile()%>
					<img src= "GetImageServlet?path=<%=motherBeneficiaryDTO.getPictureFile()%>">
					<td-->		
										
										
					<img width="100" border="2" height="120" src="<%=motherBeneficiaryDTO.getPictureFile()%>" alt=""/>
					<!--/td>
					</tr>
					</table-->
					</td>
				</table>
			</fieldset>
		</td></tr>
		<tr>
			<td colspan="100">
				<fieldset>
					<legend>
						Pregnancy Detail Info
					</legend>
					<table>
					<tr>
						<td></td>
					</tr>
					<%
					String bgcolor="#eeeeee";
					int i=0;
					int visitNumber = 0;
					VisitDTO visitDTO = null;
					List<PregnancyRecordDTO> sortedPregnancyRecordSet = new ArrayList ( pregnancyRecordSet );
					System.out.println("b4 compare");
					//Collections.sort(sortedPregnancyRecordSet, new SortByDate());
					for(PregnancyRecordDTO pregnancyRecordDTO:sortedPregnancyRecordSet)
					{						
						if(pregnancyRecordDTO!=null)
						{						
							Set<FirstTrimesterRecordDTO> firstTrimesterRecordSet = (Set<FirstTrimesterRecordDTO>)pregnancyRecordDTO.getFirstTrimesterSet ();
							Set<SecondTrimesterRecordDTO> secondTrimesterRecordSet = (Set<SecondTrimesterRecordDTO>)pregnancyRecordDTO.getSecondTrimesterSet ();
							Set<ThirdTrimesterRecordDTO> thirdTrimesterRecordSet= (Set<ThirdTrimesterRecordDTO>)pregnancyRecordDTO.getThirdTrimesterSet ();							
														
							Set<TrimesterHealthInfoDTO> trimesterHealthInfoSet = (Set<TrimesterHealthInfoDTO>)pregnancyRecordDTO.getTrimesterHealthInfoSet ();
							Set<ReferralRecordDTO> referralSectionSet= (Set<ReferralRecordDTO>)pregnancyRecordDTO.getReferralSectionSet ();
							Set<PNCSectionDTO> pncSectionSet= (Set<PNCSectionDTO>)pregnancyRecordDTO.getPncSectionSet ();
							
							MiscarriageSectionDTO miscarriageSectionDTO = (MiscarriageSectionDTO)pregnancyRecordDTO.getMiscarriageSectionDTO();
							DeliveryRecordDTO deliveryRecordDTO = (DeliveryRecordDTO)pregnancyRecordDTO.getDeliveryRecordDTO();
							
							for(TrimesterHealthInfoDTO trimesterHealthInfoDTO:trimesterHealthInfoSet)
							{
							}
							for(ReferralRecordDTO referralRecordDTO:referralSectionSet)
							{
							}
							
							List<FirstTrimesterRecordDTO> sorted = new ArrayList ( firstTrimesterRecordSet );
							//Collections.sort(sorted, new SortByDate());						
							
							%>	
							<tr>
								<td>
									<fieldset id="1" visibility="hidden">
										<legend>
											Pregnancy Record <%=i+1%>
										
										</legend>
										<table>
											<tr>
												<td colspan="2">LMP : <%=CalendarUtils.calendarToString(pregnancyRecordDTO.getLMP())%></td>
											</tr>
											<tr>
												<td colspan="2">EDD : <%=CalendarUtils.calendarToString(pregnancyRecordDTO.getEDD())%></td>
											</tr>
											<tr>
												<td colspan="2">Birth Control Kit : <%=pregnancyRecordDTO.getBirthControlKit ()%></td>
											</tr>
											<tr>
												<td colspan="2">Current Stage :<%=pregnancyRecordDTO.getCurrentStage ()%></td>
											</tr>
											<tr>
												<td colspan="2">Strip Result : <%=pregnancyRecordDTO.getStripResult ()%></td>											
											</tr>							
											<tr>
												<td colspan="2">
													<fieldset>
														<legend>Pre Delivery Record</legend>
														<table>
															<tr bgcolor="#bbbbbb">
																<td>Visit Order</td>
																<td>Visit Date</td>
																<td>Visit By</td>
																<td>Blur Vision</td>
																<td>Unusual Fatigue</td>
																<td>Severe Headache</td>
																<td>Varginal Infection</td>
																<td>Increased Thirsty</td>
																<td>Urinal Bleeding</td>
																<td>Extra Urinate</td>
																<td>Jaundice</td>
																<td>Convulsion</td>
																<td>Anemia</td>
																<td>High Fever</td>
																<td>Azma</td>
																<td>BP</td>
																<td>Uterus Height</td>
																<!--td>No. of Iron Tablet</td>	
																<td>Date of 1st TT</td>
																<td>Child Movement</td>
																<td>Saved Money</td>
																<td>Driver Name</td>
																<td>Delivery Place</td>
																<td>Delivery Assistant</td>
																<td>Delivery Kit</td>
																<td>Patient relationship</td>
																<td>Date of 2nd TT</td>
																<td>Check up</td>
																<td>Child Placement</td>
																<td>Child Heart Bit</td-->
															</tr>
							<%
							if(sorted!=null && sorted.size()>0)
							{
								%>
															<tr>
																<td colspan="17"><b>FirstTrimester Record</b></td>
															</tr>
								<%
							}
							for(FirstTrimesterRecordDTO firstTrimesterRecordDTO:sorted)
							{
								visitNumber++;
								if(visitNumber % 2 == 0)
								{
									bgcolor = "#cccccc";
								}
								else
								{
									bgcolor = "#eeeeee";
								}
								TrimesterHealthInfoDTO trimesterHealthInfoDTO = firstTrimesterRecordDTO.getTrimesterHealthInfoDTO ();								
								trimesterHealthInfoDTO.getOtherComplication ();
								trimesterHealthInfoDTO.getOtherProblem ();
								visitDTO = null;
								if(firstTrimesterRecordDTO.getVisitDTO()!=null)
								{	
									visitDTO = firstTrimesterRecordDTO.getVisitDTO();
								}
								%>
															<tr bgcolor="<%=bgcolor%>">
																<td>Visit No: <%=visitNumber%></td>
																<td><%=visitDTO!=null?CalendarUtils.calendarToString(visitDTO.getVisitDate()):""%></td>
																<td><%=visitDTO!=null?visitDTO.getUserDTO().getFirstName():""%></td>																
																<td><%=(trimesterHealthInfoDTO.getBluredVision ())==0?"No":"Yes"%></td>
																<td><%=(trimesterHealthInfoDTO.getUnusalFatigue ())==0?"No":"Yes"%></td>
																<td><%=(trimesterHealthInfoDTO.getSevereHeadache ())==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getVarginalInfection ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getFeelIncreasedThirsty ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getUrinalBleeding ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getExtraUrinate ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getIsJaundice ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getIsConvulsion ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getSevereAnemia ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getHighFever ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getEdema ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getBloodPressure ()%></td>
																<td><%=trimesterHealthInfoDTO.getUterusHeight ()%></td>												
															</tr>
								<%
							}		
							
							List<SecondTrimesterRecordDTO> sortedSecondTrimesterRecordSet = new ArrayList ( secondTrimesterRecordSet );
							//Collections.sort(sortedSecondTrimesterRecordSet, new SortByDate());
							if(sortedSecondTrimesterRecordSet!=null && sortedSecondTrimesterRecordSet.size()>0)
							{
								%>
															<tr>
																<td colspan="17"><b>SecondTrimester Record</b></td>
															</tr>
								<%
							}			
							for(SecondTrimesterRecordDTO secondTrimesterRecordDTO:sortedSecondTrimesterRecordSet)
							{
								visitNumber++;
								if(visitNumber % 2 == 0)
								{
									bgcolor = "#cccccc";
								}
								else
								{
									bgcolor = "#eeeeee";
								}
								TrimesterHealthInfoDTO trimesterHealthInfoDTO = secondTrimesterRecordDTO.getTrimesterHealthInfoDTO ();								
								trimesterHealthInfoDTO.getOtherComplication ();
								trimesterHealthInfoDTO.getOtherProblem ();
								visitDTO = null;
								if(secondTrimesterRecordDTO.getVisitDTO()!=null)
								{	
									visitDTO = secondTrimesterRecordDTO.getVisitDTO();
								}
								%>
															<tr bgcolor="<%=bgcolor%>">
																<td>Visit No: <%=visitNumber%></td>
																<td><%=visitDTO!=null?CalendarUtils.calendarToString(visitDTO.getVisitDate()):""%></td>
																<td><%=visitDTO!=null?visitDTO.getUserDTO().getFirstName():""%></td>																
																<td><%=trimesterHealthInfoDTO.getBluredVision ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getUnusalFatigue ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getSevereHeadache ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getVarginalInfection ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getFeelIncreasedThirsty ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getUrinalBleeding ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getExtraUrinate ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getIsJaundice ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getIsConvulsion ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getSevereAnemia ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getHighFever ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getEdema ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getBloodPressure ()%></td>
																<td><%=trimesterHealthInfoDTO.getUterusHeight ()%></td>
															</tr>
															<tr>
																<td colspan="17">
																	No. of Iron Tablet : <%=secondTrimesterRecordDTO.getNoOfIronTablet ()%> <br/>
																	Date of 1st TT : <%=CalendarUtils.calendarToString(secondTrimesterRecordDTO.getDateOfFirstTitiDos ())%> <br/>
																	Date of 2nd TT : <%=CalendarUtils.calendarToString(secondTrimesterRecordDTO.getDateOfSecondTitiDos ())%> <br/>
																	Child Movement : <%=secondTrimesterRecordDTO.getMovementOfChild ()%> 
																</td>
															</tr>
								<%
							}
							
							List<ThirdTrimesterRecordDTO> sortedThirdTrimesterRecordSet = new ArrayList ( thirdTrimesterRecordSet );
							//Collections.sort(sortedThirdTrimesterRecordSet, new SortByDate());
							if(sortedThirdTrimesterRecordSet!=null && sortedThirdTrimesterRecordSet.size()>0)
							{
								%>
															<tr>
																<td colspan="17"><b>ThirdTrimester Record</b></td>
															</tr>
								<%
							}
							for(ThirdTrimesterRecordDTO thirdTrimesterRecordDTO:sortedThirdTrimesterRecordSet)
							{
								visitNumber++;
								if(visitNumber % 2 == 0)
								{
									bgcolor = "#cccccc";
								}
								else
								{
									bgcolor = "#eeeeee";
								}
								visitDTO = null;
								if(thirdTrimesterRecordDTO.getVisitDTO()!=null)
								{	
									visitDTO = thirdTrimesterRecordDTO.getVisitDTO();
								}
								TrimesterHealthInfoDTO trimesterHealthInfoDTO = thirdTrimesterRecordDTO.getTrimesterHealthInfoDTO ();								
								trimesterHealthInfoDTO.getOtherComplication ();
								trimesterHealthInfoDTO.getOtherProblem ();
								%>
															<tr bgcolor="<%=bgcolor%>">
																<td>Visit No: <%=visitNumber%></td>
																<td><%=visitDTO!=null?CalendarUtils.calendarToString(visitDTO.getVisitDate()):""%></td>
																<td><%=visitDTO!=null?visitDTO.getUserDTO().getFirstName():""%></td>																
																<td><%=trimesterHealthInfoDTO.getBluredVision ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getUnusalFatigue ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getSevereHeadache ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getVarginalInfection ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getFeelIncreasedThirsty ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getUrinalBleeding ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getExtraUrinate ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getIsJaundice ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getIsConvulsion ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getSevereAnemia ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getHighFever ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getEdema ()==0?"No":"Yes"%></td>
																<td><%=trimesterHealthInfoDTO.getBloodPressure ()%></td>
																<td><%=trimesterHealthInfoDTO.getUterusHeight ()%></td>
															</tr>
															<tr>
																<td colspan="17">
																	<table width="500">
																		<tr>
																			<td>Child Movement :</td>
																			<td> <%=thirdTrimesterRecordDTO.getMovementOfChild ()==0?"Abnormal":"Normal"%></td>
																			<td>Saved Money :</td>
																			<td><%=thirdTrimesterRecordDTO.getSavedMoney ()%></td>
																		</tr>
																		<tr>
																			<td>Driver Name :</td>
																			<td><%=thirdTrimesterRecordDTO.getNameOfDriver ()%></td>
																			<td>Delivery Place :</td>
																			<td><%=thirdTrimesterRecordDTO.getPlaceOfDelivery ()%></td>
																		</tr>
																		<tr>
																			<td>Delivery Assistant :</td>
																			<td><%=thirdTrimesterRecordDTO.getDeliveryAssistant ()%></td>
																			<td>Delivery Kit :</td>
																			<td><%=thirdTrimesterRecordDTO.getDeliveryKit ()%></td>
																		</tr>
																		
																		<tr>
																			<td>Check up :</td>
																			<td><%=thirdTrimesterRecordDTO.getIsFullCheckUp ()==0?"No":"Yes"%></td>
																			<td>Child Placement :</td>
																			<td><%=thirdTrimesterRecordDTO.getIsChildPlacementNatural ()==0?"Abnormal":"Normal"%></td>
																		</tr>
																		<tr>
																			<td>Child Heart Bit :</td>
																			<td><%=thirdTrimesterRecordDTO.getHeartbeatOfChild ()%></td>
																			<td>&nbsp;</td>
																			<td>&nbsp;</td>																			
																		</tr>																	   
																	 </table>
																</td>
															</tr>
								<%
							}
							%>
														</table>
													</fieldset>
												</td>
											</tr>	
												
											<tr>
												<td colspan="2">
													<fieldset>
														<legend>Post Delivery Record</legend>
														<table>
														<%
														if(deliveryRecordDTO!=null)
														{
														%>	
															<tr>
																<td colspan="17">
																	<fieldset>
													
																		<legend>Delivery Details</legend>
																		<table>
																			<tr bgcolor="#bbbbbb">
																				<td>Delivery Date</td>
																				<td>Delivery Kit</td>
																				<td>Delivery Assistant</td>
																				<td>Delivery Desc</td>
																				<td>Complicated</td>
																				<td>MeltDeathChild</td>
																				<td>Misoprostal</td>
																				<td>No. Of Child alive</td>
																				<td>No. Of Dead alive</td>
																				<td>Place</td>
																				<td>Delivery Type</td>
																				<td>Type of Delivery Complication</td>
																			</tr>
																			<tr>
																				<td><%=CalendarUtils.calendarToString(deliveryRecordDTO.getDateOfDelivery ())%></td>
																				<td><%=deliveryRecordDTO.getDeliveryAssistant ()%></td>
																				<td><%=deliveryRecordDTO.getDeliveryKit ()%></td>						
																				<td><%=deliveryRecordDTO.getDescription ()%></td>
																				<td><%=deliveryRecordDTO.getIsDeliveryComplication ()%></td>
																				<td><%=deliveryRecordDTO.getMeltDeathChild ()%></td>
																				<td><%=deliveryRecordDTO.getMisoprostal ()%></td>
																				<td><%=deliveryRecordDTO.getNoOfAliveChild ()%></td>
																				<td><%=deliveryRecordDTO.getNoOfDeathChild ()%></td>
																				<td><%=deliveryRecordDTO.getPlaceOfDelivery ()%></td>
																				<td><%=deliveryRecordDTO.getTypeOfDelivery ()%></td>
																				<td><%=deliveryRecordDTO.getTypeOfDeliveryComplication ()%></td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>
															<%
															}
															if(miscarriageSectionDTO!=null)
															{
															%>																
															<tr>
																<td colspan="17">
																	<fieldset>
													
																		<legend>Miscarriage Information</legend>
																		<table>
																			<tr bgcolor="#bbbbbb">
																				<td>Death Date</td>
																				<td>Description</td>
																				<td>Helper of abortion</td>
																				<td>Mother alive</td>
																				<td>Services after Abortion</td>
																				<td>Abortion procedure</td>
																			</tr>																		
																			<tr>
																				<td><%=(miscarriageSectionDTO.getDateOfDeath ()!=null)?CalendarUtils.calendarToString(miscarriageSectionDTO.getDateOfDeath ()):""%></td>
																				<td><%=miscarriageSectionDTO.getDescription ()%></td>
																				<td><%=(miscarriageSectionDTO.getHelperOfAbortion ()!=null)?miscarriageSectionDTO.getHelperOfAbortion ():""%></td>
																				<td><%=(miscarriageSectionDTO.getIsMotherAlive ()>0)?"Yes":"No"%></td>
																				<td><%=(miscarriageSectionDTO.getSeviceAfterAbortion ()>0)?"Yes":"No"%></td>
																				<td><%=(miscarriageSectionDTO.getWayOfAbortion ()!= null && miscarriageSectionDTO.getWayOfAbortion ().equals("1"))?"Intentional":"Unintentional"%></td>
																			</tr>
																		</table>
																	</fieldset>
																</td>
															</tr>	
															<%
															}
															if(pncSectionSet!=null && pncSectionSet.size()>0)
															{
															%>
															<tr>
																<td colspan="17">
																	<fieldset>
													
																		<legend>Post natal care Information</legend>
																		<table>
																			<tr bgcolor="#bbbbbb">
																				<td>Abdomain Pain</td>
																				<td>Blood pressure</td>
																				<td>Temperature</td>
																				<td>Convulsion</td>
																				<td>Desc</td>
																				<td>Excessive Bleeding</td>
																				<td>Iron Tablet</td>
																				<td>Fever</td>
																				<td>Odorous Discharge</td>
																				<td>Other Symptom</td>
																				<td>Problem on Breast</td>
																				<td>Severe Headache</td>
																				<td>Torn Perineum</td>
																				<td>Vitamin A</td>
																			</tr>
																		<%
																		for(PNCSectionDTO pncSectionDTO:pncSectionSet)
																		{
																		%>
																			<tr>
																				<td><%=pncSectionDTO.getAbdomenPain ()==0?"No":"Yes"%></td>
																				<td><%=pncSectionDTO.getBloodPressure ()%></td>
																				<td><%=pncSectionDTO.getBodyTemperature ()%></td>
																				<td><%=pncSectionDTO.getIsConvulsion ()==0?"No":"Yes"%></td>
																				<td><%=pncSectionDTO.getDescription ()%></td>
																				<td><%=pncSectionDTO.getExcessiveBleeding ()==0?"No":"Yes"%></td>																
																				<td><%=pncSectionDTO.getIronTablet ()%></td>
																				<td><%=pncSectionDTO.getIsfever ()==0?"No":"Yes"%></td>
																				<td><%=pncSectionDTO.getOdorousDischarge ()==0?"No":"Yes"%></td>
																				<td><%=pncSectionDTO.getOtherSymptom ()%></td>
																				<td><%=pncSectionDTO.getProblemOnBreast ()==0?"No":"Yes"%></td>
																				<td><%=pncSectionDTO.getSevereHeadache ()==0?"No":"Yes"%></td>
																				<td><%=pncSectionDTO.getTornPerineum ()==0?"No":"Yes"%></td>
																				<td><%=pncSectionDTO.getVitaminACapsul ()%></td>
																			</tr>
																		<%								
																		}
																		%>
																		</table>
																	</fieldset>
																</td>
															</tr>
															<%
															}
															%>								
														</table>
													</fieldset>
												</td>
											</tr>
										</table>																
								</td>
							</tr>		
							<%
						}
						i++;
					}
					
					%>
						
					</table>
				</fieldset>
			</td>
			<td>
			</td>
		</tr>
		
			<logic:equal value="<%= GlobalConstants.ADD_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
				<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=ok,cancel&methodName=prepareBeneficiaryModifyAction&methodParams=add,cancel"></jsp:include>
			</logic:equal>
			
			<logic:equal value="<%= GlobalConstants.MODIFY_OPERATION %>" name="<%= SESSION_KEYS.OPERATION_TYPE %>">
					<jsp:include flush="true" page="/Common/ButtonBar.jsp?buttons=cancel&methodName=prepareBeneficiaryModifyAction&methodParams=cancel"></jsp:include>
			</logic:equal>
			
		</tbody> 
	</table>	
</html:form>

