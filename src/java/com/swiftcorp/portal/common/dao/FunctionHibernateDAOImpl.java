package com.swiftcorp.portal.common.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.FunctionSuccessResult;
import com.swiftcorp.portal.common.dto.FunctionDTO;
import com.swiftcorp.portal.common.exception.SystemException;

public class FunctionHibernateDAOImpl extends HibernateDaoSupport implements IFunctionDAO
{
	protected static final Log log = LogFactory.getLog ( FunctionHibernateDAOImpl.class );
	
	public FunctionDTO get ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		FunctionDTO functionDTO = null;
		try
		{
			functionDTO = (FunctionDTO) getHibernateTemplate ().get ( FunctionDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return functionDTO;
	}
	
	public FunctionDTO get ( String unicodeCode ) throws SystemException
	{
		log.info ( "get(code): Enter" );
		log.info ( "get(code): code = " + unicodeCode );
		FunctionDTO functionDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from FunctionDTO functionDTO where functionDTO.uniqueCode=?", unicodeCode );
			if ( list.size () > 0 )
			{
				functionDTO = (FunctionDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return functionDTO;
	}
	
	public ArrayList<FunctionDTO> getList ( ) throws SystemException
	{
		return getList ( null, FunctionSortBy.uniqueCode );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<FunctionDTO> getList ( Long groupId, FunctionSortBy sortby )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<FunctionDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT functionDTO FROM FunctionDTO functionDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE functionDTO.groupId=" + groupId );
		}
		queryStr.append ( " ORDER BY " );
		queryStr.append ( getSortByStr ( sortby ) );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return result;
	}
	
	public FunctionSuccessResult add ( FunctionDTO functionDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		FunctionSuccessResult successResult = new FunctionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( functionDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( functionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(FunctionDTO functionDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public FunctionSuccessResult modify ( FunctionDTO functionDTO )
			throws SystemException
	{
		log.info ( "modify(): Enter" );
		FunctionSuccessResult successResult = new FunctionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( functionDTO );
			successResult.setMessage ( "Modified Successfully." );
			successResult.setOperationResult ( functionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modify(FunctionDTO functionDTO): Failed to modify.", e );
			throw new SystemException ( e );
		}
		log.info ( "modify(): Exit" );
		return successResult;
	}
	
	public FunctionSuccessResult remove ( FunctionDTO functionDTO )
			throws SystemException
	{
		log.info ( "remove(): Enter" );
		FunctionSuccessResult successResult = new FunctionSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( functionDTO );
			successResult.setMessage ( "removed Successfully." );
			successResult.setOperationResult ( functionDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(FunctionDTO functionDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	private String getSortByStr ( FunctionSortBy sortBy )
	{
		// default value
		String resultStr = "functionDTO.uniqueCode";
		if ( sortBy == FunctionSortBy.uniqueCode )
		{
			resultStr = "functionDTO.uniqueCode";
		}
		else if ( sortBy == FunctionSortBy.firstName )
		{
			resultStr = "functionDTO.firstName";
		}
		else if ( sortBy == FunctionSortBy.lastname )
		{
			resultStr = "functionDTO.lastName";
		}
		return resultStr;
		
	}
}
