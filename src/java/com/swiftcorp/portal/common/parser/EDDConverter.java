package com.swiftcorp.portal.common.parser;

import java.util.Calendar;

import com.swiftcorp.portal.common.util.CalendarUtils;

public class EDDConverter extends MhealthDTODataProcessor
{
	
	@Override
	public Object parse ( Object obj )
	{
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance ();
		try
		{
			String EDDDate = (String) obj;
			calendar = CalendarUtils.stringToCalendarMhealthFormat ( EDDDate );
		}
		catch (Exception e)
		{
			
		}
		return calendar;
	}
}
