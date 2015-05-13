package com.swiftcorp.portal.module.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class ModuleNotFoundException extends BusinessRuleViolationException
{
	public ModuleNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ModuleNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public ModuleNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public ModuleNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public ModuleNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ModuleNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
