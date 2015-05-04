package com.swiftcorp.portal.common.encryption;

public class EncryptionProcessingException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EncryptionProcessingException ( )
	{
		
	}
	
	public EncryptionProcessingException ( String msg )
	{
		super ( msg );
	}
	
	public EncryptionProcessingException ( Throwable e )
	{
		super ( e );
	}
}
