package com.swiftcorp.portal.samplecom.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;



public class SamplecomNotFoundException extends BusinessRuleViolationException
{

	public SamplecomNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SamplecomNotFoundException(int errorCode, String message) {
		super(errorCode, message);
		// TODO Auto-generated constructor stub
	}

	public SamplecomNotFoundException(int errorCode) {
		super(errorCode);
		// TODO Auto-generated constructor stub
	}

	public SamplecomNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SamplecomNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SamplecomNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
