package com.swiftcorp.portal.covering.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class CoveringNotFoundException extends BusinessRuleViolationException
{
	public CoveringNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CoveringNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public CoveringNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public CoveringNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public CoveringNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public CoveringNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
