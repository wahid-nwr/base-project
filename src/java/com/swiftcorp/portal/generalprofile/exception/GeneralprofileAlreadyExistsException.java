/*
 * @ (#) GeneralprofileAlreadyExistsException.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.generalprofile.exception;
import com.swiftcorp.portal.common.exception.BusinessRuleViolationException;
/**
 * @author swift corporation
 * @since mar 3, 2011
 */
public class GeneralprofileAlreadyExistsException extends BusinessRuleViolationException
{
    public GeneralprofileAlreadyExistsException()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public GeneralprofileAlreadyExistsException(int errorCode, String message)
    {
        super(errorCode, message);
        // TODO Auto-generated constructor stub
    }
    public GeneralprofileAlreadyExistsException(int errorCode)
    {
        super(errorCode);
        // TODO Auto-generated constructor stub
    }
    public GeneralprofileAlreadyExistsException(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }
    public GeneralprofileAlreadyExistsException(String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }
    public GeneralprofileAlreadyExistsException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }
}
