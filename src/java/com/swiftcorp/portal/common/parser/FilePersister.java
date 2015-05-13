/**
 * 
 */
package com.swiftcorp.portal.common.parser;

import java.io.File;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author asraful.haque
 * 
 */
public class FilePersister extends MhealthDTODataProcessor
{
	// get the logger
	private static final Log logger = LogFactory.getLog ( FilePersister.class );
	
	// dir to save file
	private String fileSaveDir;
	// file type
	private String fileType;
	// relative path
	private String relativePathDir;
	
	@Override
	public Object parse ( Object obj )
	{
		// file path to return
		String fullFilePath = null;
		
		try
		{
			byte[] byteArray = (byte[]) obj;
			// get the file
			File file = this.getFile ();
			
			// now save the file
			FileUtils.writeByteArrayToFile ( file, byteArray );
			// this.setRelativePathDir("./BeneficiaryMultimedia/image/"+file.getName
			// ());
			fullFilePath = file.getPath ();
			
			fullFilePath = fullFilePath.replace ( "" + File.separatorChar, "/" );
			logger.debug ( "fullFilePath::::::::::::::::::" + fullFilePath );
			
			String fileRelativePath = this.getRelativePathDir () + "/" + file.getName ();
			
			// now return the path
			return fileRelativePath;
			
		}
		catch (Exception e)
		{
			// log error
			logger.error ( "Error occured while saving bytes to file " + e );
		}
		return this.getRelativePathDir ();
	}
	
	/**
	 * 
	 * @return
	 */
	private File getFile ( )
	{
		// for now get the file name with current time
		String fileName = "" + System.currentTimeMillis ();
		
		Random randomGenerator = new Random ();
		// for (int idx = 1; idx <= 10; ++idx){
		int randomInt = randomGenerator.nextInt ( 100 );
		
		// }
		
		logger.debug ( "Generate the file name " + fileName );
		
		// get fullFile path
		String fullFilePath = this.fileSaveDir + File.separatorChar + fileName + randomInt + "." + this.fileType;
		
		// get the file
		File file = new File ( fullFilePath );
		
		// return the file
		return file;
	}
	
	public String getFileSaveDir ( )
	{
		return fileSaveDir;
	}
	
	public void setFileSaveDir ( String fileSaveDir )
	{
		this.fileSaveDir = fileSaveDir;
	}
	
	public String getFileType ( )
	{
		return fileType;
	}
	
	public void setFileType ( String fileType )
	{
		this.fileType = fileType;
	}
	
	public String getRelativePathDir ( )
	{
		return relativePathDir;
	}
	
	public void setRelativePathDir ( String relativePathDir )
	{
		this.relativePathDir = relativePathDir;
	}
	
}
