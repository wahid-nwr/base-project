/**
 * 
 */
package com.swiftcorp.portal.household.service;

import com.swiftcorp.portal.dto.request.AlgQuestionAnswerReqDTO;
import com.swiftcorp.portal.household.dto.HouseHoldRegResponseDTO;

/**
 * @author asraful.haque
 * 
 */
public abstract class AbstractHouseholdAlgService
{
	
	public abstract HouseHoldRegResponseDTO saveHouseholdData ( AlgQuestionAnswerReqDTO algQuestionAnswerReqDTO );
	
	public abstract void saveHouseholdUpData ( AlgQuestionAnswerReqDTO algQuestionAnswerReqDTO );
	
}
