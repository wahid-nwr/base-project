package com.swiftcorp.portal.beneficiary.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;

public class BeneficiaryNotFoundException extends BusinessRuleViolationException
{
	public BeneficiaryNotFoundException ( )
	{
		super ();
		// TODO Auto-generated constructor stub
	}
	
	public BeneficiaryNotFoundException ( int errorCode, String message )
	{
		super ( errorCode, message );
		// TODO Auto-generated constructor stub
	}
	
	public BeneficiaryNotFoundException ( int errorCode )
	{
		super ( errorCode );
		// TODO Auto-generated constructor stub
	}
	
	public BeneficiaryNotFoundException ( String message, Throwable cause )
	{
		super ( message, cause );
		// TODO Auto-generated constructor stub
	}
	
	public BeneficiaryNotFoundException ( String message )
	{
		super ( message );
		// TODO Auto-generated constructor stub
	}
	
	public BeneficiaryNotFoundException ( Throwable cause )
	{
		super ( cause );
		// TODO Auto-generated constructor stub
	}
}
