package com.swiftcorp.portal.geo.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.geo.GeoSuccessResult;
import com.swiftcorp.portal.geo.dto.GeoDTO;

public class GeoHibernateDAOImpl extends HibernateDaoSupport implements IGeoDAO
{
	protected static final Log log = LogFactory.getLog ( GeoHibernateDAOImpl.class );
	
	public GeoDTO get ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		GeoDTO geoDTO = null;
		try
		{
			geoDTO = (GeoDTO) getHibernateTemplate ().get ( GeoDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return geoDTO;
	}
	
	public GeoDTO get ( String unicodeCode ) throws SystemException
	{
		log.info ( "get(code): Enter" );
		log.info ( "get(code): code = " + unicodeCode );
		GeoDTO geoDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from GeoDTO geoDTO where geoDTO.code=?", unicodeCode );
			if ( list.size () > 0 )
			{
				geoDTO = (GeoDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return geoDTO;
	}
	
	public ArrayList<GeoDTO> getList ( ) throws SystemException
	{
		return getList ( null, GeoSortBy.code );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<GeoDTO> getList ( Long groupId, GeoSortBy sortby )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<GeoDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT geoDTO FROM GeoDTO geoDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE geoDTO.groupId=" + groupId );
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
	
	public ArrayList<GeoDTO> getGeoListByGeoType ( int geoType )
			throws SystemException
	{
		return getListByGeoType ( geoType, GeoSortByName.name );
	}
	
	public ArrayList<GeoDTO> getListByGeoType ( int geoType, GeoSortByName geoName )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + geoName );
		
		ArrayList<GeoDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT geoDTO FROM GeoDTO geoDTO" );
		if ( geoType != 0 )
		{
			queryStr.append ( " WHERE geoDTO.geoType=" + geoType );
		}
		queryStr.append ( " ORDER BY " );
		queryStr.append ( getSortByName ( geoName ) );
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
	
	public GeoSuccessResult add ( GeoDTO geoDTO ) throws SystemException
	{
		log.info ( "add(): Enter" );
		
		GeoSuccessResult successResult = new GeoSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( geoDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( geoDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(GeoDTO geoDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public GeoSuccessResult modify ( GeoDTO geoDTO ) throws SystemException
	{
		log.info ( "modify(): Enter" );
		GeoSuccessResult successResult = new GeoSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( geoDTO );
			successResult.setMessage ( "Modified Successfully." );
			successResult.setOperationResult ( geoDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modify(GeoDTO geoDTO): Failed to modify.", e );
			throw new SystemException ( e );
		}
		log.info ( "modify(): Exit" );
		return successResult;
	}
	
	public GeoSuccessResult remove ( GeoDTO geoDTO ) throws SystemException
	{
		log.info ( "remove(): Enter" );
		GeoSuccessResult successResult = new GeoSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( geoDTO );
			successResult.setMessage ( "removed Successfully." );
			successResult.setOperationResult ( geoDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(GeoDTO geoDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	private String getSortByStr ( GeoSortBy sortBy )
	{
		// default value
		String resultStr = "geoDTO.geoNumber";
		if ( sortBy == GeoSortBy.code )
		{
			resultStr = "geoDTO.geoNumber";
		}
		else if ( sortBy == GeoSortBy.firstName )
		{
			resultStr = "geoDTO.firstName";
		}
		else if ( sortBy == GeoSortBy.lastname )
		{
			resultStr = "geoDTO.lastName";
		}
		return resultStr;
		
	}
	
	private String getSortByName ( GeoSortByName geoName )
	{
		String resultStr = "geoDTO.name";
		if ( geoName == GeoSortByName.name )
		{
			resultStr = "geoDTO.name";
		}
		return resultStr;
	}
}
