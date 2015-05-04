package com.swiftcorp.portal.account.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class AccountNotFoundException extends BusinessRuleViolationException
{
	public AccountNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public AccountNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public AccountNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public AccountNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public AccountNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
