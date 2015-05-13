/*
 * @ (#) ReportServiceImpl.java
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information").You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */

package com.swiftcorp.portal.report.service;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.util.CalendarUtils;
import com.swiftcorp.portal.report.dao.IReportDAO;
import com.swiftcorp.portal.report.dto.BeneficiaryDeathRecord;
import com.swiftcorp.portal.report.util.ReportUtils;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class ReportServiceImpl implements IReportService
{
	private static final Log log = LogFactory.getLog ( ReportServiceImpl.class );
	public final String MPR_REPORT_TEMPLATE_NAME = "mprTemplate.xls";
	
	// template dir
	private String reportTemplateDir;
	private String reportDir;
	
	private IReportDAO mpReportDAO;
	
	
	public IReportDAO getMpReportDAO() {
		return mpReportDAO;
	}

	public void setMpReportDAO(IReportDAO mpReportDAO) {
		this.mpReportDAO = mpReportDAO;
	}

	// death report geneartor
	private DeathReportGenerator deathReportGenerator;
	
	public String generateMotherDeathReport ( int year ) throws Exception
	{
		String reportFullFilePath = this.deathReportGenerator.generateMotherDeathReport ( this.reportTemplateDir, this.reportDir, year );
		
		// return the pathe
		return reportFullFilePath;
	}
	
	public String generateChildDeathReport ( int year ) throws Exception
	{
		String reportFullFilePath = this.deathReportGenerator.generateMotherDeathReport ( this.reportTemplateDir, this.reportDir, year );
		
		// return the pathe
		return reportFullFilePath;
	}
	
	public String generateMPRReport ( int year ) throws Exception
	{
		// get the mother record
		//MotherDeathRecord motherDeathRecord = this.getMotherDeathRecord ( year );
		Calendar calendarLastDate = CalendarUtils.getLastdayOfMonth(12, year);
		Calendar calendarFirstDate = CalendarUtils.getFirstdayOfMonth(1, year);
		Date fromDate= calendarFirstDate.getTime();
		Date toDate= calendarFirstDate.getTime();
		
		List<BeneficiaryDeathRecord> beneficiaryDeathRecords = mpReportDAO.getBeneficiaryDeathDTOList(fromDate, toDate);
		/*Map beans = new HashMap ();
		beans.put ( "motherDeathRecord", motherDeathRecord );
		*/
		Map<String, List<BeneficiaryDeathRecord>> beans = new HashMap<String,List<BeneficiaryDeathRecord>>();
        beans.put("mReport", beneficiaryDeathRecords);
		
		
		
		String templateFilePath = this.reportTemplateDir + File.separatorChar + MPR_REPORT_TEMPLATE_NAME;
		String reportFullFilePath = ReportUtils.getDeathReportFileName ( reportDir, "mprReport", year );
		
		// now generate report
		ReportUtils.generateReport ( templateFilePath, beans, reportFullFilePath );
		
		// return the path
		return reportFullFilePath;
	}
	
	public DeathReportGenerator getDeathReportGenerator ( )
	{
		return deathReportGenerator;
	}
	
	public void setDeathReportGenerator ( DeathReportGenerator deathReportGenerator )
	{
		this.deathReportGenerator = deathReportGenerator;
	}
	
	public String getReportTemplateDir ( )
	{
		return reportTemplateDir;
	}
	
	public void setReportTemplateDir ( String reportTemplateDir )
	{
		this.reportTemplateDir = reportTemplateDir;
	}
	
	public String getReportDir ( )
	{
		return reportDir;
	}
	
	public void setReportDir ( String reportDir )
	{
		this.reportDir = reportDir;
	}
	
}
