package com.swiftcorp.portal.report.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;
import org.apache.struts.validator.DynaValidatorActionForm;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.report.dto.ReportInputDTO;
import com.swiftcorp.portal.report.service.IReportService;
import com.swiftcorp.portal.report.util.ReportConstants;

public class ReportDownload extends DownloadAction
{
	private static final Log theLogger = LogFactory.getLog ( ReportDownload.class );
	
	private IReportService reportService;
	
	public IReportService getReportService ( )
	{
		return reportService;
	}
	
	public void setReportService ( IReportService reportService )
	{
		this.reportService = reportService;
	}
	
	protected StreamInfo getStreamInfo ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response )
			throws SystemException, Exception
	{
		
		theLogger.debug ( "getStreamInfo():Enter " );
		
		int year = 0;
		int month = 0;
		int reportId = 0;
		DynaValidatorActionForm reportForm = (DynaValidatorActionForm) form;
		ReportInputDTO input = (ReportInputDTO) reportForm.get ( "report" );
		
		year = input.getYear ();
		month = input.getMonth ();
		reportId = input.getReportId ();
		String fileName = null;
		String downloadName = null;
		theLogger.debug ( "getStreamInfo():month " + input.getMonth () );
		
		System.out.println ( "the year and id " + year + " " + reportId );
		switch ( reportId )
		{
			case ReportConstants.REPORT_ID_MOTHER_DEATH_RPT:
			{
				fileName = reportService.generateMotherDeathReport ( year );
				downloadName = "MaternalDeathReport.xls";
				break;
			}
			case ReportConstants.REPORT_ID_CHILD_DEATH_RPT:
			{
				
				fileName = reportService.generateChildDeathReport ( year );
				downloadName = "childDeathReport.xls";
				break;
			}
			case ReportConstants.REPORT_ID_MPR_RPT:
			{
				fileName = reportService.generateMPRReport( year );
				downloadName = "MPRReport.xls";
				break;
			}
		}// end switch
		
		String contentType = null;
		response.setHeader ( "Content-disposition", "attachment; filename=" + downloadName );
		contentType = "application/vnd.ms-excel";
		File file = new File ( fileName );
		
		return new FileStreamInfo ( contentType, file );
	}
}
