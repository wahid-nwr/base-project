package com.swiftcorp.portal.common.parser;

import java.util.Calendar;
import java.util.Date;

public class LMPConverter extends MhealthDTODataProcessor
{
	
	@Override
	public Object parse ( Object obj )
	{
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance ();
		try
		{
			int lmpBefore = -(Integer.parseInt ( (String) obj ));
			// get current date
			
			calendar.setTime ( new Date () );
			// minus date from calendar
			calendar.add ( Calendar.DAY_OF_MONTH, lmpBefore );
		}
		catch (Exception e)
		{
			
		}
		return calendar;
	}
}
