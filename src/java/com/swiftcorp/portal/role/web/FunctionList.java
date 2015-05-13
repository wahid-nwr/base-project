/**
 * 
 */
package com.swiftcorp.portal.role.web;

/**
 * @author asraful.haque
 * 
 */
public class FunctionList
{
	// functions array
	private String[] functions;
	
	// selected functions
	private String[] selectedFunctions;
	
	public String[] getFunctions ( )
	{
		return functions;
	}
	
	public void setFunctions ( String[] functions )
	{
		this.functions = functions;
	}
	
	public String[] getSelectedFunctions ( )
	{
		return selectedFunctions;
	}
	
	public void setSelectedFunctions ( String[] selectedFunctions )
	{
		this.selectedFunctions = selectedFunctions;
	}
	
}
