package com.swiftcorp.portal.dcrreport.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.dcrreport.dto.DcrReportDTO;


public class DcrReportService
{
	private static final Log logger = LogFactory.getLog ( DcrReportService.class );
	
	private IDcrReportService dcrReportService;
	
	public DcrReportDTO getDcrReport()
	{
		DcrReportDTO reportDTO = null;
		try
		{
			reportDTO = (DcrReportDTO)dcrReportService.get();
		}
		catch (SystemException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (BusinessRuleViolationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reportDTO;
	}

	public void setDcrReportService ( IDcrReportService dcrReportService )
	{
		this.dcrReportService = dcrReportService;
	}
}
