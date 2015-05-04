<%@ include file="/Common/Include/taglib.jsp"%>
<%
	String path="/Beneficiary/Modify/MotherBeneficiaryVisit.jsp";
%>
<script type="text/javascript" src="./resources/tabcontent.js">
<tiles:insert page="/Common/PageLayout/CommonLayout.jsp" flush="true">
	<tiles:put name="title"   value="Demo Platform" type="string"/>
	<tiles:put name="header"  value="/Common/PageLayout/Header.jsp" />
	<tiles:put name="footer"  value="/Common/PageLayout/Footer.jsp" />
	<tiles:put name="leftbar" value="/Common/PageLayout/LeftBar.jsp" />
	<tiles:put name="body"    value="<%=path%>" />
</tiles:insert>
