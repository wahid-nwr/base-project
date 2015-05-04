/**
 * 
 */
package com.swiftcorp.portal.dto.request;

/**
 * @author asraful.haque
 * 
 */
public abstract class RequestDTO
{
	// dto type
	protected int dtoType;
	
	public int getDtoType ( )
	{
		return dtoType;
	}
	
	public void setDtoType ( int dtoType )
	{
		this.dtoType = dtoType;
	}
	
}
