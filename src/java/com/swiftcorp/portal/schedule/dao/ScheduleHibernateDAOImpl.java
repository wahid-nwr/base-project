package com.swiftcorp.portal.schedule.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.swiftcorp.portal.common.exception.SystemException;
import com.swiftcorp.portal.common.util.CalendarUtils;
import com.swiftcorp.portal.schedule.ScheduleSuccessResult;
import com.swiftcorp.portal.schedule.dto.ScheduleDTO;
import com.swiftcorp.portal.user.dto.UserDTO;

public class ScheduleHibernateDAOImpl extends HibernateDaoSupport implements IScheduleDAO
{
	protected static final Log log = LogFactory.getLog ( ScheduleHibernateDAOImpl.class );
	
	public ScheduleDTO get ( Long componentId ) throws SystemException
	{
		log.info ( "get(id): Enter" );
		log.info ( "get(id): componentId = " + componentId );
		ScheduleDTO scheduleDTO = null;
		try
		{
			scheduleDTO = (ScheduleDTO) getHibernateTemplate ().get ( ScheduleDTO.class, componentId );
		}
		catch (Exception e)
		{
			log.info ( "get(id): ", e );
			throw new SystemException ( e );
		}
		log.info ( "get(id): Exit" );
		return scheduleDTO;
	}
	
