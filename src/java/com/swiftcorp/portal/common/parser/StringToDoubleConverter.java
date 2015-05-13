package com.swiftcorp.portal.common.parser;

public class StringToDoubleConverter extends MhealthDTODataProcessor
{
	
	@Override
	public Object parse ( Object str )
	{
		String doubleString = (String) str;
		// double to return
		System.out.println ( "Conversion Value :" + doubleString );
		double doubleValue = 0.0;
		
		try
		{
			doubleValue = Double.parseDouble ( doubleString );
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		
		return doubleValue;
	}
	
}
