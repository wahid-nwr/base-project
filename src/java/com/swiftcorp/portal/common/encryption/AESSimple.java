package com.swiftcorp.portal.common.encryption;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.swiftcorp.portal.common.encryption.ConfigProvider.Mode;
import com.swiftcorp.portal.common.encryption.StringEncrypter.EncryptionException;
import com.swiftcorp.portal.common.util.HexUtil;

/**
 * This class is designed to serve encryption and decryption utility Ref:
 * http://java.sun.com/developer/technicalArticles/Security/AES/AES_v1.html For
 * unrestricted key length active. Need to do the following things. Means this
 * class works perfectly after doing following thingss 1.Download Unlimited Key
 * Strength key policy from http://java.sun.com/javase/downloads/index.jsp Java
 * Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 6
 * 2. Copy the policy jars (local_policy.jar and US_export_policy.jar) into
 * JRE_HOME/lib/Security
 * 
 * All the API takes password, but it is not used in this implementation.
 * Because it use system default one If any application needs to change the
 * password, then change the password into SystemConfigProvider and make a jar
 * and then use this class
 * 
 * @author Zahangir Alam
 * @since April 23, 2010
 */
public class AESSimple
{
	
	static final int SALT_SIZE = 16;
	
	enum KeyLength
	{
		Bit128, Bit192, Bit256
	};
	
	protected SecretKey secretKey;
	protected Cipher cipher;
	protected boolean isInitialized;
	
	protected String UTF8 = "UTF-8";
	
	public AESSimple ( KeyLength keyLength ) throws EncryptionException
	{
		init ( keyLength );
	}
	
	public AESSimple ( ) throws EncryptionException
	{
		this ( KeyLength.Bit128 );
	}
	
	// encrypt as base64 and then convert hexa decimal value
	public String encrypt ( String originalMsg ) throws EncryptionException
	{
		return encryptAsBase64AsHex ( originalMsg, null );
	}
	
	// convert from hex to string, decode by base64 and then decode
	public String decrypt ( String encryptedMsg ) throws EncryptionException
	{
		return decryptFromBase64AndHex ( encryptedMsg, null );
	}
	
	// returned the initialized secretkey for the calculation
	protected SecretKey createSecretKey ( String password )
			throws EncryptionException
	{
		return secretKey;
	}
	
	public byte[] encrypt ( byte[] originalBytes, String password )
			throws EncryptionException
	{
		if ( originalBytes == null )
		{
			return null;
		}
		
		if ( originalBytes.length == 0 )
		{
			return originalBytes;
		}
		
		String pass = getValidPassword ( password );
		SecretKey secretKey = createSecretKey ( pass );
		
		Cipher cipher = null;
		try
		{
			cipher = getCipher ( Cipher.ENCRYPT_MODE, secretKey );
		}
		catch (EncryptionException e)
		{
			// we want to set secretKey null so that it is garbage collected as
			// soon as possible
			// because we dont want to hold the password in this module
			// we check password whether it is null or not. If not null that
			// means we dont hold the secretkey
			// because secretkey is designed for system key
			if ( password != null )
			{
				secretKey = null;
			}
			throw e;
		}
		
		try
		{
			return cipher.doFinal ( originalBytes );
		}
		catch (IllegalBlockSizeException e)
		{
			throw new EncryptionException ( e );
		}
		catch (BadPaddingException e)
		{
			throw new EncryptionException ( e );
		}
		finally
		{
			// we want to set secretKey null so that it is garbage collected as
			// soon as possible
			// because we dont want to hold the password in this module
			// we check password whether it is null or not. If not null that
			// means we dont hold the secretkey
			// because secretkey is designed for system key
			if ( password != null )
			{
				secretKey = null;
			}
		}
	}
	
