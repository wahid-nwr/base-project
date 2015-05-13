package com.swiftcorp.portal.patient.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class PatientNotFoundException extends BusinessRuleViolationException
{
	public PatientNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PatientNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public PatientNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public PatientNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public PatientNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public PatientNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
