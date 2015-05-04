package com.swiftcorp.portal.alert.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;

public class AlertSearchUtils
{
	protected static final Log log = LogFactory.getLog ( AlertSearchUtils.class );
	
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
		String sortColumnStr = "alertId";
		if ( sortColumnNumber == 1 )
		{
			sortColumnStr = "alertId";
		}
		else if ( sortColumnNumber == 2 )
		{
			sortColumnStr = "name";
		}
		
		int offset = 0;
		if ( pageNumber != -1 && pageNumber > 1 )
		{
			offset = (pageNumber - 1) * resultPerPage;
		}
		
		String projectSqlQuery = " SELECT a.componentId, a.alertId,a.name,a.description  FROM alert a";
		projectSqlQuery += " WHERE a.alertId like '%" + searchQueryInput + "%'";
		projectSqlQuery += " ORDER BY a.alertId ";
		projectSqlQuery += " " + sortOrder;
		projectSqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		
		log.info ( "prepareSqlQuery(): queryStr = " + projectSqlQuery );
		return projectSqlQuery;
	}
	
	public static String prepareSqlQueryByUser ( HttpServletRequest request )
	{
		int resultPerPage = GlobalConstants.SEARCH_RESULT_PER_PAGE;
		int sortColumnNumber = 1;
		boolean isAscending = true;
		int pageNumber = 1;
		
		String searchQueryInput = request.getParameter ( "searchInput" );
		log.info ( "prepareSqlQuery(): searchQueryInput = " + searchQueryInput );
		if ( searchQueryInput == null )
			searchQueryInput = "";
		String userId = request.getParameter ( "userId" );
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
		String sortColumnStr = "alertId";
		if ( sortColumnNumber == 1 )
		{
			sortColumnStr = "alertId";
		}
		else if ( sortColumnNumber == 2 )
		{
			sortColumnStr = "description";
		}
		
		int offset = 0;
		if ( pageNumber != -1 && pageNumber > 1 )
		{
			offset = (pageNumber - 1) * resultPerPage;
		}
		
		String projectSqlQuery = " SELECT a.componentId, a.alertId, a.name, a.description  FROM alert a";
		projectSqlQuery += " WHERE a.alertId like '%" + searchQueryInput + "%' and a.recieverId = " + userId;
		projectSqlQuery += " ORDER BY a.alertId ";
		projectSqlQuery += " " + sortOrder;
		projectSqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		
		log.info ( "prepareSqlQuery(): queryStr = " + projectSqlQuery );
		return projectSqlQuery;
	}
	
	public static ArrayList<String> getColumnHeader ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		columnHeader.add ( "label.alert.alertId" );
		columnHeader.add ( "label.alert.name" );
		columnHeader.add ( "label.alert.description" );
		return columnHeader;
	}
	
	public static void prepareSearchPage ( HttpServletRequest request )
	{
		String modifyURL = "alertAction.csmp?method=promptModifyAlert&componentId=";
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getColumnHeader () );
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
	}
	
}
