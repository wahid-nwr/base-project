package com.swiftcorp.portal.report.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.ApplicationConstants;
import com.swiftcorp.portal.report.dto.ReportDTO;

public class ReportGenerator
{
	private int month = -1;
	private int year = -1;
	protected static final Log log = LogFactory.getLog ( ReportGenerator.class );
	
	public ReportGenerator ( int m, int y )
	{
		month = m;
		year = y;
	}
	
	public String generateExcelXXXXXXReport ( List<ReportDTO> dataList, List<ReportDTO> paramList )
			throws IOException
	{
		String templateFileName = ApplicationConstants.FILE_STORAGE_TEMPLATE_DIR + "XXXTemplate.XLS";
		String destFileName = ApplicationConstants.FILE_STORAGE_REPORT_DIR + "XXXReport_" + month + "_" + year + ".xls";
		
		// Sample
		// Map<String, List<CLReportGenericDTO>> beans = new HashMap<String,
		// List<CLReportGenericDTO>>();
		// beans.put("recordItem", dataList);
		// beans.put("totalSumItem", sumDataList);
		// beans.put("param", paramList);
		
		Map<String, List<ReportDTO>> beans = new HashMap<String, List<ReportDTO>> ();
		beans.put ( "data", dataList );
		beans.put ( "param", paramList );
		XLSTransformer transformer = new XLSTransformer ();
		// transformer.transformXLS(templateFileName, beans, destFileName);
		return destFileName;
	}
	
}
