package com.swiftcorp.portal.role.dao;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.role.RoleSuccessResult;
import com.swiftcorp.portal.role.dto.RoleDTO;

public class RoleHibernateDAOImpl extends HibernateDaoSupport implements IRoleDAO
{
	protected static final Log log = LogFactory.getLog ( RoleHibernateDAOImpl.class );
	
	public RoleDTO get ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		RoleDTO roleDTO = null;
		try
		{
			roleDTO = (RoleDTO) getHibernateTemplate ().get ( RoleDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return roleDTO;
	}
	
	@SuppressWarnings("unchecked")
	public RoleDTO get ( String unicodeCode ) throws SystemException
	{
		log.info ( "get(code): Enter" );
		log.info ( "get(code): code = " + unicodeCode );
		RoleDTO roleDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from RoleDTO roleDTO where roleDTO.uniqueCode=?", unicodeCode );
			if ( list.size () > 0 )
			{
				roleDTO = (RoleDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return roleDTO;
	}
	
	public ArrayList<RoleDTO> getList ( ) throws SystemException
	{
		return getList ( null, RoleSortBy.uniqueCode );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<RoleDTO> getList ( Long groupId, RoleSortBy sortby )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<RoleDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT roleDTO FROM RoleDTO roleDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE roleDTO.groupId=" + groupId );
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
	
	public RoleSuccessResult add ( RoleDTO roleDTO ) throws SystemException
	{
		log.info ( "add(): Enter" );
		
		RoleSuccessResult successResult = new RoleSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.saveOrUpdate ( roleDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( roleDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(RoleDTO roleDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public RoleSuccessResult modify ( RoleDTO roleDTO ) throws SystemException
	{
		log.info ( "modify(): Enter" );
		RoleSuccessResult successResult = new RoleSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( roleDTO );
			successResult.setMessage ( "Modified Successfully." );
			successResult.setOperationResult ( roleDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modify(RoleDTO roleDTO): Failed to modify.", e );
			throw new SystemException ( e );
		}
		log.info ( "modify(): Exit" );
		return successResult;
	}
	
	public RoleSuccessResult remove ( RoleDTO roleDTO ) throws SystemException
	{
		log.info ( "remove(): Enter" );
		RoleSuccessResult successResult = new RoleSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( roleDTO );
			successResult.setMessage ( "removed Successfully." );
			successResult.setOperationResult ( roleDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(RoleDTO roleDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	private String getSortByStr ( RoleSortBy sortBy )
	{
		// default value
		String resultStr = "roleDTO.uniqueCode";
		if ( sortBy == RoleSortBy.uniqueCode )
		{
			resultStr = "roleDTO.uniqueCode";
		}
		else if ( sortBy == RoleSortBy.firstName )
		{
			resultStr = "roleDTO.firstName";
		}
		else if ( sortBy == RoleSortBy.lastname )
		{
			resultStr = "roleDTO.lastName";
		}
		return resultStr;
		
	}
	
}
