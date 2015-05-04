package com.swiftcorp.portal.group.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;

public class GroupSearchUtils
{
	protected static final Log log = LogFactory.getLog ( GroupSearchUtils.class );
	
	public static String prepareSqlQuery ( HttpServletRequest request )
	{
		int resultPerPage = GlobalConstants.SEARCH_RESULT_PER_PAGE;
		int sortColumnNumber = 1;
		boolean isAscending = true;
		int pageNumber = 1;
		
		String searchQueryInput = request.getParameter ( "searchInput" );
		log.info ( "prepareSqlQuery(): searchQueryInput = " + searchQueryInput );
		if ( searchQueryInput == null )
			searchQueryInput = "";
		
		String sortColumnNumberStr = request.getParameter ( "currentSortColumnNumber" );
		String isAscendingStr = request.getParameter ( "isAscending" );
		String pageNumberStr = request.getParameter ( "currentPageNumber" );
		log.info ( "prepareSqlQuery(): sortColumnNumber = " + sortColumnNumberStr + " : currentPageNumber = " + pageNumberStr + " : isAscendingStr = " + isAscendingStr );
		try
		{
			sortColumnNumber = Integer.parseInt ( sortColumnNumberStr );
			pageNumber = Integer.parseInt ( pageNumberStr );
			isAscending = Boolean.parseBoolean ( isAscendingStr );
		}
		catch (Exception e)
		{
			// default value ;
		}
		String sortOrder = "DESC";
		if ( isAscending )
		{
			sortOrder = "ASC";
		}
		
		int offset = 0;
		if ( pageNumber != -1 && pageNumber > 1 )
		{
			offset = (pageNumber - 1) * resultPerPage;
		}
		
		String projectSqlQuery = " SELECT a.componentId,a.uniqueCode, a.description FROM group a";
		projectSqlQuery += " WHERE a.uniqueCode like '%" + searchQueryInput + "%'";
		projectSqlQuery += " ORDER BY a.uniqueCode ";
		projectSqlQuery += " " + sortOrder;
		projectSqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		
		log.info ( "prepareSqlQuery(): queryStr = " + projectSqlQuery );
		return projectSqlQuery;
	}
	
	public static ArrayList<String> getColumnHeader ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		columnHeader.add ( "label.group.uniqueCode" );
		columnHeader.add ( "label.group.description" );
		return columnHeader;
	}
	
	public static void prepareSearchPage ( HttpServletRequest request )
	{
		String modifyURL = "groupAction.csmp?method=promptModifyGroup&componentId=";
		
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getColumnHeader () );
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
	}
	
}
