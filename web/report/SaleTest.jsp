<%@page import="java.util.Properties"%>
<%@page import="com.swiftcorp.portal.report.util.CRJavaHelper"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1" %><%@ page import="com.crystaldecisions.report.web.viewer.CrystalReportViewer,
com.crystaldecisions.sdk.occa.report.application.OpenReportOptions,
com.crystaldecisions.sdk.occa.report.application.ReportClientDocument,
com.crystaldecisions.sdk.occa.report.lib.ReportSDKExceptionBase" %><%
	// This sample code calls methods from the CRJavaHelper class, which 
	// contains examples of how to use the BusinessObjects APIs. You are free to 
	// modify and distribute the source code contained in the CRJavaHelper class. 

	Properties prop = new Properties();
			String host = "";
			String port = "";
			String databaseName = "";
			String databaseUserId = "";
			String databaseUserPass = "";
	try {
		prop.load(this.getClass().getClassLoader().getResourceAsStream("Settings.properties"));
		 host = prop.getProperty("configuration.database.host");
		 port = prop.getProperty("configuration.database.port");
		 databaseName = prop.getProperty("configuration.database.name");
		 databaseUserId = prop.getProperty("configuration.database.userid");
		 databaseUserPass = prop.getProperty("configuration.database.password");
		String reportName = "../SalesReport.rpt";
		ReportClientDocument clientDoc = (ReportClientDocument) session.getAttribute(reportName);

		if (clientDoc == null) {
			// Report can be opened from the relative location specified in the CRConfig.xml, or the report location
			// tag can be removed to open the reports as Java resources or using an absolute path
			// (absolute path not recommended for Web applications).

			clientDoc = new ReportClientDocument();
			clientDoc.setReportAppServer(ReportClientDocument.inprocConnectionString);
			
			// Open report
			//clientDoc.open(reportName, OpenReportOptions._openAsReadOnly);
			clientDoc.open(reportName, OpenReportOptions._openAsReadOnly);
			
			String connectString = "jdbc:mysql://123"+host+":"+port+"/"+databaseName;
            String driverName = "com.mysql.jdbc.Driver";
            String JNDIName = "";
            String userName = databaseUserId;               // TODO: Fill in database user
            String password = databaseUserPass;          // TODO: Fill in password
            CRJavaHelper.changeDataSource(clientDoc,"../SalesReport.rpt","transaction", userName, password, connectString, driverName, JNDIName);
			clientDoc.refreshReportDocument();
			// Store the report document in session
			session.setAttribute(reportName, clientDoc);
		}

				
		// ****** BEGIN CONNECT CRYSTALREPORTPAGEVIEWER SNIPPET ****************  
		{
			// Create the CrystalReportViewer object
			CrystalReportViewer crystalReportPageViewer = new CrystalReportViewer();

			String reportSourceSessionKey = reportName+"ReportSource";
			Object reportSource = session.getAttribute(reportSourceSessionKey);
			if (reportSource == null)
			{
				reportSource = clientDoc.getReportSource();
				session.setAttribute(reportSourceSessionKey, reportSource);
			}
			//	set the reportsource property of the viewer
			crystalReportPageViewer.setReportSource(reportSource);

			// Apply the viewer preference attributes


			// Process the report
			crystalReportPageViewer.processHttpRequest(request, response, application, null); 

		}
		// ****** END CONNECT CRYSTALREPORTPAGEVIEWER SNIPPET ****************		
	

	} catch (ReportSDKExceptionBase e) {
	    out.println(e);
	}
	
%>