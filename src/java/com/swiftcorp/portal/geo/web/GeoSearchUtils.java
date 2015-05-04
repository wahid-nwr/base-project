package com.swiftcorp.portal.geo.web;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
public class GeoSearchUtils
{
	protected static final Log log = LogFactory.getLog(GeoSearchUtils.class);
	
	public static String prepareSqlQuery( HttpServletRequest request)
	{
		int resultPerPage = GlobalConstants.SEARCH_RESULT_PER_PAGE ;
		int sortColumnNumber =1;
		boolean isAscending = true ;
		int pageNumber =1 ;
		
		String searchQueryInput = request.getParameter("searchInput");
		log.info("prepareSqlQuery(): searchQueryInput = "+ searchQueryInput);
		if(searchQueryInput == null)
		searchQueryInput = "";
		
		String sortColumnNumberStr = request.getParameter("currentSortColumnNumber");
		String isAscendingStr = request.getParameter("isAscending");
		String pageNumberStr = request.getParameter("currentPageNumber");
		log.info("prepareSqlQuery(): sortColumnNumber = " + sortColumnNumberStr 
				                    + " : currentPageNumber = "+ pageNumberStr
				                    + " : isAscendingStr = "+ isAscendingStr);
		try
		{
			sortColumnNumber = Integer.parseInt(sortColumnNumberStr);
			pageNumber = Integer.parseInt(pageNumberStr);	
			isAscending = Boolean.parseBoolean(isAscendingStr);	
		}
		catch (Exception e)
		{
		  //default value ;
		}
		String sortOrder = "DESC";
		if(isAscending)
		{
			sortOrder = "ASC";
		}	 
		String sortColumnStr = "geoId";				
		if(sortColumnNumber == 1)
		{
			sortColumnStr = "geoId";
		}
		else if(sortColumnNumber == 2)
		{
			sortColumnStr = "description";
		}
		
		int offset = 0;
		if( pageNumber != -1 && pageNumber > 1)
		{
			offset = (pageNumber - 1)*resultPerPage;
	    }
		
		String projectSqlQuery = " SELECT a.componentId, a.code, a.name  FROM geolocation a";
		projectSqlQuery += " WHERE a.code like '%" + searchQueryInput + "%'";
		projectSqlQuery += " ORDER BY a.code " ;
		projectSqlQuery += " " + sortOrder; 
		projectSqlQuery += " LIMIT " + offset + " , " +  + resultPerPage;	
		
		log.info("prepareSqlQuery(): queryStr = " + projectSqlQuery);
		return projectSqlQuery;
	}
	
	
	public static ArrayList<String> getColumnHeader()
	{
		ArrayList<String> columnHeader = new ArrayList<String>();
		columnHeader.add("label.geo.geoId");    
		columnHeader.add("label.geo.name"); 
		//columnHeader.add("label.geo.type"); 
		return columnHeader;
	}
	
	public static String getGeoHeirarchyQuery(String startWith)
	{
		String query = "SELECT hi.componentid AS treeitem"+
						/*			
						 * others info		
							" CONCAT(REPEAT('    ', lvl - 1), hi.componentid) AS treeitem,hierarchy_sys_connect_by_path('/', hi.componentid) AS path,"+
							" parentArea, lvl,"+
							" CASE"+
							" WHEN lvl >= @maxlevel THEN 1"+
							" ELSE COALESCE("+
							"("+
							" SELECT  0"+
							" FROM    geolocation hl"+
							" WHERE   hl.parentArea = ho.id"+
							" AND hl.componentid <> @start_with"+
							" LIMIT 1"+
				            " ), 1)"+
				        " END AS is_leaf,"+
				        " hierarchy_connect_by_iscycle(hi.componentid) AS is_cycle"+
				        */
				        " FROM(" +
						" SELECT hierarchy_connect_by_parent_eq_prior_id_with_level_and_loop(componentid, @maxlevel) AS id,"+
						" CAST(@level AS SIGNED) AS lvl"+
				        " FROM ("+
				                " SELECT @start_with:="+startWith+","+
				                        "@id:=@start_with,"+
				                        "@level:=0,"+
				                        "@maxlevel:=NULL"+
				                " ) vars, geolocation"+
				        " WHERE @id IS NOT NULL"+
				        " ) ho"+
				        " JOIN geolocation hi"+
				        " ON hi.componentid = ho.id";
		return query;
	}
	public static void prepareSearchPage(HttpServletRequest request)
	{
		String modifyURL = "geoAction.csmp?method=promptModifyGeo&componentId=";
        request.setAttribute(SESSION_KEYS.MODIFYING_URL,modifyURL )	;
        request.setAttribute(SESSION_KEYS.COLUMN_HEADER_LIST,getColumnHeader());
        request.setAttribute(SESSION_KEYS.MODIFYING_URL,modifyURL )	;
	}
	
	
	
}
