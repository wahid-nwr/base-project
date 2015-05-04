package com.swiftcorp.portal.geo;

import com.swiftcorp.portal.common.BusinessOperationResult;
import com.swiftcorp.portal.geo.dto.GeoImportHHRegDTO;

public class GeoImportSuccess extends BusinessOperationResult
{
	private GeoImportHHRegDTO operationResult;

	public GeoImportHHRegDTO getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(GeoImportHHRegDTO operationResult) {
		this.operationResult = operationResult;
	}
}
