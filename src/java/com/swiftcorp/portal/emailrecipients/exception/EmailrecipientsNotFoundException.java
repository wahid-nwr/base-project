package com.swiftcorp.portal.emailrecipients.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class EmailrecipientsNotFoundException extends BusinessRuleViolationException
{
	public EmailrecipientsNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmailrecipientsNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public EmailrecipientsNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public EmailrecipientsNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public EmailrecipientsNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public EmailrecipientsNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
