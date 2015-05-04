package com.swiftcorp.portal.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.ContextLoaderListener;

public class CustomContextLoaderListener extends ContextLoaderListener implements ServletContextListener
{
	
	private static final Log log = LogFactory.getLog ( CustomContextLoaderListener.class );
	
	// private ApplicationContext applicationContext;
	
	public void contextInitialized ( ServletContextEvent event )
	{
		System.out.println ( "Framework-->>StartupListener::" );
		log.debug ( "initializing context..." );
		
		super.contextInitialized ( event );
		ServletContext ctx = event.getServletContext ();
		
		/*
		 * FileStoragePath fileStoragePath =
		 * (FileStoragePath)((ApplicationContext)ctx).getBean
		 * ("FileStoragePath"); ReportServiceImpl reportService =
		 * (ReportServiceImpl)((ApplicationContext)ctx).getBean
		 * ("ReportService");
		 * 
		 * ApplicationConstants.REPORT_SERVICE = reportService ;
		 * ApplicationConstants.FILE_STORAGE_DIR =
		 * fileStoragePath.getFileStorageDirectory();
		 * 
		 * ApplicationConstants.FILE_STORAGE_UPLOAD_DIR =
		 * ApplicationConstants.FILE_STORAGE_DIR + "upload/";
		 * ApplicationConstants.FILE_STORAGE_TEMPLATE_DIR=
		 * ApplicationConstants.FILE_STORAGE_DIR + "templates/";
		 * ApplicationConstants.FILE_STORAGE_REPORT_DIR =
		 * ApplicationConstants.FILE_STORAGE_DIR + "reports/";
		 */
		boolean registered = true;// licensingService.isRegistered();
		if ( registered )
		{
			// ctx.setAttribute ( ApplicationConstants.REGISTERED, "registered"
			// );
			
			log.info ( "******************************************************************************************" );
			log.info ( "                   SERVER REGISTRATION SUCCESSFUL" );
			log.info ( "******************************************************************************************" );
			
			System.out.println ( "******************************************************************************************" );
			System.out.println ( "                   SERVER STARTED SUCCESSFULLY" );
			System.out.println ( "******************************************************************************************" );
		}
		else
		{
			log.info ( "******************************************************************************************" );
			log.info ( "                   SERVER NOT REGISTERED....PLEASE PROVIDE VALID LICENSE" );
			log.info ( "******************************************************************************************" );
			
			System.out.println ( "******************************************************************************************" );
			System.out.println ( "                   SERVER NOT REGISTERED....PLEASE PROVIDE VALID LICENSE" );
			System.out.println ( "******************************************************************************************" );
		}
		
	}
	
}
