package com.swiftcorp.portal.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.household.dto.HouseholdDTO;
import com.swiftcorp.portal.user.UserSuccessResult;
import com.swiftcorp.portal.user.dto.SSDTO;
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
	
	/**
	 * This method is used to add an SS(Shastho Shebica) to the shasthoshebica table in db
	 * in the same way as user is added to the user table
	 * @param ssDto
	 * @return
	 * @throws SystemException
	 */
	public UserSuccessResult addSS ( SSDTO ssDto ) throws SystemException
	{
		log.info ( "add(): Enter" );
		
		UserSuccessResult successResult = new UserSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( ssDto );
			successResult.setMessage ( "Added Successfully." );
			successResult.setSsOperationResult ( ssDto );
		}
		catch (Exception e)
		{
			log.debug ( "add(SSDTO ssDto): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	/**
	 * This method is used to get an SSDTO from database's shasthoshebica table
	 * in the same way as user is get from the user table
	 * @param componentId
	 * @return SSDTO
	 * @throws SystemException
	 */
	public SSDTO getSS ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		SSDTO ssDto = null;
		try
		{
			ssDto = (SSDTO) getHibernateTemplate ().get ( SSDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return ssDto;
	}

	@Override
	/**
	 * This method will get the household List for shastho shebika form the db
	 * @param componentId
	 * @param ssdto
	 * @return ArrayList<HouseholdDTO>
	 */
	public List<HouseholdDTO> getHouseholdListForSS ( String ssId, String skId )
			throws SystemException
	{
		// TODO Auto-generated method stub
		log.info ( "getList: Enter" );
		
		List<HouseholdDTO> householdDTOList = null;
		StringBuffer queryStr = new StringBuffer ();
		SSDTO ssdto = null;
		queryStr.append ( "SELECT ssdto FROM SSDTO ssdto" );
		if ( ssId != null )
		{
			queryStr.append ( " WHERE ssdto.ssId =" + ssId +"and ssdto.skId="+skId );
		}
				
		try
		{
			ArrayList ssdtoList = (ArrayList) getHibernateTemplate ().find ( queryStr.toString () );
		
			if( ssdtoList !=null )
			{
				ssdto = (SSDTO) ssdtoList.get(0);
				householdDTOList = ssdto.getHouseholdList ();
			}
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return householdDTOList;
	}
	
}
