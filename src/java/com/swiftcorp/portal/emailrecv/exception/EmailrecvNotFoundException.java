package com.swiftcorp.portal.emailrecv.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class EmailrecvNotFoundException extends BusinessRuleViolationException
{
	public EmailrecvNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmailrecvNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public EmailrecvNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public EmailrecvNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public EmailrecvNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public EmailrecvNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
