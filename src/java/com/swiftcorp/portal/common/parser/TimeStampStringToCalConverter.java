package com.swiftcorp.portal.common.parser;

import com.swiftcorp.portal.common.util.CalendarUtils;

public class TimeStampStringToCalConverter extends MhealthDTODataProcessor
{
	
	@Override
	public Object parse ( Object obj )
	{
		return CalendarUtils.stringToCalendarMillis ( (String) obj );
	}
	
}
