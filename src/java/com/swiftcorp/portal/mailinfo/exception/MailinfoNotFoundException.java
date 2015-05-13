package com.swiftcorp.portal.mailinfo.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class MailinfoNotFoundException extends BusinessRuleViolationException
{
	public MailinfoNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MailinfoNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public MailinfoNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public MailinfoNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public MailinfoNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public MailinfoNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
