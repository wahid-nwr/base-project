/*
 * @ (#) SearchOperationResult.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.search;

import java.util.ArrayList;

import com.swiftcorp.portal.common.BusinessOperationResult;

/**
 * @author swift
 * @since mar 3, 2011
 */
public class SearchOperationResult extends BusinessOperationResult
{
	private ArrayList<ArrayList<String>> searchResult;
	private int totalRowCount;
	
	public int getTotalRowCount ( )
	{
		return totalRowCount;
	}
	
	public void setTotalRowCount ( int totalRowCount )
	{
		this.totalRowCount = totalRowCount;
	}
	
	public ArrayList<ArrayList<String>> getSearchResult ( )
	{
		return searchResult;
	}
	
	public void setSearchResult ( ArrayList<ArrayList<String>> searchResult )
	{
		this.searchResult = searchResult;
	}
}
