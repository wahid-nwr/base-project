/**
 * 
 */
package com.swiftcorp.portal.beneficiary.service;

import com.swiftcorp.portal.dto.request.AlgQuestionAnswerReqDTO;

/**
 * @author asraful.haque
 * 
 */
public abstract class AbstractBeneficiaryAlgService
{
	
	public abstract void saveBenefiaryData ( AlgQuestionAnswerReqDTO algQuestionAnswerReqDTO );
	
}
