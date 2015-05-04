package com.swiftcorp.portal.beneficiary.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.dto.DTOConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;
import com.swiftcorp.portal.geo.dto.GeoDTO;

public class BeneficiarySearchUtils
{
	protected static final Log log = LogFactory.getLog ( BeneficiarySearchUtils.class );
	
	public static String prepareChildSqlQuesry ( HttpServletRequest request )
	{
		int resultPerPage = GlobalConstants.SEARCH_RESULT_PER_PAGE;
		int sortColumnNumber = 1;
		boolean isAscending = true;
		int pageNumber = 1;
		
		String searchQueryInput = request.getParameter ( "searchInput" );
		String startDateInput = request.getParameter ( "startDate" );
		String endDateInput = "";
		endDateInput = request.getParameter ( "endDate" );
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
		String sortColumnStr = "childId";
		if ( sortColumnNumber == 1 )
		{
			sortColumnStr = "childId";
		}
		else if ( sortColumnNumber == 2 )
		{
			sortColumnStr = "regDate";
		}
		
		int offset = 0;
		if ( pageNumber != -1 && pageNumber > 1 )
		{
			offset = (pageNumber - 1) * resultPerPage;
		}
		String sqlClause = "";
		String projectSqlQuery = " SELECT a.componentId, a.childId,a.regDate,a.motherId,e.uniquecode";
		projectSqlQuery += " FROM childbeneficiary a, motherbeneficiary b, pregnancyrecord c, visitinfo d,users e ";
		if ( searchQueryInput != null && !searchQueryInput.equals ( "null" ) && searchQueryInput.length () > 0 )
		{
			sqlClause = " where ";
			sqlClause += " a.childId ='" + searchQueryInput + "'";
		}
		if ( startDateInput != null && !startDateInput.equalsIgnoreCase ( "null" ) && startDateInput.length () > 0 && endDateInput != null && !endDateInput.equalsIgnoreCase ( "null" ) && endDateInput.length () > 0 )
		{
			if ( sqlClause != null && sqlClause.contains ( "where" ) )
			{
				sqlClause += " and a.regDate between '" + startDateInput + "'";
				sqlClause += " and '" + endDateInput + "'";
			}
			else
			{
				sqlClause += " where a.regDate between '" + startDateInput + "'";
				sqlClause += " and '" + endDateInput + "'";
			}
		}
		else if ( startDateInput != null && !startDateInput.equalsIgnoreCase ( "null" ) && startDateInput.length () > 0 )
		{
			if ( sqlClause != null && sqlClause.contains ( "where" ) )
			{
				sqlClause += " and a.regDate > '" + startDateInput + "'";
			}
			else
			{
				sqlClause += " where a.regDate > '" + startDateInput + "'";
			}
		}
		else if ( endDateInput != null && !endDateInput.equalsIgnoreCase ( "null" ) && endDateInput.length () > 0 )
		{
			if ( sqlClause != null && sqlClause.contains ( "where" ) )
			{
				sqlClause += " and a.regDate <'" + endDateInput + "'";
			}
			else
			{
				sqlClause += " where a.regDate <'" + endDateInput + "'";
			}
		}
		if ( sqlClause != null && sqlClause.contains ( "where" ) )
		{
			sqlClause += " and a.motherId = b.beneficiaryId and b.componentId = c.benIdForPreg and c.visitId = d.componentId";
			sqlClause += " and c.LMP = (select max(LMP) from pregnancyrecord where benIdForPreg = b.componentId) and d.userId = e.componentId ";
		}
		else
		{
			sqlClause += " where a.motherId = b.beneficiaryId and b.componentId = c.benIdForPreg and c.visitId = d.componentId";
			sqlClause += " and c.LMP = (select max(LMP) from pregnancyrecord where benIdForPreg = b.componentId) and d.userId = e.componentId ";
		}
		projectSqlQuery += sqlClause;
		projectSqlQuery += " ORDER BY a.regDate desc,e.uniqueCode,a.motherId ";
		projectSqlQuery += " " + sortOrder;
		projectSqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		
		log.info ( "prepareSqlQuery(): queryStr = " + projectSqlQuery );
		return projectSqlQuery;
	}
	
