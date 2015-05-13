package com.swiftcorp.portal.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.user.UserSuccessResult;
import com.swiftcorp.portal.user.dto.UserDTO;

public class UserHibernateDAOImpl extends HibernateDaoSupport implements IUserDAO
{
	protected static final Log log = LogFactory.getLog ( UserHibernateDAOImpl.class );
	
	public UserDTO get ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		UserDTO userDTO = null;
		try
		{
			userDTO = (UserDTO) getHibernateTemplate ().get ( UserDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return userDTO;
	}
	
	@SuppressWarnings("unchecked")
	public UserDTO get ( String unicodeCode ) throws SystemException
	{
		log.info ( "get(code): Enter" );
		log.info ( "get(code): code = " + unicodeCode );
		UserDTO userDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from UserDTO userDTO where userDTO.uniqueCode=?", unicodeCode );
			if ( list.size () > 0 )
			{
				userDTO = (UserDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return userDTO;
	}
	
	public ArrayList<UserDTO> getList ( ) throws SystemException
	{
		return getList ( null, UserSortBy.uniqueCode );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<UserDTO> getList ( Long groupId, UserSortBy sortby )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<UserDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT userDTO FROM UserDTO userDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE userDTO.groupId=" + groupId );
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
	
	public UserSuccessResult add ( UserDTO userDTO ) throws SystemException
	{
		log.info ( "add(): Enter" );
		
		UserSuccessResult successResult = new UserSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( userDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( userDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(UserDTO userDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public UserSuccessResult modify ( UserDTO userDTO ) throws SystemException
	{
		log.info ( "modify(): Enter" );
		UserSuccessResult successResult = new UserSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( userDTO );
			successResult.setMessage ( "Modified Successfully." );
			successResult.setOperationResult ( userDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modify(UserDTO userDTO): Failed to modify.", e );
			throw new SystemException ( e );
		}
		log.info ( "modify(): Exit" );
		return successResult;
	}
	
	public UserSuccessResult remove ( UserDTO userDTO ) throws SystemException
	{
		log.info ( "remove(): Enter" );
		UserSuccessResult successResult = new UserSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( userDTO );
			successResult.setMessage ( "removed Successfully." );
			successResult.setOperationResult ( userDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(UserDTO userDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	private String getSortByStr ( UserSortBy sortBy )
	{
		// default value
		String resultStr = "userDTO.uniqueCode";
		if ( sortBy == UserSortBy.uniqueCode )
		{
			resultStr = "userDTO.uniqueCode";
		}
		else if ( sortBy == UserSortBy.firstName )
		{
			resultStr = "userDTO.firstName";
		}
		else if ( sortBy == UserSortBy.lastname )
		{
			resultStr = "userDTO.lastName";
		}
		return resultStr;
		
	}
	
}
