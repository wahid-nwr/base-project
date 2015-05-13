/**
 * 
 */
package com.swiftcorp.portal.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author asraful.haque
 * 
 */
public class StringUtils
{
	private static final Log logger = LogFactory.getLog ( StringUtils.class );
	
	/**
	 * Checks whether the version1 is upper or not
	 * 
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static boolean isUpperVersion ( String version1, String version2 )
	{
		
		version1 = version1.replace ( ".", "" );
		version2 = version2.replace ( ".", "" );
		
		if ( version1.endsWith ( "0" ) )
		{
			version1 = version1.substring ( 0, version1.length () - 1 );
		}
		
		if ( version2.endsWith ( "0" ) )
		{
			version2 = version2.substring ( 0, version2.length () - 1 );
		}
		
		if ( version1.compareTo ( version2 ) > 0 )
		{
			logger.debug ( "The version is upper in the isUpper function" );
			return true;
		}
		
		logger.debug ( "The versions are " + version1 + " " + version2 );
		
		return false;
	}
}
