/**
 * 
 */
package com.swiftcorp.portal.common.parser;

/**
 * @author asraful.haque
 * 
 */
public class StringToIntConverter extends MhealthDTODataProcessor
{
	
	@Override
	public Object parse ( Object str )
	{
		String intString = (String) str;
		// integer to return
		System.out.println ( "Conversion Value :" + intString );
		int intValue = 0;
		
		try
		{
			intValue = Integer.parseInt ( intString );
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return intValue;
	}
	
}
