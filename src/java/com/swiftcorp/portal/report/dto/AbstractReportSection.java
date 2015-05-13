/**
 * 
 */
package com.swiftcorp.portal.report.dto;

/**
 * @author asraful.haque
 * 
 */
public abstract class AbstractReportSection
{
	protected String name;
	
	public String getName ( )
	{
		return name;
	}
	
	public void setName ( String name )
	{
		this.name = name;
	}
	
}
