/*
 * @ (#) SystemException.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.exception;

/**
 * This is the cms Specific exception that is thrown in case of any unexpected behavior/senario.
 * We are catching every kind of exception in our system. Any unexpected exception/error will be caught 
 * and rethrown as a SystemException. The exception will hold the cause of the problem(root exception) as it's 
 * root cause.
 * 
 * The errorcode into this Exception will contain specific codes to enable the clients to further categorize the 
 * exception.
 *
 */

/**
 * @author swift
 * @since mar 3, 2011
 */
public class SystemException extends GenericException
{
	private int errorCode;
	
	public SystemException ( )
	{
	}
	
	/**
	 * Constructs a SystemException with the specified detail message. The cause
	 * is not initialized, and may subsequently be initialized by a call to
	 * {@link #initCause}.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later
	 *            retrieval by the {@link #getMessage()} method.
	 */
	public SystemException ( String message )
	{
		super ( message );
	}
	
	/**
	 * Constructs a new SystemException with the specified detail message and
	 * cause.
	 * <p>
	 * Note that the detail message associated with <code>cause</code> is
	 * <i>not</i> automatically incorporated in this runtime exception's detail
	 * message.
	 * 
	 * @param message
	 *            the detail message (which is saved for later retrieval by the
	 *            {@link #getMessage()} method).
	 * @param cause
	 *            the cause (which is saved for later retrieval by the
	 *            {@link #getCause()} method). (A <tt>null</tt> value is
	 *            permitted, and indicates that the cause is nonexistent or
	 *            unknown.)
	 * @since 1.4
	 */
	public SystemException ( String message, Throwable cause )
	{
		super ( message, cause );
	}
	
	public SystemException ( Throwable cause )
	{
		super ( cause );
	}
	
	public SystemException ( int errorCode )
	{
		this.errorCode = errorCode;
	}
	
	public SystemException ( int errorCode, String message )
	{
		super ( message );
		this.errorCode = errorCode;
	}
	
	public int getErrorCode ( )
	{
		return errorCode;
	}
	
	public void setErrorCode ( int errorCode )
	{
		this.errorCode = errorCode;
	}
}
