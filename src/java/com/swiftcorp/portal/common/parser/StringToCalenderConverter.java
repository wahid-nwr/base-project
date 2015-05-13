/**
 * 
 */
package com.swiftcorp.portal.common.parser;

import com.swiftcorp.portal.common.util.CalendarUtils;

/**
 * @author asraful.haque
 * 
 */
public class StringToCalenderConverter extends MhealthDTODataProcessor
{
	
	@Override
	public Object parse ( Object str )
	{
		// TODO Auto-generated method stub
		return CalendarUtils.stringToCalendarMhealthFormat ( (String) str );
	}
	
}
