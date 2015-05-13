
<%@ page import="com.crystaldecisions.report.web.viewer.CrystalReportViewer"%>
<%@ page import="com.crystaldecisions.sdk.occa.report.application.OpenReportOptions"%>
<%@ page import="com.crystaldecisions.sdk.occa.report.application.ReportClientDocument"%>


<%
String reportPath;
Object reportSource;
ReportClientDocument reportClientDocument;


reportPath = request.getParameter("report_path");
reportPath = "/opt/apache-tomcat-7.0.5/webapps/swift-demo/report/CustomerReport.rpt";

/*
 * Instantiate ReportClientDocument and specify the Java Print Engine as the report processor.
 * Open a rpt file and retrieve the ReportSource and store in HTTP Session.
 * Close the ReportClientDocument, then client-side redirect to the DHTML Viewer page.
 *
 * Note: the Print Engine uses reference counting to determine if a report is still in use:
 *   - Opening a report in ReportClientDocument instance increments the reference count.
 *   - Closing a ReportClientDocument instance decrements the reference count.
 *   - Generating a ReportSource increments the reference count.
 *   - Disposing the ReportSource via the viewer (CrystalReportViewer or ReporExportControl) decrements the count.
 * 
 *     We close the ReportClientDocument instance in this page, such that the refcount stands at 1, for the
 *     ReportSource object.  Once the ReportSource object is destroyed, the print engine will release all
 *     resources for the report.
 */

reportClientDocument = new ReportClientDocument();

reportClientDocument.setReportAppServer(ReportClientDocument.inprocConnectionString);

reportClientDocument.open(reportPath, OpenReportOptions._openAsReadOnly);

reportSource = reportClientDocument.getReportSource();
session.setAttribute("ReportSource", reportSource);

reportClientDocument.close();

response.sendRedirect("viewer_frame.html");
%>