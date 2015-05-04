package com.swiftcorp.portal.common.search;

import javax.servlet.http.HttpServletRequest;

import com.swiftcorp.portal.common.web.SESSION_KEYS;

public class SearchUtil
{
	
	public static String getSearchSqlQuery ( HttpServletRequest request )
	{
		String sqlQuery = request.getParameter ( "sqlQuery" );
		
		return sqlQuery;
	}
	
	public static boolean isSearchResultShow ( HttpServletRequest request )
	{
		// Default is true;
		boolean isSearchResultShow = true;
		Object isSearchResultShowObj = request.getAttribute ( SESSION_KEYS.IS_SEARCH_RESULT_SHOW );
		if ( isSearchResultShowObj != null )
		{
			isSearchResultShow = ((Boolean) isSearchResultShowObj).booleanValue ();
		}
		
		return isSearchResultShow;
	}
	
	public static SearchOperationResult getSearchResult ( String component, HttpServletRequest request )
	{
		SearchOperationResult searchOperationResult = null;
		String searchResultKey = component + SESSION_KEYS.SEARCH_RESULT_SUFFIX;
		searchOperationResult = (SearchOperationResult) request.getAttribute ( searchResultKey );
		return searchOperationResult;
		
	}
	
	public static void prepareRequest ( HttpServletRequest request )
	{
		String currentSortColumnNumber = (String) request.getParameter ( SESSION_KEYS.CURRENT_SORT_COLUMN_NUMBER );
		String isAscending = (String) request.getParameter ( SESSION_KEYS.IS_ASCENDING );
		String currentPageNumber = (String) request.getParameter ( SESSION_KEYS.CURRENT_PAGE_NUMBER );
		
		if ( currentSortColumnNumber == null )
		{
			currentSortColumnNumber = "1";
		}
		if ( isAscending == null )
		{
			isAscending = "true";
		}
		if ( currentPageNumber == null )
		{
			currentPageNumber = "1";
		}
		request.setAttribute ( SESSION_KEYS.CURRENT_SORT_COLUMN_NUMBER, currentSortColumnNumber );
		request.setAttribute ( SESSION_KEYS.IS_ASCENDING, isAscending );
		request.setAttribute ( SESSION_KEYS.CURRENT_PAGE_NUMBER, currentPageNumber );
	}
	
}
