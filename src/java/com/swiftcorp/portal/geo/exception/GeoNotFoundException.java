package com.swiftcorp.portal.geo.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
public class GeoNotFoundException extends BusinessRuleViolationException
{
	public GeoNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GeoNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}
	public GeoNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}
	public GeoNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public GeoNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public GeoNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
