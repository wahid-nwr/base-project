package com.swiftcorp.portal.email.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class EmailNotFoundException extends BusinessRuleViolationException
{
	public EmailNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmailNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public EmailNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public EmailNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public EmailNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public EmailNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
