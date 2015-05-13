/*
 * @ (#) CalendarUtils.java
 * 
 * Copyright (c) 2010 ClickDiagnostics Inc. All Rights Reserved. This software is the
 * confidential and proprietary information of ClickDiagnostics ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with ClickDiagnostics.
 */
package com.swiftcorp.portal.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class CalendarUtils
{
	// per day * number of days
	public final static long MILLIS_PER_DAY = 24 * 60 * 60 * 1000L;
	public final static long MILLIS_PER_MONTH = 30 * 24 * 60 * 60 * 1000L;
	public static String DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
	protected static final Log log = LogFactory.getLog ( CalendarUtils.class );
	private static String MyCompany_DATE_FORMAT = "yyyyMMdd";
	private static String MYSQL_DATE_FORMAT = "yyyy-MM-dd";
	public static String MYSQL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static String MHEALTH_DATETIME_FORMAT = "dd-MM-yyyy";
	private static String DATE_TIME_MILLIS_FORMAT = "yyyyMMddHHmmss";
	
	@SuppressWarnings("static-access")
	public static boolean isToday ( Calendar creationDate )
	{
		Calendar today = Calendar.getInstance ();
		if ( today.DAY_OF_MONTH == creationDate.DAY_OF_MONTH )
		{
			if ( today.MONTH == creationDate.MONTH )
			{
				if ( today.YEAR == creationDate.YEAR )
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static Date stringToDate(String dateString)
	{
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date today=null;
			try 
			{
				today = df.parse(dateString);           
		       // System.out.println("Today = " + df.format(today));
			} 
			catch (ParseException e) 
			{
	          e.printStackTrace();
			}
		return today;
	}
	public static Calendar addDayToCurrentDate ( int day )
	{
		Calendar calendar = Calendar.getInstance ();
		calendar.setTime ( new Date () );
		calendar.add ( Calendar.DAY_OF_MONTH, day );
		
		return calendar;
	}
	
	public static Calendar getCurrentCalendar ( )
	{
		Calendar calendar = Calendar.getInstance ();
		calendar.setTime ( new Date () );
		
		// return the cal
		return calendar;
	}
	
	public static Calendar addDayToCalendar ( Calendar calendar, int day )
	{
		calendar.add ( Calendar.DAY_OF_MONTH, day );
		
		return calendar;
	}
	
	public static Calendar addMonthToCalendar ( Calendar calendar, int month )
	{
		calendar.add ( Calendar.MONTH, month );
		return calendar;
	}
	
	public static String getDateStringFromCalendar ( Calendar calendar )
	{
		DateFormat dateFormat = new SimpleDateFormat ( MHEALTH_DATETIME_FORMAT );
		String dateString = dateFormat.format ( calendar.getTime () );
		
		// now return the string
		return dateString;
	}
	
	public static Calendar stringToCalendarMhealthFormat ( String dateString )
	{
		Calendar cal = Calendar.getInstance ();
		if ( dateString == null || dateString.equals ( "" ) )
		{
			return cal;
		}
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat ( MHEALTH_DATETIME_FORMAT );
			Date myDate = dateFormat.parse ( dateString );
			cal.setTime ( myDate );
		}
		catch (Exception e)
		{
			log.info ( "getDateAsCalendar() : ", e );
		}
		return cal;
	}
	
	public static Calendar stringToCalendarMillis ( String millis )
	{
		Calendar cal = Calendar.getInstance ();
		if ( millis == null || millis.equals ( "" ) )
		{
			return cal;
		}
		try
		{
			Date date1 = new Date ();
			date1.setTime ( Long.parseLong ( millis ) );
			cal.setTime ( date1 );
		}
		catch (Exception e)
		{
			log.info ( "getDateAsCalendar() : ", e );
		}
		return cal;
	}
	
	public static Calendar stringToCalendar ( String dateString )
	{
		Calendar cal = Calendar.getInstance ();
		if ( dateString == null || dateString.equals ( "" ) )
		{
			return cal;
		}
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat ( MYSQL_DATE_FORMAT );
			Date myDate = dateFormat.parse ( dateString );
			cal.setTime ( myDate );
		}
		catch (Exception e)
		{
			log.info ( "getDateAsCalendar() : ", e );
		}
		return cal;
	}
	
	public static Calendar stringToCalendarFormatterFormat ( String dateString, String formatter )
	{
		Calendar cal = Calendar.getInstance ();
		if ( dateString == null || dateString.equals ( "" ) )
		{
			return cal;
		}
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat ( formatter );
			Date myDate = dateFormat.parse ( dateString );
			cal.setTime ( myDate );
		}
		catch (Exception e)
		{
			log.info ( "getDateAsCalendar() : ", e );
		}
		return cal;
	}
	
	public static String calendarToString ( Calendar cal )
	{
		String strDate = "";
		try
		{
			if ( cal != null )
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat ( MYSQL_DATE_FORMAT );
				strDate = dateFormat.format ( cal.getTime () );
			}
		}
		catch (Exception e)
		{
			log.info ( "getDateAsString() : ", e );
		}
		return strDate;
	}

	public static String calendarToStringDateTimeFormat ( Calendar cal )
	{
		String strDate = "";
		try
		{
			if ( cal != null )
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat ( MYSQL_DATETIME_FORMAT );
				strDate = dateFormat.format ( cal.getTime () );
			}
		}
		catch (Exception e)
		{
			log.info ( "getDateAsString() : ", e );
		}
		return strDate;
	}

	public static String calendarToStringMySqlDateTimeFormat ( Calendar cal )
	{
		String strDate = "";
		try
		{
			if ( cal != null )
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat ( MYSQL_DATETIME_FORMAT );
				strDate = dateFormat.format ( cal.getTime () );
			}
		}
		catch (Exception e)
		{
			log.info ( "getDateAsString() : ", e );
		}
		return strDate;
	}
	
	public static String calendarToStringForMyCompany ( Calendar cal )
	{
		String strDate = "";
		try
		{
			if ( cal != null )
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat ( MyCompany_DATE_FORMAT );
				strDate = dateFormat.format ( cal.getTime () );
			}
		}
		catch (Exception e)
		{
			log.info ( "calendarToStringForMyCompany() : ", e );
		}
		return strDate;
	}
	
	public static String getCurrentDateString ( )
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat ( MyCompany_DATE_FORMAT );
		String strDate = dateFormat.format ( new Date () );
		
		return strDate;
	}
	
	public static Calendar getLastdayOfMonth ( int month, int year )
	{
		Calendar cal = Calendar.getInstance ();
		
		int days = 30;
		if ( month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 )
		{
			days = 31;
		}
		else if ( month == 2 )
		{
			// need to calculate leap year
			days = 28;
		}
		cal.set ( Calendar.DAY_OF_MONTH, days );
		cal.set ( Calendar.MONTH, month - 1 ); // In calendar, Month start from
												// 0
												// Not in 1
		cal.set ( Calendar.YEAR, year );
		return cal;
	}
	
	public static Calendar getFirstdayOfMonth ( int month, int year )
	{
		Calendar cal = Calendar.getInstance ();
		
		int days = 1;
		/*if ( month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 )
		{
			days = 31;
		}
		else if ( month == 2 )
		{
			// need to calculate leap year
			days = 28;
		}*/
		cal.set ( Calendar.DAY_OF_MONTH, days );
		cal.set ( Calendar.MONTH, month - 1 ); // In calendar, Month start from
												// 0
												// Not in 1
		cal.set ( Calendar.YEAR, year );
		return cal;
	}
	
	public static void setMYSQL_DATETIME_FORMAT ( String mYSQL_DATETIME_FORMAT )
	{
		MYSQL_DATETIME_FORMAT = mYSQL_DATETIME_FORMAT;
	}
	
	public static String getMYSQL_DATETIME_FORMAT ( )
	{
		return MYSQL_DATETIME_FORMAT;
	}
	
	public static void main ( String arg[] )
	{
		try
		{
			Calendar calendar = getLastdayOfMonth ( 10, 2009 );
			System.out.println ( "Date as MySQL Format = " + calendarToString ( calendar ) );
			System.out.println ( "Date as MyCompany Format = " + calendarToStringForMyCompany ( calendar ) );
			System.out.println ( " Now loading ---------" + calendar.get ( Calendar.MONTH ) );
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}
	
	@Deprecated
	public static Calendar addMonthDayToCal ( Calendar cal, int month, int day )
	{
		Date d = new Date ();
		int daysToAdd = 0;
		// Calendar cal = Calendar.getInstance();
		int currentMonth = cal.get ( 2 );
		long t = 0;
		
		t = d.getTime ();
		if ( month > 0 )
		{
			// cal.get ( month );
			for ( int i = 0; i < month; i++ )
			{
				if ( currentMonth < 12 )
				{
					if ( currentMonth == Calendar.JANUARY )
					{
						cal.set ( 2, Calendar.JANUARY );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.FEBRUARY )
					{
						cal.set ( 2, Calendar.FEBRUARY );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.MARCH )
					{
						cal.set ( 2, Calendar.MARCH );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.APRIL )
					{
						cal.set ( 2, Calendar.APRIL );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.MAY )
					{
						cal.set ( 2, Calendar.MAY );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.JUNE )
					{
						cal.set ( 2, Calendar.JUNE );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.JULY )
					{
						cal.set ( 2, Calendar.JULY );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.AUGUST )
					{
						cal.set ( 2, Calendar.AUGUST );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.SEPTEMBER )
					{
						cal.set ( 2, Calendar.SEPTEMBER );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.OCTOBER )
					{
						cal.set ( 2, Calendar.OCTOBER );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.NOVEMBER )
					{
						cal.set ( 2, Calendar.NOVEMBER );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					else if ( currentMonth == Calendar.DECEMBER )
					{
						cal.set ( 2, Calendar.DECEMBER );
						daysToAdd += cal.getActualMaximum ( Calendar.DAY_OF_MONTH );
					}
					currentMonth++;
				}
				else
				{
					currentMonth = 0;
					i--;
				}
			}
		}
		daysToAdd += day;
		// add the desired number of days to the long value using milli seconds
		
		t += daysToAdd * MILLIS_PER_DAY;
		cal.setTime ( new Date ( t ) );
		// and now you can format the date the way you want
		String result = Integer.toString ( cal.get ( Calendar.DAY_OF_MONTH ) ) + "-" + Integer.toString ( cal.get ( Calendar.MONTH ) + 1 ) + "-" + Integer.toString ( cal.get ( Calendar.YEAR ) );
		System.out.println ( "result month add::" + result );
		return cal;
	}
	
	public static Calendar getCalendarForSchedule ( Calendar cal )
	{
		// set the hour min sec
		// cal.set ( Calendar.HOUR_OF_DAY,
		// SchedulingConstants.DEFAULT_VISIT_HOUR );
		// cal.set ( Calendar.MINUTE, SchedulingConstants.DEFAULT_VISIT_MIN );
		// cal.set ( Calendar.SECOND, SchedulingConstants.DEFAULT_VISIT_SECOND
		// );
		
		// return cal
		return cal;
	}
	
	public static Calendar getDayInitialCalendar ( Calendar cal )
	{
		// get the time
		Date date = cal.getTime ();
		Calendar initialCal = Calendar.getInstance ();
		initialCal.setTime ( date );
		
		// set the hour min sec
		initialCal.set ( Calendar.HOUR, 0 );
		initialCal.set ( Calendar.HOUR_OF_DAY, 0 );
		initialCal.set ( Calendar.MINUTE, 0 );
		initialCal.set ( Calendar.SECOND, 0 );
		
		// return cal
		return initialCal;
	}
	
	public static Calendar getDayEndCalendar ( Calendar cal )
	{
		Date date = cal.getTime ();
		Calendar endCalendar = Calendar.getInstance ();
		endCalendar.setTime ( date );
		
		// set the hour min sec
		endCalendar.set ( Calendar.HOUR, 11 );
		endCalendar.set ( Calendar.HOUR_OF_DAY, 23 );
		endCalendar.set ( Calendar.MINUTE, 59 );
		endCalendar.set ( Calendar.SECOND, 59 );
		
		// return cal
		return endCalendar;
	}
}
