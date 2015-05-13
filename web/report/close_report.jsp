<%@page import="com.crystaldecisions.report.web.viewer.CrystalReportViewer"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
CrystalReportViewer crystalReportViewer;
Object reportSource;

/*
 * Retrieve ReportSource from HTTP Session, pass to the viewer and dispose.
 * 
 * This page is used to purely dispose the ReportSource for final report cleanup, and
 * not for viewing.
 *
 * Once the ReportClientDocument instance is closed and all ReportSource objects are disposed,
 * the backend Java Print Engine will close the report and release all resources taken up by the report.
 *
 */

reportSource = session.getAttribute("ReportSource");

if(reportSource != null) {
    session.removeAttribute("ReportSource");
    crystalReportViewer = new CrystalReportViewer();
    crystalReportViewer.setReportSource(reportSource);
    crystalReportViewer.dispose();
}

%>
Report Closed.