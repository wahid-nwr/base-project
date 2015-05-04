package com.swiftcorp.portal.dcrreport.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class DcrReportNotFoundException extends BusinessRuleViolationException
{
	public DcrReportNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DcrReportNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public DcrReportNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public DcrReportNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public DcrReportNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public DcrReportNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
