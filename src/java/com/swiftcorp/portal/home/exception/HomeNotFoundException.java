package com.swiftcorp.portal.home.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class HomeNotFoundException extends BusinessRuleViolationException
{
	public HomeNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HomeNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public HomeNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public HomeNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public HomeNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public HomeNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
