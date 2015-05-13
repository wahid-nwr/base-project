package com.swiftcorp.portal.user.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.group.dto.GroupDTO;

public class UserSearchUtils
{
	protected static final Log log = LogFactory.getLog ( UserSearchUtils.class );
	
	public static String prepareSqlQuery ( HttpServletRequest request )
	{
		int resultPerPage = GlobalConstants.SEARCH_RESULT_PER_PAGE;
		boolean isAscending = true;
		int pageNumber = 1;
		int sortColumnNumber = 1;
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
		if( pageNumber != -1 && pageNumber > 1)
		{
			offset = (pageNumber - 1)*resultPerPage;
	    }
		String offSet = request.getParameter("start");
		if(offSet!=null && !offSet.equals("null") && offSet.length()>0)
		{
			offset = Integer.parseInt(offSet);
		}

		
		GroupDTO group = (GroupDTO) request.getSession ().getAttribute ( "group" );
				
		String sqlQuery = " SELECT a.componentId, a.uniqueCode, a.lastName, a.firstName,a.password,a.roleId FROM users a";
		if ( group != null )
		{
			sqlQuery += " WHERE a.groupId = " + group.getComponentId () + " AND";
		}
		else
		{
			sqlQuery += " WHERE";
		}
		sqlQuery += " ( a.uniqueCode like '%" + searchQueryInput + "%'";
		sqlQuery += " OR a.lastName like '%" + searchQueryInput + "%'";
		sqlQuery += " OR a.firstName like '%" + searchQueryInput + "%')";
		sqlQuery += " ORDER BY a.uniqueCode ";
		sqlQuery += " " + sortOrder;
		sqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		System.out.println ( "sqlQuery::::::::::" + sqlQuery );
		
		return sqlQuery;
	}
	
	public static String prepareEmailSqlQuery ( HttpServletRequest request )
	{
		
		int resultPerPage = GlobalConstants.SEARCH_RESULT_PER_PAGE;
		boolean isAscending = true;
		int pageNumber = 1;
		int sortColumnNumber = 1;
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
		if( pageNumber != -1 && pageNumber > 1)
		{
			offset = (pageNumber - 1)*resultPerPage;
	    }
		String offSet = request.getParameter("start");
		if(offSet!=null && !offSet.equals("null") && offSet.length()>0)
		{
			offset = Integer.parseInt(offSet);
		}

		
		GroupDTO group = (GroupDTO) request.getSession ().getAttribute ( "group" );
				
		String sqlQuery = " SELECT a.componentId, concat(a.lastName ,' ',a.firstName) name,a.email FROM users a";
		if ( group != null )
		{
			sqlQuery += " WHERE a.groupId = " + group.getComponentId () + " AND";
		}
		else
		{
			sqlQuery += " WHERE";
		}
		sqlQuery += " ( a.uniqueCode like '%" + searchQueryInput + "%'";
		sqlQuery += " OR a.lastName like '%" + searchQueryInput + "%'";
		sqlQuery += " OR a.firstName like '%" + searchQueryInput + "%'";
		sqlQuery += " OR a.email like '%" + searchQueryInput + "%')";
		sqlQuery += " ORDER BY a.email ";
		sqlQuery += " " + sortOrder;
		//sqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		System.out.println ( "sqlQuery::::::::::" + sqlQuery );

		return sqlQuery;
	}
	
	public static ArrayList<String> getMailColumnHeader ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		
		columnHeader.add ( "label.user.userName" );
		columnHeader.add ( "label.user.email" );
		
		return columnHeader;
	}
	
	public static ArrayList<String> getColumnHeader ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		columnHeader.add ( "label.user.uniqueCode" );
		columnHeader.add ( "label.user.lastName" );
		columnHeader.add ( "label.user.firstName" );
		columnHeader.add ( "label.user.password" );
		columnHeader.add("label.user.role");
		return columnHeader;
	}
	
	public static void prepareSearchPage ( HttpServletRequest request )
	{
		String modifyURL = "userAction.csmp?method=promptModifyUser&componentId=";
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getColumnHeader () );
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
	}
	
	public static void prepareMailSearchPage ( HttpServletRequest request )
	{
		String modifyURL = "userAction.csmp?method=promptModifyUser&componentId=";
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getMailColumnHeader () );
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
	}
}
