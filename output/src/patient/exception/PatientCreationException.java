/*
 * @ (#) CMSPatientCreationException.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.patient.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
/**
 * @author mosa
 * @since Sep 4, 2008
 */
public class PatientCreationException extends BusinessRuleViolationException
{
    public PatientCreationException()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public PatientCreationException(int errorCode, String message)
    {
        super(errorCode, message);
        // TODO Auto-generated constructor stub
    }
    public PatientCreationException(int errorCode)
    {
        super(errorCode);
        // TODO Auto-generated constructor stub
    }
    public PatientCreationException(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
    public PatientCreationException(String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }
    public PatientCreationException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
