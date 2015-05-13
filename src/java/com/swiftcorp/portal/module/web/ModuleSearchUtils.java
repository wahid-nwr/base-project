package com.swiftcorp.portal.module.web;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
/*
 * @author swift corporation
 * @since mar 3, 2011
 */
public class ModuleSearchUtils
{
	protected static final Log log = LogFactory.getLog(ModuleSearchUtils.class);
	
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
		
		String projectSqlQuery = " SELECT a.componentId,a.modulecode,a.modulename" +
				"  FROM module a";
projectSqlQuery += " WHERE a.modulecode like '%" + searchQueryInput + "%'";
projectSqlQuery += " ORDER BY a.modulecode" ;
		projectSqlQuery += " " + sortOrder; 
		projectSqlQuery += " LIMIT " + offset + " , " +  + resultPerPage;	
		
		log.info("prepareSqlQuery(): queryStr = " + projectSqlQuery);
		return projectSqlQuery;
	}
	
	
	public static ArrayList<String> getColumnHeader()
	{
		ArrayList<String> columnHeader = new ArrayList<String>();

		columnHeader.add("label.module.moduleCode");

		columnHeader.add("label.module.moduleName");
		/*
		columnHeader.add("label.module.name");		
		columnHeader.add("label.module.description"); 
		columnHeader.add("label.module.last.updatedby");
		 */ 
		return columnHeader;
	}
	
	
	public static void prepareSearchPage(HttpServletRequest request)
	{
		String modifyURL = "moduleAction.csmp?method=promptModifyModule&componentId=";
        request.setAttribute(SESSION_KEYS.MODIFYING_URL,modifyURL )	;
        request.setAttribute(SESSION_KEYS.COLUMN_HEADER_LIST,getColumnHeader());
        request.setAttribute(SESSION_KEYS.MODIFYING_URL,modifyURL )	;
	}
	
	
	
}
