package com.swiftcorp.portal.emailreferance.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class EmailreferanceNotFoundException extends BusinessRuleViolationException
{
	public EmailreferanceNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmailreferanceNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public EmailreferanceNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public EmailreferanceNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public EmailreferanceNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public EmailreferanceNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
