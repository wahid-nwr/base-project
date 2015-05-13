package com.swiftcorp.portal.common.encryption;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.swiftcorp.portal.common.encryption.StringEncrypter.EncryptionException;

public class EncryptionUtil
{
	
	private static Log theLogger = LogFactory.getLog ( EncryptionUtil.class );
	private static AESSimple encrypter;
	
	public static void initStringEncrypter ( )
	{
		if ( encrypter == null )
		{
			try
			{
				encrypter = new AESSimple ();
			}
			catch (EncryptionException e)
			{
				theLogger.error ( "initStringEncrypter(): error", e );
			}
		}
	}
	
	public static String encrypt ( String toBeEncrypted )
	{
		initStringEncrypter ();
		
		String encryptedString = "";
		if ( toBeEncrypted != null )
		{
			encryptedString = new String ( toBeEncrypted );
			try
			{
				encryptedString = encrypter.encrypt ( toBeEncrypted );
			}
			catch (EncryptionException e)
			{
				// do nothing - plain text will be returned
			}
			catch (IllegalArgumentException i)
			{
				// do nothing - plain text will be returned
			}
		}
		return encryptedString;
	}
	
	public static String encrypt ( byte[] toBeEncrypted )
	{
		initStringEncrypter ();
		
		String encryptedString = "";
		if ( toBeEncrypted != null )
		{
			try
			{
				byte encryptedBytes[] = encrypter.encrypt ( toBeEncrypted, null );
				encryptedString = new String ( encryptedBytes );
			}
			catch (EncryptionException e)
			{
				// do nothing - plain text will be returned
			}
			catch (IllegalArgumentException i)
			{
				// do nothing - plain text will be returned
			}
		}
		return encryptedString;
	}
	
	public static String decrypt ( String toBeDecrypted )
	{
		initStringEncrypter ();
		
		String decryptedString = "";
		if ( toBeDecrypted != null )
		{
			decryptedString = new String ( toBeDecrypted );
			try
			{
				decryptedString = encrypter.decrypt ( toBeDecrypted );
			}
			catch (EncryptionException e)
			{
				// do nothing - encrypted text will be returned
			}
			catch (IllegalArgumentException i)
			{
				// do nothing - encrypted text will be returned
			}
		}
		return decryptedString;
	}
	
	public static String encryptToHexValue ( String toBeEncrypted )
	{
		initStringEncrypter ();
		
		String encryptedString = "";
		if ( toBeEncrypted != null )
		{
			encryptedString = new String ( toBeEncrypted );
			try
			{
				encryptedString = encrypter.encrypt ( toBeEncrypted );
			}
			catch (EncryptionException e)
			{
				// do nothing - plain text will be returned
			}
			catch (IllegalArgumentException i)
			{
				// do nothing - plain text will be returned
			}
		}
		return encryptedString;
	}
	
	public static String decryptFromHexValue ( String toBeDecrypted )
	{
		initStringEncrypter ();
		
		String decryptedString = "";
		if ( toBeDecrypted != null )
		{
			decryptedString = new String ( toBeDecrypted );
			try
			{
				decryptedString = encrypter.decrypt ( toBeDecrypted );
			}
			catch (EncryptionException e)
			{
				// do nothing - encrypted text will be returned
			}
			catch (IllegalArgumentException i)
			{
				// do nothing - encrypted text will be returned
			}
		}
		return decryptedString;
	}
	
	// public static void main (String arg[]) throws
	// EncryptionProcessingException
	// {
	// // String encStr = decrypt("7OjjJfq7rReVRlwKQfz96w==");
	// // System.out.println(" encryp  hex result : " + encStr);
	// //
	// // String encrypt = "8755a8b683a394ab10a04861e3e23fc249de5703";
	// //
	// // encrypt = md5MessageDigest(encrypt);
	// // //String depStr = decryptFromHexValue(encStr);
	// // //System.out.println(" decrypted from hex result : " +
	// DEFAULT_ENCRYPTION_KEY.length());
	//
	// String encreptedValue = EncryptionUtil.encryptToHexValue("admin");
	// System.out.println("Pass: admin : " + "; Encrepted HEX Value:" +
	// encreptedValue );
	// System.out.println("Decryted Pass: " +
	// EncryptionUtil.decryptFromHexValue(encreptedValue));
	//
	// }
	
}
