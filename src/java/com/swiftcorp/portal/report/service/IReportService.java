/*
 * @ (#) IReportService.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.report.service;

import com.swiftcorp.portal.common.service.IGenericReportService;

/**
 * @author swift
 * @since mar 3, 2011
 */
public interface IReportService extends IGenericReportService
{
	public String generateMotherDeathReport ( int year ) throws Exception;
	
	public String generateChildDeathReport ( int year ) throws Exception;
	public String generateMPRReport ( int year ) throws Exception;
}
