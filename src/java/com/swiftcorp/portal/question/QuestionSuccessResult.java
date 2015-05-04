/*
 * @ (#) QuestionSuccessResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.question;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.question.dto.QuestionDTO;

/**
 * @author mosa
 * @since Sep 8, 2008
 */
public class QuestionSuccessResult extends BusinessOperationResult
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QuestionDTO operationResult;
	
	public QuestionDTO getOperationResult ( )
	{
		return operationResult;
	}
	
	public void setOperationResult ( QuestionDTO operationResult )
	{
		this.operationResult = operationResult;
	}
	
}
