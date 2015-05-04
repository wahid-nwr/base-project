/**
 * 
 */
package com.swiftcorp.portal.webservice;

import java.util.ArrayList;
import java.util.Calendar;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.alert.dto.AlertDTO;
import com.swiftcorp.portal.alert.service.AlertServiceImpl;
import com.swiftcorp.portal.common.exception.SystemException;

/**
 * @author asraful.haque
 * 
 */
@WebService(serviceName = "alertService")
public class AlertWebServiceEndPoint
{
	private static final Log logger = LogFactory.getLog ( AlertWebServiceEndPoint.class );
	
	// alert service to get the alert
	private AlertServiceImpl alertService;
	
	@WebMethod
	public ArrayList<AlertDTO> getAlertList ( String userId, Calendar date )
	{
		// alert list
		ArrayList<AlertDTO> alertList = null;
		
		try
		{
			alertList = alertService.generateAlertBySkId ( userId, date );
		}
		catch (SystemException e)
		{
			logger.error ( "Error occured while getting the list" );
		}
		
		// return the list
		return alertList;
	}
	
	public AlertServiceImpl getAlertService ( )
	{
		return alertService;
	}
	
	public void setAlertService ( AlertServiceImpl alertService )
	{
		this.alertService = alertService;
	}
	
}
