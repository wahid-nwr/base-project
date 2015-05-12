package com.swiftcorp.portal.info.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class InfoNotFoundException extends BusinessRuleViolationException
{
	public InfoNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InfoNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public InfoNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public InfoNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public InfoNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public InfoNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
