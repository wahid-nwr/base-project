/**
 * 
 */
package com.swiftcorp.portal.question.validation;

import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

/**
 * @author asraful.haque
 * 
 */
public class AnswerValidation extends PersistentCapableDTO
{
	
	private static final long serialVersionUID = 1L;
	
	// field validation name
	private String validationName;
	private String secondFieldValidationName;
	
	private int validationType;
	private int secondFieldvalidationType;
	
	// ans type
	private String answerType;
	private String secondFieldAnswerType;
	
	// validation value
	private String validationValue;
	private String secondFieldValidationValue;
	
}
