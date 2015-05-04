package com.swiftcorp.portal.alert.dao;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.alert.AlertSuccessResult;
import com.swiftcorp.portal.alert.dto.AlertDTO;
import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.util.CalendarUtils;
import com.swiftcorp.portal.user.dto.UserDTO;

public class AlertHibernateDAOImpl extends HibernateDaoSupport implements IAlertDAO
{
	protected static final Log log = LogFactory.getLog ( AlertHibernateDAOImpl.class );
	
	public AlertDTO get ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		AlertDTO alertDTO = null;
		try
		{
			alertDTO = (AlertDTO) getHibernateTemplate ().get ( AlertDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return alertDTO;
	}
	
	public AlertDTO get ( String alertId ) throws SystemException
	{
		log.info ( "get(code): Enter" );
		log.info ( "get(code): code = " + alertId );
		AlertDTO alertDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from AlertDTO alertDTO where alertDTO.alertId=?", alertId );
			if ( list.size () > 0 )
			{
				alertDTO = (AlertDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return alertDTO;
	}
	
	public ArrayList<AlertDTO> getAlertBySkId ( UserDTO userDTO, Calendar date )
			throws SystemException
	{
		log.info ( "getAlertBySkId(skId): Enter" );
		log.info ( "getAlertBySkId(skId): skId = " + userDTO.getComponentId () );
		ArrayList<AlertDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		Calendar cal1 = CalendarUtils.getDayInitialCalendar ( date );
		Calendar cal2 = CalendarUtils.getDayEndCalendar ( date );
		
		Object[] params =
		{
				userDTO, cal1, cal2
		};
		
		queryStr.append ( "FROM AlertDTO alertDTO" );
		
		queryStr.append ( " where alertDTO.reciever= ? and alertDTO.alertDate > ? and alertDTO.alertDate < ?" );
		
		queryStr.append ( " ORDER BY " );
		queryStr.append ( getSortByStr ( AlertSortBy.alertId ) );
		System.out.println ( "qry:::" + queryStr );
		try
		{
			result = (ArrayList) getHibernateTemplate ().find ( queryStr.toString (), params );
			log.info ( "getList(): size = " + result.size () );
		}
		catch (Exception e)
		{
			throw new SystemException ( e );
		}
		log.info ( "getList: Exit" );
		return result;
	}
	
	public ArrayList<AlertDTO> getList ( ) throws SystemException
	{
		return getList ( null, AlertSortBy.alertId );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AlertDTO> getList ( Long groupId, AlertSortBy sortby )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<AlertDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT alertDTO FROM AlertDTO alertDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE alertDTO.groupId=" + groupId );
		}
		queryStr.append ( " ORDER BY " );
		queryStr.append ( getSortByStr ( sortby ) );
		System.out.println ( "query str::" + queryStr );
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
	
	public AlertSuccessResult add ( AlertDTO alertDTO ) throws SystemException
	{
		log.info ( "add(): Enter" );
		
		AlertSuccessResult successResult = new AlertSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( alertDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( alertDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(AlertDTO alertDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public AlertSuccessResult modify ( AlertDTO alertDTO )
			throws SystemException
	{
		log.info ( "modify(): Enter" );
		AlertSuccessResult successResult = new AlertSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( alertDTO );
			successResult.setMessage ( "Modified Successfully." );
			successResult.setOperationResult ( alertDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modify(AlertDTO alertDTO): Failed to modify.", e );
			throw new SystemException ( e );
		}
		log.info ( "modify(): Exit" );
		return successResult;
	}
	
	public AlertSuccessResult remove ( AlertDTO alertDTO )
			throws SystemException
	{
		log.info ( "remove(): Enter" );
		AlertSuccessResult successResult = new AlertSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( alertDTO );
			successResult.setMessage ( "removed Successfully." );
			successResult.setOperationResult ( alertDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(AlertDTO alertDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	private String getSortByStr ( AlertSortBy sortBy )
	{
		// default value
		String resultStr = "alertDTO.alertId";
		if ( sortBy == AlertSortBy.alertId )
		{
			resultStr = "alertDTO.alertId";
		}
		else if ( sortBy == AlertSortBy.firstName )
		{
			resultStr = "alertDTO.firstName";
		}
		else if ( sortBy == AlertSortBy.lastname )
		{
			resultStr = "alertDTO.lastName";
		}
		return resultStr;
		
	}
	
	
}
