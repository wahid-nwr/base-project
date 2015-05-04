/**
 * 
 */
package com.swiftcorp.portal.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.swiftcorp.portal.dto.request.AlgQuestionAnswerReqDTO;

/**
 * @author asraful.haque
 * 
 */

@WebService(serviceName = "BeneficiaryRegService")
public class BeneficiaryWebServiceEndPoint extends SpringBeanAutowiringSupport
{
	
	public BeneficiaryWebServiceEndPoint ( )
	{
		
	}
	
	@WebMethod
	public String registerBeneficiary ( AlgQuestionAnswerReqDTO algQuestionAnswerReqDTO  )
	{
		
		return null;
	}
	
	
}
