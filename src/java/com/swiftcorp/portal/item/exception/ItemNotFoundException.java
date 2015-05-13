package com.swiftcorp.portal.item.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class ItemNotFoundException extends BusinessRuleViolationException
{
	public ItemNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public ItemNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public ItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public ItemNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ItemNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
