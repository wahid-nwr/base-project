/**
 * 
 */
package com.swiftcorp.portal.common.parser;

/**
 * @author asraful.haque
 * 
 */
public class StringToLongConverter extends MhealthDTODataProcessor
{
	
	@Override
	public Object parse ( Object str )
	{
		// TODO Auto-generated method stub
		return Long.parseLong ( (String) str );
	}
	
}
