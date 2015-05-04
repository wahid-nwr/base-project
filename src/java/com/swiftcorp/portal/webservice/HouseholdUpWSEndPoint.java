/**
 * 
 */
package com.swiftcorp.portal.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.swiftcorp.portal.dto.request.AlgQuestionAnswerReqDTO;
import com.swiftcorp.portal.household.dto.HouseholdUpdateResponseDTO;
import com.swiftcorp.portal.household.service.HouseholdUpdateService;

/**
 * @author asraful.haque
 * 
 */

@WebService(serviceName = "householdUpdateService")
public class HouseholdUpWSEndPoint extends SpringBeanAutowiringSupport
{
	Logger logger = Logger.getLogger ( this.getClass () );
	@Autowired
	private HouseholdUpdateService householdUpdateService;
	
	public HouseholdUpWSEndPoint ( )
	{
		
	}
	
	@WebMethod
	public HouseholdUpdateResponseDTO updateHousehold ( AlgQuestionAnswerReqDTO algQuestionAnswerReqDTO )
	{
		System.out.println ( "In update Household  endpoint" );
		HouseholdUpdateResponseDTO householdUpdateResponseDTO = new HouseholdUpdateResponseDTO ();
		
		try
		{
			if ( algQuestionAnswerReqDTO != null )
			{
				householdUpdateService.saveHouseholdUpData ( algQuestionAnswerReqDTO );
			}
		}
		
		catch (Exception ex)
		{
			// System.out.println (
			// "Error occured while saving Household update data", ex);
			ex.printStackTrace ();
		}
		
		// return null for now
		return householdUpdateResponseDTO;
	}
	
	public void setHouseholdUpdateService ( HouseholdUpdateService householdUpdateService )
	{
		this.householdUpdateService = householdUpdateService;
	}
	
}
