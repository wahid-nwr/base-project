================================================================================

SAP Business Objects Support

SAP Community Network: https://www.sdn.sap.com

================================================================================


DESCRIPTION

This Java Server Pages (JSP) sample demonstrates how to view a Crystal Report
using the Crystal Reports Java SDK.

================================================================================

FILES

readme.txt
    - this file
index.html 
    - start page - collects information necessary to run the sample
viewer_frame.html
    - main frame embedding Crystal Report Viewer and hyperlink to close report
java_crj12_web_view.jsp
    - opens a Crystal Report using Crystal Reports Java SDK
CrystalReportviewer.jsp
    - displays a Crystal Report using the DHTML Viewer
close_report.jsp
    - page for final cleanup of report resources
more_info.html 
    - links to our website as well as disclaimer
style.css
    - Stylesheet for html pages in sample. 
Interactive Parameters.rpt
    - Sample rpt file featuring interactive parameters

================================================================================

INSTALLATION

1. Setup an application context on your application server to host a
   web application. Refer to the Crystal Reports for Eclipse Developer Guide
   in Eclipse IDE Help.  


2. Ensure the CRConfig.xml file deployed with the web app has reportlocation tag
   set to ../..

3. The rpt files included with this sample reports off the Derby Xtreme 
   database. Ensure the sample derby.jar and Xtreme.jar are deployed with the
   web app.

4. Copy the contents of the Java_CRJ12_Web_View into your web application's 
   context root.

5. Launch the index.html file in a browser window.

================================================================================

NOTE: 

    Developer Guide for the Crystal Reports Java SDK is installed with the 
    Crystal Reports for Eclipse plugin in the Eclipse IDE:

        Help -> Help Contents -> Crystal Reports for Eclipse Developer Guide

    JavaDocs for crystal Reports Java SDK is found at:

        Help -> Help Contents -> Crystal Reports for Eclipse SDK Reference


================================================================================

Last updated January 2009

================================================================================
