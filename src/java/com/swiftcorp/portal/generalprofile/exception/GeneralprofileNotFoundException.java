package com.swiftcorp.portal.generalprofile.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class GeneralprofileNotFoundException extends BusinessRuleViolationException
{
	public GeneralprofileNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GeneralprofileNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public GeneralprofileNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public GeneralprofileNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public GeneralprofileNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public GeneralprofileNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
