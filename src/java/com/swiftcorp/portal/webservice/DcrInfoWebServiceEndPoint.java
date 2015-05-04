package com.swiftcorp.portal.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.dcrinfo.dto.DcrinfoDTO;
import com.swiftcorp.portal.dcrinfo.service.DcrInfoAddService;

@WebService(serviceName = "dcrInfoService")
public class DcrInfoWebServiceEndPoint
{
	private static final Log logger = LogFactory.getLog ( DcrInfoWebServiceEndPoint.class );
	
	// alert service to get the alert
	private DcrInfoAddService dcrInfoAddService;
	
	public DcrInfoWebServiceEndPoint ( )
	{
		
	}
	
	@WebMethod
	public void addDcrInfo ( DcrinfoDTO dcrinfoDTO )
	{
		dcrInfoAddService.addDcrInfo ( dcrinfoDTO );
	}

	public void setDcrInfoAddService ( DcrInfoAddService dcrInfoAddService )
	{
		this.dcrInfoAddService = dcrInfoAddService;
	}
}