	public static String prepareSqlQuery ( HttpServletRequest request )
	{
		int resultPerPage = GlobalConstants.SEARCH_RESULT_PER_PAGE;
		int sortColumnNumber = 1;
		boolean isAscending = true;
		int pageNumber = 1;
		
		String searchQueryInput = request.getParameter ( "searchInput" );
		String startDateInput = request.getParameter ( "startDate" );
		String endDateInput = "";
		endDateInput = request.getParameter ( "endDate" );
		String branch = request.getParameter ( "branch" );
		String skId = request.getParameter ( "skId" );
		String riskLevel = request.getParameter ( "riskLevel" );
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
		String sortColumnStr = "beneficiaryId";
		if ( sortColumnNumber == 1 )
		{
			sortColumnStr = "beneficiaryId";
		}
		else if ( sortColumnNumber == 2 )
		{
			sortColumnStr = "regDate";
		}
		
		int offset = 0;
		if ( pageNumber != -1 && pageNumber > 1 )
		{
			offset = (pageNumber - 1) * resultPerPage;
		}
		String sqlClause = "";
		String projectSqlQuery = "";
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
		if(branch!=null && !branch.equals ( "null" ) && branch.length ()>0)
		{
			if(branch.equals ( "all" ))
			{
				
			}
			else
			{
				loginUserArea = branch;
			}
		}
		String motherSqlQuery = " SELECT a.componentId, a.beneficiaryId,a.regDate,hh.ssId,e.uniquecode";
		motherSqlQuery += " ,geo.name as branch";
		motherSqlQuery += " ,(select name from geolocation glo where glo.componentid = geo.parentArea) as region,";
		motherSqlQuery += "(select componentId from geolocation glo where glo.componentid = geo.parentArea) as regionid,";
		motherSqlQuery += "(select name from geolocation cc where cc.componentid = (select parentArea from geolocation where componentid=regionid)) cc,";
		motherSqlQuery += "(select risklevel from risk rr where rr.beneficiaryId = a.beneficiaryId and generationDate=";
		motherSqlQuery += "(select max(generationDate) from risk r2 where r2.beneficiaryId = a.beneficiaryId))as risk,'mother' as beneficiaryType ";
		motherSqlQuery += " from  motherbeneficiary a,pregnancyrecord c,visitinfo d,users e,household hh,householdmember hhm,geolocation geo ";
		motherSqlQuery += " where hh.branch in ("+loginUserArea+") and geo.componentId = hh.branch and hhm.householdid=hh.componentid and hhm.beneficiaryId = a.beneficiaryId and a.componentId = c.benIdForPreg and c.visitId = d.componentId and d.userId = e.componentId ";
		String chiildSqlQuery = "select a.componentId,a.childId,a.regDate,hh.ssId,e.uniquecode";
		chiildSqlQuery += " ,geo.name as branch";
		chiildSqlQuery += " ,(select name from geolocation glo where glo.componentid = geo.parentArea) as region,";
		chiildSqlQuery += "(select componentid from geolocation glo where glo.componentid = geo.parentArea) as regionid,";
		chiildSqlQuery += "(select name from geolocation cc where cc.componentid = (select parentArea from geolocation where componentid=regionid)) cc,";
		chiildSqlQuery += "(select risklevel from risk rr where rr.beneficiaryId = a.childId and generationDate=";
		chiildSqlQuery += "(select max(generationDate) from risk r2 where r2.beneficiaryId = a.childId))as risk,'child' as beneficiaryType ";
		chiildSqlQuery += "from  childbeneficiary a,pregnancyrecord c,motherbeneficiary b,visitinfo d,users e,household hh,householdmember hhm,geolocation geo ";
		chiildSqlQuery += " where hh.branch in ("+loginUserArea+") and geo.componentId = hh.branch and hhm.householdid=hh.componentid and hhm.beneficiaryId = b.beneficiaryId  and b.componentId = c.benIdForPreg and c.visitId = d.componentId and d.userId = e.componentId";
		chiildSqlQuery += " and c.LMP = (select max(LMP) from pregnancyrecord where benIdForPreg = b.componentId) and a.motherId = b.beneficiaryId";
		if ( skId != null && !skId.equalsIgnoreCase ( "null" ) && skId.length () > 0 )
		{
			motherSqlQuery += " and e.uniquecode =" + skId;
			chiildSqlQuery += " and e.uniquecode =" + skId;
		}
		if ( searchQueryInput != null && !searchQueryInput.equals ( "null" ) && searchQueryInput.length () > 0 )
		{
			
			motherSqlQuery += " and a.beneficiaryId ='" + searchQueryInput + "'";
			chiildSqlQuery += " and a.childId ='" + searchQueryInput + "'";
		}
		if ( startDateInput != null && !startDateInput.equalsIgnoreCase ( "null" ) && startDateInput.length () > 0 && endDateInput != null && !endDateInput.equalsIgnoreCase ( "null" ) && endDateInput.length () > 0 )
		{
			if ( motherSqlQuery != null && motherSqlQuery.contains ( "where" ) )
			{
				motherSqlQuery += " and a.regDate between '" + startDateInput + "' and '" + endDateInput + "'";
				chiildSqlQuery += " and a.regDate between '" + startDateInput + "' and '" + endDateInput + "'";
			}
			else
			{
				motherSqlQuery += " where a.regDate between '" + startDateInput + "' and '" + endDateInput + "'";
				chiildSqlQuery += " where a.regDate between '" + startDateInput + "' and '" + endDateInput + "'";
			}
		}
		else if ( startDateInput != null && !startDateInput.equalsIgnoreCase ( "null" ) && startDateInput.length () > 0 )
		{
			if ( motherSqlQuery != null && motherSqlQuery.contains ( "where" ) )
			{
				motherSqlQuery += " and a.regDate > '" + startDateInput + "'";
				chiildSqlQuery += " and a.regDate > '" + startDateInput + "'";
			}
			else
			{
				motherSqlQuery += " where a.regDate > '" + startDateInput + "'";
				chiildSqlQuery += " where a.regDate > '" + startDateInput + "'";
			}
		}
		else if ( endDateInput != null && !endDateInput.equalsIgnoreCase ( "null" ) && endDateInput.length () > 0 )
		{
			if ( motherSqlQuery != null && motherSqlQuery.contains ( "where" ) )
			{
				motherSqlQuery += " and a.regDate <'" + endDateInput + "'";
				chiildSqlQuery += " and a.regDate <'" + endDateInput + "'";
			}
			else
			{
				motherSqlQuery += " where a.regDate <'" + endDateInput + "'";
				chiildSqlQuery += " where a.regDate <'" + endDateInput + "'";
			}
		}
		projectSqlQuery = "SELECT componentid,beneficiaryid,regdate,ssid,uniquecode,branch,region,cc,risk,beneficiarytype FROM ((" + motherSqlQuery + ") union (" + chiildSqlQuery + ")) AS tmp ";
		if(riskLevel!=null && !riskLevel.equals ( "null" ) && riskLevel.length ()>0)
		{
			if(riskLevel.equals ( "all" ))
			{
				
			}
			else
			{
				projectSqlQuery += " where risk ="+riskLevel;
			}
		}
		projectSqlQuery += " ORDER BY beneficiaryType,regDate desc,beneficiaryId ";
		projectSqlQuery += " " + sortOrder;
		projectSqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		
		System.out.println ( "projectSqlQuery::" + projectSqlQuery );
		log.info ( "prepareSqlQuery(): queryStr = " + projectSqlQuery );
		return projectSqlQuery;
	}
	
