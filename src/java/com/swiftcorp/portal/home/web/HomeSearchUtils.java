package com.swiftcorp.portal.home.web;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
public class HomeSearchUtils
{
	protected static final Log log = LogFactory.getLog(HomeSearchUtils.class);
	
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
		String sortColumnStr = "uniqueCode";				
		if(sortColumnNumber == 1)
		{
			sortColumnStr = "uniqueCode";
		}
		else if(sortColumnNumber == 2)
		{
			sortColumnStr = "description";
		}
		
		int offset = 0;
				
		String offSet = request.getParameter("start");
		if(offSet!=null && !offSet.equals("null") && offSet.length()>0)
		{
			offset = Integer.parseInt(offSet);
		}
		
		String projectSqlQuery = " SELECT a.componentId, a.homeid, a.homechiefname,a.regdate,a.homemember  FROM home a";
		projectSqlQuery += " WHERE a.homeid like '%" + searchQueryInput + "%'";
		projectSqlQuery += " ORDER BY a.homeid " ;
		projectSqlQuery += " " + sortOrder; 
		projectSqlQuery += " LIMIT " + offset + " , " +  + resultPerPage;	
		
		log.info("prepareSqlQuery(): queryStr = " + projectSqlQuery);
		return projectSqlQuery;
	}
	
	
	public static ArrayList<String> getColumnHeader()
	{
		ArrayList<String> columnHeader = new ArrayList<String>();
		columnHeader.add("label.home.homeId");    
		columnHeader.add("label.home.homeChiefName"); 
		columnHeader.add("label.home.regDate");
		columnHeader.add("label.home.homeMember"); 
		return columnHeader;
	}
	
	
	public static void prepareSearchPage(HttpServletRequest request)
	{
		String modifyURL = "homeAction.csmp?method=promptModifyHome&componentId=";
        request.setAttribute(SESSION_KEYS.MODIFYING_URL,modifyURL )	;
        request.setAttribute(SESSION_KEYS.COLUMN_HEADER_LIST,getColumnHeader());
        request.setAttribute(SESSION_KEYS.MODIFYING_URL,modifyURL )	;
	}
	
	
	
}
