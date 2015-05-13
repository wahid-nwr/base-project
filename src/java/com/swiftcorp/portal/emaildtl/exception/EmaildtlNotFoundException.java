package com.swiftcorp.portal.emaildtl.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class EmaildtlNotFoundException extends BusinessRuleViolationException
{
	public EmaildtlNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmaildtlNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public EmaildtlNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public EmaildtlNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public EmaildtlNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public EmaildtlNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
