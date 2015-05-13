/**
 * 
 */
package com.swiftcorp.portal.common.util;

/**
 * @author zahangir.alam
 * 
 */
public class HexUtil
{
	
	private static final char[] kDigits =
	{
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
			'd', 'e', 'f'
	};
	
	/**
	 * This method is used to convert an string to hexa decima digits If given
	 * str is null or empty it will trhow IllegalArgumentException
	 * 
	 * @param str
	 *            which will be converted to hexa decimal digits
	 * @return hexa decimal digits of given
	 * @throws IllegalArgumentException
	 *             if hex is null or empty it will trhow
	 *             IllegalArgumentException *
	 */
	public static String convertStringToHex ( String str )
			throws IllegalArgumentException
	{
		if ( str == null )
		{
			throw new IllegalArgumentException ( "str is null" );
		}
		
		// here I did not trim because " " can be converted to hexa decimal
		// digits which is
		// logical, only cares empty string then no need to convert it
		if ( str.length () == 0 )
		{
			throw new IllegalArgumentException ( "str is empty" );
		}
		
		return convertBytesToHex ( str.getBytes () );
	}
	
	/**
	 * This method is used to conver a hex decimal digits to a calculated string
	 * If given hex is null or empty it will trhow IllegalArgumentException
	 * 
	 * @param hex
	 *            which contains hex digits
	 * @return an string by converting hex digits
	 * @throws IllegalArgumentException
	 *             if hex is null or empty it will trhow
	 *             IllegalArgumentException
	 */
	public static String convertHexToString ( String hex )
			throws IllegalArgumentException
	{
		if ( hex == null )
		{
			throw new IllegalArgumentException ( "hex is null" );
		}
		
		if ( hex.trim ().length () == 0 )
		{
			throw new IllegalArgumentException ( "hex is empty" );
		}
		
		byte[] bytes = convertHexToBytes ( hex );
		return new String ( bytes );
		
	}
	
	/**
	 * This method is used to convert hex to bytes array.
	 * 
	 * @param hex
	 *            Which contains any hexa decimal value.
	 * @return bytes array converting from hex string if hex is valid.
	 * @throws IllegalArgumentException
	 *             If hex is null, is empty or invalid hex
	 */
	public static byte[] convertHexToBytes ( String hex )
			throws IllegalArgumentException
	{
		
		if ( hex == null )
		{
			throw new IllegalArgumentException ( "hex is null" );
		}
		
		String trimmedHex = hex.trim ();
		if ( hex.length () == 0 || trimmedHex.length () == 0 )
		{
			throw new IllegalArgumentException ( "hex is empty" );
		}
		
		if ( !isValidHex ( trimmedHex ) )
		{
			throw new IllegalArgumentException ( "hex contains invalid hex contents" );
		}
		
		if ( trimmedHex.length () % 2 != 0 )
		{
			throw new IllegalArgumentException ( "hex contains invalid hex contents for decrypt. It's length needs to be multiple of 2" );
		}
		
		char[] charHex = trimmedHex.toCharArray ();
		int length = charHex.length / 2;
		byte[] raw = new byte[length];
		for ( int i = 0; i < length; i++ )
		{
			int high = Character.digit ( charHex[i * 2], 16 );
			int low = Character.digit ( charHex[i * 2 + 1], 16 );
			
			int value = (high << 4) | low;
			if ( value > 127 )
				value -= 256;
			
			raw[i] = (byte) value;
		}
		return raw;
	}
	
	// need to validate it contains the valid hex digit
	private static boolean isValidHex ( String hex )
	{
		char[] charHex = hex.toCharArray ();
		int i;
		for ( i = 0; i < charHex.length; i++ )
		{
			boolean isValid = false;
			for ( int j = 0; j < kDigits.length; j++ )
			{
				if ( charHex[i] == kDigits[j] )
				{
					isValid = true;
					break;
				}
			}
			
			if ( !isValid )
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Turns array of bytes into string
	 * 
	 * @param buf
	 *            Array of bytes to convert to hex string
	 * @return Generated hex string
	 * @exception IllegalArgumentException
	 *                if buf is null or buf contains empty
	 */
	public static String convertBytesToHex ( byte buf[] )
			throws IllegalArgumentException
	{
		// first check the null, if so throw exception.
		if ( buf == null )
		{
			throw new IllegalArgumentException ( "buf is null" );
		}
		
		// if buf contains empty if so throw exception.
		if ( buf.length == 0 )
		{
			throw new IllegalArgumentException ( "buf is empty" );
		}
		
		// now we have valid object
		StringBuffer strbuf = new StringBuffer ( buf.length * 2 );
		int i;
		
		for ( i = 0; i < buf.length; i++ )
		{
			if ( ((int) buf[i] & 0xff) < 0x10 )
			{
				strbuf.append ( "0" );
			}
			
			strbuf.append ( Long.toString ( (int) buf[i] & 0xff, 16 ) );
		}
		
		return strbuf.toString ();
	}
}
