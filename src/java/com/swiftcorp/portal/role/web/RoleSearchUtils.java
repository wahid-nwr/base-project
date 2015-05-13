package com.swiftcorp.portal.role.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.ViewValueDTO;
import com.swiftcorp.portal.common.web.SESSION_KEYS;

public class RoleSearchUtils
{
	protected static final Log log = LogFactory.getLog ( RoleSearchUtils.class );
	private static List<ViewValueDTO> accessLevelList = null;
	
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
		String sortColumnStr = "uniqueCode";
		if ( sortColumnNumber == 1 )
		{
			sortColumnStr = "uniqueCode";
		}
		else if ( sortColumnNumber == 2 )
		{
			sortColumnStr = "accesslevel";
		}
		else if ( sortColumnNumber == 3 )
		{
			sortColumnStr = "description";
		}
		
		int offset = 0;
		if ( pageNumber != -1 && pageNumber > 1 )
		{
			offset = (pageNumber - 1) * resultPerPage;
		}
		
		String projectSqlQuery = " SELECT a.componentId, a.uniqueCode,a.description  FROM role a";
		projectSqlQuery += " WHERE a.uniqueCode like '%" + searchQueryInput + "%'";
		projectSqlQuery += " ORDER BY a." + sortColumnStr;
		projectSqlQuery += " " + sortOrder;
		projectSqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		
		log.info ( "prepareSqlQuery(): queryStr = " + projectSqlQuery );
		return projectSqlQuery;
	}
	
	public static ArrayList<String> getColumnHeader ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		columnHeader.add ( "label.role.name" );
		// columnHeader.add("label.role.accessLevel");
		columnHeader.add ( "label.role.description" );
		return columnHeader;
	}
	
	public static void prepareSearchPage ( HttpServletRequest request )
	{
		String modifyURL = "roleAction.csmp?method=promptModifyRole&componentId=";
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getColumnHeader () );
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
	}
	
	public static List<ViewValueDTO> getAccessLevelList ( )
	{
		if ( accessLevelList == null )
		{
			accessLevelList = new ArrayList<ViewValueDTO> ();
			accessLevelList.add ( new ViewValueDTO ( "System ", GlobalConstants.SYSTEM_LEVEL ) );
			accessLevelList.add ( new ViewValueDTO ( "Group ", GlobalConstants.GROUP_LEVEL ) );
			accessLevelList.add ( new ViewValueDTO ( "User ", GlobalConstants.END_USR_LEVEL ) );
		}
		return accessLevelList;
	}
	
}
