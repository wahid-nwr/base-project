package com.swiftcorp.portal.report.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class GenericReportHandler
{
	private static final Log logger = LogFactory.getLog ( GenericReportHandler.class );
	private DriverManagerDataSource dataSource;
	
	public DriverManagerDataSource getDataSource ( )
	{
		return dataSource;
	}
	
	public void setDataSource ( DriverManagerDataSource dataSource )
	{
		this.dataSource = dataSource;
	}
	/*
	 * public void generateReport(String fileName, HashMap<String,String>
	 * parameters, List<CustomerSummeryReportDTO> data, HttpServletResponse
	 * response)throws Exception {
	 * logger.info("generateDetailedReport() : Enter"); InputStream reportStream
	 * = null; try { reportStream =
	 * this.getClass().getClassLoader().getResourceAsStream(fileName);
	 * logger.info("generateDetailedReport() : reportStream >> "+ reportStream);
	 * ServletOutputStream servletOutputStream = response.getOutputStream();
	 * response.setContentType("application/pdf"); JRBeanCollectionDataSource
	 * datasource = new JRBeanCollectionDataSource(data);
	 * JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream,
	 * parameters,datasource); servletOutputStream.flush(); } catch (Exception
	 * e) { logger.error("generateDetailedReport() : ",e); }
	 * logger.info("generateDetailedReport() : Enter"); }
	 */

}
