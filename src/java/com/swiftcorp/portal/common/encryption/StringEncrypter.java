package com.swiftcorp.portal.common.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringEncrypter
{
	
	static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	static final String DES_ENCRYPTION_SCHEME = "DES";
	
	private KeySpec keySpec;
	private SecretKeyFactory keyFactory;
	private Cipher cipher;
	
	private static final String UNICODE_FORMAT = "UTF8";
	
	public StringEncrypter ( String encryptionScheme, String encryptionKey )
			throws EncryptionException
	{
		if ( encryptionKey == null )
		{
			throw new IllegalArgumentException ( "encryption key was null" );
		}
		if ( encryptionKey.trim ().length () < 24 )
		{
			throw new IllegalArgumentException ( "encryption key was less than 24 characters" );
		}
		try
		{
			byte[] keyAsBytes = encryptionKey.getBytes ( UNICODE_FORMAT );
			
			if ( encryptionScheme.equals ( DESEDE_ENCRYPTION_SCHEME ) )
			{
				keySpec = new DESedeKeySpec ( keyAsBytes );
			}
			else if ( encryptionScheme.equals ( DES_ENCRYPTION_SCHEME ) )
			{
				keySpec = new DESKeySpec ( keyAsBytes );
			}
			else
			{
				throw new IllegalArgumentException ( "Encryption scheme not supported: " + encryptionScheme );
			}
			
			keyFactory = SecretKeyFactory.getInstance ( encryptionScheme );
			cipher = Cipher.getInstance ( encryptionScheme );
			
		}
		catch (InvalidKeyException e)
		{
			throw new EncryptionException ( e );
		}
		catch (UnsupportedEncodingException e)
		{
			throw new EncryptionException ( e );
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new EncryptionException ( e );
		}
		catch (NoSuchPaddingException e)
		{
			throw new EncryptionException ( e );
		}
		
	}
	
	protected String encrypt ( String unencryptedString )
			throws EncryptionException
	{
		if ( unencryptedString == null || unencryptedString.trim ().length () == 0 )
		{
			throw new IllegalArgumentException ( "unencrypted string was null or empty" );
		}
		try
		{
			SecretKey key = keyFactory.generateSecret ( keySpec );
			cipher.init ( Cipher.ENCRYPT_MODE, key );
			byte[] cleartext = unencryptedString.getBytes ( UNICODE_FORMAT );
			byte[] ciphertext = cipher.doFinal ( cleartext );
			
			BASE64Encoder base64encoder = new BASE64Encoder ();
			String encrtpStr = base64encoder.encode ( ciphertext );
			return encrtpStr;
		}
		catch (Exception e)
		{
			throw new EncryptionException ( e );
		}
	}
	
	protected String encrypt ( byte[] unencryptedbytes )
			throws EncryptionException
	{
		
		try
		{
			SecretKey key = keyFactory.generateSecret ( keySpec );
			cipher.init ( Cipher.ENCRYPT_MODE, key );
			// byte[] cleartext = unencryptedString.getBytes( UNICODE_FORMAT );
			byte[] ciphertext = cipher.doFinal ( unencryptedbytes );
			
			BASE64Encoder base64encoder = new BASE64Encoder ();
			String encrtpStr = base64encoder.encode ( ciphertext );
			return encrtpStr;
		}
		catch (Exception e)
		{
			throw new EncryptionException ( e );
		}
	}
	
	public String encryptToHexValue ( String unencryptedString )
			throws EncryptionException
	{
		if ( unencryptedString == null || unencryptedString.trim ().length () == 0 )
		{
			throw new IllegalArgumentException ( "unencrypted string was null or empty" );
		}
		try
		{
			SecretKey key = keyFactory.generateSecret ( keySpec );
			cipher.init ( Cipher.ENCRYPT_MODE, key );
			byte[] cleartext = unencryptedString.getBytes ( UNICODE_FORMAT );
			byte[] ciphertext = cipher.doFinal ( cleartext );
			
			BASE64Encoder base64encoder = new BASE64Encoder ();
			String encrtpStr = base64encoder.encode ( ciphertext );
			// convert to hex
			String hexVal = decimal2HexString ( encrtpStr );
			return hexVal;
		}
		catch (Exception e)
		{
			throw new EncryptionException ( e );
		}
	}
	
	public String decrypt ( String encryptedString ) throws EncryptionException
	{
		if ( encryptedString == null || encryptedString.trim ().length () <= 0 )
		{
			throw new IllegalArgumentException ( "encrypted string was null or empty" );
		}
		try
		{
			SecretKey key = keyFactory.generateSecret ( keySpec );
			cipher.init ( Cipher.DECRYPT_MODE, key );
			BASE64Decoder base64decoder = new BASE64Decoder ();
			byte[] cleartext = base64decoder.decodeBuffer ( encryptedString );
			byte[] ciphertext = cipher.doFinal ( cleartext );
			
			return bytes2String ( ciphertext );
		}
		catch (Exception e)
		{
			throw new EncryptionException ( e );
		}
	}
	
	public String decryptFromHexValue ( String encryptedString )
			throws EncryptionException
	{
		if ( encryptedString == null || encryptedString.trim ().length () <= 0 )
		{
			throw new IllegalArgumentException ( "encrypted string was null or empty" );
		}
		try
		{
			String encryptedDecimalStr = hex2DecimalStr ( encryptedString );
			
			SecretKey key = keyFactory.generateSecret ( keySpec );
			cipher.init ( Cipher.DECRYPT_MODE, key );
			BASE64Decoder base64decoder = new BASE64Decoder ();
			byte[] cleartext = base64decoder.decodeBuffer ( encryptedDecimalStr );
			byte[] ciphertext = cipher.doFinal ( cleartext );
			
			return bytes2String ( ciphertext );
		}
		catch (Exception e)
		{
			throw new EncryptionException ( e );
		}
	}
	
	private static String bytes2String ( byte[] bytes )
	{
		StringBuffer stringBuffer = new StringBuffer ();
		for ( int i = 0; i < bytes.length; i++ )
		{
			stringBuffer.append ( (char) bytes[i] );
		}
		return stringBuffer.toString ();
	}
	
	public static String decimal2HexString ( String decimalStr )
	{
		if ( decimalStr == null )
		{
			return decimalStr;
		}
		
		byte[] bytes = decimalStr.getBytes ();
		StringBuffer stringBuffer = new StringBuffer ();
		for ( int x = 0; x < bytes.length; x++ )
		{
			int bVal = bytes[x];
			stringBuffer.append ( Integer.toString ( bVal, 16 ) );
		}
		
		return stringBuffer.toString ();
		
	}
	
	public static String hex2DecimalStr ( String hexStr )
	{
		StringBuffer resultBuffer = new StringBuffer ();
		
		if ( hexStr == null )
		{
			return hexStr;
		}
		
		for ( int x = 0; x < hexStr.length (); x++ )
		{
			String twoDigitHexValue = hexStr.substring ( x, x + 2 );
			int iValue = Integer.parseInt ( twoDigitHexValue, 16 );
			char cValue = (char) iValue;
			resultBuffer.append ( cValue );
			x++;
		}
		return resultBuffer.toString ();
	}
	
	public static class EncryptionException extends Exception
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public EncryptionException ( Throwable t )
		{
			super ( t );
		}
	}
	
	// public static void main (String arg[])
	// {
	// try
	// {
	// StringEncrypter encrypter = new
	// StringEncrypter(StringEncrypter.DES_ENCRYPTION_SCHEME,"iuyiuyiuyiuyiuyiuyiuyiuyiuyiuyiuyuiy");
	// String encStr = encrypter.encryptToHexValue("1");
	// System.out.println(" encryp  hex result : " + encStr);
	// String depStr = encrypter.decryptFromHexValue(null);
	// System.out.println(" decrypted from hex result : " + depStr);
	//
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	//
	// }
}
