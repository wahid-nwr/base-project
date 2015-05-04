/**
 * 
 */
package com.swiftcorp.portal.dto;

import com.swiftcorp.portal.common.dto.GenericDTO;

/**
 * @author asraful.haque
 * 
 */
public abstract class ResponseDTO extends GenericDTO
{
	// success Message
	protected String successMessage = "Succeeded";
	
	// language
	protected String language = "English";
	
	// error flag
	protected int errorFlag;
	
	// error code
	protected String errorCode = "";
	
	// error message
	protected String errorMessage = "";
	
	// method to parse
	public String toXML ( )
	{
		// String xmlString = new CommonResponseXMLParser ().makeXmlData ( this
		// );
		// return null for now
		return null;
	}
	
	public String getSuccessMessage ( )
	{
		return successMessage;
	}
	
	public void setSuccessMessage ( String successMessage )
	{
		this.successMessage = successMessage;
	}
	
	public int getErrorFlag ( )
	{
		return errorFlag;
	}
	
	public void setErrorFlag ( int errorFlag )
	{
		this.errorFlag = errorFlag;
	}
	
	public String getLanguage ( )
	{
		return language;
	}
	
	public void setLanguage ( String language )
	{
		this.language = language;
	}
	
	public String getErrorCode ( )
	{
		return errorCode;
	}
	
	public void setErrorCode ( String errorCode )
	{
		this.errorCode = errorCode;
	}
	
	public String getErrorMessage ( )
	{
		return errorMessage;
	}
	
	public void setErrorMessage ( String errorMessage )
	{
		this.errorMessage = errorMessage;
	}
}
