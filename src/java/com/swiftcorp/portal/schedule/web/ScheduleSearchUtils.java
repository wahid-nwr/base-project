package com.swiftcorp.portal.schedule.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;

public class ScheduleSearchUtils
{
	protected static final Log log = LogFactory.getLog ( ScheduleSearchUtils.class );
	
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
		String sortColumnStr = "sheduleDate";
		if ( sortColumnNumber == 1 )
		{
			sortColumnStr = "sheduleDate";
		}
		else if ( sortColumnNumber == 2 )
		{
			sortColumnStr = "executionDate";
		}
		
		int offset = 0;
		if ( pageNumber != -1 && pageNumber > 1 )
		{
			offset = (pageNumber - 1) * resultPerPage;
		}
		
		String projectSqlQuery = " SELECT a.componentId,a.visitItemId,a.visitItemType,a.scheduleby,a.user, a.scheduleDate,a.executionDate,a.remarks  FROM visitschedule a";
		// projectSqlQuery += " WHERE a.uniqueCode like '%" + searchQueryInput +
		// "%'";
		projectSqlQuery += " ORDER BY a.scheduleDate ";
		projectSqlQuery += " " + sortOrder;
		projectSqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		
		log.info ( "prepareSqlQuery(): queryStr = " + projectSqlQuery );
		return projectSqlQuery;
	}
	
	public static ArrayList<String> getColumnHeader ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		columnHeader.add ( "label.schedule.visitItemId" );
		columnHeader.add ( "label.schedule.visitItemType" );
		columnHeader.add ( "label.schedule.scheduleby" );
		columnHeader.add ( "label.schedule.user" );
		columnHeader.add ( "label.schedule.scheduleDate" );
		columnHeader.add ( "label.schedule.executionDate" );
		columnHeader.add ( "label.schedule.remarks" );
		return columnHeader;
	}
	
	public static void prepareSearchPage ( HttpServletRequest request )
	{
		String modifyURL = "scheduleAction.csmp?method=promptModifySchedule&componentId=";
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getColumnHeader () );
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
	}
	
}
