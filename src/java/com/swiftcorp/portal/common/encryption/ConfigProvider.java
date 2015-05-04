/**
 * 
 */
package com.swiftcorp.portal.common.encryption;

/**
 * This class is designed to provide some system related information to
 * encryption service. This is single instance class so that it is used by other
 * object as needed This is the only class through which encryption system
 * generated default password can be set by other application. This password
 * option is separated so that different application can set their own system
 * default password Suppose this encryption mechanism will be used in various
 * web/desktop applications for various client we dont want to use same system
 * generated password for all. Application's default password can be set through
 * this so that encryption mechanism will use later when encrypt(message,
 * null)/decrypt(message,null) will be called If anyone set system default
 * password, then make sure it is set before any encryption mechanism called.
 * For every application we need to set a password here and make a new jar of
 * this application and use this jar as needed CAUTION: If any client tells that
 * we should not hold password, then this API should not be used. Use
 * encrypt(message, password)/decrypt(message,password) which does not hold the
 * password information into the system for runtime in JVM.
 * 
 * @author Zahangir Alam
 * @since April 26, 2010
 */
class ConfigProvider
{
	/**
	 * This enum contains all the algorithm supported by SunJCE
	 * 
	 * @author Zahangir Alam
	 * @since April 28, 2010
	 */
	static enum Algorithm
	{
		AES, Blowfish, DES, DESede, PBEWithMD5AndDES, PBEWithHmacSHA1AndDESede, RC2, RC4, RC5, RSA
	};
	
	/**
	 * This enum defines all the mode type supported by SunJCE
	 * 
	 * @author Zahangir Alam
	 * @since April 28, 2010
	 */
	static enum Mode
	{
		NONE, CBC, CFB, ECB, OFB, PCBC
	};
	
	/**
	 * This enum defines the padding type supported by SunJCE
	 * 
	 * @author Zahangir Alam
	 * @since April 28, 2010
	 */
	static enum Padding
	{
		NoPadding, OAEPWithMD5AndMGF1Padding, PKCS5Padding, SSL3Padding
	};
	
	/**
	 * This enum defines all the password encryption related Mac
	 * 
	 * @author Zahangir Alam
	 * @since April 28, 2010
	 */
	static enum Mac
	{
		HmacMD5, HmacSHA1, PBEWithHmacMD5, PBEWithHmacSHA1, PBKDF2WithHmacSHA1, PBKDF2WithHmacMD5
	};
	
	/**
	 * This method is used to default Mode for this encryption It will be
	 * applied for all instance. Since one application will define which Mode
	 * will be used by talking with client, that's why it is configurable By
	 * default CBC Mode is returned
	 * 
	 * @return an valid String for padding
	 */
	static Mode getSystemDefaultMode ( )
	{
		return Mode.CBC;
	}
	
	/**
	 * This method is used to default padding for this encryption It will be
	 * applied for all instance. Since one application will define which padding
	 * will be used by talking with client, that's why it is configurable By
	 * default PKCS5Padding padding is returned
	 * 
	 * @return an valid String for padding
	 */
	static Padding getSystemDefaultPadding ( )
	{
		return Padding.PKCS5Padding;
	}
	
	/**
	 * This method is used to get the system default password. If any password
	 * is set the it is return that password. If the set password is null or
	 * empty then it returns the system generated default passowrd.
	 * 
	 * @return a valid password for the encryption mechanism
	 */
	static String getSystemDefaultPassword ( )
	{
		String pass = "&5-W5}$\"=5!~2iq4cl@click&x.sffv`N`\"mA@6,:'z`EDV#o]*H-&QY>CBUX]+$B?f2lKRsI00pSZ8Eet!@sfVDJQF5pujgX-vn2NPdBWUMZmVoISXG^5B=jbp8?-K6";
		return pass;
	}
	
	/**
	 * This method is used to provide the AlphaNumeric salt. Suppose AES needs
	 * 16 byte AlphaNumeric salt, Blowfish needs 8 bytes. I did not use
	 * SecureRandom which creates sometimes problem if we use padding. That's
	 * why it is alpha numeric. DON'T change it, if you are not sure about it.
	 * 
	 * @return AlphaNumeric salt of given parameter.
	 */
	static byte[] getAlphaNumericSalt ( int size )
	{
		if ( size <= 0 )
		{
			throw new RuntimeException ( "salt is negative or zero" );
		}
		
		// This string contains at least 16 bytes otherwise it will cause
		// runtime error
		String salt = "Together Initiatives Ltd.";
		byte[] result = new byte[size];
		for ( int i = 0; i < size; i++ )
		{
			result[i] = (byte) salt.charAt ( i );
		}
		return result;
	}
	
	/**
	 * This method is used to supply the system default iteration count to
	 * convert password to secret key By default it return 1024
	 * 
	 * @return an valid iteration count lie 20, 100, 1024 etc
	 */
	static int getSystemDefaultIterationCount ( )
	{
		return 1024;
	}
}
