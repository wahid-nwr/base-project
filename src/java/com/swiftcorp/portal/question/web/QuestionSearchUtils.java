package com.swiftcorp.portal.question.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.GlobalConstants;
import com.swiftcorp.portal.common.web.SESSION_KEYS;

public class QuestionSearchUtils
{
	protected static final Log log = LogFactory.getLog ( QuestionSearchUtils.class );
	
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
		String sortColumnStr = "questionId";
		if ( sortColumnNumber == 1 )
		{
			sortColumnStr = "questionId";
		}
		else if ( sortColumnNumber == 2 )
		{
			sortColumnStr = "questionName";
		}
		
		int offset = 0;
		if ( pageNumber != -1 && pageNumber > 1 )
		{
			offset = (pageNumber - 1) * resultPerPage;
		}
		
		String projectSqlQuery = " SELECT a.componentId, a.questionId, a.questionName, c.categoryName, b.answerTypeName  FROM question a, answertype b, questioncategory c ";
		projectSqlQuery += " WHERE a.categoryId= c.componentId and a.answertypeId=b.componentId and a.questionId like '%" + searchQueryInput + "%'";
		projectSqlQuery += " ORDER BY a.componentId ";
		projectSqlQuery += " " + sortOrder;
		projectSqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		
		log.info ( "prepareSqlQuery(): queryStr = " + projectSqlQuery );
		return projectSqlQuery;
	}
	
	// SQL for questionnaire
	
	public static String prepareSqlQueryForQuestionnaire ( HttpServletRequest request )
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
		String sortColumnStr = "questionnaireName";
		if ( sortColumnNumber == 1 )
		{
			sortColumnStr = "questionnaireName";
		}
		else if ( sortColumnNumber == 2 )
		{
			sortColumnStr = "questionnaireVersion";
		}
		else if ( sortColumnNumber == 3 )
		{
			sortColumnStr = "questionnaireStatus";
		}
		else if ( sortColumnNumber == 4 )
		{
			sortColumnStr = "questionnaireId";
		}
		
		int offset = 0;
		if ( pageNumber != -1 && pageNumber > 1 )
		{
			offset = (pageNumber - 1) * resultPerPage;
		}
		
		String projectSqlQuery = " SELECT a.componentId, a.questionnaireName, a.questionnaireVersion, b.statusName,  a.questionnaireTimestamp,a.questionnaireDescription, a.numberOfQuestion,a.questionnaireId  FROM questionnaire a,questionnairestatus b";
		projectSqlQuery += " WHERE a.questionnaireStatus=b.componentId and a.questionnaireId like '%" + searchQueryInput + "%'";
		projectSqlQuery += " ORDER BY a.componentId ";
		projectSqlQuery += " " + sortOrder;
		projectSqlQuery += " LIMIT " + offset + " , " + +resultPerPage;
		
		log.info ( "prepareSqlQueryForQuestionnaire(): queryStr = " + projectSqlQuery );
		return projectSqlQuery;
	}
	
	public static ArrayList<String> getColumnHeader ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		columnHeader.add ( "label.question.questionId" );
		columnHeader.add ( "label.question.name" );
		columnHeader.add ( "label.question.categoryType" );
		columnHeader.add ( "label.question.answerType" );
		return columnHeader;
	}
	
	public static ArrayList<String> getQuestionnairColumnHeader ( )
	{
		ArrayList<String> columnHeader = new ArrayList<String> ();
		columnHeader.add ( "label.questionnaire.questionnaireName" );
		columnHeader.add ( "label.questionnaire.questionnaireVersion" );
		columnHeader.add ( "label.questionnaire.questionnaireStatus" );
		columnHeader.add ( "label.questionnaire.questionnaireTimestamp" );
		columnHeader.add ( "label.questionnaire.questionnaireDescription" );
		columnHeader.add ( "label.questionnaire.numberOfQuestion" );
		columnHeader.add ( "label.questionnaire.questionnaireId" );
		return columnHeader;
	}
	
	public static void prepareSearchPage ( HttpServletRequest request )
	{
		String modifyURL = "questionAction.csmp?method=promptModifyQuestion&componentId=";
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getColumnHeader () );
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
	}
	
	public static void prepareQuestionnaireSearchPage ( HttpServletRequest request )
	{
		String modifyURL = "questionnaireAction.csmp?method=promptModifyQuestionnaire&componentId=";
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
		request.setAttribute ( SESSION_KEYS.COLUMN_HEADER_LIST, getQuestionnairColumnHeader () );
		request.setAttribute ( SESSION_KEYS.MODIFYING_URL, modifyURL );
	}
	
}
