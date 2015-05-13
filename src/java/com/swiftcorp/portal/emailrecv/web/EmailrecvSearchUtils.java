package com.swiftcorp.portal.emailrecv.web;
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
public class EmailrecvSearchUtils
{
	protected static final Log log = LogFactory.getLog(EmailrecvSearchUtils.class);
	
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
		
		String projectSqlQuery = " SELECT a.componentId,a.companyemailaddress,a.pophost,a.otherpartyemailaddress,a.isssl,a.popport,a.password,a.taggeduserid,a.smtphost,a.smtpport,a.displayname" +
				"  FROM emailrecv a";
projectSqlQuery += " WHERE a.companyemailaddress like '%" + searchQueryInput + "%'";
projectSqlQuery += " ORDER BY a.companyemailaddress" ;
		projectSqlQuery += " " + sortOrder; 
		projectSqlQuery += " LIMIT " + offset + " , " +  + resultPerPage;	
		
		log.info("prepareSqlQuery(): queryStr = " + projectSqlQuery);
		return projectSqlQuery;
	}
	
	
	public static ArrayList<String> getColumnHeader()
	{
		ArrayList<String> columnHeader = new ArrayList<String>();

		columnHeader.add("label.emailrecv.companyEmailAddress");

		columnHeader.add("label.emailrecv.popHost");

		columnHeader.add("label.emailrecv.otherPartyEmailAddress");

		columnHeader.add("label.emailrecv.isSSL");

		columnHeader.add("label.emailrecv.popPort");

		columnHeader.add("label.emailrecv.password");

		columnHeader.add("label.emailrecv.taggedUserId");

		columnHeader.add("label.emailrecv.smtpHost");

		columnHeader.add("label.emailrecv.smtpPort");

		columnHeader.add("label.emailrecv.displayName");
		/*
		columnHeader.add("label.emailrecv.name");		
		columnHeader.add("label.emailrecv.description"); 
		columnHeader.add("label.emailrecv.last.updatedby");
		 */ 
		return columnHeader;
	}
	
	
	public static void prepareSearchPage(HttpServletRequest request)
	{
		String modifyURL = "emailrecvAction.csmp?method=promptModifyEmailrecv&componentId=";
        request.setAttribute(SESSION_KEYS.MODIFYING_URL,modifyURL )	;
        request.setAttribute(SESSION_KEYS.COLUMN_HEADER_LIST,getColumnHeader());
        request.setAttribute(SESSION_KEYS.MODIFYING_URL,modifyURL )	;
	}
	
	
	
}