	public static ArrayList<String> getColumnHeader ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		columnHeader.add ( "label.beneficiary.uniqueCode" );
		columnHeader.add ( "label.beneficiary.regDate" );
		columnHeader.add ( "label.beneficiary.ssId" );
		columnHeader.add ( "label.beneficiary.skId" );		
		columnHeader.add ( "label.beneficiary.branchGeo" );		
		columnHeader.add ( "label.beneficiary.regionGeo" );
		columnHeader.add ( "label.beneficiary.cityCorporationGeo" );
		columnHeader.add ( "label.beneficiary.riskLevel" );
		columnHeader.add ( "label.beneficiary.type" );
		
		return columnHeader;
	}
	
	public static void prepareSearchPage ( HttpServletRequest request )
	{
		String modifyMotherURL = "beneficiaryAction.csmp?method=promptModifyBeneficiary&componentId=";
		String modifyChildURL = "beneficiaryAction.csmp?method=promptModifyChildBeneficiary&componentId=";
		request.setAttribute ( SESSION_KEYS.MODIFYING_MOTHER_URL, modifyMotherURL );
		request.setAttribute ( SESSION_KEYS.MODIFYING_CHILD_URL, modifyChildURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getColumnHeader () );
		
	}
	
	public static ArrayList<String> getColumnHeaderForChild ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		columnHeader.add ( "label.beneficiary.uniqueCode" );
		columnHeader.add ( "label.beneficiary.regDate" );
		// columnHeader.add ( "label.beneficiary.name" );
		
		columnHeader.add ( "label.beneficiary.motherId" );
		columnHeader.add ( "label.beneficiary.skId" );
		columnHeader.add ( "label.beneficiary.branchGeo" );
		columnHeader.add ( "label.beneficiary.regionGeo" );
		return columnHeader;
	}
	
	public static void prepareSearchPageForChild ( HttpServletRequest request )
	{
		String modifyURL = "beneficiaryAction.csmp?method=promptModifyChildBeneficiary&componentId=";
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getColumnHeaderForChild () );
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
	}
	
}
