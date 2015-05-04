package com.swiftcorp.portal.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.dcrreport.dto.DcrReportDTO;
import com.swiftcorp.portal.dcrreport.service.DcrReportService;

@WebService(serviceName = "dcrReportService")
public class DcrReportWebServiceEndPoint
{
	private static final Log logger = LogFactory.getLog ( DcrReportWebServiceEndPoint.class );
	
	private DcrReportService dcrReportService;
	
	public DcrReportWebServiceEndPoint ( )
	{
		
	}
	
	@WebMethod
	public DcrReportDTO getDcrReport ()
	{
		DcrReportDTO reportDTO = dcrReportService.getDcrReport ( );
		return reportDTO;
	}

	public void setDcrReportService ( DcrReportService dcrReportService )
	{
		this.dcrReportService = dcrReportService;
	}
}
