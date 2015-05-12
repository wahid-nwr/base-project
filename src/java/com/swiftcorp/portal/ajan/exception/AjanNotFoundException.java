package com.swiftcorp.portal.ajan.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class AjanNotFoundException extends BusinessRuleViolationException
{
	public AjanNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AjanNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public AjanNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public AjanNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public AjanNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public AjanNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