	public byte[] decrypt ( byte[] encryptedBytes, String password )
			throws EncryptionException
	{
		if ( encryptedBytes == null )
		{
			return null;
		}
		
		if ( encryptedBytes.length == 0 )
		{
			return encryptedBytes;
		}
		
		String pass = getValidPassword ( password );
		SecretKey secretKey = createSecretKey ( pass );
		
		Cipher cipher = null;
		try
		{
			cipher = getCipher ( Cipher.DECRYPT_MODE, secretKey );
		}
		catch (EncryptionException e)
		{
			// we want to set secretKey null so that it is garbage collected as
			// soon as possible
			// because we dont want to hold the password in this module
			// we check password whether it is null or not. If not null that
			// means we dont hold the secretkey
			// because secretkey is designed for system key
			if ( password != null )
			{
				secretKey = null;
			}
			throw e;
		}
		
		try
		{
			return cipher.doFinal ( encryptedBytes );
		}
		catch (IllegalBlockSizeException e)
		{
			throw new EncryptionException ( e );
		}
		catch (BadPaddingException e)
		{
			throw new EncryptionException ( e );
		}
		finally
		{
			// we want to set secretKey null so that it is garbage collected as
			// soon as possible
			// because we dont want to hold the password in this module
			// we check password whether it is null or not. If not null that
			// means we dont hold the secretkey
			// because secretkey is designed for system key
			if ( password != null )
			{
				secretKey = null;
			}
		}
	}
	
	protected String encryptAsBase64AsHex ( String originalMsg, String password )
			throws EncryptionException
	{
		if ( originalMsg == null )
		{
			return null;
		}
		
		// if empty string then no need to encrypt it. but space enabled string
		// can be encrypted
		// that's why we are not trimming check here
		if ( originalMsg.length () == 0 )
		{
			return originalMsg;
		}
		
		String base64ecoded = encryptAsBase64 ( originalMsg, password );
		try
		{
			return HexUtil.convertStringToHex ( base64ecoded );
		}
		catch (IllegalArgumentException e)
		{
			throw new EncryptionException ( e );
		}
	}
	
	protected String decryptFromBase64AndHex ( String encryptedMsg, String password )
			throws EncryptionException
	{
		if ( encryptedMsg == null )
		{
			return null;
		}
		
		// if trimmed string still empty then it is not valid encrypted string.
		// so returning exception
		if ( encryptedMsg.length () == 0 || encryptedMsg.trim ().length () == 0 )
		{
			return encryptedMsg;
		}
		
		String base64 = null;
		try
		{
			base64 = HexUtil.convertHexToString ( encryptedMsg );
		}
		catch (IllegalArgumentException e)
		{
			throw new EncryptionException ( e );
		}
		
		return decryptFromBase64 ( base64, password );
	}
	
	protected String encryptAsBase64 ( String originalMsg, String password )
			throws EncryptionException
	{
		if ( originalMsg == null )
		{
			return null;
		}
		
		// if empty string then no need to encrypt it. but space enabled string
		// can be encrypted
		// that's why we are not trimming check here
		if ( originalMsg.length () == 0 )
		{
			return originalMsg;
		}
		
		try
		{
			byte[] encrypted = encrypt ( originalMsg.getBytes ( UTF8 ), password );
			BASE64Encoder base64encoder = new BASE64Encoder ();
			return base64encoder.encode ( encrypted );
		}
		catch (UnsupportedEncodingException e)
		{
			throw new EncryptionException ( e );
		}
	}
	
	protected String decryptFromBase64 ( String encryptedMsg, String password )
			throws EncryptionException
	{
		if ( encryptedMsg == null )
		{
			return null;
		}
		
		// if trimmed string still empty then it is not valid encrypted string.
		// so returning exception
		if ( encryptedMsg.length () == 0 || encryptedMsg.trim ().length () == 0 )
		{
			return encryptedMsg;
		}
		
		BASE64Decoder base64decoder = new BASE64Decoder ();
		try
		{
			byte[] cleartext = base64decoder.decodeBuffer ( encryptedMsg.trim () );
			byte[] decryptedBytes = decrypt ( cleartext, password );
			return new String ( decryptedBytes, UTF8 );
		}
		catch (IOException e)
		{
			throw new EncryptionException ( e );
		}
	}
	
