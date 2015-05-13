package com.swiftcorp.portal.common;

import com.swiftcorp.portal.report.service.ReportServiceImpl;

public class StaticReportService
{
	private ReportServiceImpl reportService;
	
	public ReportServiceImpl getReportService ( )
	{
		return reportService;
	}
	
	public void setReportService ( ReportServiceImpl reportService )
	{
		this.reportService = reportService;
	}
	
}
