package com.swiftcorp.portal.dcrinfo.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class DcrinfoNotFoundException extends BusinessRuleViolationException
{
	public DcrinfoNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DcrinfoNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public DcrinfoNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public DcrinfoNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public DcrinfoNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public DcrinfoNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
