
<%@page import="com.crystaldecisions.reports.sdk.ReportClientDocument"%>
<%@page import="com.crystaldecisions.sdk.occa.report.reportsource.IReportSource"%>

<%@page import="com.swiftcorp.portal.report.util.JRCHelperSample"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="/crystal-tags-reportviewer.tld" prefix="crviewer" %>
<%

String reportFileName = request.getParameter("rpt");
ReportClientDocument reportClientDoc = new ReportClientDocument();
String report = reportFileName;
reportClientDoc.open(report, 0);
IReportSource  reportSource = reportClientDoc.getReportSource();
session.setAttribute("reportSource", reportSource);
String connectString = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=I:\\Database\\temp.mdb";
                    String driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
                    String JNDIName = "";
                    String userName = "dbuser";               // TODO: Fill in database user
                    String password = "dbpassword";          // TODO: Fill in password

                    // Switch all tables on the main report and sub reports
                    JRCHelperSample.changeDataSource(reportClientDoc, userName, password, connectString, driverName, JNDIName);
%>
 

<crviewer:viewer reportSourceType="reportingComponent" viewerName="CrystalReport1-viewer" 
reportSourceVar="CrystalReport1" isOwnPage="true">
<crviewer:report reportName="../CustomerReport.rpt" >
</crviewer:report>
</crviewer:viewer>