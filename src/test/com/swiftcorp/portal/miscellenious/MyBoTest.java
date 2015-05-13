package com.swiftcorp.portal.miscellenious;

import com.crystaldecisions.sdk.occa.report.application.OpenReportOptions;
import com.crystaldecisions.sdk.occa.report.application.ReportAppSession;
import com.crystaldecisions.sdk.occa.report.application.ReportClientDocument;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
 
public class MyBoTest {
 
     /**
       
@param args
      */
     public static void main(String[] args) {
    	 ReportClientDocument myDoc = new ReportClientDocument();
    	 try {
			myDoc.setReportAppServer("AGIRC-DEMO");
		} catch (ReportSDKException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 try {
			myDoc.open("/opt/apache-tomcat-7.0.5/webapps/swift-demo/report/Interactive-Parameters.rpt",OpenReportOptions._openAsReadOnly);
		} catch (ReportSDKException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
}