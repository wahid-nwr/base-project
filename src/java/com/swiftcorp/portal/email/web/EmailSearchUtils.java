package com.swiftcorp.portal.email.web;
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
public class EmailSearchUtils
{
	protected static final Log log = LogFactory.getLog(EmailSearchUtils.class);
	
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
		
		String projectSqlQuery = " SELECT a.componentId,a.sender,a.senddate,a.subject,a.type,a.echecked,a.sendertype,a.others,a.ossby,a.refid,a.allrecipients,a.cc,a.recepient,a.internaluserid,a.ossstatus,a.priority,a.emailgroupid,a.comid" +
				"  FROM email a";
projectSqlQuery += " WHERE a.sender like '%" + searchQueryInput + "%'";
projectSqlQuery += " ORDER BY a.sender" ;
		projectSqlQuery += " " + sortOrder; 
		projectSqlQuery += " LIMIT " + offset + " , " +  + resultPerPage;	
		
		log.info("prepareSqlQuery(): queryStr = " + projectSqlQuery);
		return projectSqlQuery;
	}
	
	
	public static ArrayList<String> getColumnHeader()
	{
		ArrayList<String> columnHeader = new ArrayList<String>();

		columnHeader.add("label.email.sender");

		columnHeader.add("label.email.sendDate");

		columnHeader.add("label.email.subject");

		columnHeader.add("label.email.type");

		columnHeader.add("label.email.eChecked");

		columnHeader.add("label.email.senderType");

		columnHeader.add("label.email.others");

		columnHeader.add("label.email.ossBy");

		columnHeader.add("label.email.refId");

		columnHeader.add("label.email.allRecipients");

		columnHeader.add("label.email.cc");

		columnHeader.add("label.email.recepient");

		columnHeader.add("label.email.internalUserId");

		columnHeader.add("label.email.ossStatus");

		columnHeader.add("label.email.priority");

		columnHeader.add("label.email.emailGroupId");

		columnHeader.add("label.email.comId");
		/*
		columnHeader.add("label.email.name");		
		columnHeader.add("label.email.description"); 
		columnHeader.add("label.email.last.updatedby");
		 */ 
		return columnHeader;
	}
	
	
	public static void prepareSearchPage(HttpServletRequest request)
	{
		String modifyURL = "emailAction.csmp?method=promptModifyEmail&componentId=";
        request.setAttribute(SESSION_KEYS.MODIFYING_URL,modifyURL )	;
        request.setAttribute(SESSION_KEYS.COLUMN_HEADER_LIST,getColumnHeader());
        request.setAttribute(SESSION_KEYS.MODIFYING_URL,modifyURL )	;
	}
	
	
	
}