	private void init ( KeyLength keyLength ) throws EncryptionException
	{
		if ( isInitialized )
		{
			return;
		}
		
		char[] passwordChars = ConfigProvider.getSystemDefaultPassword ().toCharArray ();
		
		SecretKeyFactory factory = null;
		try
		{
			factory = SecretKeyFactory.getInstance ( ConfigProvider.Mac.PBKDF2WithHmacSHA1.toString () );
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new EncryptionException ( e );
		}
		
		KeySpec spec = null;
		if ( keyLength == KeyLength.Bit128 )
		{
			spec = new PBEKeySpec ( passwordChars, ConfigProvider.getAlphaNumericSalt ( SALT_SIZE ), ConfigProvider.getSystemDefaultIterationCount (), 128 );
		}
		else if ( keyLength == KeyLength.Bit192 )
		{
			spec = new PBEKeySpec ( passwordChars, ConfigProvider.getAlphaNumericSalt ( SALT_SIZE ), ConfigProvider.getSystemDefaultIterationCount (), 192 );
		}
		else if ( keyLength == KeyLength.Bit256 )
		{
			spec = new PBEKeySpec ( passwordChars, ConfigProvider.getAlphaNumericSalt ( SALT_SIZE ), ConfigProvider.getSystemDefaultIterationCount (), 256 );
		}
		
		SecretKey tmp = null;
		try
		{
			tmp = factory.generateSecret ( spec );
		}
		catch (InvalidKeySpecException e)
		{
			throw new EncryptionException ( e );
		}
		
		secretKey = new SecretKeySpec ( tmp.getEncoded (), ConfigProvider.Algorithm.AES.toString () );
		
		try
		{
			String alg = ConfigProvider.Algorithm.AES.toString () + "/" + ConfigProvider.getSystemDefaultMode () + "/" + ConfigProvider.getSystemDefaultPadding ();
			cipher = Cipher.getInstance ( alg );
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new EncryptionException ( e );
		}
		catch (NoSuchPaddingException e)
		{
			throw new EncryptionException ( e );
		}
		
		isInitialized = true;
	}
	
	protected Cipher getCipher ( int cipherMode, SecretKey secretKey )
			throws EncryptionException
	{
		try
		{
			if ( ConfigProvider.getSystemDefaultMode () == Mode.ECB )
			{
				cipher.init ( cipherMode, secretKey );
			}
			else
			{
				cipher.init ( cipherMode, secretKey, new IvParameterSpec ( ConfigProvider.getAlphaNumericSalt ( SALT_SIZE ) ) );
			}
		}
		catch (InvalidKeyException e)
		{
			throw new EncryptionException ( e );
		}
		catch (InvalidAlgorithmParameterException e)
		{
			throw new EncryptionException ( e );
		}
		return cipher;
	}
	
	// this is not protected because, we don't want to override this password
	// picking
	// mechenism but some implementation in this package can override this as
	// they know how we can utilize it.
	private String getValidPassword ( String password )
	{
		if ( password == null )
		{
			return getSystemPassword ();
		}
		
		if ( password.trim ().length () == 0 )
		{
			return getSystemPassword ();
		}
		
		return password.trim ();
	}
	
	private String getSystemPassword ( )
	{
		// getting the system generated password. It is separeted method so that
		// it
		// can be different implementation
		return ConfigProvider.getSystemDefaultPassword ();
	}
	
	public static void main ( String[] args ) throws EncryptionException
	{
		AESSimple simple = new AESSimple ();
		String msg = "admin";
		String encr = simple.encrypt ( msg );
		
		System.out.println ( "encrypted msg=" + encr );
		String dec = simple.decrypt ( encr );
		System.out.println ( "decrypted msg=" + dec );
		
	}
	
}
