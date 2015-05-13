/*
 * @ (#) SamplecomAlreadyExistsException.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.samplecom.exception;

import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;



/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class SamplecomAlreadyExistsException extends BusinessRuleViolationException
{

    public SamplecomAlreadyExistsException()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public SamplecomAlreadyExistsException(int errorCode, String message)
    {
        super(errorCode, message);
        // TODO Auto-generated constructor stub
    }

    public SamplecomAlreadyExistsException(int errorCode)
    {
        super(errorCode);
        // TODO Auto-generated constructor stub
    }

    public SamplecomAlreadyExistsException(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public SamplecomAlreadyExistsException(String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public SamplecomAlreadyExistsException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
