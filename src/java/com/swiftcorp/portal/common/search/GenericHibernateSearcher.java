package com.swiftcorp.portal.common.search;

import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.search.exception.InvalidSQLSyntaxException;
import com.swiftcorp.portal.common.util.ValidationUtil;

public class GenericHibernateSearcher extends HibernateDaoSupport implements ISearcher
{
	private static final Log theLogger = LogFactory.getLog ( GenericHibernateSearcher.class );
	public void getChildGeo(String query) throws SQLException
	{
		Session session = getSession (); 
		/*if you are not using session factory, you can use ur own method to get session.*/
		Connection conn = session.connection(); 
		String sqlQuery = query;
		CallableStatement cs = null;
		try
		{
			cs = conn.prepareCall(sqlQuery);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cs.execute(); conn.close();
		session.close();
	}
	public SearchOperationResult search ( String query )
			throws SystemException, InvalidSQLSyntaxException
	{
		theLogger.info ( "search(String query):: Enter " );
		theLogger.debug ( "search(String query):: query=" + query );
		
		if ( ValidationUtil.isEmpty ( query ) )
		{
			throw new SystemException ( "QUERY STRING MUST NOT EMPTY OR NULL" );
		}
		
		ArrayList<ArrayList<String>> searchResult = null;
		int totalRow = 0;
		
		try
		{
			Session session = getSession ();
			SQLQuery sqlQuery = session.createSQLQuery ( query );
			
			List queryResults = sqlQuery.list ();
			
			totalRow = getTotalRowCount ( session, query );
			
			searchResult = new ArrayList<ArrayList<String>> ( 5 );
			
			if ( queryResults != null && queryResults.size () > 0 )
			{
				for ( Object queryResult : queryResults )
				{
					Object[] queryResultRow = (Object[]) queryResult;
					ArrayList<String> newRow = new ArrayList<String> ();
					for ( int index = 0; index < queryResultRow.length; index++ )
					{
						if ( queryResultRow[index] != null )
						{
							newRow.add ( queryResultRow[index].toString () );
						}
						else
						{
							newRow.add ( "" );
						}
					}
					
					searchResult.add ( newRow );
				}
			}
		}
		catch (DataAccessResourceFailureException e)
		{
			theLogger.error ( "DataAccessResourceFailureException occurs while generating search result.", e );
			throw new SystemException ( "DataAccessResourceFailureException occurs while generating search result.", e );
		}
		catch (HibernateException e)
		{
			theLogger.error ( "HibernateException occurs while generating search result.", e );
			if ( e instanceof SQLGrammarException || e.getCause () instanceof SQLGrammarException )
			{
				String errorMessage = "";
				if ( e.getCause () != null )
				{
					errorMessage = e.getCause ().getMessage ();
				}
				else
				{
					errorMessage = e.getMessage ();
				}
				
				throw new InvalidSQLSyntaxException ( "SQL GRAMMAR ERROR....\n" + errorMessage );
			}
			else
			{
				throw e;
			}
		}
		catch (IllegalStateException e)
		{
			theLogger.error ( "IllegalStateException occurs while generating search result.", e );
			throw new SystemException ( "IllegalStateException occurs while generating search result.", e );
		}
		
		SearchOperationResult searchOperationResult = new SearchOperationResult ();
		searchOperationResult.setSearchResult ( searchResult );
		searchOperationResult.setTotalRowCount ( totalRow );
		
		return searchOperationResult;
	}
	
	// For questionnair purpose
	
	public SearchOperationResult searchQuestionnaire ( String query )
			throws SystemException, InvalidSQLSyntaxException
	{
		theLogger.info ( "search(String query):: Enter " );
		theLogger.debug ( "search(String query):: query=" + query );
		
		if ( ValidationUtil.isEmpty ( query ) )
		{
			throw new SystemException ( "QUERY STRING MUST NOT EMPTY OR NULL" );
		}
		
		ArrayList<ArrayList<String>> searchResult = null;
		int totalRow = 0;
		
		try
		{
			Session session = getSession ();
			SQLQuery sqlQuery = session.createSQLQuery ( query );
			
			List queryResults = sqlQuery.list ();
			
			totalRow = getTotalRowCount ( session, query );
			
			searchResult = new ArrayList<ArrayList<String>> ( 5 );
			
			if ( queryResults != null && queryResults.size () > 0 )
			{
				for ( Object queryResult : queryResults )
				{
					Object[] queryResultRow = (Object[]) queryResult;
					ArrayList<String> newRow = new ArrayList<String> ();
					for ( int index = 0; index < queryResultRow.length; index++ )
					{
						if ( queryResultRow[index] != null )
						{
							newRow.add ( queryResultRow[index].toString () );
						}
						else
						{
							newRow.add ( "" );
						}
					}
					
					searchResult.add ( newRow );
				}
			}
		}
		catch (DataAccessResourceFailureException e)
		{
			theLogger.error ( "DataAccessResourceFailureException occurs while generating search result.", e );
			throw new SystemException ( "DataAccessResourceFailureException occurs while generating search result.", e );
		}
		catch (HibernateException e)
		{
			theLogger.error ( "HibernateException occurs while generating search result.", e );
			if ( e instanceof SQLGrammarException || e.getCause () instanceof SQLGrammarException )
			{
				String errorMessage = "";
				if ( e.getCause () != null )
				{
					errorMessage = e.getCause ().getMessage ();
				}
				else
				{
					errorMessage = e.getMessage ();
				}
				
				throw new InvalidSQLSyntaxException ( "SQL GRAMMAR ERROR....\n" + errorMessage );
			}
			else
			{
				throw e;
			}
		}
		catch (IllegalStateException e)
		{
			theLogger.error ( "IllegalStateException occurs while generating search result.", e );
			throw new SystemException ( "IllegalStateException occurs while generating search result.", e );
		}
		
		SearchOperationResult searchOperationResult = new SearchOperationResult ();
		searchOperationResult.setSearchResult ( searchResult );
		searchOperationResult.setTotalRowCount ( totalRow );
		
		return searchOperationResult;
	}
	
	private int getTotalRowCount ( Session session, String query )
	{
		int totalRow = 0;
		
		// TODO:: DOES NOT WORKING PROPERLY....CHEK THE IMPLEMENTATION
		
		// SQLQuery sqlQueryForTotalRow =
		// session.createSQLQuery("SELECT FOUND_ROWS()");
		//
		// List totalRowQueryResult = sqlQueryForTotalRow.list();
		//
		// if(totalRowQueryResult != null && totalRowQueryResult.size() > 0)
		// {
		// BigInteger bigInteger = (BigInteger) totalRowQueryResult.get(0);
		//
		// totalRow = bigInteger.intValue();
		// }
		
		// TODO:: ALTERNATE QUICK FIX...REMOVE THE BLOODY IMPLEMENTATION.
		
		String queryInlowerCase = query.toLowerCase ();
		
		int indexOfFrom = queryInlowerCase.indexOf ( "from " );
		int indexOfLIMIT = queryInlowerCase.lastIndexOf ( "limit" );
		
		String queryTotalRowCount = "SELECT COUNT(*) ";
		
		if ( indexOfLIMIT > 0 )
		{
			queryTotalRowCount += query.substring ( indexOfFrom, indexOfLIMIT );
		}
		else
		{
			queryTotalRowCount += query.substring ( indexOfFrom );
		}
		
		theLogger.debug ( "queryTotalRowCount=" + queryTotalRowCount );
		SQLQuery sqlQuery = session.createSQLQuery ( queryTotalRowCount );
		
		Object totalRowObj = sqlQuery.uniqueResult ();
		if ( totalRowObj != null && totalRowObj instanceof BigInteger )
		{
			totalRow = ((BigInteger) totalRowObj).intValue ();
			
		}
		
		return totalRow;
	}
	
}
