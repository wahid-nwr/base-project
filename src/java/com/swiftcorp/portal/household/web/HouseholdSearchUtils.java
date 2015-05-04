package com.swiftcorp.portal.household.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.geo.dto.GeoDTO;

public class HouseholdSearchUtils
{
	protected static final Log log = LogFactory.getLog ( HouseholdSearchUtils.class );
	
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
		String loginUserArea = "";
		GeoDTO userArea = (GeoDTO)request.getSession ().getAttribute ( SESSION_KEYS.LOGIN_USER_AREA );
		List<GeoDTO> userChildArea = (List<GeoDTO>)request.getSession ().getAttribute ( SESSION_KEYS.LOGIN_USER_CHILD_AREA );
		for(int i = 0;userChildArea!=null && i<userChildArea.size ();i++)
		{
			GeoDTO childArea = (GeoDTO)userChildArea.get ( i );
			if(childArea.getGeoType () == DTOConstants.GEO_TYPE_BRANCH)
			{
				if(loginUserArea!=null && !loginUserArea.equals ( "null" ) && loginUserArea.length ()>0)
				{
					loginUserArea += ","+childArea.getComponentId ();
				}
				else
				{
					loginUserArea = ""+childArea.getComponentId ();
				}
			}
		}
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
		String sortColumnStr = "houseNo";
		if ( sortColumnNumber == 1 )
		{
			sortColumnStr = "houseNo";
		}
		else if ( sortColumnNumber == 2 )
		{
			sortColumnStr = "totalMember";
		}
		
		int offset = 0;
		if ( pageNumber != -1 && pageNumber > 1 )
		{
			offset = (pageNumber - 1) * resultPerPage;
		}
		
		String projectSqlQuery = " SELECT distinct a.componentId, a.houseNo, a.totalMember FROM household a";
		projectSqlQuery += " WHERE a.branch in("+loginUserArea+")";
		projectSqlQuery += " and a.houseNo like '%" + searchQueryInput + "%'";
		projectSqlQuery += " ORDER BY a.houseNo ";
		projectSqlQuery += " " + sortOrder;
		projectSqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		
		log.info ( "prepareSqlQuery(): queryStr = " + projectSqlQuery );
		return projectSqlQuery;
	}
	
	public static ArrayList<String> getColumnHeader ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		columnHeader.add ( "label.household.householdId" );
		columnHeader.add ( "label.household.totalMember" );
		return columnHeader;
	}
	
	public static void prepareSearchPage ( HttpServletRequest request )
	{
		String modifyURL = "householdAction.csmp?method=promptModifyHousehold&componentId=";
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getColumnHeader () );
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
	}
	
}
