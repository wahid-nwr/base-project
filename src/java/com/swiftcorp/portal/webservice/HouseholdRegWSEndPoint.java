/**
 * 
 */
package com.swiftcorp.portal.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.swiftcorp.portal.dto.request.AlgQuestionAnswerReqDTO;
import com.swiftcorp.portal.household.dto.HouseHoldRegResponseDTO;
import com.swiftcorp.portal.household.service.HouseholdRegService;

/**
 * @author asraful.haque
 * 
 */

@WebService(serviceName = "householdRegService")
public class HouseholdRegWSEndPoint extends SpringBeanAutowiringSupport
{
	@Autowired
	private HouseholdRegService householdRegService;
	
	public HouseholdRegWSEndPoint ( )
	{
		
	}
	
	@WebMethod
	public HouseHoldRegResponseDTO registerHousehold ( AlgQuestionAnswerReqDTO algQuestionAnswerReqDTO )
	{
		System.out.println ( "Algorithm name is: " + algQuestionAnswerReqDTO.getAlgorithmName () );
		
		HouseHoldRegResponseDTO holdRegResponseDTO = householdRegService.saveHouseholdData ( algQuestionAnswerReqDTO );
		
		// return the reponse
		return holdRegResponseDTO;
	}
	
	public void setHouseholdRegService ( HouseholdRegService householdRegService )
	{
		this.householdRegService = householdRegService;
	}
	
}
