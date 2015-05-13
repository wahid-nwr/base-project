<%@ include file="/Common/Include/taglib.jsp"%>
<%
	String path="/Home/Modify/HomeEntry.jsp";
%>
<tiles:insert page="/Common/PageLayout/CommonLayout.jsp" flush="true">
	<tiles:put name="title"   value="MyCompany" type="string"/>
	<tiles:put name="header"  value="/Common/PageLayout/Header.jsp" />
	<tiles:put name="footer"  value="/Common/PageLayout/Footer.jsp" />
	<tiles:put name="leftbar" value="/Common/PageLayout/LeftBar.jsp" />
	<tiles:put name="body"    value="<%=path%>" />
</tiles:insert>
