/**
 * 
 */
package com.swiftcorp.portal.beneficiary.util;

import java.util.Calendar;
import java.util.Date;

import com.swiftcorp.portal.common.util.CalendarUtils;

/**
 * @author asraful.haque
 * 
 */
public class MotherBeneficiaryUtil
{
	public static final float THIRD_TRIMESTER_MONTH = 6.0f;
	public static final float SECOND_TRIMESTER_MONTH = 3.0f;
	public static final float FIRST_TRIMESTER_MONTH = 0.0f;
	
	// tt first dose month
	
	// tt second dose
	public static final int FIRST_DOSE_MONTH = 5;
	public static final int SECOND_DOSE_MONTH = 8;
	
	public static int getTrimesterByLMP ( Calendar lmpCalendar )
	{
		// trimester to return
		int trimester = 1;
		
		// get the current calendar
		Calendar currentCal = CalendarUtils.getCurrentCalendar ();
		long timeDifference = currentCal.getTimeInMillis () - lmpCalendar.getTimeInMillis ();
		
		// get the day
		float month = timeDifference / CalendarUtils.MILLIS_PER_MONTH;
		
		if ( month >= THIRD_TRIMESTER_MONTH )
		{
			trimester = 3;
		}
		else if ( month >= SECOND_TRIMESTER_MONTH )
		{
			trimester = 2;
		}
		
		// return trimester
		return trimester;
	}
	
	public static Calendar getFirstDoseTTDateByLMP ( Calendar lmpCalendar )
	{
		// get the date
		Date lmpDate = lmpCalendar.getTime ();
		
		Calendar ttCalendar = Calendar.getInstance ();
		// now set the date
		ttCalendar.setTime ( lmpDate );
		// add 5 month to the calendar
		ttCalendar = CalendarUtils.addMonthToCalendar ( ttCalendar, FIRST_DOSE_MONTH );
		
		return ttCalendar;
	}
	
	public static Calendar getSecondDoseTTDateByLMP ( Calendar lmpCalendar )
	{
		// get the date
		Date lmpDate = lmpCalendar.getTime ();
		
		Calendar ttCalendar = Calendar.getInstance ();
		// now set the date
		ttCalendar.setTime ( lmpDate );
		
		// add 8 month to the calendar
		ttCalendar = CalendarUtils.addMonthToCalendar ( ttCalendar, SECOND_DOSE_MONTH );
		
		return ttCalendar;
	}
	
}
