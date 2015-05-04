package com.swiftcorp.portal.algorithm;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.algorithm.dto.AlgQuestionDTO;

public class AlgQuestionSuccessResult extends BusinessOperationResult
{
	private AlgQuestionDTO operationResult;

	public AlgQuestionDTO getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(AlgQuestionDTO operationResult) {
		this.operationResult = operationResult;
	}
	
}
