<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="/crystal-tags-reportviewer.tld" prefix="crviewer" %>
<%
String reportFileName = request.getParameter("rpt");
%>
<crviewer:viewer reportSourceType="reportingComponent" viewerName="EmpReport-viewer" 
reportSourceVar="EmpReport" isOwnPage="false">
<crviewer:report reportName="/opt/apache-tomcat-7.0.5/webapps/swift-demo/report/Report2.rpt" />
</crviewer:viewer>