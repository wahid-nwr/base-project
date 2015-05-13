package com.swiftcorp.portal.emailgroup.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class EmailgroupNotFoundException extends BusinessRuleViolationException
{
	public EmailgroupNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmailgroupNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public EmailgroupNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public EmailgroupNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public EmailgroupNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public EmailgroupNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