	public ScheduleDTO get ( String unicodeCode ) throws SystemException
	{
		log.info ( "get(code): Enter" );
		log.info ( "get(code): code = " + unicodeCode );
		ScheduleDTO scheduleDTO = null;
		try
		{
			ArrayList list = (ArrayList) getHibernateTemplate ().find ( "from ScheduleDTO scheduleDTO where scheduleDTO.uniqueCode=?", unicodeCode );
			if ( list.size () > 0 )
			{
				scheduleDTO = (ScheduleDTO) list.get ( 0 );
			}
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return scheduleDTO;
	}
	
	public List<ScheduleDTO> getSheduleListByDate ( Calendar date )
			throws SystemException
	{
		log.info ( "get(code): Enter" );
		log.info ( "get(code): code = " + date );
		List<ScheduleDTO> list = null;
		try
		{
			list = (List<ScheduleDTO>) getHibernateTemplate ().find ( "from ScheduleDTO scheduleDTO where scheduleDTO.scheduleDate=?", date );
			
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return list;
	}
	
	public List<ScheduleDTO> getSheduleListByDateAndState ( Calendar date, int state )
			throws SystemException
	{
		log.info ( "get(code): code = " + date );
		List<ScheduleDTO> list = null;
		
		// get the day end and day start
		Calendar dayInitialTime = CalendarUtils.getDayInitialCalendar ( date );
		Calendar dayEndTime = CalendarUtils.getDayEndCalendar ( date );
		
		// params to pass in the db
		Object[] params =
		{
				dayInitialTime, dayEndTime, state
		};
		
		try
		{
			list = (List<ScheduleDTO>) getHibernateTemplate ().find ( "from ScheduleDTO scheduleDTO where scheduleDTO.scheduleDate>? and scheduleDTO.scheduleDate<? and scheduleDTO.state=?", params );
			// list = (List<ScheduleDTO>)
			// getHibernateTemplate().find("from ScheduleDTO scheduleDTO where scheduleDTO.scheduleDate<? and scheduleDTO.state=?",params);
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return list;
	}
	
	public ArrayList<ScheduleDTO> getList ( ) throws SystemException
	{
		return getList ( null, ScheduleSortBy.uniqueCode );
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ScheduleDTO> getList ( Long groupId, ScheduleSortBy sortby )
			throws SystemException
	{
		log.info ( "getList: Enter" );
		log.info ( "getList: sortby = " + sortby );
		
		ArrayList<ScheduleDTO> result = null;
		StringBuffer queryStr = new StringBuffer ();
		queryStr.append ( " SELECT scheduleDTO FROM ScheduleDTO scheduleDTO" );
		if ( groupId != null )
		{
			queryStr.append ( " WHERE scheduleDTO.groupId=" + groupId );
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
	
	public ScheduleSuccessResult add ( ScheduleDTO scheduleDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		ScheduleSuccessResult successResult = new ScheduleSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.save ( scheduleDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( scheduleDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(ScheduleDTO scheduleDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public ScheduleSuccessResult addOrUpdate ( ScheduleDTO scheduleDTO )
			throws SystemException
	{
		log.info ( "add(): Enter" );
		
		ScheduleSuccessResult successResult = new ScheduleSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.saveOrUpdate ( scheduleDTO );
			successResult.setMessage ( "Added Successfully." );
			successResult.setOperationResult ( scheduleDTO );
		}
		catch (Exception e)
		{
			log.debug ( "add(ScheduleDTO scheduleDTO): Failed to add." + e );
			throw new SystemException ( e );
		}
		log.info ( "add(): Exit" );
		return successResult;
	}
	
	public ScheduleSuccessResult modify ( ScheduleDTO scheduleDTO )
			throws SystemException
	{
		log.info ( "modify(): Enter" );
		ScheduleSuccessResult successResult = new ScheduleSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.update ( scheduleDTO );
			successResult.setMessage ( "Modified Successfully." );
			successResult.setOperationResult ( scheduleDTO );
		}
		catch (Exception e)
		{
			log.debug ( "modify(ScheduleDTO scheduleDTO): Failed to modify.", e );
			throw new SystemException ( e );
		}
		log.info ( "modify(): Exit" );
		return successResult;
	}
	
	public ScheduleSuccessResult remove ( ScheduleDTO scheduleDTO )
			throws SystemException
	{
		log.info ( "remove(): Enter" );
		ScheduleSuccessResult successResult = new ScheduleSuccessResult ();
		try
		{
			HibernateTemplate hibernateTemplate = getHibernateTemplate ();
			hibernateTemplate.delete ( scheduleDTO );
			successResult.setMessage ( "removed Successfully." );
			successResult.setOperationResult ( scheduleDTO );
		}
		catch (Exception e)
		{
			log.debug ( "remove(ScheduleDTO scheduleDTO): Failed to remove." + e );
			throw new SystemException ( e );
		}
		log.info ( "remove(): Exit" );
		return successResult;
	}
	
	private String getSortByStr ( ScheduleSortBy sortBy )
	{
		// default value
		String resultStr = "scheduleDTO.uniqueCode";
		if ( sortBy == ScheduleSortBy.uniqueCode )
		{
			resultStr = "scheduleDTO.uniqueCode";
		}
		else if ( sortBy == ScheduleSortBy.firstName )
		{
			resultStr = "scheduleDTO.firstName";
		}
		else if ( sortBy == ScheduleSortBy.lastname )
		{
			resultStr = "scheduleDTO.lastName";
		}
		return resultStr;
		
	}
	
	@Override
	public List<ScheduleDTO> getSheduleDTOByDateAndVisitItemId ( Calendar date, String visitItemId )
			throws SystemException
	{
		log.info ( "getSheduleDTOByDateAndVisitItemId: Enter" );
		log.info ( "getSheduleDTOByDateAndVisitItemId: date = " + CalendarUtils.calendarToStringForMyCompany ( date ) + " visitItemId = " + visitItemId );
		List<ScheduleDTO> list = null;
		
		Calendar cal1 = CalendarUtils.getDayInitialCalendar ( date );
		Calendar cal2 = CalendarUtils.getDayEndCalendar ( date );
		log.info ( "cal1 = " + CalendarUtils.calendarToStringForMyCompany ( cal1 ) + " cal2 = " + CalendarUtils.calendarToStringForMyCompany ( cal2 ) );
		Object[] params =
		{
				cal1, cal2, visitItemId
		};
		
		try
		{
			list = (List<ScheduleDTO>) getHibernateTemplate ().find ( "from ScheduleDTO scheduleDTO where scheduleDTO.scheduleDate > ? and scheduleDTO.scheduleDate < ? and scheduleDTO.visitItemId=?", params );
			
		}
		catch (Exception e)
		{
			log.error ( "getSheduleDTOByDateAndVisitItemId: ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "getSheduleDTOByDateAndVisitItemId: Exit" );
		return list;
	}
	
	public List<ScheduleDTO> getScheduleListByUserAndDate ( UserDTO user, Calendar scheduleDate )
			throws SystemException
	{
		
		log.info ( "getScheduleListByUserAndDate() enter " );
		List<ScheduleDTO> list = null;
		
		// get the day end and day start
		Calendar dayInitialTime = CalendarUtils.getDayInitialCalendar ( scheduleDate );
		Calendar dayEndTime = CalendarUtils.getDayEndCalendar ( scheduleDate );
		
		// params to pass in the db
		Object[] params =
		{
				dayInitialTime, dayEndTime, user
		};
		
		try
		{
			list = (List<ScheduleDTO>) getHibernateTemplate ().find ( "from ScheduleDTO scheduleDTO where scheduleDTO.scheduleDate>? and scheduleDTO.scheduleDate<? and scheduleDTO.user=?", params );
			// list = (List<ScheduleDTO>)
			// getHibernateTemplate().find("from ScheduleDTO scheduleDTO where scheduleDTO.scheduleDate<? and scheduleDTO.state=?",params);
		}
		catch (Exception e)
		{
			log.error ( "get(String uniqueCode): ", e );
			throw new SystemException ( e );
		}
		
		log.info ( "get(code): Exit" );
		return list;
		
	}
}
